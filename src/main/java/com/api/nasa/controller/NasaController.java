package com.api.nasa.controller;

import com.api.nasa.model.request.ApodRequest;
import com.api.nasa.model.response.ApodResponse;
import com.api.nasa.service.ApodService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping(value = "/nasa")
@RestController()
public class NasaController {

    @Qualifier("apodService")
    private final ApodService apodService;

    @Autowired
    public NasaController(ApodService apodService){
        this.apodService = apodService;
    }

    @GetMapping(value = "/apod", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApodResponse> getApod(
            @RequestParam(value = "date", required = false) String date,
            @RequestParam(value = "start_date", required = false) String startDate,
            @RequestParam(value = "end_date", required = false) String endDate,
            @RequestParam(value = "count", required = false) Integer count,
            @RequestParam(value = "thumbs", required = false) Boolean thumbs,
            @RequestParam(value = "api_key", required = true) String apiKey
    ) {
        ApodRequest request = new ApodRequest(date, startDate, endDate, count, thumbs, apiKey);
        return apodService.getApod(request);
    }

}
