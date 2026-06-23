package exception;

public class AccountDepositFailedException extends RuntimeException {
    public AccountDepositFailedException(String message) {
        super(message);
    }
}
