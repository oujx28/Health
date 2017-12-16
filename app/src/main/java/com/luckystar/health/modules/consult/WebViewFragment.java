package com.luckystar.health.modules.consult;

import android.os.Bundle;
import android.webkit.WebView;

import com.luckystar.health.R;
import com.luckystar.health.base.BaseFragment;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/9/21.
 */

public class WebViewFragment extends BaseFragment {

    public static final String TAG = "WebViewFragment";
    public static final String WEBURL = "WebUrl";
    @BindView(R.id.webview)
    WebView mWebView;

    @Override
    protected void onCreateFragment(Bundle savedInstanceState) {
        setContentView(R.layout.com_webview_fg);
    }

    @Override
    public void onStart() {
        super.onStart();
        String weburl = getArguments().getString(WEBURL);
        if (mWebView != null && weburl != null && !weburl.isEmpty()) {
            mWebView.getSettings().setJavaScriptEnabled(true);
            mWebView.loadUrl(weburl);
        }
    }

    public static WebViewFragment newInstance(String weburl) {
        WebViewFragment fragment = new WebViewFragment();
        Bundle bundle = new Bundle();
        bundle.putString(WEBURL, weburl);
        fragment.setArguments(bundle);
        return fragment;
    }
}
