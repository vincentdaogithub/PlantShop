package controller.redirect;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class PageRedirect extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String requestPage = request.getParameter("page");
        HttpSession session = request.getSession();
        String sessionPage = (String) session.getAttribute("page");

        if (requestPage != null) {
            Pages page = getPage(requestPage);

            if (page == null) {
                getServletContext().getRequestDispatcher(Pages.ERROR.getURL()).forward(request, response);
            } else {
                session.setAttribute("page", page.getPage());
                getServletContext().getRequestDispatcher(page.getURL()).forward(request, response);
            }

            return;
        }

        if (sessionPage != null) {
            Pages page = getPage(sessionPage);

            if (page == null) {
                getServletContext().getRequestDispatcher(Pages.ERROR.getURL()).forward(request, response);
            } else {
                session.setAttribute("page", page.getPage());
                getServletContext().getRequestDispatcher(page.getURL()).forward(request, response);
            }

            return;
        }

        session.setAttribute("page", Pages.HOME.getPage());
        getServletContext().getRequestDispatcher(Pages.HOME.getURL()).forward(request, response);
    }

    private Pages getPage(String pageString) {
        for (Pages page : Pages.values()) {
            if (pageString.equals(page.getPage())) {
                return page;
            }
        }

        return null;
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
