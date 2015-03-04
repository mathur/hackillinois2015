package org.hackillinois.data.model;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

/**
 * Created by Stephen on 1/26/2015.
 */
@Parcel
public class Event extends ApiItem {

    @SerializedName("event_name")
    String eventName;

    @SerializedName("description")
    String description;

    @SerializedName("time")
    long time;

    @SerializedName("location")
    String location;

    @SerializedName("type")
    String type;

    @SerializedName("icon")
    String iconUrl;

    @SerializedName("color")
    String color;

    public String getEventName() {
        return eventName;
    }

    public String getDescription() {
        return description;
    }

    public long getTime() {
        return time;
    }

    public String getLocation() {
        return location;
    }

    public String getType() {
        return type;
    }

    public String getColor() {
        return color;
    }

    public String getIconUrl() {
        return iconUrl;
    }
}
