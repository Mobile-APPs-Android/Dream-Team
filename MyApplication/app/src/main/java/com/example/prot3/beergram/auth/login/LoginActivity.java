package com.example.prot3.beergram.auth.login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.example.prot3.beergram.R;

public class LoginActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        LoginFragment loginFragment =
                (LoginFragment) getSupportFragmentManager().findFragmentById(R.id.login_contaier);

        if (loginFragment == null) {
            loginFragment = LoginFragment.newInstance();

            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.login_contaier, loginFragment).commit();
        }
    }
}

