package org.hackillinois.ui.fragments.login;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;
import org.hackillinois.HackIllinoisApplication;
import org.hackillinois.R;
import org.hackillinois.data.api.HackIllinoisApi;
import org.hackillinois.data.login.SessionManager;
import org.hackillinois.data.model.Login;
import org.hackillinois.data.model.User;
import org.hackillinois.ui.activities.LoginActivity;
import org.hackillinois.ui.activities.MainActivity;
import org.hackillinois.ui.fragments.main.SchoolProfileFragment;
import org.hackillinois.ui.utils.ViewUtils;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Created by Stephen on 2/10/2015.
 */
public class LoginInputFragment extends Fragment {


    @Inject
    HackIllinoisApi api;

    @Inject
    SessionManager sessionManager;

    @InjectView(R.id.sign_in_notice)
    TextView signInNotice;

    @InjectView(R.id.email)
    EditText emailEdit;

    @InjectView(R.id.password)
    EditText passwordEdit;

    public static LoginInputFragment newInstance() {
        return new LoginInputFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login_input, container, false);
        ButterKnife.inject(this, view);
        ViewUtils.setupUI(view, this.getActivity());
        HackIllinoisApplication app = (HackIllinoisApplication) this.getActivity().getApplication();
        app.inject(this);
        if(this.getActivity() instanceof LoginActivity) {
            signInNotice.setVisibility(View.INVISIBLE);
            LoginActivity activity = (LoginActivity) this.getActivity();
            activity.showToolbar(true);
        }
        return view;
    }

    @OnClick(R.id.login_button)
    void loginOnClick() {

        String email = emailEdit.getText().toString();
        String password = passwordEdit.getText().toString();
        Login login = new Login(email, password);
        api.login(login)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                new Action1<User>() {
                    @Override
                    public void call(User user) {
                        login(user);
                    }
                },
                new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        // TODO: Make this more specific
                        Crouton.makeText(getActivity(), "Login Failed", Style.ALERT).show();
                    }
                });
    }

    @OnClick(R.id.forgot_password_button)
    void forgotPassword() {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.hackillinois.org/password"));
        startActivity(browserIntent);
    }

    protected void login(User user) {
        sessionManager.storeUser(user);
        if(this.getActivity() instanceof LoginActivity) {
            Intent intent = new Intent(this.getActivity(), MainActivity.class);
            startActivity(intent);
            this.getActivity().finish();
        } else if(this.getActivity() instanceof MainActivity) {
            this.getFragmentManager().beginTransaction()
                .replace(R.id.main_container, SchoolProfileFragment.newInstance())
                .commit();
            MainActivity activity = (MainActivity) this.getActivity();
            activity.updateDrawerData(user);
        }
    }
}
