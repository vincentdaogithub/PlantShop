package filter.order;

import business.order.OrderActions;
import controller.redirect.ErrorRedirect;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import security.error.Errors;

public class OrderUpdateFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        Object account = httpServletRequest.getSession().getAttribute("account");
        
        if (account == null) {
            ErrorRedirect.redirect(Errors.BAD_REQUEST, request, response);
            return;
        }
        
        OrderActions action = OrderActions.convertStringToAction(request.getParameter("update"));
        
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
