package com.luckystar.health.common.utils;

import android.content.Context;
import android.util.Log;

/**
 * Log日志工具类
 * Created by TongHuiZe on 2016/3/30.
 */
public class LogUtils {
    /**
     * 是否
     */
    public static boolean isDebuggable = true;

    /** #########===== verbose =====######### */
    public static void v(String tag, String msg) {
        if (isDebuggable) {
            Log.v(tag, "--------->>>>>" + msg);
        }
    }

    public static void v(Context context, String msg) {
        if (context != null) {
            v(context.getClass().getSimpleName(), msg);
        }
    }

    public static void v(Class<?> cls, String msg) {
        v(cls.getSimpleName(), msg);
    }

    /** #########===== debug =====######### */
    public static void d(String tag, String msg) {
        if (isDebuggable) {
            Log.d(tag, "--------->>>>>" + msg);
        }
    }

    public static void d(Context context, String msg) {
        if (context != null) {
            d(context.getClass().getSimpleName(), msg);
        }
    }

    public static void d(Class<?> cls, String msg) {
        d(cls.getSimpleName(), msg);
    }

    /** #########===== info =====######### */
    public static void i(String tag, String msg) {
        if (isDebuggable) {
            Log.i(tag, "--------->>>>>" + msg);
        }
    }

    public static void i(Context context, String msg) {
        if (context != null) {
            i(context.getClass().getSimpleName(), msg);
        }
    }

    public static void i(Class<?> cls, String msg) {
        i(cls.getSimpleName(), msg);
    }

    /** #########===== warn =====######### */
    public static void w(String tag, String msg) {
        if (isDebuggable) {
            Log.w(tag, "--------->>>>>" + msg);
        }
    }

    public static void w(Context context, String msg) {
        if (context != null) {
            w(context.getClass().getSimpleName(), msg);
        }
    }

    public static void w(Class<?> cls, String msg) {
        w(cls.getSimpleName(), msg);
    }

    /** #########===== error =====######### */
    public static void e(String tag, String msg) {
        if (isDebuggable) {
             Log.e(tag, "--------->>>>>" + msg);
        }
    }

    public static void e(Context context, String msg) {
        if (context != null) {
            e(context.getClass().getSimpleName(), msg);
        }
    }

    public static void e(Class<?> cls, String msg) {
        e(cls.getSimpleName(), msg);
    }
}
