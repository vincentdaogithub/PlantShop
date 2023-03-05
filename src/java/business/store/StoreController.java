package business.store;

import controller.redirect.ErrorRedirect;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import security.error.Errors;

public class StoreController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        StoreActions storeAction = (StoreActions) request.getAttribute("storeAction");
        request.removeAttribute("storeAction");

        switch (storeAction) {
            case ADD_TO_CART:
                request.getRequestDispatcher(storeAction.getServlet()).include(request, response);
                request.setAttribute("requestPage", request.getSession().getAttribute("lastPage"));
                break;

            default:
                ErrorRedirect.redirect(Errors.SERVER_ERROR, request, response);
        }
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
