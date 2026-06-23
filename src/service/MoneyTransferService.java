package service;

import Entity.User;
import repository.UserRepository;
import exception.AccountDepositFailedException;
import exception.AccountWithdrawalFailedException;

import java.math.BigDecimal;
import java.sql.SQLException;

public class MoneyTransferService {
    private final UserRepository userRepository;

    public MoneyTransferService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void transfer(int senderId, int recipientId, BigDecimal amount){
        withdraw(senderId, amount);
        deposit(recipientId, amount);
    }

    private void deposit( int id, BigDecimal amount){
        try {
            User user = userRepository.findById(id);
            user.setBalance(user.getBalance().add(amount));
            userRepository.save(user);
        }catch (SQLException ex){
            throw new AccountDepositFailedException(ex.getMessage());
        }
    }

    private void withdraw(int id, BigDecimal amount){
        try {
            User user = userRepository.findById(id);
            user.setBalance(user.getBalance().subtract(amount));
            userRepository.save(user);
        }catch (SQLException ex){
            throw new AccountWithdrawalFailedException(ex.getMessage());
        }
    }
}