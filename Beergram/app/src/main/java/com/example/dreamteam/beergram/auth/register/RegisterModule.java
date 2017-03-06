package com.example.dreamteam.beergram.auth.register;

import dagger.Module;
import dagger.Provides;

@Module
public class RegisterModule {

    private final RegisterContract.View view;

    public RegisterModule(RegisterContract.View view) {
        this.view = view;
    }

    @Provides
    RegisterContract.View provideRegisteVier() {
        return this.view;
    }
}
