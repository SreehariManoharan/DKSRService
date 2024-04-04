package org.incidence;

import org.incidence.dto.Incident;
import org.incidence.dto.IncidentDetails;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

/**
 * The IncidentRepository interface represents a repository for managing incident details.
 */
@Document(value = "incidentsCollection" )
public interface IncidentRepository extends MongoRepository<IncidentDetails, Long> {

    IncidentDetails findByStationId(String stationId);

    @Query(value = "update IncidentDetails set incidents = ?1 where stationId = ?2")
    void updateIncidentDetailsByStationId(List<Incident> incidents,  String stationId);
}
