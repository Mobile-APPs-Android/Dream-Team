package com.example.dreamteam.beergram.newsfeed;

import com.example.dreamteam.beergram.BasePresenter;
import com.example.dreamteam.beergram.BaseView;

public interface NewsfeedContract {
    interface View extends BaseView<NewsfeedContract.Presenter> {
        void notifyPostShared();
    }

    interface Presenter extends BasePresenter {
        void postLocationToFriends();
    }
}
