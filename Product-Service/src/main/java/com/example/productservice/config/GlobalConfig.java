package com.example.productservice.config;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;


@ConfigurationProperties(prefix = "global.params")

@Data
@NoArgsConstructor
@AllArgsConstructor

public class GlobalConfig
{

    Integer p1;
    Integer p2;

/*@GetMapping("/params")
    public Map<String,Integer> params()
    {
        return Map.of("p1",p1,"p2",p2);
    }*/

}
