package com.api.nasa.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApodRequest implements Serializable {

    @JsonProperty("date")
    private String date;
    @JsonProperty("start_date")
    private String startDate;
    @JsonProperty("end_date")
    private String endDate;
    @JsonProperty("count")
    private Integer count;
    @JsonProperty("thumbs")
    private Boolean thumbs;
    @JsonProperty("api_key")
    private String apiKey;

}
