package com.example.dreamteam.beergram.newsfeed;

import dagger.Module;
import dagger.Provides;

@Module
public class NewsfeedModule {

    private final NewsfeedContract.View view;

    public NewsfeedModule(NewsfeedContract.View view){
        this.view = view;
    }

    @Provides
    NewsfeedContract.View provideNewsfeedView(){
        return this.view;
    }
}