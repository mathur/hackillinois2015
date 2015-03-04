package org.hackillinois.ui.adapters.pager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import org.hackillinois.ui.fragments.main.SchoolProfileFragment;
import org.hackillinois.ui.fragments.main.UserProfileFragment;

/**
 * Created by Stephen on 2/10/2015.
 */
public class ProfilePagerAdapter extends FragmentPagerAdapter {

    private static final int NUM_FRAGMENTS = 2;
    private static final String[] PROFILE_TITLES = new String[] {"Personal", "School"};

    public ProfilePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return UserProfileFragment.newInstance();
            case 1:
                return SchoolProfileFragment.newInstance();
        }
        return null;
    }

    @Override
    public int getCount() {
        return NUM_FRAGMENTS;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return PROFILE_TITLES[position];
    }
}
