package controller.redirect;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PageRedirect extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String pageRequested = request.getParameter("page");

        if (pageRequested == null) {
            request.getSession().setAttribute("page", Pages.HOME.getPage());
            getServletContext().getRequestDispatcher(Pages.HOME.getURL()).forward(request, response);
            return;
        }

        for (Pages page : Pages.values()) {
            if (pageRequested.equals(page.getPage())) {
                request.getSession().setAttribute("page", page.getPage());
                getServletContext().getRequestDispatcher(page.getURL()).forward(request, response);
                return;
            }
        }

        getServletContext().getRequestDispatcher(Pages.ERROR.getURL()).forward(request, response);
    }
}
