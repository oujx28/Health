package com.luckystar.health.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.StringRes;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.luckystar.health.R;

/**
 * Created by Administrator on 2017/9/11.
 */

public class BaseActivity extends RxAppCompatActivity {
    private Unbinder mUnbinder;
    /**
     * Toolbar对象 (App Bar)
     */
    protected Toolbar mToolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("BaseActivity", getClass().getSimpleName());
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);

        mUnbinder = ButterKnife.bind(this);
        findView();
        initSync();
        getContentView().postDelayed(this::initAsync, 1000);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mUnbinder != null) {
            mUnbinder.unbind();
            mUnbinder = null;
        }
    }

    public void findView() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
    }

    public View getContentView() {
        return getWindow().getDecorView();
    }

    public void initAsync() {
    }

    public void initSync() {
    }

    /**
     * 设置ToolBar标题
     *
     * @param resId 标题资源Id
     */
    protected void setToolbarTitle(@StringRes int resId) {
        TextView titleTv = (TextView) findViewById(R.id.toolbar_title);
        if (titleTv != null && resId != 0)
            titleTv.setText(resId);
    }

    /**
     * 返回
     */
    protected void setNavigation() {
        if (mToolbar != null) {
            mToolbar.setNavigationIcon(R.drawable.com_back);
            mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }
    }

    /**
     * 返回
     *
     * @param resId 图标资源
     */
    protected void setNavigation(@DrawableRes int resId) {
        if (mToolbar != null) {
            mToolbar.setNavigationIcon(resId);
            mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }
    }
}
