package com.customer.rabbitmq;

import com.customer.YamlPropertySourceFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

@Service
@PropertySource(value = "classpath:rabbitmq.yaml", factory = YamlPropertySourceFactory.class)
public class Sender {
    @Value("${rabbitmq.exchange}")
    private String exchange;
    @Value("${rabbitmq.routingkey}")
    private String routingkey;

    private final RabbitTemplate rabbitTemplate;

    public Sender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void send(Object data) {
        this.rabbitTemplate.convertAndSend(exchange, routingkey, data);
    }
}
