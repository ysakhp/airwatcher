package ust.airwatcher.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "air.pollution")
@Data
public class ConfigProperties {

        private String apiKey;
        private String baseUrl;

}
