package com.example.clientservice.Config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "client.params")

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientConfig {

    Integer x;
    Integer y;





}
