package security.filter.controller;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import controller.redirect.ErrorRedirect;
import controller.redirect.Pages;
import security.error.Errors;

public class PageRedirectFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        if (request.getAttribute("requestPage") == null) {
            Pages page = Pages.convertStringToPage(request.getParameter("page"));

            if (page == null) {
                ErrorRedirect.redirect(Errors.BAD_REQUEST, request, response);
                return;
            }

            request.setAttribute("requestPage", page);
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
