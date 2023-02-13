package controller;

import controller.redirect.Pages;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Controller extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String actionInput = request.getParameter("action");

        if (actionInput == null) {
            response.sendRedirect(Pages.ERROR.getURL());
            return;
        }

        actionInput = actionInput.trim();

        for (Action action : Action.values()) {
            if (actionInput.equals(action.getAction())) {
                getServletContext().getRequestDispatcher(action.getURL()).forward(request, response);
                return;
            }
        }

        response.sendRedirect(Pages.ERROR.getURL());
    }
}
