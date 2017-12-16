package com.luckystar.health.modules.archives;

import android.os.Bundle;

import com.luckystar.health.R;
import com.luckystar.health.base.BaseFragment;

/**
 * Created by Administrator on 2017/9/22.
 */

public class ArchivesListFragment extends BaseFragment {

    @Override
    public void onCreateFragment(Bundle savedInstanceState) {
        setContentView(R.layout.archives_list_fg);
    }

    public static ArchivesListFragment newInstance() {
        ArchivesListFragment fragment = new ArchivesListFragment();
        return fragment;
    }
}
