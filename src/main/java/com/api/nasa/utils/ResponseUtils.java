package com.api.nasa.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ResponseUtils {

    public static final String STATUS = "status";
    public static final String OK = "OK";
    public static final String SUCCESS_FALSE = "FALSE";
    public static final String TYPE_STATUS_ERROR = "error";


    public ResponseEntity formatOKResponse(Object responseObject) {
        return new ResponseEntity(responseApiDTO(responseObject), HttpStatus.OK);
    }

    private ResponseApiDTO responseApiDTO(Object responseObject) {
        return ResponseApiDTO
                .builder()
                .data(responseObject)
                .error(null)
                .build();
    }

    /*

    public ResponseEntity<Response> formatExceptionResponse(Exception exception, HttpStatus httpStatus) {
        Response body = Response.ResponseFail(exception.getMessage(), String.valueOf(httpStatus.value()));
        body.getStatus().setAppId(appId);
        return new ResponseEntity<>(body, httpStatus);
    }


    public ResponseEntity<Response> ResponseFailWithData(Object data, String mensaje, String code, HttpStatus httpStatus) {
        Response body = new Response(data, mensaje, TYPE_STATUS_ERROR, SUCCESS_FALSE, code);
        body.getStatus().setAppId(appId);
        return new ResponseEntity<>(body, httpStatus);

    }

*/

}
