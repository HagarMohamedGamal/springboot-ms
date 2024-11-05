package com.notification;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Notification {
    @Id
    @SequenceGenerator(name = "notification_id_generator",
            sequenceName = "notification_id_generator")
    @GeneratedValue(generator = "notification_id_generator", strategy = GenerationType.SEQUENCE)
    private Integer id;

    private String content;
}
