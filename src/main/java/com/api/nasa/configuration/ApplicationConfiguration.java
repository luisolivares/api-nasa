package com.api.nasa.configuration;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.Serializable;
import java.util.Collections;

@Configuration
public class ApplicationConfiguration implements Serializable {

    private final ConfigProperties configProperties;

    public ApplicationConfiguration(ConfigProperties configProperties) {
        this.configProperties = configProperties;
    }

    @Bean("nasaClient")
    public RestTemplate nasaClient() {
        return new RestTemplateBuilder()
                .rootUri(configProperties.getUrl())
                .build();
    }

    public HttpHeaders httpHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        return httpHeaders;
    }

    public UriComponentsBuilder buildUrl(String url, String endpoint) {
        return UriComponentsBuilder.fromHttpUrl(url)
                .path(endpoint);
    }

}
