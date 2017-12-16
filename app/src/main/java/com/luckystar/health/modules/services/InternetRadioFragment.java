package com.luckystar.health.modules.services;

import android.os.Bundle;

import com.luckystar.health.R;
import com.luckystar.health.base.BaseFragment;

/**
 * Created by Administrator on 2017/9/23.
 */

public class InternetRadioFragment extends BaseFragment {

    @Override
    public void onCreateFragment(Bundle savedInstanceState) {
        setContentView(R.layout.internet_radio_fg);
    }

    public static InternetRadioFragment newInstance() {
        InternetRadioFragment fragment = new InternetRadioFragment();
        return fragment;
    }
}
