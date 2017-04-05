package com.qskj.tyt.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.apkfuns.logutils.LogUtils;
import com.qskj.tyt.AppDelegate;
import com.qskj.tyt.MyAPI;
import com.qskj.tyt.MyApplication;
import com.qskj.tyt.MyApplication_;
import com.qskj.tyt.R;
import com.qskj.tyt.utils.DeviceUtil;
import com.qskj.tyt.utils.ToastUtil;
import com.qskj.tyt.view.AppCompatClearEditText;
import com.umeng.analytics.MobclickAgent;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.HashMap;
import java.util.Map;

/**
 * 修改密码界面
 */
@EActivity(R.layout.activity_modify_password)
public class ModifyPasswordActivity extends BaseActivity {

    private static final String TAG = "ModifyPasswordActivity";

    private SharedPreferences userInfoSp;
    private RequestQueue mRequestQueue;
    private String loginPassword; // 当前登录密码
    private String originalPassword; // 当前密码
    private String newPassword; // 新密码

    @ViewById(R.id.et_original_password)
    AppCompatClearEditText et_original_password;

    @ViewById(R.id.et_new_password)
    AppCompatClearEditText et_new_password;

    @ViewById(R.id.et_ensure_new_password)
    AppCompatClearEditText et_ensure_new_password;

    @ViewById(R.id.btn_ensure_modify)
    AppCompatButton btn_ensure_modify;

    @ViewById(R.id.til_original_password)
    TextInputLayout til_original_password;

    @ViewById(R.id.til_new_password)
    TextInputLayout til_new_password;

    @ViewById(R.id.til_ensure_new_password)
    TextInputLayout til_ensure_new_password;

    @ViewById(R.id.tv_title)
    AppCompatTextView tv_title;

    @ViewById(R.id.toolbar)
    Toolbar mToolbar;

    @ViewById(R.id.tv_lgName)
    AppCompatTextView tv_lgName;

    @ViewById(R.id.tv_platformName)
    AppCompatTextView tv_platformName;

    @AfterViews
    public void onAfterViews() {
        mRequestQueue = Volley.newRequestQueue(this);
        userInfoSp = MyApplication.getInstance().getUserInfoSp();
        loginPassword = userInfoSp.getString(AppDelegate.PASSWORD, "");
        initToolbar();
        initEditText();
        tv_lgName.setText("用户登录名：" + userInfoSp.getString(AppDelegate.LG_NAME, ""));
        tv_platformName.setText("登录平台名：" + MyApplication_.getInstance().getPlatFormString());
    }

    private void initToolbar() {
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        tv_title.setText(R.string.title_activity_modify_password);
    }

    private void initEditText() {
        et_new_password.setEnabled(false);
        et_ensure_new_password.setEnabled(false);

        et_original_password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String original_password = et_original_password.getText().toString().trim();
                if (original_password.length() == 0) {
                    til_original_password.setErrorEnabled(true);
                    til_original_password.setError("当前登录密码不能为空！");
                } else if (!original_password.equals(loginPassword)) {
                    til_original_password.setErrorEnabled(true);
                    til_original_password.setError("当前登录密码不正确，请重新输入！");
                } else {
                    et_original_password.setEnabled(false);
                    // TODO 设置EditText获取焦点并自动弹出软键盘
                    et_new_password.setEnabled(true);
                    til_original_password.setError(null);
                    til_original_password.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        et_new_password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String new_password = et_new_password.getText().toString().trim();
                String ensure_new_password = et_ensure_new_password.getText().toString().trim();
                if (new_password.length() == 0) {
                    til_new_password.setErrorEnabled(true);
                    til_new_password.setError("新登录密码不能为空！");
                    et_ensure_new_password.setEnabled(false);
                    if (til_ensure_new_password.getError() != null) {
                        til_ensure_new_password.setError(null);
                        til_ensure_new_password.setErrorEnabled(false);
                    }
                    setBtnUnEnable();
                } else if (new_password.equals(loginPassword)) {
                    til_new_password.setErrorEnabled(true);
                    til_new_password.setError("新登录密码和当前登录密码一致，请重新设置！");
                    et_ensure_new_password.setEnabled(false);
                    if (til_ensure_new_password.getError() != null) {
                        til_ensure_new_password.setError(null);
                        til_ensure_new_password.setErrorEnabled(false);
                    }
                    setBtnUnEnable();
                } else if (ensure_new_password.length() == 0) {
                    til_new_password.setError(null);
                    til_new_password.setErrorEnabled(false);
                    et_ensure_new_password.setEnabled(true);
                    setBtnUnEnable();
                } else if (!new_password.equals(ensure_new_password)) {
                    til_new_password.setError(null);
                    til_new_password.setErrorEnabled(false);
                    til_ensure_new_password.setEnabled(true);
                    til_ensure_new_password.setError("两次新登录密码不一致，请重新输入！");
                    et_ensure_new_password.setEnabled(true);
                    setBtnUnEnable();
                } else if (new_password.equals(ensure_new_password)) {
                    til_new_password.setError(null);
                    til_new_password.setErrorEnabled(false);
                    et_ensure_new_password.setEnabled(true);
                    til_ensure_new_password.setError(null);
                    til_ensure_new_password.setErrorEnabled(false);
                    setBtnEnable();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        et_ensure_new_password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String new_password = et_new_password.getText().toString().trim();
                String ensure_new_password = et_ensure_new_password.getText().toString().trim();
                if (ensure_new_password.length() == 0) {
                    til_ensure_new_password.setErrorEnabled(true);
                    til_ensure_new_password.setError("确认新登录密码不能为空！");
                    setBtnUnEnable();
                } else if (!ensure_new_password.equals(new_password)) {
                    til_ensure_new_password.setErrorEnabled(true);
                    til_ensure_new_password.setError("两次新登录密码不一致，请重新输入！");
                    setBtnUnEnable();
                } else if (til_ensure_new_password.getError() != null && til_new_password.getError() != null) {
                    til_ensure_new_password.setError(null);
                    til_ensure_new_password.setErrorEnabled(false);
                    setBtnUnEnable();
                } else {
                    til_ensure_new_password.setError(null);
                    til_ensure_new_password.setErrorEnabled(false);
                    setBtnEnable();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

    }

    private void setBtnEnable() {
        btn_ensure_modify.setEnabled(true);
        btn_ensure_modify.setBackgroundResource(R.drawable.selector_btn_login_tyt);
    }

    private void setBtnUnEnable() {
        btn_ensure_modify.setEnabled(false);
        btn_ensure_modify.setBackgroundResource(R.drawable.shape_rect_login_tint_tyt);
    }

    /**
     * 确认修改
     */
    @Click(R.id.btn_ensure_modify)
    void modifyPassword() {
        DeviceUtil.hideSoft(this, et_ensure_new_password);

        originalPassword = et_original_password.getText().toString().trim();
        newPassword = et_new_password.getText().toString().trim();
        onBackgrounds();
    }

    @Override
    public void onBackgrounds() {
        String url = MyAPI.getBaseUrl() + "/api/Permission/User/InitPassword";
        final String userId = userInfoSp.getString(AppDelegate.LOGIN_NAME, "");

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if ("true".equals(response)) { // 修改密码成功
                    userInfoSp.edit().putBoolean(AppDelegate.IS_LOGIN, false).commit();
                    ToastUtil.showToast(ModifyPasswordActivity.this, "登录密码修改成功，请重新登陆");
                    LoginActivity_.intent(ModifyPasswordActivity.this).flags(Intent.FLAG_ACTIVITY_NEW_TASK).start();
                    // 跳转登陆界面必须要把之前的界面全部Finish掉，不然按返回键还是会回到之前界面,目前退出登录需要关掉的界面：自身 和 MainActivity
                    finish();
                    MyApplication_.getInstance().exit();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                LogUtils.e("请求错误:" + error.toString());
                ToastUtil.showToast(ModifyPasswordActivity.this, "登录密码修改失败，请稍后再试");
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("userId", userId);
                map.put("originalPassword", originalPassword);
                map.put("newPassword", newPassword);
                return map;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put(AppDelegate.QS_LOGIN, userId);
                return map;
            }
        };

        stringRequest.setTag(this);
        mRequestQueue.add(stringRequest);
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
        super.onDestroy();
        mRequestQueue.cancelAll(this);
    }

}
