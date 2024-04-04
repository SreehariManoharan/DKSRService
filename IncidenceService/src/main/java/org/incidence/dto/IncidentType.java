package org.incidence.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.io.IOException;

public enum IncidentType {
    FEATURE;

    @JsonValue
    public String toValue() {
        switch (this) {
            case FEATURE: return "Feature";
        }
        return null;
    }

    @JsonCreator
    public static IncidentType forValue(String value) throws IOException {
        if (value.equals("Feature")) return FEATURE;
        throw new IOException("Cannot deserialize IncidentType");
    }
}
