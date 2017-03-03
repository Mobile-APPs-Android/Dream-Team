package com.example.leagueofthetwo.babuu.auth.login;

import com.example.leagueofthetwo.babuu.data.IRepository;
import com.example.leagueofthetwo.babuu.data.RepositoryComponent;
import com.example.leagueofthetwo.babuu.utils.FragmentScoped;

import javax.inject.Singleton;

import dagger.Component;

@FragmentScoped
@Component(dependencies = RepositoryComponent.class, modules = LoginModule.class)
public interface LoginComponent {
    void inject(LoginActivity activity);
}
