package com.legolas.cvpasrser.utils;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Data
@Configuration
@ConfigurationProperties(prefix = "edenai")
@NoArgsConstructor
public class EdenAIProperties {
    private String url;

    private String token;

    private List<String> providers;
}
