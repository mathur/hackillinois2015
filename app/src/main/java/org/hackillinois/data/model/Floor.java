package org.hackillinois.data.model;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

/**
 * Created by Stephen on 2/11/2015.
 */
@Parcel
public class Floor {

    @SerializedName("floor")
    String floor;

    @SerializedName("url")
    String imageUrl;

    public String getFloor() {
        return floor;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
