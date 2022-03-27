package com.api.nasa.controller;

import com.api.nasa.configuration.ApplicationConfiguration;
import com.api.nasa.configuration.ConfigProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.io.Serializable;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class NasaControllerTest implements Serializable {

    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;
    private final ApplicationConfiguration applicationConfiguration;
    private final ConfigProperties configProperties;

    @Autowired
    public NasaControllerTest(ApplicationConfiguration applicationConfiguration, ConfigProperties configProperties, MockMvc mockMvc, ObjectMapper objectMapper) {
        this.applicationConfiguration = applicationConfiguration;
        this.configProperties = configProperties;
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
    }

    @Test
    public void getApod() throws Exception {
        mockMvc.perform(get("/nasa/apod")
                        .queryParam("api_key", configProperties.getApiKey())
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

}
