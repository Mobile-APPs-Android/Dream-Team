package com.example.prot3.beergram.data;

import android.app.Application;

import com.example.prot3.beergram.BeergramApplicationModule;
import com.example.prot3.beergram.data.authprovider.RepositoryModule;

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
