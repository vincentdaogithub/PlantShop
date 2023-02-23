package security.filter.account;

import controller.Servlets;
import controller.redirect.Pages;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import security.error.Errors;
import util.UserInput;

public class RegisterFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String fullname = request.getParameter("fullname");
        String phone = request.getParameter("phone");

        if (UserInput.isEmpty(email)
                || UserInput.isEmpty(password)
                || UserInput.isEmpty(fullname)
                || UserInput.isEmpty(phone)) {
            request.setAttribute("requestPage", Pages.ERROR);
            request.setAttribute("error", Errors.BAD_REQUEST);
            request.getRequestDispatcher(Servlets.PAGE_REDIRECT.getServlet()).forward(request, response);
            return;
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }

    @Override
    public void init(FilterConfig filterConfig) {

    }
}
