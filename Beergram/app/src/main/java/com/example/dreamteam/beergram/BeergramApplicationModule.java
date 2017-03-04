package com.example.dreamteam.beergram;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

@Module
public class BeergramApplicationModule {

    private final Context mContext;

    public BeergramApplicationModule(Context context){
        mContext = context;
    }

    @Provides
    Context provideAppContext(){
        return mContext;
    }
}
