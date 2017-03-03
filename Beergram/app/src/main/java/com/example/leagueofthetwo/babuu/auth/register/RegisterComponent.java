package com.example.leagueofthetwo.babuu.auth.register;

import com.example.leagueofthetwo.babuu.data.RepositoryComponent;
import com.example.leagueofthetwo.babuu.utils.FragmentScoped;

import dagger.Component;

@FragmentScoped
@Component(dependencies = RepositoryComponent.class, modules = RegisterModule.class)
public interface RegisterComponent {
    void inject(RegisterActivity activity);
}
