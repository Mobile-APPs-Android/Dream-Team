package com.example.dreamteam.beergram.contacts;

import android.content.ContentResolver;

import com.example.dreamteam.beergram.BasePresenter;
import com.example.dreamteam.beergram.BaseView;

import java.util.ArrayList;

public interface ContactsContract {

    interface View extends BaseView<Presenter> {
        public void setupAdapter(ArrayList<String> contactNames);
    }

    interface Presenter extends BasePresenter {
        void getContacts(ContentResolver resolver);
    }
}
