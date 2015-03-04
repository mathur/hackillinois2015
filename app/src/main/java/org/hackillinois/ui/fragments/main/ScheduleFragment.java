package org.hackillinois.ui.fragments.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gc.materialdesign.views.ProgressBarCircularIndeterminate;
import com.viewpagerindicator.TitlePageIndicator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;
import org.hackillinois.HackIllinoisApplication;
import org.hackillinois.R;
import org.hackillinois.data.api.HackIllinoisApi;
import org.hackillinois.data.model.Event;
import org.hackillinois.ui.adapters.pager.SchedulePagerAdapter;
import org.hackillinois.ui.utils.DateUtils;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Created by Stephen on 1/27/2015.
 */
public class ScheduleFragment extends Fragment {

    @Inject
    HackIllinoisApi api;

    @InjectView(R.id.schedule_indicator)
    TitlePageIndicator indicator;

    @InjectView(R.id.schedule_pager)
    ViewPager pager;

    @InjectView(R.id.schedule_progress_bar)
    ProgressBarCircularIndeterminate progressBar;

    public static ScheduleFragment newInstance() {
        return new ScheduleFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_schedule, container, false);
        ButterKnife.inject(this, view);
        HackIllinoisApplication app = (HackIllinoisApplication) this.getActivity().getApplication();
        app.inject(this);
        api.schedule()
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(
                new Action1<List<Event>>() {
                    @Override
                    public void call(List<Event> events) {
                        updateEvents(events);
                        progressBar.setVisibility(View.GONE);
                    }
                },
                new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Crouton.makeText(getActivity(), "Failed to get schedule",
                                Style.ALERT).show();
                        progressBar.setVisibility(View.GONE);
                    }
                });
        return view;
    }

    private void updateEvents(List<Event> events) {
        HashMap<String, List<Event>> dayToEvents = new HashMap<>();
        // Map the list of events to the days they correspond to
        for(Event event : events) {
            String day = DateUtils.getDay(event.getTime());
            List<Event> dayEvents = dayToEvents.get(day);
            if(dayEvents == null) {
                dayEvents = new ArrayList<>();
                dayToEvents.put(day, dayEvents);
            }
            dayEvents.add(event);
        }
        SchedulePagerAdapter adapter = new SchedulePagerAdapter(this.getChildFragmentManager(), dayToEvents);
        pager.setAdapter(adapter);
        indicator.setTextColor(getResources().getColor(R.color.text_secondary));
        indicator.setSelectedColor(getResources().getColor(R.color.blue_primary));
        indicator.setViewPager(pager);
    }
}
