package com.api.nasa.controller;

import com.api.nasa.model.request.ApodRequest;
import com.api.nasa.model.response.ApodResponse;
import com.api.nasa.service.ApodService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequestMapping(value = "/api/v1/nasa")
@RequiredArgsConstructor
@Tag(
        name = "NASA",
        description = "Clase controller que se conecta a los endpont que dispone las API de la NASA."
)
public class NasaController {

    @Qualifier("apodService")
    private final ApodService apodService;

    /**
     * Endpoint para obtener la Astronomy Picture of the Day (APOD) desde la API de la NASA.
     * Permite parámetros opcionales para filtrar por fecha, rango, cantidad de resultados y miniaturas.
     *
     * @param date      Fecha específica (YYYY-MM-DD)
     * @param startDate Fecha de inicio (YYYY-MM-DD) para rango de fechas
     * @param endDate   Fecha de fin (YYYY-MM-DD) para rango de fechas
     * @param count     Número de resultados aleatorios
     * @param thumbs    Incluir miniaturas de videos (true/false)
     * @param apiKey    Clave de API de la NASA (obligatoria)
     * @return ApodResponse con la información de la imagen astronómica
     */
    @Operation(
            summary = "Obtener Astronomy Picture of the Day (APOD)",
            description = "Obtiene la imagen o video astronómico del día desde la API pública de la NASA. " +
                    "Se pueden aplicar filtros opcionales como fecha, rango de fechas, cantidad de resultados y miniaturas.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Respuesta exitosa con los datos de APOD",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ApodResponse.class))
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Solicitud inválida",
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Error interno del servidor",
                            content = @Content
                    )
            }
    )
    @GetMapping(value = "/apod", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApodResponse> getApod(
            @Parameter(description = "Fecha específica (YYYY-MM-DD)", required = false)
            @RequestParam(value = "date", required = false) String date,

            @Parameter(description = "Fecha de inicio para rango (YYYY-MM-DD)", required = false)
            @RequestParam(value = "start_date", required = false) String startDate,

            @Parameter(description = "Fecha de fin para rango (YYYY-MM-DD)", required = false)
            @RequestParam(value = "end_date", required = false) String endDate,

            @Parameter(description = "Número de resultados aleatorios", required = false)
            @RequestParam(value = "count", required = false) Integer count,

            @Parameter(description = "Incluir miniaturas de videos (true/false)", required = false)
            @RequestParam(value = "thumbs", required = false) Boolean thumbs,

            @Parameter(description = "Clave de API de la NASA", required = true)
            @RequestParam(value = "api_key", required = true) String apiKey
    ) {
        ApodRequest request = new ApodRequest(date, startDate, endDate, count, thumbs, apiKey);
        return apodService.getApod(request);
    }
}