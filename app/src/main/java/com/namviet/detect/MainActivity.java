package com.namviet.detect;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import namviet.ultils.detect.DetectUtils;
import namviet.ultils.detect.listener.DetectListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DetectUtils detectUtils = new DetectUtils(this, new DetectListener() {
            @Override
            public void onDetectSuccess(String s) {
                Toast.makeText(MainActivity.this, "" + s, Toast.LENGTH_LONG).show();
            }
        });
        detectUtils.detectMobile();
    }


}
