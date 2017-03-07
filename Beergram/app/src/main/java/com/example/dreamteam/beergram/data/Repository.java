package com.example.dreamteam.beergram.data;

import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.util.Log;

import com.example.dreamteam.beergram.data.authprovider.IAuthProvider;
import com.example.dreamteam.beergram.data.local.ILocalRepository;
import com.example.dreamteam.beergram.data.remote.IRemoteRepository;
import com.example.dreamteam.beergram.data.storage.IStorageRepository;
import com.example.dreamteam.beergram.models.Position;
import com.example.dreamteam.beergram.models.Post;
import com.example.dreamteam.beergram.models.User;
import com.example.dreamteam.beergram.utils.IRandomStringProvider;

import java.io.File;
import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

@Singleton
public class Repository implements IRepository {

    private final IStorageRepository storageRef;
    private final IAuthProvider authProvider;
    private final IRemoteRepository remoteRepository;
    private final ILocalRepository localRepository;
    private final IRandomStringProvider randomStringProvider;

    @Inject
    public Repository(
            IStorageRepository storageRef,
            IAuthProvider authProvider,
            IRemoteRepository remoteRepository,
            ILocalRepository localRepository,
            IRandomStringProvider randomStringProvider) {
        this.storageRef = storageRef;
        this.authProvider = authProvider;
        this.remoteRepository = remoteRepository;
        this.localRepository = localRepository;
        this.randomStringProvider = randomStringProvider;
    }

    @Override
    public Observable<String> createUser(String email, String password, String firstName, String lastName, String address) {
        final String[] username = {null};
        return authProvider.createUser(email, password, firstName, lastName, address)
                .switchMap(user -> {
                    Log.d("asdf", "PENCDHO");
                    username[0] = user.getEmail();
                    return this.localRepository.addCurrentUser(user);
                })
                .map(isSuccess -> username[0]);
    }

    @Override
    public Observable<String> loginUser(String email, String password) {
        final String[] username = {null};
        return this.authProvider.loginUser(email, password)
                .switchMap(user -> {
                    username[0] = user.getFirstName();
                    return this.localRepository.addCurrentUser(user);
                })
                .map(isSuccess -> username[0]);
    }

    @Override
    public Observable<User> getCurrentUser() {
        return this.localRepository.getCurrentUser();
    }

    @Override
    public Observable<Boolean> logoutUser() {
        return this.authProvider.logoutUser()
                .switchMap(isSuccess -> this.localRepository.cleanCurrentUser());
    }

    @Override
    public Observable<User> updateAccountInfo(String firstName, String lastName, String email, String address, String credentialsEmail, String credentialsPassword) {
        User[] resUser = {null};
        return this.authProvider.updateAccountInfo(firstName, lastName, email, address, credentialsEmail, credentialsPassword)
                .switchMap(user -> {
                    resUser[0] = user;
                    return this.localRepository.cleanCurrentUser();
                })
                .switchMap(isSuccess -> this.localRepository.addCurrentUser(resUser[0]))
                .switchMap(isSuccess -> this.localRepository.getCurrentUser());
    }

    public Observable<ArrayList<String>> getAllUsers() {
        return this.remoteRepository.getAllUserEmails();
    }

    @Override
    public Observable<Boolean> savePicture(File image) {
        return this.storageRef.saveImage(image);
    }

    @Override
    public Observable<Post> postLocationToFriends() {
        Post post = new Post(0, 0, "");
        return this.localRepository.getCurrentPosition()
            .switchMap(position -> {
                post.setLatitude(position.getmLatitude());
                post.setLongitude(position.getmLongtitude());

                return this.remoteRepository.addPost(post);
            });
    }

    @Override
    public Observable<String> getAddress(Position position) {
        return  this.localRepository.getAddress(position);
    }

    @Override
    public Observable<Position> getCurrentPosition() {
        return this.localRepository.getCurrentPosition();
    }

    @Override
    public Observable<Boolean> connectLocationListener() {
        return this.localRepository.connectLocationListener();
    }

    @Override
    public Observable<Boolean> disconnectLocationListener() {
        return this.localRepository.disconnectLocationListener();
    }

    public Observable<ArrayList<String>> getContacts(ContentResolver resolver) {
        return Observable.create(new ObservableOnSubscribe<ArrayList<String>>() {
            @Override
            public void subscribe(ObservableEmitter<ArrayList<String>> e) throws Exception {
                ArrayList<String> contactNames = new ArrayList<String>();
                Cursor phones = resolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,null,null, null);
                while (phones.moveToNext())
                {
                    String name=phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                    contactNames.add(name);
                    String phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                }
                phones.close();
                e.onNext(contactNames);
            }
        });
    }
}
