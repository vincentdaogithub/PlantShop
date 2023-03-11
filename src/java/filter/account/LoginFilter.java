package filter.account;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import controller.redirect.ErrorRedirect;
import security.error.Errors;
import util.UserInput;

public class LoginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (UserInput.isEmpty(email) || UserInput.isEmpty(password)) {
            ErrorRedirect.redirect(Errors.BAD_REQUEST, request, response);
            return;
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
