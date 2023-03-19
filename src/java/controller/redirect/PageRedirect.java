package controller.redirect;

import controller.DisplayServlets;
import controller.Servlets;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import security.error.Errors;
import security.filter.Accesses;

@MultipartConfig
public class PageRedirect extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Accesses accessStatus = (Accesses) request.getAttribute("authenticationStatus");

        if (accessStatus == Accesses.DENIED) {
            if (session.getAttribute("account") == null) {
                session.setAttribute("lastPage", session.getAttribute("lastPage"));
                request.setAttribute("page", Pages.LOGIN);
                request.getRequestDispatcher(Pages.LOGIN.getURL()).forward(request, response);
                return;
            } else {
                ErrorRedirect.redirect(Errors.ACCESS_DENIED, request, response);
                return;
            }
        }

        request.getRequestDispatcher(Servlets.CONTROLLER.getServletURL()).include(request, response);

        Pages requestPage = (Pages) request.getAttribute("requestPage");

        for (DisplayServlets servletMapping : DisplayServlets.values()) {
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
