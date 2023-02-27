package obj.plant;

public enum PlantStatus {
    ACTIVE(1),
    INACTIVE(0);

    private final int status;

    PlantStatus(int status) {
        this.status = status;
    }

    public final int getStatus() {
        return this.status;
    }
}
