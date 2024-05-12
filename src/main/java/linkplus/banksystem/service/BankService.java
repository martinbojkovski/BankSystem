package linkplus.banksystem.service;

import linkplus.banksystem.models.Account;
import linkplus.banksystem.models.Bank;
import linkplus.banksystem.models.DTO.BankDTO;

import java.util.List;
import java.util.Optional;

public interface BankService{
    List<Bank> findAllBanks();
    Optional<Bank> findById(Long id);
    Optional<Bank> save(BankDTO bankDTO);
    Optional<Bank> update(Long id, BankDTO bankDTO);
    Optional<Bank> delete(Long id);

}
