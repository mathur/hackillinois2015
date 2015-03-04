package org.hackillinois.ui.fragments.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import org.hackillinois.HackIllinoisApplication;
import org.hackillinois.R;
import org.hackillinois.data.login.SessionManager;
import org.hackillinois.data.model.User;

/**
 * Created by Stephen on 1/27/2015.
 */
public class UserProfileFragment extends Fragment {

    private static final String TAG = "UserProfileFragment";

    @Inject
    SessionManager sessionManager;

    @InjectView(R.id.profile_name)
    TextView name;

    @InjectView(R.id.profile_email)
    TextView email;

    @InjectView(R.id.profile_phone_number)
    TextView phoneNumber;


    public static UserProfileFragment newInstance() {
        return new UserProfileFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile_personal, container, false);
        ButterKnife.inject(this, view);
        HackIllinoisApplication app = (HackIllinoisApplication) this.getActivity().getApplication();
        app.inject(this);
        User user = sessionManager.getUser();
        name.setText(user.getName());
        email.setText(user.getEmail());
        phoneNumber.setText(user.getPhoneNumber());
        Log.d(TAG, "User profile created");
        return view;
    }
}
