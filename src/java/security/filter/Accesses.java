package security.filter;

public enum Accesses {
    APPROVED(1),
    DENIED(0),
    REQUESTING(-1);

    private final int code;

    Accesses(int code) {
        this.code = code;
    }

    public final int getCode() {
        return this.code;
    }
}
