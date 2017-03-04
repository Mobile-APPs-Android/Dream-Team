package com.example.dreamteam.beergram.camera;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.dreamteam.beergram.R;

import java.io.File;
import java.util.List;

import pl.aprilapps.easyphotopicker.EasyImage;

public class CameraFragment extends Fragment implements CameraContract.View {
    private View rootView;

    private CameraContract.Presenter presenter;

    private Context mContext;


    public CameraFragment() {
    }

    public static CameraFragment newInstance() {
        return new CameraFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        EasyImage.openCamera(this, 0);

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.rootView = inflater.inflate(R.layout.fragment_camera, container, false);



        return this.rootView;
    }

    @Override
    public void setPresenter(CameraContract.Presenter presenter) {
        this.presenter = presenter;
    }

}
