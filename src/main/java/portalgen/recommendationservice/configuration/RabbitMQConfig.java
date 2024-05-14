package portalgen.recommendationservice.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    public static final String topicExchangeName = "user-preference-exchange";
    static final String queueName = "recommendation-queue";

    public static final String responseQueue = "recommendation-response-queue";

    @Bean
    public Queue requestQueue() {
        return new Queue(queueName, true);
    }

    @Bean
    public Queue responseQueue() {
        return new Queue(responseQueue, true);
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(topicExchangeName);
    }

    @Bean
    public Binding bindingRequest(TopicExchange exchange, Queue requestQueue) {
        return BindingBuilder.bind(requestQueue).to(exchange).with("requestRoutingKey");
    }

    @Bean
    public Binding bindingResponse(TopicExchange exchange, Queue responseQueue) {
        return BindingBuilder.bind(responseQueue).to(exchange).with("responseRoutingKey");
    }

    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setReplyAddress(responseQueue);
        rabbitTemplate.setReplyTimeout(10000);
        return rabbitTemplate;
    }

}
