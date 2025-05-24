package Niggle.Nandu.Loan.Transfer.Service.Messaging;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoanNotificationProducer {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendNotification(String message){
        rabbitTemplate.convertAndSend(
                RabbitMQConfig.EXCHANGE_NAME,
                RabbitMQConfig.LOAN_NOTIFICATIONS_ROUTING_KEY,
                message
        );

        System.out.println("Sent notification to RabbitMQ: " + message);
    }
}
