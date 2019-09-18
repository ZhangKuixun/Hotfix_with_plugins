package com.kevin.tinker.tinker;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.support.multidex.MultiDex;

import com.tencent.tinker.anno.DefaultLifeCycle;
import com.tencent.tinker.loader.app.ApplicationLike;
import com.tencent.tinker.loader.shareutil.ShareConstants;

/**
 * 初始化Tinker。
 * <p>
 * 为什么在这里初始化，而不是直接在Application中初始化？因为 Tinker 需要监听我们的 Application 的生命
 * 周期，所以通过 ApplicationLike 对象进行委托，通过委托，也可以在 ApplicationLike 中完成对Tinker
 * 生命周期的监听，然后在不同的生命周期阶段去做不同的初始化工作。
 * <p>
 * 如果Tinker没有使用委托的模式整个Tinker的初始化会非常复杂,通过代理的方式会把所有的工作都封装在
 * ApplicationLike对象中。
 */
//通过 ApplicationLike 对象，帮助我们生成 Application 对象
@DefaultLifeCycle(application = ".MyTinkerApplication",//应用要生成的Application名称
        flags = ShareConstants.TINKER_ENABLE_ALL,//启用tinker
        loadVerifyFlag = false)
public class CustomTinkerLike extends ApplicationLike {
    public CustomTinkerLike(Application application,
                            int tinkerFlags,
                            boolean tinkerLoadVerifyFlag,
                            long applicationStartElapsedTime,
                            long applicationStartMillisTime,
                            Intent tinkerResultIntent) {
        super(application, tinkerFlags,
              tinkerLoadVerifyFlag,
              applicationStartElapsedTime,
              applicationStartMillisTime,
              tinkerResultIntent);
    }

    @Override
    public void onBaseContextAttached(Context base) {
        super.onBaseContextAttached(base);

        //使应用支持分包
        MultiDex.install(base);

        TinkerManager.installTinker(this);

    }
}
