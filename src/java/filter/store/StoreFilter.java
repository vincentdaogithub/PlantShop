package filter.store;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import obj.plant.Plant;
import util.UserInput;

public class StoreFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        Integer index = UserInput.toInt(request.getParameter("index"));

        if (index == null || index < 0) {
            request.setAttribute("index", 0);
        } else {
            request.setAttribute("index", index);
        }

        chain.doFilter(request, response);

        @SuppressWarnings("unchecked")
        List<Plant> plants = (List<Plant>) request.getAttribute("plants");
        request.setAttribute("size", plants.size());
    }

    @Override
    public void destroy() {

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }
}
