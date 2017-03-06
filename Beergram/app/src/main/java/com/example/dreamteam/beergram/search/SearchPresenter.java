package com.example.dreamteam.beergram.search;

import android.widget.TextView;

import com.example.dreamteam.beergram.data.IRepository;
import com.example.dreamteam.beergram.models.User;
import com.example.dreamteam.beergram.profile.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class SearchPresenter implements SearchContract.Presenter {
    private IRepository repository;
    private SearchContract.View view;

    @Inject
    public SearchPresenter(SearchContract.View view, IRepository repository) {
        this.view = view;
        this.repository = repository;
    }

    @Inject
    public void setupListener() {
        this.view.setPresenter(this);
    }

    public void start() {

    }

    public void getAllUsers(String searchValue) {
        this.repository.getAllUsers()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(userEmails -> {
                userEmails = filterUserEmails(userEmails, searchValue);
                this.view.setupAdapter(userEmails);
            });
    }

    public ArrayList<String> filterUserEmails(ArrayList<String> userEmails, String searchfield){
        Iterator<String> stringIterator = userEmails.iterator();

        while (stringIterator.hasNext()) {
            String string = stringIterator.next();
            if (!string.contains(searchfield)) {
                stringIterator.remove();
            }
        }

        return userEmails;
    }
}
