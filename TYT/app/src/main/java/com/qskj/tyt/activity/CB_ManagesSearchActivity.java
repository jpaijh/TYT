package com.qskj.tyt.activity;

import android.content.SharedPreferences;
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
import android.widget.RelativeLayout;

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
 * 搜索-仓单管理
 */
@EActivity(R.layout.cb_activity_search_manage)
public class CB_ManagesSearchActivity extends BaseActivity {

    private static final String TAG = "CB_ManagesSearchActivity";

    private MyAdapter mAdapter;
    private int lastVisibleItem;
    private int totalPage;
    private int pageIndex = 1;
    private boolean isLoading;
    private LinearLayoutManager mLinearLayoutManager;
    private RequestQueue mRequestQueue;

    private SharedPreferences userInfoSp;
    private int accountNature;

    private SearchHistoryAdapter searchHistoryAdapter;
    private String KEY_WORD;
    private String accountId;
    private List<String> strings;
    private List<CB_ManageListEntity.RowsEntity> rowsEntityList;

    @ViewById(R.id.toolbar)
    Toolbar mToolbar;

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
    AppCompatTextView tv_no_search_data;

    public void initToolbar() {
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
    }

    /**
     * 显示历史搜索记录，有的话就显示，没有就不显示
     */
    public void showHistory() {
        Set<String> historySets = getSharedPreferences(AppDelegate.SP_SEARCH_HISTORY, MODE_PRIVATE).getStringSet("history_" + TAG + accountNature, null);
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
//            searchHistoryAdapter.notifyDataSetChanged();
        }

    }


    @Override
    public void onAfterViews() {
        userInfoSp = MyApplication_.getInstance().getUserInfoSp();
        accountNature = userInfoSp.getInt(AppDelegate.ACCOUNT_NATURE, -1);
        accountId = userInfoSp.getString(AppDelegate.ACCOUNT_ID, "");
        mRequestQueue = Volley.newRequestQueue(this);
        initToolbar();
        initSearchView();
        initRecyclerView();
        showHistory();
    }

    private void initSearchView() {
        // 设置SearchView的EditText的属性
        editText.setTextSize(14);
        editText.setGravity(Gravity.CENTER_VERTICAL);

        // 设置是否显示搜索按钮
        search_view.setSubmitButtonEnabled(false);
        search_view.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (!TextUtils.isEmpty(query.trim())) {
                    // 按搜索键，隐藏软键盘
//                    DeviceUtil.hideSoft(getApplicationContext(), editText);
//                    search(query.trim());
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.trim().length() == 0) {
                    if (mRecyclerView.getVisibility() == View.GONE)
                        showView(mRecyclerView);
                    showHistory();
                    if (tv_no_search_data.getVisibility() == View.VISIBLE)
                        hideView(tv_no_search_data);
                } else if (!isLoading) {
                    pageIndex = 1;
                    isLoading = true;
                    search(newText.trim());
                }
                return true;
            }
        });
    }

    private void initRecyclerView() {
        mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (mAdapter != null && newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == mAdapter.getItemCount() && !isLoading) {
                    if (pageIndex < totalPage) {
                        isLoading = true;
                        createLoadingSnackbar(mRecyclerView);
                        pageIndex = pageIndex + 1;

                        // 加载时，隐藏软键盘
//                        DeviceUtil.hideSoft(CB_ManagesSearchActivity.this, editText);
                        search(KEY_WORD);
                    } else {
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

    @Background
    void search(String keyword) {
        KEY_WORD = keyword;
        // 请求获取 仓单管理-搜索 某些条目
        try {
            final String url = MyAPI.getBaseUrl() + "/api/Orders/Storage/FindStorage?accountId=" + accountId + "&loginAccountNature=" + accountNature + "&pageSize=10&pageIndex=" + pageIndex + "&keyword=" + URLEncoder.encode(keyword, "utf-8");
            final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, new Response.Listener<org.json.JSONObject>() {
                @Override
                public void onResponse(org.json.JSONObject response) {
                    LogUtils.d("\n搜索-URL:" + url + "\n搜索-RESPONSE:" + response.toString());

                    JSONObject jsonObject = JSON.parseObject(response.toString());
                    int total = jsonObject.getIntValue("total");
                    totalPage = jsonObject.getIntValue("totalPage");

                    if (total == 0) {
                        showView(tv_no_search_data);
                        if (mRecyclerView.getVisibility() == View.VISIBLE)
                            mRecyclerView.setVisibility(View.GONE);
                    } else {
                        hideView(tv_no_search_data);
                        if (mRecyclerView.getVisibility() == View.GONE)
                            mRecyclerView.setVisibility(View.VISIBLE);
                    }

                    if (total != 0 && pageIndex == 1) {
                        JSONArray rows = jsonObject.getJSONArray("rows");
                        rowsEntityList = JSON.parseArray(rows.toString(), CB_ManageListEntity.RowsEntity.class);
                        mAdapter = new MyAdapter(rowsEntityList);
                        mRecyclerView.setAdapter(mAdapter);
                    } else if (pageIndex <= totalPage) {
                        JSONArray rows = jsonObject.getJSONArray("rows");
                        rowsEntityList.addAll(lastVisibleItem + 1, JSON.parseArray(rows.toString(), CB_ManageListEntity.RowsEntity.class));
                        mAdapter.notifyDataSetChanged();
                        createLoadingCompleteSnackbar(mRecyclerView);
                    }

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
                    map.put(AppDelegate.QS_LOGIN, userInfoSp.getString(AppDelegate.LOGIN_NAME, ""));
                    return map;
                }
            };

            jsonObjectRequest.setTag(this);
            mRequestQueue.add(jsonObjectRequest);

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
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cb_item_manage_list, viewGroup, false);
            return new ViewHolder(view);
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public RelativeLayout rl_content;
            public AlwaysMarqueeTextView tv_storageListGrid_deliveryInvoiceCode; // 进仓编号
            public AppCompatTextView tv_storageListGrid_blNo; // 提单编号
            public AlwaysMarqueeTextView tv_storageListGrid_businessEnterpriseName; // 经营单位
            public AppCompatTextView tv_storageListGrid_storageStatus; // 状态
            public AppCompatTextView tv_storageListGrid_deliveryDate; // 进仓单创建日期

            public ViewHolder(final View itemView) {
                super(itemView);
                tv_storageListGrid_deliveryInvoiceCode = (AlwaysMarqueeTextView) itemView.findViewById(R.id.tv_storageListGrid_deliveryInvoiceCode);
                tv_storageListGrid_blNo = (AppCompatTextView) itemView.findViewById(R.id.tv_storageListGrid_blNo);
                tv_storageListGrid_businessEnterpriseName = (AlwaysMarqueeTextView) itemView.findViewById(R.id.tv_storageListGrid_businessEnterpriseName);
                tv_storageListGrid_storageStatus = (AppCompatTextView) itemView.findViewById(R.id.tv_storageListGrid_storageStatus);
                tv_storageListGrid_deliveryDate = (AppCompatTextView) itemView.findViewById(R.id.tv_storageListGrid_deliveryDate);
                rl_content = (RelativeLayout) itemView.findViewById(R.id.rl_content);
                rl_content.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        CB_ManageListEntity.RowsEntity rowsEntity = list.get(getLayoutPosition());

                        // 保存点击进入查看详情的 deliveryInvoiceCode 作为历史记录
                        Set<String> setHistory = getSharedPreferences(AppDelegate.SP_SEARCH_HISTORY, MODE_PRIVATE).getStringSet("history_" + TAG + accountNature, null);
                        if (setHistory == null) {
                            Set<String> set = new HashSet<>();
                            set.add(rowsEntity.getDeliveryInvoiceCode());
                            getSharedPreferences(AppDelegate.SP_SEARCH_HISTORY, MODE_PRIVATE).edit().putStringSet("history_" + TAG + accountNature, set).commit();
                        } else {
                            TreeSet<String> set = new TreeSet<>();
                            String[] strings = setHistory.toArray(new String[]{});
                            for (int i = 0; i < setHistory.size(); i++) {
                                set.add(strings[i]);
                            }
                            set.add(rowsEntity.getDeliveryInvoiceCode());
                            getSharedPreferences(AppDelegate.SP_SEARCH_HISTORY, MODE_PRIVATE).edit().putStringSet("history_" + TAG + accountNature, set).commit();
                        }

                        // 进入详情页
                        CB_ManageDetailsActivity_.intent(CB_ManagesSearchActivity.this).extra(AppDelegate.ID, rowsEntity.getId()).extra(AppDelegate.TOOLBAR_TITLE, rowsEntity.getDeliveryInvoiceCode()).start();

                    }
                });
            }
        }

        @Override
        public void onBindViewHolder(ViewHolder viewHolder, int position) {
            CB_ManageListEntity.RowsEntity entity = list.get(position);

            viewHolder.tv_storageListGrid_deliveryInvoiceCode.setText(entity.getDeliveryInvoiceCode());
            viewHolder.tv_storageListGrid_blNo.setText(entity.getBlNo());
            viewHolder.tv_storageListGrid_businessEnterpriseName.setText(entity.getBusinessEnterpriseName());
            viewHolder.tv_storageListGrid_deliveryDate.setText(StringUtil.dateRemoveT(entity.getDeliveryDate()));

            // 状态：新制=1 已录入=2 已确认=3 待收货=4 已收货=5 已申报=6 已报关=7
            int status = entity.getStorageStatus();
            viewHolder.tv_storageListGrid_storageStatus.setTextColor(getColors(R.color.decoView_line2_end_color));
            switch (status) {
                case 1: // 新制
                    viewHolder.tv_storageListGrid_storageStatus.setText(getStrings(R.string.xinzhi));
                    viewHolder.tv_storageListGrid_storageStatus.setTextColor(getColors(R.color.red_400));
                    break;
                case 2: // 已录入
                    viewHolder.tv_storageListGrid_storageStatus.setText(getStrings(R.string.yiluru));
                    viewHolder.tv_storageListGrid_storageStatus.setTextColor(getColors(R.color.red_400));
                    break;
                case 3: // 已确认
                    viewHolder.tv_storageListGrid_storageStatus.setText(getStrings(R.string.yiqueren));
                    viewHolder.tv_storageListGrid_storageStatus.setTextColor(getColors(R.color.red_400));
                    break;
                case 4: // 待收货
                    viewHolder.tv_storageListGrid_storageStatus.setText(getStrings(R.string.daishouhuo));
                    break;
                case 5: // 已收货
                    viewHolder.tv_storageListGrid_storageStatus.setText(getStrings(R.string.yishouhuo));
                    break;
                case 6: // 已申报
                    viewHolder.tv_storageListGrid_storageStatus.setText(getStrings(R.string.yishenbao));
                    break;
                case 7: // 已报关
                    viewHolder.tv_storageListGrid_storageStatus.setText(getStrings(R.string.yibaoguan));
                    viewHolder.tv_storageListGrid_storageStatus.setTextColor(getColors(R.color.decoView_line1_start_color));
                    break;
                default: // 状态若没有匹配的，显示“”
                    viewHolder.tv_storageListGrid_storageStatus.setText("");
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
            public AppCompatTextView tv_clear_history; // 清空搜索历史

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
                            getSharedPreferences(AppDelegate.SP_SEARCH_HISTORY, MODE_PRIVATE).edit().putStringSet("history_" + TAG + accountNature, emptySet).commit();
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mRequestQueue.cancelAll(this);
    }

}
