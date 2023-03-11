package business.cart;

import java.io.IOException;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.redirect.ErrorRedirect;
import obj.plant.Plant;
import security.error.Errors;
import util.UserInput;

public class CartUpdateServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        CartActions action = (CartActions) request.getAttribute("action");
        Plant plant = (Plant) request.getAttribute("plant");
        Integer quantity = UserInput.toInt(request.getParameter("quantity"));

        HttpSession session = request.getSession();

        Object cartObject = session.getAttribute("cart");
        Map<Plant, Integer> cart;

        if (cartObject == null) {
            cart = Collections.synchronizedMap(new LinkedHashMap<>());
        } else {
            @SuppressWarnings("unchecked")
            Map<Plant, Integer> tmp = (Map<Plant, Integer>) cartObject;
            cart = Collections.synchronizedMap(tmp);
        }

        synchronized(cart) {
            switch (action) {
                case ADD:
                    if (quantity == null) {
                        ErrorRedirect.redirect(Errors.BAD_REQUEST, request, response);
                        return;
                    }

                    cart.merge(plant, quantity, Integer::sum);
                    break;
    
                case UPDATE:
                    if (quantity == null) {
                        ErrorRedirect.redirect(Errors.BAD_REQUEST, request, response);
                        return;
                    }

                    if (!cart.containsKey(plant)) {
                        cart.put(plant, quantity);
                    } else {
                        int prevQuantity = cart.get(plant);

                        if (prevQuantity < quantity) {
                            cart.merge(plant, quantity, Integer::max);
                        } else {
                            cart.merge(plant, quantity, Integer::min);
                        }
                    }

                    break;
    
                case REMOVE:
                    cart.remove(plant);
                    break;
            
                default:
                    ErrorRedirect.redirect(Errors.BAD_REQUEST, request, response);
                    break;
            }
        }

        session.setAttribute("cart", cart);
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
