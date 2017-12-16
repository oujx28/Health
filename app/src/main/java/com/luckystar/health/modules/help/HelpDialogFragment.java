package com.luckystar.health.modules.help;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.luckystar.health.R;

/**
 * Created by Administrator on 2017/9/23.
 */

public class HelpDialogFragment extends DialogFragment implements View.OnClickListener{

    public static final String TAG = "HelpDialogFragment";

    public static HelpDialogFragment newInstance() {
        HelpDialogFragment dialogFragment = new HelpDialogFragment();
        return dialogFragment;
    }

    public static void newInstance(FragmentManager manager) {
        newInstance().show(manager, TAG);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCancelable(false);
        setStyle(DialogFragment.STYLE_NO_TITLE, 0);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.help_dialog, container, false);
        // 标题
        TextView titleTv = (TextView) rootView.findViewById(R.id.tv_title);
        titleTv.setText(getString(R.string.help_title));

        rootView.findViewById(R.id.btn_close).setOnClickListener(this); // 关闭对话框
        rootView.findViewById(R.id.btn_phone).setOnClickListener(this); // 客服电话

        return rootView;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_close:
                if (isVisible()) {
                    dismiss();
                }
                break;
            case R.id.btn_phone:
                call();
                break;
        }
    }

    /**
     * 呼叫客服
     */
    private void call() {
        Intent phoneIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + getString(R.string.help_phone)));
        startActivity(phoneIntent);
    }
}
