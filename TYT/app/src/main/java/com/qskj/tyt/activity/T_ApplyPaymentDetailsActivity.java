package com.qskj.tyt.activity;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.alibaba.fastjson.JSON;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.apkfuns.logutils.LogUtils;
import com.lsjwzh.widget.materialloadingprogressbar.CircleProgressBar;
import com.qskj.tyt.AppDelegate;
import com.qskj.tyt.MyAPI;
import com.qskj.tyt.MyApplication_;
import com.qskj.tyt.R;
import com.qskj.tyt.entity.T_ApplyPaymentListEntity;
import com.qskj.tyt.entity.T_ApplyPaymentWaterSplitEntity;
import com.qskj.tyt.utils.StringUtil;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 提款申请 详情页面
 */
@EActivity(R.layout.t_activity_apply_payment_details)
public class T_ApplyPaymentDetailsActivity extends BaseActivity {

    private String id;
    private String draweeBankName;
    private String draweeBankAccount;
    private RequestQueue mRequestQueue;

    @ViewById(R.id.ll)
    LinearLayout mLinearLayout;

    @ViewById(R.id.toolbar)
    Toolbar mToolbar;

    @ViewById(R.id.tv_title)
    AppCompatTextView tv_title;

    @ViewById(R.id.progressbar)
    CircleProgressBar mProgressbar;

    @ViewById(R.id.tv_applypayment_code)
    AppCompatTextView tv_applypayment_code;

    @ViewById(R.id.tv_applypayment_source)
    AppCompatTextView tv_applypayment_source;

    @ViewById(R.id.tv_applypayment_statusName)
    AppCompatTextView tv_applypayment_statusName;

    @ViewById(R.id.tv_applypayment_accountName)
    AppCompatTextView tv_applypayment_accountName;

    @ViewById(R.id.tv_applypayment_costName)
    AppCompatTextView tv_applypayment_costName;

    @ViewById(R.id.tv_applypayment_costName1)
    AppCompatTextView tv_applypayment_costName1;

    @ViewById(R.id.tv_applypayment_companyName)
    AppCompatTextView tv_applypayment_companyName;

    @ViewById(R.id.tv_applypayment_bank)
    AppCompatTextView tv_applypayment_bank;

    @ViewById(R.id.tv_applypayment_bankAccount)
    AppCompatTextView tv_applypayment_bankAccount;

    @ViewById(R.id.tv_applypayment_titleName)
    AppCompatTextView tv_applypayment_titleName;

    @ViewById(R.id.tv_applypayment_currency)
    AppCompatTextView tv_applypayment_currency;

    @ViewById(R.id.tv_applypayment_rmbRate)
    AppCompatTextView tv_applypayment_rmbRate;

    @ViewById(R.id.tv_applypayment_remittanceFee)
    AppCompatTextView tv_applypayment_remittanceFee;

    @ViewById(R.id.tv_applypayment_actualPaymentAmount)
    AppCompatTextView tv_applypayment_actualPaymentAmount;

    @ViewById(R.id.tv_applypayment_applyPaymentAmount)
    AppCompatTextView tv_applypayment_applyPaymentAmount;

    @ViewById(R.id.tv_applypayment_remittanceFee)
    AppCompatTextView tv_applypayment_payMethodName;

    @ViewById(R.id.tv_applypayment_remark)
    AppCompatTextView tv_applypayment_remark;

    @ViewById(R.id.tv_draweeBankName)
    AppCompatTextView tv_draweeBankName;

    @ViewById(R.id.tv_draweeBankAccount)
    AppCompatTextView tv_draweeBankAccount;

    @ViewById(R.id.tv_fileName)
    AppCompatTextView tv_fileName;

    @ViewById(R.id.tv_no_file)
    AppCompatTextView tv_no_file;

    @ViewById(R.id.tv_split_orderCode)
    AppCompatTextView tv_split_orderCode;

    @ViewById(R.id.tv_split_zpAmount)
    AppCompatTextView tv_split_zpAmount;

    @ViewById(R.id.tv_split_slipedAmount)
    AppCompatTextView tv_split_slipedAmount;

    @ViewById(R.id.tv_split_amount)
    AppCompatTextView tv_split_amount;

    @ViewById(R.id.tv_split_actualAmount)
    AppCompatTextView tv_split_actualAmount;

    @ViewById(R.id.tv_split_slipCompleted)
    AppCompatTextView tv_split_slipCompleted;

    @ViewById(R.id.tv_no_water_slip_information)
    AppCompatTextView tv_no_water_slip_information;

    @ViewById(R.id.btn_check_water_split_information)
    AppCompatButton btn_check_water_split_information;

    @ViewById(R.id.ll_water_split)
    LinearLayout ll_water_split;

    @Override
    public void onAfterViews() {
        initToolbar();
        mRequestQueue = Volley.newRequestQueue(this);
        initData();
    }

    private void initToolbar() {
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        tv_title.setText(R.string.title_activity_ApplyPaymentDetailsActivity);
    }

    private void initData() {
        showProgressbar();

        ArrayList<String> datas = getIntent().getStringArrayListExtra("datas");
        id = datas.get(0);
        //    <!-- 这两个字段从 /api/Funds/PaymentApply/FindPaymentApply 获取-->
        //    <string name="draweeBankName">提款银行</string>
        //    <string name="draweeBankAccount">提款账户</string>
        draweeBankName = datas.get(1);
        draweeBankAccount = datas.get(2);

        onBackgrounds();
    }

    // TODO 点击查看文件还没有做
    @Click(R.id.tv_fileName)
    void checkFile() {
//        ToastUtil.showToast(T_ApplyPaymentDetailsActivity.this, "查看文件");
    }

    @Override
    public void onBackgrounds() {
        // 请求获取 提款申请详情列表 http://117.78.5.212:10104/api/Funds/PaymentApply/ApplyDisplay?id=2911907
        final String URL = MyAPI.getBaseUrl() + "/api/Funds/PaymentApply/ApplyDisplay?id=" + id;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL, new Response.Listener<org.json.JSONObject>() {
            @Override
            public void onResponse(org.json.JSONObject response) {
                LogUtils.d("\n提款申请详情列表-URL:" + URL + "\n提款申请详情列表-RESPONSE:" + response.toString());

                T_ApplyPaymentListEntity T_ApplyPaymentListEntity = JSON.parseObject(response.toString(), T_ApplyPaymentListEntity.class);
                tv_applypayment_code.setText(T_ApplyPaymentListEntity.getCode());
                String source = T_ApplyPaymentListEntity.getSource();
                tv_applypayment_source.setText("1".equals(source) ? "客户自助申请" : "客服待客提款");
                tv_applypayment_statusName.setText(T_ApplyPaymentListEntity.getStatusName());
                tv_applypayment_accountName.setText(T_ApplyPaymentListEntity.getAccountName());
                tv_applypayment_costName.setText(T_ApplyPaymentListEntity.getCostName());
                tv_applypayment_costName1.setText(T_ApplyPaymentListEntity.getCostName());
                tv_applypayment_companyName.setText(T_ApplyPaymentListEntity.getCompanyName());
                tv_applypayment_bank.setText(T_ApplyPaymentListEntity.getBank());
                tv_applypayment_bankAccount.setText(T_ApplyPaymentListEntity.getBankAccount());
                tv_applypayment_titleName.setText(T_ApplyPaymentListEntity.getTitleName());
                tv_applypayment_currency.setText(T_ApplyPaymentListEntity.getCurrency());
                tv_applypayment_rmbRate.setText(T_ApplyPaymentListEntity.getRmbRate());
                tv_applypayment_remittanceFee.setText(T_ApplyPaymentListEntity.getRemittanceFee());
                tv_applypayment_actualPaymentAmount.setText(StringUtil.numberFormat(T_ApplyPaymentListEntity.getActualPaymentAmount()));
                tv_applypayment_applyPaymentAmount.setText(StringUtil.numberFormat(T_ApplyPaymentListEntity.getApplyPaymentAmount()));
                tv_applypayment_payMethodName.setText(T_ApplyPaymentListEntity.getPayMethodName());
                tv_applypayment_remark.setText(T_ApplyPaymentListEntity.getRemark());
                //  <!-- 这两个字段从 /api/Funds/PaymentApply/FindPaymentApply 获取-->
//                       <string name="draweeBankName">提款银行</string>
//                       <string name="draweeBankAccount">提款账户</string>
                tv_draweeBankName.setText(draweeBankName);
                tv_draweeBankAccount.setText(draweeBankAccount);

                // <!--<string name="取fileName字段，获取到之后，拼接一个URL，然后就打开一个网页去查看文件">相关附件(未上传)</string>-->
                String fileName = T_ApplyPaymentListEntity.getFileName();
                if (fileName != null && TextUtils.isEmpty(fileName)) {
                    tv_fileName.setText(fileName);
                } else {
                    tv_fileName.setVisibility(View.GONE);
                    tv_no_file.setVisibility(View.VISIBLE);
                }

                // TODO 少一个文件类型名（什么文件，这个字段在Log日志打印出来貌似不是）
                String customFileName = T_ApplyPaymentListEntity.getCustomFileName();
                LogUtils.d("customFileName" + customFileName);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                LogUtils.e("请求错误:" + error.toString());
                hideProgressbar();
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put(AppDelegate.QS_LOGIN, MyApplication_.getInstance().getUserInfoSp().getString(AppDelegate.LOGIN_NAME, ""));
                return map;
            }
        };

        //  请求获取 水单拆分,返回的是JsonArray http://117.78.5.212:10104/api/Funds/PaymentApply/FindSlipOrder?applyId=2911907
        final String URL1 = MyAPI.getBaseUrl() + "/api/Funds/PaymentApply/FindSlipOrder?applyId=" + id;
        JsonArrayRequest jsonarrayrequest = new JsonArrayRequest(Request.Method.GET, URL1, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                LogUtils.d("\n水单拆分-URL:" + URL1 + "\n水单拆分-RESPONSE:" + response.toString());

                final List<T_ApplyPaymentWaterSplitEntity> applyPaymentWaterSplitEntities = JSON.parseArray(response.toString(), T_ApplyPaymentWaterSplitEntity.class);
                if (applyPaymentWaterSplitEntities.size() == 0) {
                    ll_water_split.setVisibility(View.GONE);
                    tv_no_water_slip_information.setVisibility(View.VISIBLE);
                } else if (applyPaymentWaterSplitEntities.size() == 1) {
                    T_ApplyPaymentWaterSplitEntity T_ApplyPaymentWaterSplitEntity = applyPaymentWaterSplitEntities.get(0);
                    tv_split_orderCode.setText(T_ApplyPaymentWaterSplitEntity.getOrderCode());
                    tv_split_zpAmount.setText(StringUtil.numberFormat(T_ApplyPaymentWaterSplitEntity.getZpAmount()));
                    tv_split_slipedAmount.setText(StringUtil.numberFormat(T_ApplyPaymentWaterSplitEntity.getSlipedAmount()));
                    tv_split_amount.setText(StringUtil.numberFormat(T_ApplyPaymentWaterSplitEntity.getAmount()));
                    tv_split_actualAmount.setText(StringUtil.numberFormat(T_ApplyPaymentWaterSplitEntity.getActualAmount()));
                    tv_split_slipCompleted.setText("false".equals(T_ApplyPaymentWaterSplitEntity.getSlipCompleted()) ? "否" : "是");
                } else if (applyPaymentWaterSplitEntities.size() > 1) {
                    T_ApplyPaymentWaterSplitEntity T_ApplyPaymentWaterSplitEntity = applyPaymentWaterSplitEntities.get(0);
                    tv_split_orderCode.setText(T_ApplyPaymentWaterSplitEntity.getOrderCode());
                    tv_split_zpAmount.setText(StringUtil.numberFormat(T_ApplyPaymentWaterSplitEntity.getZpAmount()));
                    tv_split_slipedAmount.setText(StringUtil.numberFormat(T_ApplyPaymentWaterSplitEntity.getSlipedAmount()));
                    tv_split_amount.setText(StringUtil.numberFormat(T_ApplyPaymentWaterSplitEntity.getAmount()));
                    tv_split_actualAmount.setText(StringUtil.numberFormat(T_ApplyPaymentWaterSplitEntity.getActualAmount()));
                    tv_split_slipCompleted.setText("false".equals(T_ApplyPaymentWaterSplitEntity.getSlipCompleted()) ? "否" : "是");

                    btn_check_water_split_information.setVisibility(View.VISIBLE);
                    btn_check_water_split_information.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // 弹出 全部水单拆分信息 对话框
                            AlertDialog.Builder builder = new AlertDialog.Builder(T_ApplyPaymentDetailsActivity.this, R.style.MyAlertDialogStyle);
                            RecyclerView mRecyclerView = (RecyclerView) LayoutInflater.from(T_ApplyPaymentDetailsActivity.this).inflate(R.layout.recyclerview, null);
                            mRecyclerView.setLayoutManager(new LinearLayoutManager(T_ApplyPaymentDetailsActivity.this));
                            MyWaterSlipAdapter mAdapter = new MyWaterSlipAdapter(applyPaymentWaterSplitEntities);
                            mRecyclerView.setAdapter(mAdapter);

                            builder.setView(mRecyclerView);
                            builder.setTitle("全部水单拆分信息")
                                    .setNegativeButton("关闭", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                        }
                                    })
                                    .create()
                                    .show();
                        }
                    });
                }

                hideProgressbar();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                LogUtils.e("请求错误:" + error.toString());
                hideProgressbar();
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
        jsonarrayrequest.setTag(this);
        mRequestQueue.add(jsonObjectRequest);
        mRequestQueue.add(jsonarrayrequest);
    }

    private void showProgressbar() {
        mProgressbar.setVisibility(View.VISIBLE);
        mLinearLayout.setVisibility(View.GONE);
    }

    private void hideProgressbar() {
        mProgressbar.setVisibility(View.GONE);
        mLinearLayout.setVisibility(View.VISIBLE);
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

    private class MyWaterSlipAdapter extends RecyclerView.Adapter<MyWaterSlipAdapter.ViewHolder> {

        private List<T_ApplyPaymentWaterSplitEntity> list;

        public MyWaterSlipAdapter(List<T_ApplyPaymentWaterSplitEntity> list) {
            this.list = list;
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.t_item_applypayment_water_split_details, viewGroup, false);
            return new ViewHolder(view);
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            public AppCompatTextView tv_count; // 水单拆分（1）
            public AppCompatTextView tv_split_orderCode;
            public AppCompatTextView tv_split_zpAmount;
            public AppCompatTextView tv_split_slipedAmount;
            public AppCompatTextView tv_split_amount;
            public AppCompatTextView tv_split_actualAmount;
            public AppCompatTextView tv_split_slipCompleted;

            public ViewHolder(final View itemView) {
                super(itemView);
                tv_count = (AppCompatTextView) itemView.findViewById(R.id.tv_count);
                tv_split_orderCode = (AppCompatTextView) itemView.findViewById(R.id.tv_split_orderCode);
                tv_split_zpAmount = (AppCompatTextView) itemView.findViewById(R.id.tv_split_zpAmount);
                tv_split_slipedAmount = (AppCompatTextView) itemView.findViewById(R.id.tv_split_slipedAmount);
                tv_split_amount = (AppCompatTextView) itemView.findViewById(R.id.tv_split_amount);
                tv_split_actualAmount = (AppCompatTextView) itemView.findViewById(R.id.tv_split_actualAmount);
                tv_split_slipCompleted = (AppCompatTextView) itemView.findViewById(R.id.tv_split_slipCompleted);

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
            T_ApplyPaymentWaterSplitEntity entity = list.get(position);
            viewHolder.tv_count.setText("水单拆分（" + (position + 1) + ")");
            viewHolder.tv_split_actualAmount.setText(StringUtil.numberFormat(entity.getActualAmount()));
            viewHolder.tv_split_orderCode.setText(entity.getOrderCode());
            viewHolder.tv_split_zpAmount.setText(StringUtil.numberFormat(entity.getZpAmount()));
            viewHolder.tv_split_slipedAmount.setText(StringUtil.numberFormat(entity.getSlipedAmount()));
            viewHolder.tv_split_amount.setText(StringUtil.numberFormat(entity.getAmount()));
            viewHolder.tv_split_slipCompleted.setText("false".equals(entity.getSlipCompleted()) ? "否" : "是");
        }

    }

    @Override
    protected void onDestroy() {
        mRequestQueue.cancelAll(this);
        super.onDestroy();
    }
}
