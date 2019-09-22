package com.kevin.classLoader.plugin;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;

import java.io.File;
import java.lang.reflect.Method;
import java.util.HashMap;

import dalvik.system.DexClassLoader;

/**
 * 管理所有的插件
 *
 * 这里只限于模拟
 */
//second
public class PluginManager { 
    public static PluginManager mInstance;

    private static Context mContext;

    private static File mOptFile;

    private static HashMap<String, PluginInfo> mPluginMap;//third 将已经加载的插件保存起来

    private PluginManager(Context context) {
        mContext = context;
        mOptFile = mContext.getDir("opt", mContext.MODE_PRIVATE);
        mPluginMap = new HashMap<>();
    }

    //获取单例对象
    public static PluginManager getInstance(Context context) {
        if (mInstance == null) {
            synchronized (PluginManager.class) {
                if (mInstance == null) {
                    mInstance = new PluginManager(context);
                }
            }
        }
        return mInstance;
    }

    /**
     * second. 为外界提供加载插件的方法
     */

    //初始化PluginInfo。。一般插件框架中loadApk是初始化插件方法
    public static PluginInfo loadApk(String apkPath) {

        if (mPluginMap.get(apkPath) != null) {
            return mPluginMap.get(apkPath);
        }

        PluginInfo pluginInfo = new PluginInfo();
        pluginInfo.mClassLoader = createPluginClassLoader(apkPath);
        pluginInfo.mAssetManager = createAssetManager(apkPath);
        pluginInfo.mResources = createPluginResources(apkPath);
        
        // third 每次加载插件都要创建PluginInfo会很慢。解决：将插件中的信息保存起来。

        mPluginMap.put(apkPath, pluginInfo);
        return pluginInfo;
    }


    /**
     * first. 下面为PluginInfo中的三个成员变量创建初始化方法
     *
     * @see PluginInfo
     */

    //为插件Apk创建对应的ClassLoader，完成加载插件apk的类
    private static DexClassLoader createPluginClassLoader(String apkPath) {

        DexClassLoader classLoader = new DexClassLoader(
                apkPath,
                mOptFile.getAbsolutePath(),//解压到哪个内部路径下
                null, //搜索路径
                null);//父classLoader
        return classLoader;
    }

    //为对应插件创建AssetManager，完成加载插件apk的资源
    private static AssetManager createAssetManager(String apkPath) {
        try {
            AssetManager assetManager = AssetManager.class.newInstance();//获取一个实例
            Method addAssetPath = assetManager.getClass().getMethod(
                    "addAssetPath", String.class);//参数1-调用的方法.参数2-传递的参数类型

            //拿到方法后，就可以调用addAssetPath方法，将apkPath传入addAssetPath。
            addAssetPath.invoke(assetManager, apkPath);
            return assetManager;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //为对应插件创建Resource类
    private static Resources createPluginResources(String apkPath) {

        //Resources的创建依要赖于AssetManager
        AssetManager assetManager = createAssetManager(apkPath);

        //还有依赖于宿主Resource

        Resources superResources = mContext.getResources();//先创建宿主的Resources

        //先创建插件应用的Resources
        Resources pluginResource = new Resources(assetManager,
                                                 superResources.getDisplayMetrics(),
                                                 superResources.getConfiguration());
        return pluginResource;
    }
}
