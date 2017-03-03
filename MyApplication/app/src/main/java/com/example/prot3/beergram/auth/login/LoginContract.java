package com.example.prot3.beergram.auth.login;

import com.example.prot3.beergram.BasePresenter;
import com.example.prot3.beergram.BaseView;

public interface LoginContract {
    interface View extends BaseView<Presenter> {
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
