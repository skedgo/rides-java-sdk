/*
 * Copyright (c) 2016 Uber Technologies, Inc.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.uber.sdk.rides.client.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.squareup.moshi.Json;

import javax.annotation.Nullable;

/**
 * An ongoing or completed ride. See
 * <a href="https://developer.uber.com/v1/endpoints/#request-details">Requests</a>
 * for more information.
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE,
                isGetterVisibility = JsonAutoDetect.Visibility.NONE)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Ride {

    private String request_id;
    private Status status;
    @Nullable
    private Driver driver;
    @Nullable
    private Float surge_multiplier;
    @Nullable
    private Location location;
    @Nullable
    private Vehicle vehicle;
    private String product_id;
    private boolean shared;
    @Nullable
    private Location pickup;
    @Nullable
    private Location destination;

    /**
     * The unique ID of the ride.
     */
    public String getRideId() {
        return request_id;
    }

    /**
     * The status of the ride indicating state.
     */
    public Status getStatus() {
        return status;
    }

    /**
     * The object that contains driver details.
     */
    @Nullable
    public Driver getDriver() {
        return driver;
    }

    /**
     * The object that contains the location information of the vehicle and driver.
     */
    @Nullable
    public Location getLocation() {
        return location;
    }

    /**
     * The object that contains vehicle details.
     */
    @Nullable
    public Vehicle getVehicle() {
        return vehicle;
    }

    /**
     * The surge pricing multiplier used to calculate the increased price of a Request. A multiplier
     * of 1.0 means surge pricing is not in effect.
     */
    @Nullable
    public Float getSurgeMultiplier() {
        return surge_multiplier;
    }

    /**
     * The product ID associated to the Ride.
     */
    public String getProductId() {
        return product_id;
    }

    /**
     * Indicates whether the ride is a shared ride or not. UberPool is an example of a shared ride.
     */
    public boolean isShared() {
        return shared;
    }

    @Nullable
    public Location getPickup() {
        return pickup;
    }

    @Nullable
    public Location getDestination() {
        return destination;
    }

    /**
     * Represents all possible Ride statuses
     */
    public enum Status {
        @Json(name = "processing") PROCESSING("processing"),
        @Json(name = "no_drivers_available") NO_DRIVERS_AVAILABLE("no_drivers_available"),
        @Json(name = "accepted") ACCEPTED("accepted"),
        @Json(name = "arriving") ARRIVING("arriving"),
        @Json(name = "in_progress") IN_PROGRESS("in_progress"),
        @Json(name = "driver_canceled") DRIVER_CANCELED("driver_canceled"),
        @Json(name = "rider_canceled") RIDER_CANCELED("rider_canceled"),
        @Json(name = "completed") COMPLETED("completed");

        private String value;

        Status(String value) {
            this.value = value;
        }
    }
}
