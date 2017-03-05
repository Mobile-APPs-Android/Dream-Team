package com.example.dreamteam.beergram.profile;

import android.provider.ContactsContract;

import com.example.dreamteam.beergram.data.IRepository;

import javax.inject.Inject;

public class ProfilePresenter implements ProfileContract.Presenter{
    private IRepository repository;
    private ProfileContract.View view;

    @Inject
    public void ProfilePresenter(ProfileContract.View view, IRepository repository) {
        this.view = view;
        this.repository = repository;
    }

    @Override
    public void start() {

    }
}