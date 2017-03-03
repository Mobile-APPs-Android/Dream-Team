package com.example.leagueofthetwo.babuu.data.local.LocalDb;

import com.example.leagueofthetwo.babuu.models.Order;

import java.util.List;

public interface ILocalDbRepository {
    void addOrder(Order order);

    Order getOrderById(String id);

    Order getOrderByUserId(String userId);

    List<Order> getAllOrders();

    void cleanOrders();

    void addManyOrders(Order[] orders);
}
