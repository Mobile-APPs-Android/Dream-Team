package com.example.dreamteam.beergram.search;

import com.example.dreamteam.beergram.utils.FragmentScoped;
import com.example.dreamteam.beergram.data.RepositoryComponent;

import dagger.Component;

@FragmentScoped
@Component(dependencies = RepositoryComponent.class, modules = SearchModule.class)
public interface SearchComponent {
    void inject(SearchActivity searchActivty);
}