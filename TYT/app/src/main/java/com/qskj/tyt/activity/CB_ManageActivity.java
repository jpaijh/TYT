package com.qskj.tyt.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
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
import com.qskj.tyt.fragment.CB_ManageListFragment_;
import com.umeng.analytics.MobclickAgent;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.HashMap;
import java.util.Map;

/**
 * 管理界面：进仓单管理（经营单位，货代），入库管理（仓库）,报关行（报关单管理，清单管理）
 */
@EActivity(R.layout.tablayout_viewpager_toolbar_with_progressbar)
public class CB_ManageActivity extends BaseActivity {

    private RequestQueue mRequestQueue;
    private String toolbarTitle;
    private int totalLeft;
    private int totalMiddle;
    private int totalRight;
    private String API_TAB_LEFT;
    private String API_TAB_MIDDLE;
    private String API_TAB_RIGHT;
    private SharedPreferences userInfoSp;
    private int accountNature;

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

    public void initToolbar() {
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        toolbarTitle = getIntent().getStringExtra(AppDelegate.TOOLBAR_TITLE);
        tv_title.setText(toolbarTitle);
    }

    private void initArguments() {
        userInfoSp = MyApplication_.getInstance().getUserInfoSp();
        String accountId = userInfoSp.getString(AppDelegate.ACCOUNT_ID, "");
        accountNature = userInfoSp.getInt(AppDelegate.ACCOUNT_NATURE, -1);
        String baseAPI = "/api/Orders/Storage/FindStorage?accountId=" + accountId + "&loginAccountNature=" + accountNature;

        if (accountNature == AppDelegate.ACCOUNT_NATURE_MANAGEMENT_UNIT || accountNature == AppDelegate.ACCOUNT_NATURE_FORWARDER) { // 经营单位和货代
            // 未完成 storageStatusLis= 1~6 表示 已报关之前的状态
            API_TAB_LEFT = baseAPI + "&storageStatusList=1&storageStatusList=2&storageStatusList=3&storageStatusList=4&storageStatusList=5&storageStatusList=6";
            // 已完成 storageStatusList=7
            API_TAB_RIGHT = baseAPI + "&storageStatusList=7";
        } else if (accountNature == AppDelegate.ACCOUNT_NATURE_WAREHOUSE) { // 仓库
            // 待收货
            API_TAB_LEFT = baseAPI + "&StorageStatusList=4";
            // 已收货
            API_TAB_RIGHT = baseAPI + "&StorageStatusList=5&StorageStatusList=6&StorageStatusList=7";
        } else if (accountNature == AppDelegate.ACCOUNT_NATURE_DECLARE) { // 报关行
            if (toolbarTitle.equals(getStrings(R.string.title_activity_listmanage))) { // 清单管理
                // 待放行 0,11,13
                API_TAB_LEFT = baseAPI + "&customsAreaEntryStatusList=0&customsAreaEntryStatusList=11&customsAreaEntryStatusList=13";
                // 查验 14,15
                API_TAB_MIDDLE = baseAPI + "&customsAreaEntryStatusList=14&customsAreaEntryStatusList=15";
                // 已放行 12
                API_TAB_RIGHT = baseAPI + "&customsAreaEntryStatusList=12";
            } else if (toolbarTitle.equals(getStrings(R.string.title_activity_declaremanage))) {// 报关单管理
                // 未报关  customsDeclarationFlag=false
                API_TAB_LEFT = "/api/Orders/Order/FindOrderReportPagedList?accountId=" + accountId + "&loginAccountNature=" + accountNature
                        + "&customsDeclarationFlag=false";
                // 已报关 customsDeclarationFlag=true
                API_TAB_RIGHT = "/api/Orders/Order/FindOrderReportPagedList?accountId=" + accountId + "&loginAccountNature=" + accountNature
                        + "&customsDeclarationFlag=true";
            }

        }

    }

    @Override
    public void onBackgrounds() {
        // 请求获取 管理Tab页显示的条目数
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

                        if (toolbarTitle.equals(getStrings(R.string.title_activity_listmanage))) { // 清单管理 多一个tab页
                            String urlTabMiddle = MyAPI.getBaseUrl() + API_TAB_MIDDLE;
                            JsonObjectRequest middleTotalRequest = new JsonObjectRequest(Request.Method.GET, urlTabMiddle, new Response.Listener<org.json.JSONObject>() {
                                @Override
                                public void onResponse(org.json.JSONObject response) {
                                    JSONObject jsonObject = JSON.parseObject(response.toString());
                                    totalMiddle = jsonObject.getIntValue("total");
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

                            middleTotalRequest.setTag(this);
                            mRequestQueue.add(middleTotalRequest);
                        } else {
                            initTabLayout();
                        }

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
            CB_ManageListFragment_ leftFragment = new CB_ManageListFragment_();
            Bundle leftBundle = new Bundle();
            leftBundle.putString(AppDelegate.TOOLBAR_TITLE, toolbarTitle);
            leftBundle.putString(AppDelegate.REQUEST_API, API_TAB_LEFT);
            leftFragment.setArguments(leftBundle);
            // rightFragment
            CB_ManageListFragment_ rightFragment = new CB_ManageListFragment_();
            Bundle rightBundle = new Bundle();
            rightBundle.putString(AppDelegate.TOOLBAR_TITLE, toolbarTitle);
            rightBundle.putString(AppDelegate.REQUEST_API, API_TAB_RIGHT);
            rightFragment.setArguments(rightBundle);

            if (accountNature == AppDelegate.ACCOUNT_NATURE_MANAGEMENT_UNIT || accountNature == AppDelegate.ACCOUNT_NATURE_FORWARDER) { // 经营单位，货代 （进仓单管理）
                mAdapter.addFragment(leftFragment, "未完成（" + totalLeft + "）");
                mAdapter.addFragment(rightFragment, "已完成（" + totalRight + "）");
            } else if (accountNature == AppDelegate.ACCOUNT_NATURE_WAREHOUSE) { // 仓库 （入库管理）
                mAdapter.addFragment(leftFragment, "待收货（" + totalLeft + "）");
                mAdapter.addFragment(rightFragment, "已收货（" + totalRight + "）");
            } else if (accountNature == AppDelegate.ACCOUNT_NATURE_DECLARE) { // 报关行
                if (toolbarTitle.equals(getStrings(R.string.title_activity_declaremanage))) { // 报关单管理
                    mAdapter.addFragment(leftFragment, "未报关（" + totalLeft + "）");
                    mAdapter.addFragment(rightFragment, "已报关（" + totalRight + "）");
                } else if (toolbarTitle.equals(getStrings(R.string.title_activity_listmanage))) { // 清单管理
                    CB_ManageListFragment_ middleFragment = new CB_ManageListFragment_();
                    Bundle middleBundle = new Bundle();
                    middleBundle.putString(AppDelegate.TOOLBAR_TITLE, toolbarTitle);
                    middleBundle.putString(AppDelegate.REQUEST_API, API_TAB_MIDDLE);
                    middleFragment.setArguments(middleBundle);

                    mAdapter.addFragment(leftFragment, "待放行（" + totalLeft + "）");
                    mAdapter.addFragment(middleFragment, "查验（" + totalMiddle + "）");
                    mAdapter.addFragment(rightFragment, "已放行（" + totalRight + "）");

                    // 系统默认为1，意思是当前fragment左右两侧，就会被保存1页不销毁，多余超过的销毁掉,那么2就是表示保留2页
                    mViewPager.setOffscreenPageLimit(2);
                }
            }

            mViewPager.setAdapter(mAdapter);
            mTabLayout.setupWithViewPager(mViewPager);
        }

        hideView(mProgressbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.action_search:
                // TODO 搜索这里要改，每个现在都是不一样的，尽量弄成一样的
                CB_ManagesSearchActivity_.intent(this).start();
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
