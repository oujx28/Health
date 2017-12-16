package com.luckystar.health.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.luckystar.health.component.PLog;
import com.trello.rxlifecycle2.components.support.RxFragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Fragment基类
 */

public abstract class BaseFragment extends RxFragment {
    /**
     * 布局资源Id
     */
    private int mLayoutResId;

    /**
     * 根视图
     */
    protected View mRootView;

    /**
     * TAG标签
     */
    public final String TAG = this.getClass().getSimpleName();
    private Unbinder mUnbinder;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        PLog.i(TAG, "Fragment onAttach() is execute!");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PLog.i(TAG, "Fragment onCreate() is execute!");
        // Create Fragment
        onCreateFragment(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        PLog.i(TAG, "Fragment onCreateView() is execute!");
        if (mLayoutResId != 0 && mRootView == null) {
            mRootView = inflater.inflate(mLayoutResId, container, false);
        }
        if (mRootView != null) {
            mUnbinder = ButterKnife.bind(this, mRootView);
        }
        return mRootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        PLog.i(TAG, "Fragment onActivityCreated() is execute!");

        initSync();
        mRootView.postDelayed(this::initAsync, 100);
        setListeners();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        PLog.i(TAG, "Fragment onDestroyView() is execute!");
        if (mUnbinder != null) {
            mUnbinder.unbind();
            mUnbinder = null;
        }
    }

    protected abstract void onCreateFragment(Bundle savedInstanceState);

    /**
     * 设置视图内容
     *
     * @param layoutResId 布局资源Id
     */
    protected void setContentView(@LayoutRes int layoutResId) {
        if (layoutResId != 0)
            mLayoutResId = layoutResId;
    }


    public void initAsync() {

    }

    public void initSync() {

    }

    public void setListeners(){

    }
}
