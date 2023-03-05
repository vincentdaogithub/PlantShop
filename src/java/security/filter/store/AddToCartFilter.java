package security.filter.store;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import controller.redirect.ErrorRedirect;
import dao.plant.PlantDAO;
import obj.plant.Plant;
import security.error.Errors;
import util.UserInput;

public class AddToCartFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        Integer pid = UserInput.toInt(request.getParameter("pid"));
        Integer quantity = UserInput.toInt(request.getParameter("quantity"));

        if (pid == null || quantity == null) {
            ErrorRedirect.redirect(Errors.BAD_REQUEST, request, response);
            return;
        }

        Plant plant = PlantDAO.getPlant(pid);

        if (plant == null) {
            ErrorRedirect.redirect(Errors.FILE_NOT_FOUND, request, response);
            return;
        }

        request.setAttribute("plant", plant);
        request.setAttribute("quantity", quantity);
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }
}
