package com.api.nasa.service.impl;

import com.api.nasa.configuration.ApplicationConfiguration;
import com.api.nasa.configuration.ConfigProperties;
import com.api.nasa.model.request.ApodRequest;
import com.api.nasa.model.response.ApodResponse;
import com.api.nasa.service.ApodService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Log4j2
@Service("apodService")
public class ApodServiceImpl implements ApodService {

    private final RestTemplate restTemplate;
    private final ApplicationConfiguration applicationConfiguration;
    private final ConfigProperties configProperties;

    private static final String CIRCUIT_BREAKER_NAME = "nasaApi";

    @Autowired
    public ApodServiceImpl(ApplicationConfiguration applicationConfiguration, ConfigProperties configProperties) {
        this.restTemplate = applicationConfiguration.nasaClient();
        this.configProperties = configProperties;
        this.applicationConfiguration = applicationConfiguration;
    }

    @Override
    @CircuitBreaker(name = CIRCUIT_BREAKER_NAME, fallbackMethod = "fallbackNasaResponse")
    public ResponseEntity<ApodResponse> getApod(ApodRequest apodRequest) {
        Map<String, Object> params = new HashMap<>();
        params.put("api_key", apodRequest.getApiKey());
        params.put("count", apodRequest.getCount());
        params.put("date", apodRequest.getDate());
        params.put("start_date", apodRequest.getStartDate());
        params.put("end_date", apodRequest.getEndDate());
        params.put("thumbs", apodRequest.getThumbs());
        HttpHeaders httpHeaders = applicationConfiguration.httpHeaders();
        UriComponentsBuilder endPoint = applicationConfiguration.buildUrl(configProperties.getUrl(), configProperties.getEndPointPlanetaryApod());
        params.entrySet().stream().forEach(k -> {
            if (null != k.getValue()) {
                endPoint.queryParam(k.getKey(), k.getValue());
            }
        });
        ResponseEntity<ApodResponse> response = restTemplate.getForEntity(endPoint.toUriString(), ApodResponse.class, httpHeaders);
        return Optional.ofNullable(response)
                .map(result -> ResponseEntity.status(response.getStatusCode()).headers(httpHeaders).body(response.getBody()))
                .orElseGet(() -> new ResponseEntity<ApodResponse>(HttpStatus.NOT_FOUND));
    }

    // Método fallback cuando la API no responde o lanza error
    public String fallbackNasaResponse(Throwable throwable) {
        return """
                {
                  "title": "Servicio NASA no disponible temporalmente",
                  "explanation": "⚠️ No se pudo conectar con la API externa de la NASA.",
                  "error": "%s"
                }
                """.formatted(throwable.getMessage());
    }

}