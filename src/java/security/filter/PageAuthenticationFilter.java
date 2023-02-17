package security.filter;

import controller.Servlets;
import controller.redirect.Pages;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import obj.account.Account;

public class PageAuthenticationFilter implements Filter {
    // private FilterConfig filterConfig = null;

    @Override
    public void init(FilterConfig filterConfig) {
        // this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        Accesses access = (Accesses) request.getAttribute("pageAuthentication");

        if (access == null) {
            throw new ServletException();
        } else if (access == Accesses.APPROVED || access == Accesses.DENIED) {
            return;
        }

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpSession session = httpServletRequest.getSession();
        Account account = (Account) session.getAttribute("account");
        Pages requestPage = (Pages) request.getAttribute("requestPage");

        if (account == null || requestPage == null || account.getAccountRole() != requestPage.getAuthentication().getCode()) {
            request.setAttribute("pageAuthentication", Accesses.DENIED);
        } else {
            request.setAttribute("pageAuthentication", Accesses.APPROVED);
        }

        request.getRequestDispatcher(Servlets.PAGE_REDIRECT.getServlet()).forward(request, response);
    }

    @Override
    public void destroy() {

    }
}
