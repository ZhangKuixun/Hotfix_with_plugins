package com.kevin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.kevin.classLoader.R;

import java.io.File;
import java.lang.reflect.Method;

import dalvik.system.DexClassLoader;

/**
 * 简单实现，加载插件apk文件的类
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String apkPath = getExternalCacheDir().getAbsolutePath() + "/bundle.apk";
        loadApk(apkPath);
    }

    private void loadApk(String apkPath) {
        File optDir = getDir("opt", MODE_PRIVATE);
        //first 初始化classLoader
        DexClassLoader classLoader = new DexClassLoader(
                apkPath,
                optDir.getAbsolutePath(),//将插件apk解压到哪个目录中，必须是data/data/
                null,//搜索路径
                this.getClassLoader());//当前classLoader的父classLoader

        try {
            //second
            Class cls = classLoader.loadClass("com.kevin.bundle.BundleUtil");
            if (cls != null) {

                //third 反射获取要调用的方法,然后invoke
                Object instance = cls.newInstance();//获取一个实例
                Method method = cls.getMethod("printLog");//调用的方法
                method.invoke(instance);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
