package com.example.dreamteam.beergram.auth.logout;


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


public class LogoutFragment extends Fragment implements LogoutContract.View {

    private Button btnLogout;
    private TextView tvUserNames;

    private LogoutContract.Presenter presenter;
    private BeergramProgressDialog dialog;
    private Context context;

    public LogoutFragment() {
    }

    public static LogoutFragment newInstance() {
        return new LogoutFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mRootView = inflater.inflate(R.layout.fragment_logout, container, false);

        btnLogout = (Button) mRootView.findViewById(R.id.btn_logout);
        tvUserNames = (TextView) mRootView.findViewById(R.id.tv_user_names);

        btnLogout.setOnClickListener(v -> this.presenter.onBtnLogout());

        return mRootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        this.context = context;
    }

    @Override
    public void onResume() {
        super.onResume();

        this.presenter.start();
    }

    @Override
    public void setPresenter(LogoutContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void setUserNames(String firsName, String lastName) {
        tvUserNames.setText(firsName + " " + lastName);
    }

    @Override
    public void setDialog(BeergramProgressDialog dialog) {
        this.dialog = dialog;
    }

    @Override
    public void showDialogLoading() {
        this.dialog.showProgress(getString(R.string.loading_text));
    }

    @Override
    public void showDialogLoggingOut() {
        this.dialog.showProgress(getString(R.string.loading_logging_out_text));
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

    @Override
    public void notifyUserLogout() {
        Toast.makeText(this.context, "User logged out", Toast.LENGTH_SHORT).show();
    }
}
