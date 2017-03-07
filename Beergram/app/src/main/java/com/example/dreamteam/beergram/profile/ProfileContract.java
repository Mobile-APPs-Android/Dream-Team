package com.example.dreamteam.beergram.profile;

import android.widget.TextView;

import com.example.dreamteam.beergram.BasePresenter;
import com.example.dreamteam.beergram.BaseView;
import com.example.dreamteam.beergram.utils.BeergramProgressDialog;

public interface ProfileContract {
    interface View extends BaseView<Presenter> {
        void setDialog(BeergramProgressDialog dialog);

        void showDialogLoggingOut();

        void dismissDialog();

        void notifyUserLogout();

        void showLoginActivity();
    }
    interface Presenter extends BasePresenter {
        void setProfileValues(TextView tvEmail, TextView tvFirstName, TextView tvLastName, TextView tvAdress);

        public void onBtnLogout();
    }
}
