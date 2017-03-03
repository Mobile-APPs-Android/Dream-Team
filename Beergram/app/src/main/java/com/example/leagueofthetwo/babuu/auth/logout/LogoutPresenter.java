package com.example.leagueofthetwo.babuu.auth.logout;


import com.example.leagueofthetwo.babuu.data.IRepository;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class LogoutPresenter implements LogoutContract.Presenter {

    private final LogoutContract.View mView;
    private final IRepository mRepository;

    @Inject
    public LogoutPresenter(LogoutContract.View view, IRepository repository) {
        mView = view;
        mRepository = repository;
    }

    @Inject
    void setupListener() {
        mView.setPresenter(this);
    }

    @Override
    public void start() {
        mView.showDialogLoading();
        mRepository.getCurrentUser()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(user -> {
                    mView.dismissDialog();
                    mView.setUserNames(user.getmFirstName(), user.getmLastName());
                });

    }

    @Override
    public void onBtnLogout() {
        mView.showDialogLoggingOut();
        mRepository.logoutUser()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(isSuccess -> {
                    if (isSuccess) {
                        mView.dismissDialog();
                        mView.notifyUserLogout();
                        mView.showLoginActivity();
                    }

                });

    }
}
