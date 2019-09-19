package com.kevin.tinker.tinker;

import com.tencent.tinker.lib.service.DefaultTinkerResultService;
import com.tencent.tinker.lib.service.PatchResult;
import com.tencent.tinker.lib.util.TinkerLog;
import com.tencent.tinker.lib.util.TinkerServiceInternals;

import java.io.File;

/**
 * 作用：
 * 决定在patch安装完以后的后续操作，默认实现是杀进程。不管默认安装成功还是失败。
 */
public class CustomResultService extends DefaultTinkerResultService {
    private static final String TAG = "kevin";

    //返回patch文件的最终安装结果。
    @Override
    public void onPatchResult(PatchResult result) {
        //复制 DefaultTinkerResultService#onPatchResult 的代码，删除杀进程的代码。

        if (result == null) {
            TinkerLog.e(TAG, "DefaultTinkerResultService received null result!!!!");
            return;
        }
        TinkerLog.i(TAG, "DefaultTinkerResultService received a result:%s ", result.toString());

        //first, we want to kill the recover process
        TinkerServiceInternals.killTinkerPatchServiceProcess(getApplicationContext());

        // if success and newPatch, it is nice to delete the raw file, and restart at once
        // only main process can load an upgrade patch!
        if (result.isSuccess) {
            deleteRawPatchFile(new File(result.rawPatchFilePath));
        }
    }
}
