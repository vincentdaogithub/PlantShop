package business.store;

public enum StoreActions {
    ADD_TO_CART("add-to-cart", "/AddToCartServlet");

    private String action;
    private String servlet;

    StoreActions(String action, String servlet) {
        this.action = action;
        this.servlet = servlet;
    }

    public final String getAction() {
        return this.action;
    }

    public final String getServlet() {
        return this.servlet;
    }

    public static final StoreActions convertStringToAction(String input) {
        if (input == null) {
            return null;
        }

        for (StoreActions action : StoreActions.values()) {
            if (input.equals(action.getAction())) {
                return action;
            }
        }

        return null;
    }
}
