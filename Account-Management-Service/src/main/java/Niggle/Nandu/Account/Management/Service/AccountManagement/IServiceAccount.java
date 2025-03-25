package Niggle.Nandu.Account.Management.Service.AccountManagement;

import java.util.List;
import java.util.Optional;

public interface IServiceAccount {
    Account addAccount(Account account);
    List<Account> getAllAccount();
    Optional<Account> getAccountById(Long id);
    Optional<Account> updateAccountById(Long id, Account account);
    boolean deleteAccountById(Long id);
    Optional<String> transferFunds(FundTransferRequestDto request);

    Optional<Account> getAccountByNumber(String accountNumber);
}
