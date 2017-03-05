package com.example.dreamteam.beergram.newsfeed;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.astuetz.PagerSlidingTabStrip;
import com.example.dreamteam.beergram.R;

public class NewsfeedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newsfeed);

//        ViewPager pager = (ViewPager) findViewById(R.id.tabs_pager);
//        pager.setAdapter(new TabsNavigationAdapter(getSupportFragmentManager()));
//
//        // Bind the tabs to the ViewPager
//        PagerSlidingTabStrip tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);
//        tabs.setViewPager(pager);
    }

//    public class TabsNavigationAdapter extends FragmentStatePagerAdapter {
//        public TabsNavigationAdapter(FragmentManager fm){
//            super(fm);
//        }
//
//        @Override
//        public int getCount() {
//            return 5;
//        }
//
//        @Override
//        public Fragment getItem(int position) {
//            switch (position){
//                case 0: //NewsfeedActivity
//                case 1: //SearchActivity
//                case 2: //AddNewBeerSpotActivity
//                case 3: //ProfileActivity
//                case 4: //ContactsActivity
//            }
//
//            return TabsFragment.createFragment(position);
//        }
//
//        @Override
//        public CharSequence getPageTitle(int position) {
//            return String.format("Tab %d", position);
//        }
//    }
}