package org.airquality.exception;

/**
 * Exception class for representing errors related to Air Quality Index.
 * Extends the RuntimeException class.
 */
public class AirQualityIndexException extends RuntimeException{

    public AirQualityIndexException(String message) {
        super(message);
    }
}
