package com.example.dreamteam.beergram.profile;

import android.app.Activity;
import android.os.Bundle;

import com.example.dreamteam.beergram.R;

public class ProfileActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
    }

    /*
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

        DaggerCameraComponent
                .builder()
                .repositoryComponent(((BeergramApplication)getApplication())
                        .getRepositoryComponent())
                .profileModule(new ProfileModule(profileFragment))
                .build()
    }
     */
}
