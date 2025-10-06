package com.api.nasa.controller;

import com.api.nasa.utils.ResponseUtils;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping(value = "/api/v1/healthz")
@RequiredArgsConstructor
@Tag(
        name = "Estado Operativo.",
        description = "Clase controller que indica la disponibilidad y estado del API REST."
)
public class HealthzController {

    @Value("${spring.application.name}")
    private String nombre;

    @Value("${application-description}")
    private String descripcion;

    @Value("${application-version}")
    private String version;

    private final ResponseUtils responseUtils;

    @GetMapping(value = "", produces = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody
    ResponseEntity healtz() {
        HashMap<String, String> response = new HashMap<>();
        response.put("API", nombre);
        response.put("Descripción", descripcion);
        response.put("Project version", version);
        response.put("Java version", System.getProperty("java.version"));
        return responseUtils.formatOKResponse(response);
    }

}

