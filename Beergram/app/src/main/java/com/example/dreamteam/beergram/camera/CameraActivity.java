package com.example.dreamteam.beergram.camera;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.dreamteam.beergram.BeergramApplication;
import com.example.dreamteam.beergram.R;

public class CameraActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        CameraFragment cameraFragment =
                (CameraFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);

        if (cameraFragment == null) {
            cameraFragment = CameraFragment.newInstance();

            getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container, cameraFragment)
                .commit();
        }

        DaggerCameraComponent
                .builder()
                .repositoryComponent(((BeergramApplication)getApplication())
                        .getRepositoryComponent())
                .cameraModule(new CameraModule(cameraFragment))
                .build()
                .inject(this);

//        mDialog.setContext(this);
//        loginFragment.setDialog(mDialog);
    }
}