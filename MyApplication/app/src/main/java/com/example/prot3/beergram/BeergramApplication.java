package com.example.prot3.beergram;

import android.app.Application;

import com.example.prot3.beergram.data.RepositoryComponent;

public class BeergramApplication extends Application {

    RepositoryComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        this.component = DaggerRepositoryComponent
                .builder()
                .babuuApplicationModule(new BeergramApplicationModule(this))
                .build();

    }
    public RepositoryComponent getRepositoryComponent() {
        return this.component;
    }
}
