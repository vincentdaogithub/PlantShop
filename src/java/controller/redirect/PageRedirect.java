package controller.redirect;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ServletMappings;
import security.error.Errors;
import security.filter.Accesses;

public class PageRedirect extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Pages requestPage = (Pages) request.getAttribute("requestPage");
        Accesses accessStatus = (Accesses) request.getAttribute("authenticationStatus");

        if (accessStatus == Accesses.DENIED) {
            requestPage = Pages.ERROR;
            request.setAttribute("error", Errors.UNAUTHORIZED);
        }

        for (ServletMappings servletMapping : ServletMappings.values()) {
            if (servletMapping.getPage() == requestPage) {
                request.getRequestDispatcher(servletMapping.getServlet().getServletURL()).include(request, response);
                break;
            }
        }

        request.getSession().setAttribute("lastPage", requestPage);
        request.setAttribute("page", requestPage);
        request.getRequestDispatcher(requestPage.getURL()).forward(request, response);
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
