package com.example.leagueofthetwo.babuu;

import android.app.Application;

import com.example.leagueofthetwo.babuu.data.DaggerRepositoryComponent;
import com.example.leagueofthetwo.babuu.data.RepositoryComponent;

public class BabuuApplication extends Application {

    RepositoryComponent mComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mComponent = DaggerRepositoryComponent
                .builder()
                .babuuApplicationModule(new BabuuApplicationModule(this))
                .build();
    }

    public RepositoryComponent getRepositoryComponent() {
        return mComponent;
    }
}
