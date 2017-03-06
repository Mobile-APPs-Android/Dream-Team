package com.example.dreamteam.beergram.data.local;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.dreamteam.beergram.models.Position;
import com.example.dreamteam.beergram.models.User;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

@Singleton
public class LocalRepository implements ILocalRepository {

    private final Context context;

    private final SharedPreferences preferences;
    private final SharedPreferences.Editor prefEditor;

    private static final String CURRENT_USER_ID_KEY = "user_id";
    private static final String CURRENT_USER_FIRST_NAME = "user_f_name";
    private static final String CURRENT_USER_LAST_NAME = "user_l_name";
    private static final String CURRENT_USER_ADDRESS = "user_address";
    private static final String CURRENT_USER_EMAIL = "user_email";

    private final ILocationProvider locationProvider;

    @Inject
    public LocalRepository(Context context, ILocationProvider locationProvider) {
        this.context = context;
        this.locationProvider = locationProvider;

        this.preferences = this.context.getSharedPreferences("babuu_preferences", Context.MODE_PRIVATE);
        this.prefEditor = this.preferences.edit();
    }

    @Override
    public Observable<User> getCurrentUser() {
        return Observable.create(e -> {
            User user = new User();
            user.setUserId(this.preferences.getString(CURRENT_USER_ID_KEY, null));
            user.setFirstName(this.preferences.getString(CURRENT_USER_FIRST_NAME, null));
            user.setLastName(this.preferences.getString(CURRENT_USER_LAST_NAME, null));
            user.setAddress(this.preferences.getString(CURRENT_USER_ADDRESS, null));
            user.setEmail(this.preferences.getString(CURRENT_USER_EMAIL, null));

            e.onNext(user);
        });
    }

    @Override
    public Observable<Boolean> cleanCurrentUser() {
        return Observable.create(e -> {
            try {
                this.prefEditor.remove(CURRENT_USER_ID_KEY);
                this.prefEditor.remove(CURRENT_USER_FIRST_NAME);
                this.prefEditor.remove(CURRENT_USER_LAST_NAME);
                this.prefEditor.remove(CURRENT_USER_ADDRESS);
                this.prefEditor.remove(CURRENT_USER_EMAIL);
                this.prefEditor.commit();
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
                this.prefEditor.putString(CURRENT_USER_ID_KEY, user.getUserId());
                this.prefEditor.putString(CURRENT_USER_FIRST_NAME, user.getFirstName());
                this.prefEditor.putString(CURRENT_USER_LAST_NAME, user.getLastName());
                this.prefEditor.putString(CURRENT_USER_ADDRESS, user.getAddress());
                this.prefEditor.putString(CURRENT_USER_EMAIL, user.getEmail());
                this.prefEditor.commit();
                e.onNext(true);
            } catch (Throwable thr) {
                e.onNext(false);
            }
        });
    }

    @Override
    public Observable<String> getAddress(Position position) {
        return Observable.create(e -> e.onNext(this.locationProvider.getAddress(position)));
    }

    @Override
    public Observable<Position> getCurrentPosition() {
        return Observable.create(e -> e.onNext(this.locationProvider.getCurrentPosition()));
    }

    @Override
    public Observable<Boolean> connectLocationListener() {
        return Observable.create(e -> {
            this.locationProvider.connectLocationListener();
            e.onNext(true);
        });
    }

    @Override
    public Observable<Boolean> disconnectLocationListener() {
        return Observable.create(e -> {
            this.locationProvider.disconnectLocationListener();
            e.onNext(true);
        });
    }
}



