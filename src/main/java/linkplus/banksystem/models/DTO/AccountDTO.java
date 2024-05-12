package linkplus.banksystem.models.DTO;

import lombok.Data;

@Data
public class AccountDTO {
    private String username;
    private Double totalBalance;
    private Long bank;

    public AccountDTO(String username, Double totalBalance, Long bank) {
        this.username = username;
        this.totalBalance = totalBalance;
        this.bank = bank;
    }

    public AccountDTO() {
    }
}
