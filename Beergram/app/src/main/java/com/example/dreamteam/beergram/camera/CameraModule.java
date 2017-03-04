package com.example.dreamteam.beergram.camera;

import dagger.Module;
import dagger.Provides;

@Module
public class CameraModule {
    private final CameraContract.View view;

    public CameraModule(CameraContract.View view){
        this.view = view;
    }

    @Provides
    CameraContract.View provideCameraView(){
        return this.view;
    }
}
