package com.example.leagueofthetwo.babuu.auth.register;

import android.util.Patterns;

import com.example.leagueofthetwo.babuu.data.IRepository;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class RegisterPresenter implements RegisterContract.Presenter {

    private final RegisterContract.View mView;
    private final IRepository mRepository;

    @Inject
    public RegisterPresenter(RegisterContract.View view, IRepository repository) {
        mView = view;
        mRepository = repository;
    }

    @Inject
    public void setupListener() {
        mView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void onRegister(String email, String password, String confirmPassword, String firstName, String lastName, String address) {
        boolean isRegUserInfoValid = this.validateUserRegInfo(email, password, confirmPassword, firstName, lastName);

        if (isRegUserInfoValid) {
            mView.showDialogForCreatingUser();
            mRepository.createUser(email, password, firstName, lastName, address)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(username -> {
                        mView.dismissDialog();
                        mView.notifyCreatedUser(username);
                        mView.showHomeActivity();
                    });
        }
    }

    @Override
    public void onHasAccount() {
        mView.showLoginActivity();
    }

    private Boolean validateUserRegInfo(
            String email,
            String password,
            String confirmPassword,
            String firstName,
            String lastName) {

        Boolean isValid = true;

        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mView.showBadEmailError();
            isValid = false;
        }

        if (!password.equals(confirmPassword)) {
            mView.showBadConfirmPasswordError();
            isValid = false;
        } else if (password.isEmpty() || password.length() < 6) {
            mView.showBadPasswordError();
            isValid = false;
        }

        if (firstName.isEmpty() || firstName.length() < 2) {
            mView.showBadFirstNameError();
            isValid = false;
        }

        if (lastName.isEmpty() || lastName.length() < 2) {
            mView.showBadLastNameError();
            isValid = false;
        }

        return isValid;
    }
}
