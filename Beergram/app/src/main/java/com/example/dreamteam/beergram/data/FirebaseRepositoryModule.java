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

    private final StorageReference storageRef;
    private final FirebaseAuth auth;
    private final FirebaseAuth.AuthStateListener authListener;

    private final DatabaseReference usersData;

    public FirebaseRepositoryModule() {
        storageRef = FirebaseStorage.getInstance().getReference();

        this.usersData = FirebaseDatabase.getInstance().getReference().child(USERS_DB_CHILD_STRING);
        this.auth = FirebaseAuth.getInstance();

        this.authListener = firebaseAuth -> {
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
    StorageReference provideStorageReference() { return this.storageRef; }

    @Singleton
    @Provides
    FirebaseAuth provideAuth() {
        return this.auth;
    }

    @Singleton
    @Provides
    FirebaseAuth.AuthStateListener provideListener() {
        return this.authListener;
    }

    @Singleton
    @Provides
    @Named("usersData")
    DatabaseReference provideUserDataReference() {
        return this.usersData;
    }
}
