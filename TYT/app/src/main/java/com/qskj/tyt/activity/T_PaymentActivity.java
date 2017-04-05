package com.qskj.tyt.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.ListPopupWindow;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
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
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.apkfuns.logutils.LogUtils;
import com.qskj.tyt.AppDelegate;
import com.qskj.tyt.MyAPI;
import com.qskj.tyt.MyApplication_;
import com.qskj.tyt.R;
import com.qskj.tyt.entity.T_AccountFundBalanceEntity;
import com.qskj.tyt.utils.JsonObjectPostRequest;
import com.qskj.tyt.utils.StringUtil;
import com.qskj.tyt.utils.ToastUtil;
import com.qskj.tyt.view.AppCompatAutoCompleteClearTextView;
import com.qskj.tyt.view.AppCompatClearEditText;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * 提款操作界面
 */
@EActivity(R.layout.t_activity_payment)
public class T_PaymentActivity extends BaseActivity {

    private ArrayList<String> nameCns = new ArrayList<>();
    private SweetAlertDialog pDialog;
    private ListPopupWindow mListPop;
    private RequestQueue mRequestQueue;
    private SharedPreferences userInfoSp;
    private double generalAccount; // 普通账户余额

    @ViewById(R.id.toolbar)
    Toolbar mToolbar;

    @ViewById(R.id.tv_title)
    AppCompatTextView tv_title;

    @ViewById(R.id.tv_payee)
    AppCompatAutoCompleteClearTextView tv_payee; // 收款单位

    @ViewById(R.id.tv_receive_bank)
    AppCompatAutoCompleteClearTextView tv_receive_bank; // 收款银行

    @ViewById(R.id.tv_bankAccount)
    AppCompatAutoCompleteClearTextView tv_bankAccount; // 银行账号

    @ViewById(R.id.tv_pay_amount)
    AppCompatClearEditText tv_pay_amount; // 支付金额

    @ViewById(R.id.btn_payment)
    AppCompatButton btn_payment; // 提款按钮

    @ViewById(R.id.tv_general_account)
    AppCompatTextView tv_general_account;  // 普通账户金额

    @Override
    public void onAfterViews() {
        initToolbar();
        userInfoSp = MyApplication_.getInstance().getUserInfoSp();
        mRequestQueue = Volley.newRequestQueue(this);
        mListPop = new ListPopupWindow(this);
        onBackgrounds();
    }

    private void initToolbar() {
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        tv_title.setText(R.string.title_activity_payment);
    }

    /**
     * 提款
     */
    @Click(R.id.btn_payment)
    void prePayment() {
        String payee = tv_payee.getText().toString();
        String receive_bank = tv_receive_bank.getText().toString().trim();
        String bankAccount = tv_bankAccount.getText().toString().trim();
        String pay_amount = tv_pay_amount.getText().toString().trim();

        if (TextUtils.isEmpty(payee)) {
            ToastUtil.showToast(this, "请输入收款单位");
        } else if (TextUtils.isEmpty(receive_bank)) {
            ToastUtil.showToast(this, "请输入收款银行");
        } else if (TextUtils.isEmpty(bankAccount)) {
            ToastUtil.showToast(this, "请输入银行账号");
        } else if (TextUtils.isEmpty(pay_amount)) {
            ToastUtil.showToast(this, "请输入支付金额");
        } else if (Double.valueOf(pay_amount) > generalAccount) {
            ToastUtil.showToast(this, "支付金额超过普通账户余额");
        } else {
            pDialog = new SweetAlertDialog(T_PaymentActivity.this, SweetAlertDialog.PROGRESS_TYPE).setTitleText("正在提款...");
            pDialog.setCancelable(false);
            pDialog.show();
            payment(payee, receive_bank, bankAccount, pay_amount);
        }

    }

    /**
     * @param payee       收款单位
     * @param receiveBank 收款银行
     * @param bankAccount 银行账号
     * @param payAmount   支付金额
     */
    @Background
    void payment(String payee, String receiveBank, String bankAccount, String payAmount) {
        Map<String, String> map = new HashMap<>();
        map.put("code", "");
        map.put("accountCode", "");
        map.put("rmbRate", "1");
        map.put("paymentType", "0");
        map.put("costId", "0");
        map.put("costName", "货款");
        map.put("remittanceFee", "0");
        map.put("source", "1");
        map.put("payMethod", "183");
        map.put("payMethodName", "电汇");
        map.put("costType", "0");
        map.put("currency", "RMB");
        map.put("fundSource", "0");
        map.put(AppDelegate.ACCOUNT_ID, userInfoSp.getString(AppDelegate.ACCOUNT_ID, ""));
        map.put(AppDelegate.ACCOUNT_NAME, userInfoSp.getString(AppDelegate.ACCOUNT_NAME, ""));
        map.put(AppDelegate.TITLE_ID, String.valueOf(userInfoSp.getInt(AppDelegate.TITLE_ID, 0)));
        map.put("companyName", payee);
        map.put("bank", receiveBank);
        map.put("bankAccount", bankAccount);
        map.put("applyPaymentAmount", payAmount);
        map.put("actualPaymentAmount", payAmount);

        // 提款 POST请求 URL 中参数submit=true必须传
        final String url = MyAPI.getBaseUrl() + "/api/Funds/PaymentApply/AddApply?submit=true";
        JsonObjectPostRequest jsonObjectRequest = new JsonObjectPostRequest(url, new Response.Listener<org.json.JSONObject>() {
            @Override
            public void onResponse(org.json.JSONObject response) {
                LogUtils.d("\n提款-URL:" + url + "\n提款-RESPONSE:" + response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                LogUtils.e("请求错误:" + error.toString());
                // 因为返回的不是JsonObject而是一个 int 数据,会报错 com.android.volley.ParseError: org.json.JSONException: Value 2969419 of type java.lang.Integer cannot be converted to JSONObject
                boolean contains = error.getMessage().contains("java.lang.Integer cannot be converted to JSONObject");

                if (contains) { // 提款成功
                    pDialog.setTitleText("提款成功")
                            .setConfirmText("确定")
                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                    // 关闭本界面返回并刷新付款声请列表
                                    LocalBroadcastManager.getInstance(T_PaymentActivity.this).sendBroadcast(
                                            new Intent(AppDelegate.ACTION_T_REFRESH_APPLY_PAYMENT_LIST));
                                    finish();
                                }
                            })
                            .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                } else {  // 提款失败
                    pDialog.setTitleText("提款失败")
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
        // http://117.78.5.212:10104/api/PlatformAccounts/Company/findList
        final String accountId = userInfoSp.getString(AppDelegate.ACCOUNT_ID, "");
        final String URL = MyAPI.getBaseUrl() + "/api/PlatformAccounts/Company/findList?accountId=" + accountId;
        JsonArrayRequest requestRecieveBank = new JsonArrayRequest(Request.Method.GET, URL, new Response.Listener<org.json.JSONArray>() {
            @Override
            public void onResponse(org.json.JSONArray response) {
                LogUtils.d("\n收款银行和银行账号-URL:" + URL + "\n收款银行和银行账号-RESPONSE:" + response.toString());
                // "bank": "中国工商支","bankAccount": "113033220",
                JSONArray objects = JSON.parseArray(response.toString());
                final ArrayList<String> banks = new ArrayList<>();
                final ArrayList<String> bankAccounds = new ArrayList<>();
                final ArrayList<BankEntity> bankss = new ArrayList<>();
                for (int i = 0; i < objects.size(); i++) {
                    JSONObject jsonObject = objects.getJSONObject(i);
                    BankEntity bankEntity = new BankEntity();
                    String bank = jsonObject.getString("bank");
                    String bankAccount = jsonObject.getString("bankAccount");
                    bankEntity.setBank(bank);
                    bankEntity.setBankAccount(bankAccount);
                    banks.add(bank);
                    bankAccounds.add(bankAccount);
                    bankss.add(bankEntity);
                }

                if (banks != null) {
                    tv_receive_bank.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mListPop.setAdapter(new ArrayAdapter<>(T_PaymentActivity.this, R.layout.cb_item_listpop_search, banks));
                            mListPop.setAnchorView(tv_receive_bank);
                            mListPop.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    tv_receive_bank.setText(banks.get(position));
                                    tv_bankAccount.setText(bankAccounds.get(position));
                                    mListPop.dismiss();
                                }
                            });
                            mListPop.show();
                        }
                    });

                    tv_bankAccount.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mListPop.setAdapter(new ArrayAdapter<>(T_PaymentActivity.this, R.layout.cb_item_listpop_search, bankAccounds));
                            mListPop.setAnchorView(tv_bankAccount);
                            mListPop.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    tv_receive_bank.setText(banks.get(position));
                                    tv_bankAccount.setText(bankAccounds.get(position));
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
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put(AppDelegate.QS_LOGIN, userInfoSp.getString(AppDelegate.LOGIN_NAME, ""));
                return map;
            }
        };

        // http://117.78.5.212:10104/api/PlatformAccounts/Company/FindPagedList 收款单位
        final String URL1 = MyAPI.getBaseUrl() + "/api/PlatformAccounts/Company/FindPagedList?pageIndex=" + 1 + "&accountId=" + accountId;
        JsonObjectRequest reuqest = new JsonObjectRequest(Request.Method.GET, URL1, new Response.Listener<org.json.JSONObject>() {
            @Override
            public void onResponse(org.json.JSONObject response) {
                LogUtils.d("\n收款单位-URL:" + URL1 + "\n收款单位-RESPONSE:" + response.toString());

                JSONObject jsonObject = JSON.parseObject(response.toString());
                final int totalPage = jsonObject.getIntValue("totalPage"); // 总页数

                for (int i = 1; i <= totalPage; i++) {
                    String URL1 = MyAPI.getBaseUrl() + "/api/PlatformAccounts/Company/FindPagedList?pageIndex=" + i + "&accountId=" + accountId;
                    JsonObjectRequest reuqest1 = new JsonObjectRequest(Request.Method.GET, URL1, new Response.Listener<org.json.JSONObject>() {
                        @Override
                        public void onResponse(org.json.JSONObject response) {
                            JSONObject jsonObject = JSON.parseObject(response.toString());
                            JSONArray rows = jsonObject.getJSONArray("rows");

                            for (int j = 0; j < rows.size(); j++) {
                                String nameCn = rows.getJSONObject(j).getString("nameCn");
                                nameCns.add(nameCn);
                            }

                            LogUtils.d(nameCns);
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

                    reuqest1.setTag(this);
                    mRequestQueue.add(reuqest1);

                    if (i == totalPage && nameCns != null) {
                        tv_payee.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mListPop.setAdapter(new ArrayAdapter<>(T_PaymentActivity.this, R.layout.cb_item_listpop_search, nameCns));
                                mListPop.setAnchorView(tv_payee);
                                mListPop.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                        tv_payee.setText(nameCns.get(position));
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

        // 请求获取首页资金
        final String url = MyAPI.getBaseUrl() + "/api/Funds/FundAccount/GetAccountNameFundBalance?titleId=" + userInfoSp.getInt(AppDelegate.TITLE_ID, -1);
        JsonArrayRequest getAccountNameFundBalance = new JsonArrayRequest(Request.Method.GET, url, new Response.Listener<org.json.JSONArray>() {
            @Override
            public void onResponse(org.json.JSONArray response) {
                LogUtils.d("\n我的资金URL-" + url + "\n我的资金-RESPONSE:" + response.toString());
                //  [{"currency": "RMB","amount": 5823771.94,"fundAccountName": 1 }]
                List<T_AccountFundBalanceEntity> accountFuntBalanceEntities =
                        JSON.parseArray(response.toString(), T_AccountFundBalanceEntity.class);

                if (accountFuntBalanceEntities.size() != 0) {
                    generalAccount = accountFuntBalanceEntities.get(0).getAmount();
                    tv_general_account.setText(StringUtil.numberFormat(generalAccount));
                } else {
                    tv_general_account.setText("0.00");
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                LogUtils.e("VolleyError:" + error.toString());
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put(AppDelegate.QS_LOGIN, userInfoSp.getString(AppDelegate.LOGIN_NAME, ""));
                return map;
            }
        };

        requestRecieveBank.setTag(this);
        reuqest.setTag(this);
        getAccountNameFundBalance.setTag(this);
        mRequestQueue.add(reuqest);
        mRequestQueue.add(requestRecieveBank);
        mRequestQueue.add(getAccountNameFundBalance);
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

    private class BankEntity {

        private String bank;
        private String bankAccount;

        public BankEntity() {
        }

        public String getBank() {
            return bank;
        }

        public void setBank(String bank) {
            this.bank = bank;
        }

        public String getBankAccount() {
            return bankAccount;
        }

        public void setBankAccount(String bankAccount) {
            this.bankAccount = bankAccount;
        }
    }

}
