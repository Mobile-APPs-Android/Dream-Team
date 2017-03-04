package com.example.dreamteam.beergram.tabs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dreamteam.beergram.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class TabsFragment extends Fragment {


    public TabsFragment() {
        // Required empty public constructor
    }

    public static TabsFragment createFragment(int position) {
        TabsFragment fragment = new TabsFragment();
        Bundle args = new Bundle();
        args.putInt("position", position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_tabs, container, false);

        int position = this.getArguments().getInt("position");

        ((TextView) root.findViewById(R.id.tabs_pager))
                .setText(String.format("Position %d", position));

        return root;
    }

}