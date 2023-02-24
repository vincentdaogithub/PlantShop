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
import obj.account.Account;

public class AuthenticationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        Pages requestPage = (Pages) request.getAttribute("requestPage");
        int authentication = requestPage.getAuthentication().getCode();

        request.setAttribute("authenticationStatus", Accesses.APPROVED);

        if (authentication >= 0) {
            HttpServletRequest httpServletRequest = (HttpServletRequest) request;
            Account account = (Account) httpServletRequest.getSession().getAttribute("account");

            if (account == null || account.getRole() != authentication) {
                request.setAttribute("authenticationStatus", Accesses.DENIED);
            }
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
