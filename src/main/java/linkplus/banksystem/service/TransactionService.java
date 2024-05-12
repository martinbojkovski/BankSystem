package linkplus.banksystem.service;

import linkplus.banksystem.models.DTO.TransactionDTO;
import linkplus.banksystem.models.Transaction;

import java.util.List;
import java.util.Optional;

public interface TransactionService {
    List<Transaction> getAllTransactions();
    Optional<Transaction> doTransaction(TransactionDTO transactionDTO);
    List<Transaction> getTransactionsForAccount(Long id);
}
