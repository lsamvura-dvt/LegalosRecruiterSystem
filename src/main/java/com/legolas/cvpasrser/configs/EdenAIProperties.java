package com.legolas.cvpasrser.configs;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@Data
@ConfigurationProperties(prefix = "edenai")
public class EdenAIProperties {
    private String url;

    private String token;

    private List<String> providers;
}
