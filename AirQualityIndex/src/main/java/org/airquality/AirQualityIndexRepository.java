package org.airquality;

import org.airquality.dto.AirQualityIndexBoundDto;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Represents a repository for retrieving and storing Air Quality Index data.
 */
@Document(collection = "airQualityIndex")
public interface AirQualityIndexRepository extends MongoRepository<AirQualityIndexBoundDto, Long> {
}
