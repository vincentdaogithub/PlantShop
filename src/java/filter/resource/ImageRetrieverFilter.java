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
import javax.servlet.http.HttpServletRequest;
import obj.account.Account;
import security.error.Errors;
import security.filter.Authentications;
import util.CheckFormat;
import util.UserInput;

public class ImageRetrieverFilter implements Filter {

    private final String IMAGE_PATH_AVA = "users\\";
    private final String IMAGE_PATH_PLANT = "plants\\";
    private final String PLACEHOLDER = "placeholder.png";

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

        switch (resource) {
            case AVATAR:
                HttpServletRequest httpServletRequest = (HttpServletRequest) request;
                Account account = (Account) httpServletRequest.getSession().getAttribute("account");

                if (account == null) {
                    destroyLocalDBPath(request);
                    ErrorRedirect.redirect(Errors.BAD_REQUEST, request, response);
                    return;
                }

                builder.append(IMAGE_PATH_AVA);

                if (account.getRole() == Authentications.ADMIN.getCode()) {
                    Integer id = UserInput.toInt(request.getParameter("id"));

                    if (id != null) {
                        builder.append(id);
                        break;
                    }
                }

                builder.append(account.getID());
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
                break;

            default:
                throw new ServletException();
        }

        File folder = new File(builder.toString());

        if (!folder.isDirectory()) {
            request.setAttribute("imgFile", new File(DBPath + PLACEHOLDER));
        } else {
            File[] files = folder.listFiles();
            boolean hasImg = false;

            for (File file : files) {
                if (CheckFormat.checkSensitive(file.getName(), "^img[.]")) {
                    request.setAttribute("imgFile", file);
                    hasImg = true;
                    break;
                }
            }

            if (!hasImg) {
                request.setAttribute("imgFile", new File(DBPath + PLACEHOLDER));
            }
        }

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
