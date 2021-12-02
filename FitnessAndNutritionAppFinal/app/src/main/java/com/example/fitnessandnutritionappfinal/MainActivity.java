package com.example.fitnessandnutritionappfinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    //Ref to bottom nav view
    BottomNavigationView bottomNavigationView;
    //===========


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //For the bottom nav bar
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        getSupportFragmentManager().beginTransaction().replace(R.id.main_container, new HomeFragment()).commit();

        bottomNavigationView.setSelectedItemId(R.id.nav_home);


        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                switch(item.getItemId()){
                    case R.id.nav_home:
                        fragment = new HomeFragment();
                        break;
                    case R.id.nav_fitness:
                        fragment = new FitnessFragment();
                        break;
                    case R.id.nav_profile:
                        fragment = new ProfileFragment();
                        break;

                }

                getSupportFragmentManager().beginTransaction().replace(R.id.main_container, fragment).commit();


                return true;
            }
        //===========
        });
    }
}