package com.namviet.detect;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import namviet.ultils.detect.MobileUtils;
import namviet.ultils.detect.listener.DetectListener;

public class MainActivity extends AppCompatActivity implements DetectListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MobileUtils mobileUtils = new MobileUtils(this,this);
        mobileUtils.detectMobile();
    }

    @Override
    public void onDetectSuccess(String mobile) {

    }
}
