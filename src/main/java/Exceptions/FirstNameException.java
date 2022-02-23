package Exceptions;

public class FirstNameException extends RuntimeException{
    public FirstNameException() {
    }

    public FirstNameException(String message) {
        super(message);
    }

    public FirstNameException(String message, Throwable cause) {
        super(message, cause);
    }
}
