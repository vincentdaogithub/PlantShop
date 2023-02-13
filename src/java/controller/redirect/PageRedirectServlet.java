package controller.redirect;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PageRedirectServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String pageRequested = request.getParameter("page");

        if (pageRequested == null) {
            request.getSession().setAttribute("page", Pages.HOME.getPage());
            response.sendRedirect(Pages.HOME.getURL());
            return;
        }

        String url = "";

        for (Pages page : Pages.values()) {
            String currentPage = page.getPage();

            if (pageRequested.equals(currentPage)) {
                url = page.getURL();
                request.getSession().setAttribute("page", page.getPage());
                break;
            }
        }

        if (url.isEmpty()) {
            response.sendRedirect(Pages.ERROR.getURL());
            return;
        }

        response.sendRedirect(url);
    }
}
