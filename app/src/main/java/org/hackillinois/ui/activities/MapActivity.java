package org.hackillinois.ui.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import org.hackillinois.HackIllinoisApplication;
import org.hackillinois.R;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Stephen on 2/27/2015.
 */
public class MapActivity extends ActionBarActivity {

    @InjectView(R.id.map_toolbar)
    Toolbar toolbar;

    @InjectView(R.id.map_image)
    ImageView map;

    @Inject
    Picasso picasso;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        HackIllinoisApplication app = (HackIllinoisApplication) this.getApplication();
        app.inject(this);
        ButterKnife.inject(this);
        Bundle extras = this.getIntent().getExtras();
        String url = extras.getString("url");
        String title = extras.getString("title");
        this.setSupportActionBar(toolbar);
        this.setTitle(title);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        picasso.load(url).into(map);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
