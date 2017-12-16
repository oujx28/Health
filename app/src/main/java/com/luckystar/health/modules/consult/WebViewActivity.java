package com.luckystar.health.modules.consult;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.luckystar.health.R;
import com.luckystar.health.base.BaseActivity;

import butterknife.BindView;


public class WebViewActivity extends BaseActivity {

    public static final String TAG = "WebViewActivity";
    public static final String WEBURL = "WebUrl";

    @BindView(R.id.webview)
    WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
    }

    @Override
    public void onStart() {
        super.onStart();
        String weburl = getIntent().getStringExtra(WEBURL);
        if (mWebView != null && weburl != null && !weburl.isEmpty()) {
            mWebView.getSettings().setJavaScriptEnabled(true);
            mWebView.getSettings().setUserAgentString("mobile");
            mWebView.loadUrl(weburl);
        }
    }

    @Override
    public void initSync() {
        setNavigation();
    }


    public static void newInstance(Context context, String weburl) {
        Intent intent = new Intent(context, WebViewActivity.class);
        intent.putExtra(WEBURL, weburl);
        context.startActivity(intent);
    }
}
