package com.api.nasa.service;

import com.api.nasa.model.request.ApodRequest;
import com.api.nasa.model.response.ApodResponse;
import org.springframework.http.ResponseEntity;

import java.io.Serializable;

public interface ApodService extends Serializable {

    ResponseEntity<ApodResponse> getApod(ApodRequest apodRequest);

}
