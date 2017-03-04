package com.example.dreamteam.beergram.auth.login;

import com.example.dreamteam.beergram.data.IRepository;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class LoginPresenter implements LoginContract.Presenter {

    private final IRepository mRepository;
    LoginContract.View mView;

    @Inject
    public LoginPresenter(LoginContract.View view, IRepository repository) {
        mView = view;
        mRepository = repository;
    }

    @Inject
    public void setupListener() {
        mView.setPresenter(this);
    }

    @Override
    public void start() {
        mView.showDialogForLoading();
        mRepository.getCurrentUser()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(user -> {
                    mView.dismissDialog();
                    String email = user.getmEmail();
                    Boolean isEmailNull = email == null;

                    if (!isEmailNull) {
                        mView.showLogoutActivity();
                    }
                });
    }

    @Override
    public void loginUser(String email, String password) {
        if(email.isEmpty() || email == null || password.isEmpty() || password == null) {
            mView.notoifyBadEmailOrPassword();
            return;
        }

        mView.showDialogForLoggingUser();
        mRepository.loginUser(email, password)
                .observeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(username -> {
                    mView.dismissDialog();

                    if (username.isEmpty()) {
                        mView.notoifyBadEmailOrPassword();
                    } else {
                        mView.notifyLoggedInUser(username);
                        mView.showHomeActivity();
                    }
                });
    }

    @Override
    public void onResetPassword() {
        mView.showResetPasswordActivity();
    }

    @Override
    public void onCreateAccount() {
        mView.showRegisterActivity();
    }

}
