package com.qskj.tyt.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatDialogFragment;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.ListPopupWindow;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

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
import com.appeaser.sublimepickerlibrary.datepicker.SelectedDate;
import com.appeaser.sublimepickerlibrary.helpers.SublimeOptions;
import com.appeaser.sublimepickerlibrary.recurrencepicker.SublimeRecurrencePicker;
import com.qskj.tyt.AppDelegate;
import com.qskj.tyt.MyAPI;
import com.qskj.tyt.MyApplication_;
import com.qskj.tyt.R;
import com.qskj.tyt.adapter.ListpopAdapter;
import com.qskj.tyt.fragment.SublimePickerFragment;
import com.qskj.tyt.utils.DateUtil;
import com.qskj.tyt.view.AppCompatAutoCompleteClearTextView;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


/**
 * 账户搜素界面
 */
@EActivity(R.layout.t_activity_account_search)
public class T_AccountSearchActivity extends BaseActivity {

    private ListPopupWindow mListPop;
    private int clickTag;
    private String[] listAmountRange = new String[]{"自定义", "0-10万", "10-50万", "50-100万", "100-500万", "500万以上"};
    private ArrayList<String> listContacts = new ArrayList<>();
    private SharedPreferences userInfoSp;
    private RequestQueue mRequestQueue;

    @ViewById(R.id.acct_sale_invoice)
    AppCompatAutoCompleteClearTextView acct_sale_invoice; // 外销发票号

    @ViewById(R.id.acct_start_date)
    AppCompatAutoCompleteClearTextView acct_start_date; // 起止时间

    @ViewById(R.id.acct_end_date)
    AppCompatAutoCompleteClearTextView acct_end_date; // 结束时间

    @ViewById(R.id.acct_amount_range)
    AppCompatEditText acct_amount_range; // 金额范围

    @ViewById(R.id.acct_min_amount)
    AppCompatAutoCompleteClearTextView acct_min_amount; // 最小金额

    @ViewById(R.id.acct_max_amount)
    AppCompatAutoCompleteClearTextView acct_max_amount; // 最大金额

    @ViewById(R.id.acct_contacts)
    AppCompatEditText acct_contacts; // 往来方

    @ViewById(R.id.tv_title)
    AppCompatTextView tv_title;

    @ViewById(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    public void onAfterViews() {
        initToolbar();
        userInfoSp = MyApplication_.getInstance().getUserInfoSp();
        mRequestQueue = Volley.newRequestQueue(this);
        mListPop = new ListPopupWindow(this);
        onBackgrounds();
        initView();
    }

    public void initToolbar() {
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        tv_title.setText(R.string.title_activity_account_search);
    }

    private void initView() {
        // 设置外销发票号搜索的历史记录数据
        SharedPreferences searchHistorySp = MyApplication_.getInstance().getSearchHistorySp();
        Set<String> saleInvoiceHistory = searchHistorySp.getStringSet(AppDelegate.HISTORY_SALE_INVOICE_ACCOUNT_SEARCH, null);
        if (saleInvoiceHistory != null) {
            ArrayList<String> arrayList = new ArrayList<>(saleInvoiceHistory);
            acct_sale_invoice.setAdapter(new ListpopAdapter(this, R.layout.cb_item_listpop_with_delete, R.id.tv_text, arrayList,
                    searchHistorySp.edit(), AppDelegate.HISTORY_SALE_INVOICE_ACCOUNT_SEARCH));
        }

        acct_contacts.setText("所有");
        acct_contacts.setInputType(InputType.TYPE_NULL);
        acct_amount_range.setText("自定义");
        acct_amount_range.setInputType(InputType.TYPE_NULL);
        acct_start_date.setInputType(InputType.TYPE_NULL);
        acct_end_date.setInputType(InputType.TYPE_NULL);

        acct_amount_range.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListPop.setAdapter(new ArrayAdapter<>(T_AccountSearchActivity.this, R.layout.cb_item_listpop_search, listAmountRange));
                mListPop.setAnchorView(acct_amount_range);
                mListPop.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        acct_amount_range.setText(listAmountRange[position]);

                        switch (position) {
                            case 0: // "自定义"
                                acct_min_amount.setText("");
                                acct_max_amount.setText("");
                                acct_min_amount.setEnabled(true);
                                acct_max_amount.setEnabled(true);
                                break;
                            case 1: // "0-10万"
                                acct_min_amount.setText("0.00");
                                acct_max_amount.setText("100,000.00");
                                acct_min_amount.setEnabled(false);
                                acct_max_amount.setEnabled(false);
                                break;
                            case 2: //"10-50万"
                                acct_min_amount.setText("100,000.00");
                                acct_max_amount.setText("500,000.00");
                                acct_min_amount.setEnabled(false);
                                acct_max_amount.setEnabled(false);
                                break;
                            case 3: // "50-100万"
                                acct_min_amount.setText("500,000.00");
                                acct_max_amount.setText("1,000,000.00");
                                acct_min_amount.setEnabled(false);
                                acct_max_amount.setEnabled(false);
                                break;
                            case 4: // "100-500万"
                                acct_min_amount.setText("1,000,000.00");
                                acct_max_amount.setText("5,000,000.00");
                                acct_min_amount.setEnabled(false);
                                acct_max_amount.setEnabled(false);
                                break;
                            case 5: // "500万以上"
                                acct_min_amount.setText("5,000,000.00");
                                acct_max_amount.setText("");
                                acct_min_amount.setEnabled(false);
                                acct_max_amount.setEnabled(false);
                                break;
                        }

                        mListPop.dismiss();
                    }
                });
                mListPop.show();
            }
        });

        acct_start_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickTag = AppDelegate.CLICK_START_DATE;
                createSublimePicker();
            }
        });

        acct_end_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickTag = AppDelegate.CLICK_END_DATE;
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
        options.setCanPickDateRange(true); // 设置长按拖动进行日期范围选择

        Bundle bundle = new Bundle();
        bundle.putParcelable("SUBLIME_OPTIONS", options);
        pickerFrag.setArguments(bundle);

        pickerFrag.setStyle(AppCompatDialogFragment.STYLE_NO_TITLE, 0);
        pickerFrag.show(getSupportFragmentManager(), "SUBLIME_PICKER");

    }

    SublimePickerFragment.Callback mFragmentCallback = new SublimePickerFragment.Callback() {
        // 取消
        @Override
        public void onCancelled() {
        }

        // 确定
        @Override
        public void onDateTimeRecurrenceSet(SelectedDate selectedDate, int hourOfDay, int minute,
                                            SublimeRecurrencePicker.RecurrenceOption recurrenceOption, String recurrenceRule) {

            if (selectedDate != null) {
                String startDate = DateUtil.dateToStr(selectedDate.getStartDate().getTime());
                if (clickTag == AppDelegate.CLICK_START_DATE && selectedDate.getType() == SelectedDate.Type.SINGLE) {
                    acct_start_date.setText(startDate);
                } else if (clickTag == AppDelegate.CLICK_END_DATE && selectedDate.getType() == SelectedDate.Type.SINGLE) {
                    acct_end_date.setText(startDate);
                } else if (selectedDate.getType() == SelectedDate.Type.RANGE) {
                    acct_start_date.setText(startDate);
                    acct_end_date.setText(DateUtil.dateToStr(selectedDate.getEndDate().getTime()));
                }
            }

        }
    };

    /**
     * 搜索
     */
    @Click(R.id.btn_search)
    void search() {
        try {
            // 往来方
            String relatedCompanyName = URLEncoder.encode(acct_contacts.getText().toString().trim(), "utf-8");
            // 外销发票号
            String orderCode = acct_sale_invoice.getText().toString().trim();
            // 开始时间
            String startDate = acct_start_date.getText().toString().trim();
            // 结束时间
            String endDate = acct_end_date.getText().toString().trim();
            // 资金范围
            String mAmountRange = acct_amount_range.getText().toString().trim();
            String minAmount = "";
            String maxAmount = "";
            switch (mAmountRange) {
                case "自定义":
                    minAmount = acct_min_amount.getText().toString().trim();
                    maxAmount = acct_max_amount.getText().toString().trim();
                    break;
                case "0-10万":
                    minAmount = "0";
                    maxAmount = "100000";
                    break;
                case "10-50万":
                    minAmount = "100000";
                    maxAmount = "500000";
                    break;
                case "50-100万":
                    minAmount = "500000";
                    maxAmount = "1000000";
                    break;
                case "100-500万":
                    minAmount = "1000000";
                    maxAmount = "5000000";
                    break;
                case "500万以上":
                    minAmount = "5000000";
                    maxAmount = "";
                    break;
            }

            String arguments;
            if (relatedCompanyName.equals(URLEncoder.encode("所有", "utf-8"))) { // 如果往来方是所有就不要传
                arguments = "&orderCode=" + orderCode
                        + "&startDate=" + startDate
                        + "&endDate=" + endDate
                        + "&minAmount=" + minAmount
                        + "&maxAmount=" + maxAmount;
            } else {
                arguments = "&orderCode=" + orderCode
                        + "&startDate=" + startDate
                        + "&endDate=" + endDate
                        + "&relatedCompanyName=" + relatedCompanyName
                        + "&minAmount=" + minAmount
                        + "&maxAmount=" + maxAmount;
            }

            int account_type = getIntent().getIntExtra("ACCOUNT_TYPE", 0); // 默认为0，普通账户
            if (account_type == 0) {
                Intent intent = new Intent(AppDelegate.ACTION_T_GENERAL_ACCOUNT_SEARCH);
                intent.putExtra(AppDelegate.ACTION_T_ACCOUNT_SEARCH, arguments);
                intent.putExtra(AppDelegate.KEYWORD, orderCode);
                LocalBroadcastManager.getInstance(T_AccountSearchActivity.this).sendBroadcast(intent);
            } else if (account_type == 1) {
                Intent intent = new Intent(AppDelegate.ACTION_T_FINANCING_ACCOUNT_SEARCH);
                intent.putExtra(AppDelegate.ACTION_T_ACCOUNT_SEARCH, arguments);
                intent.putExtra(AppDelegate.KEYWORD, orderCode);
                LocalBroadcastManager.getInstance(T_AccountSearchActivity.this).sendBroadcast(intent);
            }

            finish();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onBackgrounds() {
        final String url = MyAPI.getBaseUrl() + "/api/PlatformAccounts/Company/FindPagedList?pageIndex=1&accountId=" +
                userInfoSp.getString(AppDelegate.ACCOUNT_ID, "");
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, new Response.Listener<org.json.JSONObject>() {
            @Override
            public void onResponse(org.json.JSONObject response) {
                LogUtils.d("\n往来单位-URL:" + url + "\n往来单位-RESPONSE:" + response.toString());

                JSONObject jsonObject = JSON.parseObject(response.toString());
                final int totalPage = jsonObject.getIntValue("totalPage");

                for (int i = 1; i <= totalPage; i++) {
                    String URL1 = MyAPI.getBaseUrl() + "/api/PlatformAccounts/Company/FindPagedList?pageIndex=" + i + "&accountId=" +
                            userInfoSp.getString(AppDelegate.ACCOUNT_ID, "");
                    JsonObjectRequest request1 = new JsonObjectRequest(Request.Method.GET, URL1, new Response.Listener<org.json.JSONObject>() {
                        @Override
                        public void onResponse(org.json.JSONObject response) {
                            JSONObject jsonObject = JSON.parseObject(response.toString());
                            JSONArray rows = jsonObject.getJSONArray("rows");

                            for (int j = 0; j < rows.size(); j++) {
                                String nameCn = rows.getJSONObject(j).getString("nameCn");
                                listContacts.add(nameCn);
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

                    request1.setTag(this);
                    mRequestQueue.add(request1);

                    if (i == totalPage && listContacts != null) {
                        acct_contacts.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mListPop.setAdapter(new ArrayAdapter<>(T_AccountSearchActivity.this, R.layout.cb_item_listpop_search, listContacts));
                                mListPop.setAnchorView(acct_contacts);
                                mListPop.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                        acct_contacts.setText(listContacts.get(position));
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

        request.setTag(this);
        mRequestQueue.add(request);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mRequestQueue.cancelAll(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_reset, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.action_reset:
                resetData();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 重置所有数据
     */
    private void resetData() {
        acct_contacts.setText("所有");
        acct_sale_invoice.setText("");
        acct_start_date.setText("");
        acct_end_date.setText("");
        acct_amount_range.setText("自定义");
    }

}
