package org.hackillinois.data.model;

/**
 * Created by Stephen on 1/27/2015.
 */
public class DrawerItem {

    private int iconId;
    private String label;

    public DrawerItem(int iconId, String label) {
        this.iconId = iconId;
        this.label = label;
    }

    public int getIconId() {
        return iconId;
    }

    public String getLabel() {
        return label;
    }
}
