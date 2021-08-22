package au.com.reece.addressbook.exceptions;

public class ErrorPayload {
    private final int httpStatus;
    private final ErrorType error;
    private final String message;

    public ErrorPayload(int httpStatus, ErrorType error, String message) {
        this.httpStatus = httpStatus;
        this.error = error;
        this.message = message;
    }

    public int getHttpStatus() {
        return httpStatus;
    }

    public ErrorType getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "ErrorPayload{" +
                "httpStatus=" + httpStatus +
                ", error='" + error + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
