package controller;

import controller.redirect.Pages;

public enum Actions {
    LOGIN("login", "/LoginServlet", Pages.HOME),
    REGISTER("register", "/RegisterServlet", Pages.HOME),
    USER_UPDATE("update", "/UpdateServlet", Pages.PROFILE),
    LOG_OUT("logout", "/LogOutServlet", Pages.HOME),
    STORE("store", "/StoreServlet", Pages.STORE);

    private String action;
    private String url;
    private Pages pageRedirect;

    Actions(String action, String url, Pages pageRedirect) {
        this.action = action;
        this.url = url;
        this.pageRedirect = pageRedirect;
    }

    public String getAction() {
        return this.action;
    }

    public String getURL() {
        return this.url;
    }

    public Pages getPageRedirect() {
        return this.pageRedirect;
    }

    public static final Actions convertStringToAction(String input) {
        if (input == null) {
            return null;
        }

        for (Actions action : Actions.values()) {
            if (input.equals(action.getAction())) {
                return action;
            }
        }

        return null;
    }
}
