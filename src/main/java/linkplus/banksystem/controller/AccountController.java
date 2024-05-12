package linkplus.banksystem.controller;

import linkplus.banksystem.models.Account;
import linkplus.banksystem.models.DTO.AccountDTO;
import linkplus.banksystem.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService accountService;


    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public List<Account> findAllAccounts(){
        return this.accountService.findAll();
    }

    @PostMapping("/add")
    public ResponseEntity<Account> add(@RequestBody AccountDTO accountDTO){
        return this.accountService.save(accountDTO)
                .map(account -> ResponseEntity.ok().body(account))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @GetMapping("/balance/{id}")
    public ResponseEntity<Double> getBalance(@PathVariable Long id){
        return ResponseEntity.ok(this.accountService.findById(id).get().getTotalBalance());

    }

}
