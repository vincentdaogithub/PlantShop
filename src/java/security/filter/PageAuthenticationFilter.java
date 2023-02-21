package security.filter;

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

        if (access == null || access != Accesses.REQUESTING) {
            throw new ServletException();
        }

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpSession session = httpServletRequest.getSession();
        Account account = (Account) session.getAttribute("account");
        Pages requestPage = (Pages) request.getAttribute("requestPage");

        if (account == null || requestPage == null || account.getRole() != requestPage.getAuthentication().getCode()) {
            request.setAttribute("pageAuthentication", Accesses.DENIED);
        } else {
            request.setAttribute("pageAuthentication", Accesses.APPROVED);
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
