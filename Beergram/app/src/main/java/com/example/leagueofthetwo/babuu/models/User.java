package com.example.leagueofthetwo.babuu.models;

import android.util.ArrayMap;

import java.util.HashMap;
import java.util.Map;

public class User {

    private String mUserId;

    private String mEmail;

    private String mFirstName;

    private String mLastName;

    private String mAddress;

    private Map<String, Boolean> mOrders;


    public User() {
        this.setmOrders(new HashMap<>());
    }

    public User(String email, String firstName, String lastName, String address) {
        this();
        this.setmEmail(email);
        this.setmFirstName(firstName);
        this.setmLastName(lastName);
        this.setmAddress(address);
    }

    public String getmUserId() {
        return mUserId;
    }

    public void setmUserId(String mUserId) {
        this.mUserId = mUserId;
    }

    public String getmEmail() {
        return mEmail;
    }

    public void setmEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    public String getmFirstName() {
        return mFirstName;
    }

    public void setmFirstName(String mFirstName) {
        this.mFirstName = mFirstName;
    }

    public String getmLastName() {
        return mLastName;
    }

    public void setmLastName(String mLastName) {
        this.mLastName = mLastName;
    }

    public String getmAddress() {
        return mAddress;
    }

    public void setmAddress(String mAddress) {
        this.mAddress = mAddress;
    }

    public Map<String, Boolean> getmOrders() {
        return mOrders;
    }

    public void setmOrders(Map<String, Boolean> mOrders) {
        this.mOrders = mOrders;
    }
}
