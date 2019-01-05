package com.example.anas.gitrepos;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

//This Activity's role is to choose the proper fragment to be displayed

public class MainActivity extends AppCompatActivity {

    private final String DEBUG_TAG = "MainActivityTAG";
    private final String TRENDING_FRAGMENT = "Trending Repositories";
    private final String SETTINGS_FRAGMENT = "App Settings";

    private BottomNavigationView mBottomNavigation;
    private TextView titleTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBottomNavigation = findViewById(R.id.bottom_navigation);
        titleTextView = findViewById(R.id.tv_title);

        //Setting Trending Fragment as the default UI on MainActivity Creation
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_fragment_container,new TrendingFragment()).commit();
        titleTextView.setText(TRENDING_FRAGMENT);
    }

    @Override
    protected void onStart() {
        super.onStart();

        setBottomNavigationBar();
    }

    private void setBottomNavigationBar() {

        //Setting an onclick bottom navigation listener to choose the right fragment to be displayed
        mBottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment selectedFragment = null;
                int itemSelected = menuItem.getItemId();

                switch(itemSelected){
                    //Case Trending is the selected option
                    case R.id.nav_trending : selectedFragment = new TrendingFragment();
                                             titleTextView.setText(TRENDING_FRAGMENT);
                        break;

                    //Case Settings is the selected option
                    case R.id.nav_settings : selectedFragment = new SettingsFragment();
                                             titleTextView.setText(SETTINGS_FRAGMENT);
                        break;
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.fl_fragment_container,selectedFragment).commit();

                return true;
            }
        });

    }
}
