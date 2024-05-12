package linkplus.banksystem.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;
    private Double amount;
    private Long originatingAccountId;
    private Long resultingAccountId;
    private String reason;
    private TransactionType type;

    public Transaction(Double amount, Long originatingAccountId, Long resultingAccountId, String reason, TransactionType type) {
        this.amount = amount;
        this.originatingAccountId = originatingAccountId;
        this.resultingAccountId = resultingAccountId;
        this.reason = reason;
        this.type = type;
    }


    public Transaction() {

    }
}
