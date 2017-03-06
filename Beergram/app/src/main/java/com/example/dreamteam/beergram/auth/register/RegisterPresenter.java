package com.example.dreamteam.beergram.auth.register;

import android.util.Patterns;

import com.example.dreamteam.beergram.data.IRepository;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class RegisterPresenter implements RegisterContract.Presenter {

    private final RegisterContract.View view;
    private final IRepository repository;

    @Inject
    public RegisterPresenter(RegisterContract.View view, IRepository repository) {
        this.view = view;
        this.repository = repository;
    }

    @Inject
    public void setupListener() {
        this.view.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void onRegister(String email, String password, String confirmPassword, String firstName, String lastName, String address) {
        boolean isRegUserInfoValid = this.validateUserRegInfo(email, password, confirmPassword, firstName, lastName);

        if (isRegUserInfoValid) {
            this.view.showDialogForCreatingUser();
            this.repository.createUser(email, password, firstName, lastName, address)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(username -> {
                        this.view.dismissDialog();
                        this.view.notifyCreatedUser(username);
                        this.view.showHomeActivity();
                    });
        }
    }

    @Override
    public void onHasAccount() {
        this.view.showLoginActivity();
    }

    private Boolean validateUserRegInfo(
            String email,
            String password,
            String confirmPassword,
            String firstName,
            String lastName) {

        Boolean isValid = true;

        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            this.view.showBadEmailError();
            isValid = false;
        }

        if (!password.equals(confirmPassword)) {
            this.view.showBadConfirmPasswordError();
            isValid = false;
        } else if (password.isEmpty() || password.length() < 6) {
            this.view.showBadPasswordError();
            isValid = false;
        }

        if (firstName.isEmpty() || firstName.length() < 2) {
            this.view.showBadFirstNameError();
            isValid = false;
        }

        if (lastName.isEmpty() || lastName.length() < 2) {
            this.view.showBadLastNameError();
            isValid = false;
        }

        return isValid;
    }
}
