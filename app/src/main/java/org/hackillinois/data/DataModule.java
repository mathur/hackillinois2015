package org.hackillinois.data;

import android.app.Application;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.picasso.LruCache;
import com.squareup.picasso.Picasso;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import org.hackillinois.data.api.ApiModule;
import org.hackillinois.data.login.LoginModule;
import org.hackillinois.data.model.Floor;
import org.hackillinois.ui.activities.MapActivity;
import org.hackillinois.ui.adapters.list.FloorAdapter;

/**
 * Created by Stephen on 1/26/2015.
 */
@Module(complete = false,
        library = true,
        injects = {
                FloorAdapter.class,
                MapActivity.class
        },
        includes = {
        ApiModule.class,
        LoginModule.class
        })
public class DataModule {

    @Provides @Singleton
    public Picasso providePicasso(Application app) {
        // By default Picasso uses 1/7 of usable memory
        Runtime rt = Runtime.getRuntime();
        long maxMemory = rt.maxMemory();
        return new Picasso.Builder(app)
                .memoryCache(new LruCache((int) (maxMemory / 3)))
                .loggingEnabled(true)
                .build();
    }

    @Provides @Singleton
    public Gson provideGson() {
        return new GsonBuilder().create();
    }
}
