package com.example.dreamteam.beergram.utils;

import android.app.ProgressDialog;
import android.content.Context;

import javax.inject.Inject;

public class BeergramProgressDialog {

    ProgressDialog mDialog;

    @Inject
    public BeergramProgressDialog() {
    }

    public void setContext(Context context) {
        mDialog = new ProgressDialog(context);
    }

    public void showProgress(String text) {
        mDialog.setMessage(text);
        mDialog.show();
    }

    public void dismissProgress() {
        mDialog.dismiss();
    }
}
