package com.example.dreamteam.beergram.camera;

import com.example.dreamteam.beergram.data.IRepository;

import java.io.File;

import javax.inject.Inject;

import pl.aprilapps.easyphotopicker.EasyImage;

public class CameraPresenter implements CameraContract.Presenter{
    private IRepository repository;
    private CameraContract.View view;

    @Inject
    public void CameraPresenter(CameraContract.View view, IRepository repository) {
        this.view = view;
        this.repository = repository;
    }

    @Override
    public void start() {

    }

    @Override
    public void saveImage(File imageFile) {
        this.view.showNewsFeedActivity();
//        this.repository.savePicture(imageFile)
//            .subscribe((result) -> {
//                if (result) {
//                    this.view.notifyPictureSavedSuccessful();
//                    this.view.showNewsFeedActivity();
//                }
//                else {
//                    this.view.notifyBadPicture();
//                }
//            });
    }
}
