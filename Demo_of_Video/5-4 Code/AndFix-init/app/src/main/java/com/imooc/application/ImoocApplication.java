package com.imooc.application;

import android.app.Application;

import com.imooc.andfix.AndFixPatchManager;

/**
 * Created by renzhiqiang on 17/4/22.
 */

public class ImoocApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //完成andFix模块的初始化
        initAndFix();
    }

    private void initAndFix() {
        AndFixPatchManager.getInstance().initPatch(this);
    }
}
