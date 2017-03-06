package com.example.dreamteam.beergram.auth.login;

import com.example.dreamteam.beergram.data.RepositoryComponent;

import com.example.dreamteam.beergram.utils.FragmentScoped;
import dagger.Component;

@FragmentScoped
@Component(dependencies = RepositoryComponent.class, modules = LoginModule.class)
public interface LoginComponent {
    void inject(LoginActivity activity);
}
