package Niggle.Nandu.Loan.Transfer.Service.Clients;

import Niggle.Nandu.Loan.Transfer.Service.Dto.AccountDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "Account-Management-Service",
        url ="${Account-Management-Service.url}")
public interface AccountClient {
    @GetMapping("api/accounts/by-number/{accountNumber}")
    ResponseEntity<AccountDto> getAccountByNumber(@PathVariable String accountNumber);
}

