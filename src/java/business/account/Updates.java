package business.account;

public enum Updates {
    EMAIL("email"),
    PASSWORD("password"),
    FULLNAME("fullname"),
    PHONE("phone");

    private final String updateType;

    Updates(String updateType) {
        this.updateType = updateType;
    }

    public final String getUpdateType() {
        return this.updateType;
    }

    public static final Updates convertStringToUpdateType(String input) {
        if (input == null) {
            throw new NullPointerException();
        }

        for (Updates update : Updates.values()) {
            if (input.equals(update.getUpdateType())) {
                return update;
            }
        }

        return null;
    }
}
