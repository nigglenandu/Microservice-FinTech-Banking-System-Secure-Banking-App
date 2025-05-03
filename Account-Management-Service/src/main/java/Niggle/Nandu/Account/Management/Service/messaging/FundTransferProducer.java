package Niggle.Nandu.Account.Management.Service.messaging;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FundTransferProducer {
    @Autowired
    public RabbitTemplate rabbitTemplate;

    public void sendTransferMessage(String message){
        rabbitTemplate.convertAndSend(
                RabbitMQConfiguration.EXCHANGE,
                RabbitMQConfiguration.ROUTING_KEY,
                message
        );
    }
}
