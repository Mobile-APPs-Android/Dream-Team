package com.example.leagueofthetwo.babuu;

import android.content.Context;
import android.content.ContextWrapper;

import dagger.Module;
import dagger.Provides;

@Module
public class BabuuApplicationModule {

    private final Context mContext;

    public BabuuApplicationModule(Context context){
        mContext = context;
    }

    @Provides
    Context provideAppContext(){
        return mContext;
    }
}
