package org.hackillinois.data.model;

import android.graphics.Color;
import android.util.Log;

import com.google.gson.annotations.SerializedName;

import org.hackillinois.ui.utils.DateUtils;

/**
 * Created by Stephen on 1/26/2015.
 */
public class News extends ApiItem {

    @SerializedName("title")
    String title;

    @SerializedName("description")
    String description;

    @SerializedName("time")
    long time;

    @SerializedName("emergency")
    boolean emergency;

    @SerializedName("icon")
    String iconUrl;

    @SerializedName("color")
    String color;

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getTime() {
        return DateUtils.getTimeSince(time);
    }

    public boolean isEmergency() {
        return emergency;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public int getColor() {
        int colorInt = Color.parseColor("#" + color);
        return colorInt;
    }
}
