package org.hackillinois.ui.fragments.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.OnClick;
import org.hackillinois.HackIllinoisApplication;
import org.hackillinois.R;
import org.hackillinois.data.login.SessionManager;
import org.hackillinois.ui.activities.LoginActivity;
import org.hackillinois.ui.activities.MainActivity;

/**
 * Created by Stephen on 2/10/2015.
 */
public class LoginMainFragment extends Fragment {

    @Inject
    SessionManager sessionManager;

    public static LoginMainFragment newInstance() {
        return new LoginMainFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login_main, container, false);
        ButterKnife.inject(this, view);
        HackIllinoisApplication app = (HackIllinoisApplication) this.getActivity().getApplication();
        app.inject(this);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        LoginActivity activity = (LoginActivity) this.getActivity();
        activity.showToolbar(false);
    }


    @OnClick(R.id.login_main_button)
    void navigateToLoginInput() {
        this.getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.login_container, LoginInputFragment.newInstance())
                .addToBackStack("login_main")
                .commit();
    }

    @OnClick(R.id.skip_login_button)
    void skipLogin() {
        sessionManager.skipLogin();
        Intent mainIntent = new Intent(this.getActivity(), MainActivity.class);
        startActivity(mainIntent);
        this.getActivity().finish();
    }
}
