package com.example.dreamteam.beergram.newsfeed;

import com.example.dreamteam.beergram.data.IRepository;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class NewsfeedPresenter implements NewsfeedContract.Presenter {
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
}
