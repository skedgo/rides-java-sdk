package com.uber.sdk.rides.client.model;

/**
 *
 *
 */
public class LocationAndTime extends Location {

    private int eta;

    /**
     * Constructor.
     *
     * @param latitude  The latitude in decimal notation.
     * @param longitude The longitude in decimal notation.
     */
    public LocationAndTime(float latitude, float longitude) {
        super(latitude, longitude);
    }

    public int getEta() {
        return eta;
    }
}
