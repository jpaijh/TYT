package com.qskj.tyt.activity;
import android.content.DialogInterface;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
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
import com.qskj.tyt.entity.MessageCenterEntity;
import com.qskj.tyt.utils.StringUtil;
import com.umeng.analytics.MobclickAgent;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 消息中心
 */
@EActivity(R.layout.swiperefreshlayout_recyclerview_toolbar_tvnodata)
public class MessageCenterActivity extends BaseActivity {

    private static final String TAG = "MessageCenterActivity";

    private static final String P_PHONE_NUMBER = "((\\(\\d{3,4}\\)|\\d{3,4}-|\\s)?\\d{7,14}|10086|10010|10000)";
    private static final String P_ORDER_NUMBER = "((\\(\\d{3,4}\\)|\\d{3,4}-|\\s)?\\d{7,18})";
    private Pattern mCompiledPatternNumber = Pattern.compile(P_PHONE_NUMBER);
    private Pattern mCompiledPatternOrderNumber = Pattern.compile(P_ORDER_NUMBER);
    private List<Pattern> mPatterns = new ArrayList<>();

    {
        mPatterns.add(mCompiledPatternNumber);
        mPatterns.add(mCompiledPatternOrderNumber);
    }

    private MyMessageCenterAdapter mAdapter;
    private List<MessageCenterEntity.RowsEntity> rowsEntityList;
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

    @ViewById(R.id.toolbar)
    Toolbar mToolbar;

    @ViewById(R.id.tv_title)
    AppCompatTextView tv_title;

    @ViewById(R.id.tv_no_data)
    AppCompatTextView tv_no_data;

    public void initToolbar() {
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        tv_title.setText(R.string.title_activity_message_center);
    }

    @Override
    public void onAfterViews() {
        initToolbar();
        mRequestQueue = Volley.newRequestQueue(this);
        initRecyclerView();
        initSwipeRefreshLayout();
    }

    private void initRecyclerView() {
        mLinearLayoutManager = new LinearLayoutManager(this);
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
        // 请求获取 消息中心 我的消息 http://192.168.64.222:19199/api/Messages/Message/GetMessages
        final String url = MyAPI.getBaseUrl() + "/api/Messages/Message/GetMessages?pageSize=10&pageIndex=" + pageIndex;
        final JsonObjectRequest messageRequest = new JsonObjectRequest(Request.Method.GET, url, new Response.Listener<org.json.JSONObject>() {
            @Override
            public void onResponse(org.json.JSONObject response) {
                LogUtils.d("\n消息中心-URL:" + url + "\n消息中心-RESPONSE:" + response.toString());

                JSONObject jsonObject = JSON.parseObject(response.toString());
                int total = jsonObject.getIntValue("total");
                totalPage = jsonObject.getIntValue("totalPage");

                if (total == 0) {
                    tv_no_data.setVisibility(View.VISIBLE);
                } else {
                    tv_no_data.setVisibility(View.GONE);
                }

                if (total != 0 && pageIndex == 1) {
                    JSONArray rows = jsonObject.getJSONArray("rows");
                    rowsEntityList = JSON.parseArray(rows.toString(), MessageCenterEntity.RowsEntity.class);
                    mAdapter = new MyMessageCenterAdapter(rowsEntityList);
                    mRecyclerView.setAdapter(mAdapter);
                    if (!isFirstLoading)
                        createRefreshCompleteSnackbar(mRecyclerView);
                } else if (pageIndex <= totalPage) {
                    JSONArray rows = jsonObject.getJSONArray("rows");
                    rowsEntityList.addAll(lastVisibleItem + 1, JSON.parseArray(rows.toString(), MessageCenterEntity.RowsEntity.class));
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
                map.put("QS-LOGIN", MyApplication_.getInstance().getUserInfoSp().getString(AppDelegate.LOGIN_NAME, ""));
                return map;
            }
        };

        messageRequest.setTag(this);
        mRequestQueue.add(messageRequest);
    }

    private class MyMessageCenterAdapter extends RecyclerView.Adapter<MyMessageCenterAdapter.ViewHolder> {

        private List<MessageCenterEntity.RowsEntity> list;

        public MyMessageCenterAdapter(List<MessageCenterEntity.RowsEntity> list) {
            this.list = list;
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_message_center_list, viewGroup, false);
            return new ViewHolder(view);
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            public RelativeLayout rl_content;
            public TextView tv_title; // 标题
            public TextView tv_content; // 内容
            public TextView tv_sendDate; // 发送时间
            public TextView tv_creatorName; // 发件人
            public ImageView img_message; // 发件人

            public ViewHolder(final View itemView) {
                super(itemView);
                tv_title = (TextView) itemView.findViewById(R.id.tv_title);
                tv_content = (TextView) itemView.findViewById(R.id.tv_content);
                tv_sendDate = (TextView) itemView.findViewById(R.id.tv_sendDate);
                tv_creatorName = (TextView) itemView.findViewById(R.id.tv_creatorName);
                img_message = (ImageView) itemView.findViewById(R.id.img_message);
                rl_content = (RelativeLayout) itemView.findViewById(R.id.rl_content);
                rl_content.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        MessageCenterEntity.RowsEntity rowsEntity = list.get(getLayoutPosition());

                        SpannableString message = changeToLight(Html.fromHtml(rowsEntity.getContent()).toString());
                        AlertDialog.Builder builder = new AlertDialog.Builder(MessageCenterActivity.this, R.style.MyAlertDialogStyle);
                        builder.setTitle(rowsEntity.getTypeName())
                                .setMessage(message)
                                .setNegativeButton("关闭", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                })
                                .create()
                                .show();

                        if (!rowsEntity.getReaded()) {
                            // TODO: 2015/9/23 请求网络 修改为 已读状态
                            rowsEntity.setReaded(true);
                            mAdapter.notifyDataSetChanged();
                        }

                    }
                });
            }
        }

        @Override
        public void onBindViewHolder(ViewHolder viewHolder, int position) {
            MessageCenterEntity.RowsEntity entity = list.get(position);

            viewHolder.tv_content.setText(Html.fromHtml(entity.getContent()));
            viewHolder.tv_title.setText(entity.getTitle());
            viewHolder.tv_sendDate.setText(StringUtil.dateRemoveT(entity.getSendDate()));
            viewHolder.tv_creatorName.setText(entity.getCreatorName());
            if (entity.getReaded()) {
                viewHolder.img_message.setImageResource(R.mipmap.ic_read_message);
                viewHolder.tv_title.setTextColor(getColors(R.color.read_gray));
                viewHolder.tv_creatorName.setTextColor(getColors(R.color.read_gray));
                viewHolder.tv_content.setTextColor(getColors(R.color.read_gray));
                viewHolder.tv_sendDate.setTextColor(getColors(R.color.read_gray));
            } else {
                viewHolder.img_message.setImageResource(R.mipmap.ic_new_message);
                viewHolder.tv_title.setTextColor(getColors(R.color.red));
                viewHolder.tv_creatorName.setTextColor(getColors(R.color.new_message));
                viewHolder.tv_content.setTextColor(getColors(R.color.new_message));
                viewHolder.tv_sendDate.setTextColor(getColors(R.color.new_message));
            }

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

    /**
     * 解析URL和电话号码并高亮显示
     */
    private SpannableString changeToLight(String str) {
        SpannableString string = new SpannableString(str);
        for (Pattern p : mPatterns) {
            Matcher m = p.matcher(string);
            while (m.find()) {
                int start = m.start();
                int end = m.end();
                string.setSpan(new ClickableSpan() {
                    @Override
                    public void updateDrawState(TextPaint ds) {
                        super.updateDrawState(ds);
                        ds.setColor(getColors(R.color.color_theme));
                    }

                    @Override
                    public void onClick(View widget) {

                    }

                }, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }
        return string;
    }

    @Override
    public void onDestroy() {
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
