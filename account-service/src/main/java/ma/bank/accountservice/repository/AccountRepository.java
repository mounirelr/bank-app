package ma.bank.accountservice.repository;

import ma.bank.accountservice.entities.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository  extends JpaRepository<BankAccount , String> {
}
