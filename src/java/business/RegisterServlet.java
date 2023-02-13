package business;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.redirect.Pages;
import dao.AccountDAO;
import obj.Account;

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

        Account account = new Account(email, password, fullname, phone);

        if (!AccountDAO.addAccount(account)) {
            response.sendRedirect(Pages.ERROR.getURL());
            return;
        }

        request.getSession().setAttribute("account", AccountDAO.getAccount(email, password));
        response.sendRedirect(Pages.HOME.getURL());
    }
}
