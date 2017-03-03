package com.example.leagueofthetwo.babuu.auth.register;

import dagger.Module;
import dagger.Provides;

@Module
public class RegisterModule {

    private final RegisterContract.View mView;

    public RegisterModule(RegisterContract.View view) {
        mView = view;
    }

    @Provides
    RegisterContract.View provideRegisteVier() {
        return mView;
    }
}
