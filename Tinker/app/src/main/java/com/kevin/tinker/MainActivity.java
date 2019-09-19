package com.kevin.tinker;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.kevin.tinker.data.BasePatchInfo;
import com.kevin.tinker.tinker.TinkerManager;
import com.umeng.analytics.MobclickAgent;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private static final String FILE_END = ".apk";
    private String mPatchDir;

    private BasePatchInfo mBasePatchInfo;

    @RequiresApi(api = Build.VERSION_CODES.FROYO)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPatchDir = getExternalCacheDir().getAbsoluteFile() + "/tpatch/";
        //为了创建我们的文件夹
        File file = new File(mPatchDir);
        if (file == null || file.exists()) {
            file.mkdir();
        }

    }

    public void loadPatch(View view) {
        TinkerManager.loadPatch(getPatchName(), mBasePatchInfo.data.md5);
    }


    //构造patch 文件名
    private String getPatchName() {
        return mPatchDir.concat("kevin").concat(FILE_END);
    }


    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
}
