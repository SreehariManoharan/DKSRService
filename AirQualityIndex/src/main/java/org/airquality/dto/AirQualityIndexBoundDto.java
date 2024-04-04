package org.airquality.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Represents an Air Quality Index Bound DTO.
 *
 */
public class AirQualityIndexBoundDto {
    @JsonProperty(value = "status")
    private String status;
    @JsonProperty(value = "data")
    private List<DataItem> data;
}
