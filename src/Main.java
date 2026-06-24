import repository.UserRepository;
import service.MoneyTransferService;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        int senderId = 1001;
        int recipientId = 1002;
        BigDecimal amount = new BigDecimal("500");
        MoneyTransferService moneyTransferService = new MoneyTransferService(new UserRepository());
        moneyTransferService.transfer(senderId, recipientId, amount);
    }

}