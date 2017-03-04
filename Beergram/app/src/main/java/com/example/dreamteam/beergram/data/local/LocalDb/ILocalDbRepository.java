package com.example.dreamteam.beergram.data.local.LocalDb;

import com.example.dreamteam.beergram.models.Order;

import java.util.List;

public interface ILocalDbRepository {
    void addOrder(Order order);

    Order getOrderById(String id);

    Order getOrderByUserId(String userId);

    List<Order> getAllOrders();

    void cleanOrders();

    void addManyOrders(Order[] orders);
}
