package linkplus.banksystem.service;


import linkplus.banksystem.models.Account;
import linkplus.banksystem.models.DTO.AccountDTO;

import java.util.List;
import java.util.Optional;

public interface AccountService {
    List<Account> findAll();
    Optional<Account> findById(Long id);
    Optional<Account> save(AccountDTO accountDTO);
}
