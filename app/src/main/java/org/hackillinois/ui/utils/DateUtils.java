package org.hackillinois.ui.utils;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by Stephen on 1/27/2015.
 */
public class DateUtils {

    private static final String TAG = "DateUtils";

    private static final int ONE_SECOND = 1000;
    private static final int ONE_MINUTE = 60 * ONE_SECOND;
    private static final int ONE_HOUR = 60 * ONE_MINUTE;
    private static final int ONE_DAY = 24 * ONE_HOUR;

    public static String getTimeSince(long time) {
        Date date = new Date(time * 1000);
        Date current = Calendar.getInstance().getTime();
        long millisSinceUpdate = current.getTime() - date.getTime();
        if(millisSinceUpdate < ONE_MINUTE) {
            return millisSinceUpdate/1000 + " seconds ago";
        }
        else if(millisSinceUpdate < ONE_HOUR) {
            long minuteSinceUpdate = millisSinceUpdate/(60 * 1000);
            if(minuteSinceUpdate == 1) {
                return minuteSinceUpdate + " minute ago";
            }
            return minuteSinceUpdate + " minutes ago";
        }
        else {
            long hoursSinceUpdate = millisSinceUpdate/(60 * 60 * 1000);
            if(hoursSinceUpdate == 1) {
                return hoursSinceUpdate + " hour ago";
            }
            return hoursSinceUpdate + " hours ago";
        }
    }

    public static String getDay(long time) {
        Date date = new Date(time * 1000L);
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd", Locale.US);
        Log.d(TAG, sdf.format(date));
        return sdf.format(date);
    }

    public static String getTimeOfDay(long time) {
        Date date = new Date(time * 1000L);
        SimpleDateFormat sdf = new SimpleDateFormat("h:mm a");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT-6"));
        return sdf.format(date);
    }
}
