package business.account_business;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.redirect.Pages;
import dao.account.AccountDAO;
import dao.account.AccountFactory;
import obj.account.Account;
import obj.account.Accounts;

public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String fullname = request.getParameter("fullname");
        String phone = request.getParameter("phone");

        if (email == null || password == null || fullname == null) {
            response.sendRedirect(Pages.ERROR.getURL());
            return;
        }

        Account account = AccountFactory.build(email, password, fullname, phone, Accounts.USER);

        if (!AccountDAO.addAccount(account)) {
            response.sendRedirect(Pages.ERROR.getURL());
            return;
        }

        request.getSession().setAttribute("account", AccountDAO.getAccount(email, password));
        response.sendRedirect(Pages.HOME.getURL());
    }
}
