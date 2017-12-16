package com.luckystar.health.modules.common;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckedTextView;
import android.widget.ListView;

import com.luckystar.health.R;
import com.luckystar.health.base.BaseFragment;
import com.luckystar.health.base.SimpleBaseAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/9/21.
 */

public class LeftMenuFragment extends BaseFragment {

    public static final String TYPE = "type";

    @BindView(R.id.listView)
    ListView mListView;

    /**
     * 菜单Item点击监听
     */
    private OnMenuItemClickListener mListener;
    private LeftMenuAdapter mAdapter;
    /**
     * 类型：远程咨询、家庭档案、健康监护、生活服务和本机设置
     */
    public static final String TYPE_CONSULT = "consult";
    public static final String TYPE_ARCHIVES = "archives";
    public static final String TYPE_HEALTH = "health";
    public static final String TYPE_SERVICES = "services";
    public static final String TYPE_SETTINGS = "settings";


    @Override
    public void onCreateFragment(Bundle savedInstanceState) {
        setContentView(R.layout.com_left_menu);
    }

    /**
     * 创建LeftMenuFragment实例
     *
     * @param type 类型：远程咨询、家庭档案、健康监护、生活服务和本机设置
     * @return LeftMenuFragment对象
     */
    public static LeftMenuFragment newInstance(String type) {
        LeftMenuFragment fragment = new LeftMenuFragment();
        Bundle bundle = new Bundle();
        bundle.putString(TYPE, type);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListener = (OnMenuItemClickListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString()
                    + " must implement OnMenuItemClickListener");
        }
    }

    /**
     * 初始化菜单数据
     */
    private void initMenu() {
        String[] menuArray = null;
        ArrayList<Integer> iconArray = new ArrayList<>();
        String type = getArguments().getString(TYPE);

        switch(type) {
            case TYPE_CONSULT:
                // 远程咨询
                menuArray = getResources().getStringArray(R.array.consult_menu_array);
                iconArray.add(R.drawable.cons_records_bg);
                iconArray.add(R.drawable.cons_case_history_bg);
                iconArray.add(R.drawable.cons_assess_bg);
                iconArray.add(R.drawable.cons_choice_bg);
                iconArray.add(R.drawable.cons_consult_bg);
                iconArray.add(R.drawable.cons_remind_bg);
                iconArray.add(R.drawable.cons_assess_bg);
                break;
            case TYPE_ARCHIVES:
                // 家庭档案
                menuArray = getResources().getStringArray(R.array.archives_menu_array);
                iconArray.add(R.drawable.archives_member_bg);
                iconArray.add(R.drawable.archives_device_bg);
                break;
            case TYPE_HEALTH:
                // 健康监护
                menuArray = getResources().getStringArray(R.array.health_menu_array);
                iconArray.add(R.drawable.health_device_bg);
                iconArray.add(R.drawable.health_random_bg);
                break;
            case TYPE_SERVICES:
                menuArray = getResources().getStringArray(R.array.services_menu_array);
                iconArray.add(R.drawable.ser_health_bg);
                iconArray.add(R.drawable.ser_transceiver_bg);
                iconArray.add(R.drawable.ser_fm_bg);
                iconArray.add(R.drawable.ser_entertainment_bg);
                iconArray.add(R.drawable.ser_grow_up_bg);
                iconArray.add(R.drawable.ser_search_bg);
                iconArray.add(R.drawable.ser_internet_bg);
                break;
            case TYPE_SETTINGS:
                menuArray = getResources().getStringArray(R.array.settings_menu_array);
                iconArray.add(R.drawable.set_about_bg);
                iconArray.add(R.drawable.set_setting_bg);
                iconArray.add(R.drawable.set_files_bg);
                iconArray.add(R.drawable.set_qr_code_bg);
                iconArray.add(R.drawable.set_ota_bg);
                break;
        }
        /**
         * 添加数据
         */
        if (menuArray != null) {
            List<Map<String, Object>> list = new ArrayList<>();
            for (int i = 0; i < menuArray.length; i++) {
                Map<String, Object> map = new HashMap<>();
                map.put("menuId", type + "Id" + i);
                map.put("menuName", menuArray[i]);
                if (!iconArray.isEmpty()) {
                    map.put("menuIcon", iconArray.get(i));
                }
                list.add(map);
            }
            mAdapter.appendToList(list);
        }
        setItemChecked(0, true);
    }

    @Override
    public void initSync() {
        super.initSync();

        // 设置ListView单选模式
        mListView.setItemsCanFocus(false);
        mListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        mAdapter = new LeftMenuAdapter(getContext());
        mListView.setAdapter(mAdapter);

        initMenu();
    }

    @Override
    public void setListeners() {
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mListener.onMenuItemClick(parent, view, position, id);
            }
        });
    }

    /**
     * 设置ListView中Item选中状态
     *
     * @param position  指针
     * @param isChecked 是否选中
     */
    public void setItemChecked(int position, boolean isChecked) {
        if (mListView != null) {
            if (position < mListView.getAdapter().getCount())
                mListView.setItemChecked(position, isChecked);
        }
    }

    /**
     * 菜单点击事件监听接口
     */
    public interface OnMenuItemClickListener {
        void onMenuItemClick(AdapterView<?> parent, View view, int position, long id);
    }

    public void setOnMenuItemClickListener(OnMenuItemClickListener l) {
        mListener = l;
    }

    /**
     * 左侧菜单适配器
     */
    public class LeftMenuAdapter extends SimpleBaseAdapter<Map<String, Object>> {

        LeftMenuAdapter(Context context) {
            super(context);
        }

        @Override
        public int initLayoutRes() {
            return R.layout.com_left_menu_item;
        }

        @Override
        public View getViewByViewHolder(int position, View convertView, ViewGroup parent) {
            CheckedTextView menuNameTv = (CheckedTextView) convertView.findViewById(R.id.tv_menu_name);
            menuNameTv.setText(mList.get(position).get("menuName").toString());
            if (mList.get(position).containsKey("menuIcon") && (int)mList.get(position).get("menuIcon") != 0) {
                menuNameTv.setCompoundDrawablePadding(12);
                menuNameTv.setCompoundDrawablesWithIntrinsicBounds(
                        (int)mList.get(position).get("menuIcon"), 0,
                        R.drawable.com_click_bg, 0);
            }
            return convertView;
        }
    }
}