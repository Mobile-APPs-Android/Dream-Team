package com.example.dreamteam.beergram.auth.register;

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
import com.example.dreamteam.beergram.auth.login.LoginActivity;
import com.example.dreamteam.beergram.utils.BeergramProgressDialog;

public class RegisterFragment extends Fragment implements RegisterContract.View {

    public static final String USER_ID = "userId";

    private View mRootView;

    private RegisterContract.Presenter mPresenter;
    private BeergramProgressDialog mDialog;

    Button mBtnRegister;
    EditText mEtEmail, mEtPassword, mEtConfirmPassword, mEtFirstName, mEtLastName, mEtAddress;
    TextView mTvHasAccount;
    private Context mContext;

    public RegisterFragment() {
        // Required empty public constructor
    }

    public static RegisterFragment newInstance() {
        return new RegisterFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_register, container, false);

        mBtnRegister = (Button) mRootView.findViewById(R.id.btn_register);

        mEtEmail = (EditText) mRootView.findViewById(R.id.et_email);
        mEtPassword = (EditText) mRootView.findViewById(R.id.et_password);
        mEtConfirmPassword = (EditText) mRootView.findViewById(R.id.et_confirm_password);
        mEtFirstName = (EditText) mRootView.findViewById(R.id.et_first_name);
        mEtLastName = (EditText) mRootView.findViewById(R.id.et_last_name);
        mEtAddress = (EditText) mRootView.findViewById(R.id.et_address);

        mTvHasAccount = (TextView) mRootView.findViewById(R.id.tv_already_has_account);

        mBtnRegister.setOnClickListener(v -> {
            String email = String.valueOf(mEtEmail.getText());
            String password = String.valueOf(mEtPassword.getText());
            String confirmPassword = String.valueOf(mEtConfirmPassword.getText());
            String firstName = String.valueOf(mEtFirstName.getText());
            String lastName = String.valueOf(mEtLastName.getText());
            String address = String.valueOf(mEtAddress.getText());

            mPresenter.onRegister(email, password, confirmPassword, firstName, lastName, address);
        });

        mTvHasAccount.setOnClickListener(v -> {
            mPresenter.onHasAccount();
        });


        return mRootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        mContext = context;
    }

    @Override
    public void setPresenter(RegisterContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void setDialog(BeergramProgressDialog dialog) {
        mDialog = dialog;
    }

    @Override
    public void showDialogForCreatingUser() {
        mDialog.showProgress(getString(R.string.loading_creating_user_text));
    }

    @Override
    public void dismissDialog() {
        mDialog.dismissProgress();
    }

    @Override
    public void showLoginActivity() {
        getActivity().finish();
    }

    @Override
    public void notifyCreatedUser(String username) {
        Toast.makeText(mContext, username + getString(R.string.user_created_notify_message), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showHomeActivity() {
        Intent accountIntent = new Intent(mContext, LoginActivity.class);

        getActivity().finish();

        startActivity(accountIntent);
    }

    @Override
    public void showBadEmailError() {
        mEtEmail.setError(getString(R.string.bad_email_text));
    }

    @Override
    public void showBadFirstNameError() {
        mEtFirstName.setError(getString(R.string.bad_first_name_text));
    }

    @Override
    public void showBadLastNameError() {
        mEtLastName.setError(getString(R.string.bad_last_name_text));
    }

    @Override
    public void showBadConfirmPasswordError() {
        mEtConfirmPassword.setError(getString(R.string.bad_confirm_password_text));
    }

    @Override
    public void showBadPasswordError() {
        mEtPassword.setError(getString(R.string.bad_password_text));
    }
}
