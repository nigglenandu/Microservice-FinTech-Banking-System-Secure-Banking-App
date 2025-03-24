package Niggle.Nandu.Fund.Transfer.Service.Service;

import Niggle.Nandu.Fund.Transfer.Service.Clients.AccountClients;
import Niggle.Nandu.Fund.Transfer.Service.Dto.AccountDto;
import Niggle.Nandu.Fund.Transfer.Service.Dto.AccountStatus;
import Niggle.Nandu.Fund.Transfer.Service.FundTransfer.FundTransferRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FundTransferService {

    private final AccountClients accountClient;

    public FundTransferService(AccountClients accountClients) {
        this.accountClient = accountClients;
    }

    public Optional<String> transferFunds(FundTransferRequest request){
        AccountDto sender = accountClient.getAccountByNumber(request.getFromAccountNumber());
        AccountDto receiver = accountClient.getAccountById(request.getToAccountId());

        if(sender.getStatus() != AccountStatus.ACTIVE){
            return Optional.of("Transfer failed: Sender account is not active");
        }
        if(receiver.getStatus() != AccountStatus.ACTIVE){
            return Optional.of("Transfer failed: Receiver account is not active");
        }

        return Optional.of("Transfer successful!");
    }
}
