package Niggle.Nandu.Account.Management.Service.AccountManagement;

import Niggle.Nandu.Account.Management.Service.messaging.FundTransferProducer;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements IServiceAccount {

    @Autowired
    private FundTransferProducer fundTransferProducer;

    @Autowired
    private AccountRepository accountRepository;

    private static final String ACCOUNT_SERVICE = "accountServiceBreaker";

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Account addAccount(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public List<Account> getAllAccount() {
        return accountRepository.findAll();
    }

    @Override
    public Optional<Account> getAccountById(Long id) {
        return accountRepository.findById(id);
    }

    @Override
    public Optional<Account> getAccountByNumber(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber);
    }

    @Override
    public Optional<Account> updateAccountById(Long id, Account updatedAccount) {
        return accountRepository.findById(id).
                map(account -> {
                    account.setAccountNumber(updatedAccount.getAccountNumber());
                    account.setBalance(updatedAccount.getBalance());
                    account.setStatus(updatedAccount.getStatus());
                    return accountRepository.save(account);
                });
    }

    @Override
    public boolean deleteAccountById(Long id) {
        if(accountRepository.existsById(id)){
            accountRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    @Override
    @Transactional
    public Optional<String> transferFunds(FundTransferRequestDto request) {
        try {
            Account sender = accountRepository.findByAccountNumber(request.getFromAccountNumber())
                    .orElseThrow(() -> new RuntimeException("Sender account not found"));
            Account receiver = accountRepository.findByAccountNumber(request.getToAccountNumber())
                    .orElseThrow(() -> new RuntimeException("Receiver account not found"));

            if (sender.getBalance().compareTo(request.getAmount()) < 0) {
                return Optional.of("Transfer failed: Insufficient funds");
            }

            sender.setBalance(sender.getBalance().subtract(request.getAmount()));
            receiver.setBalance(receiver.getBalance().add(request.getAmount()));
            accountRepository.save(sender);
            accountRepository.save(receiver);

            return Optional.of("Transfer successful");
        } catch (Exception e) {
            return Optional.of("Transfer failed: " + e.getMessage());
        }
    }

    private String processInternalTransfer(FundTransferRequestDto request){
        return accountRepository.findByAccountNumber(request.getToAccountNumber())
                .map(receiver -> {
                    receiver.setBalance(receiver.getBalance().add(request.getAmount()));
                    accountRepository.save(receiver);
                    return "Transfer successful: Internal";
                }).orElse("failed: Receiver account not found");
    }

    @CircuitBreaker(name = ACCOUNT_SERVICE, fallbackMethod = "externalTransferFallback")
    @Retry(name = ACCOUNT_SERVICE)
    private Optional<String> processExternalTransfer(FundTransferRequestDto request) {
        String externalAPiUrl = "http://localhost:8081/api/funds/transfer";
        try {
            ExternalTransferRequest externalRequest = new ExternalTransferRequest(
                    request.getAmount(),
                    request.getFromAccountNumber(),
                    request.getToAccountNumber()
            );

//          RestTemplate restTemplate = new RestTemplate();
            HttpEntity<ExternalTransferRequest> httpEntity = new HttpEntity<>(externalRequest);
            //ResponseEntity<ExternalTransferResponse> response = restTemplate.postForEntity(externalAPiUrl, httpEntity, ExternalTransferResponse.class);
            ResponseEntity<String> response = restTemplate.postForEntity(externalAPiUrl, httpEntity, String.class);

            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null && response.getBody().toLowerCase().contains("success")) {
                return Optional.of("success");
            } else {
                return Optional.of("Failed");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.of("Failed");
        }
    }
        private Optional<String> externalTransferFallback(FundTransferRequestDto request, Throwable throwable){
            System.out.println("Fallback executed because: "+ throwable.getMessage());
            return Optional.of("Failed: External service is down");
        }
}