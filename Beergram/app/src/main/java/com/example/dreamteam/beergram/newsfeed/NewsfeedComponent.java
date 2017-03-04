package com.example.dreamteam.beergram.newsfeed;

import com.example.dreamteam.beergram.utils.FragmentScoped;
import com.example.dreamteam.beergram.data.RepositoryComponent;

import javax.inject.Singleton;

import dagger.Component;

@FragmentScoped
@Component(dependencies = RepositoryComponent.class, modules = NewsfeedModule.class)
public interface NewsfeedComponent {
    void inject(NewsfeedActivity activity);
}