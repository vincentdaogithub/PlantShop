package controller;

public enum Servlets {
    PAGE_REDIRECT("/PageRedirect"),
    CONTROLLER("/Controller");

    private final String servlet;

    Servlets(String servlet) {
        this.servlet = servlet;
    }

    public final String getServlet() {
        return this.servlet;
    }
}
