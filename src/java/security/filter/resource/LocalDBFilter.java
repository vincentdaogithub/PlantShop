package security.filter.resource;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class LocalDBFilter implements Filter {

    private FilterConfig filterConfig;
    private String LOCAL_DB_PATH = null;
    private final String LOCAL_DB_NAME = "\\local_db\\";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        if (LOCAL_DB_PATH != null) {
            request.setAttribute("localDB", LOCAL_DB_PATH);
            chain.doFilter(request, response);
            return;
        }

        String DBName = request.getServletContext().getContextPath().replace("/", "");
        String projectPath = filterConfig.getServletContext().getRealPath("/");
        int projectIndex = projectPath.indexOf(DBName);
        LOCAL_DB_PATH = projectPath.substring(0, projectIndex + DBName.length()) + LOCAL_DB_NAME;

        request.setAttribute("localDB", LOCAL_DB_PATH);
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }
}
