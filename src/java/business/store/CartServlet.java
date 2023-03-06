package business.store;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import obj.plant.Plant;

public class CartServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        @SuppressWarnings("unchecked")
        Map<Plant, Integer> cartMap = (Map<Plant, Integer>) request.getSession().getAttribute("cart");

        if (cartMap == null || cartMap.isEmpty()) {
            return;
        }

        request.setAttribute("cart", cartMap.entrySet().toArray());
    }
}
