package org.airquality.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * This interface represents a client for retrieving air quality index bound data from a REST API.
 */
@FeignClient(name = "Air-Quality-index-bound-client", url = "https://api.waqi.info/v2/map/bounds")
public interface AirQualityIndexBoundClient {

    @RequestMapping(method = RequestMethod.GET)
    String getAirQualityIndexBound(@RequestParam("latlng") String latlng, @RequestParam("token") String token);
}
