package com.example.dreamteam.beergram.auth.logout;

import com.example.dreamteam.beergram.data.RepositoryComponent;
import com.example.dreamteam.beergram.utils.FragmentScoped;

import dagger.Component;

@FragmentScoped
@Component(dependencies = RepositoryComponent.class, modules = LogoutModule.class)
public interface LogoutComponent {
    void inject(LogoutActivity activity);
}
