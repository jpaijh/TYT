package com.qskj.tyt.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.ListPopupWindow;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.qskj.tyt.AppDelegate;
import com.qskj.tyt.MyApplication_;
import com.qskj.tyt.R;
import com.qskj.tyt.adapter.ListpopAdapter;
import com.qskj.tyt.utils.DeviceUtil;
import com.qskj.tyt.view.AppCompatAutoCompleteClearTextView;
import com.umeng.analytics.MobclickAgent;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Set;

/**
 * 天易通-商品搜素 界面
 */
@EActivity(R.layout.cb_activity_commodity_search)
public class CB_CommoditySearchActivity extends BaseActivity {

    private static final String TAG = "CB_CommoditySearchActivity";

    private ListPopupWindow mListPop;
    private String[] status = new String[]{"全部", "新制", "已审核", "待提交", "已提交", "已备案"};

    @ViewById(R.id.acct_status)
    AppCompatEditText acct_status; // 商品状态

    @ViewById(R.id.acct_keyword)
    AppCompatAutoCompleteClearTextView acct_keyword; // 关键字

    @ViewById(R.id.tv_title)
    AppCompatTextView tv_title;

    @ViewById(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    public void onAfterViews() {
        initToolbar();

        // 设置关键字搜索的历史记录数据
        SharedPreferences searchHistorySp = MyApplication_.getInstance().getSearchHistorySp();
        Set<String> KeyHistory = searchHistorySp.getStringSet(AppDelegate.HISTORY_COMMODITY_KEY_SEARCH, null);
        if (KeyHistory != null) {
            ArrayList<String> arrayList = new ArrayList<>(KeyHistory);
            acct_keyword.setAdapter(new ListpopAdapter(this, R.layout.cb_item_listpop_with_delete, R.id.tv_text, arrayList,
                    searchHistorySp.edit(), AppDelegate.HISTORY_COMMODITY_KEY_SEARCH));
        }

        acct_keyword.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DeviceUtil.hideSoft(getApplicationContext(), acct_keyword);
            }
        });

        mListPop = new ListPopupWindow(this);
        acct_status.setText("全部");
        // 目的是无法输入,因为只能选择状态
        acct_status.setInputType(InputType.TYPE_NULL);
        acct_status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListPop.setAdapter(new ArrayAdapter<>(CB_CommoditySearchActivity.this, R.layout.cb_item_listpop_search, status));
                mListPop.setAnchorView(acct_status);
                mListPop.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        acct_status.setText(status[position]);
                        mListPop.dismiss();
                    }
                });
                mListPop.show();
            }
        });

    }

    private void initToolbar() {
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        tv_title.setText("商品搜索");
    }

    @Click(R.id.btn_search)
    void search() {
        try {
            // 获取关键字
            String keyword = acct_keyword.getText().toString().trim();
            String keywordURLEncoder = URLEncoder.encode(keyword, "utf-8");
            // 获取商品状态
            String statusStr = acct_status.getText().toString().trim();
            // 状态：新制 0 已审核 1 待提交 2 已提交 3 已备案 4
            int status = -1;
            switch (statusStr) {
                case "全部":
                    status = -1;
                    break;
                case "新制":
                    status = 0;
                    break;
                case "已审核":
                    status = 1;
                    break;
                case "待提交":
                    status = 2;
                    break;
                case "已提交":
                    status = 3;
                    break;
                case "已备案":
                    status = 4;
                    break;
            }

            Intent intent = new Intent(AppDelegate.ACTION_CB_SEARCH_COMMODITY);
            // &keyword=&commoditystatus=&
            if (status == -1) { // 搜索全部的话 就不要 commoditystatus 这个参数
                String ACTION_TYT_SEARCH_COMMODITY = "&keyword=" + keywordURLEncoder;
                intent.putExtra(AppDelegate.ACTION_CB_SEARCH_COMMODITY, ACTION_TYT_SEARCH_COMMODITY);
            } else {
                String ACTION_TYT_SEARCH_COMMODITY = "&keyword=" + keywordURLEncoder + "&commoditystatus=" + status;
                intent.putExtra(AppDelegate.ACTION_CB_SEARCH_COMMODITY, ACTION_TYT_SEARCH_COMMODITY);
            }
            if (!TextUtils.isEmpty(keyword))
                intent.putExtra(AppDelegate.KEYWORD, keyword);

            // 发送广播，将搜索条件和关键字传递过去，然后请求数据
            LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
            finish();

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_reset, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.action_reset:
                clearDate();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void clearDate() {
        acct_keyword.setText("");
        acct_status.setText("全部");
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
