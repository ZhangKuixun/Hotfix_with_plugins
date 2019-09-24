package com.kevin.atlas;

import android.content.Context;
import android.taobao.atlas.runtime.AtlasPreLauncher;
import android.util.Log;

public class DemoPreLaunch implements AtlasPreLauncher {
    @Override
    public void initBeforeAtlas(Context context) {
        Log.d("prelaunch", "prelaunch invokded");
    }
}
