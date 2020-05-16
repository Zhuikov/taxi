package Exceptions;

public class NoUnusedCarsException extends Exception {
    public NoUnusedCarsException() {
        super("There is no unused cars");
    }
}
