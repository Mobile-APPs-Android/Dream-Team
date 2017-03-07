package com.example.dreamteam.beergram.profile;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dreamteam.beergram.R;
import com.example.dreamteam.beergram.auth.login.LoginActivity;
import com.example.dreamteam.beergram.utils.BeergramProgressDialog;

public class ProfileFragment extends Fragment implements ProfileContract.View {
    private View root;
    private ProfileContract.Presenter presenter;
    private Context context;
    private BeergramProgressDialog dialog;

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

        Button btnLogout = (Button) this.root.findViewById(R.id.profile_btn_logout);

        btnLogout.setOnClickListener(v -> {
            this.presenter.onBtnLogout();
        });

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

    @Override
    public void setDialog(BeergramProgressDialog dialog) {
        this.dialog = dialog;
    }

    @Override
    public void showDialogLoggingOut() {
        this.dialog.showProgress(getString(R.string.loading_logging_out_text));
    }

    @Override
    public void notifyUserLogout() {
        Toast.makeText(this.context, "User logged out", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void dismissDialog() {
        this.dialog.dismissProgress();
    }

    @Override
    public void showLoginActivity() {
        Intent intet = new Intent(this.context, LoginActivity.class);

        getActivity().finish();
        startActivity(intet);
    }
}