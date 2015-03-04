package org.hackillinois;

import android.app.Application;

import java.util.Arrays;
import java.util.List;

import dagger.ObjectGraph;
import org.hackillinois.R;
import org.hackillinois.data.DataModule;
import org.hackillinois.data.HackIllinoisModule;
import org.hackillinois.data.api.ApiModule;
import org.hackillinois.data.login.LoginModule;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by Stephen on 1/26/2015.
 */
public class HackIllinoisApplication extends Application {

    private ObjectGraph objectGraph;

    @Override
    public void onCreate() {
        super.onCreate();
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                                    .setDefaultFontPath("fonts/ProximaNova.ttf")
                                    .setFontAttrId(R.attr.fontPath)
                                    .build());
        objectGraph = ObjectGraph.create(getModules().toArray());
    }

    protected List<Object> getModules() {
        return Arrays.asList(
                new ApiModule(),
                new DataModule(),
                new HackIllinoisModule(this),
                new LoginModule());
    }

    public void inject(Object object) {
        objectGraph.inject(object);
    }
}
