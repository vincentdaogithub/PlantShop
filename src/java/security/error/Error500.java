package security.error;

import controller.redirect.Pages;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class Error500 implements Filter {
    // private FilterConfig filterConfig = null;

    @Override
    public void init(FilterConfig filterConfig) {
        // this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        httpServletRequest.getSession().setAttribute("currentPage", Pages.ERROR.getPage());
        request.setAttribute("error", Errors.SERVER_ERROR);
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
