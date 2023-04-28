package obj.account;

public enum AccountStatuses {
    ACTIVE(1),
    INACTIVE(0);

    private final int status;

    AccountStatuses(int status) {
        this.status = status;
    }

    public final int getStatus() {
        return this.status;
    }
}
