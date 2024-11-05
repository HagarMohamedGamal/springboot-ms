package com.customer.rabbitmq;

import com.customer.YamlPropertySourceFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Slf4j
@PropertySource(value = "classpath:rabbitmq.yaml", factory = YamlPropertySourceFactory.class)
@Component
public class Receiver {

    @RabbitListener(queues = "customerQueue", id = "listener")
    public void receive(String data) {
        log.info("Rabbit sender object {} received", data);
    }
}
