package com.example.leagueofthetwo.babuu.auth.logout;

import com.example.leagueofthetwo.babuu.BasePresenter;
import com.example.leagueofthetwo.babuu.BaseView;
import com.example.leagueofthetwo.babuu.utils.BabuuProgressDialog;

public interface LogoutContract {
    interface View extends BaseView<Presenter> {
        void setUserNames(String firsName, String lastName);

        void setDialog(BabuuProgressDialog dialog);

        void showDialogLoading();

        void showDialogLoggingOut();

        void dismissDialog();

        void showLoginActivity();

        void notifyUserLogout();
    }

    interface Presenter extends BasePresenter {
        void onBtnLogout();
    }
}
