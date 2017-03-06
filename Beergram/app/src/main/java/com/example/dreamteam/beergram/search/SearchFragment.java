package com.example.dreamteam.beergram.search;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dreamteam.beergram.R;

public class SearchFragment extends Fragment implements SearchContract.View {
    private View root;
    private SearchContract.Presenter presenter;
    private Context context;

    public SearchFragment() {
    }

    public static SearchFragment newInstance() {
        return new SearchFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        this.root = inflater.inflate(R.layout.fragment_search, container, false);

        return this.root;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        this.context = context;
    }

    @Override
    public void setPresenter(SearchContract.Presenter presenter) {
        this.presenter = presenter;
    }
}
