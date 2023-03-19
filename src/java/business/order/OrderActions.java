package business.order;

public enum OrderActions {

    ADD("add"),
    ADD_ALL("add-all"),
    CANCEL("cancel"),
    CANCEL_ALL("cancel-all"),
    ORDER_AGAIN("order-again");

    private final String action;

    OrderActions(String action) {
        this.action = action;
    }

    public final String getAction() {
        return this.action;
    }

    public static final OrderActions convertStringToAction(String input) {
        if (input == null) {
            return null;
        }

        for (OrderActions action : OrderActions.values()) {
            if (action.getAction().equals(input)) {
                return action;
            }
        }

        return null;
    }
}
