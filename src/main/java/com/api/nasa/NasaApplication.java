package com.api.nasa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@ConfigurationPropertiesScan("com.api.nasa.configuration")
public class NasaApplication {

    public static void main(String[] args) {
        SpringApplication.run(NasaApplication.class, args);
    }

    @Bean
    public RestTemplate getRestemplate() {
        return new RestTemplate();
    }

}
