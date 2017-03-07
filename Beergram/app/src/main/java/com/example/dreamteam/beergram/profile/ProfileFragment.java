package com.example.dreamteam.beergram.profile;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dreamteam.beergram.R;
import com.example.dreamteam.beergram.auth.logout.LogoutActivity;

public class ProfileFragment extends Fragment implements ProfileContract.View {
    private View root;
    private ProfileContract.Presenter presenter;
    private Context context;

    public ProfileFragment() {
    }

    public static ProfileFragment newInstance() {
        return new ProfileFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.root = inflater.inflate(R.layout.fragment_profile, container, false);

        TextView tvEmail = (TextView) this.root.findViewById(R.id.tv_email_value);
        TextView tvFirstName = (TextView) this.root.findViewById(R.id.tv_firstname_value);
        TextView tvLastName= (TextView) this.root.findViewById(R.id.tv_lastname_value);
        TextView tvAdress = (TextView) this.root.findViewById(R.id.tv_address_value);

        this.presenter.setProfileValues(tvEmail, tvFirstName, tvLastName, tvAdress);
        return this.root;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        this.context = context;
    }

    @Override
    public void setPresenter(ProfileContract.Presenter presenter) {
        this.presenter = presenter;
    }

//    @Override
//    public void showLogoutActivity() {
//        Intent intet = new Intent(this.context, LogoutActivity.class);
//
//        getActivity().finish();
//        startActivity(intet);
//    }
}