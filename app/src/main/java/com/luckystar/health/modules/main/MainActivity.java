package com.luckystar.health.modules.main;

import android.os.Bundle;
import android.view.View;

import com.luckystar.health.R;
import com.luckystar.health.base.BaseActivity;
import com.luckystar.health.bean.BpHisBean;
import com.luckystar.health.bean.EcgHisBean;
import com.luckystar.health.bean.GluHisBean;
import com.luckystar.health.bean.Spo2hHisBean;
import com.luckystar.health.bean.TempHisBean;
import com.luckystar.health.bean.UserBean;
import com.luckystar.health.bean.UserInfoBean;
import com.luckystar.health.common.utils.RxUtil;
import com.luckystar.health.common.utils.ToastUtil;
import com.luckystar.health.component.PLog;
import com.luckystar.health.component.RequestUtil;
import com.luckystar.health.component.RetrofitSingleton;
import com.luckystar.health.db.DBManager;
import com.luckystar.health.db.table.UserInfo;

import io.reactivex.Observable;

import com.luckystar.health.common.utils.GsonUtil;
import com.luckystar.health.modules.archives.ArchivesMainActivity;
import com.luckystar.health.modules.consult.ConsultMainActivity;
import com.luckystar.health.modules.health.HealthMainActivity;
import com.luckystar.health.modules.help.HelpDialogFragment;
import com.luckystar.health.modules.location.LocationMainActivity;
import com.luckystar.health.modules.monitor.MonitorMainActivity;
import com.luckystar.health.modules.services.ServicesMainActivity;


/**
 * 健康医疗主页——包括：
 * 远程咨询（consult）
 * 家庭看护（monitor）
 * 家庭档案（archives）
 * 健康检查（health）
 * 家庭相册（album）
 * 一键帮助（help）
 * 定位追踪（location）
 * 生活服务（service）
 * 本机设置（settings）
 */
public class MainActivity extends BaseActivity {
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadInfoByNetwork();
    }

    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.ll_consult:
                ConsultMainActivity.newInstance(this);
                break;
            case R.id.ll_monitor:
                MonitorMainActivity.newInstance(this);
                break;
            case R.id.ll_archives:
                ArchivesMainActivity.newInstance(this);
                break;
            case R.id.ll_health:
                HealthMainActivity.newInstance(this);
                break;
            case R.id.ll_album:
                break;
            case R.id.ll_help:
                HelpDialogFragment.newInstance(getSupportFragmentManager());
                break;
            case R.id.ll_location:
                LocationMainActivity.newInstance(this);
                break;
            case R.id.ll_service:
                ServicesMainActivity.newInstance(this);
                break;
            case R.id.ll_settings:
                break;
        }
    }

    private Observable<UserBean> loadUsersByNetwork() {
        return RetrofitSingleton.getInstance()
                .getUsers()
                .compose(RxUtil.activityLifecycle(this));
    }

    private Observable<UserInfoBean> loadUserInfo(String userid) {
        return RetrofitSingleton.getInstance()
                .getUserInfo(userid)
                .compose(RxUtil.activityLifecycle(this));
    }

    private Observable<Object> loadHistory(String type, String userid) {
        return RetrofitSingleton.getInstance()
                .getHistory(type, userid)
                .compose(RxUtil.activityLifecycle(this));
    }

    private void loadInfoByNetwork() {
        loadUsersByNetwork()
                .doOnError(throwable -> {
                    PLog.e(TAG, "获取用户列表失败");
                    throwable.printStackTrace();
                })
                .doOnSubscribe(action -> ToastUtil.showShort("正在加载用户列表...."))
                .doOnNext(user -> {
                    //PLog.e(TAG, user.name + " +++++++++++++++ " + Thread.currentThread().getName());
                    UserInfo userinfo = DBManager.getInstance().getUserInfo(user.id);
                    if (userinfo == null) {
                        getAllHistory(user.id);
                        loadUserInfo(user.id)
                                .subscribe(detail -> {
                                    DBManager.getInstance().insertUserInfo(new UserInfo(detail.id, detail.name, detail.headicon,
                                            detail.sex, Integer.parseInt(detail.age), Integer.parseInt(detail.height), Integer.parseInt(detail.weight),
                                            detail.cityname, detail.cityid, detail.phone, detail.bloodtypeid, detail.bloodtypename,
                                            detail.allergyid, detail.allergyname, detail.medicalid, detail.medicalname,
                                            detail.geneticid, detail.geneticname, detail.descr,
                                            "0", "0", "0", user.isbandmobile, detail.createtimedate));
                                });
                    }
                })
                .doOnComplete(() -> {
                    PLog.e(TAG, "获取用户列表 加载完毕");
                })
                .subscribe();
    }

    private void getAllHistory(String userid) {
        Observable.just(RequestUtil.BLOODPRE_HISTORY, RequestUtil.TEMPERATURE_HISTORY, RequestUtil.GLU_HISTORY,
                RequestUtil.BLOOD_OXYGEN_HISTORY, RequestUtil.HEARTRATE_HISTORY)
                .doOnNext(type -> {
                    loadHistory(type, userid)
                            .doOnError(throwable -> PLog.e(TAG, throwable.toString()))
                            .doOnNext(history -> {
                                switch (type) {
                                    case RequestUtil.BLOODPRE_HISTORY:
                                        PLog.e(TAG, GsonUtil.GsonToBean(history, BpHisBean.class).toString());
                                        break;
                                    case RequestUtil.TEMPERATURE_HISTORY:
                                        PLog.e(TAG, GsonUtil.GsonToBean(history, TempHisBean.class).toString());
                                        break;
                                    case RequestUtil.GLU_HISTORY:
                                        PLog.e(TAG, GsonUtil.GsonToBean(history, GluHisBean.class).toString());
                                        break;
                                    case RequestUtil.BLOOD_OXYGEN_HISTORY:
                                        PLog.e(TAG, GsonUtil.GsonToBean(history, Spo2hHisBean.class).toString());
                                        break;
                                    case RequestUtil.HEARTRATE_HISTORY:
                                        PLog.e(TAG, GsonUtil.GsonToBean(history, EcgHisBean.class).toString());
                                        break;
                                }
                            })
                            .subscribe();
                })
                .subscribe();
    }
}
