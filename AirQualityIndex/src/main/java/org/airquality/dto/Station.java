package org.airquality.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Station {
    @JsonProperty(value = "name")
    private String name;
    @JsonProperty(value = "time")
    private String time;
}
