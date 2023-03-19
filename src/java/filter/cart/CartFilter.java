package filter.cart;

import business.sort.ComparatorPlant;
import business.sort.PriceRanges;
import business.sort.Searches;
import business.sort.Sorts;
import business.store.StoreActions;
import controller.redirect.ErrorRedirect;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map.Entry;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import obj.plant.Plant;
import security.error.Errors;
import util.CheckFormat;
import util.RegexBuilder;
import util.UserInput;

public class CartFilter implements Filter {

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
        
        Searches search = Searches.convertStringToSearch(request.getParameter(StoreActions.SEARCH.getAction()));

        if (search != null && !doSearch(request, search)) {
            ErrorRedirect.redirect(Errors.BAD_REQUEST, request, response);
            return;
        }

        Sorts sort = Sorts.convertStringToSort(request.getParameter(StoreActions.SORT.getAction()));

        if (sort != null && !doSort(request, sort)) {
            ErrorRedirect.redirect(Errors.BAD_REQUEST, request, response);
        }

        @SuppressWarnings("unchecked")
        List<Entry<Plant, Integer>> cart = (List<Entry<Plant, Integer>>) request.getAttribute("cart");
        request.setAttribute("size", cart.size());
    }
    
    private boolean doSearch(ServletRequest request, Searches search) {

        @SuppressWarnings("unchecked")
        List<Entry<Plant, Integer>> cart = Collections.synchronizedList((List<Entry<Plant, Integer>>) request.getAttribute("cart"));

        synchronized(cart) {
            switch (search) {
                case NAME:
                    String searchName = request.getParameter(Searches.NAME.getSearch());
                    String regex = new RegexBuilder().generateSearchRegex(searchName).build();

                    if (regex.equals(".")) {
                        request.removeAttribute("searchQuery");
                        return true;
                    }

                    cart.removeIf((entry) -> {
                        return !CheckFormat.checkInsensitive(entry.getKey().getName(), regex);
                    });

                    request.setAttribute("searchName", searchName);
                    
                    break;
    
                case PRICE:
                    String searchPrice = request.getParameter(search.getSearch());
                    PriceRanges range = PriceRanges.convertStringToRange(searchPrice);
    
                    if (range == null) {
                        return false;
                    }
    
                    switch (range) {
                        case BELOW_5:
                            cart.removeIf((entry) -> {
                                return entry.getKey().getPrice() >= 5;
                            });

                            break;

                        case FIVE_TO_10:
                            cart.removeIf((entry) -> {
                                return entry.getKey().getPrice() < 5 || 10 < entry.getKey().getPrice();
                            });

                            break;

                        case TEN_TO_15:
                            cart.removeIf((entry) -> {
                                return entry.getKey().getPrice() < 10 || 15 < entry.getKey().getPrice();
                            });

                            break;

                        case ABOVE_15:
                            cart.removeIf((entry) -> {
                                return entry.getKey().getPrice() <= 15;
                            });

                            break;
                    
                        default:
                            return false;
                    }

                    request.setAttribute("searchPrice", searchPrice);
                    break;

                default:
                    return false;
            }
        }

        StringBuilder builder = new StringBuilder();
        builder.append("&search=");
        builder.append(search.getSearch());
        builder.append('&');
        builder.append(search.getSearch());
        builder.append('=');
        builder.append(request.getParameter(search.getSearch()));

        request.setAttribute("searchQuery", builder.toString());
        return true;
    }

    private boolean doSort(ServletRequest request, Sorts sort) {

        @SuppressWarnings("unchecked")
        List<Entry<Plant, Integer>> cart = Collections.synchronizedList((List<Entry<Plant, Integer>>) request.getAttribute("cart"));

        synchronized(cart) {
            switch (sort) {
                case ORDER_TIME:
                    break;

                case NAME_ASC:
                    cart.sort(ComparatorPlant.entryNameAscending());
                    break;

                case NAME_DSC:
                    cart.sort(ComparatorPlant.entryNameDescending());
                    break;

                case PRICE_ASC:
                    cart.sort(ComparatorPlant.entryPriceAscending());
                    break;

                case PRICE_DSC:
                    cart.sort(ComparatorPlant.entryPriceDescending());
                    break;
            
                default:
                    return false;
            }
        }

        request.setAttribute("sortCheck", sort.getSort());
        return true;
    }

    @Override
    public void destroy() {

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }
}
