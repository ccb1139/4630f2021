package com.example.assignment3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_open);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new fun_fact_fragment()).commit();
            navigationView.setCheckedItem(R.id.nav_fun_facts);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_fun_facts:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new fun_fact_fragment()).commit();
                break;
            case R.id.nav_investment_port:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new invest_fragment()).commit();
                break;
            case R.id.nav_resume:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new resume_fragment()).commit();
                break;
            case R.id.nav_weather:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new weather_fragment()).commit();
                break;
            case R.id.nav_share:
                Toast.makeText(this, "share", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                String body = "Share this app";
                intent.putExtra(Intent.EXTRA_TEXT, body);
                startActivity(Intent.createChooser(intent, "share using"));
                break;
        }

        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    @Override
    public void onBackPressed(){
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public void open(View view){
        Intent openNewBrowser = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/ccb1139/ccb1139.github.io/blob/main/resume.pdf"));
        startActivity(openNewBrowser);
    }

    public void cur_weather(View view){
        Intent openNewBrowser = new Intent(Intent.ACTION_VIEW, Uri.parse("https://weather.com/weather/today"));
        startActivity(openNewBrowser);
    }

    public void from_weather(View view){
        Intent openNewBrowser = new Intent(Intent.ACTION_VIEW, Uri.parse("https://weather.com/weather/tenday/l/Lowell+MA?canonicalCityId=b62d0c6494d493bb32073a1c73b226160eea800198016adba7b57ad821ce2ab9"));
        startActivity(openNewBrowser);
    }

    public void want_weather(View view){
        Intent openNewBrowser = new Intent(Intent.ACTION_VIEW, Uri.parse("https://weather.com/weather/tenday/l/5f96b3ddf1d8af66ec451ce8c40623fb48d7d24bcea4736be85ef9205f3b8a00"));
        startActivity(openNewBrowser);
    }

    public void fam_weather(View view){
        Intent openNewBrowser = new Intent(Intent.ACTION_VIEW, Uri.parse("https://weather.com/weather/tenday/l/bf217d537cc1c8074ec195ce07fb74de3c1593caa6033b7c3be4645ccc5b01de"));
        startActivity(openNewBrowser);
    }
}