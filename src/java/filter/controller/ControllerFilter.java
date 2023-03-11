package filter.controller;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import controller.Actions;
import controller.redirect.ErrorRedirect;
import security.error.Errors;
import util.UserInput;

public class ControllerFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        String actionString = request.getParameter("action");

        if (UserInput.isEmpty(actionString)) {
            return;
        }

        Actions action = Actions.convertStringToAction(actionString);

        if (action == null) {
            ErrorRedirect.redirect(Errors.BAD_REQUEST, request, response);
            return;
        }

        request.setAttribute("action", action);
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }
}
