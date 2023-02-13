package obj.account;

public enum Accounts {
    ADMIN(1),
    USER(0);

    private final int role;

    Accounts(int role) {
        this.role = role;
    }

    public final int getRole() {
        return this.role;
    }
}
