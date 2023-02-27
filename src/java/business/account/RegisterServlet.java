package business.account;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Servlets;
import controller.redirect.Pages;
import dao.account.AccountDAO;
import dao.account.AccountFactory;
import obj.account.Account;
import obj.account.Accounts;
import security.error.Errors;

public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String fullname = request.getParameter("fullname");
        String phone = request.getParameter("phone");

        Account account = AccountFactory.buildForDB(email, password, fullname, phone, Accounts.USER);

        if (!AccountDAO.addAccount(account)) {
            request.setAttribute("requestPage", Pages.ERROR);
            request.setAttribute("error", Errors.USER_EXISTED);
        } else {
            request.setAttribute("requestPage", Pages.HOME);
            request.getSession().setAttribute("account", AccountDAO.getAccount(email, password));
        }

        request.getRequestDispatcher(Servlets.PAGE_REDIRECT.getServlet()).forward(request, response);
    }
}
