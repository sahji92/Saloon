package com.dpk.saloon;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.dpk.saloon.ui.main.MainFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
    }
}