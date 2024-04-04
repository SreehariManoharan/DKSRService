package org.incidence.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * The IncidentDetails class represents the details of an incident.
 */
public class IncidentDetails {

    private String stationId;
    private List<Incident> incidents;
    public String getStationId() {
        return stationId;
    }

    public void setStationId(String stationId) {
        this.stationId = stationId;
    }
    @JsonProperty("incidents")
    public List<Incident> getIncidents() { return incidents; }
    @JsonProperty("incidents")
    public void setIncidents(List<Incident> value) { this.incidents = value; }
}
