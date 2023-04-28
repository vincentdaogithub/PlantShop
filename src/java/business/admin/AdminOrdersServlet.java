package business.admin;

import dao.order.OrderDAO;
import java.io.IOException;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import obj.order.Order;
import obj.order.OrderDetail;
import util.UserInput;

public class AdminOrdersServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Integer index = UserInput.toInt(request.getParameter("index"));

        if (index == null || index < 0) {
            request.setAttribute("index", 0);
        } else {
            request.setAttribute("index", index);
        }

        Map<Order, OrderDetail> orders = OrderDAO.getOrders();

        request.setAttribute("size", orders.size());
        request.setAttribute("orders", orders);
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
