package security.filter;

public enum Authentications {

    LOGGED_IN(2),
    ADMIN(1),
    USER(0),
    OPEN(-1),
    SERVER(-2);

    private final int code;

    Authentications(int code) {
        this.code = code;
    }

    public final int getCode() {
        return this.code;
    }
}
