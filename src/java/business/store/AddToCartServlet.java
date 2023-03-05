package business.store;

import java.io.IOException;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import obj.plant.Plant;

public class AddToCartServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        @SuppressWarnings("unchecked")
        Map<Plant, Integer> cart = (Map<Plant, Integer>) session.getAttribute("cart");

        if (cart == null) {
            cart = Collections.synchronizedMap(new LinkedHashMap<>());
        }

        synchronized(cart) {
            cart.put((Plant) request.getAttribute("plant"), (Integer) request.getAttribute("quantity"));
        }

        synchronized(session.getId().intern()) {
            session.setAttribute("cart", cart);
        }
    }
}
