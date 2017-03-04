package com.example.dreamteam.beergram.auth.logout;

import com.example.dreamteam.beergram.BasePresenter;
import com.example.dreamteam.beergram.BaseView;
import com.example.dreamteam.beergram.utils.BeergramProgressDialog;

public interface LogoutContract {
    interface View extends BaseView<Presenter> {
        void setUserNames(String firsName, String lastName);

        void setDialog(BeergramProgressDialog dialog);

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
