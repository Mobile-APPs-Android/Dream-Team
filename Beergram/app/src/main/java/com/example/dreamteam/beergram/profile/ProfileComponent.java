package com.example.dreamteam.beergram.profile;

import com.example.dreamteam.beergram.data.RepositoryComponent;

import com.example.dreamteam.beergram.utils.FragmentScoped;

import dagger.Component;

@FragmentScoped
@Component(dependencies = RepositoryComponent.class, modules = ProfileModule.class)
public interface ProfileComponent {
    void inject(ProfileActivity activity);
}