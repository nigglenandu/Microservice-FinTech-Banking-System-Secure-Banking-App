package Niggle.Nandu.Fund.Transfer.Service.Clients;

import Niggle.Nandu.Fund.Transfer.Service.Dto.AccountDto;
import Niggle.Nandu.Fund.Transfer.Service.FundTransfer.FundTransferRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@FeignClient(name = "Account-Management-Service",
        url ="${Account-Management-Service.url}")
public interface AccountClients {
    @GetMapping("api/accounts/by-number/{accountNumber}")
    AccountDto getAccountByNumber(@PathVariable String accountNumber);

    @PostMapping("api/accounts/funds/transfer")
    Optional<String> triggerFundTransfer(@RequestBody FundTransferRequest request);

    @GetMapping("api/accounts/{accountId}")
    AccountDto getAccountById(@PathVariable String accountId);
}
