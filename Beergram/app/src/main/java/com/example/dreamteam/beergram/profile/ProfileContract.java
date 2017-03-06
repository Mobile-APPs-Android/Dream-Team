package com.example.dreamteam.beergram.profile;

import android.widget.TextView;

import com.example.dreamteam.beergram.BasePresenter;
import com.example.dreamteam.beergram.BaseView;

public interface ProfileContract {
    interface View extends BaseView<Presenter> {
        //void showLogoutActivity(String searchValue);
    }

    interface Presenter extends BasePresenter {
        void setProfileValues(TextView tvEmail, TextView tvFirstName, TextView tvLastName, TextView tvAdress);
    }
}
