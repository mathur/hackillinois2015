package org.hackillinois.data.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Stephen on 1/26/2015.
 */
public class ApiItem {

    @SerializedName("key")
    long key;

    public long getKey() {
        return key;
    }
}
