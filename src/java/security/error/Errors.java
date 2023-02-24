package security.error;

public enum Errors {
    BAD_REQUEST("400", "Bad request", "Seriously. How can we know which one to show you?"),
    UNAUTHORIZED("401", "Unauthorized access", "Wait a minute. What are you doing here?"),
    ACCESS_DENIED("402", "Access denied", "Curiosity killed the cat. Or technically YOU."),
    FILE_NOT_FOUND("404", "File(s) not found", "Poof! Out of existence."),
    SERVER_ERROR("500", "Server error", "Yeah I know. My code sucks."),
    USER_EXISTED("USER_EXISTED", "The user has already exist", "Whoa whoa. Doppelgänger?");

    private final String errorCode;
    private final String errorDetail;
    private final String errorMessage;

    Errors(String errorCode, String errorDetail, String errorMessage) {
        this.errorCode = errorCode;
        this.errorDetail = errorDetail;
        this.errorMessage = errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorDetail() {
        return errorDetail;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
