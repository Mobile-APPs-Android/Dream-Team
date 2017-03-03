package com.example.leagueofthetwo.babuu.data;

import com.example.leagueofthetwo.babuu.data.authprovider.IAuthProvider;
import com.example.leagueofthetwo.babuu.data.local.ILocalRepository;
import com.example.leagueofthetwo.babuu.data.remote.IRemoteRepository;
import com.example.leagueofthetwo.babuu.models.Order;
import com.example.leagueofthetwo.babuu.models.Position;
import com.example.leagueofthetwo.babuu.models.User;
import com.example.leagueofthetwo.babuu.utils.IRandomStringProvider;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

@Singleton
public class Repository implements IRepository {

    private final IAuthProvider mAuthProvider;
    private final IRemoteRepository mRemoteRepository;
    private final ILocalRepository mLocalRepository;
    private final IRandomStringProvider mRandomStringProvider;

    @Inject
    public Repository(
            IAuthProvider authProvider,
            IRemoteRepository remoteRepository,
            ILocalRepository localRepository,
            IRandomStringProvider randomStringProvider) {
        mAuthProvider = authProvider;
        mRemoteRepository = remoteRepository;
        mLocalRepository = localRepository;
        mRandomStringProvider = randomStringProvider;
    }

    @Override
    public Observable<String> createUser(String email, String password, String firstName, String lastName, String address) {
        final String[] username = {null};
        return mAuthProvider.createUser(email, password, firstName, lastName, address)
                .switchMap(user -> {
                    username[0] = user.getmEmail();
                    return mLocalRepository.addCurrentUser(user);
                })
                .map(isSuccess -> username[0]);
    }

    @Override
    public Observable<String> loginUser(String email, String password) {
        final String[] username = {null};
        return mAuthProvider.loginUser(email, password)
                .switchMap(user -> {
                    username[0] = user.getmFirstName();
                    return mLocalRepository.addCurrentUser(user);
                })
                .switchMap(isSuccess -> mRemoteRepository.getUserOrders())
                .switchMap(orders -> mLocalRepository.addManyOrders(orders))
                .map(isSuccess -> username[0]);
    }

    @Override
    public Observable<User> getCurrentUser() {
        return mLocalRepository.getCurrentUser();
    }

    @Override
    public Observable<Boolean> logoutUser() {
        return mAuthProvider.logoutUser()
                .switchMap(isSuccess -> mLocalRepository.cleanCurrentUser())
                .switchMap(isSuccess -> mLocalRepository.cleanOrders());
    }

    @Override
    public Observable<User> updateAccountInfo(String firstName, String lastName, String email, String address, String credentialsEmail, String credentialsPassword) {
        User[] resUser = {null};
        return mAuthProvider.updateAccountInfo(firstName, lastName, email, address, credentialsEmail, credentialsPassword)
                .switchMap(user -> {
                    resUser[0] = user;
                    return mLocalRepository.cleanCurrentUser();
                })
                .switchMap(isSuccess -> mLocalRepository.addCurrentUser(resUser[0]))
                .switchMap(isSuccess -> mLocalRepository.getCurrentUser());
    }

    @Override
    public Observable<Order> saveOrder(Order order) {
        return mLocalRepository.addOrder(order)
                .switchMap(isSuccess -> mLocalRepository.getCurrentUser())
                .switchMap(user -> {
                    order.setmId(mRandomStringProvider.nextString());
                    order.setmUserId(user.getmUserId());
                    return mRemoteRepository.addOrder(order);
                });
    }

    @Override
    public Observable<Order[]> getUserOrders() {
        return mLocalRepository.getCurrentUserOrders()
                .switchMap(orders -> Observable.just(orders.toArray(new Order[]{})));
    }

    @Override
    public Observable<Boolean> getIsFirstTimeForUser() {
        return mLocalRepository.getCurrentUser()
                .switchMap(u -> mLocalRepository.getIsFirstTimeForUser(u.getmEmail()));
    }

}
