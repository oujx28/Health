package com.luckystar.health.modules.monitor;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import com.luckystar.health.R;
import com.luckystar.health.base.BaseActivity;
import com.luckystar.health.modules.common.TestFragment;

/**
 * Created by Administrator on 2017/9/22.
 */

public class MonitorMainActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.com_main_act);
    }

    @Override
    public void initSync() {
        setToolbarTitle(R.string.monitor_main_title);
        setNavigation();

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.fragment_container, TestFragment.newInstance("请打开手机端App，家庭看护功能"));
        ft.commit();
    }

    public static void newInstance(Context context) {
        Intent intent = new Intent(context, MonitorMainActivity.class);
        context.startActivity(intent);
    }
}
