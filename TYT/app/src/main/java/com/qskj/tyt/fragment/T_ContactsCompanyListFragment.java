package com.qskj.tyt.fragment;

import android.content.SharedPreferences;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.qskj.tyt.activity.T_ContactsCompanyDetailsActivity_;
import com.qskj.tyt.entity.T_ContactsCompanyEntity;
import com.qskj.tyt.view.AlwaysMarqueeTextView;
import com.umeng.analytics.MobclickAgent;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 往来单位列表：我的国内开票单位，我的外商
 */
@EFragment(R.layout.swiperefreshlayout_recyclerview_tvnodata)
public class T_ContactsCompanyListFragment extends BaseFragment {

    private static final String TAG = "T_ContactsCompanyListFragment";

    private MyContactsCompanyAdapter mAdapter;
    private List<T_ContactsCompanyEntity.RowsEntity> rowsEntity;
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

    @ViewById(R.id.tv_no_data)
    AppCompatTextView tv_no_data;

    @Override
    public void onAfterViews() {
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
                if ((mAdapter != null && lastVisibleItem + 1 == mAdapter.getItemCount())
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
                LogUtils.d("\n往来单位列表-URL:" + url + "\n往来单位列表-RESPONSE:" + response.toString());

                JSONObject jsonObject = JSON.parseObject(response.toString());
                int total = jsonObject.getIntValue("total");
                totalPage = jsonObject.getIntValue("totalPage");

                if (total == 0) {
                    showView(tv_no_data);
                } else {
                    hideView(tv_no_data);
                }

                if (total != 0 && pageIndex == 1) {
                    JSONArray rows = jsonObject.getJSONArray("rows");
                    rowsEntity = JSON.parseArray(rows.toString(), T_ContactsCompanyEntity.RowsEntity.class);
                    mAdapter = new MyContactsCompanyAdapter(rowsEntity);
                    mRecyclerView.setAdapter(mAdapter);
                    if (!isFirstLoading)
                        createRefreshCompleteSnackbar(mRecyclerView);
                } else if (pageIndex <= totalPage) {
                    JSONArray rows = jsonObject.getJSONArray("rows");
                    rowsEntity.addAll(lastVisibleItem + 1, JSON.parseArray(rows.toString(), T_ContactsCompanyEntity.RowsEntity.class));
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
                map.put(AppDelegate.QS_LOGIN, userInfoSp.getString(AppDelegate.LOGIN_NAME, ""));
                return map;
            }
        };

        listRequest.setTag(this);
        mRequestQueue.add(listRequest);
    }

    private class MyContactsCompanyAdapter extends RecyclerView.Adapter<MyContactsCompanyAdapter.ViewHolder> {

        private List<T_ContactsCompanyEntity.RowsEntity> list;

        public MyContactsCompanyAdapter(List<T_ContactsCompanyEntity.RowsEntity> list) {
            this.list = list;
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_four_field_cardview_list, viewGroup, false);
            return new ViewHolder(view);
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public LinearLayout ll_content;
            public AlwaysMarqueeTextView tv_nameCn; // 名称（公司名称）
            public AlwaysMarqueeTextView tv_down_left; // 左下暂时没用到
            public AppCompatTextView tv_country; // 国家
            public AppCompatTextView tv_status; // 状态

            public ViewHolder(final View itemView) {
                super(itemView);
                tv_nameCn = (AlwaysMarqueeTextView) itemView.findViewById(R.id.tv_top_left);
                tv_down_left = (AlwaysMarqueeTextView) itemView.findViewById(R.id.tv_down_left);
                tv_country = (AppCompatTextView) itemView.findViewById(R.id.tv_top_right);
                tv_status = (AppCompatTextView) itemView.findViewById(R.id.tv_down_right);

                ll_content = (LinearLayout) itemView.findViewById(R.id.ll_content);
                ll_content.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        T_ContactsCompanyEntity.RowsEntity rowsEntity = list.get(getLayoutPosition());
                        T_ContactsCompanyDetailsActivity_.intent(T_ContactsCompanyListFragment.this).extra(AppDelegate.ROWS_ENTITY, rowsEntity).start();
                    }
                });
            }
        }

        @Override
        public void onBindViewHolder(ViewHolder viewHolder, int position) {
            T_ContactsCompanyEntity.RowsEntity entity = list.get(position);

            viewHolder.tv_down_left.setText("");
            viewHolder.tv_nameCn.setText(entity.getNameCn());
            viewHolder.tv_country.setText(entity.getCountry());

            // <!-- 0：新制 1：待批，2：审批通过 -2：审批不通过-->
            int status = entity.getStatus();
            switch (status) {
                case 0: // 新制
                    viewHolder.tv_status.setText(getStrings(R.string.brand_new));
                    viewHolder.tv_status.setTextColor(getColors(R.color.orange_500));
                    break;
                case 1: // 待批
                    viewHolder.tv_status.setText(getStrings(R.string.wait_approve));
                    viewHolder.tv_status.setTextColor(getColors(R.color.color_theme_light));
                    break;
                case 2: // 审批同意
                    viewHolder.tv_status.setText(getStrings(R.string.pass_approve));
                    viewHolder.tv_status.setTextColor(getColors(R.color.green_300));
                    break;
                case -2: // 审批不同意
                    viewHolder.tv_status.setText(getStrings(R.string.nopass_approve));
                    viewHolder.tv_status.setTextColor(getColors(R.color.red_500));
                    break;
                default:
                    viewHolder.tv_status.setText("");
                    break;
            }

        }

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

    @Override
    public void onDestroy() {
        mRequestQueue.cancelAll(this);
        super.onDestroy();
    }

}
