package org.hackillinois.ui.fragments.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.astuetz.PagerSlidingTabStrip;

import butterknife.ButterKnife;
import butterknife.InjectView;
import org.hackillinois.R;
import org.hackillinois.ui.adapters.pager.ProfilePagerAdapter;

/**
 * Created by Stephen on 2/10/2015.
 */
public class ProfileFragment extends Fragment {

    private static final String TAG = "ProfileFragment";

    @InjectView(R.id.profile_pager)
    ViewPager profilePager;

    @InjectView(R.id.profile_tab_strip)
    PagerSlidingTabStrip profileTabStrip;

    public static ProfileFragment newInstance() {
        return new ProfileFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        ButterKnife.inject(this, view);
        ProfilePagerAdapter adapter = new ProfilePagerAdapter(this.getChildFragmentManager());
        profilePager.setAdapter(adapter);
        profileTabStrip.setViewPager(profilePager);
        Log.d(TAG, "Profile view created");
        return view;
    }
}
