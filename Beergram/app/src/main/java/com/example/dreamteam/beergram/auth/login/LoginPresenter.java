package com.example.dreamteam.beergram.auth.login;

import com.example.dreamteam.beergram.data.IRepository;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class LoginPresenter implements LoginContract.Presenter {

    private final IRepository repository;
    private LoginContract.View view;

    @Inject
    public LoginPresenter(LoginContract.View view, IRepository repository) {
        this.view = view;
        this.repository = repository;
    }

    @Inject
    public void setupListener() {
        this.view.setPresenter(this);
    }

    @Override
    public void start() {
        this.view.showDialogForLoading();
        this.repository.getCurrentUser()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(user -> {
                    this.view.dismissDialog();
                    String email = user.getEmail();
                    Boolean isEmailNull = email == null;

                    if (!isEmailNull) {
                        this.view.showLogoutActivity();
                    }
                });
    }

    @Override
    public void loginUser(String email, String password) {
        if(email.isEmpty() || email == null || password.isEmpty() || password == null) {
            this.view.notoifyBadEmailOrPassword();
            return;
        }

        this.view.showDialogForLoggingUser();
        this.repository.loginUser(email, password)
                .observeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(username -> {
                    this.view.dismissDialog();

                    if (username.isEmpty()) {
                        this.view.notoifyBadEmailOrPassword();
                    } else {
                        this.view.notifyLoggedInUser(username);
                        this.view.showHomeActivity();
                    }
                });
    }

    @Override
    public void onResetPassword() {
        this.view.showResetPasswordActivity();
    }

    @Override
    public void onCreateAccount() {
        this.view.showRegisterActivity();
    }

}
