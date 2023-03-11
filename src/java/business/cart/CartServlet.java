package business.cart;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import obj.plant.Plant;

public class CartServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Object cartObject = request.getSession().getAttribute("cart");
        Map<Plant, Integer> cart;

        if (cartObject == null) {
            cart = Collections.synchronizedMap(new LinkedHashMap<>());
        } else {
            @SuppressWarnings("unchecked")
            Map<Plant, Integer> tmp = (Map<Plant, Integer>) cartObject;
            cart = Collections.synchronizedMap(tmp);
        }

        request.setAttribute("cart", new ArrayList<>(cart.entrySet()));
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
