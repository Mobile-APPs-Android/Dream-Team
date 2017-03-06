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
import com.example.dreamteam.beergram.camera.CameraActivity;
import com.example.dreamteam.beergram.utils.BeergramProgressDialog;

public class RegisterFragment extends Fragment implements RegisterContract.View {

    private View root;
    private RegisterContract.Presenter presenter;
    private BeergramProgressDialog dialog;

    Button btnRegister;
    EditText etEmail, etPassword, etConfirmPassword, etFirstName, etLastName, etAddress;
    TextView tvHasAccount;
    private Context context;

    public RegisterFragment() {
    }

    public static RegisterFragment newInstance() {
        return new RegisterFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.root = inflater.inflate(R.layout.fragment_register, container, false);

        this.btnRegister = (Button) this.root.findViewById(R.id.btn_register);

        this.etEmail = (EditText) this.root.findViewById(R.id.et_email);
        this.etPassword = (EditText) this.root.findViewById(R.id.et_password);
        this.etConfirmPassword = (EditText) this.root.findViewById(R.id.et_confirm_password);
        this.etFirstName = (EditText) this.root.findViewById(R.id.et_first_name);
        this.etLastName = (EditText) this.root.findViewById(R.id.et_last_name);
        this.etAddress = (EditText) this.root.findViewById(R.id.et_address);

        this.tvHasAccount = (TextView) this.root.findViewById(R.id.tv_already_has_account);

        this.btnRegister.setOnClickListener(v -> {
            String email = String.valueOf(this.etEmail.getText());
            String password = String.valueOf(this.etPassword.getText());
            String confirmPassword = String.valueOf(this.etConfirmPassword.getText());
            String firstName = String.valueOf(this.etFirstName.getText());
            String lastName = String.valueOf(this.etLastName.getText());
            String address = String.valueOf(this.etAddress.getText());

            this.presenter.onRegister(email, password, confirmPassword, firstName, lastName, address);
        });

        this.tvHasAccount.setOnClickListener(v -> {
            this.presenter.onHasAccount();
        });


        return this.root;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        this.context = context;
    }

    @Override
    public void setPresenter(RegisterContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void setDialog(BeergramProgressDialog dialog) {
        this.dialog = dialog;
    }

    @Override
    public void showDialogForCreatingUser() {
        this.dialog.showProgress(getString(R.string.loading_creating_user_text));
    }

    @Override
    public void dismissDialog() {
        this.dialog.dismissProgress();
    }

    @Override
    public void showLoginActivity() {
        getActivity().finish();
    }

    @Override
    public void notifyCreatedUser(String username) {
        Toast.makeText(this.context, username + getString(R.string.user_created_notify_message), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showHomeActivity() {
        Intent accountIntent = new Intent(this.context, CameraActivity.class);

        getActivity().finish();

        startActivity(accountIntent);
    }

    @Override
    public void showBadEmailError() {
        this.etEmail.setError(getString(R.string.bad_email_text));
    }

    @Override
    public void showBadFirstNameError() {
        this.etFirstName.setError(getString(R.string.bad_first_name_text));
    }

    @Override
    public void showBadLastNameError() {
        this.etLastName.setError(getString(R.string.bad_last_name_text));
    }

    @Override
    public void showBadConfirmPasswordError() {
        this.etConfirmPassword.setError(getString(R.string.bad_confirm_password_text));
    }

    @Override
    public void showBadPasswordError() {
        this.etPassword.setError(getString(R.string.bad_password_text));
    }
}
