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

/**
 * A product representing a type of ride on the Uber platform. See
 * <a href="https://developer.uber.com/v1/endpoints/#product-types">Products</a>
 * for more information.
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE,
                isGetterVisibility = JsonAutoDetect.Visibility.NONE)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Product {

    private String product_id;
    private String short_description;
    private String display_name;
    private String description;
    private int capacity;
    private String image;
    private boolean shared;
    private boolean upfront_fare_enabled;
    private PriceDetail price_details;

    /**
     * A unique identifier representing a specific product for a given latitude &amp; longitude. For
     * example, uberX in San Francisco will have a different product_id than uberX in Los Angeles.
     */
    public String getProductId() {
        return product_id;
    }

    /**
     * Display name of product.
     */
    public String getDisplayName() {
        return display_name;
    }

    /**
     * Description of product.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Capacity of product. For samples, 4 people.
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Image URL representing the product.
     */
    public String getImage() {
        return image;
    }

    /**
     * @return {@code true} if the ride may be shared with others.
     */
    public boolean isShared() {
        return shared;
    }

    /**
     * @return {code true} if this product is configured to work with upfront fares.
     */
    public boolean isUpfrontFareEnabled(){
        return upfront_fare_enabled;
    }

    /**
     * An abbreviated description of the product. Localized according to Accept-Language header.
     */
    public String getShortDescription() {
        return short_description;
    }

    /**
     * The basic price details (not including any surge pricing adjustments).
     * This field is null for products with metered fares (taxi) or upfront prices (uberPOOL).
     */
    public PriceDetail getPriceDetails() {
        return price_details;
    }
}

