package com.qskj.tyt.activity;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.ListPopupWindow;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.alibaba.fastjson.JSON;
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
import com.qskj.tyt.MyApplication;
import com.qskj.tyt.MyApplication_;
import com.qskj.tyt.R;
import com.qskj.tyt.adapter.ListpopAdapter;
import com.qskj.tyt.entity.UserLoginEntity;
import com.qskj.tyt.utils.DeviceUtil;
import com.qskj.tyt.utils.NetUtil;
import com.qskj.tyt.utils.ToastUtil;
import com.qskj.tyt.view.AppCompatAutoCompleteClearTextView;
import com.qskj.tyt.view.AppCompatClearEditText;
import com.umeng.analytics.MobclickAgent;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;


/**
 * 登录界面
 */
@EActivity(R.layout.activity_login)
public class LoginActivity extends BaseActivity {

    private static final String TAG = "LoginActivity";
    private String name;
    private String pwd;
    private ListPopupWindow mListPop;
    private ProgressDialog mProgressDialog;
    private RequestQueue mRequestQueue;
    private SharedPreferences sp_UserInfo;
    private SharedPreferences.Editor edit_UserInfo;
    private Set<String> userNamesSet;

    @ViewById(R.id.tv_user_name)
    AppCompatAutoCompleteClearTextView tv_user_name;

    @ViewById(R.id.et_password)
    AppCompatClearEditText et_password;

    @ViewById(R.id.btn_login)
    AppCompatButton btn_login;

    @ViewById(R.id.toolbar)
    Toolbar mToolbar;

    @ViewById(R.id.tv_title)
    AppCompatTextView tv_title;

    @ViewById(R.id.nested_scroll_view)
    NestedScrollView mScrollView;

    @Override
    public void onAfterViews() {
        initToolbar();
        mRequestQueue = Volley.newRequestQueue(this);
        mListPop = new ListPopupWindow(this);
        sp_UserInfo = MyApplication_.getInstance().getUserInfoSp();
        edit_UserInfo = sp_UserInfo.edit();
        initInputBox();
    }

    private void initToolbar() {
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        tv_title.setText(getStrings(R.string.title_activity_login) + "-" + MyApplication_.getInstance().getPlatFormString());
    }

    private void initInputBox() {
        tv_user_name.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                changeScrollView();
                return false;
            }
        });

        et_password.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                changeScrollView();
                return false;
            }
        });

        tv_user_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (et_password.length() != 0 && tv_user_name.length() != 0) {
                    setBtnEnable();
                } else {
                    setBtnUnEnable();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        et_password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (et_password.length() != 0 && tv_user_name.length() != 0) {
                    setBtnEnable();
                } else {
                    setBtnUnEnable();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        userNamesSet = sp_UserInfo.getStringSet(AppDelegate.USER_NAMES + MyApplication_.getInstance().getPlatFormIntKey(), null);
        if (MyApplication.IS_RELEASE_VERSION && userNamesSet != null) {
            // 正式版本：用户名输入提示,不需要去保存密码，安全问题
            ArrayList<String> arrayList = new ArrayList<>(userNamesSet);
            tv_user_name.setAdapter(new ListpopAdapter(this, R.layout.cb_item_listpop_with_delete, R.id.tv_text, arrayList,
                    edit_UserInfo, AppDelegate.USER_NAMES + MyApplication_.getInstance().getPlatFormIntKey()));
        } else if (userNamesSet != null) {
            // Debug版本：点击提示，选择账号并会自动输入密码（方便调试）
            final ArrayList<String> arrayList = new ArrayList<>(userNamesSet);
            tv_user_name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListPop.setAdapter(new ArrayAdapter<>(LoginActivity.this, R.layout.cb_item_listpop_search, arrayList));
                    mListPop.setAnchorView(tv_user_name);
                    mListPop.setInputMethodMode(ListPopupWindow.POSITION_PROMPT_ABOVE);
                    mListPop.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            tv_user_name.setText(arrayList.get(position));
                            String password = sp_UserInfo.getString(arrayList.get(position) + AppDelegate.PASSWORD, "");
                            et_password.setText(password);
                            DeviceUtil.hideSoft(LoginActivity.this, tv_user_name);
                            mListPop.dismiss();
                        }
                    });
                    mListPop.show();
                }
            });
        }

    }

    /**
     * 登录
     */
    @Click(R.id.btn_login)
    void login() {
        DeviceUtil.hideSoft(this, et_password);

        if (NetUtil.isNetworkConnected(this)) {
            name = tv_user_name.getText().toString().trim();
            pwd = et_password.getText().toString().trim();
            showProgressDialog();
            onBackgrounds();
        } else {
            ToastUtil.showToast(this, "网络连接不可用，请检查网络");
        }

    }

    @Override
    public void onBackgrounds() {
        final String url_login = MyAPI.getBaseUrl() + "/api/Permission/User/Login?name=" + name + "&pwd=" + pwd;
        final JsonObjectRequest userLoginRequest = new JsonObjectRequest(Request.Method.POST, url_login, new Response.Listener<org.json.JSONObject>() {
            @Override
            public void onResponse(org.json.JSONObject response) {
                LogUtils.d("\n登录-URL:" + url_login + "\n登录-RESPONSE:" + response.toString());

                final UserLoginEntity userLoginEntity = JSON.parseObject(response.toString(), UserLoginEntity.class);
                // 登陆成功标记设置为true,每次进入App先判断这个标记，若是已经登陆过状态，就直接进入MainActivity
                edit_UserInfo.putBoolean(AppDelegate.IS_LOGIN, true).commit();
                // 登陆成功：保存用户名
                if (userNamesSet == null) {
                    Set<String> stringSets = new TreeSet<>();
                    stringSets.add(name);
                    edit_UserInfo.putStringSet(AppDelegate.USER_NAMES + MyApplication_.getInstance().getPlatFormIntKey(), stringSets).commit();
                } else {
                    userNamesSet.add(name);
                }
                // 登陆成功：保存密码
                edit_UserInfo.putString(name + AppDelegate.PASSWORD, pwd).commit();

                // 登陆成功：保存其他参数
                edit_UserInfo.putString(AppDelegate.LOGIN_NAME, userLoginEntity.getLoginName()).commit(); // QS-LOGIN,很多请求需要这个参数
                edit_UserInfo.putString(AppDelegate.ACCOUNT_NAME, userLoginEntity.getAccountName()).commit();// 用户名
                edit_UserInfo.putString(AppDelegate.LG_NAME, name).commit();// 登录名
                edit_UserInfo.putString(AppDelegate.PASSWORD, pwd).commit();  // 登陆密码
                edit_UserInfo.putString(AppDelegate.EMAIL, userLoginEntity.getEmail()).commit();  // 邮箱
                edit_UserInfo.putString(AppDelegate.MOBILE, userLoginEntity.getMobile()).commit();// 手机号码
                edit_UserInfo.putString(AppDelegate.ACCOUNT_ID, userLoginEntity.getPltAccountId()).commit();// pltAccountId（就是accountId）
                edit_UserInfo.putString(AppDelegate.PLT_ACCOUNT_NAME, userLoginEntity.getPltAccountName()).commit(); // 账户名（平台）
                LogUtils.d("登录-UserLoginEntity:" + userLoginEntity);

                // 跨境平台还需要这个请求：需获取用户详细信息里面的accountNature字段，通过这个字段就知道是不同的人物从而主页创建不同的模块
                int platFormIntKey = MyApplication_.getInstance().getPlatFormIntKey();
                if (platFormIntKey == AppDelegate.PLATFORM_CROSS_BORDER) {
                    final String url_getUserInfoDetails = MyAPI.getBaseUrl() + "/api/Permission/User/getUser?id=" + userLoginEntity.getLoginName();
                    JsonObjectRequest userInfoDetailRequest = new JsonObjectRequest(Request.Method.GET, url_getUserInfoDetails, new Response.Listener<org.json.JSONObject>() {
                        @Override
                        public void onResponse(org.json.JSONObject response) {
                            LogUtils.d("\n登录-URL:" + url_getUserInfoDetails + "\n登录-RESPONSE:" + response.toString());

                            JSONObject jsonObject = JSON.parseObject(response.toString());
                            int accountNature = jsonObject.getIntValue(AppDelegate.ACCOUNT_NATURE);
                            edit_UserInfo.putInt(AppDelegate.ACCOUNT_NATURE, accountNature).commit();

                            mProgressDialog.dismiss();
                            MainActivity_.intent(LoginActivity.this).start();
                            // 当用户使用自有账号登录时，可以这样统计：账号统计
                            MobclickAgent.onProfileSignIn(userLoginEntity.getLoginName());
                            finish();
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            LogUtils.e("请求错误:" + error.toString());
                            mProgressDialog.dismiss();
                        }
                    }) {
                        @Override
                        public Map<String, String> getHeaders() throws AuthFailureError {
                            Map<String, String> map = new HashMap<>();
                            map.put("QS-LOGIN", userLoginEntity.getLoginName());
                            return map;
                        }
                    };

                    userInfoDetailRequest.setTag(this);
                    mRequestQueue.add(userInfoDetailRequest);

                } else if (platFormIntKey == AppDelegate.PLATFORM_TRADING) { // TitleID只在代理外贸平台用到
                    final String url_titleID = MyAPI.getBaseUrl() + "/api/platformAccounts/Title/FindTitle";
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url_titleID, new Response.Listener<org.json.JSONObject>() {
                        @Override
                        public void onResponse(org.json.JSONObject response) {
                            LogUtils.d("\n我的TitleId-URL:" + url_titleID + "\n我的TitleId-RESPONSE:" + response.toString());

                            String rows = JSON.parseObject(response.toString()).getJSONArray("rows").toString();
                            if (!rows.equals("[]")) {
                                // 第一次进入应用，存入第一个实体的TitleID
                                JSONObject jsonObject = JSON.parseObject(JSON.parseArray(rows).get(0).toString());
                                edit_UserInfo.putInt(AppDelegate.TITLE_ID, jsonObject.getIntValue(AppDelegate.ID)).commit();
                            }

                            mProgressDialog.dismiss();
                            MainActivity_.intent(LoginActivity.this).start();
                            // 当用户使用自有账号登录时，可以这样统计：账号统计
                            MobclickAgent.onProfileSignIn(userLoginEntity.getLoginName());
                            finish();

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            LogUtils.e("我的TitleId请求错误:" + error.toString());
                        }
                    }) {
                        @Override
                        public Map<String, String> getHeaders() throws AuthFailureError {
                            Map<String, String> map = new HashMap<>();
                            map.put(AppDelegate.QS_LOGIN, userLoginEntity.getLoginName());
                            return map;
                        }
                    };

                    jsonObjectRequest.setTag(this);
                    mRequestQueue.add(jsonObjectRequest);

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                LogUtils.e("请求错误:" + error.toString());
                ToastUtil.showToast(LoginActivity.this, "用户名密码错误");
                setBtnUnEnable();
                mProgressDialog.dismiss();
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("Content-Type", "application/x-www-form-urlencoded");
                return map;
            }
        };

        userLoginRequest.setTag(this);
        mRequestQueue.add(userLoginRequest);

    }

    private void setBtnEnable() {
        btn_login.setEnabled(true);
        btn_login.setBackgroundResource(R.drawable.selector_btn_login_tyt);
    }

    private void setBtnUnEnable() {
        btn_login.setEnabled(false);
        btn_login.setBackgroundResource(R.drawable.shape_rect_login_tint_tyt);
    }

    /**
     * 让ScrollView向上移
     */
    private void changeScrollView() {
        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mScrollView.smoothScrollTo(0, mScrollView.getChildAt(0).getHeight());
            }
        }, 100);
    }

    private void showProgressDialog() {
        mProgressDialog = new ProgressDialog(this, R.style.MyProgressDialogStyle);
        mProgressDialog.setMessage("正在登录...");
        mProgressDialog.show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onPageStart(TAG);
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd(TAG);
        MobclickAgent.onPause(this);
    }

    @Override
    protected void onDestroy() {
        mRequestQueue.cancelAll(this);
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
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

}
