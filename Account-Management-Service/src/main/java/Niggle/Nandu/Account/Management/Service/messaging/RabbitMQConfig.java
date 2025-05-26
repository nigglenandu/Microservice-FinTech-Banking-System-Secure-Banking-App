package Niggle.Nandu.Account.Management.Service.messaging;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    public static final String EXCHANGE_NAME = "niggle.exchange";
    public static final String FUND_TRANSFERS_QUEUE = "fund.transfers.queue";
    public static final String LOAN_NOTIFICATIONS_QUEUE = "loan.notifications.queue";
    public static final String FUND_TRANSFERS_ROUTING_KEY = "fund.transfers";
    public static final String LOAN_NOTIFICATIONS_ROUTING_KEY = "loan.notifications";

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }

    @Bean
    public Queue fundTransfersQueue() {
        return new Queue(FUND_TRANSFERS_QUEUE, true);
    }

    @Bean
    public Queue loanNotificationsQueue() {
        return new Queue(LOAN_NOTIFICATIONS_QUEUE, true);
    }

    @Bean
    public Binding fundTransfersBinding(Queue fundTransfersQueue, TopicExchange exchange) {
        return BindingBuilder.bind(fundTransfersQueue)
                .to(exchange)
                .with(FUND_TRANSFERS_ROUTING_KEY);
    }

    @Bean
    public Binding loanNotificationsBinding(Queue loanNotificationsQueue, TopicExchange exchange) {
        return BindingBuilder.bind(loanNotificationsQueue)
                .to(exchange)
                .with(LOAN_NOTIFICATIONS_ROUTING_KEY);
    }
}