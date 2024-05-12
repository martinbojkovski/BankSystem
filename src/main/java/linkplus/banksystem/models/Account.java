package linkplus.banksystem.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

@Data
@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String username;
    private Double totalBalance;
    @ManyToOne
    @JsonIgnore
    private Bank bank;

    public Account(String username, Double totalBalance, Bank bank) {
        this.username = username;
        this.totalBalance = totalBalance;
        this.bank = bank;
    }

    public Account() {

    }
}
