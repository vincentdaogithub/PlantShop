package controller.redirect;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import controller.Servlets;
import security.error.Errors;

public class ErrorRedirect {
    public static final void redirect(Errors error, ServletRequest request, ServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("requestPage", Pages.ERROR);
        request.setAttribute("error", error);
        request.getRequestDispatcher(Servlets.PAGE_REDIRECT.getServletURL()).include(request, response);
    }
}