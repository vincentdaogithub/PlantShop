package obj.plant;

public enum PlantStatuses {
    ACTIVE(1),
    INACTIVE(0);

    private final int status;

    PlantStatuses(int status) {
        this.status = status;
    }

    public final int getStatus() {
        return this.status;
    }

    public static final PlantStatuses convertIntToStatus(Integer input) {
        if (input == null) {
            return null;
        }

        for (PlantStatuses status : PlantStatuses.values()) {
            if (status.getStatus() == input) {
                return status;
            }
        }

        return null;
    }
}
