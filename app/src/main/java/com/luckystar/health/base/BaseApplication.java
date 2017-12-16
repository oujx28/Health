package com.luckystar.health.base;

import android.app.Application;
import android.content.Context;

import com.luckystar.health.component.PLog;
import io.reactivex.plugins.RxJavaPlugins;

/**
 * Created by Administrator on 2017/9/11.
 */

public class BaseApplication extends Application {
    private static String sCacheDir;
    private static Context sAppContext;

    @Override
    public void onCreate() {
        super.onCreate();

        sAppContext = getApplicationContext();

        RxJavaPlugins.setErrorHandler(throwable -> {
            if (throwable != null) {
                PLog.e(throwable.toString());
                throwable.printStackTrace();
            } else {
                PLog.e("call onError but exception is null");
            }
        });
        /**
         * 如果存在SD卡则将缓存写入SD卡,否则写入手机内存
         */
        if (getApplicationContext().getExternalCacheDir() != null && ExistSDCard()) {
            sCacheDir = getApplicationContext().getExternalCacheDir().toString();
        } else {
            sCacheDir = getApplicationContext().getCacheDir().toString();
        }
    }
    private boolean ExistSDCard() {
        return android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
    }

    public static Context getAppContext() {
        return sAppContext;
    }

    public static String getAppCacheDir() {
        return sCacheDir;
    }
}
