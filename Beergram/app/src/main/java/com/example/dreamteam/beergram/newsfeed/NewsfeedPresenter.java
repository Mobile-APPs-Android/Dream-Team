package com.example.dreamteam.beergram.newsfeed;

import com.example.dreamteam.beergram.data.IRepository;
import com.example.dreamteam.beergram.models.Position;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class NewsfeedPresenter implements NewsfeedContract.Presenter {
    private final static String EMPTY_STRING = "";

    private IRepository repository;
    private NewsfeedContract.View view;

    @Inject
    public NewsfeedPresenter(NewsfeedContract.View view, IRepository repository) {
        this.view = view;
        this.repository = repository;
    }

    @Inject
    public void setupListener() {
        this.view.setPresenter(this);
    }

    public void start() {

    }

    public void postLocationToFriends() {
        this.repository.postLocationToFriends()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(post -> {
                    this.view.notifyPostShared();
                });
    }

    @Override
    public void onGetPosition(Position position) {
        this.repository.getAddress(position)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

    @Override
    public void onStart() {
        this.repository.connectLocationListener()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(success -> {
                    this.view.setMyLocationBtn();
                });
    }

    @Override
    public void onStop() {
        this.repository.disconnectLocationListener()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(success -> {
                });
    }

    @Override
    public void onMyLocation() {
        this.repository.getCurrentPosition()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this.view::showCurrentPosition);
    }

    @Override
    public void onBack() {
        this.view.returnActivityResult(EMPTY_STRING);
    }
}
