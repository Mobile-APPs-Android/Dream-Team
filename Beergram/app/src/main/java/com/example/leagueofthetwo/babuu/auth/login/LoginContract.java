package com.example.leagueofthetwo.babuu.auth.login;

import com.example.leagueofthetwo.babuu.BasePresenter;
import com.example.leagueofthetwo.babuu.BaseView;
import com.example.leagueofthetwo.babuu.utils.BabuuProgressDialog;

public interface LoginContract {

    interface View extends BaseView<Presenter> {
        void setDialog(BabuuProgressDialog progressDialog);

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
