package org.example.notificationservice.config;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ConfigurationProperties(prefix = "global.params")
public class GlobalConfig {
    private Integer g1;
    private Integer g2;
}
