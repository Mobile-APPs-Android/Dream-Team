package com.example.dreamteam.beergram.data.remote;

import com.example.dreamteam.beergram.models.Post;
import com.example.dreamteam.beergram.models.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.internal.functions.ObjectHelper;

@Singleton
public class FirebaseRemoteRepository implements IRemoteRepository {

    private final FirebaseAuth auth;
    private final DatabaseReference usersData;

    @Inject
    public FirebaseRemoteRepository(FirebaseAuth auth, @Named("usersData") DatabaseReference usersData) {
        this.auth = auth;
        this.usersData = usersData;
    }

    public Observable<ArrayList<String>> getAllUserEmails() {
        return Observable.create(new ObservableOnSubscribe<ArrayList<String>>() {
            @Override
            public void subscribe(ObservableEmitter<ArrayList<String>> e) throws Exception {
                usersData.addValueEventListener(new ValueEventListener() {
                    @Override
                    public  void onDataChange(DataSnapshot dataSnapshot) {
                        ArrayList<String> users = collectUsers((Map<String,Object>) dataSnapshot.getValue());
                        e.onNext(users);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }

                    private ArrayList<String> collectUsers(Map<String, Object> users) {
                        ArrayList<String> usersEmails = new ArrayList<>();
                        for (Map.Entry<String, Object> entry : users.entrySet()){

                            Map singleUser = (Map) entry.getValue();
                            String userEmail = (String) singleUser.get("email");
                            userEmail = userEmail.split("@")[0];
                            usersEmails.add(userEmail);
                        }

                        return usersEmails;
                    }
                });
            }
        });
    }

    public Observable<Post> addPost(Post post) {
        return Observable.create(new ObservableOnSubscribe<Post>() {
            @Override
            public void subscribe(ObservableEmitter<Post> e) throws Exception {
                FirebaseUser user = auth.getCurrentUser();
                if (user != null) {
                    usersData.child(user.getUid()).child("lastPost").setValue(post);
                }

                e.onNext(post);
            }
        });
    }
}
