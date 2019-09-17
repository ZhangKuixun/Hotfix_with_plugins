package com.kevin.andfix;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.kevin.andfix.andfix.AndFixPatchManager;
import com.kevin.andfix.tools.Utils;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private static final String FILE_END = ".apatch";
    private String mPatchDir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPatchDir = getExternalCacheDir().getAbsoluteFile() + "/apatch/";
        //为了创建我们的文件夹
        File file = new File(mPatchDir);
        if (file == null || file.exists()) {
            file.mkdir();
        }
    }

    //创建bug
    public void createBug(View view) {
        Utils.printLog();
    }

    //修复bug
    public void fixBug(View view) {
        AndFixPatchManager.getInstance().addPatch(getPatchName());
    }

    //构造patch 文件名
    private String getPatchName() {
        return mPatchDir.concat("kevin").concat(FILE_END);
    }
}
