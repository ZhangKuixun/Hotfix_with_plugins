package com.kevin.atlas;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.b_remote).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClassName(getBaseContext(), "com.kevin.remotebundle.RemoteActivity");
                startActivity(intent);
            }
        });
        findViewById(R.id.b_first).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClassName(getBaseContext(), "com.kevin.firstbundle.FirstActivity");
                startActivity(intent);
            }
        });
    }
}
