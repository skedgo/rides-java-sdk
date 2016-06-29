package com.uber.sdk.rides.client.model;

/**
 * https://developer.uber.com/docs/rides/api/v1-products
 */
public class ServiceFee {

    private String name;
    private float fee;

    /**
     * The name of the service fee.
     */
    public String getName() {
        return name;
    }

    /**
     * The amount of the service fee.
     */
    public float getFee() {
        return fee;
    }
}
