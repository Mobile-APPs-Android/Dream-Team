package com.example.dreamteam.beergram.auth.login;

import com.example.dreamteam.beergram.BasePresenter;
import com.example.dreamteam.beergram.BaseView;
import com.example.dreamteam.beergram.utils.BeergramProgressDialog;

public interface LoginContract {

    interface View extends BaseView<Presenter> {
        void setDialog(BeergramProgressDialog progressDialog);

        void showRegisterActivity();

        void showResetPasswordActivity();

        void showHomeActivity();

        void showLogoutActivity();

        void notifyLoggedInUser(String username);

        void notoifyBadEmailOrPassword();

        void showDialogForLoggingUser();

        void showDialogForLoading();

        void dismissDialog();
    }

    interface Presenter extends BasePresenter {
        void loginUser(String email, String password);

        void onResetPassword();

        void onCreateAccount();
    }
}
