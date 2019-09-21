package com.kevin.small.application;

import android.app.Application;

import net.wequick.small.Small;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Small.preSetUp(this); //Small框架的初始化
    }

}
