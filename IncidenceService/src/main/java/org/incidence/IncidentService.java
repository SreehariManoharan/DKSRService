package org.incidence;

import org.incidence.client.TomTomIncidentClient;
import org.incidence.dto.Converter;
import org.incidence.dto.IncidentDetails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

/**
 * The IncidentService class is responsible for managing incident-related operations.
 */
@Service
public class IncidentService {
    @Value(value = "${incidence-details.api-key}")
    private String apikey;
    private final TomTomIncidentClient tomTomIncidentClient;
    private final IncidentRepository incidentRepository;


    public IncidentService(TomTomIncidentClient tomTomIncidentClient, IncidentRepository incidentRepository) {
        this.tomTomIncidentClient = tomTomIncidentClient;
        this.incidentRepository = incidentRepository;
    }

    /**
     * Verify here, already an entry exists for Berlin and then update
     *
     * @param bbox
     */
    public void saveIncidentDetails(String bbox, String stationId) {
        Optional.ofNullable(tomTomIncidentClient.getIncidentDetails("5", apikey, bbox))
                .map(incidentDetails -> {
                    try {
                        IncidentDetails latestIncidentDetails = Converter.fromJsonString(incidentDetails);
                        latestIncidentDetails.setStationId(stationId);
                        return latestIncidentDetails;
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                })
                .map(updatedLatestIncidentDetails -> saveOrUpdateIncidents(updatedLatestIncidentDetails, stationId))
                .orElseThrow();
    }

    private IncidentDetails saveOrUpdateIncidents(IncidentDetails updatedLatestIncidentDetails, String stationId) {
       return Optional.ofNullable(incidentRepository.findByStationId(stationId))
                .map(existingIncidentDetails -> {
                    incidentRepository.updateIncidentDetailsByStationId(updatedLatestIncidentDetails.getIncidents(), stationId);
                    return incidentRepository.findByStationId(stationId);
                }).orElse(incidentRepository.save(updatedLatestIncidentDetails));
    }

    public IncidentDetails getIncidentDetails() {
        return incidentRepository.findByStationId("Berlin");
    }
}
