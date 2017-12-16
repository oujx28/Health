package com.luckystar.health.modules.services;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.ListFragment;
import android.support.v4.view.ViewPager;

import com.lhh.apst.library.AdvancedPagerSlidingTabStrip;
import com.luckystar.health.R;
import com.luckystar.health.base.BaseFragment;
import com.luckystar.health.bean.HealthTabBean;
import com.luckystar.health.common.utils.RxUtil;
import com.luckystar.health.common.utils.ToastUtil;
import com.luckystar.health.component.PLog;
import com.luckystar.health.component.RetrofitSingleton;

import java.util.List;

import butterknife.BindView;
import io.reactivex.Observable;

/**
 * Created by Administrator on 2017/9/23.
 */

public class HealthTabFragment extends BaseFragment {

    public static final String TAG = "HealthTabFragment";
    @BindView(R.id.tabs)
    AdvancedPagerSlidingTabStrip mPagerTabStrip;
    @BindView(R.id.viewPager)
    ViewPager mViewPager;

    @Override
    public void onCreateFragment(Bundle savedInstanceState) {
        setContentView(R.layout.services_health_tab);
    }

    @Override
    public void initSync() {
        getHealthTab()
                .doOnError(action -> ToastUtil.showShort("获取健康养生标签失败！"))
                .subscribe(list -> {
                    setHealthTabs(list);
                });
    }

    private Observable<List<HealthTabBean>> getHealthTab() {
        return RetrofitSingleton.getInstance()
                .getHealthTab()
                .compose(RxUtil.fragmentLifecycle(this));
    }

    public static HealthTabFragment newInstance() {
        HealthTabFragment fragment = new HealthTabFragment();
        return fragment;
    }
    /**
     * 设置健康养生标签
     *
     * @param list 数据源
     */
    private void setHealthTabs(List<HealthTabBean> list) {
        HealthTabAdapter adapter = new HealthTabAdapter(getChildFragmentManager(), list);
        mViewPager.setAdapter(adapter);

        mPagerTabStrip.setViewPager(mViewPager);
    }
    /**
     * 健康养生适配器
     */
    static class HealthTabAdapter extends FragmentPagerAdapter {

        private final List<HealthTabBean> mList;

        public HealthTabAdapter(FragmentManager fm, List<HealthTabBean> list) {
            super(fm);
            mList = list;
        }

        @Override
        public Fragment getItem(int position) {
            return HealthContentFragment.newInstance();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mList.get(position).name;
        }

        @Override
        public int getCount() {
            return (mList ==  null || mList.isEmpty()) ? 0 : mList.size();
        }
    }
}
