package com.luckystar.health.modules.services;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.AdapterView;

import com.luckystar.health.R;
import com.luckystar.health.base.BaseActivity;
import com.luckystar.health.base.BaseFragment;
import com.luckystar.health.common.Constants;
import com.luckystar.health.common.utils.StringUtils;
import com.luckystar.health.common.utils.ToastUtil;
import com.luckystar.health.common.utils.Util;
import com.luckystar.health.component.PLog;
import com.luckystar.health.modules.common.LeftMenuFragment;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 生活服务主页：健康养生、网络电台、本地FM、娱乐天地、成长关怀、周边搜索和一键上网
 */

public class ServicesMainActivity extends BaseActivity implements LeftMenuFragment.OnMenuItemClickListener{

    public static final String TAG = "ServicesMainActivity";
    private LeftMenuFragment mLeftMenuFragment;
    private HealthTabFragment  mHealthTabFragment;
    private InternetRadioFragment  mInternetRadioFragment;

    private HashMap<Integer, BaseFragment> mFragmentMap;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.com_main_act);
    }

    @Override
    public void initSync() {
        // 标题
        setToolbarTitle(R.string.services_main_title);
        // 返回
        setNavigation();

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        mLeftMenuFragment = LeftMenuFragment.newInstance(LeftMenuFragment.TYPE_SERVICES);
        ft.add(R.id.left_menu, mLeftMenuFragment);
        mHealthTabFragment = HealthTabFragment.newInstance();
        ft.add(R.id.fragment_container, mHealthTabFragment);
        ft.commit();
    }

    @Override
    public void initAsync() {
        mInternetRadioFragment = InternetRadioFragment.newInstance();

        mFragmentMap = new HashMap<>();
        mFragmentMap.put(0, mHealthTabFragment);
        mFragmentMap.put(1, mInternetRadioFragment);
    }

    @Override
    public void onMenuItemClick(AdapterView<?> parent, View view, int position, long id) {
        PLog.d(TAG, "Menu item position=" + position);
        switch (position) {
            case 2: // 本地FM
                launchFM();
                break;
            case 3:
                Util.startActivityForWeb(this, Constants.WEB_URL_ENTERTAINMENT);
                break;
            case 4:
                Util.startActivityForWeb(this, Constants.WEB_URL_GROUP);
                break;
            case 6:
                Util.startActivityForWeb(this, Constants.WEB_URL_INTERNET);
                break;
            default:
                showFragment(mFragmentMap.get(position));
                break;
        }
    }

    private void showFragment(BaseFragment fragment) {
        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            if (fragment.isAdded()) {
                ft.show(fragment);
            } else {
                ft.replace(R.id.fragment_container, fragment);
            }
            ft.commit();
        }
    }

    private void resetMenuItemFocus() {
        int position = Constants.INVALID_INDEX;

        Set<Map.Entry<Integer, BaseFragment>> sets = mFragmentMap.entrySet();
        for (Map.Entry<Integer, BaseFragment> entry : sets) {
            if (entry.getValue().isVisible()) {
                position = entry.getKey();
            }
        }

        if (position != Constants.INVALID_INDEX) {
            mLeftMenuFragment.setItemChecked(position, true);
        }
    }

    private void launchFM() {
        try {
            Intent intent = new Intent();
            ComponentName comp = new ComponentName("com.mediatek.fmradio", "com.mediatek.fmradio.FmRadioActivity");
            intent.setComponent(comp);
            intent.setAction("android.intent.action.VIEW");
            startActivity(intent);
        } catch (Exception e) {
            ToastUtil.showShort("调用本地FM出错！");
            e.printStackTrace();
        }
    }

    public static void newInstance(Context context) {
        Intent intent = new Intent(context, ServicesMainActivity.class);
        context.startActivity(intent);
    }
}
