package com.luckystar.health.common;

import com.luckystar.health.base.BaseApplication;

/**
 * Created by Administrator on 2017/9/11.
 */

public class AppConfigs {

    /**
     * 服务器地址
     */
    public static final String BASE_URL = "http://your_server_address";


    /**
     * 网页URL
     */
    public static final String WEB_PAGE_URL = "http://your_server_address";

    /**
     * 文件服务器地址
     */
    public static final String FILE_URL = "http://your_server_address";

    /**
     * SharedPreferences键
     */
    public static final String SHARED_PREFS_KEY = "health";

    /**
     * DES加密密钥
     */
    public static final String DES_KEY = "!@#$%^&*";

    /**
     * 每页数据：5条
     */
    public static final String PAGE_SIZE_5 = "5";

    /**
     * 每页数据：10条
     */
    public static final String PAGE_SIZE_10 = "10";

    /**
     * 每页数据：10条
     */
    public static final String PAGE_SIZE_20 = "20";

    public static final String NET_CACHE = BaseApplication.getAppCacheDir();
}
