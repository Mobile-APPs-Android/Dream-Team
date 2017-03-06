package com.example.dreamteam.beergram.newsfeed;

import com.example.dreamteam.beergram.BasePresenter;
import com.example.dreamteam.beergram.BaseView;
import com.example.dreamteam.beergram.models.Position;

public interface NewsfeedContract {
    interface View extends BaseView<NewsfeedContract.Presenter> {
        void notifyPostShared();

        void showSearchActivity(String searchValue);

        void showCurrentPosition(Position position);

        void returnActivityResult(String address);

        void setMyLocationBtn();
    }

    interface Presenter extends BasePresenter {
        void postLocationToFriends();

        void onGetPosition(Position position);

        void onStart();

        void onBack();

        void onStop();

        void onMyLocation();
    }
}
