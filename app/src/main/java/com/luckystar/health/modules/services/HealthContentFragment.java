package com.luckystar.health.modules.services;

import android.os.Bundle;

import com.luckystar.health.R;
import com.luckystar.health.base.BaseFragment;

/**
 * Created by Administrator on 2017/9/23.
 */

public class HealthContentFragment extends BaseFragment {

    @Override
    public void onCreateFragment(Bundle savedInstanceState) {
        setContentView(R.layout.dia_com_title);
    }

    public static HealthContentFragment newInstance() {
        HealthContentFragment fragment = new HealthContentFragment();
        return fragment;
    }
}
