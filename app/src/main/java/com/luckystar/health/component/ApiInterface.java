package com.luckystar.health.component;


import com.google.gson.JsonObject;
import com.luckystar.health.bean.BpHisBean;
import com.luckystar.health.bean.EcgHisBean;
import com.luckystar.health.bean.GluHisBean;
import com.luckystar.health.bean.HealthTabBean;
import com.luckystar.health.bean.ResponseBean;
import com.luckystar.health.bean.Spo2hHisBean;
import com.luckystar.health.bean.UserBean;
import com.luckystar.health.bean.UserInfoBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    /**
     * 家庭成员
     */
    @GET("duserlist")
    Observable<ResponseBean<UserBean>> getUsers(@Query("deviceid") String deviceid);

    @POST("userInfo")
    Observable<ResponseBean<UserInfoBean>> getUserInfo(@Query("deviceuserid") String userid);

    @POST("{type}")
    Observable<ResponseBean<Object>> getHistory(@Path("type") String type,
                                                    @Query("userid") String userid,
                                                    @Query("page") int page,
                                                    @Query("pageSize") int pageSize);

    @POST("healthLabel")
    Observable<ResponseBean<HealthTabBean>> getContent();
}
