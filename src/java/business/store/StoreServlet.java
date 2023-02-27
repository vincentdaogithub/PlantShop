package business.store;

import controller.redirect.ErrorRedirect;
import dao.plant.PlantDAO;
import obj.plant.Plant;
import security.error.Errors;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class StoreServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ArrayList<Plant> plants = PlantDAO.getPlants();

        if (plants == null) {
            ErrorRedirect.redirect(Errors.FILE_NOT_FOUND, request, response);
            return;
        }

        request.setAttribute("plants", plants);
    }
}
