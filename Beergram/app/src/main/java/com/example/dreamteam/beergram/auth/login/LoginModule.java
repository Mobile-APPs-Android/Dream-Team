package com.example.dreamteam.beergram.auth.login;

import dagger.Module;
import dagger.Provides;

@Module
public class LoginModule {

    private final LoginContract.View view;

    public LoginModule(LoginContract.View view){
        this.view = view;
    }

    @Provides
    LoginContract.View provideLoginView(){
        return this.view;
    }
}
