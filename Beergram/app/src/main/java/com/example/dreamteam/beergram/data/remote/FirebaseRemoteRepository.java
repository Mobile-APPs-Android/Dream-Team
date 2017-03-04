package com.example.dreamteam.beergram.data.remote;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

@Singleton
public class FirebaseRemoteRepository implements IRemoteRepository {

    private final FirebaseAuth mAuth;
    private final DatabaseReference mUsersData;

    @Inject
    public FirebaseRemoteRepository(FirebaseAuth auth, @Named("usersData") DatabaseReference usersData) {
        mAuth = auth;
        mUsersData = usersData;
    }
}
