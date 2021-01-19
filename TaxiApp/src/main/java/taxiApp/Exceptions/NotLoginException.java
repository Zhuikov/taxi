package taxiApp.Exceptions;

public class NotLoginException extends RuntimeException {
    public NotLoginException() {
        super("Not login");
    }
}
