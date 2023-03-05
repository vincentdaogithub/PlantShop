package controller;

import controller.redirect.Pages;

public enum ServletMappings {
    STORE(Pages.STORE, Servlets.STORE);

    private Pages page;
    private Servlets servlet;

    ServletMappings(Pages page, Servlets servlet) {
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
