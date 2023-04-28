package dao.plant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import obj.plant.Plant;
import util.DBUtils;

public class PlantDAO {

    private static final String PLANTS_TABLE = "Plants";
    private static final String PLANT_ID = "PID";
    private static final String PLANT_NAME = "PName";
    private static final String PLANT_PRICE = "price";
    private static final String PLANT_DESCRIPTION = "description";
    private static final String PLANT_STATUS = "status";
    private static final String PLANT_CATEGORY_ID = "CateID";

    private static final String CATEGORIES_TABLE = "Categories";
    private static final String CATEGORY_ID = "CateID";
    private static final String CATEGORY_NAME = "CateName";

    private static final String GET_PLANT_ID_QUERY = String.format(
            "SELECT * FROM %s AS a JOIN %s AS b ON a.%s = b.%s AND %s = ?",
            PLANTS_TABLE, CATEGORIES_TABLE, PLANT_CATEGORY_ID, CATEGORY_ID, PLANT_ID);

    private static final String GET_PLANTS_QUERY = String.format(
            "SELECT * FROM %s AS a JOIN %s AS b ON a.%s = b.%s",
            PLANTS_TABLE, CATEGORIES_TABLE, PLANT_CATEGORY_ID, CATEGORY_ID);

    private static final String ADD_PLANT_QUERY = String.format(
            "INSERT INTO %s(%s, %s, %s, %s, %s) VALUES(?, ?, ?, ?, ?)",
            PLANTS_TABLE, PLANT_NAME, PLANT_PRICE, PLANT_DESCRIPTION, PLANT_STATUS, PLANT_CATEGORY_ID);

    public static final Plant getPlant(int plantID) {

        try (
                Connection connection = DBUtils.makeConnection();) {

            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

            try (
                    PreparedStatement statement = connection.prepareStatement(
                            GET_PLANT_ID_QUERY,
                            ResultSet.TYPE_SCROLL_SENSITIVE,
                            ResultSet.CONCUR_READ_ONLY);) {

                statement.setInt(1, plantID);
                ResultSet results = statement.executeQuery();

                connection.commit();

                if (results.next() && results.isLast()) {
                    return new Plant(
                            results.getInt(PLANT_ID),
                            results.getString(PLANT_NAME),
                            results.getInt(PLANT_PRICE),
                            results.getString(PLANT_DESCRIPTION),
                            results.getInt(PLANT_STATUS),
                            results.getInt(PLANT_CATEGORY_ID),
                            results.getString(CATEGORY_NAME));
                }

                return null;
            } catch (Exception e) {
                connection.rollback();
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    public static final Set<Plant> getPlants() {

        try (
                Connection connection = DBUtils.makeConnection();) {

            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

            try (
                    PreparedStatement statement = connection.prepareStatement(
                            GET_PLANTS_QUERY,
                            ResultSet.TYPE_SCROLL_SENSITIVE,
                            ResultSet.CONCUR_READ_ONLY);) {

                ResultSet results = statement.executeQuery();
                Set<Plant> plants = Collections.synchronizedSet(new LinkedHashSet<>());

                connection.commit();

                synchronized (plants) {
                    while (results.next()) {
                        Plant plant = new Plant(
                                results.getInt(PLANT_ID),
                                results.getString(PLANT_NAME),
                                results.getInt(PLANT_PRICE),
                                results.getString(PLANT_DESCRIPTION),
                                results.getInt(PLANT_STATUS),
                                results.getInt(PLANT_CATEGORY_ID),
                                results.getString(CATEGORY_NAME)
                        );

                        plants.add(plant);
                    }
                }

                return plants;
            } catch (Exception e) {
                connection.rollback();
                return new LinkedHashSet<>();
            }
        } catch (Exception e) {
            return new LinkedHashSet<>();
        }
    }

    public static final boolean addPlant(Plant plant) {

        if (plant == null) {
            throw new NullPointerException();
        }

        try (
                Connection connection = DBUtils.makeConnection();) {

            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);

            try (
                    PreparedStatement addPlant = connection.prepareStatement(ADD_PLANT_QUERY);) {

                addPlant.setString(1, plant.getName());
                addPlant.setInt(2, plant.getPrice());
                addPlant.setString(3, plant.getDescription());
                addPlant.setInt(4, plant.getStatus());
                addPlant.setInt(5, plant.getCategoryID());

                if (addPlant.executeUpdate() != 1) {
                    throw new SQLException("Can't add plant");
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
}
