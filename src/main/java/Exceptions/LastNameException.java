package Exceptions;

public class LastNameException extends RuntimeException {
    public LastNameException() {
    }

    public LastNameException(String message) {
        super(message);
    }

    public LastNameException(String message, Throwable cause) {
        super(message, cause);
    }
}
