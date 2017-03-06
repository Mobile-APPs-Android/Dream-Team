package com.example.dreamteam.beergram.data;

import com.example.dreamteam.beergram.models.Position;
import com.example.dreamteam.beergram.models.Post;
import com.example.dreamteam.beergram.models.User;

import java.io.File;
import java.util.ArrayList;

import io.reactivex.Observable;

public interface IRepository {
    Observable<String> createUser(String email, String password, String firstName, String lastName, String address);

    Observable<String> loginUser(String email, String password);

    Observable<User> getCurrentUser();

    Observable<Boolean> logoutUser();

    Observable<User> updateAccountInfo(String firstName, String lastName, String email, String address, String credentialsEmail, String credentialsPassword);

    public Observable<ArrayList<String>> getAllUsers();

    Observable<Boolean> savePicture(File image);

    public Observable<Post> postLocationToFriends();

    Observable<String> getAddress(Position position);

    Observable<Position> getCurrentPosition();

    Observable<Boolean> connectLocationListener();

    Observable<Boolean> disconnectLocationListener();
}
