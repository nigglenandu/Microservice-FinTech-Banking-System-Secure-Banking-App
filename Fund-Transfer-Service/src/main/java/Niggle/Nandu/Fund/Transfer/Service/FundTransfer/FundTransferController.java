package Niggle.Nandu.Fund.Transfer.Service.FundTransfer;

import Niggle.Nandu.Fund.Transfer.Service.Service.FundTransferService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/funds")
public class FundTransferController {
    @Autowired
    private FundTransferService fundTransferService;

    @PostMapping("/transfer")
    public ResponseEntity<String> transferFunds(@RequestBody @Valid FundTransferRequest request){
        return fundTransferService.transferFunds(request)
                .map(message -> new ResponseEntity<>(message, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>("Transfer failed", HttpStatus.BAD_REQUEST));
    }
}
