package org.hackillinois.ui.adapters.list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.hackillinois.R;
import org.hackillinois.data.model.Event;
import org.hackillinois.ui.utils.DateUtils;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Stephen on 2/9/2015.
 */
public class EventsAdapter extends BaseAdapter {

    private static final String TAG = "EventsAdapter";

    private Context context;
    private List<Event> events;

    public EventsAdapter(Context context, List<Event> events) {
        this.context = context;
        Collections.sort(events, new Comparator<Event>() {
            @Override
            public int compare(Event lhs, Event rhs) {
                return (int) (lhs.getTime() - rhs.getTime());
            }
        });
        this.events = events;
    }

    @Override
    public int getCount() {
        return events.size();
    }

    @Override
    public Object getItem(int position) {
        return events.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.list_item_event, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Event event = events.get(position);
        holder.name.setText(event.getEventName());
        holder.description.setText(event.getDescription());
        holder.location.setText(event.getLocation());
        holder.time.setText(DateUtils.getTimeOfDay(event.getTime()));
        Picasso.with(context)
                .load(event.getIconUrl())
                .into(holder.icon);
        return convertView;
    }

    class ViewHolder {

        @InjectView(R.id.event_icon)
        ImageView icon;

        @InjectView(R.id.event_time)
        TextView time;

        @InjectView(R.id.event_name)
        TextView name;

        @InjectView(R.id.event_description)
        TextView description;

        @InjectView(R.id.event_location)
        TextView location;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
