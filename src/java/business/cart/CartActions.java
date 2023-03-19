package business.cart;

import util.UserInput;

public enum CartActions {
    
    ADD("add"),
    UPDATE("update"),
    REMOVE("remove");

    private final String action;

    CartActions(String action) {
        this.action = action;
    }

    public final String getAction() {
        return this.action;
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
