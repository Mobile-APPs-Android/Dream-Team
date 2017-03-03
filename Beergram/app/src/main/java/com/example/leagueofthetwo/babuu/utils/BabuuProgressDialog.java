package com.example.leagueofthetwo.babuu.utils;

import android.app.ProgressDialog;
import android.content.Context;

import javax.inject.Inject;

public class BabuuProgressDialog {

    ProgressDialog mDialog;

    @Inject
    public BabuuProgressDialog() {
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
