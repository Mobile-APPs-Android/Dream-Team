package com.example.dreamteam.beergram.contacts;

import android.content.ContentResolver;

import com.example.dreamteam.beergram.data.IRepository;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ContactsPresenter implements ContactsContract.Presenter {

    private final IRepository repository;
    private ContactsContract.View view;

    @Inject
    public ContactsPresenter(ContactsContract.View view, IRepository repository) {
        this.view = view;
        this.repository = repository;
    }

    @Inject
    public void setupListener() {
        this.view.setPresenter(this);
    }

    @Override
    public void start() {
    }

    public void getContacts(ContentResolver resolver) {
        this.repository.getContacts(resolver)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(contactNames -> {
                    this.view.setupAdapter(contactNames);
                });


    }
}
