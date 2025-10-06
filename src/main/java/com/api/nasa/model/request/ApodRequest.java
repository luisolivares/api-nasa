package com.api.nasa.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Parámetros de solicitud para obtener Astronomy Picture of the Day (APOD) desde la API de la NASA")
public class ApodRequest implements Serializable {

    @JsonProperty("date")
    @Schema(description = "Fecha específica (YYYY-MM-DD)", example = "2025-10-05", required = false)
    private String date;

    @JsonProperty("start_date")
    @Schema(description = "Fecha de inicio para rango de fechas (YYYY-MM-DD)", example = "2025-10-01", required = false)
    private String startDate;

    @JsonProperty("end_date")
    @Schema(description = "Fecha de fin para rango de fechas (YYYY-MM-DD)", example = "2025-10-05", required = false)
    private String endDate;

    @JsonProperty("count")
    @Schema(description = "Número de resultados aleatorios", example = "3", required = false)
    private Integer count;

    @JsonProperty("thumbs")
    @Schema(description = "Incluir miniaturas de videos (true/false)", example = "true", required = false)
    private Boolean thumbs;

    @JsonProperty("api_key")
    @Schema(description = "Clave de API de la NASA", example = "DEMO_KEY", required = true)
    private String apiKey;

}