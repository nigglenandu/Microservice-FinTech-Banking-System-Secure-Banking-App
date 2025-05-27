package Niggle.Nandu.Loan.Transfer.Service.Clients;

import Niggle.Nandu.Loan.Transfer.Service.Dto.FundTransferRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "Fund-Transfer-Service", url = "${Fund-Transfer-Service.url}")
public interface FundTransferClient {
    @PostMapping("/api/funds/transfer")
    ResponseEntity<String> transferFunds(@RequestBody FundTransferRequestDto request);
}
