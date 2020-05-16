package Exceptions;

public class NoEntityException extends Exception {
    public NoEntityException(int id) {
        super("No entity with required id: " + id);
    }
}
