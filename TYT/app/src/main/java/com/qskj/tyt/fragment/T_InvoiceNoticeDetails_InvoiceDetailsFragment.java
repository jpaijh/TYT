package com.qskj.tyt.fragment;

import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.apkfuns.logutils.LogUtils;
import com.lsjwzh.widget.materialloadingprogressbar.CircleProgressBar;
import com.qskj.tyt.AppDelegate;
import com.qskj.tyt.MyAPI;
import com.qskj.tyt.MyApplication_;
import com.qskj.tyt.R;
import com.qskj.tyt.entity.T_InvoiceNoticeDetailsEntity;
import com.qskj.tyt.utils.StringUtil;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.HashMap;
import java.util.Map;

/**
 * 开票通知-通知详情
 */
@EFragment(R.layout.t_activity_invoice_notice_details)
public class T_InvoiceNoticeDetails_InvoiceDetailsFragment extends BaseFragment {

    private RequestQueue mRequestQueue;

    @ViewById(R.id.tv_details_ordercode)
    AppCompatTextView tv_details_ordercode; // 通知单号

    @ViewById(R.id.tv_details_code)
    AppCompatTextView tv_details_code; // 外销发票号

    @ViewById(R.id.tv_details_adviceDate)
    AppCompatTextView tv_details_adviceDate; // 通知时间

    @ViewById(R.id.tv_details_draweeName)
    AppCompatTextView tv_details_draweeName; // 收款单位

    @ViewById(R.id.tv_details_titleName)
    AppCompatTextView tv_details_titleName; // 付款单位

    @ViewById(R.id.tv_details_amount)
    AppCompatTextView tv_details_amount; // 总金额

    @ViewById(R.id.tv_details_address)
    AppCompatTextView tv_details_address; // 寄单地址

    @ViewById(R.id.tv_details_remark)
    AppCompatTextView tv_details_remark; // 备注

    @ViewById(R.id.tv_titleName)
    AppCompatTextView tv_titleName; // 名称

    @ViewById(R.id.tv_address)
    AppCompatTextView tv_address; // 地址

    @ViewById(R.id.tv_phone)
    AppCompatTextView tv_phone; // 电话

    @ViewById(R.id.tv_taxCode)
    AppCompatTextView tv_taxCode; // 税号

    @ViewById(R.id.tv_bank)
    AppCompatTextView tv_bank; // 开户行

    @ViewById(R.id.tv_bankAccount)
    AppCompatTextView tv_bankAccount; // 银行账号

    @ViewById(R.id.recyclerview)
    RecyclerView mRecyclerView;

    @ViewById(R.id.nestedScrollView)
    NestedScrollView mNestedScrollView;

    @ViewById(R.id.progressbar)
    CircleProgressBar mProgressBar;

    @ViewById(R.id.tv_no_data)
    AppCompatTextView tv_no_data;

    @AfterViews
    public void onAfterViews() {
        mRequestQueue = Volley.newRequestQueue(getActivity());
        showView(mProgressBar);
        hideView(mNestedScrollView);
        onBackgrounds();
    }

    @Override
    public void onBackgrounds() {
        // 开票通知-通知详情
        final String url = MyAPI.getBaseUrl() + "/api/Funds/InvoiceNotice/GetInvoiceNoticeById?id=" + getArguments().getInt("id");
        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, new Response.Listener<org.json.JSONObject>() {
            @Override
            public void onResponse(org.json.JSONObject response) {
                LogUtils.d("\n开票通知-通知详情-URL:" + url + "\n开票通知-通知详情-RESPONSE:" + response.toString());

                T_InvoiceNoticeDetailsEntity entity = JSON.parseObject(response.toString(), T_InvoiceNoticeDetailsEntity.class);

                if (entity == null) {
                    showView(tv_no_data);
                    hideView(mProgressBar);
                    return;
                }

                tv_details_ordercode.setText(entity.getOrderCode());
                tv_details_code.setText(entity.getCode());
                tv_details_adviceDate.setText(StringUtil.dateRemoveT(entity.getAdviceDate()));
                tv_details_draweeName.setText(entity.getDraweeName());
                tv_details_titleName.setText(entity.getTitleName());
                tv_details_amount.setText(StringUtil.numberFormat(entity.getAmount()));
                tv_details_address.setText(entity.getAddress());

                Object remark = entity.getRemark();
                if (remark != null)
                    tv_details_remark.setText((String) remark);

                tv_titleName.setText(entity.getTitleName());
                tv_address.setText(entity.getAddress());
                tv_phone.setText(entity.getPhone());
                tv_taxCode.setText(entity.getTaxCode());

                Object bank = entity.getBank();
                if (bank != null)
                    tv_bank.setText((String) bank);

                Object bankAccount = entity.getBankAccount();
                if (bankAccount != null)
                    tv_bankAccount.setText((String) bankAccount);

                showView(mNestedScrollView);
                hideView(mProgressBar);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mProgressBar.setVisibility(View.VISIBLE);
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

    @Override
    public void onDestroyView() {
        mRequestQueue.cancelAll(this);
        super.onDestroyView();
    }

}
