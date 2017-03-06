package com.example.dreamteam.beergram.auth.logout;


import com.example.dreamteam.beergram.data.IRepository;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class LogoutPresenter implements LogoutContract.Presenter {

    private final LogoutContract.View view;
    private final IRepository repository;

    @Inject
    public LogoutPresenter(LogoutContract.View view, IRepository repository) {
        this.view = view;
        this.repository = repository;
    }

    @Inject
    void setupListener() {
        this.view.setPresenter(this);
    }

    @Override
    public void start() {
        this.view.showDialogLoading();
        this.repository.getCurrentUser()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(user -> {
                    this.view.dismissDialog();
                    this.view.setUserNames(user.getFirstName(), user.getLastName());
                });

    }

    @Override
    public void onBtnLogout() {
        this.view.showDialogLoggingOut();
        this.repository.logoutUser()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(isSuccess -> {
                    if (isSuccess) {
                        this.view.dismissDialog();
                        this.view.notifyUserLogout();
                        this.view.showLoginActivity();
                    }

                });

    }
}
