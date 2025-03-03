package filter.account;

import business.account.Updates;
import controller.Servlets;
import controller.redirect.ErrorRedirect;
import dao.account.AccountDAO;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import obj.account.Account;
import security.error.Errors;
import util.UserInput;

@MultipartConfig
public class UpdateFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        Updates update = Updates.convertStringToUpdate(request.getParameter("update"));

        if (update == null) {
            ErrorRedirect.redirect(Errors.BAD_REQUEST, request, response);
            return;
        }

        String password = request.getParameter("password");
        String updateValue = null;

        switch (update) {
            case EMAIL:
                updateValue = request.getParameter("new-email");
                request.setAttribute("email", updateValue);
                break;

            case PASSWORD:
                password = request.getParameter("old-password");
                updateValue = request.getParameter("new-password");
                request.setAttribute("password", updateValue);
                break;

            case FULLNAME:
                updateValue = request.getParameter("new-fullname");
                break;

            case PHONE:
                updateValue = request.getParameter("new-phone");
                break;

            case AVATAR:
                request.getRequestDispatcher(Servlets.IMAGE_UPLOAD.getServletURL()).include(request, response);
                chain.doFilter(request, response);
                return;

            default:
                throw new ServletException();
        }

        if (UserInput.isEmpty(password) || UserInput.isEmpty(updateValue)) {
            ErrorRedirect.redirect(Errors.BAD_REQUEST, request, response);
            return;
        }

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpSession session = httpServletRequest.getSession();
        Account account = (Account) session.getAttribute("account");

        if (account == null || AccountDAO.getAccount(account.getEmail(), password) == null) {
            ErrorRedirect.redirect(Errors.UNAUTHORIZED, request, response);
            return;
        }

        request.setAttribute("update", update);
        request.setAttribute("updateValue", updateValue);

        if (update == Updates.EMAIL) {
            request.setAttribute("email", updateValue);
        } else {
            request.setAttribute("email", account.getEmail());
        }

        if (update == Updates.PASSWORD) {
            request.setAttribute("password", updateValue);
        } else {
            request.setAttribute("password", password);
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
