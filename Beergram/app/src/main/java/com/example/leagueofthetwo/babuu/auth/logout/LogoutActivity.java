package com.example.leagueofthetwo.babuu.auth.logout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.leagueofthetwo.babuu.BabuuApplication;
import com.example.leagueofthetwo.babuu.R;
import com.example.leagueofthetwo.babuu.utils.BabuuProgressDialog;

import javax.inject.Inject;

public class LogoutActivity extends AppCompatActivity {

    @Inject
    LogoutPresenter mPresneter;

    @Inject
    BabuuProgressDialog mDialog;

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
                .repositoryComponent(((BabuuApplication) getApplication()).getRepositoryComponent())
                .logoutModule(new LogoutModule(logoutFragment))
                .build()
                .inject(this);

        mDialog.setContext(this);
        logoutFragment.setDialog(mDialog);
    }
}
