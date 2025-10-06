package com.api.nasa.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Respuesta de la API de la NASA para Astronomy Picture of the Day (APOD)")
public class ApodResponse implements Serializable {

    @JsonProperty("date")
    @Schema(description = "Fecha de la imagen o video (YYYY-MM-DD)", example = "2025-10-05")
    private String date;

    @JsonProperty("explanation")
    @Schema(description = "Descripción detallada de la imagen o video",
            example = "Una vista espectacular de la Vía Láctea desde el Observatorio Paranal...")
    private String explanation;

    @JsonProperty("hdurl")
    @Schema(description = "URL de la imagen en alta resolución",
            example = "https://apod.nasa.gov/apod/image/2510/milkyway_hd.jpg")
    private String hdurl;

    @JsonProperty("media_type")
    @Schema(description = "Tipo de contenido (image/video)", example = "image")
    private String mediaType;

    @JsonProperty("service_version")
    @Schema(description = "Versión del servicio de la NASA", example = "v1")
    private String serviceVersion;

    @JsonProperty("title")
    @Schema(description = "Título de la imagen o video", example = "La Vía Láctea desde Chile")
    private String title;

    @JsonProperty("url")
    @Schema(description = "URL de la imagen o video de tamaño estándar",
            example = "https://apod.nasa.gov/apod/image/2510/milkyway_chile.jpg")
    private String url;
}