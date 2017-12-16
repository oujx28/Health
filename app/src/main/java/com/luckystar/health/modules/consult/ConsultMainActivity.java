package com.luckystar.health.modules.consult;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.AdapterView;

import com.luckystar.health.R;
import com.luckystar.health.base.BaseActivity;
import com.luckystar.health.base.BaseFragment;
import com.luckystar.health.common.Constants;
import com.luckystar.health.common.utils.DeviceUtils;
import com.luckystar.health.component.PLog;
import com.luckystar.health.modules.common.LeftMenuFragment;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

/**
 * 远程咨询主页：健康档案、病历管理、健康评估、健康精选、健康咨询、用药提醒
 */

public class ConsultMainActivity extends BaseActivity implements LeftMenuFragment.OnMenuItemClickListener{
    public static final String TAG = "ConsultMainActivity";

    private LeftMenuFragment mLeftMenuFragment;
    private WebViewFragment mConsultFragment;
    private HealthRecordsFragment mHealthRecordsFragment;
    private CaseHistoryFragment mCaseHistoryFragment;
    private HealthAssessmentFragment mHealthAssessmentFragment;
    private DrugRemindFragment mDrugRemindFragment;
    private ConstitutionFragment mConstitutionFragment;
    /**
     * Fragment对象：健康档案、病历管理、健康评估、健康精选、健康咨询、用药提醒
     */
    private static final int HEALTH_RECORDS_INDEX = 0;
    private static final int HEALTH_HISTORY_INDEX = 1;
    private static final int HEALTH_ASSESSMENT_INDEX = 2;
    private static final int HEALTH_CHOICE_INDEX = 3;
    private static final int HEALTH_CONSULT_INDEX =  4;
    private static final int DRUG_REMIND_INDEX = 5;
    private static final int CONSTITUTION_INDEX = 6;

    private HashMap<Integer, BaseFragment> mFragmentMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.com_main_act);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        resetMenuItemFocus();
    }

    @Override
    public void initSync() {
        // 标题
        setToolbarTitle(R.string.consult_main_title);
        setNavigation();// 返回

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        /**
         * 创建LeftMenuFragment对象并添加
         */
        mLeftMenuFragment = LeftMenuFragment.newInstance(LeftMenuFragment.TYPE_CONSULT);
        ft.add(R.id.left_menu, mLeftMenuFragment);

        mHealthRecordsFragment = HealthRecordsFragment.newInstance();
        ft.add(R.id.fragment_container, mHealthRecordsFragment);
        ft.commit();
    }

    @Override
    public void initAsync() {
        mCaseHistoryFragment = CaseHistoryFragment.newInstance();
        mConsultFragment = WebViewFragment.newInstance(Constants.WEB_URL_CONSULT + DeviceUtils.getDeviceId(this));
        mHealthAssessmentFragment = HealthAssessmentFragment.newInstance();
        mDrugRemindFragment = DrugRemindFragment.newInstance();
        mConstitutionFragment = ConstitutionFragment.newInstance();

        mFragmentMap = new HashMap<Integer, BaseFragment>();
        mFragmentMap.put(HEALTH_RECORDS_INDEX, mHealthRecordsFragment);
        mFragmentMap.put(HEALTH_HISTORY_INDEX, mCaseHistoryFragment);
        mFragmentMap.put(HEALTH_ASSESSMENT_INDEX, mHealthAssessmentFragment);
        mFragmentMap.put(HEALTH_CONSULT_INDEX, mConsultFragment);
        mFragmentMap.put(DRUG_REMIND_INDEX, mDrugRemindFragment);
        mFragmentMap.put(CONSTITUTION_INDEX, mConstitutionFragment);
    }

    @Override
    public void onMenuItemClick(AdapterView<?> parent, View view, int position, long id) {
        PLog.d(TAG, "Menu item position=" + position);
        switch(position) {
            case HEALTH_CHOICE_INDEX: // 健康精选
                WebViewActivity.newInstance(this, Constants.WEB_URL_CHOICE);
                break;
            default:
                showFragment(mFragmentMap.get(position));
                break;
        }
    }

    private void showFragment(BaseFragment fragment) {
        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            if (fragment.isAdded()) {
                ft.show(fragment);
            } else {
                ft.replace(R.id.fragment_container, fragment);
            }
            ft.commit();
        }
    }

    private void resetMenuItemFocus() {
        int position = Constants.INVALID_INDEX;

        Set<Entry<Integer, BaseFragment>> sets = mFragmentMap.entrySet();
        for (Entry<Integer, BaseFragment> entry : sets) {
            if (entry.getValue().isVisible()) {
                position = entry.getKey();
            }
        }

        if (position != Constants.INVALID_INDEX) {
            mLeftMenuFragment.setItemChecked(position, true);
        }
    }

    public static void newInstance(Context context) {
        Intent intent = new Intent(context, ConsultMainActivity.class);
        context.startActivity(intent);
    }
}
