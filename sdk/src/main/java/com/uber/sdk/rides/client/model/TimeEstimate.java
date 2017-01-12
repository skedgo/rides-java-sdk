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

import javax.annotation.Nullable;

/**
 * An estimated time for a product on the Uber platform. See
 * <a href="https://developer.uber.com/docs/rides/api/v1-estimates-time">Time Estimates</a>
 * for more information.
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TimeEstimate {

    private String product_id;
    private String display_name;
    @Nullable
    private Integer estimate;

    /**
     * Unique identifier representing a specific product for a given latitude &amp; longitude. For
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
     * ETA for the product (in seconds). Always show estimate in minutes.
     */
    @Nullable
    public Integer getEstimate() {
        return estimate;
    }
}
