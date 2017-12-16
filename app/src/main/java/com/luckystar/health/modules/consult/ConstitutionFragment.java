package com.luckystar.health.modules.consult;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.IdRes;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.luckystar.health.R;
import com.luckystar.health.base.BaseFragment;
import com.luckystar.health.component.PLog;
import com.luckystar.health.component.radarview.RadarData;
import com.luckystar.health.component.radarview.RadarView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/9/26.
 */

public class ConstitutionFragment extends BaseFragment implements View.OnClickListener{

    @BindView(R.id.rv_list)
    RecyclerView mCategoryRecyclerView;
    @BindView(R.id.btn_submit)
    Button mSubmitBtn;
    @BindView(R.id.radarView)
    RadarView mRadarView;
    @BindView(R.id.tv_hint)
    TextView mHintTv;
    @BindView(R.id.layout_conclusion)
    LinearLayout mConclusionLayout;

    private CategoryAdapter mCategoryAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<String[]> mCategoryList;
    private int[] mCategoryScore;

    @Override
    public void onCreateFragment(Bundle savedInstancedState) {
        setContentView(R.layout.constitution_fg);
    }

    @Override
    public void initSync() {
        mCategoryList = new ArrayList<>();
        TypedArray categoryArray = getResources().obtainTypedArray(R.array.constitution_category_array);
        for (int i = 0; i < categoryArray.length(); i++) {
            String[] category = getResources().getStringArray(categoryArray.getResourceId(i, 0));
            mCategoryList.add(category);
        }
        mCategoryScore = new int[mCategoryList.size()];

        mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mCategoryAdapter = new CategoryAdapter();

        mCategoryRecyclerView.setLayoutManager(mLayoutManager);
        mCategoryRecyclerView.setAdapter(mCategoryAdapter);
        mCategoryRecyclerView.setItemViewCacheSize(20);
        mSubmitBtn.setOnClickListener(this);

        mConclusionLayout.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onClick(View view) {
        showConclusion();
    }

    private int  getConclusion(List<Float> values) {
        int i = 0;
        int count2 = 0;
        int count3 = 0;
        if (values.get(0) >= 60) {
            // conclustion A
            for(i = 1; i < values.size(); i++) {
                if (values.get(i) >= 30) {
                    break;
                }
            }
            if (i == values.size())
                return R.string.conclusion_a;

            for(i = 1; i < values.size(); i++) {
                float value = values.get(i);
                if (value >= 40) {
                    count2++;
                }
                if (value >= 30 && value <= 39) {
                    count3++;
                }
            }
            // conclustion B
            if (i == values.size()) {
                if (count3 == 1 )
                    return R.string.conclusion_b1;
                if (count3 > 1)
                    return R.string.conclusion_b2;
            }
            else if (count2 == 1 && count3 > 0) {
                // conclustion C1
                return R.string.conclusion_c1;
            } else if (count2 > 1) {
                return R.string.conclusion_d1;
            }
        } else {
            for (i = 1; i < values.size(); i++) {
                float value = values.get(i);
                if (value >= 40) {
                    count2++;
                }
                if (value >= 30 && value <= 39) {
                    count3++;
                }
            }
            if (count2 == 1 && count3 > 0) {
                // conclustion C2
                return R.string.conclusion_c2;
            } else if (count2 > 1) {
                return R.string.conclusion_d2;
            } else if (count3 == 1) {
                return R.string.conclusion_e1;
            } else if (count3 > 1) {
                return R.string.conclusion_e2;
            } else {
                return R.string.conclusion_g;
            }
        }
        return R.string.conclusion_g;
    }

    private Spanned getConclusionContent(int conclusion) {
        String content = getString(conclusion);
        switch (conclusion) {
            case R.string.conclusion_a:
                content += getString(R.string.hints_a);
                break;
            case R.string.conclusion_b1:
            case R.string.conclusion_b2:
                content += getString(R.string.hints_a) + getString(R.string.hints_b) + getString(R.string.hints_f);
                break;
            case R.string.conclusion_c1:
            case R.string.conclusion_c2:
                content += getString(R.string.hints_b) + getString(R.string.hints_c);
                break;
            case R.string.conclusion_d1:
                content += getString(R.string.hints_f) + getString(R.string.hints_c) + getString(R.string.hints_f);
                break;
            case R.string.conclusion_d2:
                content += getString(R.string.hints_f) + getString(R.string.hints_c);
                break;
            case R.string.conclusion_e1:
                content += getString(R.string.hints_c);
                break;
            case R.string.conclusion_e2:
                content += getString(R.string.hints_b) + getString(R.string.hints_c);
                break;
            case R.string.conclusion_g:
                break;
        }
        return Html.fromHtml(content);
    }

    private void showRadarView(List<Float> values) {
        mRadarView.setEmptyHint("无数据");
        List<Integer> layerColor = new ArrayList<>();
        Collections.addAll(layerColor,
                getColor(R.color.radar_layer_1), getColor(R.color.radar_layer_2),
                getColor(R.color.radar_layer_3), getColor(R.color.radar_layer_4));
        mRadarView.setLayerColor(layerColor);
        String[] type = getResources().getStringArray(R.array.constitution_type_array);
        List<String> vertexText = new ArrayList<>(Arrays.asList(type));
        mRadarView.setVertexText(vertexText);
        //List<Float> values = new ArrayList<>();
        //Collections.addAll(values, 68.75f, 12.5f, 12.5f, 12.5f, 12.5f, 25f, 25f, 25f, 25f);
        RadarData data = new RadarData(values, getColor(R.color.blue));
        mRadarView.addData(data);
    }

    private void showConclusion() {
        List<Float> values = new ArrayList<>();
        for (int i = 0; i < mCategoryScore.length; i++) {
            values.add(getReallyScore(mCategoryScore[i], mCategoryList.get(i).length - (i == 5 ? 2 : 1))); // 第5类 限女性或限男性
            PLog.e(TAG, i + ":" + mCategoryScore[i] + " -> " + values.get(i));
        }
        mHintTv.setText(getConclusionContent(getConclusion(values)));
        showRadarView(values);

        mCategoryRecyclerView.setVisibility(View.INVISIBLE);
        mConclusionLayout.setVisibility(View.VISIBLE);
    }

    private int getColor(@ColorRes int id) {
        return getResources().getColor(id);
    }

    private float getReallyScore(int score, int count) {
        if (score == 0 || score < count)
            return 0;
        return (float) (score - count) / (count * 4) * 100;
    }

    public static ConstitutionFragment newInstance() {
        ConstitutionFragment fragment = new ConstitutionFragment();
        return fragment;
    }

    /**
     * Category Adapter
     */
    public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.constitution_category, parent, false);
            ViewHolder viewHolder = new ViewHolder(v);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            CategoryItemAdapter adapter = new CategoryItemAdapter(position, mCategoryList.get(position));
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
            holder.mCategoryRecyclerView.setLayoutManager(layoutManager);
            holder.mCategoryRecyclerView.setAdapter(adapter);
            holder.mCategoryTitleTv.setText(mCategoryList.get(position)[0]);
        }

        @Override
        public int getItemCount() {
            return mCategoryList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            TextView mCategoryTitleTv;
            RecyclerView mCategoryRecyclerView;

            public ViewHolder (View itemView) {
                super(itemView);
                mCategoryTitleTv = (TextView) itemView.findViewById(R.id.tv_category_title);
                mCategoryRecyclerView = (RecyclerView) itemView.findViewById(R.id.rv_list);
            }
        }
    }

    /**
     * Category Item Adapter
     */
    public class CategoryItemAdapter extends RecyclerView.Adapter<CategoryItemAdapter.ViewHolder> {

        private int mCategoryType;
        private String[] mCategoryItems;
        public CategoryItemAdapter(int type, String[] categoryItems) {
            mCategoryType = type;
            mCategoryItems = categoryItems;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.constitution_category_item, parent, false);
            ViewHolder viewHolder = new ViewHolder(v);
            return viewHolder;
        }
        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.mScore = 0;
            holder.mTitleTv.setText(mCategoryItems[position + 1]);
            holder.mChoiceRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                    int score = 1;
                    switch(checkedId) {
                        case R.id.rb_choice_one:
                            score = 1;
                            break;
                        case R.id.rb_choice_two:
                            score = 2;
                            break;
                        case R.id.rb_choice_three:
                            score = 3;
                            break;
                        case R.id.rb_choice_four:
                            score = 4;
                            break;
                        case R.id.rb_choice_five:
                            score = 5;
                            break;
                    }
                    // 以下的项目是分数是从5 到 1
                    if (mCategoryType == 0 &&
                            (position == 1 || position == 2 || position == 3 || position == 4 || position == 6 || position == 7)) {
                        score = 6 - score;
                    }
                    //PLog.e(TAG, "category type:" + mCategoryType + " old score:" + holder.mScore + " new score:" + score);
                    mCategoryScore[mCategoryType] -= holder.mScore;
                    mCategoryScore[mCategoryType] += score;
                    holder.mScore = score;
                }
            });
        }

        @Override
        public int getItemCount() {
            return mCategoryItems.length -  1;
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            int mScore;
            TextView mTitleTv;
            RadioGroup mChoiceRg;
            public ViewHolder (View itemView) {
                super(itemView);
                mTitleTv = (TextView) itemView.findViewById(R.id.tv_title);
                mChoiceRg = (RadioGroup) itemView.findViewById(R.id.rg_choice);
            }
        }
    }
}
