package linkplus.banksystem.service.impl;

import linkplus.banksystem.models.Account;
import linkplus.banksystem.models.Bank;
import linkplus.banksystem.models.DTO.TransactionDTO;
import linkplus.banksystem.models.Transaction;
import linkplus.banksystem.models.TransactionType;
import linkplus.banksystem.models.exceptions.*;
import linkplus.banksystem.repository.AccountRepository;
import linkplus.banksystem.repository.BankRepository;
import linkplus.banksystem.repository.TransactionRepository;
import linkplus.banksystem.service.TransactionService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;
    private final BankRepository bankRepository;
    private static final Double THRESHOLD_FEE = 500.00;

    public TransactionServiceImpl(TransactionRepository transactionRepository, AccountRepository accountRepository, BankRepository bankRepository) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
        this.bankRepository = bankRepository;
    }

    @Override
    public List<Transaction> getAllTransactions() {
        return this.transactionRepository.findAll();
    }

    @Override
    public Optional<Transaction> doTransaction(TransactionDTO transactionDTO) {
        Account accountSender = this.accountRepository.findById(transactionDTO.getOriginatingAccountId()).orElseThrow(() -> new CustomerAccountNotFoundException(transactionDTO.getOriginatingAccountId()));
        Account accountReceiver = this.accountRepository.findById(transactionDTO.getResultingAccountId()).orElseThrow(() -> new CustomerAccountNotFoundException(transactionDTO.getResultingAccountId()));
        Bank sendersBank = this.bankRepository.findById(accountSender.getBank().getBankId()).orElseThrow(BankNotFoundException::new);

        if(transactionDTO.getType().equals(TransactionType.TRANSACTION)){

            if(accountSender.getTotalBalance() >= transactionDTO.getAmount()){
                accountSender.setTotalBalance(accountSender.getTotalBalance() - transactionDTO.getAmount());
                this.accountRepository.save(accountSender);
                if(transactionDTO.getAmount() > THRESHOLD_FEE){

                    Double amountReceived = transactionDTO.getAmount() * (100-sendersBank.getPercentFeeAmount()) / 100;
                    sendersBank.setTotalTransactionFeeAmount(sendersBank.getTotalTransactionFeeAmount() + (transactionDTO.getAmount() * sendersBank.getPercentFeeAmount() / 100));
                    sendersBank.setTotalTransferAmount(sendersBank.getTotalTransferAmount() + transactionDTO.getAmount());
                    this.bankRepository.save(sendersBank);

                    accountReceiver.setTotalBalance(accountReceiver.getTotalBalance() + amountReceived);
                    this.accountRepository.save(accountReceiver);
                }else{
                    Double amountReceived = transactionDTO.getAmount() - sendersBank.getFlatFeeAmount();
                    sendersBank.setTotalTransactionFeeAmount(sendersBank.getTotalTransactionFeeAmount() + sendersBank.getFlatFeeAmount());
                    sendersBank.setTotalTransferAmount(sendersBank.getTotalTransferAmount() + transactionDTO.getAmount());
                    this.bankRepository.save(sendersBank);

                    accountReceiver.setTotalBalance(accountReceiver.getTotalBalance() + amountReceived);
                    this.accountRepository.save(accountReceiver);
            }

            }else {
                throw new InsufficientFundsException(accountSender.getId());
            }

        }
        else if(transactionDTO.getType().equals(TransactionType.DEPOSIT)){
            if(accountSender.equals(accountReceiver)){
                sendersBank.setTotalTransferAmount(sendersBank.getTotalTransferAmount() + transactionDTO.getAmount());
                this.bankRepository.save(sendersBank);

                accountReceiver.setTotalBalance(accountReceiver.getTotalBalance() + transactionDTO.getAmount());
                this.accountRepository.save(accountReceiver);
            }else{
                throw new DepositException();
            }
        }
        else{ // TransactionType.WITHDRAW
            if(accountSender.equals(accountReceiver)){
                if(accountSender.getTotalBalance() > transactionDTO.getAmount()){
                    sendersBank.setTotalTransferAmount(sendersBank.getTotalTransferAmount() + transactionDTO.getAmount());
                    this.bankRepository.save(sendersBank);

                    accountReceiver.setTotalBalance(accountReceiver.getTotalBalance() - transactionDTO.getAmount());
                    this.accountRepository.save(accountReceiver);
                }else{
                    throw new InsufficientFundsException(accountSender.getId());
                }
            }else {
                throw new WithdrawException();
            }
        }

        Transaction transaction = new Transaction(transactionDTO.getAmount(), transactionDTO.getOriginatingAccountId(),
                transactionDTO.getResultingAccountId(), transactionDTO.getReason(), transactionDTO.getType());

        return Optional.of(this.transactionRepository.save(transaction));
    }

    @Override
    public List<Transaction> getTransactionsForAccount(Long id) {
        if(this.accountRepository.findById(id).isPresent()){
            if(this.transactionRepository.findByOriginatingAccountIdOrResultingAccountId(id, id).isEmpty())
            {
                throw new RuntimeException("The account doesn't have any transactions done yet");
            }else{
                return this.transactionRepository.findByOriginatingAccountIdOrResultingAccountId(id, id);
            }

        }else{
            throw new CustomerAccountNotFoundException(id);
        }

    }
}
