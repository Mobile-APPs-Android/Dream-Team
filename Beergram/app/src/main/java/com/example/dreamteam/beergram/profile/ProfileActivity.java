package com.example.dreamteam.beergram.profile;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.dreamteam.beergram.BeergramApplication;
import com.example.dreamteam.beergram.R;

import javax.inject.Inject;

public class ProfileActivity extends AppCompatActivity {

    @Inject
    ProfilePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        ProfileFragment profileFragment =
                (ProfileFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);

        if (profileFragment == null) {
            profileFragment = ProfileFragment.newInstance();

            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_container, profileFragment)
                    .commit();
        }

        DaggerProfileComponent
                .builder()
                .repositoryComponent(((BeergramApplication)getApplication())
                        .getRepositoryComponent())
                .profileModule(new ProfileModule(profileFragment))
                .build()
                .inject(this);
    }
}