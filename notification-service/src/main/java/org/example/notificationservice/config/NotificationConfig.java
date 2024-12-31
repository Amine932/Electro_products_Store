package org.example.notificationservice.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data @AllArgsConstructor @NoArgsConstructor @Builder
@ConfigurationProperties(prefix = "notifications.params")
public class NotificationConfig {
    private Integer n1;
    private Integer n2;

}
