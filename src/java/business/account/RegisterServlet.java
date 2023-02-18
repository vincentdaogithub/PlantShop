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
        HttpSession session = request.getSession();

        if (email == null || password == null || fullname == null || phone == null) {
            request.setAttribute("requestPage", Pages.ERROR);
            getServletContext().getRequestDispatcher(Servlets.PAGE_REDIRECT.getServlet()).forward(request, response);
            return;
        }

        Account account = AccountFactory.build(email, password, fullname, phone, Accounts.USER);

        if (!AccountDAO.addAccount(account)) {
            request.setAttribute("requestPage", Pages.ERROR);
            getServletContext().getRequestDispatcher(Servlets.PAGE_REDIRECT.getServlet()).forward(request, response);
            return;
        }

        session.setAttribute("account", AccountDAO.getAccount(email, password));
        request.setAttribute("requestPage", Pages.HOME);
        getServletContext().getRequestDispatcher(Servlets.PAGE_REDIRECT.getServlet()).forward(request, response);
    }
}
