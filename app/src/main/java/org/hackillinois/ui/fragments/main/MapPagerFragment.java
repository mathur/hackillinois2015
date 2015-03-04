package org.hackillinois.ui.fragments.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.astuetz.PagerSlidingTabStrip;
import com.gc.materialdesign.views.ProgressBarCircularIndeterminate;

import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;
import org.hackillinois.HackIllinoisApplication;
import org.hackillinois.R;
import org.hackillinois.data.api.HackIllinoisApi;
import org.hackillinois.data.model.Map;
import org.hackillinois.ui.adapters.pager.MapPagerAdapter;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Created by Stephen on 1/27/2015.
 */
public class MapPagerFragment extends Fragment {

    @Inject
    HackIllinoisApi api;

    @InjectView(R.id.map_pager)
    ViewPager mapPager;

    @InjectView(R.id.map_tab_strip)
    PagerSlidingTabStrip mapTabStrip;

    @InjectView(R.id.maps_progress_bar)
    ProgressBarCircularIndeterminate progressBar;

    public static MapPagerFragment newInstance() {
        return new MapPagerFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map_pager, container, false);
        ButterKnife.inject(this, view);
        HackIllinoisApplication app = (HackIllinoisApplication) this.getActivity().getApplication();
        app.inject(this);
        api.maps()
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(
        new Action1<List<Map>>() {
            @Override
            public void call(List<Map> maps) {
                updateMaps(maps);
                progressBar.setVisibility(View.GONE);
            }
        },
        new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                Crouton.makeText(getActivity(), "Failed to get maps", Style.ALERT).show();
                progressBar.setVisibility(View.GONE);
            }
        });
        return view;
    }

    private void updateMaps(List<Map> maps) {
        MapPagerAdapter adapter = new MapPagerAdapter(this.getChildFragmentManager(), maps);
        mapPager.setAdapter(adapter);
        mapTabStrip.setViewPager(mapPager);
    }
}
