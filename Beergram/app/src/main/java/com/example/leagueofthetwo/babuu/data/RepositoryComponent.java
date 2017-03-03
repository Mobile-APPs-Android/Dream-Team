package com.example.leagueofthetwo.babuu.data;

import android.app.Application;

import com.example.leagueofthetwo.babuu.BabuuApplication;
import com.example.leagueofthetwo.babuu.BabuuApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        RepositoryModule.class,
        FirebaseRepositoryModule.class,
        BabuuApplicationModule.class})
public interface RepositoryComponent {
    void inject(Application application);

    IRepository getRepository();
}
