package com.example.prot3.beergram.data.authprovider;

import com.example.prot3.beergram.models.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

public class FirebaseAuthProvider implements IFirebaseAuthProvider {
    private final FirebaseAuth auth;
    private final FirebaseAuth.AuthStateListener authListener;
    private final DatabaseReference usersData;

    public FirebaseAuthProvider(FirebaseAuth auth, FirebaseAuth.AuthStateListener authListener, DatabaseReference usersData) {
        this.auth = auth;
        this.authListener = authListener;
        this.usersData = usersData;
    }

    @Override
    public Observable<User> createUser(String email, String password, String firstName, String lastName, String address) {
        return Observable.create(new ObservableOnSubscribe<User>() {
            @Override
            public void subscribe(ObservableEmitter<User> e) throws Exception {
                auth.signInWithEmailAndPassword(email, password)
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
                                    usersData
                                            .child(currUser.getUid())
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
    public void addUserStatusChangeListener() {
        this.auth.addAuthStateListener(this.authListener);
    }

    @Override
    public void removeUserStatusChangeListener() {
        this.auth.removeAuthStateListener(this.authListener);
    }
}
