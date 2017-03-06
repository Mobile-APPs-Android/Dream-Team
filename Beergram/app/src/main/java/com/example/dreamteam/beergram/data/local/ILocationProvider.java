package com.example.dreamteam.beergram.data.local;

import com.example.dreamteam.beergram.models.Position;

public interface ILocationProvider {
    String getAddress(Position position);

    Position getCurrentPosition();

    void connectLocationListener();

    void disconnectLocationListener();
}
