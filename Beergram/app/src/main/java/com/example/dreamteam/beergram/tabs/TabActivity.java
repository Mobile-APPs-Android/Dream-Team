package com.example.dreamteam.beergram.tabs;

        import android.content.Intent;
        import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
        import android.support.v7.widget.Toolbar;
        import android.view.View;
        import android.widget.Button;
        import com.example.dreamteam.beergram.R;

public class TabActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar toolbar;
    private Button btnSimpleTabs, btnIconTabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btnSimpleTabs = (Button) findViewById(R.id.btnSimpleTabs);
        btnIconTabs = (Button) findViewById(R.id.btnIconTabs);;

        btnSimpleTabs.setOnClickListener(this);
        btnIconTabs.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSimpleTabs:
                startActivity(new Intent(TabActivity.this, SimpleTabsActivity.class));
                break;
            case R.id.btnIconTabs:
                startActivity(new Intent(TabActivity.this, IconTabsActivity.class));
                break;
        }
    }
}

/*
import android.app.ActionBar;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import com.example.dreamteam.beergram.R;

public class TabActivity extends AppCompatActivity implements ActionBar.TabListener {
    public static TabActivity instance;
    private OneFragment fragmentOne;
    private TwoFragment fragmentTwo;
    private TabLayout allTabs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instance=this;
        setContentView(R.layout.activity_tab);
        getAllWidgets();
        setupTabLayout();
    }
    public static TabActivity getInstance() {
        return instance;
    }
    private void getAllWidgets() {
        allTabs = (TabLayout) findViewById(R.id.tabs);
    }
    private void setupTabLayout() {
        fragmentOne = new OneFragment();
        fragmentTwo = new TwoFragment();
        allTabs.addTab(allTabs.newTab().setText("ONE"),true);
        allTabs.addTab(allTabs.newTab().setText("TWO"));
    }

    private void setCurrentTabFragment(int tabPosition)
    {
        switch (tabPosition)
        {
            case 0 :
                replaceFragment(fragmentOne);
                break;
            case 1 :
                replaceFragment(fragmentTwo);
                break;
        }
    }
    public void replaceFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frame_container, fragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.commit();
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, android.app.FragmentTransaction ft) {
        setCurrentTabFragment(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, android.app.FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, android.app.FragmentTransaction ft) {

    }
}
*/