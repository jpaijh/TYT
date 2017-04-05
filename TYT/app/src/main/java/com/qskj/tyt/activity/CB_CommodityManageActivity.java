package com.qskj.tyt.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.apkfuns.logutils.LogUtils;
import com.qskj.tyt.AppDelegate;
import com.qskj.tyt.MyAPI;
import com.qskj.tyt.MyApplication_;
import com.qskj.tyt.R;
import com.qskj.tyt.entity.CB_CommodityManageEntity;
import com.qskj.tyt.view.AlwaysMarqueeTextView;
import com.umeng.analytics.MobclickAgent;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * 商品管理：商品列表
 */
@EActivity(R.layout.swiperefreshlayout_recyclerview_toolbar_tvnodata)
public class CB_CommodityManageActivity extends BaseActivity {

    private static final String TAG = "CB_CommodityManageActivity";

    private List<CB_CommodityManageEntity.RowsEntity> rowsEntityList;
    private MyAdapter mAdapter;
    private LinearLayoutManager mLinearLayoutManager;
    private int lastVisibleItem;
    private int totalPage;
    private int pageIndex = 1;
    private boolean isFirstLoading = false;
    private boolean isLoading;
    private LocalBroadcastManager mBroadcastManager;
    private BroadcastReceiver mReceiver;
    private RequestQueue mRequestQueue;
    private SharedPreferences userInfoSp;
    private String keyword;
    private String accountId;
    private String arguments = "";

    @ViewById(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    @ViewById(R.id.recyclerview)
    RecyclerView mRecyclerView;

    @ViewById(R.id.toolbar)
    Toolbar mToolbar;

    @ViewById(R.id.tv_title)
    AppCompatTextView tv_title;

    @ViewById(R.id.tv_no_data)
    AppCompatTextView tv_no_data;

    /**
     * 将搜索有记录的 关键字 存起来（商品搜索界面 关键字 模糊搜索的历史记录）
     *
     * @param keyword
     */
    private void saveKeyHistory(String keyword) {
        // 存入进行搜索过的关键字
        SharedPreferences searchHistorySp = MyApplication_.getInstance().getSearchHistorySp();
        Set<String> search_history = searchHistorySp.getStringSet(AppDelegate.HISTORY_COMMODITY_KEY_SEARCH, null);
        if (search_history == null) {
            Set<String> newSet = new TreeSet<>();
            newSet.add(keyword);
            searchHistorySp.edit().putStringSet(AppDelegate.HISTORY_COMMODITY_KEY_SEARCH, newSet).commit();
        } else {
            Set<String> sets = new TreeSet<>();
            String[] strings = search_history.toArray(new String[]{});
            for (int i = 0; i < strings.length; i++) {
                sets.add(strings[i]);
            }
            sets.add(keyword);
            searchHistorySp.edit().putStringSet(AppDelegate.HISTORY_COMMODITY_KEY_SEARCH, sets).commit();
        }
    }

    @Override
    public void onAfterViews() {
        initToolbar();
        mRequestQueue = Volley.newRequestQueue(this);
        userInfoSp = MyApplication_.getInstance().getUserInfoSp();
        accountId = userInfoSp.getString(AppDelegate.ACCOUNT_ID, "");
        initBroadcastReceiver();
        initSwipeRefreshLayout();
        initRecyclerView();
    }

    private void initToolbar() {
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        tv_title.setText(R.string.title_activity_commoditymanage_list);
    }

    private void initBroadcastReceiver() {
        // 注册广播接收者
        mBroadcastManager = LocalBroadcastManager.getInstance(this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(AppDelegate.ACTION_CB_SEARCH_COMMODITY);
        mReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                arguments = intent.getStringExtra(AppDelegate.ACTION_CB_SEARCH_COMMODITY);
                keyword = intent.getStringExtra(AppDelegate.KEYWORD);

                mSwipeRefreshLayout.setRefreshing(true);
                isLoading = true;
                saveCurrentTime(TAG);
                pageIndex = 1;
                isFirstLoading = true;
                onBackgrounds();
            }
        };
        mBroadcastManager.registerReceiver(mReceiver, intentFilter);
    }

    private void initSwipeRefreshLayout() {
        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(true);
                isLoading = true;
                saveCurrentTime(TAG);
                pageIndex = 1;
                isFirstLoading = true;
                onBackgrounds();
            }
        });

        mSwipeRefreshLayout.setColorSchemeResources(R.color.color_progress_1, R.color.color_progress_2);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                pageIndex = 1;
                isFirstLoading = false;
                onBackgrounds();
                createRefreshSnackbar(mRecyclerView, TAG);
            }
        });
    }

    private void initRecyclerView() {
        mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (mAdapter != null && newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == mAdapter.getItemCount() && !isLoading) {
                    mSwipeRefreshLayout.setRefreshing(true);
                    isLoading = true;

                    if (pageIndex < totalPage) {
                        createLoadingSnackbar(mRecyclerView);
                        pageIndex = pageIndex + 1;
                        onBackgrounds();
                    } else {
                        mSwipeRefreshLayout.setRefreshing(false);
                        isLoading = false;
                        createNoDateSnackbar(mRecyclerView);
                    }

                    super.onScrollStateChanged(recyclerView, newState);
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                lastVisibleItem = mLinearLayoutManager.findLastVisibleItemPosition();
                super.onScrolled(recyclerView, dx, dy);
            }

        });

    }

    @Override
    public void onBackgrounds() {
        // 请求获取 商品管理列表
        final String URL = MyAPI.getBaseUrl() + "/api/PlatformAccounts/CompanyCommodity/FindCommodityPagedList?pageSize=10&pageIndex=" + pageIndex + "&accountId=" + accountId + arguments;
        JsonObjectRequest commodityListRequest = new JsonObjectRequest(Request.Method.GET, URL, new Response.Listener<org.json.JSONObject>() {
            @Override
            public void onResponse(org.json.JSONObject response) {
                LogUtils.d("\n商品列表-URL:" + URL + "\n商品列表-RESPONSE:" + response.toString());

                JSONObject jsonObject = JSON.parseObject(response.toString());
                int total = jsonObject.getIntValue("total");
                totalPage = jsonObject.getIntValue("totalPage");

                if (keyword != null && !TextUtils.isEmpty(keyword) && total > 0) {
                    saveKeyHistory(keyword);
                }

                if (total == 0) {
                    hideView(mRecyclerView);
                    showView(tv_no_data);
                } else {
                    showView(mRecyclerView);
                    hideView(tv_no_data);
                }

                if (total != 0 && pageIndex == 1) {
                    JSONArray rows = jsonObject.getJSONArray("rows");
                    rowsEntityList = JSON.parseArray(rows.toString(), CB_CommodityManageEntity.RowsEntity.class);
                    mAdapter = new MyAdapter(rowsEntityList);
                    mRecyclerView.setAdapter(mAdapter);
                    if (!isFirstLoading)
                        createRefreshCompleteSnackbar(mRecyclerView);
                } else if (pageIndex <= totalPage) {
                    JSONArray rows = jsonObject.getJSONArray("rows");
                    rowsEntityList.addAll(lastVisibleItem + 1, JSON.parseArray(rows.toString(), CB_CommodityManageEntity.RowsEntity.class));
                    mAdapter.notifyDataSetChanged();
                    createLoadingCompleteSnackbar(mRecyclerView);
                }

                mSwipeRefreshLayout.setRefreshing(false);
                isLoading = false;
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                LogUtils.e("请求错误:" + error.toString());
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put(AppDelegate.QS_LOGIN, userInfoSp.getString(AppDelegate.LOGIN_NAME, ""));
                return map;
            }
        };

        commodityListRequest.setTag(this);
        mRequestQueue.add(commodityListRequest);
    }

    private class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

        private List<CB_CommodityManageEntity.RowsEntity> list;

        public MyAdapter(List<CB_CommodityManageEntity.RowsEntity> list) {
            this.list = list;
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cb_item_commoditymanage_list, viewGroup, false);
            return new ViewHolder(view);
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            public RelativeLayout rl_content;
            public AlwaysMarqueeTextView tv_commodityManagementGrid_commodityNameCn; // 中文品名
            public AppCompatTextView tv_commodityManagementGrid_hsCode; // HS编码
            public AppCompatTextView tv_commodityManagementGrid_statusView; // 状态
            public AppCompatTextView tv_commodityManagementGrid_customsRecordNo; // 商品备案号

            public ViewHolder(final View itemView) {
                super(itemView);
                tv_commodityManagementGrid_commodityNameCn = (AlwaysMarqueeTextView) itemView.findViewById(R.id.tv_commodityManagementGrid_commodityNameCn);
                tv_commodityManagementGrid_hsCode = (AppCompatTextView) itemView.findViewById(R.id.tv_commodityManagementGrid_hsCode);
                tv_commodityManagementGrid_statusView = (AppCompatTextView) itemView.findViewById(R.id.tv_commodityManagementGrid_statusView);
                tv_commodityManagementGrid_customsRecordNo = (AppCompatTextView) itemView.findViewById(R.id.tv_commodityManagementGrid_customsRecordNo);
                rl_content = (RelativeLayout) itemView.findViewById(R.id.rl_content);
                rl_content.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        CB_CommodityManageEntity.RowsEntity rowsEntity = list.get(getLayoutPosition());
                        CB_CommodityDetailsActivity_.intent(CB_CommodityManageActivity.this).extra(AppDelegate.COMMODITY_MANAGE_ROWS_ENTITY, rowsEntity).start();
                    }
                });
            }
        }

        @Override
        public void onBindViewHolder(ViewHolder viewHolder, int position) {
            CB_CommodityManageEntity.RowsEntity entity = list.get(position);

            viewHolder.tv_commodityManagementGrid_commodityNameCn.setText(entity.getCommodityNameCn());
            viewHolder.tv_commodityManagementGrid_hsCode.setText(entity.getHsCode());
            viewHolder.tv_commodityManagementGrid_customsRecordNo.setText(entity.getCustomsRecordNo());

            // 状态：新制 0 已审核 1 待提交 2 已提交 3 已备案 4
            String status = entity.getStatusView();
            viewHolder.tv_commodityManagementGrid_statusView.setText(status);
            switch (status) {
                case "新制":
                    viewHolder.tv_commodityManagementGrid_statusView.setTextColor(getColors(R.color.red_500));
                    break;
                case "已审核":
                    viewHolder.tv_commodityManagementGrid_statusView.setTextColor(getColors(R.color.deep_purple_500));
                    break;
                case "待提交":
                    viewHolder.tv_commodityManagementGrid_statusView.setTextColor(getColors(R.color.yellow_900));
                    break;
                case "已提交":
                    viewHolder.tv_commodityManagementGrid_statusView.setTextColor(getColors(R.color.blue_700));
                    break;
                case "已备案":
                    viewHolder.tv_commodityManagementGrid_statusView.setTextColor(getColors(R.color.green_300));
                    break;
            }

        }

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
                CB_CommoditySearchActivity_.intent(this).start();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDestroy() {
        mRequestQueue.cancelAll(this);
        mBroadcastManager.unregisterReceiver(mReceiver);
        super.onDestroy();
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
