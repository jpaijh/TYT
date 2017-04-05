package com.qskj.tyt.activity;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.apkfuns.logutils.LogUtils;
import com.qskj.tyt.AppDelegate;
import com.qskj.tyt.MyAPI;
import com.qskj.tyt.MyApplication_;
import com.qskj.tyt.R;
import com.qskj.tyt.entity.T_InvoiceInformationEntity;
import com.qskj.tyt.entity.T_InvoiceInformationProductsEntity;
import com.qskj.tyt.utils.StringUtil;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.json.JSONArray;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 发票信息 详情页-商品详情
 */
@EActivity(R.layout.swiperefreshlayout_recyclerview_toolbar_tvnodata)
public class T_InvoiceInformationProductsActivity extends BaseActivity {

    private static final String TAG = "T_InvoiceInformationProductsActivity";

    private MyInvoiceInformationProductsAdapter mAdapter;
    private LinearLayoutManager mLinearLayoutManager;
    private RequestQueue mRequestQueue;
    private boolean isFirstLoading;

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
        initRecyclerView();
        initSwipeRefreshLayout();
    }

    private void initToolbar() {
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        tv_title.setText(R.string.title_activity_invoice_information_products);
    }

    private void initRecyclerView() {
        mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setHasFixedSize(true);
    }

    private void initSwipeRefreshLayout() {
        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(true);
                isFirstLoading = true;
                saveCurrentTime(TAG);
                onBackgrounds();
            }
        });

        mSwipeRefreshLayout.setColorSchemeResources(R.color.color_progress_1, R.color.color_progress_2);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                isFirstLoading = false;
                onBackgrounds();
                createRefreshSnackbar(mRecyclerView, TAG);
            }
        });
    }

    @Background
    public void onBackgrounds() {
        // 发票信息 详情页-商品详情 http://101.200.194.102:10104/api/Funds/VatRegister/FindVatInvoiceDetails?ownerId=2962926
        T_InvoiceInformationEntity.RowsEntity rowsEntity = (T_InvoiceInformationEntity.RowsEntity) getIntent().getSerializableExtra("rowsEntity");
        if (rowsEntity != null) {
            final String url = MyAPI.getBaseUrl() + "/api/Funds/VatRegister/FindVatInvoiceDetails?owerId=" + rowsEntity.getId();

            JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    LogUtils.d("\n发票信息-商品详情-URL:" + url + "\n发票信息-商品详情-RESPONSE:" + response.toString());

                    List<T_InvoiceInformationProductsEntity> invoiceInformationProductsEntities =
                            JSON.parseArray(response.toString(), T_InvoiceInformationProductsEntity.class);

                    if (invoiceInformationProductsEntities.size() == 0) {
                        showView(tv_no_data);
                    } else {
                        hideView(tv_no_data);
                    }

                    if (invoiceInformationProductsEntities != null) {
                        mAdapter = new MyInvoiceInformationProductsAdapter(invoiceInformationProductsEntities);
                        mRecyclerView.setAdapter(mAdapter);
                    }

                    if (!isFirstLoading)
                        createRefreshCompleteSnackbar(mRecyclerView);

                    mSwipeRefreshLayout.setRefreshing(false);
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

            jsonArrayRequest.setTag(this);
            mRequestQueue.add(jsonArrayRequest);
        }

    }

    private class MyInvoiceInformationProductsAdapter extends RecyclerView.Adapter<MyInvoiceInformationProductsAdapter.ViewHolder> {

        private List<T_InvoiceInformationProductsEntity> list;

        public MyInvoiceInformationProductsAdapter(List<T_InvoiceInformationProductsEntity> list) {
            this.list = list;
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.t_item_invoice_information_products_details, viewGroup, false);
            return new ViewHolder(view);
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            public TextView tv_orderCommodityName; // 品名
            public TextView tv_orderCommodityUnit; // 单位
            public TextView tv_orderCommodityQuantity; // 数量
            public TextView tv_orderCommodityPriceWov; // 单价
            public TextView tv_orderCommodityAmountWov; // 不含税金额
            public TextView tv_orderCommodityRebateRate; // 税率
            public TextView tv_orderCommodityVatAmount; // 税额

            public ViewHolder(final View itemView) {
                super(itemView);
                tv_orderCommodityName = (TextView) itemView.findViewById(R.id.tv_orderCommodityName);
                tv_orderCommodityUnit = (TextView) itemView.findViewById(R.id.tv_orderCommodityUnit);
                tv_orderCommodityQuantity = (TextView) itemView.findViewById(R.id.tv_orderCommodityQuantity);
                tv_orderCommodityPriceWov = (TextView) itemView.findViewById(R.id.tv_orderCommodityPriceWov);
                tv_orderCommodityAmountWov = (TextView) itemView.findViewById(R.id.tv_orderCommodityAmountWov);
                tv_orderCommodityRebateRate = (TextView) itemView.findViewById(R.id.tv_orderCommodityRebateRate);
                tv_orderCommodityVatAmount = (TextView) itemView.findViewById(R.id.tv_orderCommodityVatAmount);
                // 整个条目点击事件
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                });
            }
        }

        @Override
        public void onBindViewHolder(ViewHolder viewHolder, int position) {
            T_InvoiceInformationProductsEntity entity = list.get(position);

            viewHolder.tv_orderCommodityName.setText(entity.getOrderCommodityName());
            viewHolder.tv_orderCommodityUnit.setText(entity.getOrderCommodityUnit());
            viewHolder.tv_orderCommodityQuantity.setText(StringUtil.numberFormat(entity.getOrderCommodityQuantity()));
            viewHolder.tv_orderCommodityPriceWov.setText(StringUtil.numberFormat(entity.getOrderCommodityPriceWov()));
            viewHolder.tv_orderCommodityAmountWov.setText(StringUtil.numberFormat(entity.getOrderCommodityAmountWov()));
            viewHolder.tv_orderCommodityRebateRate.setText(StringUtil.numberFormat(entity.getOrderCommodityRebateRate()));
            viewHolder.tv_orderCommodityVatAmount.setText(StringUtil.numberFormat(entity.getOrderCommodityVatAmount()));

        }

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
    public void onDestroy() {
        mRequestQueue.cancelAll(this);
        super.onDestroy();
    }

}
