package com.example.leagueofthetwo.babuu.auth.register;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.leagueofthetwo.babuu.BabuuApplication;
import com.example.leagueofthetwo.babuu.R;
import com.example.leagueofthetwo.babuu.utils.BabuuProgressDialog;

import javax.inject.Inject;

public class RegisterActivity extends AppCompatActivity {

    @Inject
    RegisterPresenter mPresenter;

    @Inject
    BabuuProgressDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        RegisterFragment registerFragment =
                (RegisterFragment) getSupportFragmentManager()
                        .findFragmentById(R.id.fragment_container);

        if (registerFragment == null) {
            registerFragment = RegisterFragment.newInstance();

            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_container, registerFragment)
                    .commit();
        }

        DaggerRegisterComponent
                .builder()
                .repositoryComponent(((BabuuApplication) getApplication()).getRepositoryComponent())
                .registerModule(new RegisterModule(registerFragment))
                .build()
                .inject(this);

        mDialog.setContext(this);
        registerFragment.setDialog(mDialog);
    }
}
