package com.example.dreamteam.beergram.aboutapp;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.dreamteam.beergram.BeergramApplication;
import com.example.dreamteam.beergram.R;

import javax.inject.Inject;

public class AboutAppActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_app);

        AboutAppFragment aboutAppFragment =
                (AboutAppFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);

        if (aboutAppFragment == null) {
            aboutAppFragment = AboutAppFragment.newInstance();

            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_container, aboutAppFragment)
                    .commit();
        }

        DaggerAboutAppComponent
                .builder()
                .repositoryComponent(((BeergramApplication)getApplication())
                        .getRepositoryComponent())
                .aboutAppModule(new AboutAppModule(aboutAppFragment))
                .build()
                .inject(this);
    }
}
