package exception;

public class TableCreationFailedException extends RuntimeException {
    public TableCreationFailedException(String message) {
        super(message);
    }
}
