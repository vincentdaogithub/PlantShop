package business.account_business;

import controller.redirect.Pages;
import dao.account.AccountDAO;
import obj.account.Account;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (email == null || password == null) {
            response.sendRedirect(Pages.ERROR.getURL());
        }

        Account account = AccountDAO.getAccount(email, password);

        if (account == null) {
            response.sendRedirect(Pages.ERROR.getURL());
            return;
        }

        request.getSession().setAttribute("account", account);
        response.sendRedirect(Pages.HOME.getURL());
    }
}
