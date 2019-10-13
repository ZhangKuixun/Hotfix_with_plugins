package com.imooc.plugin;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;

import java.io.File;
import java.lang.reflect.Method;
import java.util.HashMap;

import dalvik.system.DexClassLoader;

/**
 * Created by renzhiqiang on 17/5/19.
 */

public class PluginManager {

    private static PluginManager mInstacne;

    private static Context mContext;
    private static File mOptFile;
    private static HashMap<String, PluginInfo> mPluginMap;

    private PluginManager(Context context) {
        mContext = context;
        mOptFile = mContext.getDir("opt", mContext.MODE_PRIVATE);
        mPluginMap = new HashMap<>();
    }

    //获取单例对象方法
    public static PluginManager getInstance(Context context) {

        if (mInstacne == null) {
            synchronized (PluginManager.class) {
                if (mInstacne == null) {

                    mInstacne = new PluginManager(context);
                }
            }
        }

        return mInstacne;
    }


    public static PluginInfo loadApk(String apkPath) {

        if (mPluginMap.get(apkPath) != null) {
             return mPluginMap.get(apkPath);
        }

        PluginInfo pluginInfo = new PluginInfo();
        pluginInfo.mClassLoader = createPluginDexClassLoader(apkPath);
        pluginInfo.mAssetManager = createPluginAssetManager(apkPath);
        pluginInfo.mResouces = createPluginResources(apkPath);

        mPluginMap.put(apkPath, pluginInfo);

        return pluginInfo;
    }

    //为插件apk创建对应的classLoader
    private static DexClassLoader createPluginDexClassLoader(String apkPath) {

        DexClassLoader classLoader = new DexClassLoader(apkPath,
                mOptFile.getAbsolutePath(), null, null);
        return classLoader;
    }

    //为对应的插件创建AssetManager
    private static AssetManager createPluginAssetManager(String apkPath) {

        try {
            AssetManager assetManager = AssetManager.class.newInstance();
            Method addAssetPath = assetManager.getClass().getMethod("addAssetPath",
                    String.class);

            addAssetPath.invoke(assetManager, apkPath);
            return assetManager;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    //为对应的插件创建resoures
    private static Resources createPluginResources(String apkPath) {

        AssetManager assetManager = createPluginAssetManager(apkPath);

        Resources superResources = mContext.getResources();

        Resources pluginResources = new Resources(assetManager,
                superResources.getDisplayMetrics(), superResources.getConfiguration());

        return pluginResources;
    }
}
