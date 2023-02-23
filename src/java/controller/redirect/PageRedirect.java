package controller.redirect;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import security.error.Errors;
import security.filter.Accesses;

public class PageRedirect extends HttpServlet {
    
    private static final long serialVersionUID = 1L;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        if (request.getAttribute("requestPage") == null) {
            Pages page = Pages.convertStringToPage(request.getParameter("page"));

            if (page == null) {
                ErrorRedirect.redirect(Errors.BAD_REQUEST, request, response);
                return;
            }

            request.setAttribute("requestPage", page);
        }

        Pages requestPage = (Pages) request.getAttribute("requestPage");
        int authenCode = requestPage.getAuthentication().getCode();

        if (authenCode >= 0) {
            request.setAttribute("pageAuthentication", Accesses.REQUESTING);
            getServletContext().getRequestDispatcher(requestPage.getURL()).include(request, response);

            Accesses pageAuthen = (Accesses) request.getAttribute("pageAuthentication");

            switch (pageAuthen) {
                case APPROVED:
                    break;

                case DENIED:
                    requestPage = Pages.ERROR;
                    request.setAttribute("error", Errors.UNAUTHORIZED);
                    break;

                case REQUESTING:
                    requestPage = Pages.ERROR;
                    request.setAttribute("error", Errors.SERVER_ERROR);
            }
        }

        session.setAttribute("currentPage", requestPage.getPage());
        getServletContext().getRequestDispatcher(requestPage.getURL()).forward(request, response);
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
