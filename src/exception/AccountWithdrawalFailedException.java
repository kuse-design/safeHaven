package exception;

public class AccountWithdrawalFailedException extends RuntimeException {
    public AccountWithdrawalFailedException(String message) {
        super(message);
    }
}
