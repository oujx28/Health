package com.luckystar.health.component;


import android.content.Context;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.google.gson.JsonObject;
import com.luckystar.health.base.BaseApplication;
import com.luckystar.health.bean.BpHisBean;
import com.luckystar.health.bean.EcgHisBean;
import com.luckystar.health.bean.GluHisBean;
import com.luckystar.health.bean.HealthTabBean;
import com.luckystar.health.bean.UserInfoBean;
import com.luckystar.health.common.AppConfigs;
import com.luckystar.health.common.utils.DeviceUtils;
import com.luckystar.health.common.utils.RxUtil;
import com.luckystar.health.common.utils.ToastUtil;
import com.luckystar.health.common.utils.Util;
import com.luckystar.health.bean.ResponseBean;
import com.luckystar.health.bean.UserBean;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.luckystar.health.component.RequestUtil.LOADING_HEALTH_TABS;
import static com.luckystar.health.component.RequestUtil.interceptRequestParam;

public class RetrofitSingleton {

    private static final String TAG = "RetrofitSingleton";
    private static ApiInterface sApiService = null;
    private static Retrofit sRetrofit = null;
    private static OkHttpClient sOkHttpClient = null;
    private static Context sContext = null;

    private static final int PAGE = 1;
    private static final int PAGESIZE = 100000;

    private void init() {
        initOkHttp();
        initRetrofit();
        sApiService = sRetrofit.create(ApiInterface.class);
        sContext = BaseApplication.getAppContext();
    }

    private RetrofitSingleton() {
        init();
    }

    public static RetrofitSingleton getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final RetrofitSingleton INSTANCE = new RetrofitSingleton();
    }

    private static void initOkHttp() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        // 缓存 http://www.jianshu.com/p/93153b34310e
        File cacheFile = new File(AppConfigs.NET_CACHE);
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 50);
        Interceptor cacheInterceptor = chain -> {
            Request request = chain.request();
            request = interceptRequestParam(request);
            if (!Util.isNetworkConnected(BaseApplication.getAppContext())) {
                request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .addHeader("Connection", "close")
                        .build();
            }
            Response response = chain.proceed(request);
            Response.Builder newBuilder = response.newBuilder();
            if (Util.isNetworkConnected(BaseApplication.getAppContext())) {
                int maxAge = 0;
                // 有网络时 设置缓存超时时间0个小时
                newBuilder.header("Cache-Control", "public, max-age=" + maxAge);
            } else {
                // 无网络时，设置超时为4周
                int maxStale = 60 * 60 * 24 * 28;
                newBuilder.header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale);
            }
            return newBuilder.build();
        };
        builder.cache(cache).addInterceptor(cacheInterceptor);

        //设置超时
        builder.connectTimeout(15, TimeUnit.SECONDS);
        builder.readTimeout(20, TimeUnit.SECONDS);
        builder.writeTimeout(20, TimeUnit.SECONDS);
        //错误重连
        builder.retryOnConnectionFailure(true);
        sOkHttpClient = builder.build();
    }

    private static void initRetrofit() {
        sRetrofit = new Retrofit.Builder()
            .baseUrl(AppConfigs.BASE_URL)
            .client(sOkHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build();
    }

    private static Consumer<Throwable> disposeFailureInfo(Throwable t) {
        return throwable -> {
            if (t.toString().contains("GaiException") || t.toString().contains("SocketTimeoutException") ||
                t.toString().contains("UnknownHostException")) {
                ToastUtil.showShort("网络问题");
            } else if (t.toString().contains("API没有")) {
                ToastUtil.showShort("错误: " + t.getMessage());
            }
            PLog.w(t.getMessage());
        };
    }

    private boolean isResponseOk(ResponseBean<?> response) {
        if ("ok".equals(response.describe)) {
            return true;
        }
        return false;
    }

    public Observable<UserBean> getUsers() {
        return sApiService.getUsers(DeviceUtils.getDeviceId(sContext))
                .flatMap(response -> {
                    if (isResponseOk(response)) {
                        return Observable.fromIterable(response.objlist);
                    }
                    return Observable.error(new RuntimeException(String.format("getUsers 获取用户数据出错")));
                })
                .doOnError(RetrofitSingleton::disposeFailureInfo)
                .subscribeOn(Schedulers.io());
                //.compose(RxUtil.io());

    }

    public Observable<UserInfoBean> getUserInfo(String userid) {
        return sApiService.getUserInfo(userid)
                .flatMap(response -> {
                    if (isResponseOk(response)) {
                        return Observable.just(response.obj);
                    }
                    return Observable.error(new RuntimeException("getUserInfo 获取用户数据出错"));
                })
                .doOnError(RetrofitSingleton::disposeFailureInfo)
                .subscribeOn(Schedulers.io());
    }

    public Observable<Object> getHistory(String type, String userid) {
        return sApiService.getHistory(type, userid, PAGE, PAGESIZE)
                .flatMap(response -> {
                    if (response.objlist != null || response.objlist.size() == 0) {
                        return Observable.fromIterable(response.objlist);
                    }
                    return Observable.error(new RuntimeException(String.format("用户" + userid + "没有数据")));
                })
                .doOnError(RetrofitSingleton::disposeFailureInfo)
                .subscribeOn(Schedulers.io());
                //.compose(RxUtil.io());
    }

    public Observable<List<HealthTabBean>> getHealthTab() {
        return sApiService.getContent()
                .flatMap(response -> {
                    if (isResponseOk(response)) {
                        return Observable.just(response.objlist);
                    }
                    return Observable.error(new RuntimeException("获取不到Health Tab"));
                })
                .doOnError(RetrofitSingleton::disposeFailureInfo)
                .compose(RxUtil.io());
                //.subscribeOn(Schedulers.io())
    }
}
