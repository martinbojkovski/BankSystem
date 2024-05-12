package linkplus.banksystem.repository;

import linkplus.banksystem.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByOriginatingAccountIdOrResultingAccountId(Long id, Long sameId);
}
