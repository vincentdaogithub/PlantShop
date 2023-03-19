package business.cart;

import dao.plant.PlantDAO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import obj.plant.Plant;

public class CartServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Object cartObject = request.getSession().getAttribute("cart");
        Map<Integer, Integer> cartAsID;

        if (cartObject == null) {
            cartAsID = Collections.synchronizedMap(new LinkedHashMap<>());
        } else {
            @SuppressWarnings("unchecked")
            Map<Integer, Integer> tmp = (Map<Integer, Integer>) cartObject;
            cartAsID = tmp;
        }
        
        Map<Plant, Integer> cartAsPlant = new LinkedHashMap<>();
        
        cartAsID.forEach((key, value) -> {
            Plant plant = PlantDAO.getPlant(key);
            
            if (plant != null) {
                cartAsPlant.put(plant, value);
            }
        });

        request.setAttribute("cart", new ArrayList<>(cartAsPlant.entrySet()));
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
