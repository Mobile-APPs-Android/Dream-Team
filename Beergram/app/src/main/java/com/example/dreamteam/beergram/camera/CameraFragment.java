package com.example.dreamteam.beergram.camera;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dreamteam.beergram.R;
import com.example.dreamteam.beergram.auth.logout.LogoutActivity;

import java.io.File;

import pl.aprilapps.easyphotopicker.EasyImage;


public class CameraFragment extends Fragment implements CameraContract.View, EasyImage.Callbacks {
    private View rootView;
    private CameraContract.Presenter presenter;

    private Context mContext;


    public CameraFragment() {
    }

    public static CameraFragment newInstance() {
        return new CameraFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.rootView = inflater.inflate(R.layout.fragment_camera, container, false);

        EasyImage.openCamera(this, 0);

        return this.rootView;
    }

    @Override
    public void setPresenter(CameraContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showNewsFeedActivity() {
        Intent intent = new Intent(mContext, LogoutActivity.class); // TODO change activity with NewsFeedActivity

        this.getActivity().finish();
        startActivity(intent);
    }

    @Override
    public void onImagePickerError(Exception e, EasyImage.ImageSource source, int type) {
        e.printStackTrace();
    }

    @Override
    public void onImagePicked(File imageFile, EasyImage.ImageSource source, int type) {
        this.presenter.saveImage(imageFile, source);
        this.showNewsFeedActivity();
    }

    @Override
    public void onCanceled(EasyImage.ImageSource source, int type) {
        if (source == EasyImage.ImageSource.CAMERA) {
            File photoFile = EasyImage.lastlyTakenButCanceledPhoto(this.getContext());
            if (photoFile != null) photoFile.delete();
        }
        this.showNewsFeedActivity();
    }
}
