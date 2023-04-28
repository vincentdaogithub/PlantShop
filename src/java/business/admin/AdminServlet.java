package business.admin;

import controller.redirect.ErrorRedirect;
import dao.account.AccountDAO;
import dao.plant.PlantDAO;
import java.io.IOException;
import java.util.StringTokenizer;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import obj.plant.Plant;
import security.error.Errors;
import util.UserInput;

public class AdminServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private static final String ADMIN_ACTION = "admin-action";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter(ADMIN_ACTION);

        if (action == null || action.trim().isEmpty()) {
            ErrorRedirect.redirect(Errors.BAD_REQUEST, request, response);
            return;
        }

        String args = request.getParameter(action);

        if (args == null || args.trim().isEmpty()) {
            ErrorRedirect.redirect(Errors.BAD_REQUEST, request, response);
            return;
        }

        StringTokenizer tokenizer = new StringTokenizer(args, "-");
        String target = tokenizer.hasMoreTokens() ? tokenizer.nextToken() : null;
        String targetArg = tokenizer.hasMoreTokens() ? tokenizer.nextToken() : null;

        if (target == null || targetArg == null) {
            ErrorRedirect.redirect(Errors.BAD_REQUEST, request, response);
            return;
        }

        final boolean isSuccessful;

        switch (target) {
            case "account":
                isSuccessful = updateAccount(targetArg, tokenizer);
                break;

            case "plant":
                isSuccessful = plantFunction(targetArg, tokenizer, request);
                break;

            default:
                isSuccessful = false;
                break;
        }

        if (!isSuccessful) {
            ErrorRedirect.redirect(Errors.BAD_REQUEST, request, response);
        }
    }

    private boolean updateAccount(String targetArg, StringTokenizer tokenizer) {

        if (!targetArg.equals("all")) {
            Integer accountID = UserInput.toInt(targetArg);

            if (accountID == null) {
                return false;
            }

            String updateTarget = tokenizer.hasMoreTokens() ? tokenizer.nextToken() : null;
            String updateArg = tokenizer.hasMoreTokens() ? tokenizer.nextToken() : null;

            if (updateTarget == null || updateArg == null) {
                return false;
            }

            switch (updateTarget) {
                case "status":
                    Integer status = UserInput.toInt(updateArg);

                    if (status == null) {
                        return false;
                    }

                    return AccountDAO.updateStatus(accountID, status);

                default:
                    return false;
            }
        }

        return true;
    }

    private boolean plantFunction(String targerArg, StringTokenizer tokenizer, HttpServletRequest request) {

        if (targerArg.equals("new")) {
            return createPlant(request);
        }

        return false;
    }

    private boolean createPlant(HttpServletRequest request) {

        String name = request.getParameter("name");

        if (UserInput.isEmpty(name)) {
            return false;
        }

        Integer price = UserInput.toInt(request.getParameter("price"));

        if (price == null) {
            return false;
        }

        String description = request.getParameter("description");

        if (UserInput.isEmpty(description)) {
            return false;
        }

        Integer status = UserInput.toInt(request.getParameter("status"));

        if (status == null) {
            return false;
        }

        Integer category = UserInput.toInt(request.getParameter("category"));

        if (category == null) {
            return false;
        }

        Plant plant = new Plant(0, name, price, description, status, category, "");
        return PlantDAO.addPlant(plant);
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
