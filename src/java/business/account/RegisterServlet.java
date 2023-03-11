package business.account;

import controller.redirect.ErrorRedirect;
import dao.account.AccountDAO;
import dao.account.AccountFactory;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
            ErrorRedirect.redirect(Errors.USER_EXISTED, request, response);
        } else {
            request.getSession().setAttribute("account", AccountDAO.getAccount(email, password));
        }
    }
}
