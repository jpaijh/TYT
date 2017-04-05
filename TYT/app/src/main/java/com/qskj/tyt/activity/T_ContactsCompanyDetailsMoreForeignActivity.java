package com.qskj.tyt.activity;

import android.content.SharedPreferences;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

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
import com.qskj.tyt.entity.T_ContactsCompanyDetailsMoreEntity;
import com.qskj.tyt.entity.T_ContactsCompanyEntity;
import com.qskj.tyt.utils.StringUtil;
import com.qskj.tyt.view.AlwaysMarqueeTextView;
import com.umeng.analytics.MobclickAgent;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 往来单位-列表详情-更多-国外：收汇信息
 */
@EActivity(R.layout.t_contacts_company_details_more)
public class T_ContactsCompanyDetailsMoreForeignActivity extends BaseActivity {

    private static final String TAG = "T_ContactsCompanyDetailsMoreForeignActivity";
    private T_ContactsCompanyEntity.RowsEntity rowsEntity;
    private List<T_ContactsCompanyDetailsMoreEntity.RowsEntity> rowsEntityList;
    private MyAdapter mAdapter;
    private int lastVisibleItem;
    private int totalPage;
    private int pageIndex = 1;
    private boolean isLoading;
    private boolean isFirstLoading;
    private LinearLayoutManager mLinearLayoutManager;
    private RequestQueue mRequestQueue;
    private SharedPreferences userInfoSp;

    @ViewById(R.id.progressbar)
    CircleProgressBar mProgressbar;

    @ViewById(R.id.tv_no_data)
    AppCompatTextView tv_no_data;

    @ViewById(R.id.tv_title)
    AppCompatTextView tv_title;

    @ViewById(R.id.toolbar)
    Toolbar mToolbar;

    @ViewById(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    @ViewById(R.id.recyclerview)
    RecyclerView mRecyclerView;

    @ViewById(R.id.ll)
    LinearLayout ll;

    @ViewById(R.id.tv_ll_left)
    AppCompatTextView tv_ll_left;  // 金额合计：

    @ViewById(R.id.tv_ll_right)
    AppCompatTextView tv_ll_right; // 金额合计，列表最后一条数据的usdAmount 字段

    @Override
    public void onAfterViews() {
        initToolbar();
        rowsEntity = (T_ContactsCompanyEntity.RowsEntity) getIntent().getSerializableExtra(AppDelegate.ROWS_ENTITY);
        mRequestQueue = Volley.newRequestQueue(this);
        userInfoSp = MyApplication_.getInstance().getUserInfoSp();
        initRecyclerView();
        initSwipeRefreshLayout();
    }

    public void initToolbar() {
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        tv_title.setText("收汇信息");
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

    @Background
    public void onBackgrounds() {
        // status=1 外商
        final String url = MyAPI.getBaseUrl() + "/api/Funds/BankSlip/FindAccountBankSlip?status=1&foreignId=" + rowsEntity.getId() +
                "&accountId=" + userInfoSp.getString(AppDelegate.ACCOUNT_ID, "") + "&pageSize=10&pageIndex=" + pageIndex;
        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, new Response.Listener<org.json.JSONObject>() {
            @Override
            public void onResponse(org.json.JSONObject response) {
                LogUtils.d("\n收汇信息-URL:" + url + "\n收汇信息-RESPONSE:" + response.toString());

                JSONObject jsonObject = JSON.parseObject(response.toString());
                int total = jsonObject.getIntValue("total");
                totalPage = jsonObject.getIntValue("totalPage");

                if (total == 0) {
                    showView(tv_no_data);
                    hideView(ll);
                } else {
                    showView(ll);
                    hideView(tv_no_data);
                }

                if (total != 0 && pageIndex == 1) {
                    JSONArray rows = jsonObject.getJSONArray("rows");
                    rowsEntityList = JSON.parseArray(rows.toString(), T_ContactsCompanyDetailsMoreEntity.RowsEntity.class);

                    // 获取第一页数据的最后一条拿到它的usdAmount字段就是它的金额合计
                    T_ContactsCompanyDetailsMoreEntity.RowsEntity rowsEntity = rowsEntityList.get(rowsEntityList.size() - 1);
                    tv_ll_left.setText("金额合计：");
                    tv_ll_right.setText(StringUtil.numberFormat(rowsEntity.getUsdAmount()) + " " + "USD");

                    mAdapter = new MyAdapter(rowsEntityList);
                    mRecyclerView.setAdapter(mAdapter);
                    if (!isFirstLoading)
                        createRefreshCompleteSnackbar(mRecyclerView);
                } else if (pageIndex <= totalPage) {
                    JSONArray rows = jsonObject.getJSONArray("rows");
                    List<T_ContactsCompanyDetailsMoreEntity.RowsEntity> rowsEntities = JSON.parseArray(rows.toString(), T_ContactsCompanyDetailsMoreEntity.RowsEntity.class);
                    rowsEntities.remove(rowsEntities.get(rowsEntities.size() - 1));
                    rowsEntityList.addAll(lastVisibleItem + 1, rowsEntities);
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
                map.put(AppDelegate.QS_LOGIN, MyApplication_.getInstance().getUserInfoSp().getString(AppDelegate.LOGIN_NAME, ""));
                return map;
            }
        };

        jsonObjectRequest.setTag(this);
        mRequestQueue.add(jsonObjectRequest);
    }

    private class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

        private List<T_ContactsCompanyDetailsMoreEntity.RowsEntity> list;

        public MyAdapter(List<T_ContactsCompanyDetailsMoreEntity.RowsEntity> list) {
            this.list = list;
        }

        @Override
        public int getItemCount() {
            return list.size() - 1;
        } // 最后一条数据不取

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_four_field_cardview_simple_list, viewGroup, false);
            return new ViewHolder(view);
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            public LinearLayout ll_content;
            public AlwaysMarqueeTextView tv_accountName; // 账户名
            public AlwaysMarqueeTextView tv_receiptTypeCn; // 收汇方式
            public AppCompatTextView tv_receiptDate; // 收汇日期
            public AppCompatTextView tv_receiptAmount; // 收汇金额

            public ViewHolder(final View itemView) {
                super(itemView);
                tv_accountName = (AlwaysMarqueeTextView) itemView.findViewById(R.id.tv_top_left);
                tv_receiptTypeCn = (AlwaysMarqueeTextView) itemView.findViewById(R.id.tv_down_left);
                tv_receiptAmount = (AppCompatTextView) itemView.findViewById(R.id.tv_top_right);
                tv_receiptDate = (AppCompatTextView) itemView.findViewById(R.id.tv_down_right);

                ll_content = (LinearLayout) itemView.findViewById(R.id.ll_content);
                ll_content.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                });
            }
        }

        @Override
        public void onBindViewHolder(ViewHolder viewHolder, int position) {
            T_ContactsCompanyDetailsMoreEntity.RowsEntity entity = list.get(position);

            viewHolder.tv_accountName.setText(entity.getAccountName());
            viewHolder.tv_receiptTypeCn.setText(entity.getReceiptTypeCn());
            viewHolder.tv_receiptDate.setText(StringUtil.dateRemoveT(entity.getReceiptDate()));
            viewHolder.tv_receiptAmount.setText(StringUtil.numberFormat(entity.getReceiptAmount()));
            viewHolder.tv_receiptAmount.setTextColor(getColors(R.color.green_300));
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

}
