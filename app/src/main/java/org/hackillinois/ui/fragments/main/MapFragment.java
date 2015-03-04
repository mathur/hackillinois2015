package org.hackillinois.ui.fragments.main;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import org.parceler.Parcels;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import org.hackillinois.R;
import org.hackillinois.data.model.Floor;
import org.hackillinois.ui.adapters.list.FloorAdapter;

/**
 * Created by Stephen on 2/11/2015.
 */
public class MapFragment extends Fragment {

    private static final String ARGS_KEY = "args";

    @InjectView(R.id.floor_list)
    ListView floorList;

    public static MapFragment newInstance(List<Floor> floors) {
        Parcelable floorsParcel = Parcels.wrap(floors);
        Bundle args = new Bundle();
        args.putParcelable(ARGS_KEY, floorsParcel);
        MapFragment mapFragment = new MapFragment();
        mapFragment.setArguments(args);
        return mapFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map, container, false);
        ButterKnife.inject(this, view);
        Parcelable floorsParcel = this.getArguments().getParcelable(ARGS_KEY);
        List<Floor> floors = Parcels.unwrap(floorsParcel);
        FloorAdapter adapter = new FloorAdapter(this.getActivity(), floors);
        floorList.setAdapter(adapter);
        return view;
    }
}
