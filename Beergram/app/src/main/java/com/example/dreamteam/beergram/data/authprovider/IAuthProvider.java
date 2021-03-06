package com.example.dreamteam.beergram.data.authprovider;

import com.example.dreamteam.beergram.models.User;

import io.reactivex.Observable;

public interface IAuthProvider {
    Observable<User> createUser(String email, String password, String firstName, String lastName, String address);

    Observable<User> loginUser(String email, String password);

    Observable<User> getCurrentUser();

    Observable<Boolean> logoutUser();

    Observable<User> updateAccountInfo(String firstName, String lastName, String email, String address, String credentialsEmail, String credentialsPassword);

    void addUserStatusChangeListener();

    void removeUserStatusChangeListener();
}
