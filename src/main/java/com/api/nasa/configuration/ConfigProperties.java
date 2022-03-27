package com.api.nasa.configuration;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.Serializable;

@ConfigurationProperties(prefix = "nasa")
@Data
public class ConfigProperties implements Serializable {

    private String url;
    @Value("${nasa.url.planetary.apod}")
    private String endPointPlanetaryApod;
    @Value("${nasa.api.key}")
    private String apiKey;

}
