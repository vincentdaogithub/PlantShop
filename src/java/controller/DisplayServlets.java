package controller;

import controller.redirect.Pages;

public enum DisplayServlets {
    STORE(Pages.STORE, Servlets.STORE),
    CART(Pages.CART, Servlets.CART),
    ORDER(Pages.ORDER, Servlets.ORDER);

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
