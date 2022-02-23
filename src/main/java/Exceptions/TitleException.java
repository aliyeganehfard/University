package Exceptions;

public class TitleException extends RuntimeException{
    public TitleException() {
    }

    public TitleException(String message) {
        super(message);
    }

    public TitleException(String message, Throwable cause) {
        super(message, cause);
    }
}
