package org.incidence.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.io.IOException;

public enum GeometryType {
    LINE_STRING, POINT;

    @JsonValue
    public String toValue() {
        return switch (this) {
            case LINE_STRING -> "LineString";
            case POINT -> "Point";
        };
    }

    @JsonCreator
    public static GeometryType forValue(String value) throws IOException {
        if (value.equals("LineString")) return LINE_STRING;
        if (value.equals("Point")) return POINT;
        throw new IOException("Cannot deserialize GeometryType");
    }
}
