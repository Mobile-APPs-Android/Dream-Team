package com.example.dreamteam.beergram.auth.logout;

import dagger.Module;
import dagger.Provides;

@Module
public class LogoutModule {

    private final LogoutContract.View mView;

    public LogoutModule(LogoutContract.View view) {
        mView = view;
    }

    @Provides
    LogoutContract.View provideLogoutView() {
        return mView;
    }
}
