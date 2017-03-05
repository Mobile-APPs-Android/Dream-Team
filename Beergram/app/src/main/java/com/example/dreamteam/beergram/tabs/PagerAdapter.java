//package com.example.dreamteam.beergram.tabs;
//
//import android.support.v4.app.Fragment;
//import android.support.v4.app.FragmentManager;
//import android.support.v4.app.FragmentStatePagerAdapter;
//
//import com.example.dreamteam.beergram.auth.login.LoginFragment;
//import com.example.dreamteam.beergram.auth.register.RegisterFragment;
//import com.example.dreamteam.beergram.camera.CameraFragment;
//
//public class PagerAdapter extends FragmentStatePagerAdapter {
//    int mNumOfTabs;
//
//    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
//        super(fm);
//        this.mNumOfTabs = NumOfTabs;
//    }
//
//    @Override
//    public Fragment getItem(int position) {
//
//        switch (position) {
//            case 0:
//                CameraFragment tab1 = new CameraFragment();
//                return tab1;
//            case 1:
//                LoginFragment tab2 = new LoginFragment();
//                return tab2;
//            case 2:
//                RegisterFragment tab3 = new RegisterFragment();
//                return tab3;
//            default:
//                return null;
//        }
//    }
//
//    @Override
//    public int getCount() {
//        return mNumOfTabs;
//    }
//}