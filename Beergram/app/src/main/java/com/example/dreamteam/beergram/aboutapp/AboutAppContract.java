package com.example.dreamteam.beergram.aboutapp;


import android.widget.TextView;

import com.example.dreamteam.beergram.BasePresenter;
import com.example.dreamteam.beergram.BaseView;

import java.io.File;

public interface AboutAppContract {

    interface View extends BaseView<Presenter> {
    }

    interface Presenter extends BasePresenter {
    }
}