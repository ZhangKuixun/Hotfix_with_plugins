package com.kevin.tinker.tinker;

import android.content.Context;
import android.util.Log;

import com.kevin.tinker.CustomPatchListener;
import com.tencent.tinker.entry.ApplicationLike;
import com.tencent.tinker.lib.patch.UpgradePatch;
import com.tencent.tinker.lib.reporter.DefaultLoadReporter;
import com.tencent.tinker.lib.reporter.DefaultPatchReporter;
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

    private static CustomPatchListener mPatchListener;

    /**
     * 完成Tinker的初始化
     */
    public static void installTinker(ApplicationLike applicationLike) {
        mAppLike = applicationLike;
        if (isInstalled) {
            return;
        }

        mPatchListener = new CustomPatchListener(getApplicationContext());//初始化CustomPatchListener

        DefaultLoadReporter loadReporter = new DefaultLoadReporter(getApplicationContext());//是在加载过程中异常监听回调
        DefaultPatchReporter patchReporter = new DefaultPatchReporter(getApplicationContext());//过了加载阶段，在patch文件合成阶段发生异常监听回调

        UpgradePatch upgradePatchProcessor = new UpgradePatch();//决定patch文件的安装策略，这个在实际开发中基本不会去自定义

        TinkerInstaller.install(applicationLike,
                                loadReporter,
                                patchReporter,
                                mPatchListener,
                                CustomResultService.class,
                                upgradePatchProcessor);

        isInstalled = true;
    }


    //完成patch文件的加载
    public static void loadPatch(String path,String md5Value) {
        Log.e("kevin", "--loadPatch " + path);
        if (Tinker.isTinkerInstalled()) {
            mPatchListener.setCurrentMD5(md5Value);

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
