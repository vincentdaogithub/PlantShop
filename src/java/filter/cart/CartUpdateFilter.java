package filter.cart;

import business.cart.CartActions;
import controller.redirect.ErrorRedirect;
import dao.plant.PlantDAO;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import obj.plant.Plant;
import security.error.Errors;
import util.UserInput;

public class CartUpdateFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        CartActions action = CartActions.convertStringToAction(request.getParameter("update"));
        Integer pid = UserInput.toInt(request.getParameter("pid"));
        Plant plant = PlantDAO.getPlant(pid);

        if (action == null || plant == null) {
            ErrorRedirect.redirect(Errors.BAD_REQUEST, request, response);
            return;
        }

        request.setAttribute("action", action);
        request.setAttribute("plant", plant);
        
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }
}
