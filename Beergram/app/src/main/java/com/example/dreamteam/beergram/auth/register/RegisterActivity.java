package com.example.dreamteam.beergram.auth.register;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.dreamteam.beergram.BeergramApplication;
import com.example.dreamteam.beergram.R;
import com.example.dreamteam.beergram.utils.BeergramProgressDialog;

import javax.inject.Inject;

public class RegisterActivity extends AppCompatActivity {

    @Inject
    RegisterPresenter mPresenter;

    @Inject
    BeergramProgressDialog mDialog;

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
                .repositoryComponent(((BeergramApplication) getApplication()).getRepositoryComponent())
                .registerModule(new RegisterModule(registerFragment))
                .build()
                .inject(this);

        mDialog.setContext(this);
        registerFragment.setDialog(mDialog);
    }
}
