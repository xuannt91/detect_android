package com.namviet.detect;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import namviet.ultils.detect.DetectUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new DetectUtils(this).detectMobile();
    }


}
