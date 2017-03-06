package com.example.dreamteam.beergram.newsfeed;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.dreamteam.beergram.BeergramApplication;
import com.example.dreamteam.beergram.R;

import javax.inject.Inject;

public class NewsfeedActivity extends AppCompatActivity {
    private static final int MY_PERMISSIONS_REQUEST_COARSE = 0;

    @Inject
    NewsfeedPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newsfeed);

        NewsFeedFragment newsFeedFragment =
                (NewsFeedFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);

        if (newsFeedFragment == null) {
            newsFeedFragment = NewsFeedFragment.newInstance();

            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_container, newsFeedFragment)
                    .commit();
        }

        DaggerNewsfeedComponent
                .builder()
                .repositoryComponent(((BeergramApplication)getApplication())
                .getRepositoryComponent())
                .newsfeedModule(new NewsfeedModule(newsFeedFragment))
                .build()
                .inject(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            this.checkForPermission();
        } else {
            this.presenter.onStart();
        }
    }

    private void checkForPermission() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                MY_PERMISSIONS_REQUEST_COARSE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_COARSE:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    this.presenter.onStart();
                } else {
                    setResult(Activity.RESULT_CANCELED, null);
                    finish();
                }
                break;
        }
    }

    @Override
    protected void onStop() {
        this.presenter.onStop();
        super.onStop();
    }
}