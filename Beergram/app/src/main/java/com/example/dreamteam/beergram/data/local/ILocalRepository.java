package com.example.dreamteam.beergram.data.local;

import com.example.dreamteam.beergram.models.Order;
import com.example.dreamteam.beergram.models.User;

import java.util.List;

import io.reactivex.Observable;

public interface ILocalRepository {
    Observable<Boolean> getIsFirstTimeForUser(String email);

    Observable<Order> getOrderById(String id);

    Observable<List<Order>> getCurrentUserOrders();

    Observable<Boolean> addOrder(Order order);

    Observable<Boolean> addManyOrders(Order[] orders);

    Observable<Boolean> cleanOrders();

    Observable<User> getCurrentUser();

    Observable<Boolean> cleanCurrentUser();

    Observable<Boolean> addCurrentUser(User user);
}
