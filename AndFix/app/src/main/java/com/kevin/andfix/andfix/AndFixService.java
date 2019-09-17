package com.kevin.andfix.andfix;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;

import java.io.File;

/**
 * 1.检查是否有新的patch文件
 * 2.下载patch文件
 * 3.加载patch文件
 */
public class AndFixService extends Service {
    private static final String TAG = "kevin";
    private static final String FILE_END = ".apatch";
    private static final int UPDATE_PATCH = 0x02;
    private static final int DOWNLOAD_PATCH = 0x01;

    private String mPatchFileDir;
    private String mPatchFile;
    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case UPDATE_PATCH:
                    checkPatchUpdate();
                    break;
                case DOWNLOAD_PATCH:
                    downloadPatch();
                    break;
            }

        }
    };

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    //完成文件目录的构造
    private void init() {
        //文件的初始化
        mPatchFileDir = getExternalCacheDir().getAbsolutePath() + "/apatch/";
        File patchDir = new File(mPatchFileDir);
        try {
            if (patchDir != null || !patchDir.exists()) {
                patchDir.mkdir();
            }
        } catch (Exception e) {
            // 有的手机会创建文件失败。
            e.printStackTrace();
            stopSelf();
        }
    }

    //检查服务器是否有patch文件
    private void checkPatchUpdate() {
        //请求服务器成功

        //////检查返回实体是否有下载文件的url，如果有，下载patch文件
        //////////mHandler.sendEmptyMessage(DOWNLOAD_PATCH);
        //////如果没有，stopSelf();

        //请求服务器失败
        //////stopSelf();
    }

    //完成patch文件的下载
    private void downloadPatch() {
        //初始化patch文件下载路径
        mPatchFile = mPatchFileDir.concat(String.valueOf(System.currentTimeMillis())).concat(FILE_END);

        //请求服务器，下载文件。

        //////下载成功，将我们下载好的patch文件添加到我们的andfix中
        ////////////AndFixPatchManager.getInstance().addPatch(mPatchFile);

        //////下载失败，stopSelf();
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mHandler.sendEmptyMessage(UPDATE_PATCH);
        return START_NOT_STICKY;//这个返回值，如果service被系统回收以后，不会自动重启。
    }
}
