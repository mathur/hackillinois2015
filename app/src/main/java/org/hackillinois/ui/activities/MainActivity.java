package org.hackillinois.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnItemClick;
import org.hackillinois.HackIllinoisApplication;
import org.hackillinois.R;
import org.hackillinois.data.login.SessionManager;
import org.hackillinois.data.model.DrawerItem;
import org.hackillinois.data.model.User;
import org.hackillinois.ui.adapters.drawer.MainDrawerAdapter;
import org.hackillinois.ui.fragments.login.LoginInputFragment;
import org.hackillinois.ui.fragments.main.AlertsFragment;
import org.hackillinois.ui.fragments.main.MapPagerFragment;
import org.hackillinois.ui.fragments.main.ScheduleFragment;
import org.hackillinois.ui.fragments.main.SchoolProfileFragment;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;


public class MainActivity extends ActionBarActivity {

    private static final String TAG = "MainActivity";

    private ActionBarDrawerToggle drawerToggle;
    private ArrayList<DrawerItem> items;

    private int currentDrawerItem;

    @InjectView(R.id.main_drawer)       DrawerLayout drawerLayout;
    @InjectView(R.id.main_drawer_list)  ListView drawerList;
    @InjectView(R.id.main_toolbar)      Toolbar toolbar;
    @InjectView(R.id.profile_name)      TextView name;
    @InjectView(R.id.profile_email)     TextView email;
    @InjectView(R.id.profile_code)      TextView code;

    @Inject SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        HackIllinoisApplication app = (HackIllinoisApplication) this.getApplication();
        app.inject(this);

        // TODO: Check if user is logged in
        if(!sessionManager.needsLogin()) {
            Intent loginIntent = new Intent(this, LoginActivity.class);
            startActivity(loginIntent);
            finish();
        } else {

            ButterKnife.inject(this);

            drawerToggle = new ActionBarDrawerToggle(this, drawerLayout,
                    toolbar, R.string.drawer_open, R.string.drawer_close);
            drawerLayout.setDrawerListener(drawerToggle);
            items = new ArrayList<>();

            items.add(new DrawerItem(R.drawable.alert_icon, "Alerts"));
            items.add(new DrawerItem(R.drawable.map_icon, "Map"));
            items.add(new DrawerItem(R.drawable.schedule_icon, "Schedule"));
            items.add(new DrawerItem(R.drawable.profile_icon, "Profile"));

            MainDrawerAdapter adapter = new MainDrawerAdapter(this, items);
            drawerList.setAdapter(adapter);
            setSupportActionBar(toolbar);

            if(sessionManager.getUser() != null) {
                User user = sessionManager.getUser();
                updateDrawerData(user);

            } else {
                name.setText("User not logged in");
            }

            // If the fragment changes, currentDrawerItem changes
            currentDrawerItem = 2;
            this.getSupportFragmentManager().beginTransaction()
                    .replace(R.id.main_container, ScheduleFragment.newInstance())
                    .commit();
            setTitle("Schedule");
        }
    }

    @OnItemClick(R.id.main_drawer_list)
    void drawerItemClick(int position) {
        Log.d(TAG, "POSITION: " + position);
        DrawerItem item = items.get(position);
        if(position != currentDrawerItem) {
            if (item.getLabel().equals("Alerts")) {
                switchToFragment(AlertsFragment.newInstance());
            } else if (item.getLabel().equals("Map")) {
                switchToFragment(MapPagerFragment.newInstance());
            } else if (item.getLabel().equals("Profile")) {
                if(sessionManager.getUser() == null) {
                    switchToFragment(LoginInputFragment.newInstance());
                } else {
                    switchToFragment(SchoolProfileFragment.newInstance());
                }
            } else if (item.getLabel().equals("Schedule")) {
                switchToFragment(ScheduleFragment.newInstance());
            }
            setTitle(item.getLabel());
            currentDrawerItem = position;
        }
        drawerLayout.closeDrawer(Gravity.START);
    }

    private void switchToFragment(Fragment fragment) {
        this.getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_container, fragment)
                .commit();
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return drawerToggle.onOptionsItemSelected(item) ||
               super.onOptionsItemSelected(item);

    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    public void updateDrawerData(User user) {
        name.setText(user.getName());
        email.setText(user.getEmail());
        code.setText("Check-in code: " + user.getCode());
    }
}
