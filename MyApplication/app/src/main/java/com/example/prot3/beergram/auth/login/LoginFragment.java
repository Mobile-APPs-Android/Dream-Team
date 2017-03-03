package com.example.prot3.beergram.auth.login;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.prot3.beergram.R;


public class LoginFragment extends Fragment {
    private View rootView;
    private EditText editTextPassword;
    private EditText editTextEmail;
    private Button btn_login;
    private Context context;
    private LoginPresenter presenter;

    public LoginFragment() {
    }

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.rootView = inflater.inflate(R.layout.fragment_login, container, false);

        this.btn_login = (Button) this.rootView.findViewById(R.id.btn_login);

        this.editTextEmail = (EditText) this.rootView.findViewById(R.id.edit_text_email);
        this.editTextPassword = (EditText) this.rootView.findViewById(R.id.edit_text_password);

        this.btn_login.setOnClickListener(view -> {
            String email = String.valueOf(this.editTextEmail.getText());
            String password = String.valueOf(this.editTextPassword.getText());
            this.presenter.loginUser(email, password);
        });

        return rootView;
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

}
