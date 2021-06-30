package com.duskdevotee.aidda;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    //declaring member variable for drawer layout
    private DrawerLayout drawer;
    //private int CAMERA_PERMISSION_CODE=10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CAMERA}, CAMERA_PERMISSION_CODE);

        //As we removed default toolbar so we are setting our custom one...
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //initializing the member variable for our big drawer layout
        drawer = findViewById(R.id.drawer_layout);
        //setting nav_view access variable and listener on navigation_bar
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        //Drawer continuously listens for toggle button pressed or not?
        drawer.addDrawerListener(toggle);
        toggle.syncState(); //Hamburger icon rotation animated effect

        //to avoid crashes caused by rotating devices if device is rotated the condition below will be false
        if(savedInstanceState==null) {
            //default fragment to open
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new MonitorFragment()).commit();
            //default fragment to be selected
            navigationView.setCheckedItem(R.id.nav_monitor);
        }


    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.nav_monitor:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new MonitorFragment()).commit();
                break;
            case R.id.nav_help:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new HelpFragment()).commit();
                break;
            case R.id.nav_contact:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new ContactFragment()).commit();
                break;
            case R.id.nav_share:
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT,"Hey, Do you feel fatigue or drowsiness while driving? I have found a solution for you. Try AIDDA App now, It uses latest AI techniques to alarm the user when he/she is feeling sleepliness. Checkout at www.example.com or email at muhammad.sadaqat.janjua@gmail.com for more info.");
                startActivity(intent.createChooser(intent,"Share via"));
                break;
        }
        //closing drawer after click on item inside navigation
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}