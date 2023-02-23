package business.account;

import controller.Servlets;
import controller.redirect.Pages;
import dao.account.AccountDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import obj.account.Account;
import security.error.Errors;

public class UpdateAccountServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Updates update = Updates.convertStringToUpdate(request.getParameter("update"));

        if (update == null) {
            request.setAttribute("requestPage", Pages.ERROR);
            request.setAttribute("error", Errors.BAD_REQUEST);
            request.getRequestDispatcher(Servlets.PAGE_REDIRECT.getServlet()).forward(request, response);
            return;
        }

        boolean updateStatus = false;

        if (update == Updates.PASSWORD) {
            updateStatus = updatePassword(update, request);
        } else {
            updateStatus = updateInformation(update, request);
        }

        if (updateStatus) {
            request.setAttribute("requestPage", Pages.PROFILE);
            HttpSession session = request.getSession();

            Account account = (Account) session.getAttribute("account");
            String email = update == Updates.EMAIL
                    ? (String) request.getParameter("new-email")
                    : account.getEmail();

            String password = update == Updates.PASSWORD
                    ? (String) request.getParameter("new-password")
                    : (String) request.getParameter("password");

            session.setAttribute("account", AccountDAO.getAccount(email, password));
        } else {
            request.setAttribute("requestPage", Pages.ERROR);
            request.setAttribute("error", Errors.UNAUTHORIZED);
        }

        request.getRequestDispatcher(Servlets.PAGE_REDIRECT.getServlet()).forward(request, response);
    }

    private final boolean updateInformation(Updates update, HttpServletRequest request) throws ServletException {
        String valueToChange = null;

        switch (update) {
            case EMAIL:
                valueToChange = request.getParameter("new-email");
                break;

            case FULLNAME:
                valueToChange = request.getParameter("new-fullname");
                break;

            case PHONE:
                valueToChange = request.getParameter("new-phone");
                break;

            default:
                throw new ServletException();
        }

        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");

        return AccountDAO.updateAccount(update, account, valueToChange);
    }

    private final boolean updatePassword(Updates update, HttpServletRequest request) {
        String valueToChange = request.getParameter("new-password");
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");

        return AccountDAO.updateAccount(update, account, valueToChange);
    }
}
