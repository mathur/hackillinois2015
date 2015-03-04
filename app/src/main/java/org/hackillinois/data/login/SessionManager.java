package org.hackillinois.data.login;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;

import org.hackillinois.data.model.User;

/**
 * This class manages a user login by storing a user account on login, and persisting it through
 * SharedPrefs
 */
public class SessionManager {

    private static final String TAG = "SessionManager";
    private static final String ACCOUNT_PREFS = "account_prefs";
    private static final String ACCOUNT = "account";

    private Gson gson;
    private SharedPreferences prefs;

    private User user;
    private boolean skippedLogin = false;

    public SessionManager(Context context, Gson gson) {
        prefs = context.getSharedPreferences(ACCOUNT_PREFS, Context.MODE_PRIVATE);
        this.gson = gson;
    }

    public User getUser() {
        if(user == null) {
            String userJson = prefs.getString(ACCOUNT, null);
            user = gson.fromJson(userJson, User.class);
        }
        return user;
    }

    public boolean needsLogin() {
        return getUser() != null || skippedLogin;
    }

    public void skipLogin() {
        skippedLogin = true;
    }

    public void storeUser(User user) {
        this.user = user;
        String userJson = gson.toJson(user);
        prefs.edit()
                .putString(ACCOUNT, userJson)
                .apply();
    }
}
