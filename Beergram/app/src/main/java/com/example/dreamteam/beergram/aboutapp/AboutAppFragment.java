package com.example.dreamteam.beergram.aboutapp;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dreamteam.beergram.R;

import pl.aprilapps.easyphotopicker.EasyImage;

public class AboutAppFragment extends Fragment implements AboutAppContract.View {
    private View rootView;
    private AboutAppContract.Presenter presenter;
    private Context context;

    public AboutAppFragment() {
    }

    public static AboutAppFragment newInstance() {
        return new AboutAppFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.rootView = inflater.inflate(R.layout.fragment_about_app, container, false);

        TextView tvApp = (TextView) this.rootView.findViewById(R.id.tv_appname_value);
        TextView tvText = (TextView) this.rootView.findViewById(R.id.tv_text_value);

        return this.rootView;
    }

    @Override
    public void setPresenter(AboutAppContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        this.context = context;
    }

}
