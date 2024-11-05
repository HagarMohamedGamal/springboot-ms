package com.notification;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

@Service
public record NotificationService(NotificationRepository notificationRepository) {
    public void saveNotification(NotificationDto notificationDto) {
        if (notificationDto != null && StringUtils.isNotBlank(notificationDto.notification()))
            notificationRepository.save(Notification.builder().content(notificationDto.notification()).build());
    }
}
