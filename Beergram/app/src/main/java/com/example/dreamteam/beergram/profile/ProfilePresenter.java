package com.example.dreamteam.beergram.profile;

import android.widget.TextView;

import com.example.dreamteam.beergram.data.IRepository;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ProfilePresenter implements ProfileContract.Presenter {
    private IRepository repository;
    private ProfileContract.View view;

    @Inject
    public ProfilePresenter(ProfileContract.View view, IRepository repository) {
        this.view = view;
        this.repository = repository;
    }

    @Inject
    public void setupListener() {
        this.view.setPresenter(this);
    }

    public void start() {

    }

    public void setProfileValues(TextView tvEmail, TextView tvFirstName, TextView tvLastName, TextView tvAdress){
        this.repository.getCurrentUser()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(user -> {
                    tvEmail.setText(user.getmEmail());
                    tvFirstName.setText(user.getmFirstName());
                    tvLastName.setText(user.getmLastName());
                    tvAdress.setText(user.getmAddress());
                });
    }
}
