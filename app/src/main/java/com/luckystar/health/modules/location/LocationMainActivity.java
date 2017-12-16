package com.luckystar.health.modules.location;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import com.luckystar.health.R;
import com.luckystar.health.base.BaseActivity;

/**
 * Created by Administrator on 2017/9/23.
 */

public class LocationMainActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.com_main_act);
    }

    @Override
    public void initSync() {
        setToolbarTitle(R.string.location_main_title);
        setNavigation();

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.fragment_container, LocationMapFragment.newInstance());
        ft.commit();
    }

    public static void newInstance(Context context) {
        Intent intent = new Intent(context, LocationMainActivity.class);
        context.startActivity(intent);
    }
}
