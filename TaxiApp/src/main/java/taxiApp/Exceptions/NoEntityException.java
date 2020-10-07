package taxiApp.Exceptions;

public class NoEntityException extends Exception {
    public NoEntityException(Long id) {
        super("No entity with required id: " + id);
    }
}
