package com.example.dreamteam.beergram.data.remote;

import com.example.dreamteam.beergram.models.Order;

import io.reactivex.Observable;

public interface IRemoteRepository {
    Observable<Order> addOrder(Order order);

    Observable<Order[]> getUserOrders();
}
