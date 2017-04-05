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
import com.qskj.tyt.entity.CB_ManageListEntity;
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
 * 搜索-清单管理
 */
@EActivity(R.layout.cb_activity_search_manage)
public class CB_SearchActivity_ListManage extends BaseActivity {

    private static final String TAG = "CB_SearchActivity_ListManage";

    int lastVisibleItem;
    int totalPage;
    int pageIndex = 1;
    boolean isLoading;
    MyAdapter mAdapter;
    SearchHistoryAdapter searchHistoryAdapter;
    LinearLayoutManager mLinearLayoutManager;
    List<CB_ManageListEntity.RowsEntity> rowsEentityList;
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
        // 请求获取 清单管理-搜索 某个条目
        try {
            final String url = MyAPI.getBaseUrl() + "/api/Orders/Storage/FindStorage?accountId=" + accountId + "&loginAccountNature=" + accountNature + "&pageSize=10&pageIndex=" + pageIndex + "&keyword=" + URLEncoder.encode(keyword, "utf-8")
                    + "&customsAreaEntryStatusList=0&customsAreaEntryStatusList=11&customsAreaEntryStatusList=12&customsAreaEntryStatusList=13&customsAreaEntryStatusList=14&customsAreaEntryStatusList=15";
            final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url,
                    new Response.Listener<org.json.JSONObject>() {
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
                                rowsEentityList = JSON.parseArray(rows.toString(), CB_ManageListEntity.RowsEntity.class);
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
                                rowsEentityList.addAll(lastVisibleItem + 1, JSON.parseArray(rows.toString(), CB_ManageListEntity.RowsEntity.class));
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

        private List<CB_ManageListEntity.RowsEntity> list;

        public MyAdapter(List<CB_ManageListEntity.RowsEntity> list) {
            this.list = list;
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cb_item_listmanage_list, viewGroup, false);
            return new ViewHolder(view);
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            public RelativeLayout rl_content;
            public AlwaysMarqueeTextView tv_listmanage_deliveryInvoiceCode; // 进仓单编号
            public AppCompatTextView tv_listmanage_customsAreaEntryStatus;  // 状态
            public AppCompatTextView tv_listmanage_forwarderConfirmed; // 确认状态
            public AppCompatTextView tv_listmanage_deliveryDate; // 创建日期
            public AppCompatTextView tv_listmanage_enumber; // 出仓
            public AppCompatTextView tv_listmanage_inventorynum; //库存
            public AppCompatTextView tv_listmanage_inwarehousenum; //进仓

            public ViewHolder(final View itemView) {
                super(itemView);
                tv_listmanage_deliveryInvoiceCode = (AlwaysMarqueeTextView) itemView.findViewById(R.id.tv_list_manage_deliveryInvoiceCode);
                tv_listmanage_forwarderConfirmed = (AppCompatTextView) itemView.findViewById(R.id.tv_list_manage_forwarderConfirmed);
                tv_listmanage_deliveryDate = (AppCompatTextView) itemView.findViewById(R.id.tv_list_manage_deliveryDate);
                tv_listmanage_customsAreaEntryStatus = (AppCompatTextView) itemView.findViewById(R.id.tv_list_manage_customsAreaEntryStatus);
                tv_listmanage_enumber = (AppCompatTextView) itemView.findViewById(R.id.tv_listmanage_enumber);
                tv_listmanage_inventorynum = (AppCompatTextView) itemView.findViewById(R.id.tv_listmanage_inventorynum);
                tv_listmanage_inwarehousenum = (AppCompatTextView) itemView.findViewById(R.id.tv_listmanage_inwarehousenum);
                rl_content = (RelativeLayout) itemView.findViewById(R.id.rl_content);
                rl_content.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        CB_ManageListEntity.RowsEntity rowsEentity = list.get(getLayoutPosition());

                        // 保存点击进入查看详情的 deliveryInvoiceCode 作为历史记录
                        Set<String> setHistory = getSharedPreferences("SP_SEARCH_HISTORY", MODE_PRIVATE).getStringSet("history_" + TAG, null);
                        if (setHistory == null) {
                            Set<String> set = new HashSet<>();
                            set.add(rowsEentity.getDeliveryInvoiceCode());
                            getSharedPreferences("SP_SEARCH_HISTORY", MODE_PRIVATE).edit().putStringSet("history_" + TAG, set).commit();
                        } else {
                            TreeSet<String> set = new TreeSet<>();
                            String[] strings = setHistory.toArray(new String[]{});
                            for (int i = 0; i < setHistory.size(); i++) {
                                set.add(strings[i]);
                            }
                            set.add(rowsEentity.getDeliveryInvoiceCode());
                            getSharedPreferences("SP_SEARCH_HISTORY", MODE_PRIVATE).edit().putStringSet("history_" + TAG, set).commit();
                        }

                        // 仓单ID,进仓单编号 deliveryInvoiceCode
                        // 进入详情
//                        ListManageDetailsActivity_.intent(CB_SearchActivity_ListManage.this).extra("id", rowsEentity.getId())
//                                .extra("deliveryInvoiceCode", rowsEentity.getDeliveryInvoiceCode()).start();

                    }
                });
            }
        }

        @Override
        public void onBindViewHolder(ViewHolder viewHolder, int position) {
            CB_ManageListEntity.RowsEntity entity = list.get(position);
            viewHolder.tv_listmanage_deliveryInvoiceCode.setText(entity.getDeliveryInvoiceCode());
            viewHolder.tv_listmanage_deliveryDate.setText(StringUtil.dateRemoveT(entity.getDeliveryDate()));
            viewHolder.tv_listmanage_inwarehousenum.setText("进仓:" + entity.getPackingQuantity());
            viewHolder.tv_listmanage_inventorynum.setText("库存:" + entity.getLeftQuantity());
            viewHolder.tv_listmanage_enumber.setText("出仓:" + entity.getOutQuantity());

            if (entity.isForwarderConfirmed()) {
                viewHolder.tv_listmanage_forwarderConfirmed.setText("已确认");
                viewHolder.tv_listmanage_forwarderConfirmed.setTextColor(getColors(R.color.decoView_line1_end_color));
            } else {
                viewHolder.tv_listmanage_forwarderConfirmed.setText("未确认");
                viewHolder.tv_listmanage_forwarderConfirmed.setTextColor(getColors(R.color.red_500));
            }

            viewHolder.tv_listmanage_customsAreaEntryStatus.setText(entity.getCustomsAreaEntryStatusFormat());
            int customsAreaEntryStatus = entity.getCustomsAreaEntryStatus();
            switch (customsAreaEntryStatus) {
                case 0: // 未申报
                    viewHolder.tv_listmanage_customsAreaEntryStatus.setTextColor(getColors(R.color.decoView_line2_end_color));
                    break;
                case 11: // 已报海关
                    viewHolder.tv_listmanage_customsAreaEntryStatus.setTextColor(getColors(R.color.decoView_line2_end_color));
                    break;
                case 12: // 放行
                    viewHolder.tv_listmanage_customsAreaEntryStatus.setTextColor(getColors(R.color.decoView_line1_start_color));
                    break;
                case 13: // 未放行
                    viewHolder.tv_listmanage_customsAreaEntryStatus.setTextColor(getColors(R.color.decoView_line2_end_color));
                    break;
                case 14: // 查验
                    viewHolder.tv_listmanage_customsAreaEntryStatus.setTextColor(getColors(R.color.red_500));
                    break;
                case 15: // 已移位查验
                    viewHolder.tv_listmanage_customsAreaEntryStatus.setTextColor(getColors(R.color.red_500));
                    break;
            }
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
