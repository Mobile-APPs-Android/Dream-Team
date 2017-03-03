package com.example.prot3.beergram.auth.login;

import dagger.Provides;

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
