package org.example.notificationservice.web;

import io.micrometer.observation.annotation.Observed;
import org.example.notificationservice.Repository.NotificationRepository;
import org.example.notificationservice.entity.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/api")
@Component
@CrossOrigin(origins = "http://localhost:4200")

public class NotificationWeb {
    @Autowired
    NotificationRepository notificationRepository;
    @GetMapping("/notifications")
    public List<Notification> getNotifications(){
      return notificationRepository.findAll();
    }
    @GetMapping("/last-notification")
    public Notification getLastNotification() {
        List<Notification> allNotifications = getNotifications();
        int size = allNotifications.size();

        return size > 0 ? allNotifications.get(size - 1) : null;
    }
}
