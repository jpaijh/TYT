package com.qskj.tyt.activity;

import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.lsjwzh.widget.materialloadingprogressbar.CircleProgressBar;
import com.qskj.tyt.AppDelegate;
import com.qskj.tyt.R;
import com.umeng.analytics.MobclickAgent;


import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * 打开链接的WebView
 */
@EActivity(R.layout.cb_activity_web_view)
public class CB_WebActivity extends BaseActivity {

    private static final String TAG = "CB_WebActivity";

    @ViewById(R.id.webView)
    WebView mWebView;

    @ViewById(R.id.progressbar)
    CircleProgressBar mProgressbar;

    @ViewById(R.id.toolbar)
    Toolbar mToolbar;

    @ViewById(R.id.tv_title)
    AppCompatTextView tv_title;

    @Override
    public void onAfterViews() {
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        showView(mProgressbar);
        tv_title.setText(getIntent().getStringExtra(AppDelegate.TOOLBAR_TITLE));
        // WebView加载web资源
        mWebView.loadUrl(getIntent().getStringExtra(AppDelegate.URL));
        // 设置网页用WebView打开
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                //返回值是true的时候WebView打开，为false调用系统浏览器或第三方浏览器
                return true;
            }
        });

        // 判断页面加载过程
        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    // 网页加载完成
                    mProgressbar.setVisibility(View.GONE);
                }
            }
        });

        WebSettings settings = mWebView.getSettings();
        // 启用支持javascript(支持缩放必备)
        settings.setJavaScriptEnabled(true);
        // 设定支持缩放
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);
        // 不显示webview缩放按钮（Android 3.0）
        settings.setDisplayZoomControls(false);
        // 设定支持viewport（双击缩放）
        settings.setUseWideViewPort(true);
        // 设定第一次进入页面显示整页web视图
        settings.setLoadWithOverviewMode(true);
        // 缓存的使用-优先使用缓存
        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        // 设置网页的排列算法，SINGLE_COLUMN 单列显示
        // settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);

    }

    // 改写物理按键——返回的逻辑
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && mWebView.canGoBack()) {
            mWebView.goBack();// 返回上一页面
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onPageStart(TAG);
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd(TAG);
        MobclickAgent.onPause(this);
    }

}
