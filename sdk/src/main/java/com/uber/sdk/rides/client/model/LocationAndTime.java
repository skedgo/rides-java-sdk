package com.uber.sdk.rides.client.model;

/**
 *
 *
 */
public class LocationAndTime extends Location {

    // for pickup, ETA is only available when the trips is accepted or arriving.
    // for destination, ETA is only available once the trip is in progress.
    private Integer eta;

    /**
     * Constructor.
     *
     * @param latitude  The latitude in decimal notation.
     * @param longitude The longitude in decimal notation.
     */
    public LocationAndTime(float latitude, float longitude) {
        super(latitude, longitude);
    }

    public Integer getEta() {
        return eta;
    }
}
