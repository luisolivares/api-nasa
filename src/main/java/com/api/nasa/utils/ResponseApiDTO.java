package com.api.nasa.utils;

import com.api.nasa.handler.ApiError;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseApiDTO<T> {
    private T data;
    private ApiError error;
}
