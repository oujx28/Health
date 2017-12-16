package com.luckystar.health.modules.consult;

import android.os.Bundle;

import com.luckystar.health.R;
import com.luckystar.health.base.BaseFragment;

/**
 * Created by Administrator on 2017/9/22.
 */

public class DrugRemindFragment extends BaseFragment {
    @Override
    public void onCreateFragment(Bundle savedInstanceState) {
        setContentView(R.layout.com_right_content);
    }

    public static DrugRemindFragment newInstance() {
        DrugRemindFragment fragment = new DrugRemindFragment();
        return fragment;
    }
}
