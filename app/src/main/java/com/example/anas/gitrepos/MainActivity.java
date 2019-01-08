package com.example.anas.gitrepos;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

//This Activity's role is to choose the proper fragment to be displayed

public class MainActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {

    public static String orderParam;
    private final String DEBUG_TAG = "MainActivityTAG";
    private final String TRENDING_FRAGMENT = "Trending Repositories";
    private final String SETTINGS_FRAGMENT = "App Settings";
    private BottomNavigationView mBottomNavigation;
    private TextView titleTextView;
    private Fragment selectedFragment;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(DEBUG_TAG,"MAIN - ONCREATE");
        super.onCreate(null);
        setContentView(R.layout.activity_main);

        mBottomNavigation = findViewById(R.id.bottom_navigation);
        titleTextView = findViewById(R.id.tv_title);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        orderParam = sharedPreferences.getString(getString(R.string.key_pref_order),getString(R.string.descending_pref_order_value));
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);

        //Setting Trending Fragment as the default UI on MainActivity Creation
        selectedFragment = new TrendingFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.fl_fragment_container,selectedFragment).commit();
        titleTextView.setText(TRENDING_FRAGMENT);

        //Setting the bottom navigation listener
        setBottomNavigationBarListener();

    }

    private void setBottomNavigationBarListener() {

        //Setting an onclick bottom navigation listener to choose the right fragment to be displayed
        mBottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                selectedFragment = null;
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

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if(key.equals(getString(R.string.key_pref_order))) {
            orderParam = sharedPreferences.getString(key, getString(R.string.descending_pref_order_value));

        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        sharedPreferences.unregisterOnSharedPreferenceChangeListener(this);
    }
}
