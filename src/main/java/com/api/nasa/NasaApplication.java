package com.api.nasa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@ConfigurationPropertiesScan("com.api.nasa.configuration")
@SpringBootApplication
public class NasaApplication {

    public static void main(String[] args) {
        SpringApplication.run(NasaApplication.class, args);
    }

    @Bean
    public RestTemplate getRestemplate() {
        return new RestTemplate();
    }

}
