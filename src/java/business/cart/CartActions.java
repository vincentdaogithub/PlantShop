package business.cart;

import util.UserInput;

public enum CartActions {
    
    ADD("add", "/AddToCartServlet"),
    UPDATE("update", "/UpdateCartServlet"),
    REMOVE("remove", "/RemoveCartServlet");

    private String action;
    private String URL;

    CartActions(String action, String URL) {
        this.action = action;
        this.URL = URL;
    }

    public final String getAction() {
        return this.action;
    }

    public final String getURL() {
        return this.URL;
    }

    public static final CartActions convertStringToAction(String input) {
        if (UserInput.isEmpty(input)) {
            return null;
        }

        for (CartActions action : CartActions.values()) {
            if (action.getAction().equals(input)) {
                return action;
            }
        }

        return null;
    }
}
