package com.qskj.tyt.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
import com.qskj.tyt.entity.T_InvoiceNoticeDetailsEntity;
import com.qskj.tyt.utils.StringUtil;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.HashMap;

import java.util.List;
import java.util.Map;

/**
 * 开票通知-商品详情
 */
@EFragment(R.layout.swiperefreshlayout_recyclerview_tvnodata)
public class T_InvoiceNoticeDetails_ProductsDetailsFragment extends BaseFragment {

    private static final String TAG = "T_InvoiceNoticeDetails_ProductsDetailsFragment";

    private MyInvoiceNoticeProductsAdapter mAdapter;
    private LinearLayoutManager mLinearLayoutManager;
    private boolean isFirstLoading;
    private RequestQueue mRequestQueue;

    @ViewById(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    @ViewById(R.id.recyclerview)
    RecyclerView mRecyclerView;

    @ViewById(R.id.tv_no_data)
    AppCompatTextView tv_no_data;

    @Override
    public void onAfterViews() {
        mRequestQueue = Volley.newRequestQueue(getActivity());
        initRecyclerView();
        initSwipeRefreshLayout();
    }

    private void initSwipeRefreshLayout() {
        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(true);
                saveCurrentTime(TAG);
                isFirstLoading = true;
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

    private void initRecyclerView() {
        mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setHasFixedSize(true);
    }

    @Override
    public void onBackgrounds() {
        // 获取开票通知-商品列表
        final String url = MyAPI.getBaseUrl() + "/api/Funds/InvoiceNotice/GetInvoiceNoticeById?id=" + getArguments().getInt("id");
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, new Response.Listener<org.json.JSONObject>() {
            @Override
            public void onResponse(org.json.JSONObject response) {
                LogUtils.d("\n开票通知-商品列表-URL:" + url + "\n开票通知-商品列表-nRESPONSE:" + response.toString());

                JSONObject jsonObject = JSON.parseObject(response.toString());
                JSONArray details = jsonObject.getJSONArray("details");
                List<T_InvoiceNoticeDetailsEntity.DetailsEntity> detailsEntities = JSON.parseArray(details.toString(), T_InvoiceNoticeDetailsEntity.DetailsEntity.class);

                if (detailsEntities.size() == 0) {
                    showView(tv_no_data);
                } else {
                    hideView(tv_no_data);
                }

                if (detailsEntities != null) {
                    mAdapter = new MyInvoiceNoticeProductsAdapter(detailsEntities);
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

        jsonObjectRequest.setTag(this);
        mRequestQueue.add(jsonObjectRequest);
    }

    private class MyInvoiceNoticeProductsAdapter extends RecyclerView.Adapter<MyInvoiceNoticeProductsAdapter.ViewHolder> {

        private List<T_InvoiceNoticeDetailsEntity.DetailsEntity> list;

        public MyInvoiceNoticeProductsAdapter(List<T_InvoiceNoticeDetailsEntity.DetailsEntity> list) {
            this.list = list;
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.t_item_invoice_notice_products_details, viewGroup, false);
            return new ViewHolder(view);
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            public TextView tv_orderCommodityName; // 品名
            public TextView tv_specification; // 规格型号
            public TextView tv_orderCommodityUnit; // 单位
            public TextView tv_orderCommodityQuantity; // 数量
            public TextView tv_orderCommodityPriceWov; // 单价
            public TextView tv_orderCommodityAmountWov; // 不含税金额
            public TextView tv_orderCommodityRebateRate; // 税率
            public TextView tv_orderCommodityVatAmount; // 税额

            public ViewHolder(final View itemView) {
                super(itemView);
                tv_orderCommodityName = (TextView) itemView.findViewById(R.id.tv_orderCommodityName);
                tv_specification = (TextView) itemView.findViewById(R.id.tv_specification);
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
            T_InvoiceNoticeDetailsEntity.DetailsEntity entity = list.get(position);

            viewHolder.tv_orderCommodityName.setText(entity.getOrderCommodityName());

            Object specification = entity.getSpecification();
            if (specification != null)
                viewHolder.tv_specification.setText((String) specification);

            viewHolder.tv_orderCommodityUnit.setText(entity.getOrderCommodityUnit());
            viewHolder.tv_orderCommodityQuantity.setText(StringUtil.numberFormat(entity.getOrderCommodityQuantity()));
            viewHolder.tv_orderCommodityPriceWov.setText(StringUtil.numberFormat(entity.getOrderCommodityPriceWov()));
            viewHolder.tv_orderCommodityAmountWov.setText(StringUtil.numberFormat(entity.getOrderCommodityAmountWov()));
            viewHolder.tv_orderCommodityRebateRate.setText(StringUtil.numberFormat(entity.getOrderCommodityRebateRate()));
            viewHolder.tv_orderCommodityVatAmount.setText(StringUtil.numberFormat(entity.getOrderCommodityVatAmount()));

        }

    }

    @Override
    public void onDestroyView() {
        mRequestQueue.cancelAll(this);
        super.onDestroyView();
    }

}
