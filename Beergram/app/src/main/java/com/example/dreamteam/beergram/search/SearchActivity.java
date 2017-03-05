package com.example.dreamteam.beergram.search;

import android.app.Activity;
import android.os.Bundle;

import com.example.dreamteam.beergram.R;

public class SearchActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
    }
    /*
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

        DaggerCameraComponent
                .builder()
                .repositoryComponent(((BeergramApplication)getApplication())
                        .getRepositoryComponent())
                .searchModule(new SearchModule(searchFragment))
                .build()
    }
     */
}
