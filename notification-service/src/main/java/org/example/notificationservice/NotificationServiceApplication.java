package org.example.notificationservice;

import org.apache.kafka.common.protocol.types.Field;
import org.example.notificationservice.Repository.NotificationRepository;
import org.example.notificationservice.config.GlobalConfig;
import org.example.notificationservice.config.NotificationConfig;
import org.example.notificationservice.entity.Notification;
import org.example.notificationservice.event.SaleEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.kafka.annotation.KafkaListener;

import java.util.UUID;

@SpringBootApplication
@EnableConfigurationProperties({GlobalConfig.class, NotificationConfig.class})

public class NotificationServiceApplication {


    public static void main(String[] args) {
        SpringApplication.run(NotificationServiceApplication.class, args);
    }

    @Autowired
    NotificationRepository notificationRepository;

    @KafkaListener(topics = "notificationTopic", groupId = "notificationId")
    public void handelNotification(SaleEvent saleEvent){
        System.out.println("New sale added , sale Id" + " " + saleEvent.getSaleId()+ " " + saleEvent.getClientId() + " " + saleEvent.getProductId() );
        Notification notification = Notification.builder().clientId(saleEvent.getClientId()).description("New sale added").productId(saleEvent.getProductId()).build();
        notificationRepository.save(notification);
    }
}
