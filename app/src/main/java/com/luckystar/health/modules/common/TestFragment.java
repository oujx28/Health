package com.luckystar.health.modules.common;

import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckedTextView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import android.widget.LinearLayout.LayoutParams;

import com.luckystar.health.R;
import com.luckystar.health.base.BaseFragment;
import com.luckystar.health.base.SimpleBaseAdapter;
import com.trello.rxlifecycle2.components.support.RxFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/9/22.
 */

public class TestFragment extends RxFragment {

    private static final String KEY_CONTENT = "Content";

    public static TestFragment newInstance(String content) {
        TestFragment fragment = new TestFragment();
        Bundle bundle = new Bundle();
        bundle.putString(KEY_CONTENT, content);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        TextView tv = new TextView(getContext());
        tv.setGravity(Gravity.CENTER);
        tv.setText(getArguments().getString(KEY_CONTENT));
        tv.setTextSize(18);
        tv.setPadding(20, 20, 20, 20);

        LinearLayout layout = new LinearLayout(getContext());
        layout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        layout.setGravity(Gravity.CENTER);
        layout.addView(tv);

        return layout;
    }

}
