package Exceptions;

public class CinemaNameException extends RuntimeException{
    public CinemaNameException() {
    }

    public CinemaNameException(String message) {
        super(message);
    }

    public CinemaNameException(String message, Throwable cause) {
        super(message, cause);
    }
}
