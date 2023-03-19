package business.cart;

import controller.redirect.ErrorRedirect;
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
import security.error.Errors;
import util.UserInput;

public class CartUpdateServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        CartActions action = (CartActions) request.getAttribute("action");
        Plant plant = (Plant) request.getAttribute("plant");
        Integer plantID = plant.getID();
        Integer quantity = UserInput.toInt(request.getParameter("quantity"));

        HttpSession session = request.getSession();

        Object cartObject = session.getAttribute("cart");
        Map<Integer, Integer> cart;

        if (cartObject == null) {
            cart = Collections.synchronizedMap(new LinkedHashMap<>());
        } else {
            @SuppressWarnings("unchecked")
            Map<Integer, Integer> tmp = (Map<Integer, Integer>) cartObject;
            cart = Collections.synchronizedMap(tmp);
        }

        synchronized(cart) {
            switch (action) {
                case ADD:
                    if (quantity == null) {
                        ErrorRedirect.redirect(Errors.BAD_REQUEST, request, response);
                        return;
                    }

                    cart.merge(plantID, quantity, Integer::sum);
                    break;
    
                case UPDATE:
                    if (quantity == null) {
                        ErrorRedirect.redirect(Errors.BAD_REQUEST, request, response);
                        return;
                    }

                    if (!cart.containsKey(plantID)) {
                        cart.put(plantID, quantity);
                    } else {
                        int prevQuantity = cart.get(plantID);

                        if (prevQuantity < quantity) {
                            cart.merge(plantID, quantity, Integer::max);
                        } else {
                            cart.merge(plantID, quantity, Integer::min);
                        }
                    }

                    break;
    
                case REMOVE:
                    cart.remove(plantID);
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
