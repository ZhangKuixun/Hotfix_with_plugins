package com.imooc;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.io.File;
import java.lang.reflect.Method;

import dalvik.system.DexClassLoader;

///storage/emulated/0/Android/data/com.imooc/cache
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
        //初始化classLoader
        DexClassLoader classLoader = new DexClassLoader(apkPath,
                optDir.getAbsolutePath(), null, this.getClassLoader());

        try {
            Class cls = classLoader.loadClass("com.imooc.bundle.BundleUtil");
            if (cls != null) {

                Object instacne = cls.newInstance();
                Method method = cls.getMethod("printLog");
                method.invoke(instacne);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
