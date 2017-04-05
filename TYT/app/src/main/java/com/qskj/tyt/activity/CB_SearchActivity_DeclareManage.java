package com.qskj.tyt.activity;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
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
import com.qskj.tyt.MyAPI;
import com.qskj.tyt.R;
import com.qskj.tyt.entity.CB_DeclareManageEntity;
import com.qskj.tyt.utils.StringUtil;
import com.qskj.tyt.view.AlwaysMarqueeTextView;
import com.umeng.analytics.MobclickAgent;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * 搜索-报关单管理
 */
@EActivity(R.layout.cb_activity_search_manage)
public class CB_SearchActivity_DeclareManage extends BaseActivity {

    private static final String TAG = "CB_SearchActivity_DeclareManage";

    int lastVisibleItem;
    int totalPage;
    int pageIndex = 1;
    boolean isLoading;
    MyAdapter mAdapter;
    SearchHistoryAdapter searchHistoryAdapter;
    LinearLayoutManager mLinearLayoutManager;
    List<CB_DeclareManageEntity.RowsEntity> rowsEentityList;
    String KEY_WORD;
    String accountId;
    String accountNature;
    List<String> strings;

    @ViewById(R.id.toolbar)
    Toolbar toolbar;

    @ViewById(R.id.search_view)
    SearchView search_view;

    /**
     * 这是系统的SearchView的EditText通过找到它去修改SearchView的style
     */
    @ViewById(R.id.search_src_text)
    SearchView.SearchAutoComplete editText;

    @ViewById(R.id.recyclerview)
    RecyclerView mRecyclerView;

    @ViewById(R.id.tv_no_search_data)
    TextView mTvNodate;

    public void initToolbar() {
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
    }

    /**
     * 设置SearchView的EditText的属性
     */
    public void initSearchView() {
        editText.setTextSize(14);
        editText.setGravity(Gravity.CENTER_VERTICAL);
    }

    /**
     * 显示历史搜索记录，有的话就显示，没有就不显示
     */
    public void showHistory() {
        if (!mRecyclerView.isShown()) {
            mRecyclerView.setVisibility(View.VISIBLE);
        }

        Set<String> historySets = getSharedPreferences("SP_SEARCH_HISTORY", MODE_PRIVATE).getStringSet("history_" + TAG, null);
        if (historySets != null) {
            String[] historyStr = historySets.toArray(new String[]{});
            strings = new ArrayList<>();
            for (int i = 0; i < historyStr.length; i++) {
                strings.add(historyStr[i]);
            }

            if (historyStr.length != 0)
                strings.add("清空历史搜索记录");
            searchHistoryAdapter = new SearchHistoryAdapter(strings);
            mRecyclerView.setAdapter(searchHistoryAdapter);
        }
    }

    @Override
    public void onAfterViews() {
        initToolbar();
        initSearchView();

        // 注意这两行代码位置不要换
        accountNature = getSharedPreferences("UserInfo", Context.MODE_PRIVATE).getString("accountNature", "");
        showHistory();

        accountId = getSharedPreferences("UserInfo", Context.MODE_PRIVATE).getString("accountId", "");

        mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (mAdapter != null && newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == mAdapter.getItemCount()) {
                    if (!isLoading) {
                        isLoading = true;
                        pageIndex = pageIndex + 1;

                        if (pageIndex <= totalPage) { // 注意：pageIndex我定义的是从1开始的，这个表示正在加载的Snackbar显示到加载最后一页
                            createLoadingSnackbar(mRecyclerView);
                            // 加载时，隐藏软键盘
                            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
                        }

                        search(KEY_WORD);
                    }
                    super.onScrollStateChanged(recyclerView, newState);
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = mLinearLayoutManager.findLastVisibleItemPosition();
            }

        });

        // 设置是否显示搜索按钮
        search_view.setSubmitButtonEnabled(false);

        search_view.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (!TextUtils.isEmpty(query.trim())) {
                    search(query.trim());
                    // 按搜索键，隐藏软键盘
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (TextUtils.isEmpty(newText.trim())) {
                    mTvNodate.setVisibility(View.GONE);
                    showHistory();
                } else {
                    pageIndex = 1;
                    search(newText.trim());
                }
                return true;
            }
        });

    }

    @Background
    void search(String keyword) {
        KEY_WORD = keyword;
        // 请求获取 报关单管理-搜索 某个条目
        try {
            final String url = MyAPI.getBaseUrl() + "/api/Orders/Order/FindOrderReportPagedList?accountId=" + accountId + "&loginAccountNature=" + accountNature + "&pageSize=10&pageIndex=" + pageIndex
                    + "&keyword=" + URLEncoder.encode(keyword, "utf-8");
            final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, new Response.Listener<org.json.JSONObject>() {
                @Override
                public void onResponse(org.json.JSONObject response) {
                    JSONObject jsonObject = JSON.parseObject(response.toString());
                    int total = jsonObject.getIntValue("total");
                    totalPage = jsonObject.getIntValue("totalPage");

                    if (total == 0) {
                        mTvNodate.setVisibility(View.VISIBLE);
                        mRecyclerView.setVisibility(View.GONE);
                    } else {
                        mTvNodate.setVisibility(View.GONE);
                        mRecyclerView.setVisibility(View.VISIBLE);
                    }

                    if (total != 0 && pageIndex == 1) {
                        JSONArray rows = jsonObject.getJSONArray("rows");
                        rowsEentityList = JSON.parseArray(rows.toString(), CB_DeclareManageEntity.RowsEntity.class);
                        LogUtils.d(rowsEentityList);

                        if (rowsEentityList != null) {
                            mAdapter = new MyAdapter(rowsEentityList);
                            mRecyclerView.setAdapter(mAdapter);
                        }

                    } else if (pageIndex > totalPage) {
                        isLoading = false;
                        if (pageIndex != 1)
                            createNoDateSnackbar(mRecyclerView);
                    } else {
                        JSONArray rows = jsonObject.getJSONArray("rows");
                        rowsEentityList.addAll(lastVisibleItem + 1, JSON.parseArray(rows.toString(), CB_DeclareManageEntity.RowsEntity.class));
                        mAdapter.notifyDataSetChanged();
                        isLoading = false;
                        createLoadingCompleteSnackbar(mRecyclerView);
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
                    map.put("QS-LOGIN", getSharedPreferences("UserInfo", Context.MODE_PRIVATE).getString("loginName", ""));
                    return map;
                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(jsonObjectRequest);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

    private class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

        private List<CB_DeclareManageEntity.RowsEntity> list;

        public MyAdapter(List<CB_DeclareManageEntity.RowsEntity> list) {
            this.list = list;
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cb_item_declaremanage_list, viewGroup, false);
            return new ViewHolder(view);
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            public RelativeLayout rl_content;
            public AppCompatTextView tv_declaremanage_code; // Code
            public AppCompatTextView tv_declaremanage_blNo; // 提单编号
            public AppCompatTextView tv_declaremanage_customsFlag; // 未报关/已报关
            public AppCompatTextView tv_declaremanage_createDate; // 创建日期
            public AppCompatTextView tv_declaremanage_actualQuantity; // 实际出仓
            public AppCompatTextView tv_declaremanage_packageQuantity; // 报关件数
            public AlwaysMarqueeTextView tv_declaremanage_exportPort; // 开始港口
            public AppCompatTextView tv_declaremanage_destinationPort; // 终止港口

            public ViewHolder(final View itemView) {
                super(itemView);
                tv_declaremanage_code = (AppCompatTextView) itemView.findViewById(R.id.tv_declare_manage_code);
                tv_declaremanage_createDate = (AppCompatTextView) itemView.findViewById(R.id.tv_declare_manage_createDate);
                tv_declaremanage_blNo = (AppCompatTextView) itemView.findViewById(R.id.tv_declare_manage_blNo);
                tv_declaremanage_customsFlag = (AppCompatTextView) itemView.findViewById(R.id.tv_declare_manage_customsFlag);
                tv_declaremanage_actualQuantity = (AppCompatTextView) itemView.findViewById(R.id.tv_declare_manage_actualQuantity);
                tv_declaremanage_packageQuantity = (AppCompatTextView) itemView.findViewById(R.id.tv_declare_manage_packageQuantity);
                tv_declaremanage_exportPort = (AlwaysMarqueeTextView) itemView.findViewById(R.id.tv_declare_manage_exportPort);
                tv_declaremanage_destinationPort = (AppCompatTextView) itemView.findViewById(R.id.tv_declare_manage_destinationPort);
                rl_content = (RelativeLayout) itemView.findViewById(R.id.rl_content);
                rl_content.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        CB_DeclareManageEntity.RowsEntity rowsEentity = list.get(getLayoutPosition());

                        // 保存点击进入查看详情的 code 作为历史记录
                        Set<String> setHistory = getSharedPreferences("SP_SEARCH_HISTORY", MODE_PRIVATE).getStringSet("history_" + TAG, null);
                        if (setHistory == null) {
                            Set<String> set = new HashSet<>();
                            set.add(rowsEentity.getCode());
                            getSharedPreferences("SP_SEARCH_HISTORY", MODE_PRIVATE).edit().putStringSet("history_" + TAG, set).commit();
                        } else {
                            TreeSet<String> set = new TreeSet<>();
                            String[] strings = setHistory.toArray(new String[]{});
                            for (int i = 0; i < setHistory.size(); i++) {
                                set.add(strings[i]);
                            }
                            set.add(rowsEentity.getCode());
                            getSharedPreferences("SP_SEARCH_HISTORY", MODE_PRIVATE).edit().putStringSet("history_" + TAG, set).commit();
                        }

                        // OrderID,进仓单编号 deliveryInvoiceCode,isCustomsFlag 是否报关
                        // 进入详情
//                        DeclareManageDetailsActivity_.intent(CB_SearchActivity_DeclareManage.this).extra("id", rowsEentity.getOrderId())
//                                .extra("deliveryInvoiceCode", rowsEentity.getCode())
//                                .extra("isCustomsFlag", rowsEentity.isCustomsFlag()).start();

                    }
                });
            }
        }

        @Override
        public void onBindViewHolder(ViewHolder viewHolder, int position) {
            CB_DeclareManageEntity.RowsEntity entity = list.get(position);

            viewHolder.tv_declaremanage_code.setText(entity.getCode());
            viewHolder.tv_declaremanage_blNo.setText(entity.getBlNo());
            if (entity.isCustomsFlag()) {
                viewHolder.tv_declaremanage_customsFlag.setText("已报关");
                viewHolder.tv_declaremanage_customsFlag.setTextColor(getColors(R.color.decoView_line1_end_color));
            } else {
                viewHolder.tv_declaremanage_customsFlag.setText("未报关");
                viewHolder.tv_declaremanage_customsFlag.setTextColor(getColors(R.color.red_500));
            }
            viewHolder.tv_declaremanage_createDate.setText(StringUtil.dateRemoveT(entity.getCreateDate()));

            viewHolder.tv_declaremanage_actualQuantity.setText("实际出仓：" + entity.getActualQuantity());
            viewHolder.tv_declaremanage_packageQuantity.setText("报关件数：" + entity.getPackageQuantity());
            viewHolder.tv_declaremanage_exportPort.setText(entity.getExportPort());
            viewHolder.tv_declaremanage_destinationPort.setText(entity.getDestinationPort());

        }

    }

    private class SearchHistoryAdapter extends RecyclerView.Adapter<SearchHistoryAdapter.ViewHolder> {

        private List<String> list;

        public SearchHistoryAdapter(List<String> list) {
            this.list = list;
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cb_item_searchhistory, viewGroup, false);
            return new ViewHolder(view);
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public AppCompatTextView tv_history; // 搜索历史
            public AppCompatTextView tv_clear_history; // 搜索历史

            public ViewHolder(final View itemView) {
                super(itemView);
                tv_history = (AppCompatTextView) itemView.findViewById(R.id.tv_history);
                tv_clear_history = (AppCompatTextView) itemView.findViewById(R.id.tv_clear_history);

                // 整个条目点击事件
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (getLayoutPosition() == list.size() - 1) {
                            Set<String> emptySet = new HashSet<>();
                            getSharedPreferences("SP_SEARCH_HISTORY", MODE_PRIVATE).edit().putStringSet("history_" + TAG, emptySet).commit();
                            strings.clear();
                            searchHistoryAdapter.notifyDataSetChanged();
                        } else {
                            editText.setText(list.get(getLayoutPosition()));
                        }
                    }
                });
            }
        }

        @Override
        public void onBindViewHolder(ViewHolder viewHolder, int position) {
            if (position == list.size() - 1) {
                viewHolder.tv_history.setVisibility(View.GONE);
                viewHolder.tv_clear_history.setVisibility(View.VISIBLE);
            } else {
                viewHolder.tv_history.setText(list.get(position));
                viewHolder.tv_clear_history.setVisibility(View.GONE);
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
