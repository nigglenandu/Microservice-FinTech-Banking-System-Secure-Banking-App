package Niggle.Nandu.Fund.Transfer.Service.Service;

import feign.FeignException;
import Niggle.Nandu.Fund.Transfer.Service.Clients.AccountClients;
import Niggle.Nandu.Fund.Transfer.Service.Dto.AccountDto;
import Niggle.Nandu.Fund.Transfer.Service.Dto.AccountStatus;
import Niggle.Nandu.Fund.Transfer.Service.FundTransfer.FundTransferRequest;
import Niggle.Nandu.Fund.Transfer.Service.message.RabbitMQConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class  FundTransferService {
    private static final Logger log = LoggerFactory.getLogger(FundTransferService.class);

    private final AccountClients accountClient;

    public FundTransferService(AccountClients accountClients) {
        this.accountClient = accountClients;
    }

    public Optional<String> transferFunds(FundTransferRequest request) {
        try {
            if (request == null || request.getFromAccountNumber() == null || request.getToAccountNumber() == null) {
                log.error("Invalid FundTransferRequest: {}", request);
                return Optional.of("Transfer failed: Invalid request data");
            }
            log.info("Calling Account Service with account number: {}", request.getFromAccountNumber());
            AccountDto sender = accountClient.getAccountByNumber(request.getFromAccountNumber());
            if (sender == null) {
                log.error("Sender account not found: {}", request.getFromAccountNumber());
                return Optional.of("Transfer failed: Sender account not found");
            }
            log.info("Sender Account Details: {}", sender);
            AccountDto receiver = accountClient.getAccountByNumber(request.getToAccountNumber());
            if (receiver == null) {
                log.error("Receiver account not found: {}", request.getToAccountNumber());
                return Optional.of("Transfer failed: Receiver account not found");
            }
            log.info("Receiver Account Details: {}", receiver);

            if (sender.getStatus() != AccountStatus.ACTIVE) {
                log.warn("Sender account is not active: {}", sender);
                return Optional.of("Transfer failed: Sender account is not active");
            }
            if (receiver.getStatus() != AccountStatus.ACTIVE) {
                log.warn("Receiver account is not active: {}", receiver);
                return Optional.of("Transfer failed: Receiver account is not active");
            }
            log.info("Initiating fund transfer for request: {}", request);
            Optional<String> transferResult = accountClient.triggerFundTransfer(request);
            log.info("Transfer result: {}", transferResult);
            return transferResult;
        } catch (FeignException e) {
            log.error("Error calling Account Management Service: {}", e.getMessage(), e);
            return Optional.of("Transfer failed: Unable to process transfer");
        }
    }

    @RabbitListener(queues = RabbitMQConfig.FUND_TRANSFERS_QUEUE)
    public void processFundTransferMessage(String message) {
        log.info("Received fund transfer message: {}", message);
    }
}