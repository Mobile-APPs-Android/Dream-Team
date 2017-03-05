package com.example.dreamteam.beergram.camera;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.dreamteam.beergram.R;
import com.example.dreamteam.beergram.newsfeed.NewsfeedActivity;
import com.example.dreamteam.beergram.newsfeed.NewsfeedModule;
import com.example.dreamteam.beergram.newsfeed.NewsfeedModule_ProvideNewsfeedViewFactory;

import java.io.File;

import pl.aprilapps.easyphotopicker.EasyImage;

public class CameraFragment extends Fragment implements CameraContract.View, EasyImage.Callbacks {
    private View rootView;
    private CameraContract.Presenter presenter;

    private Context context;


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
    public void onAttach(Context context) {
        super.onAttach(context);

        this.context = context;
    }

    @Override
    public void showNewsFeedActivity() {
        Intent intent = new Intent(this.context, NewsfeedActivity.class); // TODO change activity with NewsFeedActivity

        this.getActivity().finish();
        startActivity(intent);
    }

    @Override
    public void onImagePickerError(Exception e, EasyImage.ImageSource source, int type) {
        e.printStackTrace();
    }

    @Override
    public void onImagePicked(File imageFile, EasyImage.ImageSource source, int type) {
        this.presenter.saveImage(imageFile);
    }

    @Override
    public void onCanceled(EasyImage.ImageSource source, int type) {
        if (source == EasyImage.ImageSource.CAMERA) {
            File photoFile = EasyImage.lastlyTakenButCanceledPhoto(this.getContext());
            if (photoFile != null) photoFile.delete();
        }
        this.showNewsFeedActivity();
    }

    public void notifyPictureSavedSuccessful() {
        Toast.makeText(this.context, getString(R.string.picture_saved_successfully), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void notifyBadPicture() {
        Toast.makeText(this.context, getString(R.string.picture_error_save), Toast.LENGTH_SHORT).show();

    }

}
