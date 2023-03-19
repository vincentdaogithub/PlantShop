package dao.order;

import dao.plant.PlantDAO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import obj.order.Order;
import obj.order.OrderDetail;
import obj.order.OrderStatuses;
import obj.plant.Plant;
import util.DBUtils;

public class OrderDAO {

    // Orders table
    private static final String ORDER_TABLE = "Orders";
    private static final String ORDER_ID_ORDER = "OrderID";
    private static final String ORDER_DATE = "OrdDate";
    private static final String SHIP_DATE = "shipdate";
    private static final String STATUS = "status";
    private static final String ACCOUNT_ID = "AccID";

    // OrderDetails table
    private static final String DETAIL_TABLE = "OrderDetails";
    private static final String DETAIL_ID = "DetailId";
    private static final String ORDER_ID_DETAIL = "OrderID";
    private static final String PLANT_ID = "FID";
    private static final String QUANTITY = "quantity";

    // queries
    private static final String GET_ORDER_QUERY = String.format(
            "SELECT * FROM %s AS a JOIN %s AS b ON a.%s = b.%s AND %s = ? AND a.%s = ?",
            ORDER_TABLE, DETAIL_TABLE, ORDER_ID_ORDER, ORDER_ID_DETAIL, ACCOUNT_ID, ORDER_ID_ORDER);

    private static final String GET_ORDERS_QUERY = String.format(
            "SELECT * FROM %s AS a JOIN %s AS b ON a.%s = b.%s and %s = ?",
            ORDER_TABLE, DETAIL_TABLE, ORDER_ID_ORDER, ORDER_ID_DETAIL, ACCOUNT_ID);

    private static final String INSERT_ORDER_QUERY = String.format(
            "INSERT INTO %s(%s, %s, %s, %s) VALUES (?, ?, ?, ?)",
            ORDER_TABLE, ORDER_DATE, SHIP_DATE, STATUS, ACCOUNT_ID);

    private static final String INSERT_DETAIL_QUERY = String.format(
            "INSERT INTO %s(%s, %s, %s) VALUES (?, ?, ?)",
            DETAIL_TABLE, ORDER_ID_DETAIL, PLANT_ID, QUANTITY);

    private static final String LATEST_ORDER_QUERY = String.format(
            "SELECT TOP (?) * FROM %s WHERE %s = ? ORDER BY %s DESC",
            ORDER_TABLE, ACCOUNT_ID, ORDER_ID_ORDER);

    private static final String CANCEL_ORDER_QUERY = String.format(
            "UPDATE %s SET %s = %s WHERE %s = ?",
            ORDER_TABLE, STATUS, OrderStatuses.CANCELLED.getStatusCode(), ORDER_ID_ORDER);

    private static final String UPDATE_ORDER_COMPLETED_QUERY = String.format(
            "UPDATE %s SET %s = %s WHERE %s <= ? AND %s <> %s",
            ORDER_TABLE, STATUS, OrderStatuses.COMPLETED.getStatusCode(), SHIP_DATE,
            STATUS, OrderStatuses.CANCELLED.getStatusCode());

    public static final boolean addOrder(Order order, OrderDetail orderDetail) {

        if (order == null || orderDetail == null) {
            return false;
        }

        try (
                Connection connection = DBUtils.makeConnection();) {

            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);

            try (
                    PreparedStatement insertOrder = connection.prepareStatement(INSERT_ORDER_QUERY);
                    PreparedStatement insertDetail = connection.prepareStatement(INSERT_DETAIL_QUERY);
                    PreparedStatement latestOrder = connection.prepareStatement(LATEST_ORDER_QUERY);) {

                insertOrder.setDate(1, Date.valueOf(order.getOrderDate()));
                insertOrder.setDate(2, Date.valueOf(order.getShipDate()));
                insertOrder.setInt(3, order.getStatus().getStatusCode());
                insertOrder.setInt(4, order.getAccountID());

                if (insertOrder.executeUpdate() != 1) {
                    throw new SQLException("Can't insert order");
                }

                latestOrder.setInt(1, 1);
                latestOrder.setInt(2, order.getAccountID());
                ResultSet tmp = latestOrder.executeQuery();

                final int currentOrderID;

                if (tmp.next()) {
                    currentOrderID = tmp.getInt(ORDER_ID_ORDER);
                } else {
                    throw new SQLException("Can't get the latest order ID");
                }

                insertDetail.setInt(1, currentOrderID);
                insertDetail.setInt(2, orderDetail.getPlant().getID());
                insertDetail.setInt(3, orderDetail.getQuantity());

                if (insertDetail.executeUpdate() != 1) {
                    throw new SQLException("Can't insert order detail");
                }

                connection.commit();
                return true;
            } catch (Exception e) {
                connection.rollback();
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    public static final boolean addOrders(Map<Order, OrderDetail> orders) {
        if (orders == null || orders.isEmpty()) {
            return false;
        }

        try (
                Connection connection = DBUtils.makeConnection();) {

            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);

            try (
                    PreparedStatement insertOrder = connection.prepareStatement(INSERT_ORDER_QUERY);
                    PreparedStatement insertDetail = connection.prepareStatement(INSERT_DETAIL_QUERY);
                    PreparedStatement latestOrder = connection.prepareStatement(LATEST_ORDER_QUERY);) {

                final int accountID = orders.keySet().toArray(new Order[0])[0].getAccountID();

                for (Map.Entry<Order, OrderDetail> e : orders.entrySet()) {
                    insertOrder.setDate(1, Date.valueOf(e.getKey().getOrderDate()));
                    insertOrder.setDate(2, Date.valueOf(e.getKey().getShipDate()));
                    insertOrder.setInt(3, e.getKey().getStatus().getStatusCode());
                    insertOrder.setInt(4, e.getKey().getAccountID());

                    insertOrder.addBatch();
                }

                final int[] insertOrderResults = insertOrder.executeBatch();

                for (int result : insertOrderResults) {
                    if (result != 1) {
                        throw new SQLException("Can't insert order");
                    }
                }

                latestOrder.setInt(1, insertOrderResults.length);
                latestOrder.setInt(2, accountID);
                ResultSet tmp = latestOrder.executeQuery();

                final ArrayList<Integer> currentOrderIDs = new ArrayList<>();

                while (tmp.next()) {
                    currentOrderIDs.add(tmp.getInt(ORDER_ID_ORDER));
                }

                if (currentOrderIDs.size() != insertOrderResults.length) {
                    throw new SQLException("Size of update batch does not match");
                }

                Collections.reverse(currentOrderIDs);
                int orderIndex = 0;

                for (Map.Entry<Order, OrderDetail> e : orders.entrySet()) {
                    insertDetail.setInt(1, currentOrderIDs.get(orderIndex));
                    insertDetail.setInt(2, e.getValue().getPlant().getID());
                    insertDetail.setInt(3, e.getValue().getQuantity());

                    orderIndex++;
                    insertDetail.addBatch();
                }

                final int[] insertDetailResults = insertDetail.executeBatch();

                if (insertDetailResults.length != insertOrderResults.length) {
                    throw new SQLException("Sizes of order details and orders don't match");
                }

                for (int result : insertDetailResults) {
                    if (result != 1) {
                        throw new SQLException("Can't insert order detail");
                    }
                }

                connection.commit();
                return true;
            } catch (Exception e) {
                connection.rollback();
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    public static final boolean cancelOrder(Integer orderID) {

        if (orderID == null) {
            return false;
        }

        try (
                Connection connection = DBUtils.makeConnection();) {

            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);

            try (
                    PreparedStatement cancelOrder = connection.prepareStatement(CANCEL_ORDER_QUERY);) {

                cancelOrder.setInt(1, orderID);

                if (cancelOrder.executeUpdate() != 1) {
                    throw new SQLException("Can't cancel the order");
                }

                connection.commit();
                return true;

            } catch (Exception exception) {
                connection.rollback();
                return false;
            }

        } catch (Exception exception) {
            return false;
        }
    }

    public static final Map<Order, OrderDetail> getOrder(Integer orderID, Integer accountID) {

        if (orderID == null || accountID == null) {
            return null;
        }

        try (
                Connection connection = DBUtils.makeConnection();) {

            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

            try (
                    PreparedStatement getOrder = connection.prepareStatement(
                            GET_ORDER_QUERY,
                            ResultSet.TYPE_SCROLL_SENSITIVE,
                            ResultSet.CONCUR_READ_ONLY);
                    PreparedStatement updateOrders = connection.prepareStatement(UPDATE_ORDER_COMPLETED_QUERY);) {

                updateOrders.setDate(1, Date.valueOf(LocalDate.now()));
                updateOrders.executeUpdate();

                getOrder.setInt(1, accountID);
                getOrder.setInt(2, orderID);
                ResultSet result = getOrder.executeQuery();

                if (!result.next() || !result.isLast()) {
                    throw new SQLException("Can't get order");
                }

                Order order = new Order(
                        result.getInt(ORDER_ID_ORDER),
                        result.getDate(ORDER_DATE).toLocalDate(),
                        result.getDate(SHIP_DATE).toLocalDate(),
                        OrderStatuses.convertIntToStatus(result.getInt(STATUS)),
                        result.getInt(ACCOUNT_ID)
                );

                Plant plant = PlantDAO.getPlant(result.getInt(PLANT_ID));

                if (plant == null) {
                    throw new SQLException("Can't get plant");
                }

                OrderDetail orderDetail = new OrderDetail(
                        result.getInt(DETAIL_ID),
                        result.getInt(ORDER_ID_DETAIL),
                        plant,
                        result.getInt(QUANTITY)
                );

                Map<Order, OrderDetail> orderMap = new LinkedHashMap<>();
                orderMap.put(order, orderDetail);

                connection.commit();
                return orderMap;
            } catch (Exception exception) {
                connection.rollback();
                return new LinkedHashMap<>();
            }
        } catch (Exception exception) {
            return new LinkedHashMap<>();
        }
    }

    public static final Map<Order, OrderDetail> getOrders(Integer accountID) {

        if (accountID == null) {
            return null;
        }

        try (
                Connection connection = DBUtils.makeConnection();) {

            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

            try (
                    PreparedStatement getOrders = connection.prepareStatement(GET_ORDERS_QUERY);
                    PreparedStatement updateOrders = connection.prepareStatement(UPDATE_ORDER_COMPLETED_QUERY);) {

                updateOrders.setDate(1, Date.valueOf(LocalDate.now()));
                updateOrders.executeUpdate();

                getOrders.setInt(1, accountID);
                ResultSet results = getOrders.executeQuery();
                Map<Order, OrderDetail> orderMap = new LinkedHashMap<>();

                while (results.next()) {
                    Order order = new Order(
                            results.getInt(ORDER_ID_ORDER),
                            results.getDate(ORDER_DATE).toLocalDate(),
                            results.getDate(SHIP_DATE).toLocalDate(),
                            OrderStatuses.convertIntToStatus(results.getInt(STATUS)),
                            results.getInt(ACCOUNT_ID)
                    );

                    Plant plant = PlantDAO.getPlant(results.getInt(PLANT_ID));

                    if (plant == null) {
                        throw new SQLException("Can't get plant");
                    }

                    OrderDetail orderDetail = new OrderDetail(
                            results.getInt(DETAIL_ID),
                            results.getInt(ORDER_ID_DETAIL),
                            plant,
                            results.getInt(QUANTITY)
                    );

                    orderMap.put(order, orderDetail);
                }

                connection.commit();
                return orderMap;
            } catch (Exception exception) {
                connection.rollback();
                return new LinkedHashMap<>();
            }
        } catch (Exception exception) {
            return new LinkedHashMap<>();
        }
    }

    public static final boolean orderAgain(Integer orderID, Integer accountID) {
        if (orderID == null || accountID == null) {
            throw new NullPointerException("Null arguments");
        }

        try (
                Connection connection = DBUtils.makeConnection();) {

            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);

            try (
                    PreparedStatement getOrder = connection.prepareStatement(
                            GET_ORDER_QUERY,
                            ResultSet.TYPE_SCROLL_SENSITIVE,
                            ResultSet.CONCUR_READ_ONLY);
                    PreparedStatement insertOrder = connection.prepareStatement(INSERT_ORDER_QUERY);
                    PreparedStatement insertDetail = connection.prepareStatement(INSERT_DETAIL_QUERY);
                    PreparedStatement latestOrder = connection.prepareStatement(LATEST_ORDER_QUERY);) {

                getOrder.setInt(1, accountID);
                getOrder.setInt(2, orderID);
                ResultSet result = getOrder.executeQuery();

                if (!result.next() || !result.isLast()) {
                    throw new SQLException("Can't get order");
                }

                Order order = OrderFactory.createOrder(accountID, OrderStatuses.PROCESSING);
                order.setOrderID(result.getInt(ORDER_ID_ORDER));

                Plant plant = PlantDAO.getPlant(result.getInt(PLANT_ID));

                if (plant == null) {
                    throw new SQLException("Can't get plant");
                }

                OrderDetail orderDetail = new OrderDetail(
                        result.getInt(DETAIL_ID),
                        result.getInt(ORDER_ID_DETAIL),
                        plant,
                        result.getInt(QUANTITY)
                );

                insertOrder.setDate(1, Date.valueOf(order.getOrderDate()));
                insertOrder.setDate(2, Date.valueOf(order.getShipDate()));
                insertOrder.setInt(3, order.getStatus().getStatusCode());
                insertOrder.setInt(4, order.getAccountID());

                if (insertOrder.executeUpdate() != 1) {
                    throw new SQLException("Can't insert order");
                }

                latestOrder.setInt(1, 1);
                latestOrder.setInt(2, order.getAccountID());
                ResultSet tmp = latestOrder.executeQuery();

                final int currentOrderID;

                if (tmp.next()) {
                    currentOrderID = tmp.getInt(ORDER_ID_ORDER);
                } else {
                    throw new SQLException("Can't get the latest order ID");
                }

                insertDetail.setInt(1, currentOrderID);
                insertDetail.setInt(2, orderDetail.getPlant().getID());
                insertDetail.setInt(3, orderDetail.getQuantity());

                if (insertDetail.executeUpdate() != 1) {
                    throw new SQLException("Can't insert order detail");
                }

                connection.commit();
                return true;
            } catch (Exception exception) {
                connection.rollback();
                return false;
            }
        } catch (Exception exception) {
            return false;
        }
    }
}
