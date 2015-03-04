package org.hackillinois.ui.adapters.list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import org.hackillinois.R;
import org.hackillinois.data.model.News;

/**
 * Created by Stephen on 1/27/2015.
 */
public class AlertsAdapter extends BaseAdapter {

    private List<News> alerts;
    private LayoutInflater inflater;
    private Context context;

    public AlertsAdapter(Context context) {
        inflater = LayoutInflater.from(context);
        alerts = new ArrayList<>();
        this.context = context;
    }

    @Override
    public int getCount() {
        return alerts.size();
    }

    @Override
    public Object getItem(int position) {
        return alerts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null) {
            convertView = inflater.inflate(R.layout.list_item_alert, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        News alert = alerts.get(position);
        holder.title.setText(alert.getTitle());
        holder.description.setText(alert.getDescription());
        holder.time.setText(alert.getTime());
        holder.sideBar.setBackgroundColor(alert.getColor());
        Picasso.with(context)
                .load(alert.getIconUrl())
                .into(holder.icon);
        return convertView;
    }

    public void setData(List<News> alerts) {
        this.alerts = alerts;
        Collections.sort(alerts, new Comparator<News>() {
            @Override
            public int compare(News lhs, News rhs) {
                if(lhs.isEmergency()) {
                    return -1;
                } else if (rhs.isEmergency()) {
                    return 1;
                }
                return 0;
            }
        });
        this.notifyDataSetChanged();
    }

    class ViewHolder {

        @InjectView(R.id.alert_item_side_bar)
        View sideBar;

        @InjectView(R.id.alert_description)
        TextView description;

        @InjectView(R.id.alert_image)
        ImageView icon;

        @InjectView(R.id.alert_time)
        TextView time;

        @InjectView(R.id.alert_title)
        TextView title;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
