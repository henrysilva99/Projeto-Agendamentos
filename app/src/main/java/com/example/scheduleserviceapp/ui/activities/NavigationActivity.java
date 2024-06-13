package com.example.scheduleserviceapp.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.example.scheduleserviceapp.R;
import com.example.scheduleserviceapp.ui.fragments.ExploreFragment;
import com.example.scheduleserviceapp.ui.fragments.HomeFragment;
import com.example.scheduleserviceapp.ui.fragments.ProfileFragment;
import com.example.scheduleserviceapp.ui.fragments.ScheduleFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class NavigationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        final BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
    }

    private final BottomNavigationView.OnNavigationItemSelectedListener navListener = item -> {
        Fragment selectedFragment = null;
        int itemId = item.getItemId();

        if (itemId == R.id.home) {
            selectedFragment = new HomeFragment();
        } else if (itemId == R.id.explore) {
            selectedFragment = new ExploreFragment();
        } else if (itemId == R.id.schedule) {
            selectedFragment = new ScheduleFragment();
        } else if (itemId == R.id.profile) {
            selectedFragment = new ProfileFragment();
        }

        if (selectedFragment != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
        }

        return true;
    };


}