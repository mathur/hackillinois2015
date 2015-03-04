package org.hackillinois.ui.fragments.main;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import org.parceler.Parcels;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import org.hackillinois.R;
import org.hackillinois.data.model.Event;
import org.hackillinois.ui.adapters.list.EventsAdapter;

/**
 * Created by Stephen on 1/27/2015.
 */
public class DayFragment extends Fragment {

    private static final String TAG = "DayFragment";

    @InjectView(R.id.events_list)
    ListView eventsList;

    public static DayFragment newInstance(List<Event> events) {
        DayFragment dayFragment = new DayFragment();
        Bundle args = new Bundle();
        Parcelable wrappedEvents = Parcels.wrap(events);
        args.putParcelable("events", wrappedEvents);
        dayFragment.setArguments(args);
        return dayFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO: Replace this with actual layout
        View view = inflater.inflate(R.layout.fragment_day, container, false);
        ButterKnife.inject(this, view);
        List<Event> events = Parcels.unwrap(this.getArguments().getParcelable("events"));
        EventsAdapter adapter = new EventsAdapter(this.getActivity(), events);
        eventsList.setAdapter(adapter);
        Log.d(TAG, "Day fragment created");
        return view;
    }
}
