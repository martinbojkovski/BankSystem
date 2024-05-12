package linkplus.banksystem.controller;

import linkplus.banksystem.models.DTO.TransactionDTO;
import linkplus.banksystem.models.Transaction;
import linkplus.banksystem.service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService transactionService;


    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping
    public List<Transaction> findAllTransactions(){
        return this.transactionService.getAllTransactions();
    }

    @PostMapping("/add")
    public ResponseEntity<Transaction> add(@RequestBody TransactionDTO transactionDTO){
        return this.transactionService.doTransaction(transactionDTO)
                .map(transaction -> ResponseEntity.ok().body(transaction))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @GetMapping("/{id}")
    public List<Transaction> findTransactionForAccount(@PathVariable Long id){
        return this.transactionService.getTransactionsForAccount(id);

    }
}
