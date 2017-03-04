package com.example.dreamteam.beergram.auth.register;

import com.example.dreamteam.beergram.data.RepositoryComponent;
import com.example.dreamteam.beergram.utils.FragmentScoped;

import dagger.Component;

@FragmentScoped
@Component(dependencies = RepositoryComponent.class, modules = RegisterModule.class)
public interface RegisterComponent {
    void inject(RegisterActivity activity);
}
