package com.imooc;

import android.app.Application;

import net.wequick.small.Small;

/**
 * Created by renzhiqiang on 17/5/21.
 */

public class ImoocApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Small.preSetUp(this); //small框架的初始化
    }
}
