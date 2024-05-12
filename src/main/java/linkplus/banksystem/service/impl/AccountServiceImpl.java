package linkplus.banksystem.service.impl;

import linkplus.banksystem.models.Account;
import linkplus.banksystem.models.Bank;
import linkplus.banksystem.models.DTO.AccountDTO;
import linkplus.banksystem.models.exceptions.BankNotFoundException;
import linkplus.banksystem.models.exceptions.CustomerAccountNotFoundException;
import linkplus.banksystem.repository.AccountRepository;
import linkplus.banksystem.repository.BankRepository;
import linkplus.banksystem.service.AccountService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final BankRepository bankRepository;

    public AccountServiceImpl(AccountRepository accountRepository, BankRepository bankRepository) {
        this.accountRepository = accountRepository;
        this.bankRepository = bankRepository;
    }


    @Override
    public List<Account> findAll() {
        return this.accountRepository.findAll();
    }

    @Override
    public Optional<Account> findById(Long id) {
        return Optional.of(this.accountRepository.findById(id).orElseThrow(() -> new CustomerAccountNotFoundException(id)));
    }

    @Override
    public Optional<Account> save(AccountDTO accountDTO) {
        Bank bank = this.bankRepository.findById(accountDTO.getBank()).orElseThrow(BankNotFoundException::new);
        Account account = new Account(accountDTO.getUsername(),accountDTO.getTotalBalance(),bank);
        return Optional.of(this.accountRepository.save(account));
    }
}
