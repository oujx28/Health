package com.luckystar.health.modules.consult;

import android.os.Bundle;

import com.luckystar.health.R;
import com.luckystar.health.base.BaseFragment;

/**
 * Created by Administrator on 2017/9/22.
 */

public class HealthRecordsFragment extends BaseFragment {

    @Override
    public void onCreateFragment(Bundle savedInstanceState) {
        setContentView(R.layout.consult_health_records);
    }

    public static HealthRecordsFragment newInstance() {
        HealthRecordsFragment fragment = new HealthRecordsFragment();
        return fragment;
    }

}
