package Niggle.Nandu.Loan.Transfer.Service.Messaging;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    public static final String EXCHANGE_NAME = "niggle.exchange";
    public static final String LOAN_NOTIFICATIONS_QUEUE = "loan.notifications.queue";
    public static final String FUND_TRANSFERS_QUEUE = "fund.transfers.queue";
    public static final String LOAN_NOTIFICATIONS_ROUTING_KEY = "loan.notifications";
    public static final String FUND_TRANSFERS_ROUTING_KEY = "fund-transfers";

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }

    @Bean
    public Queue loanNotificationsQueue(){
        return new Queue(LOAN_NOTIFICATIONS_QUEUE, true);
    }

    @Bean
    public Queue fundTransfersQueue(){
        return new Queue(FUND_TRANSFERS_QUEUE, true);
    }

    @Bean
    public Binding LoanNotificationsBinding(Queue loanNotificationsQueue, TopicExchange exchange){
        return BindingBuilder.bind(loanNotificationsQueue)
                .to(exchange)
                .with(LOAN_NOTIFICATIONS_ROUTING_KEY);
    }

    @Bean
    public Binding fundTransfersBinding(Queue fundTransfersQueue, TopicExchange exchange){
        return BindingBuilder.bind(fundTransfersQueue)
                .to(exchange)
                .with(FUND_TRANSFERS_ROUTING_KEY);
    }
}
