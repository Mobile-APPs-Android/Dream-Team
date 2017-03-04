package com.example.dreamteam.beergram.newsfeed;

import com.example.dreamteam.beergram.auth.login.LoginContract;

import dagger.Module;
import dagger.Provides;

@Module
public class NewsfeedModule {

    private final LoginContract.View mView;

    public NewsfeedModule(LoginContract.View view){
        mView = view;
    }

    @Provides
    LoginContract.View provideNewsfeedView(){
        return mView;
    }
}