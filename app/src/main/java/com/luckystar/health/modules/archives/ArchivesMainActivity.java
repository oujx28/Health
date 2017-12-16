package com.luckystar.health.modules.archives;

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
 * Created by Administrator on 2017/9/22.
 */

public class ArchivesMainActivity extends BaseActivity implements LeftMenuFragment.OnMenuItemClickListener{

    private static final String TAG = "ArchivesMainActivity";
    private LeftMenuFragment mLeftMenuFragment;
    private ArchivesListFragment mArchivesListFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.com_main_act);
    }

    @Override
    public void initSync() {
        // 标题
        setToolbarTitle(R.string.archives_main_title);
        setNavigation(); // 返回

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        mLeftMenuFragment = LeftMenuFragment.newInstance(LeftMenuFragment.TYPE_ARCHIVES);
        ft.add(R.id.left_menu, mLeftMenuFragment);
        mArchivesListFragment = ArchivesListFragment.newInstance();
        ft.add(R.id.fragment_container, mArchivesListFragment);
        ft.commit();
    }

    @Override
    public void onMenuItemClick(AdapterView<?> parent, View view, int position, long id) {
        PLog.d(TAG, "Menu item position=" + position);
    }

    public static void newInstance(Context context) {
        Intent intent = new Intent(context, ArchivesMainActivity.class);
        context.startActivity(intent);
    }
}
