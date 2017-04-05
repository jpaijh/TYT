package com.qskj.tyt.fragment;

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
import com.qskj.tyt.activity.T_InvoiceInformationProductsActivity_;
import com.qskj.tyt.entity.T_InvoiceInformationEntity;
import com.qskj.tyt.utils.StringUtil;
import com.qskj.tyt.view.AlwaysMarqueeTextView;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 我的票据 - 发票信息
 * Created by 赵 鑫 on 2015/9/16.
 */
@EFragment(R.layout.swiperefreshlayout_recyclerview_tvnodata)
public class T_MyInvoice_InvoiceInformationFragment extends BaseFragment {

    private static final String TAG = "T_MyInvoice_InvoiceInformationFragment";

    private MyInvoiceInformationAdapter mAdapter;
    private List<T_InvoiceInformationEntity.RowsEntity> rowsEntityList;
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
        // 请求获取 发票信息
        final String url = MyAPI.getBaseUrl() + "/api/Funds/VatRegister/FindVatInvoicePreList?pageSize=10&pageIndex=" + pageIndex;
        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, new Response.Listener<org.json.JSONObject>() {
            @Override
            public void onResponse(org.json.JSONObject response) {
                LogUtils.d("\n发票信息-URL:" + url + "\n发票信息-RESPONSE:" + response.toString());

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
                    rowsEntityList = JSON.parseArray(rows.toString(), T_InvoiceInformationEntity.RowsEntity.class);
                    mAdapter = new MyInvoiceInformationAdapter(rowsEntityList);
                    mRecyclerView.setAdapter(mAdapter);
                    if (!isFirstLoading)
                        createRefreshCompleteSnackbar(mRecyclerView);
                } else if (pageIndex <= totalPage) {
                    JSONArray rows = jsonObject.getJSONArray("rows");
                    rowsEntityList.addAll(lastVisibleItem + 1, JSON.parseArray(rows.toString(), T_InvoiceInformationEntity.RowsEntity.class));
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

    private class MyInvoiceInformationAdapter extends RecyclerView.Adapter<MyInvoiceInformationAdapter.ViewHolder> {

        private List<T_InvoiceInformationEntity.RowsEntity> list;

        public MyInvoiceInformationAdapter(List<T_InvoiceInformationEntity.RowsEntity> list) {
            this.list = list;
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.t_item_invoice_information_list, parent, false);
            return new ViewHolder(view);
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public RelativeLayout rl_content;
            public AlwaysMarqueeTextView tv_invoice_code; // 发票号
            public AppCompatTextView tv_type; // 发票类型
            public AppCompatTextView tv_invoice_amount;  // 开票金额
            public AppCompatTextView tv_createDate; // 开票日期
            public AppCompatTextView tv_confirmDate; // 增票确认日期
            public AppCompatTextView tv_saleOrder; // 外销发票号
            public AppCompatTextView tv_orderCode; // 订单流水号
            public AppCompatTextView tv_invoice_status; // 增票状态
            public AppCompatTextView tv_accountName; // 开票单位
            public AppCompatTextView tv_draweerName; // 开票单位名称

            public ViewHolder(final View itemView) {
                super(itemView);
                tv_invoice_code = (AlwaysMarqueeTextView) itemView.findViewById(R.id.tv_invoice_code);
                tv_type = (AppCompatTextView) itemView.findViewById(R.id.tv_type);
                tv_invoice_amount = (AppCompatTextView) itemView.findViewById(R.id.tv_invoice_amount);
                tv_createDate = (AppCompatTextView) itemView.findViewById(R.id.tv_createDate);
                tv_confirmDate = (AppCompatTextView) itemView.findViewById(R.id.tv_confirmDate);
                tv_saleOrder = (AppCompatTextView) itemView.findViewById(R.id.tv_saleOrder);
                tv_orderCode = (AppCompatTextView) itemView.findViewById(R.id.tv_orderCode);
                tv_invoice_status = (AppCompatTextView) itemView.findViewById(R.id.tv_invoice_status);
                tv_accountName = (AppCompatTextView) itemView.findViewById(R.id.tv_accountName);
                tv_draweerName = (AppCompatTextView) itemView.findViewById(R.id.tv_draweerName);
                rl_content = (RelativeLayout) itemView.findViewById(R.id.rl_content);
                rl_content.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        T_InvoiceInformationEntity.RowsEntity rowsEntity = list.get(getLayoutPosition());
                        T_InvoiceInformationProductsActivity_.intent(getActivity()).extra("rowsEntity", rowsEntity).start();
                    }
                });
            }
        }

        @Override
        public void onBindViewHolder(ViewHolder viewHolder, int position) {
            T_InvoiceInformationEntity.RowsEntity entity = list.get(position);

            viewHolder.tv_invoice_code.setText(entity.getCode());
            viewHolder.tv_invoice_amount.setText(StringUtil.numberFormat(entity.getAmount()) + "(RMB)");
            viewHolder.tv_invoice_amount.setTextColor(getColors(R.color.color_theme));
            viewHolder.tv_saleOrder.setText(entity.getSaleOrder());
            viewHolder.tv_orderCode.setText(entity.getOrderCode());

            // 这个 赠票确认日期 有问题，返回的是Obejct，有问题再改
            Object confirmDate = entity.getConfirmDate();
            if (confirmDate != null)
                viewHolder.tv_confirmDate.setText((String) confirmDate);

            //  开票单位 开票单位名称 不确定是不是这两个字段,而且返回的是Object
            Object accountName = entity.getAccountName();
            if (accountName != null)
                viewHolder.tv_accountName.setText((String) accountName);

            Object draweerName = entity.getDraweerName();
            if (draweerName != null)
                viewHolder.tv_draweerName.setText((String) draweerName);

//            <!--状态（0:新制；1：提交；2：已接受,3;已认证）-->
            // 字体的颜色需要改
            int status = entity.getStatus();
            switch (status) {
                case 0:
                    viewHolder.tv_invoice_status.setText("（" + getStrings(R.string.brand_new) + "）");
                    viewHolder.tv_invoice_status.setTextColor(getColors(R.color.red_500));
                    break;
                case 1:
                    viewHolder.tv_invoice_status.setText("（" + getStrings(R.string.commit) + "）");
                    viewHolder.tv_invoice_status.setTextColor(getColors(R.color.color_theme));
                    break;
                case 2:
                    viewHolder.tv_invoice_status.setText("（" + getStrings(R.string.invoice_accepted) + "）");
                    viewHolder.tv_invoice_status.setTextColor(getColors(R.color.color_theme_light));
                    break;
                case 3:
                    viewHolder.tv_invoice_status.setText("（" + getStrings(R.string.verified) + "）");
                    viewHolder.tv_invoice_status.setTextColor(getColors(R.color.green_300));
                    break;
            }

            viewHolder.tv_createDate.setText(StringUtil.dateRemoveT(entity.getCreateDate()));
            //   货款发票 = 0,费用发票 = 1
            int type = entity.getType();
            if (type == 0) {
                viewHolder.tv_type.setText(R.string.payment_invoice);
            } else if (type == 1) {
                viewHolder.tv_type.setText(R.string.expense_invoice);
            }
        }

    }

    @Override
    public void onDestroyView() {
        mRequestQueue.cancelAll(this);
        super.onDestroyView();
    }

}
