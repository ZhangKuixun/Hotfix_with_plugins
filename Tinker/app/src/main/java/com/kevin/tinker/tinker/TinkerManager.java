package com.kevin.tinker.tinker;

import android.content.Context;
import android.util.Log;

import com.tencent.tinker.entry.ApplicationLike;
import com.tencent.tinker.lib.tinker.Tinker;
import com.tencent.tinker.lib.tinker.TinkerInstaller;

/**
 * 对Tinker的所有api做一层封装
 */
public class TinkerManager {
    //是否初始化过Tinker或者说是否已经安装Tinker
    private static boolean isInstalled = false;
    //Tinker委托类
    private static ApplicationLike mAppLike;

    /**
     * 完成Tinker的初始化
     *
     * @param applicationLike
     */
    public static void installTinker(ApplicationLike applicationLike) {
        mAppLike = applicationLike;
        if (isInstalled) {
            return;
        }

        TinkerInstaller.install(mAppLike);//完成Tinker初始化
        isInstalled = true;
    }


    //完成patch文件的加载
    public static void loadPatch(String path) {
        Log.e("kevin", "--loadPatch");
        if (Tinker.isTinkerInstalled()) {

            Log.e("kevin", "TinkerInstaller.onReceiveUpgradePatch");
            TinkerInstaller.onReceiveUpgradePatch(getApplicationContext(), path);
            Log.e("kevin", "TinkerInstaller.onReceiveUpgradePatch--");
        }
    }

    //通过ApplicationLike获取Context
    private static Context getApplicationContext() {
        if (mAppLike != null)
            return mAppLike.getApplication().getApplicationContext();
        return null;
    }
}
