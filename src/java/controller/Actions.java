package controller;

public enum Actions {
    LOGIN("login", "/LoginServlet"),
    REGISTER("register", "/RegisterServlet"),
    USER_UPDATE("account-update", "/UpdateAccountServlet"),
    LOG_OUT("log-out", "/LogOutServlet");

    private final String action;
    private final String url;

    Actions(String action, String url) {
        this.action = action;
        this.url = url;
    }

    public String getAction() {
        return this.action;
    }

    public String getURL() {
        return this.url;
    }
}
