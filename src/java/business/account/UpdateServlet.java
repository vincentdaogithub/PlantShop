package business.account;

import controller.redirect.ErrorRedirect;
import dao.account.AccountDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import obj.account.Account;
import security.error.Errors;

@MultipartConfig
public class UpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Updates update = Updates.convertStringToUpdate(request.getParameter("update"));
        
        if (update == Updates.AVATAR) {
            return;
        }
        
        String updateValue = (String) request.getAttribute("updateValue");
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");

        if (!AccountDAO.updateAccount(update, account.getEmail(), updateValue)) {
            ErrorRedirect.redirect(Errors.BAD_REQUEST, request, response);
            return;
        }

        String email = (String) request.getAttribute("email");
        String password = (String) request.getAttribute("password");
        session.setAttribute("account", AccountDAO.getAccount(email, password));

        clearRequestData(request);
    }

    private void clearRequestData(HttpServletRequest request) {
        request.removeAttribute("update");
        request.removeAttribute("updateValue");
        request.removeAttribute("email");
        request.removeAttribute("password");
    }
}
