package com.kevin.tinker;

import android.content.Context;

import com.kevin.tinker.tinker.TinkerService;
import com.kevin.tinker.utils.MD5Util;
import com.tencent.tinker.lib.listener.DefaultPatchListener;
import com.tencent.tinker.lib.tinker.Tinker;
import com.tencent.tinker.loader.shareutil.ShareConstants;

import java.io.File;

/**
 * 作用：
 * 1.校验Patch文件是否合法。比如MD5。
 * 2.如果合法，启动service去安装patch文件。这个步骤我们不需要扩展
 */
public class CustomPatchListener extends DefaultPatchListener {

    private String currentMD5;

    public void setCurrentMD5(String currentMD5) {
        this.currentMD5 = currentMD5;
    }

    public CustomPatchListener(Context context) {
        super(context);
    }

    //
    @Override
    public int onPatchReceived(String path) {
        int returnCode = patchCheck(path, MD5Util.getMD5String(path));
        if (returnCode == ShareConstants.ERROR_PATCH_OK) {
            //校验通过，可以加载patch文件
            TinkerService.runPatchService(context, path);
        } else {
            Tinker.with(context).getLoadReporter().onLoadPatchListenerReceiveFail(new File(path), returnCode);
        }
        return returnCode;
    }

    //扩展校验方法
    @Override
    protected int patchCheck(String path, String patchMd5) {
        //patch文件md5校验
        if (!MD5Util.check(path, patchMd5)) {
            //如果加载的patch不相等，说明从服务器上下载的文件被篡改过、或者说下载的文件有误。
            return ShareConstants.ERROR_PATCH_DISABLE;
        }

        //还有教校验规则可以继续在这里添加

        return super.patchCheck(path, patchMd5);
    }

}
