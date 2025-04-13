package Niggle.Nandu.Fund.Transfer.Service.Clients;

import Niggle.Nandu.Fund.Transfer.Service.Dto.AccountDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "Account-Management-Service",
        url ="${Account-Management-Service.url}")
public interface AccountClients {
    @GetMapping("api/accounts/by-number/{accountNumber}")
    AccountDto getAccountByNumber(@PathVariable String accountNumber);

    @GetMapping("api/accounts/{accountId}")
    AccountDto getAccountById(@PathVariable String accountId);
}
