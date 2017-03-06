package com.example.dreamteam.beergram;

import android.app.Application;

import com.example.dreamteam.beergram.data.DaggerRepositoryComponent;
import com.example.dreamteam.beergram.data.RepositoryComponent;

public class BeergramApplication extends Application {

    RepositoryComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        this.component = DaggerRepositoryComponent
                .builder()
                .beergramApplicationModule(new BeergramApplicationModule(this))
                .build();
    }

    public RepositoryComponent getRepositoryComponent() {
        return this.component;
    }
}
