package com.example.prot3.beergram.data;

import com.example.prot3.beergram.data.authprovider.IFirebaseAuthProvider;
import com.example.prot3.beergram.models.User;
import com.example.prot3.beergram.utils.IRandomStringProvider;

import io.reactivex.Observable;

public class Repository implements IRepository {
    private final IFirebaseAuthProvider firebaseAuthProvider;
    private final IRandomStringProvider randomStringProvider;

//    @Inject
    public Repository(IFirebaseAuthProvider firebaseAuthProvider, IRandomStringProvider randomStringProvider) {
        this.firebaseAuthProvider = firebaseAuthProvider;
        this.randomStringProvider = randomStringProvider;
    }

    @Override
    public Observable<String> createUser(String email, String password, String firstName, String lastName, String address) {
        final String[] username = {null};
        return firebaseAuthProvider.createUser(email, password, firstName, lastName, address)
                .map(isSuccess -> username[0]);
    }

    @Override
    public Observable<String> loginUser(String email, String password) {
        final String[] username = {null};
        return firebaseAuthProvider.loginUser(email, password)
                .map(isSuccess -> username[0]);
    }

    @Override
    public Observable<User> getCurrentUser() {
        return null;
    }

    @Override
    public Observable<Boolean> logoutUser() {
        return null;
    }

    @Override
    public Observable<User> updateAccountInfo(String firstName, String lastName, String email, String address, String credentialsEmail, String credentialsPassword) {
        return null;
    }

    @Override
    public Observable<Boolean> getIsFirstTimeForUser() {
        return null;
    }
}
