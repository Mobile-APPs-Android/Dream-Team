package com.example.leagueofthetwo.babuu.data;

import com.example.leagueofthetwo.babuu.models.Order;
import com.example.leagueofthetwo.babuu.models.Position;
import com.example.leagueofthetwo.babuu.models.User;

import io.reactivex.Observable;

public interface IRepository {
    Observable<String> createUser(String email, String password, String firstName, String lastName, String address);

    Observable<String> loginUser(String email, String password);

    Observable<User> getCurrentUser();

    Observable<Boolean> logoutUser();

    Observable<User> updateAccountInfo(String firstName, String lastName, String email, String address, String credentialsEmail, String credentialsPassword);

    Observable<Order> saveOrder(Order order);

    Observable<Order[]> getUserOrders();

    Observable<Boolean> getIsFirstTimeForUser();

}
