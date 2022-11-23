package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class VideosActivity extends AppCompatActivity implements NavigationBarView.OnItemSelectedListener, CallbackFragment {
    BottomNavigationView bottomNavigationView;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videos);
        setTheme(R.style.Theme_Videos);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.footer_home);
    }

    FragmentHome fragmentHome = new FragmentHome();
    FragmentUpload fragmentUpload = new FragmentUpload();

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.footer_home:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, fragmentHome).commit();
                return true;

            case R.id.footer_upload:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, fragmentUpload).commit();
                return true;

            case R.id.footer_user:
                //getSupportFragmentManager().beginTransaction().replace(R.id.container, thirdFragment).commit();
                return true;
        }
        return false;
    }

    public void replaceFragmentMovie(){
        FragmentMovie fragment = new FragmentMovie();
        fragment.setCallbackFragment(this);
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.replace(R.id.flFragment, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void changefragmentRegister() {

    }

    @Override
    public void changefragmentLogin() {

    }

    @Override
    public void changefragmentMovie() {
        replaceFragmentMovie();
    }
}