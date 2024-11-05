package com.notification.rabbitmq;

import com.notification.YamlPropertySourceFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@EnableRabbit
@PropertySource(value = "classpath:rabbitmq.yaml", factory = YamlPropertySourceFactory.class)
@Configuration
public class RabbitMqConfiguration {
    @Value("${rabbitmq.queue}")
    public String queueName;
    @Value("${rabbitmq.exchange}")
    public String exchangeName;
    @Value("${rabbitmq.routingkey}")
    public String routingkey;

    @Bean
    public Queue queue() {
        return new Queue(queueName, false);
    }

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(exchangeName);
    }

    @Bean
    public Binding binding(Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(routingkey);
    }
}
