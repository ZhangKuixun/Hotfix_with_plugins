package com.kevin.andfix.application;

import android.app.Application;

import com.kevin.andfix.andfix.AndFixPatchManager;

public class IApplication extends Application {
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
