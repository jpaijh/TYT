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
import com.qskj.tyt.activity.T_AccountFundDetailsActivity_;
import com.qskj.tyt.entity.T_AccountFundDetailsEntity;
import com.qskj.tyt.utils.StringUtil;
import com.qskj.tyt.view.AlwaysMarqueeTextView;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * 我的资金-普通账户界面
 */
@EFragment(R.layout.swiperefreshlayout_recyclerview_tvnodata)
public class T_MyFund_AccountGeneralFragment extends BaseFragment {

    private static final String TAG = "T_MyFund_AccountGeneralFragment";

    private List<T_AccountFundDetailsEntity.RowsEntity> rowsEntityList;
    private MyAccountGeneralAdapter mAdapter;
    private LocalBroadcastManager mBroadcastManager;
    private BroadcastReceiver mReceiver;
    private int lastVisibleItem;
    private int totalPage;
    private int pageIndex = 1;
    private boolean isLoading;
    private boolean isFirstLoading;
    private LinearLayoutManager mLinearLayoutManager;
    private RequestQueue mRequestQueue;
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
        initBroadcastReceiver();
        initRecyclerView();
        initSwipeRefreshLayout();
    }

    /**
     * 注册广播接收者
     */
    private void initBroadcastReceiver() {
        mBroadcastManager = LocalBroadcastManager.getInstance(getActivity());
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(AppDelegate.ACTION_T_GENERAL_ACCOUNT_SEARCH);
        mReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                arguments = intent.getStringExtra(AppDelegate.ACTION_T_ACCOUNT_SEARCH);
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

    @Override
    public void onBackgrounds() {
        // 普通账户资金列表 type = 1
        final String URL = MyAPI.getBaseUrl() + "/api/Funds/FundAccount/FindVFundDetiDetails?type=1&pageSize=10&pageIndex=" + pageIndex
                + "&accountId=" + MyApplication_.getInstance().getUserInfoSp().getString(AppDelegate.ACCOUNT_ID, "")
                + "&titleId=" + MyApplication_.getInstance().getUserInfoSp().getInt(AppDelegate.TITLE_ID, 0) + arguments;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL, new Response.Listener<org.json.JSONObject>() {
            @Override
            public void onResponse(org.json.JSONObject response) {
                LogUtils.d("\n普通账户-URL:" + URL + "\n普通账户-RESPONSE:" + response.toString());

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
                    rowsEntityList = JSON.parseArray(rows.toString(), T_AccountFundDetailsEntity.RowsEntity.class);
                    mAdapter = new MyAccountGeneralAdapter(rowsEntityList);
                    mRecyclerView.setAdapter(mAdapter);
                    if (!isFirstLoading)
                        createRefreshCompleteSnackbar(mRecyclerView);
                } else if (pageIndex <= totalPage) {
                    JSONArray rows = jsonObject.getJSONArray("rows");
                    rowsEntityList.addAll(lastVisibleItem + 1, JSON.parseArray(rows.toString(), T_AccountFundDetailsEntity.RowsEntity.class));
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
     * 将搜索有记录的 关键字 存起来（订单搜索界面 外销发票号 模糊搜索的历史记录）
     *
     * @param keyword
     */
    private void saveKeyHistory(String keyword) {
        // 存入进行搜索过的关键字
        SharedPreferences searchHistorySp = MyApplication_.getInstance().getSearchHistorySp();
        Set<String> search_history = searchHistorySp.getStringSet(AppDelegate.HISTORY_SALE_INVOICE_ACCOUNT_SEARCH, null);
        if (search_history == null) {
            Set<String> newSet = new TreeSet<>();
            newSet.add(keyword);
            searchHistorySp.edit().putStringSet(AppDelegate.HISTORY_SALE_INVOICE_ACCOUNT_SEARCH, newSet).commit();
        } else {
            Set<String> sets = new TreeSet<>();
            String[] strings = search_history.toArray(new String[]{});
            for (int i = 0; i < strings.length; i++) {
                sets.add(strings[i]);
            }
            sets.add(keyword);
            searchHistorySp.edit().putStringSet(AppDelegate.HISTORY_SALE_INVOICE_ACCOUNT_SEARCH, sets).commit();
        }
    }

    private class MyAccountGeneralAdapter extends RecyclerView.Adapter<MyAccountGeneralAdapter.ViewHolder> {

        private List<T_AccountFundDetailsEntity.RowsEntity> list;

        public MyAccountGeneralAdapter(List<T_AccountFundDetailsEntity.RowsEntity> list) {
            this.list = list;
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.t_item_account_list, viewGroup, false);
            return new ViewHolder(view);
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public RelativeLayout rl_content;
            public AlwaysMarqueeTextView tv_relatedCompanyName; // 往来方
            public AppCompatTextView tv_fundName; // 项目
            public AppCompatTextView tv_amountRmb;  // 收入/ 支出金额
            public AppCompatTextView tv_createDate; // 时间

            public ViewHolder(final View itemView) {
                super(itemView);
                tv_relatedCompanyName = (AlwaysMarqueeTextView) itemView.findViewById(R.id.tv_relatedCompanyName);
                tv_fundName = (AppCompatTextView) itemView.findViewById(R.id.tv_fundName);
                tv_amountRmb = (AppCompatTextView) itemView.findViewById(R.id.tv_amountRmb);
                tv_createDate = (AppCompatTextView) itemView.findViewById(R.id.tv_createDate);
                rl_content = (RelativeLayout) itemView.findViewById(R.id.rl_content);
                rl_content.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        T_AccountFundDetailsEntity.RowsEntity rowsEntity = list.get(getLayoutPosition());

                        T_AccountFundDetailsActivity_.intent(getActivity()).extra("rowsEntity", rowsEntity).start();
                    }
                });
            }
        }

        @Override
        public void onBindViewHolder(ViewHolder viewHolder, int position) {
            T_AccountFundDetailsEntity.RowsEntity entity = list.get(position);

            viewHolder.tv_relatedCompanyName.setText(entity.getRelatedCompanyName());
            viewHolder.tv_fundName.setText(entity.getFundName());

            double amountRmb = entity.getAmountRmb();
            if (amountRmb <= 0) {
                viewHolder.tv_amountRmb.setText(StringUtil.numberFormat(amountRmb));
                viewHolder.tv_amountRmb.setTextColor(getColors(R.color.red_500));
            } else {
                viewHolder.tv_amountRmb.setText("+" + StringUtil.numberFormat(amountRmb));
                viewHolder.tv_amountRmb.setTextColor(getColors(R.color.green_300));
            }

            viewHolder.tv_createDate.setText(StringUtil.dateRemoveT(entity.getCreateDate()));
        }

    }

    @Override
    public void onDestroyView() {
        mRequestQueue.cancelAll(this);
        mBroadcastManager.unregisterReceiver(mReceiver);
        super.onDestroyView();
    }

}
