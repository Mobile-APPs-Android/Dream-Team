package com.example.dreamteam.beergram;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

@Module
public class BeergramApplicationModule {

    private final Context context;

    public BeergramApplicationModule(Context context){
        this.context = context;
    }

    @Provides
    Context provideAppContext(){
        return this.context;
    }
}
