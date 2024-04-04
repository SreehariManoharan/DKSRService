package org.airquality.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DataItem {
    @JsonProperty(value = "lat")
    private double lat;
    @JsonProperty(value = "lon")
    private double lon;
    @JsonProperty(value = "uid")
    private int uid;
    @JsonProperty(value = "aqi")
    private String aqi;
    @JsonProperty(value = "station")
    private Station station;
}
