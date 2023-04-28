package controller;

import controller.redirect.Pages;

public enum DisplayServlets {
    STORE(Pages.STORE, Servlets.STORE),
    CART(Pages.CART, Servlets.CART),
    ORDER(Pages.ORDER, Servlets.ORDER),
    MANAGE_ACCOUNTS(Pages.MANAGE_ACCOUNTS, Servlets.ADMIN_ACCOUNTS),
    VIEW_ORDERS(Pages.VIEW_ORDERS, Servlets.ADMIN_ORDERS),
    MANAGE_PLANTS(Pages.MANAGE_PLANTS, Servlets.ADMIN_PLANTS),
    CREATE_PLANT(Pages.CREATE_PLANT, Servlets.ADMIN_CATEGORIES);

    private final Pages page;
    private final Servlets servlet;

    DisplayServlets(Pages page, Servlets servlet) {
        this.page = page;
        this.servlet = servlet;
    }

    public final Pages getPage() {
        return this.page;
    }

    public final Servlets getServlet() {
        return this.servlet;
    }
}
