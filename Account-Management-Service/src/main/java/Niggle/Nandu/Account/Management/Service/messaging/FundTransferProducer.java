package Niggle.Nandu.Account.Management.Service.messaging;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FundTransferProducer {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendTransferMessage(String message){
        rabbitTemplate.convertAndSend(
                RabbitMQConfig.EXCHANGE_NAME,
                RabbitMQConfig.FUND_TRANSFERS_ROUTING_KEY,
                message
        );
        System.out.println("Send fund transfer message to RabbitMQ: " + message);
    }
}
