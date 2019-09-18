package com.kevin.tinker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.kevin.tinker.tinker.TinkerManager;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private static final String FILE_END = ".apk";
    private String mPatchDir;

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
        TinkerManager.loadPatch(getPatchName());
    }


    //构造patch 文件名
    private String getPatchName() {
        return mPatchDir.concat("kevin").concat(FILE_END);
    }


}
