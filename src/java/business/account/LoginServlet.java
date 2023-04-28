package business.account;

import controller.redirect.Pages;
import dao.account.AccountDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import obj.account.Account;
import security.filter.Authentications;

public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        Account account = AccountDAO.getAccount(email, password);

        if (account == null) {
            request.setAttribute("email", email);
            request.setAttribute("password", password);
            request.setAttribute("requestPage", Pages.LOGIN);
            request.setAttribute("loginFail", true);
        } else {
            if (account.getRole() == Authentications.ADMIN.getCode()) {
                request.setAttribute("requestPage", Pages.MANAGE);
            }

            request.getSession().setAttribute("account", account);
        }
    }
}
