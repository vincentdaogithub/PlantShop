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
            getServletContext().getRequestDispatcher(Pages.ERROR.getURL()).forward(request, response);
        }

        Account account = AccountDAO.getAccount(email, password);

        if (account == null) {
            getServletContext().getRequestDispatcher(Pages.ERROR.getURL()).forward(request, response);
            return;
        }

        request.getSession().setAttribute("account", account);
        getServletContext().getRequestDispatcher(Pages.HOME.getURL()).forward(request, response);
    }
}
