package business.order;

import controller.redirect.ErrorRedirect;
import dao.order.OrderDAO;
import dao.order.OrderFactory;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import obj.account.Account;
import obj.order.Order;
import obj.order.OrderDetail;
import obj.order.OrderStatuses;
import security.error.Errors;
import util.UserInput;

public class OrderUpdateServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private final String ORDER_ID = "order-id";
    private final String PLANT_ID = "pid";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        OrderActions action = (OrderActions) request.getAttribute("action");

        Map<Integer, Integer> ordersAsID;
        Object ordersOnSession = request.getSession().getAttribute("orders");

        if (ordersOnSession == null) {
            ordersAsID = Collections.synchronizedMap(new LinkedHashMap<>());
        } else {
            @SuppressWarnings("unchecked")
            Map<Integer, Integer> tmp = Collections.synchronizedMap((Map<Integer, Integer>) ordersOnSession);
            ordersAsID = tmp;
        }

        boolean status;

        synchronized (ordersAsID) {
            switch (action) {
                case ADD:
                    status = addOrder(request);
                    break;

                case ADD_ALL:
                    status = addOrders(request);
                    break;

                case CANCEL:
                    status = cancelOrder(request);
                    break;

                case ORDER_AGAIN:
                    status = orderAgain(request);
                    break;

                default:
                    status = false;
            }
        }

        if (!status) {
            ErrorRedirect.redirect(Errors.BAD_REQUEST, request, response);
        }
    }

    private boolean addOrder(HttpServletRequest request) {

        String plantIDString = request.getParameter(PLANT_ID);
        Integer plantID = UserInput.toInt(plantIDString);

        if (plantID == null) {
            return false;
        }

        @SuppressWarnings("unchecked")
        Map<Integer, Integer> cart = (Map<Integer, Integer>) request.getSession().getAttribute("cart");

        if (cart == null || !cart.containsKey(plantID)) {
            return false;
        }

        Integer quantity = cart.get(plantID);
        Account account = (Account) request.getSession().getAttribute("account");

        Order order = OrderFactory.createOrder(account.getID(), OrderStatuses.PROCESSING);
        OrderDetail orderDetail = OrderFactory.createOrderDetail(plantID, quantity);

        if (!OrderDAO.addOrder(order, orderDetail)) {
            return false;
        }

        cart.remove(plantID);
        request.getSession().setAttribute("cart", cart);
        return true;
    }

    private boolean addOrders(HttpServletRequest request) {

        @SuppressWarnings("unchecked")
        Map<Integer, Integer> cart = (Map<Integer, Integer>) request.getSession().getAttribute("cart");

        if (cart == null || cart.isEmpty()) {
            return false;
        }

        Account account = (Account) request.getSession().getAttribute("account");
        Map<Order, OrderDetail> orders = Collections.synchronizedMap(new LinkedHashMap<>());

        cart.forEach((plantID, quantity) -> {
            Order order = OrderFactory.createOrder(account.getID(), OrderStatuses.PROCESSING);
            OrderDetail orderDetail = OrderFactory.createOrderDetail(plantID, quantity);
            orders.put(order, orderDetail);
        });

        if (!OrderDAO.addOrders(orders)) {
            return false;
        }

        Map<Integer, Integer> tmp = new LinkedHashMap<>();
        request.getSession().setAttribute("cart", tmp);
        return true;
    }

    private boolean cancelOrder(HttpServletRequest request) {

        Integer orderID = UserInput.toInt(request.getParameter(ORDER_ID));

        if (orderID == null) {
            return false;
        }

        return OrderDAO.cancelOrder(orderID);
    }

    private boolean orderAgain(HttpServletRequest request) {

        Account account = (Account) request.getSession().getAttribute("account");
        Integer orderID = UserInput.toInt(request.getParameter(ORDER_ID));

        if (orderID == null || account == null) {
            return false;
        }

        return OrderDAO.orderAgain(orderID, account.getID());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        processRequest(request, response);
    }
}
