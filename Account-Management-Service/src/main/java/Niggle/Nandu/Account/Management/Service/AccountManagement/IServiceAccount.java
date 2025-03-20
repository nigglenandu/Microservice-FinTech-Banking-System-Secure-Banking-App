package Niggle.Nandu.Account.Management.Service.AccountManagement;

import com.NiggleNandu.FinTech.Banking.System.Secure.Banking.App.FundTransfer.FundTransferRequest;

import java.util.List;
import java.util.Optional;

public interface IServiceAccount {
    Account addAccount(Account account);
    List<Account> getAllAccount();
    Optional<Account> getAccountById(Long id);
    Optional<Account> updateAccountById(Long id, Account account);
    boolean deleteAccountById(Long id);
    Optional<String> transferFunds(FundTransferRequest request);
}
