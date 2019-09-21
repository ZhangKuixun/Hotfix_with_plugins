package com.kevin.small;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import net.wequick.small.Small;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Small.setUp(this, new Small.OnCompleteListener() {//加载插件是一个耗时操作
            @Override
            public void onComplete() {
                //参数1 要启动的插件。参数2 当前上下文
                Small.openUri("main", MainActivity.this);
            }
        });

    }
}
