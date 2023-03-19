package obj.order;

public enum OrderStatuses {

    UNDEFINED(0, "undefined"),
    PROCESSING(1, "processing"),
    COMPLETED(2, "completed"),
    CANCELLED(3, "cancelled");

    private final int statusCode;
    private final String status;

    OrderStatuses(int statusCode, String status) {
        this.statusCode = statusCode;
        this.status = status;
    }

    public final int getStatusCode() {
        return statusCode;
    }

    public final String getStatus() {
        return this.status;
    }

    public static final OrderStatuses convertIntToStatus(Integer input) {
        if (input == null) {
            return null;
        }

        for (OrderStatuses status : OrderStatuses.values()) {
            if (status.getStatusCode() == input) {
                return status;
            }
        }

        return null;
    }
}
