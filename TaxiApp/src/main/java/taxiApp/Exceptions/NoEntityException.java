package taxiApp.Exceptions;

public class NoEntityException extends Exception {
    public NoEntityException(Long id, String entityType) {
        super("No entity " + entityType + " with required id: " + id);
    }
}
