package com.example.dreamteam.beergram.auth.logout;

import dagger.Module;
import dagger.Provides;

@Module
public class LogoutModule {

    private final LogoutContract.View view;

    public LogoutModule(LogoutContract.View view) {
        this.view = view;
    }

    @Provides
    LogoutContract.View provideLogoutView() {
        return this.view;
    }
}
