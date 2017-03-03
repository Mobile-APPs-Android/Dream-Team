package com.example.leagueofthetwo.babuu.data.remote;

import com.example.leagueofthetwo.babuu.models.Order;

import io.reactivex.Observable;

public interface IRemoteRepository {
    Observable<Order> addOrder(Order order);

    Observable<Order[]> getUserOrders();
}
