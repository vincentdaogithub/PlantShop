package business.store;

public enum StoreActions {
    SEARCH("search"),
    SORT("sort");

    private String action;

    StoreActions(String action) {
        this.action = action;
    }

    public String getAction() {
        return this.action;
    }

    public static final StoreActions convertStringToAction(String input) {
        if (input == null) {
            return null;
        }

        for (StoreActions action : StoreActions.values()) {
            if (action.getAction().equals(input)) {
                return action;
            }
        }

        return null;
    }
}
