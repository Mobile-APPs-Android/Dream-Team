package com.example.dreamteam.beergram.data;

import com.example.dreamteam.beergram.models.User;

import io.reactivex.Observable;

public interface IRepository {
    Observable<String> createUser(String email, String password, String firstName, String lastName, String address);

    Observable<String> loginUser(String email, String password);

    Observable<User> getCurrentUser();

    Observable<Boolean> logoutUser();

    Observable<User> updateAccountInfo(String firstName, String lastName, String email, String address, String credentialsEmail, String credentialsPassword);

    Observable<Boolean> getIsFirstTimeForUser();
}
