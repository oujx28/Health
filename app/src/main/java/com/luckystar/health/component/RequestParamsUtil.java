package com.luckystar.health.component;

import android.content.Context;


import com.luckystar.health.common.utils.DeviceUtils;
import com.luckystar.health.common.utils.PBECoder;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 请求参数工具类
 * Created by TongHuiZe on 2016/2/20.
 */
public class RequestParamsUtil {
    private static final String TAG = RequestParamsUtil.class.getSimpleName();

    /**
     * 创建参数链字符串
     *
     * @param params 请求参数
     * @return 参数链字符串
     */
    public static String createLinkedString(Map<String, String> params) {
        List<String> keys = new ArrayList<>(params.keySet());
        Collections.sort(keys);

        String linkedStr = "";
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = params.get(key);
            if (null != value) {
                if (i == keys.size() - 1) {// 拼接时，不包括最后一个&字符
                    linkedStr = linkedStr + key + "=" + value;
                } else {
                    linkedStr = linkedStr + key + "=" + value + "&";
                }
            }
        }

        return linkedStr;
    }


    /**
     * 获取请求参数
     *
     * @param context 上下文对象
     * @return 默认请求参数
     */
    public static Map<String, String> getRequestParams(Context context) {
        Map<String, String> params = new HashMap<>();
        params.put("v", DeviceUtils.getVersionCode(context) + "");
        params.put("st", String.valueOf(System.currentTimeMillis()));

        String linkedStr = createLinkedString(params);
        params.put("data", encrypt(linkedStr));
        return params;
    }

    /**
     * 获取请求参数
     *
     * @param params 参数
     * @return 请求参数
     */
    public static Map<String, String> getRequestParams(Context context, Map<String, String> params) {
        if (params != null) {
            // 判断参数是否存在，若存在先移除
            if (params.containsKey("v")) {
                params.remove("v");
            } else if (params.containsKey("st")) {
                params.remove("st");
            } else if (params.containsKey("data")) {
                params.remove("data");
            }

            params.put("v", String.valueOf(DeviceUtils.getVersionCode(context)));
            params.put("st", String.valueOf(System.currentTimeMillis()));

            String linkedStr = createLinkedString(params);
            //LogUtils.e(TAG, "data=" + encrypt(linkedStr));
            params.put("data", encrypt(linkedStr));
        }

        return params;
    }

    /**
     * 加密
     *
     * @param str 参数链字符串
     * @return 密文
     */
    public static String encrypt(String str) {
        String result = null;
        PBECoder pbecoder = new PBECoder();
        try {
            //LogUtils.i(TAG, "原文：" + str);
            String inputStr = URLEncoder.encode(str, "UTF-8");
            //LogUtils.i(TAG, "URLEncoder.encode()后：" + inputStr);
            byte[] input = inputStr.getBytes();
            //LogUtils.e(TAG, "getBytes***");
            String pwd = pbecoder.defaultPassword;
            byte[] salt = pbecoder.getDefaultSalt().getBytes();
            //LogUtils.e(TAG, "salt getBytes***");
            byte[] data = pbecoder.encrypt(input, pwd, salt);
            //LogUtils.e(TAG, "pbecoder.encrypt***");
            result = PBECoder.byte2hex(data);
            //LogUtils.i(TAG, "密文：" + result);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}
