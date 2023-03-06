package controller;

public enum Servlets {
    PAGE_REDIRECT("/PageRedirect"),
    CONTROLLER("/Controller"),
    STORE("/StoreServlet"),
    CART("/CartServlet");

    private final String servlet;

    Servlets(String servlet) {
        this.servlet = servlet;
    }

    public final String getServletURL() {
        return this.servlet;
    }
}
