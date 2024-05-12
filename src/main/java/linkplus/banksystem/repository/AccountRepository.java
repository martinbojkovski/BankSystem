package linkplus.banksystem.repository;

import linkplus.banksystem.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account,Long> {
}