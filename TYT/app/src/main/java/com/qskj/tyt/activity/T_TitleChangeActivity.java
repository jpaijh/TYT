package com.qskj.tyt.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

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
import com.lsjwzh.widget.materialloadingprogressbar.CircleProgressBar;
import com.qskj.tyt.AppDelegate;
import com.qskj.tyt.MyAPI;
import com.qskj.tyt.MyApplication_;
import com.qskj.tyt.R;
import com.qskj.tyt.entity.T_TitleChangeEntity;
import com.umeng.analytics.MobclickAgent;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 抬头切换界面
 */
@EActivity(R.layout.recyclerview_toolbar)
public class T_TitleChangeActivity extends BaseActivity {

    private static final String TAG = "T_TitleChangeActivity";
    private RequestQueue mRequestQueue;
    private SharedPreferences userInfoSp;
    private MyAdapter mAdapter;
    private ArrayList<T_TitleChangeEntity.RowsEntity> listRowsEntity = new ArrayList<>();

    @ViewById(R.id.recyclerview)
    RecyclerView mRecyclerView;

    @ViewById(R.id.toolbar)
    Toolbar mToolbar;

    @ViewById(R.id.tv_title)
    AppCompatTextView tv_title;

    @ViewById(R.id.tv_no_data)
    AppCompatTextView tv_no_data;

    @ViewById(R.id.progressbar)
    CircleProgressBar mProgressBar;

    @Override
    public void onAfterViews() {
        initToolbar();
        mRequestQueue = Volley.newRequestQueue(this);
        userInfoSp = MyApplication_.getInstance().getUserInfoSp();
        initRecyclerView();
        showView(mProgressBar);
        onBackgrounds();
    }

    private void initRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(this)
                .marginResId(R.dimen.divider_margin_left, R.dimen.divider_margin_right) // 设置分割线距离两端距离
//                .color(Color.RED)  // 设置分割线颜色
//                .sizeResId(R.dimen.divider) // 设置分割线粗细
//                .drawable(R.drawable.abc_list_pressed_holo_light) // 设置分割线背景
                .build());
    }

    private void initToolbar() {
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        tv_title.setText(R.string.title_activity_title_change_Activity);
    }

    @Override
    public void onBackgrounds() {
        String url = MyAPI.getBaseUrl() + "/api/platformAccounts/Title/FindTitle?disable=false";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, new Response.Listener<org.json.JSONObject>() {
            @Override
            public void onResponse(org.json.JSONObject response) {

                JSONObject jsonObject = JSON.parseObject(response.toString());
                int total = jsonObject.getIntValue("total");
                int totalPage = jsonObject.getIntValue("totalPage");

                if (total != 0) {
                    hideView(tv_no_data);

                    for (int i = 1; i <= totalPage; i++) {
                        final String URL1 = MyAPI.getBaseUrl() + "/api/platformAccounts/Title/FindTitle?disable=false&pageIndex=" + i;
                        JsonObjectRequest request1 = new JsonObjectRequest(Request.Method.GET, URL1, new Response.Listener<org.json.JSONObject>() {
                            @Override
                            public void onResponse(org.json.JSONObject response) {
                                LogUtils.d("\n我的TitleId-URL:" + URL1 + "\n我的TitleId-RESPONSE:" + response.toString());

                                JSONObject jsonObject = JSON.parseObject(response.toString());
                                JSONArray rows = jsonObject.getJSONArray("rows");

                                for (int j = 0; j < rows.size(); j++) {
                                    List<T_TitleChangeEntity.RowsEntity> rowsEntities = JSON.parseArray(rows.toString(), T_TitleChangeEntity.RowsEntity.class);
                                    listRowsEntity.add(rowsEntities.get(j));
                                }

                                // 第一次进入代理平台LoginActivity会获取一次titleID,并存起来，根据存的值去初始化勾选一个抬头
                                int titleId = userInfoSp.getInt(AppDelegate.TITLE_ID, 0);
                                for (int i = 0; i < listRowsEntity.size(); i++) {
                                    if (listRowsEntity.get(i).getId() == titleId) {
                                        listRowsEntity.get(i).setChecked(true);
                                    }
                                }

                                if (mAdapter == null)
                                    mAdapter = new MyAdapter(listRowsEntity);
                                mRecyclerView.setAdapter(mAdapter);

                                hideView(mProgressBar);
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

                    }
                } else {
                    hideView(mProgressBar);
                    showView(tv_no_data);
                }

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
                map.put(AppDelegate.QS_LOGIN, userInfoSp.getString(AppDelegate.LOGIN_NAME, ""));
                return map;
            }
        };

        request.setTag(this);
        mRequestQueue.add(request);

    }

    private class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

        private List<T_TitleChangeEntity.RowsEntity> list;

        public MyAdapter(List<T_TitleChangeEntity.RowsEntity> list) {
            this.list = list;
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.t_item_title_change_tv_with_checkbox, viewGroup, false);
            return new ViewHolder(view);
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            public AppCompatTextView tv_titleName;
            public AppCompatCheckBox checkbox;

            public ViewHolder(View itemView) {
                super(itemView);
                tv_titleName = (AppCompatTextView) itemView.findViewById(R.id.tv_titleName);
                checkbox = (AppCompatCheckBox) itemView.findViewById(R.id.checkbox);

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) { // 第一次安装，默认勾选第一个抬头，点击切换抬头，并保存抬头发送通知让用到titleId的地方更新数据
                        final T_TitleChangeEntity.RowsEntity itemEntity = list.get(getLayoutPosition());
                        AlertDialog.Builder builder = new AlertDialog.Builder(T_TitleChangeActivity.this, R.style.MyAlertDialogStyle);
                        builder.setMessage("确定切换抬头至“" + itemEntity.getName() + "”?")
                                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        list.get(getLayoutPosition()).setChecked(true);
                                        for (int i = 0; i < list.size(); i++) {
                                            if (i != getLayoutPosition() && list.get(i).isChecked()) {
                                                list.get(i).setChecked(false);
                                            }
                                        }

                                        notifyDataSetChanged();
                                        userInfoSp.edit().putInt(AppDelegate.TITLE_ID, itemEntity.getId()).commit();
                                        LocalBroadcastManager.getInstance(T_TitleChangeActivity.this).sendBroadcast(new Intent(AppDelegate.ACTION_UPDATE_AMOUNT));
                                    }
                                })
                                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                }).create().show();
                    }
                });
            }
        }

        @Override
        public void onBindViewHolder(ViewHolder viewHolder, int position) {
            viewHolder.tv_titleName.setText(list.get(position).getName());
            boolean checked = list.get(position).isChecked();
            if (checked) {
                showView(viewHolder.checkbox);
            } else {
                viewHolder.checkbox.setVisibility(View.INVISIBLE);
            }
            viewHolder.checkbox.setChecked(checked);
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
    protected void onDestroy() {
        mRequestQueue.cancelAll(this);
        super.onDestroy();
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

}
