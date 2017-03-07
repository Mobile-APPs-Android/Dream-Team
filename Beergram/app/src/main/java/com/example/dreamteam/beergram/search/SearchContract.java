package com.example.dreamteam.beergram.search;

import com.example.dreamteam.beergram.BasePresenter;
import com.example.dreamteam.beergram.BaseView;
import com.example.dreamteam.beergram.models.User;
import com.example.dreamteam.beergram.utils.BeergramProgressDialog;

import java.util.ArrayList;

public interface SearchContract {
    interface View extends BaseView<Presenter> {
        void setupAdapter(ArrayList<String> users);

        void setDialog(BeergramProgressDialog dialog);

        void dismissDialog();

        public void showDialogForSearching();
    }

    interface Presenter extends BasePresenter {
        ArrayList<String> filterUserEmails(ArrayList<String> userEmails, String searchfield);

        public void getAllUsers(String searchValue);
    }
}
