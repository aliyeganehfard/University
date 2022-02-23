package Exceptions;

public class UnitException extends RuntimeException{
    public UnitException() {
    }

    public UnitException(String message) {
        super(message);
    }

    public UnitException(String message, Throwable cause) {
        super(message, cause);
    }
}
