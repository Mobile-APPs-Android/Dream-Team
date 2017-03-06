package com.example.dreamteam.beergram.data.authprovider;

import android.support.annotation.NonNull;

import com.example.dreamteam.beergram.models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

@Singleton
public class FirebaseAuthProvider implements IAuthProvider {

    final FirebaseAuth auth;
    final FirebaseAuth.AuthStateListener authListener;
    final DatabaseReference usersData;

    @Inject
    public FirebaseAuthProvider(FirebaseAuth auth, FirebaseAuth.AuthStateListener authListener, @Named("usersData") DatabaseReference userData) {
        this.auth = auth;
        this.authListener = authListener;
        this.usersData = userData;
    }

    @Override
    public Observable<User> createUser(String email, String password, String firstName, String lastName, String address) {
        return Observable.create(new ObservableOnSubscribe<User>() {
            @Override
            public void subscribe(ObservableEmitter<User> e) throws Exception {
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            FirebaseUser user = auth.getCurrentUser();

                            User newUser = new User(email, firstName, lastName, address);
                            usersData.child(user.getUid()).setValue(newUser);

                            e.onNext(newUser);
                        }
                    });
            }
        });

    }

    @Override
    public Observable<User> loginUser(String email, String password) {
        return Observable.create(new ObservableOnSubscribe<User>() {
            @Override
            public void subscribe(ObservableEmitter<User> e) throws Exception {
                auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                FirebaseUser currUser = auth.getCurrentUser();

                                if (currUser != null) {
                                    usersData.child(currUser.getUid())
                                            .addValueEventListener(new ValueEventListener() {
                                                @Override
                                                public void onDataChange(DataSnapshot dataSnapshot) {
                                                    User user = dataSnapshot.getValue(User.class);
                                                    e.onNext(user);
                                                }

                                                @Override
                                                public void onCancelled(DatabaseError databaseError) {

                                                }
                                            });
                                }
                            } else {
                                User u = new User();
                                e.onNext(u);
                            }
                        });
            }
        });
    }

    @Override
    public Observable<User> getCurrentUser() {
        return Observable.create(new ObservableOnSubscribe<User>() {
            @Override
            public void subscribe(ObservableEmitter<User> e) throws Exception {
                FirebaseUser currUser = auth.getCurrentUser();

                if (currUser != null) {
                    usersData
                            .child(currUser.getUid())
                            .addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    User user = dataSnapshot.getValue(User.class);
                                    user.setUserId(currUser.getUid());
                                    e.onNext(user);
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });
                } else {
                    e.onNext(new User());
                }
            }
        });
    }

    @Override
    public Observable<Boolean> logoutUser() {
        return Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(ObservableEmitter<Boolean> e) throws Exception {
                auth.signOut();
                e.onNext(true);
            }
        });
    }

    @Override
    public Observable<User> updateAccountInfo(String firstName, String lastName, String email, String address, String credentialsEmail, String credentialsPassword) {
        return Observable.create(new ObservableOnSubscribe<User>() {
            @Override
            public void subscribe(ObservableEmitter<User> e) throws Exception {
                FirebaseUser currUser = auth.getCurrentUser();
                AuthCredential credential = EmailAuthProvider.getCredential(credentialsEmail, credentialsPassword);

                currUser.reauthenticate(credential)
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                currUser.updateEmail(email)
                                        .addOnCompleteListener(task1 -> {
                                            if (task1.isSuccessful()) {
                                                User updateUser = new User(email, firstName, lastName, address);
                                                updateUser.setUserId(currUser.getUid());

                                                usersData.child(currUser.getUid())
                                                        .setValue(updateUser)
                                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                            @Override
                                                            public void onComplete(@NonNull Task<Void> task1) {
                                                                if (task1.isSuccessful()) {
                                                                    e.onNext(updateUser);
                                                                } else {
                                                                    e.onNext(new User());
                                                                }
                                                            }
                                                        });
                                            } else {
                                                e.onNext(new User());
                                            }
                                        });
                            } else {
                                e.onNext(new User());
                            }
                        });


            }
        });
    }

    @Override
    public void addUserStatusChangeListener() {
        this.auth.addAuthStateListener(this.authListener);
    }

    @Override
    public void removeUserStatusChangeListener() {
        this.auth.removeAuthStateListener(this.authListener);
    }
}
