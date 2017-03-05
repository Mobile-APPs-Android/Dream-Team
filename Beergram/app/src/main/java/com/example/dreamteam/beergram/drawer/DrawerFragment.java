package com.example.dreamteam.beergram.drawer;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.astuetz.PagerSlidingTabStrip;
import com.example.dreamteam.beergram.R;
import com.example.dreamteam.beergram.camera.CameraFragment;

public class DrawerFragment extends Fragment {
    private View rootView;


    public DrawerFragment() {
    }

    public static CameraFragment newInstance() {
        return new CameraFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.rootView = inflater.inflate(R.layout.fragment_drawer, container, false);

        ViewPager pager = (ViewPager) this.rootView.findViewById(R.id.tabs);
        pager.setAdapter(new PagerAdapter(this.getActivity().getSupportFragmentManager()));

        // Bind the tabs to the ViewPager
        PagerSlidingTabStrip tabs = (PagerSlidingTabStrip) this.rootView.findViewById(R.id.tabs);
        tabs.setViewPager(pager);

        return this.rootView;
    }



}
