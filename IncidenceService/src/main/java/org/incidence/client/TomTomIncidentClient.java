package org.incidence.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * This is a Feign client interface for accessing the TomTom Incident service.
 */
@FeignClient(name = "tom-tom-incident-client", url = "https://api.tomtom.com/traffic/services")
public interface TomTomIncidentClient {

    @RequestMapping(value = "/{versionNumber}/incidentDetails", method = RequestMethod.GET)
    String getIncidentDetails(@PathVariable String versionNumber, @RequestParam(value = "key") String key, @RequestParam(value = "bbox") String bbox);
}
