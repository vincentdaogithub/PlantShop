package business.account;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Servlets;
import controller.redirect.Pages;
import dao.account.AccountDAO;
import obj.account.Account;
import security.error.Errors;

public class UpdateAccountServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String update = request.getParameter("update");

        if (update == null) {
            request.setAttribute("requestPage", Pages.ERROR);
            request.setAttribute("error", Errors.BAD_REQUEST);
            request.getRequestDispatcher(Servlets.PAGE_REDIRECT.getServlet()).forward(request, response);
            return;
        }

        Updates updateType = Updates.convertStringToUpdateType(update);
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        String valueToChange;

        switch (updateType) {
            case EMAIL:
                valueToChange = request.getParameter("new-email");
                break;

            case PASSWORD:
                String oldPassword = request.getParameter("old-password");

                if (AccountDAO.getAccount(account.getEmail(), oldPassword) == null) {
                    request.setAttribute("requestPage", Pages.ERROR);
                    request.setAttribute("error", Errors.FILE_NOT_FOUND);
                    request.getRequestDispatcher(Servlets.PAGE_REDIRECT.getServlet()).forward(request, response);
                    return;
                }

                valueToChange = request.getParameter("new-password");
                break;

            case FULLNAME:
                valueToChange = request.getParameter("new-fullname");
                break;

            case PHONE:
                valueToChange = request.getParameter("new-phone");
                break;

            default:
                request.setAttribute("requestPage", Pages.ERROR);
                request.setAttribute("error", Errors.BAD_REQUEST);
                request.getRequestDispatcher(Servlets.PAGE_REDIRECT.getServlet()).forward(request, response);
                return;
        }

        if (!AccountDAO.updateAccount(updateType, account, valueToChange)) {
            request.setAttribute("requestPage", Pages.ERROR);
            request.setAttribute("error", Errors.SERVER_ERROR);
        } else {
            request.setAttribute("requestPage", Pages.PROFILE);
        }

        request.getRequestDispatcher(Servlets.PAGE_REDIRECT.getServlet()).forward(request, response);
    }
}
