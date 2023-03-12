package controller.redirect;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import security.error.Errors;

public class ErrorRedirect {
    public static final void redirect(Errors error, ServletRequest request, ServletResponse response)
            throws ServletException, IOException {
        
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpSession session = httpServletRequest.getSession();

        session.setAttribute("lastPage", session.getAttribute("lastPage"));
        request.setAttribute("error", error);
        request.getRequestDispatcher(Pages.ERROR.getURL()).forward(request, response);
    }
}