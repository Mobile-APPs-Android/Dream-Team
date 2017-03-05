package com.example.dreamteam.beergram.newsfeed;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.dreamteam.beergram.BeergramApplication;
import com.example.dreamteam.beergram.R;

import javax.inject.Inject;

public class NewsfeedActivity extends AppCompatActivity {

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
}