package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.os.Bundle;
import android.view.Menu;

public class VideosActivity extends AppCompatActivity {
    private Toolbar toolbar;
    @Nullable
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videos);
        setTheme(R.style.Theme_Videos);
        toolbar = (Toolbar) findViewById(R.id.footer);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(null);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.footer_menu, menu);
        //return super.onCreateOptionsMenu(menu);
        return true;
    }
}