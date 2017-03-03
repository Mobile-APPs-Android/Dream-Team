package com.example.leagueofthetwo.babuu.auth.login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.leagueofthetwo.babuu.BabuuApplication;
import com.example.leagueofthetwo.babuu.R;
import com.example.leagueofthetwo.babuu.utils.BabuuProgressDialog;

import javax.inject.Inject;

public class LoginActivity extends AppCompatActivity {

    @Inject
    LoginPresenter mPresenter;

    @Inject
    BabuuProgressDialog mDialog;

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
                .repositoryComponent(((BabuuApplication)getApplication())
                        .getRepositoryComponent())
                .loginModule(new LoginModule(loginFragment))
                .build()
                .inject(this);

        mDialog.setContext(this);
        loginFragment.setDialog(mDialog);
    }
}
