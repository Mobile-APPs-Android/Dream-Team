package com.example.leagueofthetwo.babuu.data.local;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;

import com.example.leagueofthetwo.babuu.data.local.LocalDb.ILocalDbRepository;
import com.example.leagueofthetwo.babuu.models.Order;
import com.example.leagueofthetwo.babuu.models.Position;
import com.example.leagueofthetwo.babuu.models.User;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

@Singleton
public class LocalRepository implements ILocalRepository {

    private final Context mContext;

    private final SharedPreferences mPreferences;
    private final SharedPreferences.Editor mPrefEditor;

    private static final Boolean DEFAULT_FIRST_TIME_VALUE = true;
    private static final Boolean NOT_FIRST_TIME_FOR_USER_VALUE = false;

    private static final String CURRENT_USER_ID_KEY = "user_id";
    private static final String CURRENT_USER_FIRST_NAME = "user_f_name";
    private static final String CURRENT_USER_LAST_NAME = "user_l_name";
    private static final String CURRENT_USER_ADDRESS = "user_address";
    private static final String CURRENT_USER_EMAIL = "user_email";

    private final ILocalDbRepository mLocalDbRepository;

    @Inject
    public LocalRepository(Context context, ILocalDbRepository localDbRepository) {
        mContext = context;
        mLocalDbRepository = localDbRepository;

        mPreferences = mContext.getSharedPreferences("babuu_preferences", Context.MODE_PRIVATE);
        mPrefEditor = mPreferences.edit();
    }

    @Override
    public Observable<Boolean> getIsFirstTimeForUser(String email) {
        return Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(ObservableEmitter<Boolean> e) throws Exception {
                boolean isFirstTime = false;

                if (email != null) {
                    isFirstTime = mPreferences.getBoolean(email, DEFAULT_FIRST_TIME_VALUE);
                }

                if (isFirstTime) {
                    mPrefEditor.putBoolean(email, NOT_FIRST_TIME_FOR_USER_VALUE);
                    mPrefEditor.apply();
                }

                e.onNext(isFirstTime);
            }
        });
    }

    @Override
    public Observable<Order> getOrderById(String id) {
        return Observable.create(e -> e.onNext(mLocalDbRepository.getOrderById(id)));
    }

    @Override
    public Observable<List<Order>> getCurrentUserOrders() {
        return Observable.create(e -> e.onNext(mLocalDbRepository.getAllOrders()));
    }

    @Override
    public Observable<Boolean> addOrder(Order order) {
        return Observable.create(e -> {
            try {
                mLocalDbRepository.addOrder(order);
                e.onNext(true);
            } catch (Throwable thr) {
                e.onNext(false);
            }
        });
    }

    @Override
    public Observable<Boolean> addManyOrders(Order[] orders) {
        return Observable.create(e -> {
            try {
                mLocalDbRepository.addManyOrders(orders);
                e.onNext(true);
            } catch (Throwable thr) {
                e.onNext(false);
            }
        });
    }

    @Override
    public Observable<Boolean> cleanOrders() {
        return Observable.create(e -> {
            try {
                mLocalDbRepository.cleanOrders();
                e.onNext(true);
            } catch (Throwable thr) {
                e.onNext(false);
            }
        });
    }

    @Override
    public Observable<User> getCurrentUser() {
        return Observable.create(e -> {
            User user = new User();
            user.setmUserId(mPreferences.getString(CURRENT_USER_ID_KEY, null));
            user.setmFirstName(mPreferences.getString(CURRENT_USER_FIRST_NAME, null));
            user.setmLastName(mPreferences.getString(CURRENT_USER_LAST_NAME, null));
            user.setmAddress(mPreferences.getString(CURRENT_USER_ADDRESS, null));
            user.setmEmail(mPreferences.getString(CURRENT_USER_EMAIL, null));

            e.onNext(user);
        });
    }

    @Override
    public Observable<Boolean> cleanCurrentUser() {
        return Observable.create(e -> {
            try {
                mPrefEditor.remove(CURRENT_USER_ID_KEY);
                mPrefEditor.remove(CURRENT_USER_FIRST_NAME);
                mPrefEditor.remove(CURRENT_USER_LAST_NAME);
                mPrefEditor.remove(CURRENT_USER_ADDRESS);
                mPrefEditor.remove(CURRENT_USER_EMAIL);
                mPrefEditor.commit();
                e.onNext(true);
            } catch (Throwable thr) {
                e.onNext(false);
            }
        });
    }

    @Override
    public Observable<Boolean> addCurrentUser(User user) {
        return Observable.create(e -> {
            try {
                mPrefEditor.putString(CURRENT_USER_ID_KEY, user.getmUserId());
                mPrefEditor.putString(CURRENT_USER_FIRST_NAME, user.getmFirstName());
                mPrefEditor.putString(CURRENT_USER_LAST_NAME, user.getmLastName());
                mPrefEditor.putString(CURRENT_USER_ADDRESS, user.getmAddress());
                mPrefEditor.putString(CURRENT_USER_EMAIL, user.getmEmail());
                mPrefEditor.commit();
                e.onNext(true);
            } catch (Throwable thr) {
                e.onNext(false);
            }
        });
    }
}



