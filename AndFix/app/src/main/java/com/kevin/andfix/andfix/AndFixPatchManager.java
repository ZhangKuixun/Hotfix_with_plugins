package com.kevin.andfix.andfix;

import android.content.Context;

import com.alipay.euler.andfix.patch.PatchManager;

import java.io.IOException;

/**
 * @function 管理AndFix所有的api
 */
public class AndFixPatchManager {

    private static AndFixPatchManager mInstance = null;

    private static PatchManager mPatchManager = null;

    /**
     * 单例模式的双检查机制
     */
    public static AndFixPatchManager getInstance() {
        if (mInstance == null) {
            synchronized (AndFixPatchManager.class) {
                if (mInstance == null) {
                    mInstance = new AndFixPatchManager();
                }
            }
        }
        return mInstance;
    }

    //初始化AddFix方法
    public void initPatch(Context context) {
        mPatchManager = new PatchManager(context);
        mPatchManager.init(Utils.getVersionName(context));
        mPatchManager.loadPatch();
    }

    //加载patch文件
    public void addPatch(String path) {
        if (mPatchManager != null) {
            try {
                mPatchManager.addPatch(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
