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
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

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
import com.qskj.tyt.entity.T_NoticeForeignExchangeEntity;
import com.qskj.tyt.utils.StringUtil;
import com.qskj.tyt.view.AlwaysMarqueeTextView;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 通知收汇 列表界面
 */
@EActivity(R.layout.swiperefreshlayout_recyclerview_toolbar_tvnodata)
public class T_NoticeForeignExchangeActivity extends BaseActivity {

    private static final String TAG = "T_NoticeForeignExchangeActivity";

    private List<T_NoticeForeignExchangeEntity.RowsEntity> rowsEntityList;
    private MyAdapter mAdapter;
    private int lastVisibleItem;
    private int totalPage;
    private int pageIndex = 1;
    private boolean isLoading;
    private boolean isFirstLoading;
    private LinearLayoutManager mLinearLayoutManager;
    private RequestQueue mRequestQueue;
    private LocalBroadcastManager mBroadcastManager;
    private BroadcastReceiver mReceiver;
    private SharedPreferences userInfoSp;

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

    @Override
    public void onAfterViews() {
        initToolbar();
        mRequestQueue = Volley.newRequestQueue(this);
        userInfoSp = MyApplication_.getInstance().getUserInfoSp();
        initRecyclerView();
        initSwipeRefreshLayout();
        initBroadcastReceiver();
    }

    private void initToolbar() {
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        tv_title.setText(R.string.title_activity_notice_foreign_exchange);
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

    @Background
    public void onBackgrounds() {
        final String url = MyAPI.getBaseUrl() + "/api/Funds/BankSlipNotice/FindBankSlipNotice?orderBy=10&pageSize=10&pageIndex=" + pageIndex
                + "&accountId=" + userInfoSp.getString(AppDelegate.ACCOUNT_ID, "");
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, new Response.Listener<org.json.JSONObject>() {
            @Override
            public void onResponse(org.json.JSONObject response) {
                LogUtils.d("\n通知收汇-URL:" + url + "\n通知收汇-RESPONSE:" + response.toString());

                JSONObject jsonObject = JSON.parseObject(response.toString());
                int total = jsonObject.getIntValue("total");
                totalPage = jsonObject.getIntValue("totalPage");

                if (total == 0) {
                    showView(tv_no_data);
                } else {
                    hideView(tv_no_data);
                }

                if (total != 0 && pageIndex == 1) {
                    JSONArray rows = jsonObject.getJSONArray("rows");
                    rowsEntityList = JSON.parseArray(rows.toString(), T_NoticeForeignExchangeEntity.RowsEntity.class);
                    mAdapter = new MyAdapter(rowsEntityList);
                    mRecyclerView.setAdapter(mAdapter);
                    if (!isFirstLoading)
                        createRefreshCompleteSnackbar(mRecyclerView);
                } else if (pageIndex <= totalPage) {
                    JSONArray rows = jsonObject.getJSONArray("rows");
                    rowsEntityList.addAll(lastVisibleItem + 1, JSON.parseArray(rows.toString(), T_NoticeForeignExchangeEntity.RowsEntity.class));
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

        jsonObjectRequest.setTag(this);
        mRequestQueue.add(jsonObjectRequest);
    }

    private class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

        private List<T_NoticeForeignExchangeEntity.RowsEntity> list;

        public MyAdapter(List<T_NoticeForeignExchangeEntity.RowsEntity> list) {
            this.list = list;
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_four_field_cardview_list, viewGroup, false);
            return new ViewHolder(view);
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            public LinearLayout ll_content;
            public AlwaysMarqueeTextView tv_notice_foreign_exchang_amount; // 金额
            public AlwaysMarqueeTextView tv_foreignName; // 外商
            public AppCompatTextView tv_notice_foreign_exchang_status; // 状态
            public AppCompatTextView tv_notice_foreign_exchang_createDate; // 制作日期

            public ViewHolder(final View itemView) {
                super(itemView);
                tv_notice_foreign_exchang_amount = (AlwaysMarqueeTextView) itemView.findViewById(R.id.tv_top_left);
                tv_foreignName = (AlwaysMarqueeTextView) itemView.findViewById(R.id.tv_down_left);
                tv_notice_foreign_exchang_status = (AppCompatTextView) itemView.findViewById(R.id.tv_top_right);
                tv_notice_foreign_exchang_createDate = (AppCompatTextView) itemView.findViewById(R.id.tv_down_right);
                ll_content = (LinearLayout) itemView.findViewById(R.id.ll_content);
                ll_content.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        T_NoticeForeignExchangeEntity.RowsEntity rowsEntity = list.get(getLayoutPosition());
                        T_NoticeForeignExchangeDetailsActivity_.intent(T_NoticeForeignExchangeActivity.this)
                                .extra(AppDelegate.ROWS_ENTITY, rowsEntity).start();
                    }
                });
            }
        }

        @Override
        public void onBindViewHolder(ViewHolder viewHolder, int position) {
            T_NoticeForeignExchangeEntity.RowsEntity entity = list.get(position);

            viewHolder.tv_notice_foreign_exchang_amount.setText(StringUtil.numberFormat(entity.getAmount()));
            viewHolder.tv_foreignName.setText(entity.getForeignName());
            viewHolder.tv_notice_foreign_exchang_createDate.setText(StringUtil.dateRemoveT(entity.getCreateDate()));

            // <!--审批状态 新制 = 0 审批汇总=1，未申请审批=-1，审批同意=2，审批不同意=-2，已支付=3，已入账=4，待受理= -10 已退回=-11，结算撤回=-12 -->
            int status = entity.getStatus();
            switch (status) {
                case 0: // 新制
                    viewHolder.tv_notice_foreign_exchang_status.setText(getStrings(R.string.brand_new));
                    viewHolder.tv_notice_foreign_exchang_status.setTextColor(getColors(R.color.red_500));
                    break;
                case 1: // 审批汇总
                    viewHolder.tv_notice_foreign_exchang_status.setText(getStrings(R.string.approve_collect));
                    viewHolder.tv_notice_foreign_exchang_status.setTextColor(getColors(R.color.red_500));
                    break;
                case -1: // 未申请审批
                    viewHolder.tv_notice_foreign_exchang_status.setText(getStrings(R.string.no_applly_approve));
                    viewHolder.tv_notice_foreign_exchang_status.setTextColor(getColors(R.color.red_500));
                    break;
                case 2: // 审批同意
                    viewHolder.tv_notice_foreign_exchang_status.setText(getStrings(R.string.pass_approve));
                    viewHolder.tv_notice_foreign_exchang_status.setTextColor(getColors(R.color.green_300));
                    break;
                case -2: // 审批不同意
                    viewHolder.tv_notice_foreign_exchang_status.setText(getStrings(R.string.nopass_approve));
                    viewHolder.tv_notice_foreign_exchang_status.setTextColor(getColors(R.color.red));
                    break;
                case 3: // 已支付
                    viewHolder.tv_notice_foreign_exchang_status.setText(getStrings(R.string.prepaid));
                    viewHolder.tv_notice_foreign_exchang_status.setTextColor(getColors(R.color.green_300));
                    break;
                case 4: // 已入账
                    viewHolder.tv_notice_foreign_exchang_status.setText(getStrings(R.string.postinged));
                    viewHolder.tv_notice_foreign_exchang_status.setTextColor(getColors(R.color.green_300));
                    break;
                case -10: // 待受理
                    viewHolder.tv_notice_foreign_exchang_status.setText(getStrings(R.string.wait_accepted));
                    viewHolder.tv_notice_foreign_exchang_status.setTextColor(getColors(R.color.color_theme));
                    break;
                case -11: // 已退回
                    viewHolder.tv_notice_foreign_exchang_status.setText(getStrings(R.string.returned));
                    viewHolder.tv_notice_foreign_exchang_status.setTextColor(getColors(R.color.red_500));
                    break;
                case -12: // 结算撤回
                    viewHolder.tv_notice_foreign_exchang_status.setText(getStrings(R.string.settle_revoke));
                    viewHolder.tv_notice_foreign_exchang_status.setTextColor(getColors(R.color.red_500));
                    break;
            }

        }

    }

    private void initBroadcastReceiver() {
        // 注册广播接收者
        mBroadcastManager = LocalBroadcastManager.getInstance(this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(AppDelegate.ACTION_T_REFRESH_NOTICE_FOREIGN_EXCHANGE_LIST);
        mReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                reLoad();
            }
        };
        mBroadcastManager.registerReceiver(mReceiver, intentFilter);
    }

    /**
     * 重新请求加载第一页数据
     */
    private void reLoad() {
        mSwipeRefreshLayout.setRefreshing(true);
        isLoading = true;
        pageIndex = 1;
        isFirstLoading = false;
        onBackgrounds();
    }

    @Override
    protected void onDestroy() {
        mRequestQueue.cancelAll(this);
        mBroadcastManager.unregisterReceiver(mReceiver);
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_more, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.action_more:
                T_NewNoticeForeignExchangeActivity_.intent(this).start();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
