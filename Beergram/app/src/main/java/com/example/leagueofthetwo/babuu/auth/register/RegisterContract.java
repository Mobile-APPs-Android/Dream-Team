package com.example.leagueofthetwo.babuu.auth.register;

import com.example.leagueofthetwo.babuu.BasePresenter;
import com.example.leagueofthetwo.babuu.BaseView;
import com.example.leagueofthetwo.babuu.utils.BabuuProgressDialog;

public interface RegisterContract {
    interface View extends BaseView<Presenter> {
        void setDialog(BabuuProgressDialog dialog);

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
