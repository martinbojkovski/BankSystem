package linkplus.banksystem.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Bank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bankId;
    private String name;
    @OneToMany(mappedBy = "bank", cascade = CascadeType.ALL)
    private List<Account> bankCustomers;
    private Double totalTransactionFeeAmount;
    private Double totalTransferAmount;
    private Integer FlatFeeAmount;
    private Integer PercentFeeAmount;

    public Bank(String name, List<Account> bankCustomers, Double totalTransactionFeeAmount, Double totalTransferAmount, Integer flatFeeAmount, Integer percentFeeAmount) {
        this.name = name;
        this.bankCustomers = bankCustomers;
        this.totalTransactionFeeAmount = totalTransactionFeeAmount;
        this.totalTransferAmount = totalTransferAmount;
        FlatFeeAmount = flatFeeAmount;
        PercentFeeAmount = percentFeeAmount;
    }

    public Bank() {

    }
}
