package com.example.leagueofthetwo.babuu.auth.login;

import dagger.Module;
import dagger.Provides;

@Module
public class LoginModule {

    private final LoginContract.View mView;

    public LoginModule(LoginContract.View view){
        mView = view;
    }

    @Provides
    LoginContract.View provideLoginView(){
        return mView;
    }
}
