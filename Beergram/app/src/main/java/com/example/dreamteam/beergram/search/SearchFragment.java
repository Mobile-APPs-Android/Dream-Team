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

    public void setupAdapter(User[] users) {
        ArrayAdapter<User> userAdapter =
                new ArrayAdapter<User>(this.root.getContext(), -1, users) {
                    @NonNull
                    @Override
                    public View getView(int position, View convertView, ViewGroup parent) {
                        View view = convertView;
                        if (view == null) {
                            LayoutInflater inflater = LayoutInflater.from(this.getContext());
                            view = inflater.inflate(R.layout.item_user, parent, false);
                        }

                        TextView tvTitle = (TextView) view.findViewById(R.id.user_list_title);

                        String email = this.getItem(position).getEmail();
                        tvTitle.setText(email);

                        return view;
                    }
                };

        ListView lvUsers = (ListView) this.root.findViewById(R.id.lv_users);


        lvUsers.setAdapter(userAdapter);
    }

}
