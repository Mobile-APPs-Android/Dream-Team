package com.example.dreamteam.beergram.newsfeed;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dreamteam.beergram.R;


public class NewsFeedFragment extends Fragment implements NewsfeedContract.View {
    private View rootView;
    private NewsfeedContract.Presenter presenter;
    private Context context;

    public NewsFeedFragment() {
    }

    public static NewsFeedFragment newInstance() {
        return new NewsFeedFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.rootView = inflater.inflate(R.layout.fragment_news_feed, container, false);

        return this.rootView;
    }

    @Override
    public void setPresenter(NewsfeedContract.Presenter presenter) {
        this.presenter = presenter;
    }
}
