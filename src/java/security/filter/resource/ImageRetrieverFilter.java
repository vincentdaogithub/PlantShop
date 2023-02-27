package security.filter.resource;

import controller.redirect.ErrorRedirect;
import controller.resource.Resources;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import obj.account.Account;
import security.error.Errors;

public class ImageRetrieverFilter implements Filter {

    private final String IMAGE_PATH_AVA = "users\\ava\\";
    private final String IMAGE_EXT = ".jpg";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        Resources resource = Resources.convertStringToResource(request.getParameter("resource"));
        String DBPath = (String) request.getAttribute("localDB");

        if (resource == null || DBPath == null) {
            destroyLocalDBPath(request);
            ErrorRedirect.redirect(Errors.BAD_REQUEST, request, response);
            return;
        }

        String imageName;
        StringBuilder builder = new StringBuilder();
        builder.append(DBPath);

        switch (resource) {
            case AVATAR:
                HttpServletRequest httpServletRequest = (HttpServletRequest) request;
                Account account = (Account) httpServletRequest.getSession().getAttribute("account");

                if (account == null) {
                    destroyLocalDBPath(request);
                    ErrorRedirect.redirect(Errors.ACCESS_DENIED, request, response);
                    return;
                }

                imageName = String.valueOf(account.getID());
                builder.append(IMAGE_PATH_AVA);
                break;

            default:
                destroyLocalDBPath(request);
                ErrorRedirect.redirect(Errors.BAD_REQUEST, request, response);
                return;
        }
        
        builder.append(imageName);
        builder.append(IMAGE_EXT);
        request.setAttribute("imagePath", builder.toString());

        destroyLocalDBPath(request);
        chain.doFilter(request, response);
    }

    public void destroyLocalDBPath(ServletRequest request) {
        request.removeAttribute("localDB");
    }

    @Override
    public void destroy() {

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }
}
