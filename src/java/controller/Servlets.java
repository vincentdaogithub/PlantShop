package controller;

public enum Servlets {
    PAGE_REDIRECT("/PageRedirect"),
    CONTROLLER("/Controller"),
    STORE("/StoreServlet"),
    CART("/CartServlet"),
    IMAGE_UPLOAD("/ImageUploader"),
    ORDER("/OrderServlet"),
    ADMIN_ACCOUNTS("/AdminAccountsServlet"),
    ADMIN_ORDERS("/AdminOrdersServlet"),
    ADMIN_PLANTS("/AdminPlantsServlet"),
    ADMIN_CATEGORIES("/AdminCategoriesServlet");

    private final String servlet;

    Servlets(String servlet) {
        this.servlet = servlet;
    }

    public final String getServletURL() {
        return this.servlet;
    }
}
