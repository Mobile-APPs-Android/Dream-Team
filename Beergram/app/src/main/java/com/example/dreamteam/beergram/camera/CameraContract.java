package com.example.dreamteam.beergram.camera;

import com.example.dreamteam.beergram.BasePresenter;
import com.example.dreamteam.beergram.BaseView;

import java.io.File;

public interface CameraContract {

    interface View extends BaseView<Presenter> {

        void showNewsFeedActivity();

        void notifyPictureSavedSuccessful();

        void notifyBadPicture();

    }

    interface Presenter extends BasePresenter {
        void saveImage(File imageFile);
    }
}