package linkplus.banksystem.service.impl;

import linkplus.banksystem.models.Account;
import linkplus.banksystem.models.Bank;
import linkplus.banksystem.models.DTO.BankDTO;
import linkplus.banksystem.models.exceptions.BankNotFoundException;
import linkplus.banksystem.repository.BankRepository;
import linkplus.banksystem.service.BankService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BankServiceImpl implements BankService {

    private final BankRepository bankRepository;

    public BankServiceImpl(BankRepository bankRepository) {
        this.bankRepository = bankRepository;
    }


    @Override
    public List<Bank> findAllBanks() {
        return this.bankRepository.findAll();
    }

    @Override
    public Optional<Bank> findById(Long id) {
        return Optional.of(this.bankRepository.findById(id).orElseThrow(BankNotFoundException::new));
    }

    @Override
    public Optional<Bank> save(BankDTO bankDTO) {
        List<Account> accounts = new ArrayList<>();
        Double totalTransactionFeeAmount = 0.00;
        Double totalTransferAmount = 0.00;
        Bank bank = new Bank(bankDTO.getName(),accounts,totalTransactionFeeAmount,totalTransferAmount, bankDTO.getFlatFeeAmount(),bankDTO.getPercentFeeAmount());
        return Optional.of(this.bankRepository.save(bank));
    }

    @Override
    public Optional<Bank> update(Long id, BankDTO bankDTO) {
        Bank bank = this.findById(id).get();
        bank.setName(bank.getName());
        bank.setFlatFeeAmount(bankDTO.getFlatFeeAmount());
        bank.setPercentFeeAmount(bankDTO.getPercentFeeAmount());
        return Optional.of(this.bankRepository.save(bank));
    }

    @Override
    public Optional<Bank> delete(Long id) {
        Bank bank = this.findById(id).get();
        this.bankRepository.delete(bank);
        return Optional.of(bank);
    }
}
