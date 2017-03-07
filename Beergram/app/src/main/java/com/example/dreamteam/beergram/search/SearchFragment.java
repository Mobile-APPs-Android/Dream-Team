package com.example.dreamteam.beergram.search;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.widget.ListViewAutoScrollHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.dreamteam.beergram.R;
import com.example.dreamteam.beergram.models.User;
import com.example.dreamteam.beergram.utils.BeergramProgressDialog;

import java.util.ArrayList;

public class SearchFragment extends Fragment implements SearchContract.View {
    private final String SEARCH_VALUE = "searchValue";

    private View root;
    private SearchContract.Presenter presenter;
    private Context context;

    private BeergramProgressDialog dialog;

    public SearchFragment() {
    }

    public static SearchFragment newInstance() {
        return new SearchFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        this.root = inflater.inflate(R.layout.fragment_search, container, false);

        String searchValue = this.getActivity().getIntent().getStringExtra(SEARCH_VALUE);
        this.presenter.getAllUsers(searchValue);

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

    @Override
    public void setDialog(BeergramProgressDialog dialog) {
        this.dialog = dialog;
    }

    @Override
    public void dismissDialog() {
        this.dialog.dismissProgress();
    }

    @Override
    public void showDialogForSearching() {
        this.dialog.showProgress(getString(R.string.searching_text));
    }

    public void setupAdapter(ArrayList<String> userEmails) {
        ArrayAdapter<String> userAdapter =
                new ArrayAdapter<String>(this.root.getContext(), -1, userEmails) {
                    @NonNull
                    @Override
                    public View getView(int position, View convertView, ViewGroup parent) {
                        View view = convertView;
                        if (view == null) {
                            LayoutInflater inflater = LayoutInflater.from(this.getContext());
                            view = inflater.inflate(R.layout.item_user, parent, false);
                        }

                        TextView tvTitle = (TextView) view.findViewById(R.id.user_list_title);

                        tvTitle.setText(this.getItem(position));

                        return view;
                    }
                };

        ListView lvUsers = (ListView) this.root.findViewById(R.id.lv_users);


        lvUsers.setAdapter(userAdapter);
    }

}
