package com.example.dreamteam.beergram.data.remote;

import com.example.dreamteam.beergram.models.Post;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

@Singleton
public class FirebaseRemoteRepository implements IRemoteRepository {

    private final FirebaseAuth auth;
    private final DatabaseReference usersData;

    @Inject
    public FirebaseRemoteRepository(FirebaseAuth auth, @Named("usersData") DatabaseReference usersData) {
        this.auth = auth;
        this.usersData = usersData;
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
