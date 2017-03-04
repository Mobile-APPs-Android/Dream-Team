package com.example.dreamteam.beergram;

import android.app.Application;

import com.example.dreamteam.beergram.data.DaggerRepositoryComponent;
import com.example.dreamteam.beergram.data.RepositoryComponent;
import com.example.leagueofthetwo.babuu.data.DaggerRepositoryComponent;

public class BeergramApplication extends Application {

    RepositoryComponent mComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mComponent = DaggerRepositoryComponent
                .builder()
                .beergramApplicationModule(new BeergramApplicationModule(this))
                .build();
    }

    public RepositoryComponent getRepositoryComponent() {
        return mComponent;
    }
}
