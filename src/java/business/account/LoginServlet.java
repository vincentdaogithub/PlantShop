package business.account;

import controller.Servlets;
import controller.redirect.Pages;
import dao.account.AccountDAO;
import obj.account.Account;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        HttpSession session = request.getSession();

        if (email == null || password == null) {
            request.setAttribute("requestPage", Pages.ERROR);
            getServletContext().getRequestDispatcher(Servlets.PAGE_REDIRECT.getServlet()).forward(request, response);
            return;
        }

        Account account = AccountDAO.getAccount(email, password);

        if (account == null) {
            request.setAttribute("requestPage", Pages.ERROR);
            getServletContext().getRequestDispatcher(Servlets.PAGE_REDIRECT.getServlet()).forward(request, response);
        } else {
            session.setAttribute("account", account);
            request.setAttribute("requestPage", Pages.HOME);
            getServletContext().getRequestDispatcher(Servlets.PAGE_REDIRECT.getServlet()).forward(request, response);
        }
    }
}
