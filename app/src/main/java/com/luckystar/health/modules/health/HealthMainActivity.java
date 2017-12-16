package com.luckystar.health.modules.health;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.AdapterView;

import com.luckystar.health.R;
import com.luckystar.health.base.BaseActivity;
import com.luckystar.health.component.PLog;
import com.luckystar.health.modules.common.LeftMenuFragment;

/**
 * Created by Administrator on 2017/9/23.
 */

public class HealthMainActivity extends BaseActivity implements LeftMenuFragment.OnMenuItemClickListener {

    public static final String TAG = "HealthMainActivity";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.com_main_act);
    }

    @Override
    public void initSync() {
        setToolbarTitle(R.string.health_main_title); // 标题
        setNavigation(); // 返回

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        LeftMenuFragment leftMenuFragment = LeftMenuFragment.newInstance(LeftMenuFragment.TYPE_HEALTH);
        ft.add(R.id.left_menu, leftMenuFragment);

        ft.commit();
    }

    @Override
    public void onMenuItemClick(AdapterView<?> parent, View view, int position, long id) {
        PLog.d(TAG, "Menu item position=" + position);
    }

    public static void newInstance(Context context) {
        Intent intent = new Intent(context, HealthMainActivity.class);
        context.startActivity(intent);
    }
}
