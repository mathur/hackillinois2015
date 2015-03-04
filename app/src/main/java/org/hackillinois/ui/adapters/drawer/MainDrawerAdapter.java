package org.hackillinois.ui.adapters.drawer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import org.hackillinois.R;
import org.hackillinois.data.model.DrawerItem;

/**
 * Created by Stephen on 1/27/2015.
 */
public class MainDrawerAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;
    private List<DrawerItem> items = new ArrayList<>();

    public MainDrawerAdapter(Context context, List<DrawerItem> items) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null) {
            convertView = inflater.inflate(R.layout.list_item_main_drawer, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        DrawerItem item = items.get(position);
        Picasso.with(context)
                .load(item.getIconId())
                .resize(72, 0)
                .into(holder.icon);
        holder.name.setText(item.getLabel());
        return convertView;
    }

    class ViewHolder {

        @InjectView(R.id.main_drawer_item_image)
        ImageView icon;

        @InjectView(R.id.main_drawer_item_name)
        TextView name;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
