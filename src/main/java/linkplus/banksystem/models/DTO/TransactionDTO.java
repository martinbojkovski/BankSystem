package linkplus.banksystem.models.DTO;

import linkplus.banksystem.models.TransactionType;
import lombok.Data;

@Data
public class TransactionDTO {
    private Double amount;
    private Long originatingAccountId;
    private Long resultingAccountId;
    private String reason;
    private TransactionType type;

    public TransactionDTO(Double amount, Long originatingAccountId, Long resultingAccountId, String reason, TransactionType type) {
        this.amount = amount;
        this.originatingAccountId = originatingAccountId;
        this.resultingAccountId = resultingAccountId;
        this.reason = reason;
        this.type = type;
    }

    public TransactionDTO() {
    }
}
