package org.incidence.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Incident {
    private IncidentType type;
    private Properties properties;
    private Geometry geometry;

    @JsonProperty("type")
    public IncidentType getType() { return type; }
    @JsonProperty("type")
    public void setType(IncidentType value) { this.type = value; }

    @JsonProperty("properties")
    public Properties getProperties() { return properties; }
    @JsonProperty("properties")
    public void setProperties(Properties value) { this.properties = value; }

    @JsonProperty("geometry")
    public Geometry getGeometry() { return geometry; }
    @JsonProperty("geometry")
    public void setGeometry(Geometry value) { this.geometry = value; }
}
