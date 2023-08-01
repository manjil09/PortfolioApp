package com.profile.manjilkoju;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity implements NavigationBarView.OnItemSelectedListener {
    BottomNavigationView bottomNavigationView;
    HomeFragment homeFragment = new HomeFragment();
    ProfileFragment profileFragment = new ProfileFragment();
    MySkillsFragment mySkillsFragment = new MySkillsFragment();
    HobbiesFragment hobbiesFragment = new HobbiesFragment();
    ContactMeFragment contactMeFragment = new ContactMeFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.home);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        //navigates through the respective fragment when bottom navigation bar is used
        if (item.getItemId() == R.id.home){
            getSupportFragmentManager().beginTransaction().replace(R.id.flContainer, homeFragment).commit();
            return true;
        }else if (item.getItemId() == R.id.profile){
            getSupportFragmentManager().beginTransaction().replace(R.id.flContainer, profileFragment).commit();
            return true;
        }else if (item.getItemId() == R.id.skills){
            getSupportFragmentManager().beginTransaction().replace(R.id.flContainer, mySkillsFragment).commit();
            return true;
        }else if (item.getItemId() == R.id.hobbies){
            getSupportFragmentManager().beginTransaction().replace(R.id.flContainer, hobbiesFragment).commit();
            return true;
        }else if (item.getItemId() == R.id.contactMe){
            getSupportFragmentManager().beginTransaction().replace(R.id.flContainer, contactMeFragment).commit();
            return true;
        }
        return false;
    }
}