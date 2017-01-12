package com.uber.sdk.rides.client.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * https://developer.uber.com/docs/rides/api/v1-products
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PriceDetail {

    private float base;
    private float minimum;
    private float cost_per_minute;
    private float cost_per_distance;
    private String distance_unit;
    private float cancellation_fee;
    private String currency_code;
    private List<ServiceFee> service_fees;

    /**
     * The base price
     */
    public float getBase() {
        return base;
    }

    /**
     * The minimum price of a trip
     */
    public float getMinimum() {
        return minimum;
    }

    /**
     * The charge per minute (if applicable for the product type).
     */
    public float getCostPerMinute() {
        return cost_per_minute;
    }

    /**
     * The charge per distance unit (if applicable for the product type).
     */
    public float getCostPerDistance() {
        return cost_per_distance;
    }

    /**
     * The unit of distance used to calculate the fare (either mile or km).
     */
    public String getDistanceUnit() {
        return distance_unit;
    }

    /**
     * The fee if a rider cancels the trip after the grace period.
     */
    public float getCancellationFee() {
        return cancellation_fee;
    }

    /**
     * ISO 4217 currency code.
     */
    public String getCurrencyCode() {
        return currency_code;
    }

    /**
     * List containing additional fees added to the price of a product.
     */
    public List<ServiceFee> getServiceFees() {
        return service_fees;
    }
}
