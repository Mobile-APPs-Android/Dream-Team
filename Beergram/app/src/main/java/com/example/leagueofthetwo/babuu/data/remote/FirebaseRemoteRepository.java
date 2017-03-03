package com.example.leagueofthetwo.babuu.data.remote;

import com.example.leagueofthetwo.babuu.models.Order;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

@Singleton
public class FirebaseRemoteRepository implements IRemoteRepository {

    private final FirebaseAuth mAuth;
    private final DatabaseReference mUsersData;
    private final DatabaseReference mOrdersData;
    private final StorageReference mOrdersPicturesData;

    @Inject
    public FirebaseRemoteRepository(FirebaseAuth auth, @Named("usersData") DatabaseReference usersData, @Named("ordersData") DatabaseReference ordersData, StorageReference ordersPicturesData) {
        mAuth = auth;
        mUsersData = usersData;
        mOrdersData = ordersData;
        mOrdersPicturesData = ordersPicturesData;
    }

    @Override
    public Observable<Order> addOrder(Order order) {
        return Observable.create(e -> {
            FirebaseUser user = mAuth.getCurrentUser();
            if (user != null) {  // TODO: UPLOAD TO FIREBASE AND GET URL
                mOrdersData.child(order.getmId()).setValue(order)
                        .addOnSuccessListener(aVoid -> e.onNext(order));
            }
        });
    }

    @Override
    public Observable<Order[]> getUserOrders() {
        return Observable.create(e -> {
            FirebaseUser user = mAuth.getCurrentUser();
            String userId = user.getUid();
            mOrdersData.orderByChild("mUserId").equalTo(userId).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    List<Order> orders = new ArrayList();
                    for (DataSnapshot child : dataSnapshot.getChildren()) {
                        orders.add(child.getValue(Order.class));
                    }

                    e.onNext(orders.toArray(new Order[]{}));
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        });
    }
}
