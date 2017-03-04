package com.example.dreamteam.beergram.auth.login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.dreamteam.beergram.BeergramApplication;
import com.example.dreamteam.beergram.R;
import com.example.dreamteam.beergram.utils.BeergramProgressDialog;

import javax.inject.Inject;

public class LoginActivity extends AppCompatActivity {

    @Inject
    LoginPresenter mPresenter;

    @Inject
    BeergramProgressDialog mDialog;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        LoginFragment loginFragment =
                (LoginFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);

        if (loginFragment == null) {
            loginFragment = LoginFragment.newInstance();

            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_container, loginFragment).commit();
        }

        DaggerLoginComponent
                .builder()
                .repositoryComponent(((BeergramApplication)getApplication())
                        .getRepositoryComponent())
                .loginModule(new LoginModule(loginFragment))
                .build()
                .inject(this);

        mDialog.setContext(this);
        loginFragment.setDialog(mDialog);
    }
}
