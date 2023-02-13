package obj.account;

public enum AccountStatus {
    ACTIVE(1),
    INACTIVE(0);

    private final int status;

    AccountStatus(int status) {
        this.status = status;
    }

    public final int getStatus() {
        return this.status;
    }
}
