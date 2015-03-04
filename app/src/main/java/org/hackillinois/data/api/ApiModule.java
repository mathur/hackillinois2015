package org.hackillinois.data.api;

import com.google.gson.Gson;

import org.hackillinois.ui.fragments.main.AlertsFragment;
import org.hackillinois.ui.fragments.main.MapPagerFragment;
import org.hackillinois.ui.fragments.main.ScheduleFragment;
import org.hackillinois.ui.fragments.main.UserProfileFragment;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.Endpoint;
import retrofit.Endpoints;
import retrofit.RestAdapter;
import retrofit.client.Client;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;

/**
 * Created by Stephen on 1/26/2015.
 */
@Module(complete = false,
        library = true,
        injects = {
        AlertsFragment.class,
        UserProfileFragment.class,
        ScheduleFragment.class,
        MapPagerFragment.class
})
public class ApiModule {

    private static final String API_URL = "https://www.hackillinois.org/mobile";

    @Provides @Singleton
    Client provideClient() {
        return new OkClient();
    }

    @Provides @Singleton
    Endpoint provideEndpoint() {
        return Endpoints.newFixedEndpoint(API_URL);
    }

    @Provides @Singleton
    RestAdapter provideRestAdapter(Endpoint endpoint, Client client, Gson gson) {
        return new RestAdapter.Builder()
                .setClient(client)
                .setEndpoint(endpoint)
                .setConverter(new GsonConverter(gson))
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();
    }

    @Provides @Singleton
    HackIllinoisApi provideApi(RestAdapter restAdapter) {
        return restAdapter.create(HackIllinoisApi.class);
    }

}
