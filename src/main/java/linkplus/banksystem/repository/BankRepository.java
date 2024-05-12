package linkplus.banksystem.repository;


import linkplus.banksystem.models.Bank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankRepository extends JpaRepository<Bank,Long> {
}
