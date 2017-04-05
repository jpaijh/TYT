package com.qskj.tyt.fragment;

import android.content.SharedPreferences;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
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
import com.qskj.tyt.AppDelegate;
import com.qskj.tyt.MyAPI;
import com.qskj.tyt.MyApplication_;
import com.qskj.tyt.R;
import com.qskj.tyt.entity.T_ContactsCompanyDetailsMoreInlandShouldPayEntity;
import com.qskj.tyt.utils.StringUtil;
import com.qskj.tyt.view.AlwaysMarqueeTextView;
import com.umeng.analytics.MobclickAgent;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 往来单位列表-详情-更多-国内：资金往来
 */
@EFragment(R.layout.t_contacts_company_details_more)
public class T_ContactsCompanyDetailsMoreInlandFragment extends BaseFragment {

    private static final String TAG = "T_ContactsCompanyDetailsMoreInlandFragment";

    private MyShouldPayAdapter mShouldPayAdapter;
    private List<T_ContactsCompanyDetailsMoreInlandShouldPayEntity.RowsEntity> rowsEntityList;
    private int lastVisibleItem;
    private int totalPage;
    private int pageIndex = 1;
    private boolean isLoading;
    private boolean isFirstLoading;
    private LinearLayoutManager mLinearLayoutManager;
    private RequestQueue mRequestQueue;
    private String REQUEST_API;
    private SharedPreferences userInfoSp;

    @ViewById(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    @ViewById(R.id.recyclerview)
    RecyclerView mRecyclerView;

    @ViewById(R.id.toolbar)
    Toolbar mToolbar;

    @ViewById(R.id.tv_no_data)
    AppCompatTextView tv_no_data;

    @ViewById(R.id.ll)
    LinearLayout ll;

    @ViewById(R.id.tv_ll_left)
    AppCompatTextView tv_ll_left;  // 实付信息： 应付信息：应付累计：

    @ViewById(R.id.tv_ll_right)
    AppCompatTextView tv_ll_right; // 应付累计：列表最后一条数据的OrderCommodityAmount字段

    @Override
    public void onAfterViews() {
        mToolbar.setVisibility(View.GONE);
        REQUEST_API = getArguments().getString(AppDelegate.REQUEST_API);
        userInfoSp = MyApplication_.getInstance().getUserInfoSp();
        mRequestQueue = Volley.newRequestQueue(getActivity());
        initRecyclerView();
        initSwipeRefreshLayout();
    }

    private void initRecyclerView() {
        mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if ((mShouldPayAdapter != null && lastVisibleItem + 1 == mShouldPayAdapter.getItemCount())
                        && newState == RecyclerView.SCROLL_STATE_IDLE && !isLoading) {
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
        final String url = MyAPI.getBaseUrl() + REQUEST_API + "&pageSize=10&pageIndex=" + pageIndex;
        JsonObjectRequest listRequest = new JsonObjectRequest(Request.Method.GET, url, new Response.Listener<org.json.JSONObject>() {
            @Override
            public void onResponse(org.json.JSONObject response) {
                LogUtils.d("\n资金往来列表-URL:" + url + "\n资金往来列表-RESPONSE:" + response.toString());

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
                    rowsEntityList = JSON.parseArray(rows.toString(), T_ContactsCompanyDetailsMoreInlandShouldPayEntity.RowsEntity.class);

                    // 获取第一页数据的最后一条拿到它的OrderCommodityAmount字段就是它的应付累计
                    T_ContactsCompanyDetailsMoreInlandShouldPayEntity.RowsEntity rowsEntity = rowsEntityList.get(rowsEntityList.size() - 1);
                    tv_ll_left.setText("应付累计：");
                    tv_ll_right.setText(StringUtil.numberFormat(rowsEntity.getOrderCommodityAmount()));

                    mShouldPayAdapter = new MyShouldPayAdapter(rowsEntityList);
                    mRecyclerView.setAdapter(mShouldPayAdapter);
                    if (!isFirstLoading)
                        createRefreshCompleteSnackbar(mRecyclerView);
                } else if (pageIndex <= totalPage) {
                    JSONArray rows = jsonObject.getJSONArray("rows");
                    List<T_ContactsCompanyDetailsMoreInlandShouldPayEntity.RowsEntity> rowsEntities = JSON.parseArray(rows.toString(), T_ContactsCompanyDetailsMoreInlandShouldPayEntity.RowsEntity.class);
                    rowsEntities.remove(rowsEntities.get(rowsEntities.size() - 1));
                    rowsEntityList.addAll(lastVisibleItem + 1, rowsEntities); // 删掉最后一条数据
                    mShouldPayAdapter.notifyDataSetChanged();
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
                map.put(AppDelegate.QS_LOGIN, userInfoSp.getString(AppDelegate.LOGIN_NAME, ""));
                return map;
            }
        };

        listRequest.setTag(this);
        mRequestQueue.add(listRequest);
    }

    // 应付信息
    private class MyShouldPayAdapter extends RecyclerView.Adapter<MyShouldPayAdapter.ViewHolder> {

        private List<T_ContactsCompanyDetailsMoreInlandShouldPayEntity.RowsEntity> list;

        public MyShouldPayAdapter(List<T_ContactsCompanyDetailsMoreInlandShouldPayEntity.RowsEntity> list) {
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
            public AlwaysMarqueeTextView tv_saleOrder; // 外销发票号
            public AlwaysMarqueeTextView tv_orderCommodityName; // 商品名称
            public AppCompatTextView tv_orderCommodityAmount; // 商品总金额
            public AppCompatTextView tv_createDate; // 创建日期

            public ViewHolder(final View itemView) {
                super(itemView);
                tv_saleOrder = (AlwaysMarqueeTextView) itemView.findViewById(R.id.tv_top_left);
                tv_orderCommodityName = (AlwaysMarqueeTextView) itemView.findViewById(R.id.tv_down_left);
                tv_orderCommodityAmount = (AppCompatTextView) itemView.findViewById(R.id.tv_top_right);
                tv_createDate = (AppCompatTextView) itemView.findViewById(R.id.tv_down_right);

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
            T_ContactsCompanyDetailsMoreInlandShouldPayEntity.RowsEntity entity = list.get(position);

            viewHolder.tv_saleOrder.setText(entity.getSaleOrder());
            viewHolder.tv_orderCommodityName.setText(entity.getOrderCommodityName());
            viewHolder.tv_createDate.setText(StringUtil.dateRemoveT(entity.getCreateDate()));
            viewHolder.tv_orderCommodityAmount.setText(StringUtil.numberFormat(entity.getOrderCommodityAmount()));
            viewHolder.tv_orderCommodityAmount.setTextColor(getColors(R.color.green_300));
        }

    }

    @Override
    public void onDestroy() {
        mRequestQueue.cancelAll(this);
        super.onDestroy();
    }

    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart(TAG);
    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd(TAG);
    }


}
