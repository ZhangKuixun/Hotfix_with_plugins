package com.kevin.classLoader.plugin;

import android.content.res.AssetManager;
import android.content.res.Resources;

import dalvik.system.DexClassLoader;

/**
 * 插件实体类。这个类代表我们要获取的插件的信息，这个类中的这些信息我们要关心。
 */
//第一步
public class PluginInfo {

    public DexClassLoader mClassLoader;//插件中的类需要这个classLoader去加载

    public AssetManager mAssetManager;//创建资源相关的类

    public Resources mResources;

}
