package com.customer.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@RabbitListener(queues = "rabbitmq.demoDLQueue", id = "dlListener")
@Component
public class DLReceiver {

    @RabbitListener
    public void receive(String data){
        log.info("Rabbit dl sender object {} received", data);
    }
}
