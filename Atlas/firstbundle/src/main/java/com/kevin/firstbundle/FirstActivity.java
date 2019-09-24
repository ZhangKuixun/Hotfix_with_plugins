package com.kevin.firstbundle;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class FirstActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e("kevin", "FirstActivity");
    }
}
