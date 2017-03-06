package com.example.dreamteam.beergram.search;

import dagger.Module;
import dagger.Provides;

@Module
public class SearchModule {

    private final SearchContract.View view;

    public SearchModule(SearchContract.View view){
        this.view = view;
    }

    @Provides
    SearchContract.View provideProfileView(){
        return this.view;
    }
}