package com.qskj.tyt.fragment;

import android.content.SharedPreferences;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.qskj.tyt.entity.T_InvoiceNoticeEntity;
import com.qskj.tyt.view.AlwaysMarqueeTextView;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 预警中心-开票预警
 */
@EFragment(R.layout.swiperefreshlayout_recyclerview_tvnodata)
public class T_WarningCenter_InvoiceWarning extends BaseFragment {

    private static final String TAG = "T_WarningCenter_InvoiceWarning";
    private String accountId;

    private List<T_InvoiceNoticeEntity.RowsEntity> rowsEntityList;
    private MyInvoiceNoticeAdapter mAdapter;
    private int lastVisibleItem;
    private int totalPage;
    private int pageIndex = 1;
    private boolean isLoading;
    private boolean isFirstLoading;
    private LinearLayoutManager mLinearLayoutManager;
    private RequestQueue mRequestQueue;
    private SharedPreferences userInfoSp;

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
        accountId = userInfoSp.getString(AppDelegate.ACCOUNT_ID, "");
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
        // 请求获取  预警中心-开票预警 http://192.168.64.184:10103/api/Funds/VatRegister/FindVatInvoiceVerifyOverdue
        final String url = MyAPI.getBaseUrl() + "/api/Funds/VatRegister/FindVatInvoiceVerifyOverdue?pageSize=10&pageIndex=" + pageIndex + "&accountId=" + accountId;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, new Response.Listener<org.json.JSONObject>() {
            @Override
            public void onResponse(org.json.JSONObject response) {
                LogUtils.d("\n预警中心-开票预警-URL:" + url + "\n预警中心-开票预警-RESPONSE:" + response.toString());

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
                map.put(AppDelegate.QS_LOGIN, userInfoSp.getString(AppDelegate.LOGIN_NAME, ""));
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
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.t_item_invoice_warning_list, viewGroup, false);
            return new ViewHolder(view);
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            public RelativeLayout rl_content;
            public AlwaysMarqueeTextView tv_warning_export_invoice_number; // 外销发票号
            public AlwaysMarqueeTextView tv_warning_foreign_businessman; // 外商名称
            public AppCompatTextView tv_warning_invoice_unit;  // 开票单位
            public AppCompatTextView tv_warning_amount; // 金额
            public AppCompatTextView tv_warning_invoice_date; // 发票日期
            public AppCompatTextView tv_warning_declare_date; // 报关日期
            public AppCompatTextView tv_warning_approve_expiration_date; // 认证截止日期
            public AppCompatTextView tv_warning_order_serial_number; // 订单流水号
            public AppCompatTextView tv_warning_po_number; // PO号
            public AppCompatTextView tv_warning_create_date; // 创建时间

            public ViewHolder(final View itemView) {
                super(itemView);
                tv_warning_export_invoice_number = (AlwaysMarqueeTextView) itemView.findViewById(R.id.tv_warning_export_invoice_number);
                tv_warning_foreign_businessman = (AlwaysMarqueeTextView) itemView.findViewById(R.id.tv_warning_foreign_businessman);
                tv_warning_invoice_unit = (AppCompatTextView) itemView.findViewById(R.id.tv_warning_invoice_unit);
                tv_warning_amount = (AppCompatTextView) itemView.findViewById(R.id.tv_warning_amount);
                tv_warning_invoice_date = (AppCompatTextView) itemView.findViewById(R.id.tv_warning_invoice_date);
                tv_warning_declare_date = (AppCompatTextView) itemView.findViewById(R.id.tv_warning_declare_date);
                tv_warning_approve_expiration_date = (AppCompatTextView) itemView.findViewById(R.id.tv_warning_approve_expiration_date);
                tv_warning_order_serial_number = (AppCompatTextView) itemView.findViewById(R.id.tv_warning_order_serial_number);
                tv_warning_po_number = (AppCompatTextView) itemView.findViewById(R.id.tv_warning_po_number);
                tv_warning_create_date = (AppCompatTextView) itemView.findViewById(R.id.tv_warning_create_date);
                rl_content = (RelativeLayout) itemView.findViewById(R.id.rl_content);
                rl_content.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
            }
        }

        @Override
        public void onBindViewHolder(ViewHolder viewHolder, int position) {
            //TODO 没有数据，所以不知道返回的是什么数据，所以没有办法创建实体类
            T_InvoiceNoticeEntity.RowsEntity entity = list.get(position);

//            viewHolder.tv_invoice_notice_orderCode.setText(entity.getOrderCode());
//            Object remark = entity.getRemark();
//            if (remark != null)
//                viewHolder.tv_invoice_notice_remark.setText((String) remark);

        }

    }

    @Override
    public void onDestroyView() {
        mRequestQueue.cancelAll(this);
        super.onDestroyView();
    }

}
