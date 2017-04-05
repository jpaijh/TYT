package com.qskj.tyt.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatDialogFragment;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.ListPopupWindow;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.apkfuns.logutils.LogUtils;
import com.appeaser.sublimepickerlibrary.datepicker.SelectedDate;
import com.appeaser.sublimepickerlibrary.helpers.SublimeOptions;
import com.appeaser.sublimepickerlibrary.recurrencepicker.SublimeRecurrencePicker;
import com.qskj.tyt.AppDelegate;
import com.qskj.tyt.MyAPI;
import com.qskj.tyt.MyApplication_;
import com.qskj.tyt.R;
import com.qskj.tyt.fragment.SublimePickerFragment;
import com.qskj.tyt.utils.DateUtil;
import com.qskj.tyt.utils.JsonObjectPostRequest;
import com.qskj.tyt.utils.ToastUtil;
import com.qskj.tyt.view.AppCompatAutoCompleteClearTextView;
import com.qskj.tyt.view.AppCompatClearEditText;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * 新建收汇通知界面
 */
@EActivity(R.layout.t_activity_new_notice_foreign_exchange)
public class T_NewNoticeForeignExchangeActivity extends BaseActivity {

    private ArrayList<ReceiptTypeEntity> receiptTypes = new ArrayList<>();
    private ArrayList<String> receiptTypeNames = new ArrayList<>();
    private String receiptTypeValue;
    private ArrayList<String> foreignNames = new ArrayList<>();
    private ArrayList<String> currencys = new ArrayList<>();
    private SweetAlertDialog pDialog;
    private ListPopupWindow mListPop;
    private RequestQueue mRequestQueue;
    private SharedPreferences userInfoSp;
    private String accountId;

    @ViewById(R.id.toolbar)
    Toolbar mToolbar;

    @ViewById(R.id.tv_title)
    AppCompatTextView tv_title;

    @ViewById(R.id.tv_receiptDate)
    AppCompatTextView tv_receiptDate; // 付汇日期

    @ViewById(R.id.ll_receiptDate)
    LinearLayout ll_receiptDate; // 付汇日期

    @ViewById(R.id.tv_receiptType)
    AppCompatTextView tv_receiptType; // 付汇类型

    @ViewById(R.id.ll_receiptType)
    LinearLayout ll_receiptType; // 付汇类型

    @ViewById(R.id.tv_saleOrder)
    AppCompatAutoCompleteClearTextView tv_saleOrder; // 外销发票号

    @ViewById(R.id.tv_foreignName)
    AppCompatTextView tv_foreignName; // 外商

    @ViewById(R.id.ll_foreignName)
    LinearLayout ll_foreignName; // 外商

    @ViewById(R.id.et_receiptBank)
    AppCompatClearEditText et_receiptBank; // 付汇银行

    @ViewById(R.id.tv_currency)
    AppCompatTextView tv_currency;  // 币别

    @ViewById(R.id.ll_currency)
    LinearLayout ll_currency; // 币别

    @ViewById(R.id.et_amount)
    AppCompatClearEditText et_amount;  // 金额

    @ViewById(R.id.et_customerRemark)
    AppCompatClearEditText et_customerRemark;  // 备注

    @Override
    public void onAfterViews() {
        initToolbar();
        initView();
        userInfoSp = MyApplication_.getInstance().getUserInfoSp();
        accountId = userInfoSp.getString(AppDelegate.ACCOUNT_ID, "");
        mRequestQueue = Volley.newRequestQueue(this);
        mListPop = new ListPopupWindow(this);
        onBackgrounds();
    }

    private void initToolbar() {
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        tv_title.setText("创建水单通知");
    }

    private void initView() {
        ll_receiptDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createSublimePicker();
            }
        });

    }

    private void createSublimePicker() {
        SublimePickerFragment pickerFrag = new SublimePickerFragment();
        pickerFrag.setCallback(mFragmentCallback);

        SublimeOptions options = new SublimeOptions();
        options.setDisplayOptions(SublimeOptions.ACTIVATE_DATE_PICKER); // 设置显示什么选择器（日期，时间）
        options.setPickerToShow(SublimeOptions.Picker.DATE_PICKER); // 设置显示什么选择器（日期，时间）
        options.setCanPickDateRange(false); // 设置长按拖动进行日期范围选择

        Bundle bundle = new Bundle();
        bundle.putParcelable("SUBLIME_OPTIONS", options);
        pickerFrag.setArguments(bundle);

        pickerFrag.setStyle(AppCompatDialogFragment.STYLE_NO_TITLE, 0);
        pickerFrag.show(getSupportFragmentManager(), "SUBLIME_PICKER");

    }

    SublimePickerFragment.Callback mFragmentCallback = new SublimePickerFragment.Callback() {

        @Override
        public void onCancelled() {
        }

        @Override
        public void onDateTimeRecurrenceSet(SelectedDate selectedDate, int hourOfDay, int minute,
                                            SublimeRecurrencePicker.RecurrenceOption recurrenceOption, String recurrenceRule) {

            if (selectedDate != null) {
                String startDate = DateUtil.dateToStr(selectedDate.getStartDate().getTime());
                if (selectedDate.getType() == SelectedDate.Type.SINGLE) { // 单选
                    tv_receiptDate.setText(startDate);
                }
            }

        }
    };

    @Click(R.id.btn_notice)
    void clickNotice() {
        String receiptDate = tv_receiptDate.getText().toString().trim();
        String foreignName = tv_foreignName.getText().toString().trim();

        if (TextUtils.isEmpty(receiptDate)) {
            ToastUtil.showToast(this, "请选择付汇日期");
        } else if (TextUtils.isEmpty(receiptTypeValue)) {
            ToastUtil.showToast(this, "请选择付汇类型");
        } else if (TextUtils.isEmpty(foreignName)) {
            ToastUtil.showToast(this, "请选择外商");
        } else {
            String saleOrder = tv_saleOrder.getText().toString().trim();
            String receiptBank = et_receiptBank.getText().toString().trim();
            String currency = tv_currency.getText().toString().trim();
            String amount = et_amount.getText().toString().trim();
            String customerRemark = et_customerRemark.getText().toString().trim();

            pDialog = new SweetAlertDialog(T_NewNoticeForeignExchangeActivity.this, SweetAlertDialog.PROGRESS_TYPE).setTitleText("正在创建水单通知...");
            pDialog.setCancelable(false);
            pDialog.show();
            notice(receiptDate, foreignName, receiptTypeValue, saleOrder, receiptBank, currency, amount, customerRemark);
        }

    }

    @Background
    void notice(String receiptDate, String foreignName, String receiptType, String saleOrder,
                String receiptBank, String currency, String amount, String customerRemark) {
        Map<String, String> map = new HashMap<>();
        map.put("amount", amount); //金额
        map.put("currency", currency);  //币别
        map.put("remark", customerRemark); //备注
        map.put("foreignName", foreignName); //外商
        map.put("receiptBank", receiptBank); //银行
        map.put("receiptDate", receiptDate); //付汇日期
        map.put("saleOrder", saleOrder); //外销发票号
        map.put("receiptType", receiptType); //付汇类型ID
        map.put("status", "1"); //固定传1

        final String url = MyAPI.getBaseUrl() + "/api/Funds/BankSlipNotice/Add?submit=true";
        LogUtils.d("\n创建水单通知-URL:" + url);
        JsonObjectPostRequest jsonObjectRequest = new JsonObjectPostRequest(url, new Response.Listener<org.json.JSONObject>() {
            @Override
            public void onResponse(org.json.JSONObject response) {
                LogUtils.d("\n创建水单通知-URL:" + url + "\n创建水单通知-RESPONSE:" + response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                LogUtils.e("请求错误:" + error.toString());
                // 因为返回的不是JsonObject而是一个 int 数据,会报错 com.android.volley.ParseError: org.json.JSONException: Value 2969419 of type java.lang.Integer cannot be converted to JSONObject
                boolean contains = error.getMessage().contains("java.lang.Integer cannot be converted to JSONObject");

                if (contains) {
                    pDialog.setTitleText("通知成功")
                            .setConfirmText("确定")
                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                    LocalBroadcastManager.getInstance(T_NewNoticeForeignExchangeActivity.this).sendBroadcast(
                                            new Intent(AppDelegate.ACTION_T_REFRESH_NOTICE_FOREIGN_EXCHANGE_LIST));
                                    finish();
                                }
                            })
                            .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                } else {  // 提款失败
                    pDialog.setTitleText("通知失败")
                            .setContentText("请稍后再试...")
                            .setConfirmText("确定")
                            .changeAlertType(SweetAlertDialog.ERROR_TYPE);
                }
            }
        }, map) {
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

    @Override
    public void onBackgrounds() {
        // 付汇类型
        String URL_ReceiptType = MyAPI.getBaseUrl() + "/api/Resource/Common/FindMiscDataList?category=PAYMENT_WAY&pageIndex=1";
        JsonObjectRequest requestReceiptType = new JsonObjectRequest(Request.Method.GET, URL_ReceiptType, new Response.Listener<org.json.JSONObject>() {
            @Override
            public void onResponse(org.json.JSONObject response) {
                JSONObject jsonObject = JSON.parseObject(response.toString());
                final int totalPage = jsonObject.getIntValue("totalPage"); // 总页数

                for (int i = 1; i <= totalPage; i++) {
                    final String URL_ReceiptType_Paging = MyAPI.getBaseUrl() + "/api/Resource/Common/FindMiscDataList?category=PAYMENT_WAY&pageIndex=" + i;
                    JsonObjectRequest requestReceiptTypePaging = new JsonObjectRequest(Request.Method.GET, URL_ReceiptType_Paging, new Response.Listener<org.json.JSONObject>() {
                        @Override
                        public void onResponse(org.json.JSONObject response) {
                            LogUtils.d("\n付汇类型-URL:" + URL_ReceiptType_Paging + "\n付汇类型-RESPONSE:" + response.toString());
                            JSONObject jsonObject = JSON.parseObject(response.toString());
                            JSONArray rows = jsonObject.getJSONArray("rows");

                            for (int j = 0; j < rows.size(); j++) {
                                String name = rows.getJSONObject(j).getString("name");
                                String value = rows.getJSONObject(j).getString("value");
                                receiptTypeNames.add(name);
                                receiptTypes.add(new ReceiptTypeEntity(name, value));
                            }

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            LogUtils.e("请求错误:" + error.toString());
                        }
                    });

                    requestReceiptTypePaging.setTag(this);
                    mRequestQueue.add(requestReceiptTypePaging);

                    if (i == totalPage && receiptTypes != null) {
                        ll_receiptType.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mListPop.setAdapter(new ArrayAdapter<>(T_NewNoticeForeignExchangeActivity.this, R.layout.cb_item_listpop_search, receiptTypeNames));
                                mListPop.setAnchorView(tv_receiptType);
                                mListPop.setVerticalOffset(30);
                                mListPop.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                        tv_receiptType.setText(receiptTypeNames.get(position));
                                        receiptTypeValue = receiptTypes.get(position).getValue();
                                        mListPop.dismiss();
                                    }
                                });
                                mListPop.show();
                            }
                        });
                    }
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                LogUtils.e("请求错误:" + error.toString());
            }
        });

        requestReceiptType.setTag(this);
        mRequestQueue.add(requestReceiptType);

        // 外商
        String urlContactsCompanyForeign = MyAPI.getBaseUrl() + "/api/PlatformAccounts/Company/FindPagedList?type=1&pageIndex=1&accountId=" + accountId;
        JsonObjectRequest requestContactsCompanyForeign = new JsonObjectRequest(Request.Method.GET, urlContactsCompanyForeign, new Response.Listener<org.json.JSONObject>() {
            @Override
            public void onResponse(org.json.JSONObject response) {
                JSONObject jsonObject = JSON.parseObject(response.toString());
                final int totalPage = jsonObject.getIntValue("totalPage");

                for (int i = 1; i <= totalPage; i++) {
                    final String url = MyAPI.getBaseUrl() + "/api/PlatformAccounts/Company/FindPagedList?type=1&accountId=" + accountId + "&pageIndex=" + i;
                    JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, new Response.Listener<org.json.JSONObject>() {
                        @Override
                        public void onResponse(org.json.JSONObject response) {
                            LogUtils.d("\n外商-URL:" + url + "\n外商-RESPONSE:" + response.toString());
                            JSONObject jsonObject = JSON.parseObject(response.toString());
                            JSONArray rows = jsonObject.getJSONArray("rows");

                            for (int j = 0; j < rows.size(); j++) {
                                String nameCns = rows.getJSONObject(j).getString("nameCn");
                                foreignNames.add(nameCns);
                            }

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

                    request.setTag(this);
                    mRequestQueue.add(request);

                    if (i == totalPage && foreignNames != null) {
                        ll_foreignName.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mListPop.setAdapter(new ArrayAdapter<>(T_NewNoticeForeignExchangeActivity.this, R.layout.cb_item_listpop_search, foreignNames));
                                mListPop.setAnchorView(tv_foreignName);
                                mListPop.setVerticalOffset(30);
                                mListPop.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                        tv_foreignName.setText(foreignNames.get(position));
                                        mListPop.dismiss();
                                    }
                                });
                                mListPop.show();
                            }
                        });
                    }
                }

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

        requestContactsCompanyForeign.setTag(this);
        mRequestQueue.add(requestContactsCompanyForeign);

        // 币别
        final String url = MyAPI.getBaseUrl() + "/api/Resource/Common/GetCurrency?keyWord=";
        JsonArrayRequest requestCurrency = new JsonArrayRequest(Request.Method.GET, url, new Response.Listener<org.json.JSONArray>() {
            @Override
            public void onResponse(org.json.JSONArray response) {
                LogUtils.d("\n币别-URL:" + url + "\n币别-RESPONSE:" + response.toString());

                JSONArray objects = JSONArray.parseArray(response.toString());

                for (int j = 0; j < objects.size(); j++) {
                    String codes = objects.getJSONObject(j).getString("code");
                    currencys.add(codes);
                }


                if (currencys != null) {
                    tv_currency.setText(currencys.get(0)); // 默认设置为USD
                    ll_currency.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mListPop.setAdapter(new ArrayAdapter<>(T_NewNoticeForeignExchangeActivity.this, R.layout.cb_item_listpop_search, currencys));
                            mListPop.setAnchorView(tv_currency);
                            mListPop.setVerticalOffset(30);
                            mListPop.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    tv_currency.setText(currencys.get(position));
                                    mListPop.dismiss();
                                }
                            });
                            mListPop.show();
                        }
                    });
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                LogUtils.e("请求错误:" + error.toString());
            }
        });

        requestCurrency.setTag(this);
        mRequestQueue.add(requestCurrency);

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
    protected void onDestroy() {
        mRequestQueue.cancelAll(this);
        super.onDestroy();
    }

    private class ReceiptTypeEntity {

        private String name;
        private String value;

        public ReceiptTypeEntity(String name, String value) {
            this.name = name;
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

}
