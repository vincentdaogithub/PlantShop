package security.filter.store;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import business.store.Searches;
import business.store.Sorts;
import business.store.StoreActions;
import controller.redirect.ErrorRedirect;
import obj.plant.Plant;
import security.error.Errors;
import security.filter.store.comparator.ComparatorPlant;

public class StoreFunctionFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        chain.doFilter(request, response);

        StoreActions action = StoreActions.convertStringToAction(request.getParameter("action"));

        if (action == null) {
            return;
        }

        switch (action) {
            case SEARCH:
                Searches search = Searches.convertStringToSearch(action.getAction());

                if (search == null) {
                    ErrorRedirect.redirect(Errors.SERVER_ERROR, request, response);
                    return;
                }

                break;

            case SORT:
                Sorts sort = Sorts.convertStringToSort(request.getParameter(action.getAction()));

                if (sort == null || !doSort(request, sort)) {
                    ErrorRedirect.redirect(Errors.SERVER_ERROR, request, response);
                    return;
                }

                break;
                
            default:
                ErrorRedirect.redirect(Errors.BAD_REQUEST, request, response);
        }
    }

    private boolean doSort(ServletRequest request, Sorts sort) {

        @SuppressWarnings("unchecked")
        List<Plant> plants = Collections.synchronizedList((List<Plant>) request.getAttribute("plants"));

        synchronized(plants) {
            switch (sort) {
                case NAME_ASC:
                    plants.sort(ComparatorPlant.nameAscending());
                    break;

                case NAME_DSC:
                    plants.sort(ComparatorPlant.nameDescending());
                    break;

                case PRICE_ASC:
                    plants.sort(ComparatorPlant.priceAscending());
                    break;

                case PRICE_DSC:
                    plants.sort(ComparatorPlant.priceDescending());
                    break;
            
                default:
                    return false;
            }
        }

        request.setAttribute("plants", plants);
        return true;
    }

    @Override
    public void destroy() {

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }
}
