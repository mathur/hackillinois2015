package org.hackillinois.ui.fragments.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

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
import org.hackillinois.data.model.News;
import org.hackillinois.ui.adapters.list.AlertsAdapter;
import rx.android.app.AppObservable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Created by Stephen on 1/26/2015.
 */
public class AlertsFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private AlertsAdapter adapter;

    @InjectView(R.id.alerts_list)
    ListView alertsList;

    @InjectView(R.id.alerts_swipe)
    SwipeRefreshLayout swipeLayout;

    @InjectView(R.id.alerts_progress_bar)
    ProgressBarCircularIndeterminate progressBar;

    @Inject
    HackIllinoisApi api;

    public static AlertsFragment newInstance() {
        return new AlertsFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_alerts, container, false);
        ButterKnife.inject(this, view);
        HackIllinoisApplication app = (HackIllinoisApplication) this.getActivity().getApplication();
        app.inject(this);
        adapter = new AlertsAdapter(this.getActivity());
        alertsList.setAdapter(adapter);
        swipeLayout.setColorSchemeColors(R.color.blue_secondary);
        swipeLayout.setOnRefreshListener(this);
        onRefresh();
        return view;
    }

    private void updateList(List<News> news) {
        adapter.setData(news);
    }

    @Override
    public void onRefresh() {
        AppObservable.bindFragment(this, api.news())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(
                new Action1<List<News>>() {
                    @Override
                    public void call(List<News> news) {
                        updateList(news);
                        swipeLayout.setRefreshing(false);
                        progressBar.setVisibility(View.GONE);
                    }
                },
                new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Crouton.makeText(getActivity(), "Failed to get alerts",
                                Style.ALERT).show();
                        swipeLayout.setRefreshing(false);
                        progressBar.setVisibility(View.GONE);
                    }
                });
    }
}
