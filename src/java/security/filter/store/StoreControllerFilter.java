package security.filter.store;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import business.store.StoreActions;
import controller.redirect.ErrorRedirect;
import security.error.Errors;
import util.UserInput;

public class StoreControllerFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        String action = request.getParameter("store");

        if (UserInput.isEmpty(action)) {
            request.setAttribute("storeAction", StoreActions.STORE);
        } else {
            StoreActions storeAction = StoreActions.convertStringToAction(action);

            if (storeAction == null) {
                ErrorRedirect.redirect(Errors.BAD_REQUEST, request, response);
                return;
            }

            request.setAttribute("storeAction", storeAction);
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }
}
