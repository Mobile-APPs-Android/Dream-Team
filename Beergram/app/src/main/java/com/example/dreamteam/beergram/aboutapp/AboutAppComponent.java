package com.example.dreamteam.beergram.aboutapp;

import com.example.dreamteam.beergram.data.RepositoryComponent;
import com.example.dreamteam.beergram.utils.FragmentScoped;

import dagger.Component;

@FragmentScoped
@Component(dependencies = RepositoryComponent.class, modules = AboutAppModule.class)
public interface AboutAppComponent {
    void inject(AboutAppActivity activity);
}