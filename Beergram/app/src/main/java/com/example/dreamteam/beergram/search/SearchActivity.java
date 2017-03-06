package com.example.dreamteam.beergram.search;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.dreamteam.beergram.BeergramApplication;
import com.example.dreamteam.beergram.R;
import com.example.dreamteam.beergram.profile.DaggerProfileComponent;

import javax.inject.Inject;

public class SearchActivity extends AppCompatActivity {

    @Inject
    SearchPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        SearchFragment searchFragment =
                (SearchFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);

        if (searchFragment == null) {
            searchFragment = SearchFragment.newInstance();

            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_container, searchFragment)
                    .commit();
        }

        DaggerSearchComponent
                .builder()
                .repositoryComponent(((BeergramApplication)getApplication())
                .getRepositoryComponent())
                .searchModule(new SearchModule(searchFragment))
                .build()
                .inject(this);
    }
}