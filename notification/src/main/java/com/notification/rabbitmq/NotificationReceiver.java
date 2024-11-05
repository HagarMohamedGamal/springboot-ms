package com.notification.rabbitmq;

import com.notification.NotificationDto;
import com.notification.NotificationService;
import com.notification.YamlPropertySourceFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Slf4j
@PropertySource(value = "classpath:rabbitmq.yaml", factory = YamlPropertySourceFactory.class)
@Component
public record NotificationReceiver(NotificationService notificationService) {

    @RabbitListener(queues = "customerQueue", id = "listener")
    public void receive(String data) {
        log.info("Rabbit sender object '{}' received", data);
        notificationService.saveNotification(new NotificationDto(data));
    }
}
