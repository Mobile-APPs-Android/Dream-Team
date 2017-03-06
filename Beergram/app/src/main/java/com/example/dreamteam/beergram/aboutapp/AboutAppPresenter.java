package com.example.dreamteam.beergram.aboutapp;

import com.example.dreamteam.beergram.data.IRepository;

import javax.inject.Inject;

public class AboutAppPresenter implements AboutAppContract.Presenter{
    private IRepository repository;
    private AboutAppContract.View view;

    @Inject
    public void CameraPresenter(AboutAppContract.View view, IRepository repository) {
        this.view = view;
        this.repository = repository;
    }

    @Override
    public void start() {

    }
}
