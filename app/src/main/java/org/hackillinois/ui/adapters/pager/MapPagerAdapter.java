package org.hackillinois.ui.adapters.pager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

import org.hackillinois.data.model.Map;
import org.hackillinois.ui.fragments.main.MapFragment;

/**
 * Created by Stephen on 2/11/2015.
 */
public class MapPagerAdapter extends FragmentPagerAdapter {

    private List<Map> maps;

    public MapPagerAdapter(FragmentManager fm, List<Map> maps) {
        super(fm);
        this.maps = maps;
    }

    @Override
    public Fragment getItem(int position) {
        return MapFragment.newInstance(maps.get(position).getFloors());
    }

    @Override
    public int getCount() {
        return maps.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return maps.get(position).getName();
    }
}
