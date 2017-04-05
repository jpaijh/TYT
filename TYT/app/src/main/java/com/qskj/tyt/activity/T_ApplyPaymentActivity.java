package com.qskj.tyt.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
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
import com.qskj.tyt.entity.T_ApplyPaymentActivityEntity;
import com.qskj.tyt.utils.StringUtil;
import com.qskj.tyt.view.AlwaysMarqueeTextView;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 申请提款
 */
@EActivity(R.layout.swiperefreshlayout_recyclerview_toolbar_tvnodata)
public class T_ApplyPaymentActivity extends BaseActivity {

    private static final String TAG = "T_ApplyPaymentActivity";

    private List<T_ApplyPaymentActivityEntity.RowsEntity> rowsEntityList; // 列表数据集合
    private MyAdapter mAdapter; // 数据适配器
    private int lastVisibleItem; // RecyclerView 最后显示的条目位置
    private int totalPage; // 分页请求总页数
    private int pageIndex = 1; // 当前请求页
    private boolean isLoading; // 是否正在加载中（是否显示SwipeRefreshLayout的加载视图）
    private boolean isFirstLoading; // 是否是第一次加载
    private LinearLayoutManager mLinearLayoutManager; // 线性布局管理器
    private RequestQueue mRequestQueue; // 请求队列
    private LocalBroadcastManager mBroadcastManager;
    private BroadcastReceiver mReceiver;

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
        mRequestQueue = Volley.newRequestQueue(this);  // 实例化请求队列
        initRecyclerView();
        initSwipeRefreshLayout();
        initBroadcastReceiver();
    }

    /**
     * 初始化Toolbar
     */
    private void initToolbar() {
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        tv_title.setText(R.string.apply_payment);
    }

    /**
     * 初始化RecyclerView
     */
    private void initRecyclerView() {
        /**
         * 给RecyclerView设置线性布局管理器，LinearLayoutManager默认垂直方向
         */
        mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        /**
         * 若列表都是长一样的，加这句提高效率
         */
        mRecyclerView.setHasFixedSize(true);
        /**
         * 设置滚动监听器
         */
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                // 滑动到最后一个条目
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

    /**
     * 初始化SwipeRefreshLayout
     */
    private void initSwipeRefreshLayout() {
        /**
         * 进入界面SwipeRefreshLayout第一次加载
         */
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

        /**
         * 设置加载时SwipeRefreshLayout Progress的颜色
         */
        mSwipeRefreshLayout.setColorSchemeResources(R.color.color_progress_1, R.color.color_progress_2);

        /**
         * 设置下拉刷新监听器
         */
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

    @Override
    public void onBackgrounds() {
        //请求获取 申请提款 http://192.168.64.222:19199/api/Funds/PaymentApply/FindPaymentApply
        final String url = MyAPI.getBaseUrl() + "/api/Funds/PaymentApply/FindPaymentApply?pageSize=10&pageIndex=" + pageIndex;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, new Response.Listener<org.json.JSONObject>() {
            @Override
            public void onResponse(org.json.JSONObject response) {
                LogUtils.d("\n申请提款-URL:" + url + "\n申请提款-RESPONSE:" + response.toString());

                JSONObject jsonObject = JSON.parseObject(response.toString());
                int total = jsonObject.getIntValue("total");
                totalPage = jsonObject.getIntValue("totalPage");

                if (total == 0) {
                    showView(tv_no_data);
                } else {
                    hideView(tv_no_data);
                }

                if (total != 0 && pageIndex == 1) { // 加载第一页数据
                    JSONArray rows = jsonObject.getJSONArray("rows");
                    rowsEntityList = JSON.parseArray(rows.toString(), T_ApplyPaymentActivityEntity.RowsEntity.class);
                    mAdapter = new MyAdapter(rowsEntityList);
                    mRecyclerView.setAdapter(mAdapter);
                    if (!isFirstLoading)
                        createRefreshCompleteSnackbar(mRecyclerView);
                } else if (pageIndex <= totalPage) { // 加载其他页数据
                    JSONArray rows = jsonObject.getJSONArray("rows");
                    rowsEntityList.addAll(lastVisibleItem + 1, JSON.parseArray(rows.toString(), T_ApplyPaymentActivityEntity.RowsEntity.class));
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
                map.put(AppDelegate.QS_LOGIN, MyApplication_.getInstance().getUserInfoSp().getString(AppDelegate.LOGIN_NAME, ""));
                return map;
            }
        };

        jsonObjectRequest.setTag(this);
        mRequestQueue.add(jsonObjectRequest);
    }

    /**
     * 数据适配器
     */
    private class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

        private List<T_ApplyPaymentActivityEntity.RowsEntity> list;

        public MyAdapter(List<T_ApplyPaymentActivityEntity.RowsEntity> list) {
            this.list = list;
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.t_item_apply_payment_list, viewGroup, false);
            return new ViewHolder(view);
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            public RelativeLayout rl_content;
            public AlwaysMarqueeTextView tv_apply_code; // 提款单号
            public AlwaysMarqueeTextView tv_saleOrders; // 外销发票号
            public AppCompatTextView tv_actualPaymentAmount; // 支付金额
            public AppCompatTextView tv_applyPaymentAmount; // 结算金额
            public AppCompatTextView tv_status;  // 状态
            public AppCompatTextView tv_applyDate; // 声请日期

            public ViewHolder(final View itemView) {
                super(itemView);
                tv_apply_code = (AlwaysMarqueeTextView) itemView.findViewById(R.id.tv_apply_code);
                tv_saleOrders = (AlwaysMarqueeTextView) itemView.findViewById(R.id.tv_saleOrders);
                tv_actualPaymentAmount = (AppCompatTextView) itemView.findViewById(R.id.tv_actualPaymentAmount);
                tv_applyPaymentAmount = (AppCompatTextView) itemView.findViewById(R.id.tv_applyPaymentAmount);
                tv_status = (AppCompatTextView) itemView.findViewById(R.id.tv_status);
                tv_applyDate = (AppCompatTextView) itemView.findViewById(R.id.tv_applyDate);
                rl_content = (RelativeLayout) itemView.findViewById(R.id.rl_content);
                rl_content.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        T_ApplyPaymentActivityEntity.RowsEntity rowsEntity = list.get(getLayoutPosition());
                        ArrayList<String> datas = new ArrayList<>();
                        datas.add(rowsEntity.getId());
                        datas.add(rowsEntity.getDraweeBankName());
                        datas.add(rowsEntity.getDraweeBankAccount());
                        T_ApplyPaymentDetailsActivity_.intent(T_ApplyPaymentActivity.this).stringArrayListExtra("datas", datas).start();
                    }
                });
            }
        }

        @Override
        public void onBindViewHolder(ViewHolder viewHolder, int position) {
            T_ApplyPaymentActivityEntity.RowsEntity entity = list.get(position);

            viewHolder.tv_apply_code.setText(entity.getCode());
            viewHolder.tv_apply_code.setTextColor(getColors(R.color.color_theme));
            viewHolder.tv_saleOrders.setText(entity.getSaleOrders());
            viewHolder.tv_applyPaymentAmount.setText(StringUtil.numberFormat(entity.getApplyPaymentAmount()));
            viewHolder.tv_actualPaymentAmount.setText(StringUtil.numberFormat(entity.getActualPaymentAmount()));
            viewHolder.tv_applyDate.setText(StringUtil.dateRemoveT(entity.getApplyDate() != null ? entity.getApplyDate() : ""));

            // <!--审批状态 新制 = 0 审批汇总=1，未申请审批=-1，审批同意=2，审批不同意=-2，已支付=3，已入账=4，待受理= -10 已退回=-11，结算撤回=-12 -->
            int status = entity.getStatus();
            switch (status) {
                case 0: // 新制
                    viewHolder.tv_status.setText(getStrings(R.string.brand_new));
                    viewHolder.tv_status.setTextColor(getColors(R.color.red_500));
                    break;
                case 1: // 审批中
                    viewHolder.tv_status.setText(getStrings(R.string.approve_collect));
                    viewHolder.tv_status.setTextColor(getColors(R.color.color_theme));
                    break;
                case -1: // 未申请审批
                    viewHolder.tv_status.setText(getStrings(R.string.no_applly_approve));
                    viewHolder.tv_status.setTextColor(getColors(R.color.red_500));
                    break;
                case 2: // 审批同意
                    viewHolder.tv_status.setText(getStrings(R.string.pass_approve));
                    viewHolder.tv_status.setTextColor(getColors(R.color.green_300));
                    break;
                case -2: // 审批不同意
                    viewHolder.tv_status.setText(getStrings(R.string.nopass_approve));
                    viewHolder.tv_status.setTextColor(getColors(R.color.red_500));
                    break;
                case 3: // 已支付
                    viewHolder.tv_status.setText(getStrings(R.string.prepaid));
                    viewHolder.tv_status.setTextColor(getColors(R.color.green_300));
                    break;
                case 4: // 已入账
                    viewHolder.tv_status.setText(getStrings(R.string.postinged));
                    viewHolder.tv_status.setTextColor(getColors(R.color.green_300));
                    break;
                case -10: // 待受理
                    viewHolder.tv_status.setText(getStrings(R.string.wait_accepted));
                    viewHolder.tv_status.setTextColor(getColors(R.color.color_theme));
                    break;
                case -11: // 已退回
                    viewHolder.tv_status.setText(getStrings(R.string.returned));
                    viewHolder.tv_status.setTextColor(getColors(R.color.red_500));
                    break;
                case -12: // 结算撤回
                    viewHolder.tv_status.setText(getStrings(R.string.settle_revoke));
                    viewHolder.tv_status.setTextColor(getColors(R.color.red_500));
                    break;
            }

        }

    }

    /**
     * 创建OptionsMenu
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_search, menu);
        return true;
    }

    /**
     * OptionsMenu的Item的点击事件
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
//            case R.id.action_search:
//                ToastUtil.showToast(this, "搜索");
//                return true;
            case R.id.action_add:
                T_PaymentActivity_.intent(this).start();
                return true;
        }
        return super.onOptionsItemSelected(item);
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

    private void initBroadcastReceiver() {
        // 注册广播接收者
        mBroadcastManager = LocalBroadcastManager.getInstance(this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(AppDelegate.ACTION_T_REFRESH_APPLY_PAYMENT_LIST);
        mReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                reLoad();
            }
        };
        mBroadcastManager.registerReceiver(mReceiver, intentFilter);
    }

    @Override
    protected void onDestroy() {
        /**
         * 销毁界面时取消添加到请求队列的所有请求
         */
        mRequestQueue.cancelAll(this);
        mBroadcastManager.unregisterReceiver(mReceiver);
        super.onDestroy();
    }

}
