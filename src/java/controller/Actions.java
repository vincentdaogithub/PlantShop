package controller;

public enum Actions {

    LOGIN("login", "/LoginServlet"),
    REGISTER("register", "/RegisterServlet"),
    USER_UPDATE("user-update", "/UpdateServlet"),
    LOG_OUT("logout", "/LogOutServlet"),
    CART_UPDATE("cart-update", "/CartUpdateServlet");

    private String action;
    private String url;

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
