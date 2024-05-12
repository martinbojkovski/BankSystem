package linkplus.banksystem.controller;

import linkplus.banksystem.models.Bank;
import linkplus.banksystem.models.DTO.BankDTO;
import linkplus.banksystem.service.BankService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/banks")
public class BankController {
    private final BankService bankService;

    public BankController(BankService bankService) {
        this.bankService = bankService;
    }

    @GetMapping
    public List<Bank> findAllBanks(){
        return this.bankService.findAllBanks();
    }

    @PostMapping("/add")
    public ResponseEntity<Bank> add(@RequestBody BankDTO bankDTO){
        return this.bankService.save(bankDTO)
                .map(bank -> ResponseEntity.ok().body(bank))
                .orElseGet(() -> ResponseEntity.badRequest().build());

    }

    @GetMapping("/transaction_fees/{id}")
    public ResponseEntity<Double> getBankTransactionFeeAmount(@PathVariable Long id){
        return ResponseEntity.ok(this.bankService.findById(id).get().getTotalTransactionFeeAmount());

    }

    @GetMapping("/transfer_amount/{id}")
    public ResponseEntity<Double> getBankTotalTransferAmount(@PathVariable Long id){
        return ResponseEntity.ok(this.bankService.findById(id).get().getTotalTransferAmount());

    }


}
