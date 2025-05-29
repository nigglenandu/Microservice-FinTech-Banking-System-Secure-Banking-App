package Niggle.Nandu.Fund.Transfer.Service.message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;

@Component
public class FundTransferConsumer {
    private static final Logger logger = LoggerFactory.getLogger(FundTransferConsumer.class);

    @RabbitListener(queues = "fund.transfers.queue")
    public void receiveMessage(String message) {
        logger.info("Received from RabbitMQ: {}", message);

        try {
            logger.info("Processing message: {}", message);

            logger.info("Message processed successfully.");

        } catch (HttpClientErrorException e) {
            logger.error("HTTP error while calling external API: {}", e.getMessage());
        } catch (RestClientException e) {
            logger.error("RestClientException while calling external API: {}", e.getMessage());
        } catch (Exception e) {
            logger.error("Unexpected error: {}", e.getMessage());
        }
    }
}
