package business.account;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Servlets;
import controller.redirect.Pages;

public class LogOutServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getSession().invalidate();
        request.setAttribute("requestPage", Pages.HOME);
        request.getRequestDispatcher(Servlets.PAGE_REDIRECT.getServletURL()).forward(request, response);
    }
}
