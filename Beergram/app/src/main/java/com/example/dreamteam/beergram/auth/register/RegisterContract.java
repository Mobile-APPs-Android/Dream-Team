package com.example.dreamteam.beergram.auth.register;

import com.example.dreamteam.beergram.BasePresenter;
import com.example.dreamteam.beergram.BaseView;
import com.example.dreamteam.beergram.utils.BeergramProgressDialog;

public interface RegisterContract {
    interface View extends BaseView<Presenter> {
        void setDialog(BeergramProgressDialog dialog);

        void showDialogForCreatingUser();

        void dismissDialog();

        void showLoginActivity();

        void notifyCreatedUser(String username);

        void showHomeActivity();

        void showBadEmailError();

        void showBadFirstNameError();

        void showBadLastNameError();

        void showBadConfirmPasswordError();

        void showBadPasswordError();
    }

    interface Presenter extends BasePresenter {
        void onRegister(String email, String password, String confirmPassword, String firstName, String lastName, String address);

        void onHasAccount();
    }
}
