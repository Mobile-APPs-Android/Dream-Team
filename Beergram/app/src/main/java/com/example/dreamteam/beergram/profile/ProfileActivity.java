package com.example.dreamteam.beergram.profile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.dreamteam.beergram.BeergramApplication;
import com.example.dreamteam.beergram.R;
<<<<<<< HEAD
import com.example.dreamteam.beergram.utils.BeergramProgressDialog;
=======
import com.example.dreamteam.beergram.auth.logout.LogoutActivity;
>>>>>>> origin/master

import javax.inject.Inject;

public class ProfileActivity extends AppCompatActivity {

    @Inject
    ProfilePresenter presenter;

    @Inject
    BeergramProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        ProfileFragment profileFragment =
                (ProfileFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);

        if (profileFragment == null) {
            profileFragment = ProfileFragment.newInstance();

            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_container, profileFragment)
                    .commit();

//            Button btn = (Button)findViewById(R.id.btn_logout);
//
//            btn.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    startActivity(new Intent(ProfileActivity.this, LogoutActivity.class));
//                }
//            });
        }

        DaggerProfileComponent
                .builder()
                .repositoryComponent(((BeergramApplication)getApplication())
                        .getRepositoryComponent())
                .profileModule(new ProfileModule(profileFragment))
                .build()
                .inject(this);

        this.dialog.setContext(this);
        profileFragment.setDialog(this.dialog);
    }
}