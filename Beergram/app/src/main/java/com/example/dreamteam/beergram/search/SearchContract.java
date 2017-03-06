package com.example.dreamteam.beergram.search;

import com.example.dreamteam.beergram.BasePresenter;
import com.example.dreamteam.beergram.BaseView;
import com.example.dreamteam.beergram.models.User;

import java.util.ArrayList;

public interface SearchContract {
    interface View extends BaseView<Presenter> {
        void setupAdapter(ArrayList<String> users);
    }

    interface Presenter extends BasePresenter {
        ArrayList<String> filterUserEmails(ArrayList<String> userEmails, String searchfield);

        public void getAllUsers(String searchValue);
    }
}
