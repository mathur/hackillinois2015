package org.hackillinois.data.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Stephen on 1/26/2015.
 */
public class Sponsor extends ApiItem {

    @SerializedName("name")
    String name;

    @SerializedName("prize_title")
    String prizeTitle;

    @SerializedName("level")
    String level;
}
