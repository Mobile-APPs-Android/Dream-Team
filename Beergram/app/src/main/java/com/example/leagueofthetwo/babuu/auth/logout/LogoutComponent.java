package com.example.leagueofthetwo.babuu.auth.logout;

import com.example.leagueofthetwo.babuu.data.RepositoryComponent;
import com.example.leagueofthetwo.babuu.utils.FragmentScoped;

import dagger.Component;

@FragmentScoped
@Component(dependencies = RepositoryComponent.class, modules = LogoutModule.class)
public interface LogoutComponent {
    void inject(LogoutActivity activity);
}
