package security.filter;

import controller.Servlets;
import controller.redirect.Pages;
import security.error.Errors;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class PageRedirectFilter implements Filter {
    // private FilterConfig filterConfig = null;

    @Override
    public void init(FilterConfig filterConfig) {
        // this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        request.setAttribute("requestPage", Pages.ERROR);
        request.setAttribute("error", Errors.ACCESS_DENIED);
        request.getRequestDispatcher(Servlets.PAGE_REDIRECT.getServlet()).forward(request, response);

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
