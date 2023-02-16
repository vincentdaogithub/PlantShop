package controller.redirect;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import obj.account.Account;

public class PageRedirect extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String requestParam = request.getParameter("page");
        HttpSession session = request.getSession();
        String sessionPage = (String) session.getAttribute("page");
        Account account = (Account) session.getAttribute("account");

        if (requestParam != null) {
            Pages page = Pages.convertStringToPage(requestParam);

            if (page == null) {
                getServletContext().getRequestDispatcher(Pages.ERROR.getURL()).forward(request, response);
                return;
            }

            int authenCode = page.getAuthentication().getCode();

            if (authenCode >= 0 && (account == null || account.getAccountRole() != authenCode)) {
                session.setAttribute("page", Pages.ERROR.getPage());
                getServletContext().getRequestDispatcher(Pages.ERROR.getURL()).forward(request, response);
                return;
            }

            session.setAttribute("page", page.getPage());
            getServletContext().getRequestDispatcher(page.getURL()).forward(request, response);
            return;
        }

        if (sessionPage != null) {
            Pages page = Pages.convertStringToPage(sessionPage);

            if (page == null) {
                getServletContext().getRequestDispatcher(Pages.ERROR.getURL()).forward(request, response);
                return;
            }

            int authenCode = page.getAuthentication().getCode();

            if (authenCode >= 0 && (account == null || account.getAccountRole() != authenCode)) {
                session.setAttribute("page", Pages.ERROR.getPage());
                getServletContext().getRequestDispatcher(Pages.ERROR.getURL()).forward(request, response);
                return;
            }

            session.setAttribute("page", page.getPage());
            getServletContext().getRequestDispatcher(page.getURL()).forward(request, response);
            return;
        }

        session.setAttribute("page", Pages.HOME.getPage());
        getServletContext().getRequestDispatcher(Pages.HOME.getURL()).forward(request, response);
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
