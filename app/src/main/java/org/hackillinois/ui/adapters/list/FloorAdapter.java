package org.hackillinois.ui.adapters.list;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import org.hackillinois.HackIllinoisApplication;
import org.hackillinois.R;
import org.hackillinois.data.model.Floor;
import org.hackillinois.ui.activities.MapActivity;

import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by Stephen on 2/11/2015.
 */
public class FloorAdapter extends BaseAdapter {

    @Inject
    Picasso picasso;

    private Activity activity;
    private List<Floor> floors;

    public FloorAdapter(Activity activity, List<Floor> floors) {
        this.activity = activity;
        this.floors = floors;
        HackIllinoisApplication app = (HackIllinoisApplication) activity.getApplication();
        app.inject(this);
    }

    @Override
    public int getCount() {
        return floors.size();
    }

    @Override
    public Object getItem(int position) {
        return floors.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(activity);
            convertView = inflater.inflate(R.layout.list_item_floor, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Floor floor = floors.get(position);
        holder.url = floor.getImageUrl();
        picasso.load(floor.getImageUrl())
                .resize(512, 0)
                .into(holder.image);

        return convertView;
    }

    class ViewHolder {

        String url;

        @InjectView(R.id.map_image)
        ImageView image;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }

        @OnClick(R.id.map_image)
        void mapClick() {
            Intent intent = new Intent(activity, MapActivity.class);
            Bundle extras = new Bundle();
            extras.putString("url", url);
            extras.putString("title", "Map");
            intent.putExtras(extras);
            activity.startActivity(intent);
        }
    }
}
