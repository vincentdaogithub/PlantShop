package filter.resource;

import controller.redirect.ErrorRedirect;
import controller.resource.Resources;
import java.io.File;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import obj.account.Account;
import security.error.Errors;
import util.CheckFormat;
import util.UserInput;

@MultipartConfig
public class ImageUploadFilter implements Filter {

    private final String IMAGE_PATH_AVA = "users\\";
    private final String IMAGE_PATH_PLANT = "plants\\";

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

        StringBuilder builder = new StringBuilder();
        builder.append(DBPath);
        
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;

        switch (resource) {
            case AVATAR:
                Account account = (Account) httpServletRequest.getSession().getAttribute("account");

                if (account == null) {
                    destroyLocalDBPath(request);
                    ErrorRedirect.redirect(Errors.BAD_REQUEST, request, response);
                    return;
                }

                builder.append(IMAGE_PATH_AVA);
                builder.append(account.getID());
                request.setAttribute("resourceType", Resources.AVATAR);
                break;

            case PLANT:
                String pid = request.getParameter("pid");

                if (UserInput.isEmpty(pid)) {
                    destroyLocalDBPath(request);
                    ErrorRedirect.redirect(Errors.BAD_REQUEST, request, response);
                    return;
                }

                builder.append(IMAGE_PATH_PLANT);
                builder.append(pid);
                request.setAttribute("resourceType", Resources.PLANT);
                break;

            default:
                throw new ServletException();
        }

        File folder = new File(builder.toString());

        if (!folder.isDirectory()) {
            if (!folder.mkdir()) {
                ErrorRedirect.redirect(Errors.SERVER_ERROR, request, response);
                return;
            }
        } else {
            File[] files = folder.listFiles();

            for (File file : files) {
                if (CheckFormat.checkSensitive(file.getName(), "^img[.]")) {
                    if (!file.delete()) {
                        ErrorRedirect.redirect(Errors.SERVER_ERROR, request, response);
                        return;
                    }
                }
            }
        }

        request.setAttribute("imgFolder", folder);
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
