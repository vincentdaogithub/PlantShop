package business.admin;

import controller.redirect.ErrorRedirect;
import dao.account.AccountDAO;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import obj.account.Account;
import security.error.Errors;
import util.UserInput;

public class AdminAccountsServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Integer index = UserInput.toInt(request.getParameter("index"));

        if (index == null || index < 0) {
            request.setAttribute("index", 0);
        } else {
            request.setAttribute("index", index);
        }

        List<Account> accounts = AccountDAO.getAccounts();

        if (accounts.isEmpty()) {
            ErrorRedirect.redirect(Errors.SERVER_ERROR, request, response);
            return;
        }

        request.setAttribute("size", accounts.size());
        request.setAttribute("accounts", accounts);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
