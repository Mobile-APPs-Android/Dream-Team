package com.example.dreamteam.beergram.contacts;

import android.content.ContentResolver;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.dreamteam.beergram.R;

import java.util.ArrayList;

public class ContactsFragment extends Fragment implements ContactsContract.View {

    private View root;
    private ContactsContract.Presenter presenter;
    private Context context;

    public ContactsFragment() {
    }

    public static ContactsFragment newInstance() {
        return new ContactsFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.root = inflater.inflate(R.layout.fragment_contacts, container, false);

        ContentResolver resolver = this.context.getContentResolver();
        this.presenter.getContacts(resolver);

        return this.root;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        this.context = context;
    }

    @Override
    public void onResume() {
        super.onResume();

        this.presenter.start();
    }

    @Override
    public void setPresenter(ContactsContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void setupAdapter(ArrayList<String> contactNames) {
        ArrayAdapter<String> userAdapter =
                new ArrayAdapter<String>(this.root.getContext(), -1, contactNames) {
                    @NonNull
                    @Override
                    public View getView(int position, View convertView, ViewGroup parent) {
                        View view = convertView;
                        if (view == null) {
                            LayoutInflater inflater = LayoutInflater.from(this.getContext());
                            view = inflater.inflate(R.layout.item_contact, parent, false);
                        }

                        TextView tvTitle = (TextView) view.findViewById(R.id.contact_list_title);

                        tvTitle.setText(this.getItem(position));

                        return view;
                    }
                };

        ListView lvConctacts = (ListView) this.root.findViewById(R.id.lv_contacts);


        lvConctacts.setAdapter(userAdapter);
    }
}
