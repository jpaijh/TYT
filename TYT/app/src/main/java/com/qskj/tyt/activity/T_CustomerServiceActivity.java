package com.qskj.tyt.activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.text.ClipboardManager;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
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
import com.lsjwzh.widget.materialloadingprogressbar.CircleProgressBar;
import com.qskj.tyt.AppDelegate;
import com.qskj.tyt.MyAPI;
import com.qskj.tyt.MyApplication_;
import com.qskj.tyt.R;
import com.qskj.tyt.utils.ToastUtil;
import com.umeng.analytics.MobclickAgent;


import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.HashMap;
import java.util.Map;

/**
 * 专属客服
 */
@EActivity(R.layout.t_activity_customer_service)
public class T_CustomerServiceActivity extends BaseActivity {

    private static final String TAG = "T_CustomerServiceActivity";

    private String clerkMobile;
    private String clerkName;
    private RequestQueue mRequestQueue;
    private SharedPreferences userInfoSp;

    @ViewById(R.id.cardView)
    CardView mCardView;

    @ViewById(R.id.tv_clerkName)
    AppCompatTextView tv_clerkName;

    @ViewById(R.id.tv_clerkMobile)
    AppCompatTextView tv_clerkMobile;

    @ViewById(R.id.tv_clerkTelephone)
    AppCompatTextView tv_clerkTelephone;

    @ViewById(R.id.tv_clerkEmail)
    AppCompatTextView tv_clerkEmail;

    @ViewById(R.id.tv_clerkQq)
    AppCompatTextView tv_clerkQq;

    @ViewById(R.id.progressbar)
    CircleProgressBar mProgressbar;

    @ViewById(R.id.tv_title)
    AppCompatTextView tv_title;

    @ViewById(R.id.tv_no_data)
    AppCompatTextView tv_no_data;

    @ViewById(R.id.toolbar)
    Toolbar mToolbar;

    public void initToolbar() {
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        tv_title.setText(R.string.title_activity_customer_service);
    }

    @AfterViews
    public void onAfterViews() {
        initToolbar();
        mRequestQueue = Volley.newRequestQueue(this);
        userInfoSp = MyApplication_.getInstance().getUserInfoSp();
        showView(mProgressbar);
        hideView(mCardView);
        onBackgrounds();
    }

    @Override
    public void onBackgrounds() {
        final String URL = MyAPI.getBaseUrl() + "/api/PlatformAccounts/Account/GetAccount?id=" + userInfoSp.getString(AppDelegate.ACCOUNT_ID, "");
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL, new Response.Listener<org.json.JSONObject>() {
            @Override
            public void onResponse(org.json.JSONObject response) {
                LogUtils.d("\n专属客服-URL:" + URL + "\n专属客服-RESPONSE:" + response.toString());
                // "clerk": { "clerkId": 2761505,"clerkName": "刘玲","clerkMobile": "11111","clerkTelephone": "","clerkEmail": null, "clerkQq": "" }
                JSONObject jsonObject = JSON.parseObject(response.toString());
                JSONObject clerk = jsonObject.getJSONObject("clerk");
                clerkName = clerk.getString("clerkName");
                clerkMobile = clerk.getString("clerkMobile");
                String clerkTelephone = clerk.getString("clerkTelephone");
                String clerkEmail = clerk.getString("clerkEmail");
                String clerkQq = clerk.getString("clerkQq");

                tv_clerkName.setText(clerkName);
                tv_clerkMobile.setText(clerkMobile);
                tv_clerkTelephone.setText(clerkTelephone);
                tv_clerkEmail.setText(clerkEmail);
                tv_clerkQq.setText(clerkQq);

                // 只要有一项信息不为空就 显示
                if (!TextUtils.isEmpty(clerkName) || !TextUtils.isEmpty(clerkMobile)
                        || !TextUtils.isEmpty(clerkTelephone) || !TextUtils.isEmpty(clerkEmail) || !TextUtils.isEmpty(clerkQq)) {
                    showView(mCardView);
                } else {
                    tv_no_data.setText("暂无专属客服");
                    showView(tv_no_data);
                }

                hideView(mProgressbar);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                LogUtils.e("请求错误:" + error.toString());
                mProgressbar.setVisibility(View.GONE);
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

    @Click(R.id.rl_content)
    void createDialog() {
        if (!TextUtils.isEmpty(clerkMobile) && !TextUtils.isEmpty(clerkName)) {
            String[] strings = {"拨打", "新建联系人", "复制号码"};
            AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.MyAlertDialogStyle);
            builder.setTitle(TextUtils.isEmpty(clerkName) ? clerkMobile : clerkName + "：" + clerkMobile)
                    .setItems(strings, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            switch (which) {
                                case 0: // 直接拨打电话
                                    Intent callNumber = new Intent(Intent.ACTION_CALL);
                                    callNumber.setData(Uri.parse("tel:" + clerkMobile));
                                    if (ActivityCompat.checkSelfPermission(T_CustomerServiceActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                                        return;
                                    }
                                    startActivity(callNumber);
                                    break;
                                case 1: // 新建联系人
                                    Intent newContact = new Intent(Intent.ACTION_INSERT);
                                    newContact.setType("vnd.android.cursor.dir/person");
                                    newContact.setType("vnd.android.cursor.dir/contact");
                                    newContact.setType("vnd.android.cursor.dir/raw_contact");
                                    newContact.putExtra(ContactsContract.Intents.Insert.PHONE, clerkMobile);
                                    newContact.putExtra(ContactsContract.Intents.Insert.NAME, clerkName);
                                    startActivity(newContact);
                                    break;
                                case 2: // 复制号码
                                    ClipboardManager clip = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                                    clip.setText(clerkMobile);
                                    ToastUtil.showToast(getApplicationContext(), "复制成功");
                                    break;
                            }
                        }
                    })
                    .setNegativeButton("关闭", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }).create().show();
        }
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
        mRequestQueue.cancelAll(this);
        super.onDestroy();
    }

}
