package com.example.dreamteam.beergram.data.local;

import com.example.dreamteam.beergram.models.Position;
import com.example.dreamteam.beergram.models.User;

import io.reactivex.Observable;

public interface ILocalRepository {
    Observable<Boolean> getIsFirstTimeForUser(String email);

    Observable<User> getCurrentUser();

    Observable<Boolean> cleanCurrentUser();

    Observable<Boolean> addCurrentUser(User user);

    Observable<String> getAddress(Position position);

    Observable<Position> getCurrentPosition();

    Observable<Boolean> connectLocationListener();

    Observable<Boolean> disconnectLocationListener();
}
