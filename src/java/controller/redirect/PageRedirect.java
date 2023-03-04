package controller.redirect;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
