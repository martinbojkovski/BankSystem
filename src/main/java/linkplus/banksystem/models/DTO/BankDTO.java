package linkplus.banksystem.models.DTO;

import jakarta.persistence.OneToMany;
import linkplus.banksystem.models.Account;
import lombok.Data;

import java.util.List;

@Data
public class BankDTO {
    private String name;

    private List<Account> bankCustomers;

    private Double totalTransactionFeeAmount;

    private Double totalTransferAmount;

    private Integer FlatFeeAmount;

    private Integer PercentFeeAmount;

    public BankDTO(String name, List<Account> bankCustomers, Double totalTransactionFeeAmount, Double totalTransferAmount, Integer flatFeeAmount, Integer percentFeeAmount) {
        this.name = name;
        this.bankCustomers = bankCustomers;
        this.totalTransactionFeeAmount = totalTransactionFeeAmount;
        this.totalTransferAmount = totalTransferAmount;
        FlatFeeAmount = flatFeeAmount;
        PercentFeeAmount = percentFeeAmount;
    }

    public BankDTO() {
    }
}
