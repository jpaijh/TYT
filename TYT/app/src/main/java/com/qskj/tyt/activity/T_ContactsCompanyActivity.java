package com.qskj.tyt.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.apkfuns.logutils.LogUtils;
import com.lsjwzh.widget.materialloadingprogressbar.CircleProgressBar;
import com.qskj.tyt.AppDelegate;
import com.qskj.tyt.MyAPI;
import com.qskj.tyt.MyApplication_;
import com.qskj.tyt.R;
import com.qskj.tyt.adapter.TableLayoutAdapter;
import com.qskj.tyt.fragment.T_ContactsCompanyListFragment_;
import com.umeng.analytics.MobclickAgent;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.HashMap;
import java.util.Map;

/**
 * 我的往来单位
 */
@EActivity(R.layout.tablayout_viewpager_toolbar_with_progressbar)
public class T_ContactsCompanyActivity extends BaseActivity {

    private RequestQueue mRequestQueue;
    private int totalLeft;
    private int totalRight;
    private String API_TAB_LEFT;
    private String API_TAB_RIGHT;
    private SharedPreferences userInfoSp;

    @ViewById(R.id.tabLayout)
    TabLayout mTabLayout;

    @ViewById(R.id.viewpager)
    ViewPager mViewPager;

    @ViewById(R.id.toolbar)
    Toolbar mToolbar;

    @ViewById(R.id.tv_title)
    AppCompatTextView tv_title;

    @ViewById(R.id.progressbar)
    CircleProgressBar mProgressbar;

    @Override
    public void onAfterViews() {
        mRequestQueue = Volley.newRequestQueue(this);
        showView(mProgressbar);
        initToolbar();
        initArguments();
        onBackgrounds();
    }

    private void initToolbar() {
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        tv_title.setText(R.string.title_activity_contacts_company);
    }

    private void initArguments() {
        userInfoSp = MyApplication_.getInstance().getUserInfoSp();
        String accountId = userInfoSp.getString(AppDelegate.ACCOUNT_ID, "");

        String baseAPI = "/api/PlatformAccounts/Company/FindPagedList?accountId=" + accountId;

        // 我的国内开票单位 type=0
        API_TAB_LEFT = baseAPI + "&type=0";
        // 我的外商 type=1
        API_TAB_RIGHT = baseAPI + "&type=1";

    }

    @Override
    public void onBackgrounds() {
        // 请求获取Tab页显示的条目数
        String urlTabLeft = MyAPI.getBaseUrl() + API_TAB_LEFT;
        JsonObjectRequest leftTotalRequest = new JsonObjectRequest(Request.Method.GET, urlTabLeft, new Response.Listener<org.json.JSONObject>() {
            @Override
            public void onResponse(org.json.JSONObject response) {
                JSONObject jsonObject = JSON.parseObject(response.toString());
                totalLeft = jsonObject.getIntValue("total");

                String urlTabRight = MyAPI.getBaseUrl() + API_TAB_RIGHT;
                JsonObjectRequest rightTotalRequest = new JsonObjectRequest(Request.Method.GET, urlTabRight, new Response.Listener<org.json.JSONObject>() {
                    @Override
                    public void onResponse(org.json.JSONObject response) {
                        JSONObject jsonObject = JSON.parseObject(response.toString());
                        totalRight = jsonObject.getIntValue("total");
                        initTabLayout();

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        LogUtils.e("请求错误:" + error.toString());
                        initTabLayout();
                    }
                }) {
                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        Map<String, String> map = new HashMap<>();
                        map.put(AppDelegate.QS_LOGIN, userInfoSp.getString(AppDelegate.LOGIN_NAME, ""));
                        return map;
                    }
                };

                rightTotalRequest.setTag(this);
                mRequestQueue.add(rightTotalRequest);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                LogUtils.e("请求错误:" + error.toString());
                initTabLayout();
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put(AppDelegate.QS_LOGIN, userInfoSp.getString(AppDelegate.LOGIN_NAME, ""));
                return map;
            }
        };

        leftTotalRequest.setTag(this);
        mRequestQueue.add(leftTotalRequest);
    }

    public void initTabLayout() {
        if (mViewPager != null) {
            TableLayoutAdapter mAdapter = new TableLayoutAdapter(getSupportFragmentManager());
            // leftFragment
            T_ContactsCompanyListFragment_ leftFragment = new T_ContactsCompanyListFragment_();
            Bundle leftBundle = new Bundle();
            leftBundle.putString(AppDelegate.REQUEST_API, API_TAB_LEFT);
            leftFragment.setArguments(leftBundle);
            // rightFragment
            T_ContactsCompanyListFragment_ rightFragment = new T_ContactsCompanyListFragment_();
            Bundle rightBundle = new Bundle();
            rightBundle.putString(AppDelegate.REQUEST_API, API_TAB_RIGHT);
            rightFragment.setArguments(rightBundle);

            mAdapter.addFragment(leftFragment, "国内开票单位（" + totalLeft + "）");
            mAdapter.addFragment(rightFragment, "我的外商（" + totalRight + "）");

            mViewPager.setAdapter(mAdapter);
            mTabLayout.setupWithViewPager(mViewPager);
        }

        hideView(mProgressbar);
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
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    @Override
    protected void onDestroy() {
        mRequestQueue.cancelAll(this);
        super.onDestroy();
    }

}
