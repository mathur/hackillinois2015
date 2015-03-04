package org.hackillinois.data.login;

import android.app.Application;

import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import org.hackillinois.ui.activities.MainActivity;
import org.hackillinois.ui.fragments.login.LoginInputFragment;
import org.hackillinois.ui.fragments.login.LoginMainFragment;
import org.hackillinois.ui.fragments.main.SchoolProfileFragment;
import org.hackillinois.ui.fragments.main.UserProfileFragment;

/**
 * Created by Stephen on 2/10/2015.
 */
@Module(complete = false,
        injects = {
        LoginInputFragment.class,
        LoginMainFragment.class,
        MainActivity.class,
        SchoolProfileFragment.class,
        UserProfileFragment.class
})
public class LoginModule {

    @Provides @Singleton
    public SessionManager provideSessionManager(Application app, Gson gson) {
        return new SessionManager(app, gson);
    }
}
