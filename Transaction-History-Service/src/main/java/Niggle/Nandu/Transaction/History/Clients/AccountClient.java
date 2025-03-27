package Niggle.Nandu.Transaction.History.Clients;

import Niggle.Nandu.Transaction.History.Dto.AccountDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "Account-Management-Service", url = "http://Account-Management-Service/accounts/all")
public interface AccountClient {
    @GetMapping("/accounts/{id}")
    AccountDto getAccountById(@PathVariable("id") Long id);
}
