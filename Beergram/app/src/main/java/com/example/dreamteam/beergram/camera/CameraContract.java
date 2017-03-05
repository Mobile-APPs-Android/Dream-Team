package com.example.dreamteam.beergram.camera;

import com.example.dreamteam.beergram.BasePresenter;
import com.example.dreamteam.beergram.BaseView;

import java.io.File;

import pl.aprilapps.easyphotopicker.EasyImage;


public interface CameraContract {

    interface View extends BaseView<com.example.dreamteam.beergram.camera.CameraContract.Presenter> {
//        void setDialog(BeergramProgressDialog progressDialog);
//
//        void showRegisterActivity();
//
//        void showResetPasswordActivity();
//
        void showNewsFeedActivity();
//
//        void showLogoutActivity();
//
        void notifyPictureSavedSuccessful();

        void notifyBadPicture();
//
//        void showDialogForLoggingUser();
//
//        void showDialogForLoading();
//
//        void dismissDialog();
    }

    interface Presenter extends BasePresenter {
        void saveImage(File imageFile);
    }
}