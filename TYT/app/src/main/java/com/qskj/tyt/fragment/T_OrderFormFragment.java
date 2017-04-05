package com.qskj.tyt.fragment;

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
import android.text.TextUtils;
import android.view.LayoutInflater;
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
import com.qskj.tyt.activity.T_OrderFormDetailsActivity_;
import com.qskj.tyt.entity.T_OrderFormEntity;
import com.qskj.tyt.utils.StringUtil;
import com.qskj.tyt.view.AlwaysMarqueeTextView;
import com.umeng.analytics.MobclickAgent;


import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * 订单
 */
@EFragment(R.layout.swiperefreshlayout_recyclerview_tvnodata)
public class T_OrderFormFragment extends BaseFragment {

    private static final String TAG = "T_OrderFormFragment";

    private List<T_OrderFormEntity.RowsEntity> rowsEntityList;
    private MyOrderFormEntityAdapter mAdapter;
    private LinearLayoutManager mLinearLayoutManager;
    private int lastVisibleItem;
    private int totalPage;
    private int pageIndex = 1;
    private boolean isLoading;
    private boolean isFirstLoading;
    private LocalBroadcastManager mBroadcastManager;
    private BroadcastReceiver mReceiver;
    private RequestQueue mRequestQueue;
    private SharedPreferences userInfoSp;
    private String arguments = "";
    private String keyword;

    @ViewById(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    @ViewById(R.id.recyclerview)
    RecyclerView mRecyclerView;

    @ViewById(R.id.tv_no_data)
    AppCompatTextView tv_no_data;

    @Override
    public void onAfterViews() {
        mRequestQueue = Volley.newRequestQueue(getActivity());
        userInfoSp = MyApplication_.getInstance().getUserInfoSp();
        initBroadcastReceiver();
        initRecyclerView();
        initSwipeRefreshLayout();
    }

    private void initRecyclerView() {
        mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (mAdapter != null && newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == mAdapter.getItemCount() && !isLoading) {
                    mSwipeRefreshLayout.setRefreshing(true);
                    isLoading = true;

                    if (pageIndex < totalPage) {
//                        createLoadingSnackbar(mSwipeRefreshLayout);
                        pageIndex = pageIndex + 1;
                        onBackgrounds();
                    } else {
                        mSwipeRefreshLayout.setRefreshing(false);
                        isLoading = false;
//                        createNoDateSnackbar(mSwipeRefreshLayout);
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

    private void initBroadcastReceiver() {
        mBroadcastManager = LocalBroadcastManager.getInstance(getActivity());
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(AppDelegate.ACTION_T_ORDER_FORM_SEARCH);
        mReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                arguments = intent.getStringExtra(AppDelegate.ACTION_T_ORDER_FORM_SEARCH);
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
//                createRefreshSnackbar(mRecyclerView, TAG);
            }
        });
    }

    @Override
    public void onBackgrounds() {
        final String URL = MyAPI.getBaseUrl() + "/api/Orders/Order/FindMyOrderList?pageSize=10&pageIndex=" + pageIndex + arguments;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL, new Response.Listener<org.json.JSONObject>() {
            @Override
            public void onResponse(org.json.JSONObject response) {
                LogUtils.d("\n我的订单-URL:" + URL + "\n我的订单-RESPONSE:" + response.toString());

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
                    rowsEntityList = JSON.parseArray(rows.toString(), T_OrderFormEntity.RowsEntity.class);
                    mAdapter = new MyOrderFormEntityAdapter(rowsEntityList);
                    mRecyclerView.setAdapter(mAdapter);
//                    if (!isFirstLoading)
//                        createRefreshCompleteSnackbar(mRecyclerView);
                } else if (pageIndex <= totalPage) {
                    JSONArray rows = jsonObject.getJSONArray("rows");
                    rowsEntityList.addAll(lastVisibleItem + 1, JSON.parseArray(rows.toString(), T_OrderFormEntity.RowsEntity.class));
                    mAdapter.notifyDataSetChanged();
//                    createLoadingCompleteSnackbar(mRecyclerView);
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

    /**
     * 将搜索有记录的 关键字 存起来（订单搜索界面 外销发票号 模糊搜索的历史记录）
     *
     * @param keyword
     */
    private void saveKeyHistory(String keyword) {
        // 存入进行搜索过的关键字
        SharedPreferences searchHistorySp = MyApplication_.getInstance().getSearchHistorySp();
        Set<String> search_history = searchHistorySp.getStringSet(AppDelegate.HISTORY_SALE_INVOICE_SEARCH, null);
        if (search_history == null) {
            Set<String> newSet = new TreeSet<>();
            newSet.add(keyword);
            searchHistorySp.edit().putStringSet(AppDelegate.HISTORY_SALE_INVOICE_SEARCH, newSet).commit();
        } else {
            Set<String> sets = new TreeSet<>();
            String[] strings = search_history.toArray(new String[]{});
            for (int i = 0; i < strings.length; i++) {
                sets.add(strings[i]);
            }
            sets.add(keyword);
            searchHistorySp.edit().putStringSet(AppDelegate.HISTORY_SALE_INVOICE_SEARCH, sets).commit();
        }
    }


    private class MyOrderFormEntityAdapter extends RecyclerView.Adapter<MyOrderFormEntityAdapter.ViewHolder> {

        private List<T_OrderFormEntity.RowsEntity> list;

        public MyOrderFormEntityAdapter(List<T_OrderFormEntity.RowsEntity> list) {
            this.list = list;
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_orderform_list, viewGroup, false);
            return new ViewHolder(view);
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            public RelativeLayout rl_content;
            public AlwaysMarqueeTextView tv_salesorder; // 外销发票号
            public AppCompatTextView tv_statusFormatCn; // 状态
            public AppCompatTextView tv_orderform_amount; // 订单金额
            public AppCompatTextView tv_orderform_createDate; // 创建时间
            public AppCompatTextView tv_orderform_foreignName;  // 外商名称
            public AppCompatTextView tv_orderform_code; // 订单流水号
            public AppCompatTextView tv_orderItemName; // 服务项目
            public AppCompatTextView tv_customerPurchaseOrder; // 客户PO号

            public ViewHolder(final View itemView) {
                super(itemView);
                tv_salesorder = (AlwaysMarqueeTextView) itemView.findViewById(R.id.tv_salesorder);
                tv_statusFormatCn = (AppCompatTextView) itemView.findViewById(R.id.tv_statusFormatCn);
                tv_orderform_amount = (AppCompatTextView) itemView.findViewById(R.id.tv_orderform_amount);
                tv_orderform_createDate = (AppCompatTextView) itemView.findViewById(R.id.tv_orderform_createDate);
                tv_orderform_foreignName = (AppCompatTextView) itemView.findViewById(R.id.tv_orderform_foreignName);
                tv_orderform_code = (AppCompatTextView) itemView.findViewById(R.id.tv_orderform_code);
                tv_orderItemName = (AppCompatTextView) itemView.findViewById(R.id.tv_orderItemName);
                tv_customerPurchaseOrder = (AppCompatTextView) itemView.findViewById(R.id.tv_customerPurchaseOrder);
                rl_content = (RelativeLayout) itemView.findViewById(R.id.rl_content);
                rl_content.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        T_OrderFormEntity.RowsEntity rowsEntity = list.get(getLayoutPosition());
                        T_OrderFormDetailsActivity_.intent(getActivity()).extra(AppDelegate.ORDER_ID, rowsEntity.getId()).start();
                    }
                });
            }
        }

        @Override
        public void onBindViewHolder(ViewHolder viewHolder, int position) {
            T_OrderFormEntity.RowsEntity entity = list.get(position);

            viewHolder.tv_salesorder.setText(entity.getSalesOrder());
            viewHolder.tv_salesorder.setTextColor(getColors(R.color.color_theme));

            String statusFormatCn = entity.getStatusFormatCn();
            viewHolder.tv_statusFormatCn.setText(statusFormatCn);
            switch (statusFormatCn) {
                case "草稿":
                    viewHolder.tv_statusFormatCn.setTextColor(getColors(R.color.color_theme_light));
                    break;
                case "进行中":
                    viewHolder.tv_statusFormatCn.setTextColor(getColors(R.color.red_500));
                    break;
                case "已完成":
                    viewHolder.tv_statusFormatCn.setTextColor(getColors(R.color.green_300));
                    break;
            }

            viewHolder.tv_orderform_amount.setText(StringUtil.numberFormat(entity.getAmount()));
            viewHolder.tv_orderform_createDate.setText(StringUtil.dateRemoveT(entity.getCreateDate()));
            viewHolder.tv_orderform_foreignName.setText(entity.getForeignName());
            viewHolder.tv_orderform_code.setText(entity.getCode());
            viewHolder.tv_orderItemName.setText(entity.getOrderItemName());
            viewHolder.tv_customerPurchaseOrder.setText(entity.getCustomerPurchaseOrder());

        }

    }

    @Override
    public void onDestroyView() {
        mRequestQueue.cancelAll(this);
        mBroadcastManager.unregisterReceiver(mReceiver);
        super.onDestroyView();
    }

    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart(TAG);
    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd(TAG);
    }

}
