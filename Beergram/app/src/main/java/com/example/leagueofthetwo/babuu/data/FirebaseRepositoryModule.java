package com.example.leagueofthetwo.babuu.data;

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
    private static final String ORDERS_DB_CHILD_STRING = "orders";

    private static final String ORDERS_PICTURES_CHILD_STRING = "orders";

    private final FirebaseAuth mAuth;
    private final FirebaseAuth.AuthStateListener mAuthListener;

    private final DatabaseReference mOrdersData;
    private final DatabaseReference mUsersData;

    private final StorageReference mOrdersPicturesData;

    public FirebaseRepositoryModule() {
        mUsersData = FirebaseDatabase.getInstance().getReference().child(USERS_DB_CHILD_STRING);
        mOrdersData = FirebaseDatabase.getInstance().getReference().child(ORDERS_DB_CHILD_STRING);

        mOrdersPicturesData = FirebaseStorage.getInstance().getReference().child(ORDERS_PICTURES_CHILD_STRING);

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

    @Singleton
    @Provides
    @Named("ordersData")
    DatabaseReference provideOrdersDataReference() {
        return mOrdersData;
    }

    @Singleton
    @Provides
    StorageReference provideOrdersPicturesDataReference() {
        return mOrdersPicturesData;
    }
}
