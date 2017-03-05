package com.example.dreamteam.beergram.data;

import android.app.Application;

import com.example.dreamteam.beergram.BeergramApplicationModule;
import com.example.dreamteam.beergram.data.storage.StorageRepository;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        RepositoryModule.class,
        FirebaseRepositoryModule.class,
        BeergramApplicationModule.class})
public interface RepositoryComponent {
    void inject(Application application);

    IRepository getRepository();
}
