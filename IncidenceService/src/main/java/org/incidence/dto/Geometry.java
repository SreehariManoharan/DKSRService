package org.incidence.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class Geometry {
    private GeometryType type;
    private List<Coordinate> coordinates;

    @JsonProperty("type")
    public GeometryType getType() { return type; }
    @JsonProperty("type")
    public void setType(GeometryType value) { this.type = value; }

    @JsonProperty("coordinates")
    public List<Coordinate> getCoordinates() { return coordinates; }
    @JsonProperty("coordinates")
    public void setCoordinates(List<Coordinate> value) { this.coordinates = value; }
}
