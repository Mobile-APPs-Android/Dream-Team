package com.example.dreamteam.beergram.camera;

import com.example.dreamteam.beergram.auth.login.LoginContract;
import com.example.dreamteam.beergram.data.IRepository;

import java.io.File;

import javax.inject.Inject;

import pl.aprilapps.easyphotopicker.EasyImage;

public class CameraPresenter implements CameraContract.Presenter{
    private IRepository repository;
    private LoginContract.View view;

    @Inject
    public void CameraPresenter(LoginContract.View view, IRepository repository) {
        this.view = view;
        this.repository = repository;
    }

    @Override
    public void start() {

    }

    @Override
    public void saveImage(File imageFile, EasyImage.ImageSource source) {

    }
}
