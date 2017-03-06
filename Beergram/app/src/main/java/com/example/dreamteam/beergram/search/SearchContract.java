package com.example.dreamteam.beergram.search;

import com.example.dreamteam.beergram.BasePresenter;
import com.example.dreamteam.beergram.BaseView;
import com.example.dreamteam.beergram.models.User;

public interface SearchContract {
    interface View extends BaseView<Presenter> {
        void setupAdapter(User[] users);
    }

    interface Presenter extends BasePresenter {
        public void getAllUsers();
    }
}
