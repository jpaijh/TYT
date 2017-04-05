package com.qskj.tyt.fragment;


import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
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
import com.qskj.tyt.activity.T_InvoiceNoticeDetailsActivity_;
import com.qskj.tyt.entity.T_InvoiceNoticeEntity;
import com.qskj.tyt.utils.StringUtil;
import com.qskj.tyt.view.AlwaysMarqueeTextView;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 我的票据-开票通知
 */
@EFragment(R.layout.swiperefreshlayout_recyclerview_tvnodata)
public class T_MyInvoice_InvoiceNoticeFragment extends BaseFragment {

    private static final String TAG = "MyInvoice_InvoiceNoticeFragment";

    private List<T_InvoiceNoticeEntity.RowsEntity> rowsEntityList;
    private MyInvoiceNoticeAdapter mAdapter;
    private int lastVisibleItem;
    private int totalPage;
    private int pageIndex = 1;
    private boolean isLoading;
    private boolean isFirstLoading;
    private LinearLayoutManager mLinearLayoutManager;
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
        // 请求获取 开票通知
        final String url = MyAPI.getBaseUrl() + "/api/Funds/InvoiceNotice/FindVatInvoiceListFront?pageSize=10&pageIndex=" + pageIndex;
        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, new Response.Listener<org.json.JSONObject>() {
            @Override
            public void onResponse(org.json.JSONObject response) {
                LogUtils.d("\n开票通知-URL:" + url + "\n开票通知-RESPONSE:" + response.toString());

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
                    rowsEntityList = JSON.parseArray(rows.toString(), T_InvoiceNoticeEntity.RowsEntity.class);
                    mAdapter = new MyInvoiceNoticeAdapter(rowsEntityList);
                    mRecyclerView.setAdapter(mAdapter);
                    if (!isFirstLoading)
                        createRefreshCompleteSnackbar(mRecyclerView);
                } else if (pageIndex <= totalPage) {
                    JSONArray rows = jsonObject.getJSONArray("rows");
                    rowsEntityList.addAll(lastVisibleItem + 1, JSON.parseArray(rows.toString(), T_InvoiceNoticeEntity.RowsEntity.class));
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

    private class MyInvoiceNoticeAdapter extends RecyclerView.Adapter<MyInvoiceNoticeAdapter.ViewHolder> {

        private List<T_InvoiceNoticeEntity.RowsEntity> list;

        public MyInvoiceNoticeAdapter(List<T_InvoiceNoticeEntity.RowsEntity> list) {
            this.list = list;
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.t_item_invoice_notice_list, viewGroup, false);
            return new ViewHolder(view);
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public RelativeLayout rl_content;
            public AlwaysMarqueeTextView tv_invoice_notice_orderCode; // 通知单号
            public AlwaysMarqueeTextView tv_account_name; // 开票单位名称
            public AppCompatTextView tv_status;  // 状态
            public AppCompatTextView tv_adviceDate; // 通知时间
            public AppCompatTextView tv_amount; // 开票总金额
            public AppCompatTextView tv_code; // 外销发票号
            public AppCompatTextView tv_invoice_notice_remark; // 备注

            public ViewHolder(final View itemView) {
                super(itemView);
                tv_invoice_notice_orderCode = (AlwaysMarqueeTextView) itemView.findViewById(R.id.tv_invoice_notice_orderCode);
                tv_account_name = (AlwaysMarqueeTextView) itemView.findViewById(R.id.tv_account_name);
                tv_status = (AppCompatTextView) itemView.findViewById(R.id.tv_status);
                tv_adviceDate = (AppCompatTextView) itemView.findViewById(R.id.tv_adviceDate);
                tv_amount = (AppCompatTextView) itemView.findViewById(R.id.tv_amount);
                tv_code = (AppCompatTextView) itemView.findViewById(R.id.tv_code);
                tv_invoice_notice_remark = (AppCompatTextView) itemView.findViewById(R.id.tv_invoice_notice_remark);
                rl_content = (RelativeLayout) itemView.findViewById(R.id.rl_content);
                rl_content.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        T_InvoiceNoticeEntity.RowsEntity rowsEntity = list.get(getLayoutPosition());
                        // 进入详情页
                        T_InvoiceNoticeDetailsActivity_.intent(getActivity()).extra("rowsEntity", rowsEntity).start();
                    }
                });
            }
        }

        @Override
        public void onBindViewHolder(ViewHolder viewHolder, int position) {
            T_InvoiceNoticeEntity.RowsEntity entity = list.get(position);

            viewHolder.tv_invoice_notice_orderCode.setText(entity.getOrderCode());
            viewHolder.tv_account_name.setText(entity.getAccountName());
            viewHolder.tv_adviceDate.setText(StringUtil.dateRemoveT(entity.getAdviceDate()));
            viewHolder.tv_amount.setText(StringUtil.numberFormat(entity.getAmount()));
            viewHolder.tv_code.setText(entity.getCode());

            Object remark = entity.getRemark();
            if (remark != null)
                viewHolder.tv_invoice_notice_remark.setText((String) remark);

            int status = entity.getStatus();
            switch (status) {
                case 0: // 新制 = 0
                    viewHolder.tv_status.setText(R.string.brand_new);
                    viewHolder.tv_status.setTextColor(getColors(R.color.red_500));
                    break;
                case 1:// 提交=1
                    viewHolder.tv_status.setText(R.string.commit);
                    viewHolder.tv_status.setTextColor(getColors(R.color.color_theme));
                    break;
                case 2:// 受理=2
                    viewHolder.tv_status.setText(R.string.accepted);
                    viewHolder.tv_status.setTextColor(getColors(R.color.color_theme_light));
                    break;
                case 3: // 生效=3
                    viewHolder.tv_status.setText(R.string.take_effect);
                    viewHolder.tv_status.setTextColor(getColors(R.color.green_300));
                    break;
                case 4: // 退回=4
                    viewHolder.tv_status.setText(R.string.send_back);
                    viewHolder.tv_status.setTextColor(getColors(R.color.red_500));
                    break;
            }

        }

    }

    @Override
    public void onDestroyView() {
        mRequestQueue.cancelAll(this);
        super.onDestroyView();
    }

}
