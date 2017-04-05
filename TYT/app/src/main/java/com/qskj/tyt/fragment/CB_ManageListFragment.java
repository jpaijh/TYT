package com.qskj.tyt.fragment;

import android.content.SharedPreferences;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
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
import com.qskj.tyt.activity.CB_ManageDetailsActivity_;
import com.qskj.tyt.entity.CB_DeclareManageEntity;
import com.qskj.tyt.entity.CB_ManageListEntity;
import com.qskj.tyt.utils.StringUtil;
import com.qskj.tyt.view.AlwaysMarqueeTextView;
import com.umeng.analytics.MobclickAgent;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 管理列表：进仓单管理列表（经营单位，货代），入库管理列表（仓库）,报关行（报关单管理列表，清单管理列表）
 */
@EFragment(R.layout.swiperefreshlayout_recyclerview_tvnodata)
public class CB_ManageListFragment extends BaseFragment {

    private static final String TAG = "CB_ManageListFragment";

    private ManageListAdapter mManageListAdapter;
    private ListManageListAdapter mListManageListAdapter;
    private List<CB_ManageListEntity.RowsEntity> rowsEntityManage;
    private DeclareManageListAdapter mDeclareManageListAdapter;
    private List<CB_DeclareManageEntity.RowsEntity> rowsEntityDeclareManage;
    private int lastVisibleItem;
    private int totalPage;
    private int pageIndex = 1;
    private boolean isLoading;
    private boolean isFirstLoading;
    private LinearLayoutManager mLinearLayoutManager;
    private RequestQueue mRequestQueue;
    private String toolbarTitle;
    private String REQUEST_API;
    private int accountNature;
    private SharedPreferences userInfoSp;

    @ViewById(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    @ViewById(R.id.recyclerview)
    RecyclerView mRecyclerView;

    @ViewById(R.id.tv_no_data)
    AppCompatTextView tv_no_data;

    @Override
    public void onAfterViews() {
        toolbarTitle = getArguments().getString(AppDelegate.TOOLBAR_TITLE);
        REQUEST_API = getArguments().getString(AppDelegate.REQUEST_API);
        userInfoSp = MyApplication_.getInstance().getUserInfoSp();
        accountNature = userInfoSp.getInt(AppDelegate.ACCOUNT_NATURE, -1);
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
                if ((mManageListAdapter != null && lastVisibleItem + 1 == mManageListAdapter.getItemCount()) ||
                        (mDeclareManageListAdapter != null && lastVisibleItem + 1 == mDeclareManageListAdapter.getItemCount()) ||
                        (mListManageListAdapter != null && lastVisibleItem + 1 == mListManageListAdapter.getItemCount())
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
                saveCurrentTime(TAG + accountNature + toolbarTitle);
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
                createRefreshSnackbar(mRecyclerView, TAG + accountNature + toolbarTitle);
            }
        });
    }

    @Override
    public void onBackgrounds() {
        // 请求获取 管理列表
        final String url = MyAPI.getBaseUrl() + REQUEST_API + "&pageSize=10&pageIndex=" + pageIndex;
        JsonObjectRequest listRequest = new JsonObjectRequest(Request.Method.GET, url, new Response.Listener<org.json.JSONObject>() {
            @Override
            public void onResponse(org.json.JSONObject response) {
                LogUtils.d("\n管理列表-URL:" + url + "\n管理列表-RESPONSE:" + response.toString());

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
                    if (accountNature == AppDelegate.ACCOUNT_NATURE_DECLARE) { // 报关行
                        if (toolbarTitle.equals(getStrings(R.string.title_activity_declaremanage))) { // 报关单管理
                            rowsEntityDeclareManage = JSON.parseArray(rows.toString(), CB_DeclareManageEntity.RowsEntity.class);
                            mDeclareManageListAdapter = new DeclareManageListAdapter(rowsEntityDeclareManage);
                            mRecyclerView.setAdapter(mDeclareManageListAdapter);
                        } else if (toolbarTitle.equals(getStrings(R.string.title_activity_listmanage))) { // 清单管理
                            rowsEntityManage = JSON.parseArray(rows.toString(), CB_ManageListEntity.RowsEntity.class);
                            mListManageListAdapter = new ListManageListAdapter(rowsEntityManage);
                            mRecyclerView.setAdapter(mListManageListAdapter);
                        }

                    } else {  // 进仓单管理（经营单位，货代），入库管理（仓库）
                        rowsEntityManage = JSON.parseArray(rows.toString(), CB_ManageListEntity.RowsEntity.class);
                        mManageListAdapter = new ManageListAdapter(rowsEntityManage);
                        mRecyclerView.setAdapter(mManageListAdapter);
                    }
                    if (!isFirstLoading)
                        createRefreshCompleteSnackbar(mRecyclerView);
                } else if (pageIndex <= totalPage) {
                    JSONArray rows = jsonObject.getJSONArray("rows");
                    if (accountNature == AppDelegate.ACCOUNT_NATURE_DECLARE) { // 报关行
                        if (toolbarTitle.equals(getStrings(R.string.title_activity_declaremanage))) { // 报关单管理
                            rowsEntityDeclareManage.addAll(lastVisibleItem + 1, JSON.parseArray(rows.toString(), CB_DeclareManageEntity.RowsEntity.class));
                            mDeclareManageListAdapter.notifyDataSetChanged();
                        } else if (toolbarTitle.equals(getStrings(R.string.title_activity_listmanage))) { // 清单管理
                            rowsEntityManage.addAll(lastVisibleItem + 1, JSON.parseArray(rows.toString(), CB_ManageListEntity.RowsEntity.class));
                            mListManageListAdapter.notifyDataSetChanged();
                        }
                    } else {
                        rowsEntityManage.addAll(lastVisibleItem + 1, JSON.parseArray(rows.toString(), CB_ManageListEntity.RowsEntity.class));
                        mManageListAdapter.notifyDataSetChanged();
                    }
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

    // 经营单位，货代（进仓单管理列表Adapter） 仓库（入库管理列表Adapter）
    private class ManageListAdapter extends RecyclerView.Adapter<ManageListAdapter.ViewHolder> {

        public ManageListAdapter(List<CB_ManageListEntity.RowsEntity> list) {
            this.list = list;
        }

        private List<CB_ManageListEntity.RowsEntity> list;

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
                        CB_ManageDetailsActivity_.intent(getActivity()).extra(AppDelegate.ID, rowsEntity.getId())
                                .extra(AppDelegate.TOOLBAR_TITLE, rowsEntity.getDeliveryInvoiceCode()).start();
                    }
                });
            }
        }

        @Override
        public void onBindViewHolder(ViewHolder viewHolder, int position) {
            CB_ManageListEntity.RowsEntity entity = list.get(position);

            viewHolder.tv_storageListGrid_deliveryInvoiceCode.setText(entity.getDeliveryInvoiceCode());
            viewHolder.tv_storageListGrid_blNo.setText(entity.getBlNo());
            viewHolder.tv_storageListGrid_deliveryDate.setText(StringUtil.dateRemoveT(entity.getDeliveryDate()));

            switch (accountNature) {
                case AppDelegate.ACCOUNT_NATURE_MANAGEMENT_UNIT: // 经营单位
                    viewHolder.tv_storageListGrid_businessEnterpriseName.setText(entity.getAccountName());
                    break;
                case AppDelegate.ACCOUNT_NATURE_FORWARDER: // 货代
                    viewHolder.tv_storageListGrid_businessEnterpriseName.setText(entity.getBusinessEnterpriseName());
                    break;
                case AppDelegate.ACCOUNT_NATURE_WAREHOUSE: // 仓库
                    viewHolder.tv_storageListGrid_businessEnterpriseName.setText(entity.getBusinessEnterpriseName());
                    break;
            }

            int status = entity.getStorageStatus();
            switch (status) {
                case 1: // 新制
                    viewHolder.tv_storageListGrid_storageStatus.setText(getStrings(R.string.xinzhi));
                    viewHolder.tv_storageListGrid_storageStatus.setTextColor(getColors(R.color.red_500));
                    break;
                case 2: // 已录入
                    viewHolder.tv_storageListGrid_storageStatus.setText(getStrings(R.string.yiluru));
                    viewHolder.tv_storageListGrid_storageStatus.setTextColor(getColors(R.color.cyan_700));
                    break;
                case 3: // 已确认
                    viewHolder.tv_storageListGrid_storageStatus.setText(getStrings(R.string.yiqueren));
                    viewHolder.tv_storageListGrid_storageStatus.setTextColor(getColors(R.color.deep_purple_500));
                    break;
                case 4: // 待收货
                    viewHolder.tv_storageListGrid_storageStatus.setText(getStrings(R.string.daishouhuo));
                    viewHolder.tv_storageListGrid_storageStatus.setTextColor(getColors(R.color.yellow_900));
                    break;
                case 5: // 已收货
                    viewHolder.tv_storageListGrid_storageStatus.setText(getStrings(R.string.yishouhuo));
                    viewHolder.tv_storageListGrid_storageStatus.setTextColor(getColors(R.color.orange_700));
                    break;
                case 6: // 已申报
                    viewHolder.tv_storageListGrid_storageStatus.setText(getStrings(R.string.yishenbao));
                    viewHolder.tv_storageListGrid_storageStatus.setTextColor(getColors(R.color.blue_700));
                    break;
                case 7: // 已报关
                    viewHolder.tv_storageListGrid_storageStatus.setText(getStrings(R.string.yibaoguan));
                    viewHolder.tv_storageListGrid_storageStatus.setTextColor(getColors(R.color.green_300));
                    break;
                default: // 状态若没有匹配的，显示“”
                    viewHolder.tv_storageListGrid_storageStatus.setText("");
                    break;

            }

        }

    }

    // 报关行（报关单管理列表Adapter）
    private class DeclareManageListAdapter extends RecyclerView.Adapter<DeclareManageListAdapter.ViewHolder> {

        private List<CB_DeclareManageEntity.RowsEntity> list;

        public DeclareManageListAdapter(List<CB_DeclareManageEntity.RowsEntity> list) {
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
            public AppCompatTextView tv_declare_manage_code; // Code
            public AppCompatTextView tv_declare_manage_blNo; // 提单编号
            public AppCompatTextView tv_declare_manage_customsFlag; // 未报关/已报关
            public AppCompatTextView tv_declare_manage_createDate; // 创建日期
            public AppCompatTextView tv_declare_manage_actualQuantity; // 实际出仓
            public AppCompatTextView tv_declare_manage_packageQuantity; // 报关件数
            public AlwaysMarqueeTextView tv_declare_manage_exportPort; // 开始港口
            public AppCompatTextView tv_declare_manage_destinationPort; // 终止港口

            public ViewHolder(final View itemView) {
                super(itemView);
                tv_declare_manage_code = (AppCompatTextView) itemView.findViewById(R.id.tv_declare_manage_code);
                tv_declare_manage_createDate = (AppCompatTextView) itemView.findViewById(R.id.tv_declare_manage_createDate);
                tv_declare_manage_blNo = (AppCompatTextView) itemView.findViewById(R.id.tv_declare_manage_blNo);
                tv_declare_manage_customsFlag = (AppCompatTextView) itemView.findViewById(R.id.tv_declare_manage_customsFlag);
                tv_declare_manage_actualQuantity = (AppCompatTextView) itemView.findViewById(R.id.tv_declare_manage_actualQuantity);
                tv_declare_manage_packageQuantity = (AppCompatTextView) itemView.findViewById(R.id.tv_declare_manage_packageQuantity);
                tv_declare_manage_exportPort = (AlwaysMarqueeTextView) itemView.findViewById(R.id.tv_declare_manage_exportPort);
                tv_declare_manage_destinationPort = (AppCompatTextView) itemView.findViewById(R.id.tv_declare_manage_destinationPort);
                rl_content = (RelativeLayout) itemView.findViewById(R.id.rl_content);
                rl_content.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        CB_DeclareManageEntity.RowsEntity rowsEntity = list.get(getLayoutPosition());
                        CB_ManageDetailsActivity_.intent(getActivity()).extra(AppDelegate.ID, rowsEntity.getOrderId())
                                .extra(AppDelegate.TOOLBAR_TITLE, rowsEntity.getCode())
                                .extra(AppDelegate.IS_CUSTOMS_FLAG, rowsEntity.isCustomsFlag())
                                .extra(AppDelegate.MANAGE_UI_NAME, toolbarTitle)
                                .start();

                    }
                });
            }
        }

        @Override
        public void onBindViewHolder(ViewHolder viewHolder, int position) {
            CB_DeclareManageEntity.RowsEntity entity = list.get(position);

            viewHolder.tv_declare_manage_code.setText(entity.getCode());
            viewHolder.tv_declare_manage_blNo.setText(entity.getBlNo());
            if (entity.isCustomsFlag()) {
                viewHolder.tv_declare_manage_customsFlag.setText("已报关");
                viewHolder.tv_declare_manage_customsFlag.setTextColor(getColors(R.color.green_300));
            } else {
                viewHolder.tv_declare_manage_customsFlag.setText("未报关");
                viewHolder.tv_declare_manage_customsFlag.setTextColor(getColors(R.color.red_500));
            }
            viewHolder.tv_declare_manage_createDate.setText(StringUtil.dateRemoveT(entity.getCreateDate()));

            viewHolder.tv_declare_manage_actualQuantity.setText("实际出仓：" + entity.getActualQuantity());
            viewHolder.tv_declare_manage_packageQuantity.setText("报关件数：" + entity.getPackageQuantity());
            viewHolder.tv_declare_manage_exportPort.setText(entity.getExportPort());
            viewHolder.tv_declare_manage_destinationPort.setText(entity.getDestinationPort());

        }

    }

    // 报关行（清单管理列表Adapter）
    private class ListManageListAdapter extends RecyclerView.Adapter<ListManageListAdapter.ViewHolder> {

        private List<CB_ManageListEntity.RowsEntity> list;

        public ListManageListAdapter(List<CB_ManageListEntity.RowsEntity> list) {
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
            public AlwaysMarqueeTextView tv_list_manage_deliveryInvoiceCode; // 进仓单编号
            public AppCompatTextView tv_list_manage_customsAreaEntryStatus;  // 状态
            public AppCompatTextView tv_list_manage_forwarderConfirmed; // 确认状态
            public AppCompatTextView tv_list_manage_deliveryDate; // 创建日期
            public AppCompatTextView tv_list_manage_enumber; // 出仓
            public AppCompatTextView tv_list_manage_inventorynum; //库存
            public AppCompatTextView tv_list_manage_inwarehousenum; //进仓

            public ViewHolder(final View itemView) {
                super(itemView);
                tv_list_manage_deliveryInvoiceCode = (AlwaysMarqueeTextView) itemView.findViewById(R.id.tv_list_manage_deliveryInvoiceCode);
                tv_list_manage_forwarderConfirmed = (AppCompatTextView) itemView.findViewById(R.id.tv_list_manage_forwarderConfirmed);
                tv_list_manage_deliveryDate = (AppCompatTextView) itemView.findViewById(R.id.tv_list_manage_deliveryDate);
                tv_list_manage_customsAreaEntryStatus = (AppCompatTextView) itemView.findViewById(R.id.tv_list_manage_customsAreaEntryStatus);
                tv_list_manage_enumber = (AppCompatTextView) itemView.findViewById(R.id.tv_listmanage_enumber);
                tv_list_manage_inventorynum = (AppCompatTextView) itemView.findViewById(R.id.tv_listmanage_inventorynum);
                tv_list_manage_inwarehousenum = (AppCompatTextView) itemView.findViewById(R.id.tv_listmanage_inwarehousenum);
                rl_content = (RelativeLayout) itemView.findViewById(R.id.rl_content);
                rl_content.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        CB_ManageListEntity.RowsEntity rowsEntity = list.get(getLayoutPosition());
                        CB_ManageDetailsActivity_.intent(getActivity()).extra(AppDelegate.ID, rowsEntity.getId())
                                .extra(AppDelegate.MANAGE_UI_NAME, toolbarTitle)
                                .extra(AppDelegate.TOOLBAR_TITLE, rowsEntity.getDeliveryInvoiceCode()).start();
                    }
                });
            }
        }

        @Override
        public void onBindViewHolder(ViewHolder viewHolder, int position) {
            CB_ManageListEntity.RowsEntity entity = list.get(position);
            viewHolder.tv_list_manage_deliveryInvoiceCode.setText(entity.getDeliveryInvoiceCode());
            viewHolder.tv_list_manage_deliveryDate.setText(StringUtil.dateRemoveT(entity.getDeliveryDate()));
            viewHolder.tv_list_manage_inwarehousenum.setText("进仓:" + entity.getPackingQuantity());
            viewHolder.tv_list_manage_inventorynum.setText("库存:" + entity.getLeftQuantity());
            viewHolder.tv_list_manage_enumber.setText("出仓:" + entity.getOutQuantity());

            if (entity.isForwarderConfirmed()) {
                viewHolder.tv_list_manage_forwarderConfirmed.setText("已确认");
                viewHolder.tv_list_manage_forwarderConfirmed.setTextColor(getColors(R.color.green_300));
            } else {
                viewHolder.tv_list_manage_forwarderConfirmed.setText("未确认");
                viewHolder.tv_list_manage_forwarderConfirmed.setTextColor(getColors(R.color.red_500));
            }

            viewHolder.tv_list_manage_customsAreaEntryStatus.setText(entity.getCustomsAreaEntryStatusFormat());
            int customsAreaEntryStatus = entity.getCustomsAreaEntryStatus();
            switch (customsAreaEntryStatus) {
                case 0: // 未申报
                    viewHolder.tv_list_manage_customsAreaEntryStatus.setTextColor(getColors(R.color.orange_700));
                    break;
                case 11: // 已报海关
                    viewHolder.tv_list_manage_customsAreaEntryStatus.setTextColor(getColors(R.color.cyan_700));
                    break;
                case 12: // 放行
                    viewHolder.tv_list_manage_customsAreaEntryStatus.setTextColor(getColors(R.color.green_300));
                    break;
                case 13: // 未放行
                    viewHolder.tv_list_manage_customsAreaEntryStatus.setTextColor(getColors(R.color.red_500));
                    break;
                case 14: // 查验
                    viewHolder.tv_list_manage_customsAreaEntryStatus.setTextColor(getColors(R.color.deep_purple_500));
                    break;
                case 15: // 已移位查验
                    viewHolder.tv_list_manage_customsAreaEntryStatus.setTextColor(getColors(R.color.blue_700));
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
