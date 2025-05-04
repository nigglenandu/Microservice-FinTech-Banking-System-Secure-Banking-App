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

    @RabbitListener(queues = "fund_transfer_queue")
    public void receiveMessage(String message) {
        logger.info("Received from RabbitMQ: {}", message);

        try {
            // Here, add the actual message processing logic
            logger.info("Processing message: {}", message);

            // Example: Calling an external API, updating the database, etc.
            // Simulate processing here
            // Example: restTemplate.getForObject("external-api-url", ResponseType.class);

            // Simulate success
            logger.info("Message processed successfully.");

        } catch (HttpClientErrorException e) {
            logger.error("HTTP error while calling external API: {}", e.getMessage());
            // Handle the error (e.g., retry, dead-letter queue, etc.)
        } catch (RestClientException e) {
            logger.error("RestClientException while calling external API: {}", e.getMessage());
            // Handle the error (e.g., retry, dead-letter queue, etc.)
        } catch (Exception e) {
            logger.error("Unexpected error: {}", e.getMessage());
            // Handle the error (e.g., logging, requeueing, etc.)
        }
    }
}
