package org.hackillinois.ui.fragments.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
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
 * Created by Stephen on 2/10/2015.
 */
public class SchoolProfileFragment extends Fragment {

    @Inject
    SessionManager sessionManager;

    @InjectView(R.id.school_name)
    TextView schoolName;

    @InjectView(R.id.school_contact_name)
    TextView contactName;

    @InjectView(R.id.school_contact_email)
    TextView contactEmail;

    @InjectView(R.id.school_contact_phone)
    TextView contactPhone;

    public static SchoolProfileFragment newInstance() {
        return new SchoolProfileFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile_school, container, false);
        ButterKnife.inject(this, view);
        HackIllinoisApplication app = (HackIllinoisApplication) this.getActivity().getApplication();
        app.inject(this);
        User user = sessionManager.getUser();
        schoolName.setText(user.getSchool());
        contactName.setText(user.getContactName());
        contactEmail.setText(user.getContactEmail());
        contactPhone.setText(user.getContactPhoneNumber());
        return view;
    }
}
