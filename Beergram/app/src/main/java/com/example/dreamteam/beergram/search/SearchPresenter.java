package com.example.dreamteam.beergram.search;

import com.example.dreamteam.beergram.data.IRepository;

import javax.inject.Inject;

public class SearchPresenter implements SearchContract.Presenter{
    private IRepository repository;
    private SearchContract.View view;

    @Inject
    public void SearchPresenter(SearchContract.View view, IRepository repository) {
        this.view = view;
        this.repository = repository;
    }

    @Override
    public void start() {

    }
}
