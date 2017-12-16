package com.luckystar.health.component;


import android.content.Context;

import com.luckystar.health.base.BaseApplication;
import com.luckystar.health.common.AppConfigs;
import com.luckystar.health.common.utils.DeviceUtils;
import com.luckystar.health.common.utils.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import okhttp3.HttpUrl;
import okhttp3.Request;

/**
 * 网络请求工具类
 * Created by TongHuiZe on 2016/2/19.
 */
public class RequestUtil {
    /**
     * 请求成功
     */
    public static final String REQUEST_SUCCESS = "1";

    /**
     * 请求失败
     */
    public static final String REQUEST_FAILURE = "0";

    /**
     * 获取URL绝对路径
     *
     * @param relativeUrl 接口名
     * @return 接口URL绝对路径
     */
    public static String getAbsoluteUrl(String relativeUrl) {
        return AppConfigs.BASE_URL + relativeUrl;
    }

    /**
     * 获取网页URL
     *
     * @param relativeUrl 接口名
     * @return 网页URL
     */
    public static String getWebPageUrl(String relativeUrl) {
        return AppConfigs.WEB_PAGE_URL + relativeUrl;
    }

    /**
     * 初始化文件服务器
     */
    public static final String INIT_SERVER = "initial";

    /**
     * 上传文件
     */
    public static final String UPLOAD_FILE = "upload";

    /**
     * 家庭成员
     */
    public static final String LOAD_FAMILY_MEMBERS = "duserlist";
    /**
     * 家庭成员档案信息
     */
    public static final String LOAD_MEMBER_INFO = "userInfo";

    /**
     * 血压列表
     */
    public static final String BLOODPRE_HISTORY = "bloodpreHistory";
    /**
     * 体温列表
     */
    public static final String TEMPERATURE_HISTORY = "temperatureHistory";
    /**
     * 血糖列表
     */
    public static final String GLU_HISTORY = "bloodSugarHistory";
    /**
     * 血氧列表
     */
    public static final String BLOOD_OXYGEN_HISTORY = "bloodOxygenHistory";
    /**
     * 心率列表
     */
    public static final String HEARTRATE_HISTORY = "heartrateHistory";
    /*#########***** 远程咨询——开始 *****#########*/

    /**
     * 加载地区数据
     */
//    public static final String LOADING_AREA_DATA = "getPlace";

    /**
     * 血型列表
     */
//    public static final String LOAD_BLOOD_TYPE = "bloodtypeList";

    /**
     * 药物过敏史
     */
//    public static final String LOAD_ALLERGY_LIST = "allergyList";

    /**
     * 既往病史列表
     */
//    public static final String LOAD_PAST_HISTORY_LIST = "medicalList";

    /**
     * 遗传病列表
     */
//    public static final String LOAD_HEREDITARY_LIST = "geneticList";



    /**
     * 提交家庭成员档案信息
     */
    public static final String SUBMIT_MEMBER_INFO = "modifyUserInfo";

    /**
     * 病历类型
     */
    public static final String LOAD_CASE_HISTORY_TYPE = "getRecordList";

    /**
     * 添加病历
     */
    public static final String ADD_CASE_HISTORY = "addMedCard";

    /**
     * 病例列表
     */
    public static final String LOAD_CASE_HISTORY_LIST = "medCardList";

    /**
     * 删除病历
     */
    public static final String DELETE_CASE_HISTORY = "delmedCard";

    /**
     * 健康评估列表
     */
    public static final String LOAD_QUESTION_LIST = "questionList";

    /**
     * 删除评估问卷
     */
    public static final String DELETE_QUESTION = "delquestion";

    /**
     * 添加问卷HTML5页面
     */
    public static final String LOAD_ADD_QUESTION = "question";

    /**
     * 预览问卷
     */
    public static final String PREVIEW_QUESTION = "reQuestion";

    /*#########***** 远程咨询——结束 *****#########*/






    /*#########***** 家庭监护——开始 *****#########*/


    /*#########***** 家庭监护——结束 *****#########*/



    /*#########***** 家庭档案——开始 *****#########*/
    /**
     * 添加定位设备
     */
    public static final String ADD_DEVICE = "addMobile";

    /**
     * 定位设备列表
     */
    public static final String LOAD_DEVICE_LIST = "mobileList";

    /**
     * 删除定位设备
     */
    public static final String DELETE_DEVICE = "delMobile";

    /**
     * 添加家庭成员
     */
    public static final String DEVICE_REGISTER = "deviceregister";

    /**
     * 删除家庭成员
     */
    public static final String DELETE_USER = "deluser";

    /*#########***** 家庭档案——结束 *****#########*/


    /*#########***** 健康监护——开始 *****#########*/
    /**
     * 血糖
     */
    // 血糖基础数据
//    public static final String LOAD_GLU_BASIC_DATA = "getBloodSugarDataList";

    // 上传血糖测量结果
    public static final String UPLOAD_GLU_DATA = "addBloodSugar";

    // 血糖历史数据
    public static final String LOAD_GLU_HIS_DATA = "bloodSugarHistory";


    /**
     * 血压
     */
    // 血压基础数据
//    public static final String LOAD_BP_BASIC_DATA = "getBloodpreDataList";

    // 上传血压测量结果
    public static final String UPLOAD_BP_DATA = "addBloodpre";

    // 血压历史数据
    public static final String LOAD_BP_HIS_DATA = "bloodpreHistory";

    /**
     * 心电
     */
    // 心电基础数据
//    public static final String LOAD_ECG_BASIC_DATA = "getHeartRateDataList";

    // 上传心电测量结果
    public static final String UPLOAD_ECG_DATA = "addHeartRate";

    // 心电历史数据
    public static final String LOAD_ECG_HIS_DATA = "heartrateHistory";

    /**
     * 血氧
     */
    // 血氧基础数据
//    public static final String LOAD_SPO2H_BASIC_DATA = "getBloodOxygenDataList";

    // 上传血氧测量结果
    public static final String UPLOAD_SPO2H_DATA = "addBloodOxygen";

    // 血氧历史数据
    public static final String LOAD_SPO2H_HIS_DATA = "bloodOxygenHistory";

    /**
     * 体温
     */
    // 体温基础数据
//    public static final String LOAD_TEMP_BASIC_DATA = "getTemperatureDataList";

    // 上传体温测量结果
    public static final String UPLOAD_TEMP_DATA = "addTemperature";

    // 体温历史数据
    public static final String LOAD_TEMP_HIS_DATA = "temperatureHistory";

    /*#########***** 健康监护——结束 *****#########*/







    /*#########***** 家庭相册——开始 *****#########*/
    /**
     * 家庭相册列表
     */
    public static final String LOAD_ALBUM_LIST = "photoList";

    /**
     * 添加家庭相册
     */
    public static final String ADD_IMG_TO_ALBUM = "createPhoto";

    /**
     * 删除照片
     */
    public static final String DELETE_IMG = "delPhoto";

    /*#########***** 家庭相册——结束 *****#########*/







    /*#########***** 一键帮助——开始 *****#########*/


    /*#########***** 一键帮助——结束 *****#########*/







    /*#########***** 定位追踪——开始 *****#########*/


    /*#########***** 全球追踪——结束 *****#########*/
    /**
     * 获取移动定位设备坐标
     */
    public static final String QUERY_MOBILE = "queryMobileByDuserid";






    /*#########***** 生活服务——开始 *****#########*/
    /**
     * 健康养生——标签
     */
    public static final String LOADING_HEALTH_TABS = "healthLabel";

    /**
     * 健康养生——内容列表
     */
    public static final String LOADING_HEALTH_LIST = "healthList";

    /**
     * 健康养生——详情
     */
    public static final String GET_HEALTH_INFO_URL = "healthview";

    /**
     * 成长关怀
     */
    public static final String SERVICE_GROWUP = "growup";





    /*#########***** 生活服务——结束 *****#########*/








    /*#########***** 本机设置——开始 *****#########*/
    /**
     * 检查更新
     */
    public static final String CHECK_UPGRADE = "checkversion";


    /*#########***** 本机设置——结束 *****#########*/

    /**
     *
     * @param 截获Request 添加参数
     * @return
     */

    public static Request interceptRequestParam(Request request) {
        Map<String, String> params = new TreeMap<>();
        Context context = BaseApplication.getAppContext();
        String url = request.url().toString();
        HttpUrl.Builder httpUrlBuilder = request.url().newBuilder();

        //params.put("deviceid", DeviceUtils.getDeviceId(context));
        params.put("v", DeviceUtils.getVersionCode(context));
        params.put("st", String.valueOf(System.currentTimeMillis()));

        for (Map.Entry<String, String> param : params.entrySet()) {
            httpUrlBuilder.addQueryParameter(param.getKey(), param.getValue());
        }

        if (url.indexOf('?') > 0) {
            params.putAll(StringUtils.stringToMap(url.substring(url.indexOf("?") + 1)));
        }
        httpUrlBuilder.addQueryParameter("data",
                RequestParamsUtil.encrypt(RequestParamsUtil.createLinkedString(params)));

        request = request.newBuilder()
                .url(httpUrlBuilder.build())
                .build();
        return request;
    }
}
