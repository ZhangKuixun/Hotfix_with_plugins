package com.kevin.atlas;

import android.app.Application;

public class DemoApplication extends Application {

    @Override
    public void onCreate() {

        super.onCreate();
//        Atlas.getInstance().setClassNotFoundInterceptorCallback(intent -> {
//            final String className = intent.getComponent().getClassName();
//            final String bundleName = AtlasBundleInfoManager.instance().getBundleForComponet(className);
//
//            if (!TextUtils.isEmpty(bundleName) && !AtlasBundleInfoManager.instance().isInternalBundle(bundleName)) {
//
//                //远程bundle
//                Activity activity = ActivityTaskMgr.getInstance().peekTopActivity();
//                File remoteBundleFile = new File(activity.getExternalCacheDir(),"lib" + bundleName.replace(".","_") + ".so");
//
//                String path = "";
//                if (remoteBundleFile.exists()){
//                    path = remoteBundleFile.getAbsolutePath();
//                }else {
//                    Toast.makeText(activity, " 远程bundle不存在，请确定 : " + remoteBundleFile.getAbsolutePath() , Toast.LENGTH_LONG).show();
//                    return intent;
//                }
//
//
//                PackageInfo info = activity.getPackageManager().getPackageArchiveInfo(path, 0);
//                try {
//                    Atlas.getInstance().installBundle(info.packageName, new File(path));
//                } catch (BundleException e) {
//                    Toast.makeText(activity, " 远程bundle 安装失败，" + e.getMessage() , Toast.LENGTH_LONG).show();
//
//                    e.printStackTrace();
//                }
//
//                activity.startActivities(new Intent[]{intent});
//
//            }
//
//            return intent;
//        });

    }
}
