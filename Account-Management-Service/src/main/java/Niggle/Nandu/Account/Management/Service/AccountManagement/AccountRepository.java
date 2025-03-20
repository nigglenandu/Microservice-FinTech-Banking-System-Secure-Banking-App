package Niggle.Nandu.Account.Management.Service.AccountManagement;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByAccountNumber(String AccountNumber);
}
