package com.example.dreamteam.beergram.profile;

import dagger.Module;
import dagger.Provides;

@Module
public class ProfileModule {

    private final ProfileContract.View view;

    public ProfileModule(ProfileContract.View view){
        this.view = view;
    }

    @Provides
    ProfileContract.View provideProfileView(){
        return this.view;
    }
}