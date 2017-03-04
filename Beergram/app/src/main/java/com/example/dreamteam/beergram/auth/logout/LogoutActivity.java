package com.example.dreamteam.beergram.auth.logout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.dreamteam.beergram.BeergramApplication;
import com.example.dreamteam.beergram.R;
import com.example.dreamteam.beergram.utils.BeergramProgressDialog;

import javax.inject.Inject;

public class LogoutActivity extends AppCompatActivity {

    @Inject
    LogoutPresenter mPresneter;

    @Inject
    BeergramProgressDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logout);

        LogoutFragment logoutFragment = (LogoutFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);

        if (logoutFragment == null) {
            logoutFragment = LogoutFragment.newInstance();

            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_container, logoutFragment)
                    .commit();
        }

        DaggerLogoutComponent
                .builder()
                .repositoryComponent(((BeergramApplication) getApplication()).getRepositoryComponent())
                .logoutModule(new LogoutModule(logoutFragment))
                .build()
                .inject(this);

        mDialog.setContext(this);
        logoutFragment.setDialog(mDialog);
    }
}
