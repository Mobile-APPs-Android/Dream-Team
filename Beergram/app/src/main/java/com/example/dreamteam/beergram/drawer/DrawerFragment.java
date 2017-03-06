package com.example.dreamteam.beergram.drawer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dreamteam.beergram.R;
import com.example.dreamteam.beergram.aboutapp.AboutAppActivity;
import com.example.dreamteam.beergram.auth.logout.LogoutActivity;
import com.example.dreamteam.beergram.camera.CameraActivity;
import com.example.dreamteam.beergram.newsfeed.NewsfeedActivity;
import com.example.dreamteam.beergram.profile.ProfileActivity;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;

public class DrawerFragment extends Fragment {

    private View view;

    public DrawerFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_drawer, container, false);

        this.view = rootView;

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();

        this.setupDrawer();
    }

    protected void setupDrawer() {
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.tb_drawer);

        new DrawerBuilder()
                .withActivity(getActivity())
                .withToolbar(toolbar)
                .addDrawerItems(
                        this.createCameraDrawerItem(),
                        this.createProfileDrawerItem(),
                        this.createNewsFeedDrawerItem(),
                        this.createCameraDrawerItem(),
                        this.createAboutAppDrawerItem(),
                        this.createLogoutDrawerItem()
                        //this.createContactsDrawerItem()
                )
                .build();
    }

    private PrimaryDrawerItem createCameraDrawerItem() {
        return new PrimaryDrawerItem()
                .withIdentifier(R.integer.CameraDrawerItemIdentifier)
                .withName(R.string.CameraDrawerItemName)
                .withIcon(R.drawable.ic_camera)
                .withSelectedIcon(R.drawable.ic_camera_active)
                .withOnDrawerItemClickListener((view, position, drawerItem) -> {
                    Intent intent = new Intent(getContext(), CameraActivity.class);
                    startActivity(intent);
                    return true;
                });
    }

    private PrimaryDrawerItem createNewsFeedDrawerItem() {
        return new PrimaryDrawerItem()
                .withIdentifier(R.integer.NewsFeedDrawerItemIdentifier)
                .withName(R.string.NewsFeedDrawerItemName)
                .withIcon(R.drawable.ic_home_inactive)
                .withSelectedIcon(R.drawable.ic_homes_active)
                .withOnDrawerItemClickListener((view, position, drawerItem) -> {
                    Intent intent = new Intent(getContext(), NewsfeedActivity.class);
                    startActivity(intent);
                    return true;
                });
    }

    private PrimaryDrawerItem createProfileDrawerItem() {
        return new PrimaryDrawerItem()
                .withIdentifier(R.integer.ProfileDrawerItemIdentifier)
                .withName(R.string.ProfileDrawerItemName)
                .withIcon(R.drawable.ic_profiles)
                .withSelectedIcon(R.drawable.ic_profile_active)
                .withOnDrawerItemClickListener((view, position, drawerItem) -> {
                    Intent intent = new Intent(getContext(), ProfileActivity.class);
                    startActivity(intent);
                    return true;
                });
    }

        private PrimaryDrawerItem createAboutAppDrawerItem() {
        return new PrimaryDrawerItem()
                .withIdentifier(R.integer.AboutAppDrawerItemIdentifier)
                .withName(R.string.AboutAppDrawerItemName)
                .withIcon(R.drawable.ic_about_us_inactive)
                .withSelectedIcon(R.drawable.ic_about_us)
                .withOnDrawerItemClickListener((view, position, drawerItem) -> {
                    Intent intent = new Intent(getContext(), AboutAppActivity.class);
                    startActivity(intent);
                    return true;
                });
    }

    private PrimaryDrawerItem createLogoutDrawerItem() {
        return new PrimaryDrawerItem()
                .withIdentifier(R.integer.LogoutDrawerItemIdentifier)
                .withName(R.string.LogoutDrawerItemName)
                .withIcon(R.drawable.ic_logout_inactive)
                .withSelectedIcon(R.drawable.ic_logout_active)
                .withOnDrawerItemClickListener((view, position, drawerItem) -> {
                    Intent intent = new Intent(getContext(), LogoutActivity.class);
                    startActivity(intent);
                    return true;
                });
    }

//    private PrimaryDrawerItem createContactsDrawerItem() {
//        return new PrimaryDrawerItem()
//                .withIdentifier(R.integer.ContactsDrawerItemIdentifier)
//                .withName(R.string.ContactsDrawerItemName)
//                .withIcon(R.drawable.ic_contactsss)
//                .withSelectedIcon(R.drawable.ic_contacts_active)
//                .withOnDrawerItemClickListener((view, position, drawerItem) -> {
//                    Intent intent = new Intent(getContext(), ContactsActivity.class);
//                    startActivity(intent);
//                    return true;
//                });
//    }

}