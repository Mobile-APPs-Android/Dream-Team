package com.example.dreamteam.beergram.aboutapp;

import dagger.Module;
import dagger.Provides;

@Module
public class AboutAppModule {
    private final AboutAppContract.View view;

    public AboutAppModule(AboutAppContract.View view){
        this.view = view;
    }

    @Provides
    AboutAppContract.View provideCameraView(){
        return this.view;
    }
}