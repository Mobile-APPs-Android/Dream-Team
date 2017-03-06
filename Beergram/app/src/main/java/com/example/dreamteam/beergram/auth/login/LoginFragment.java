package com.example.dreamteam.beergram.auth.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dreamteam.beergram.R;
import com.example.dreamteam.beergram.auth.logout.LogoutActivity;
import com.example.dreamteam.beergram.auth.register.RegisterActivity;
import com.example.dreamteam.beergram.camera.CameraActivity;
import com.example.dreamteam.beergram.newsfeed.NewsfeedActivity;
import com.example.dreamteam.beergram.profile.ProfileActivity;
import com.example.dreamteam.beergram.utils.BeergramProgressDialog;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment implements LoginContract.View {

    private View root;
    private LoginContract.Presenter presenter;

    private Button mBtnLogin;
    private EditText etEmail, etPassword;
    private TextView tvCreateAccount, tvResetPassword;
    private Context context;

    private BeergramProgressDialog dialog;

    public LoginFragment() {
    }

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.root = inflater.inflate(R.layout.fragment_login, container, false);
        mBtnLogin = (Button) this.root.findViewById(R.id.btn_login);

        this.etEmail = (EditText) this.root.findViewById(R.id.et_email);
        this.etPassword = (EditText) this.root.findViewById(R.id.et_password);

        this.tvCreateAccount = (TextView) this.root.findViewById(R.id.tv_no_account);
        this.tvResetPassword = (TextView) this.root.findViewById(R.id.tv_reset_password);

        mBtnLogin.setOnClickListener(v -> {
            String email = String.valueOf(this.etEmail.getText());
            String password = String.valueOf(this.etPassword.getText());
            this.presenter.loginUser(email, password);
        });

        this.tvCreateAccount.setOnClickListener(v -> this.presenter.onCreateAccount());

        this.tvResetPassword.setOnClickListener(v -> this.presenter.onResetPassword());

        return this.root;
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
    public void setPresenter(LoginContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void setDialog(BeergramProgressDialog progressDialog) {
        this.dialog = progressDialog;
    }

    @Override
    public void showRegisterActivity() {
        Intent intent = new Intent(this.context, RegisterActivity.class);

        startActivity(intent);
    }

    @Override
    public void showResetPasswordActivity() {
        Intent intent = new Intent(this.context, RegisterActivity.class); // TODO change activity with ResetPasswordActivity

        startActivity(intent);
    }

    @Override
    public void showHomeActivity() {
        Intent intent = new Intent(this.context, ProfileActivity.class);

        getActivity().finish();

        startActivity(intent);
    }

    @Override
    public void showLogoutActivity() {
        Intent intet = new Intent(this.context, LogoutActivity.class); // TODO change activity with Logout Activity

        getActivity().finish();
        startActivity(intet);
    }

    @Override
    public void notifyLoggedInUser(String username) {
        Toast.makeText(this.context, username + getString(R.string.user_loggedin_notify_message), Toast.LENGTH_SHORT)
                .show();
    }

    @Override
    public void notoifyBadEmailOrPassword() {
        Toast.makeText(this.context, R.string.bad_credentials_message_text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showDialogForLoggingUser() {
        this.dialog.showProgress(getString(R.string.loading_logging_in_text));
    }

    @Override
    public void showDialogForLoading() {
        this.dialog.showProgress(getString(R.string.loading_text));
    }

    @Override
    public void dismissDialog() {
        this.dialog.dismissProgress();
    }
}
