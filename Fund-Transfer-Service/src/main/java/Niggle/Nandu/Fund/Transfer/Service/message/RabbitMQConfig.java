package Niggle.Nandu.Fund.Transfer.Service.message;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    public static final String QUEUE = "fund_transfer_queue";

    @Bean
    public Queue queue(){
        return new Queue(QUEUE);
    }
}
