package org.hackillinois.ui.adapters.pager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import java.util.HashMap;
import java.util.List;

import org.hackillinois.data.model.Event;
import org.hackillinois.ui.fragments.main.DayFragment;

/**
 * Created by Stephen on 1/27/2015.
 */
public class SchedulePagerAdapter extends FragmentPagerAdapter {

    private static final String TAG = "SchedulePagerAdapter";

    private static final int NUM_DAYS = 3;
    private static final String[] DAY_TITLES = new String[] {"Friday", "Saturday", "Sunday"};
    private static final String[] DAY_DATES = new String[] {"02-27", "02-28", "03-01"};

    private HashMap<String, List<Event>> daysToEvents;

    public SchedulePagerAdapter(FragmentManager fm, HashMap<String, List<Event>> daysToEvents) {
        super(fm);
        this.daysToEvents = daysToEvents;
        Log.d(TAG, "Schedule pager adapter created");
    }

    @Override
    public Fragment getItem(int position) {
        Log.d(TAG, "Schedule pager adapter get item: " + position);
        List<Event> events = daysToEvents.get(DAY_DATES[position]);
        return DayFragment.newInstance(events);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return DAY_TITLES[position];
    }

    @Override
    public int getCount() {
        return NUM_DAYS;
    }
}
