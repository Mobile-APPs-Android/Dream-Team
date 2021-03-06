package com.example.dreamteam.beergram.data.remote;

import com.example.dreamteam.beergram.models.Position;
import com.example.dreamteam.beergram.models.Post;
import com.example.dreamteam.beergram.models.User;

import java.util.ArrayList;

import io.reactivex.Observable;

public interface IRemoteRepository {
    Observable<Post> addPost(Post post);

    public Observable<ArrayList<String>> getAllUserEmails();
}
