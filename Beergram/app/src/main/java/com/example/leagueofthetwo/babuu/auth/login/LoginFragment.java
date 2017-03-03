package com.example.leagueofthetwo.babuu.auth.login;

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

import com.example.leagueofthetwo.babuu.R;
import com.example.leagueofthetwo.babuu.auth.logout.LogoutActivity;
import com.example.leagueofthetwo.babuu.auth.register.RegisterActivity;
import com.example.leagueofthetwo.babuu.utils.BabuuProgressDialog;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment implements LoginContract.View {

    private View mRootView;
    private LoginContract.Presenter mPresenter;

    private Button mBtnLogin;
    private EditText mEtEmail, mEtPassword;
    private TextView mTvCreateAccount, mTvResetPassword;
    private Context mContext;

    private BabuuProgressDialog mDialog;

    public LoginFragment() {
        // Required empty public constructor
    }

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_login, container, false);

        mBtnLogin = (Button) mRootView.findViewById(R.id.btn_login);

        mEtEmail = (EditText) mRootView.findViewById(R.id.et_email);
        mEtPassword = (EditText) mRootView.findViewById(R.id.et_password);

        mTvCreateAccount = (TextView) mRootView.findViewById(R.id.tv_no_account);
        mTvResetPassword = (TextView) mRootView.findViewById(R.id.tv_reset_password);

        mBtnLogin.setOnClickListener(v -> {
            String email = String.valueOf(mEtEmail.getText());
            String password = String.valueOf(mEtPassword.getText());
            mPresenter.loginUser(email, password);

        });

        mTvCreateAccount.setOnClickListener(v -> mPresenter.onCreateAccount());

        mTvResetPassword.setOnClickListener(v -> mPresenter.onResetPassword());

        return mRootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        mContext = context;
    }

    @Override
    public void onResume() {
        super.onResume();

        mPresenter.start();
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void setDialog(BabuuProgressDialog progressDialog) {
        mDialog = progressDialog;
    }

    @Override
    public void showRegisterActivity() {
        Intent intent = new Intent(mContext, RegisterActivity.class);

        startActivity(intent);
    }

    @Override
    public void showResetPasswordActivity() {
        Intent intent = new Intent(mContext, RegisterActivity.class); // TODO change activity with ResetPasswordActivity

        startActivity(intent);
    }

    @Override
    public void showHomeActivity() {
        Intent intent = new Intent(mContext, RegisterActivity.class);

        getActivity().finish();

        startActivity(intent);
    }

    @Override
    public void showLogoutActivity() {
        Intent intet = new Intent(mContext, LogoutActivity.class); // TODO change activity with Logout Activity

        getActivity().finish();
        startActivity(intet);
    }

    @Override
    public void notifyLoggedInUser(String username) {
        Toast.makeText(mContext, username + getString(R.string.user_loggedin_notify_message), Toast.LENGTH_SHORT)
                .show();
    }

    @Override
    public void notoifyBadEmailOrPassword() {
        Toast.makeText(mContext, R.string.bad_credentials_message_text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showDialogForLoggingUser() {
        mDialog.showProgress(getString(R.string.loading_logging_in_text));
    }

    @Override
    public void showDialogForLoading() {
        mDialog.showProgress(getString(R.string.loading_text));
    }

    @Override
    public void dismissDialog() {
        mDialog.dismissProgress();
    }
}
