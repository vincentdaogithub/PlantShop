package controller.redirect;

public enum Servlets {
    CONTROLLER("Controller", "/Controller"),
    LOGIN_SERVLET("LoginServlet", "/LoginServlet");

    private final String servlet;
    private final String url;

    Servlets(String servlet, String url) {
        this.servlet = servlet;
        this.url = url;
    }

    public final String getServlet() {
        return servlet;
    }

    public final String getURL() {
        return url;
    }
}
