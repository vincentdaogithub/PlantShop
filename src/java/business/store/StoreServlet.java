package business.store;

import controller.redirect.ErrorRedirect;
import dao.plant.PlantDAO;
import obj.plant.Plant;
import security.error.Errors;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class StoreServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Integer beginIndex = (Integer) request.getAttribute("index");
        List<Plant> plants = PlantDAO.getPlants(beginIndex);

        if (plants.isEmpty()) {
            ErrorRedirect.redirect(Errors.FILE_NOT_FOUND, request, response);
            return;
        }

        request.setAttribute("plants", plants);
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
