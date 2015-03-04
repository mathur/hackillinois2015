package org.hackillinois.data;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Stephen on 2/10/2015.
 */
@Module(complete = false,
        includes = {
        DataModule.class,
})
public class HackIllinoisModule {

    private Application app;

    public HackIllinoisModule(Application app) {
        this.app = app;
    }

    @Provides @Singleton
    public Application provideApplication() {
        return app;
    }
}
