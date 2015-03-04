package org.hackillinois.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Stephen on 2/11/2015.
 */
public class Map extends ApiItem {

    @SerializedName("name")
    String name;

    @SerializedName("floors")
    List<Floor> floors;

    public String getName() {
        return name;
    }

    public List<Floor> getFloors() {
        return floors;
    }
}
