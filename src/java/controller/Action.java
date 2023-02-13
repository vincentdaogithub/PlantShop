package controller;

public enum Action {
    LOGIN("login", "/LoginServlet"),
    REGISTER("register", "/RegisterServlet");

    private final String action;
    private final String url;

    Action(String action, String url) {
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
