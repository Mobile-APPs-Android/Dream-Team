package com.example.dreamteam.beergram.data;

import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class FirebaseRepositoryModule {
    private static final String USERS_DB_CHILD_STRING = "users";

    private final FirebaseAuth mAuth;
    private final FirebaseAuth.AuthStateListener mAuthListener;

    private final DatabaseReference mUsersData;

    public FirebaseRepositoryModule() {
        mUsersData = FirebaseDatabase.getInstance().getReference().child(USERS_DB_CHILD_STRING);

        mAuth = FirebaseAuth.getInstance();

        mAuthListener = firebaseAuth -> {
            FirebaseUser user = firebaseAuth.getCurrentUser();
            if (user != null) {
                Log.d("", "User NOT logged");
            } else {
                Log.d("", "User Logged");
            }
        };
    }

    @Singleton
    @Provides
    FirebaseAuth provideAuth() {
        return mAuth;
    }

    @Singleton
    @Provides
    FirebaseAuth.AuthStateListener provideListener() {
        return mAuthListener;
    }

    @Singleton
    @Provides
    @Named("usersData")
    DatabaseReference provideUserDataReference() {
        return mUsersData;
    }
}
