package business.account;

public enum Updates {
    EMAIL("email"),
    PASSWORD("password"),
    FULLNAME("fullname"),
    PHONE("phone"),
    AVATAR("ava");

    private final String updateType;

    Updates(String updateType) {
        this.updateType = updateType;
    }

    public final String getUpdateType() {
        return this.updateType;
    }

    public static final Updates convertStringToUpdate(String input) {
        if (input == null) {
            return null;
        }

        for (Updates update : Updates.values()) {
            if (input.equals(update.getUpdateType())) {
                return update;
            }
        }

        return null;
    }
}
