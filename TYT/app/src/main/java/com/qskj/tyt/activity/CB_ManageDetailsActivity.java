package com.qskj.tyt.activity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.ClipboardManager;
import android.text.Html;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.alibaba.fastjson.JSON;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.apkfuns.logutils.LogUtils;
import com.lsjwzh.widget.materialloadingprogressbar.CircleProgressBar;
import com.qskj.tyt.AppDelegate;
import com.qskj.tyt.MyAPI;
import com.qskj.tyt.MyApplication_;
import com.qskj.tyt.R;
import com.qskj.tyt.adapter.TableLayoutAdapter;
import com.qskj.tyt.entity.CB_ManageDetailsEntity;
import com.qskj.tyt.fragment.CB_ManageDetailsFragment_CommodityInfo_;
import com.qskj.tyt.fragment.CB_ManageDetailsFragment_DeclareInfo_;
import com.qskj.tyt.fragment.CB_ManageDetailsFragment_WarehouseInfo_;
import com.qskj.tyt.utils.JsonObjectPostRequest;
import com.qskj.tyt.utils.StringUtil;
import com.qskj.tyt.utils.ToastUtil;
import com.qskj.tyt.view.PopupWindow.ActionItem;
import com.qskj.tyt.view.PopupWindow.TitlePopup;
import com.qskj.tyt.view.TimeLine.TimeLineAdapter;
import com.qskj.tyt.view.TimeLine.TimeLineEntity;
import com.umeng.analytics.MobclickAgent;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 管理列表详情页：进仓单管理详情（经营单位，货代），入库管理详情（仓库）,报关行（报关单管理详情，清单管理详情）
 */
@EActivity(R.layout.cb_activity_manage_details)
public class CB_ManageDetailsActivity extends BaseActivity {

    private int id;
    private int accountNature;
    private RequestQueue mRequestQueue;
    // 货代需要
    private String blNo;
    private ProgressDialog mProgressDialog;
    private CB_ManageDetailsEntity CBManageDetailsEntity;

    // 共有部分
    @ViewById(R.id.progressbar)
    CircleProgressBar mProgressbar;

    @ViewById(R.id.tv_title)
    AppCompatTextView tv_title;

    @ViewById(R.id.toolbar)
    Toolbar mToolbar;

    // 头部布局
    @ViewById(R.id.frameLayout_head)
    FrameLayout mFrameLayoutHead;

    @ViewById(R.id.tv_head_big_title)
    AppCompatTextView tv_head_big_title; // 默认显示（经营单位和仓库：进仓通知单 ，货代：accountName）

    @ViewById(R.id.tv_head_title)
    AppCompatTextView tv_head_title; // 默认显示（经营单位：货物状态，货代：货物状态，仓库：进仓时间）

    @ViewById(R.id.tv_head_subtitle)
    AppCompatTextView tv_head_subtitle; // 默认显示（经营单位：入库时间，货代：已（未）确认清单 仓库：没用到需要隐藏）

    @ViewById(R.id.tv_head_subtitle2)
    AppCompatTextView tv_head_subtitle2; // 默认隐藏的，要用的话先要显示它

    // 经营单位 仓库 下部布局
    @ViewById(R.id.ll_tablayout_viewpager)
    LinearLayout ll_tablayout_viewpager;

    // 货代下部布局
    @ViewById(R.id.nestedScrollView_hd)
    NestedScrollView nestedScrollView_hd;

    // 经营单位 仓库
    @ViewById(R.id.tabLayout)
    TabLayout mTabLayout;

    @ViewById(R.id.viewpager)
    ViewPager mViewPager;

    // 货代
    @ViewById(R.id.time_line_recycler)
    RecyclerView time_line_recycler;

    @ViewById(R.id.tv_receiverName)
    AppCompatTextView tv_receiverName;

    @ViewById(R.id.tv_deliveryInvoiceCode)
    AppCompatTextView tv_deliveryInvoiceCode;

    @ViewById(R.id.tv_commodityDescCn)
    AppCompatTextView tv_commodityDescCn;

    @ViewById(R.id.tv_vol)
    AppCompatTextView tv_vol;

    @ViewById(R.id.tv_packingQuantity)
    AppCompatTextView tv_packingQuantity;

    @ViewById(R.id.tv_grossWeight)
    AppCompatTextView tv_grossWeight;

    @ViewById(R.id.tv_destinationPortCn)
    AppCompatTextView tv_destinationPortCn;

    @ViewById(R.id.tv_frontMark)
    AppCompatTextView tv_frontMark;

    @ViewById(R.id.tv_deliveryCarNo)
    AppCompatTextView tv_deliveryCarNo;

    @ViewById(R.id.tv_deliveryCarDriverTelephone)
    AppCompatTextView tv_deliveryCarDriverTelephone;

    @ViewById(R.id.tv_warehouseRemark)
    AppCompatTextView tv_warehouseRemark;

    @ViewById(R.id.tv_warehouseAddress)
    AppCompatTextView tv_warehouseAddress;

    @ViewById(R.id.tv_schedule)
    AppCompatTextView tv_schedule;

    @ViewById(R.id.tv_message)
    AppCompatTextView tv_message;

    @ViewById(R.id.ll_check_map)
    LinearLayout ll_check_map;

    @Override
    public void onAfterViews() {
        id = getIntent().getIntExtra(AppDelegate.ID, 0);
        accountNature = MyApplication_.getInstance().getUserInfoSp().getInt(AppDelegate.ACCOUNT_NATURE, -1);
        mRequestQueue = Volley.newRequestQueue(this);
        showView(mProgressbar);
        initToolbar();
        onBackgrounds();
    }

    public void initToolbar() {
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        tv_title.setText(getIntent().getStringExtra(AppDelegate.TOOLBAR_TITLE));
    }

    @Override
    public void onBackgrounds() {
        final String url = MyAPI.getBaseUrl() + "/api/Orders/Order/GetById?id=" + id;
        final JsonObjectRequest detailsRequest = new JsonObjectRequest(Request.Method.GET, url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                LogUtils.d("\n列表详情-URL:" + url + "\n列表详情-RESPONSE:" + response.toString());

                CBManageDetailsEntity = JSON.parseObject(response.toString(), CB_ManageDetailsEntity.class);

                if (accountNature == AppDelegate.ACCOUNT_NATURE_MANAGEMENT_UNIT) { // 经营单位（进仓单管理）
                    blNo = CBManageDetailsEntity.getOrderExport().getBlNo();// 经营单位：提单编号：用来 预录入单 查看

                    tv_head_big_title.setText("进仓通知单");
                    initHeadTitle();
                    String startTimeOfDelivery = CBManageDetailsEntity.getOrderDelivery().getStartTimeOfDelivery();
                    String latestTimeOfDelivery = CBManageDetailsEntity.getOrderDelivery().getLatestTimeOfDelivery();
                    tv_head_subtitle.setText("请于：" + StringUtil.YMDDtoYMD(startTimeOfDelivery) + " 至 " + StringUtil.YMDDtoYMD(latestTimeOfDelivery) + "\n之内安排进仓");
                    initFragments();

                } else if (accountNature == AppDelegate.ACCOUNT_NATURE_FORWARDER) { // 货代（进仓单管理）
                    tv_head_big_title.setText(CBManageDetailsEntity.getAccountName());
                    initHeadTitle();
                    if (CBManageDetailsEntity.getOrderDelivery().isForwarderConfirmed()) {
                        tv_head_subtitle.setText("已确认清单");
                    } else {
                        tv_head_subtitle.setText("未确认清单");
                    }

                    CB_ManageDetailsEntity.OrderDeliveryEntity orderDelivery = CBManageDetailsEntity.getOrderDelivery();
                    CB_ManageDetailsEntity.OrderExportEntity orderExport = CBManageDetailsEntity.getOrderExport();

                    // 进仓进度状态信息
                    List<TimeLineEntity> timeLineEntityList = new ArrayList<>();
                    List<CB_ManageDetailsEntity.OrderDeliveryEntity.NodesEntity> nodes = orderDelivery.getNodes();
                    for (int i = 0; i < nodes.size(); i++) {
                        int nodeStatus = nodes.get(i).getNodeStatus();
                        String name = nodes.get(i).getName();
                        if (nodeStatus != 0) {
                            String date = StringUtil.dateRemoveT(nodes.get(i).getActionStatus().getOperateDate());
                            timeLineEntityList.add(new TimeLineEntity(name, date, nodeStatus, CB_ManageDetailsActivity.this));
                        } else {
                            timeLineEntityList.add(new TimeLineEntity(name, nodeStatus, CB_ManageDetailsActivity.this));
                        }
                    }
                    LinearLayoutManager layoutManager = new LinearLayoutManager(CB_ManageDetailsActivity.this);
                    TimeLineAdapter adapter = new TimeLineAdapter(timeLineEntityList);
                    time_line_recycler.setLayoutManager(layoutManager);
                    time_line_recycler.setAdapter(adapter);
                    time_line_recycler.setNestedScrollingEnabled(false);

                    tv_receiverName.setText(orderDelivery.getReceiverName());
                    tv_deliveryInvoiceCode.setText(orderDelivery.getDeliveryInvoiceCode());
                    String strings = "预计船期为 "
                            + "<font color='black'><b>" + StringUtil.dateRemoveT(orderDelivery.getEstimatedSchedual()) + "</b></font>"
                            + " ，" + "届时将通知贵司具体出运情况，货物请务必于 "
                            + "<font color='black'><b>" + StringUtil.YMDDtoYMD(orderDelivery.getStartTimeOfDelivery()) + "</b></font>" + " 至 "
                            + "<font color='black'><b>" + StringUtil.YMDDtoYMD(orderDelivery.getLatestTimeOfDelivery()) + "</b></font>" + " 之内安排前进仓。";
                    tv_schedule.setText(Html.fromHtml(strings));
                    tv_commodityDescCn.setText(orderExport.getCommodityDescCn());
                    tv_vol.setText(StringUtil.numberFormat(orderExport.getVol()) + " " + orderExport.getVolUnitEn());
                    tv_packingQuantity.setText(StringUtil.numberFormat(orderExport.getPackingQuantity()) + " " + orderExport.getPackingUnitEn());
                    tv_grossWeight.setText(StringUtil.numberFormat(orderExport.getGrossWeight()) + " " + orderExport.getWeightUnitEn());
                    tv_destinationPortCn.setText(orderExport.getDestinationPortCn());
                    tv_frontMark.setText(orderExport.getFrontMark());
                    tv_deliveryCarNo.setText(orderDelivery.getDeclareNo());
                    tv_deliveryCarDriverTelephone.setText(orderDelivery.getDeliveryCarDriverTelephone());
                    String strDeclare = "报关行信息：" + orderDelivery.getDeclareEnterpriseName() + "\n十位代码：    "
                            + orderDelivery.getDeclareEnterpriseCode() + "\n请及时做好电子委托！";
                    tv_message.setText(strDeclare);
                    tv_warehouseRemark.setText(orderDelivery.getWarehouseRemark());

                    String warehouseAddress = orderDelivery.getWarehouseAddress();
                    if (TextUtils.isEmpty(warehouseAddress)) {
                        ll_check_map.setVisibility(View.GONE);
                    } else {
                        ll_check_map.setVisibility(View.VISIBLE);
                    }
                    tv_warehouseAddress.setText(warehouseAddress);

                    showView(nestedScrollView_hd);
                } else if (accountNature == AppDelegate.ACCOUNT_NATURE_WAREHOUSE) { // 仓库（入库管理）
                    tv_head_big_title.setText("进仓通知单");
                    tv_head_title.setText("进仓时间：" + StringUtil.dateRemoveT(CBManageDetailsEntity.getOrderDelivery().getLatestTimeOfDelivery()));
                    hideView(tv_head_subtitle);
                    initFragments();
                } else if (accountNature == AppDelegate.ACCOUNT_NATURE_DECLARE) { // 报关行
                    tv_head_subtitle.setText("创建日期：" + StringUtil.dateRemoveT(CBManageDetailsEntity.getCreateDate()));

                    String MANAGE_UI_NAME = getIntent().getStringExtra(AppDelegate.MANAGE_UI_NAME);
                    if (MANAGE_UI_NAME.equals(getStrings(R.string.title_activity_declaremanage))) { // 报关单管理
                        tv_head_big_title.setText("报关单查看");
                        tv_head_title.setText(getIntent().getBooleanExtra(AppDelegate.IS_CUSTOMS_FLAG, false) ? "已提交报关" : "未提交报关");
                        initFragments();
                    } else if (MANAGE_UI_NAME.equals(getStrings(R.string.title_activity_listmanage))) { // 清单管理
                        tv_head_big_title.setText("出口申报清单");
                        tv_head_title.setText(CBManageDetailsEntity.getOrderDelivery().isGeneratedDeclarationFlag() ? "已生成报关单" : "未生成报关单");
                        showView(tv_head_subtitle2);
                        tv_head_subtitle2.setText(CBManageDetailsEntity.getOrderDelivery().isForwarderConfirmed() ? "清单状态：已确认" : "清单状态：未确认");
                        initFragments();
                    }
                }

                hideView(mProgressbar);
                showView(mFrameLayoutHead);
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

        detailsRequest.setTag(this);
        mRequestQueue.add(detailsRequest);
    }

    // 经营单位 货代
    private void initHeadTitle() {
        float detailTotalActualInPackingQuantity = CBManageDetailsEntity.getOrderExport().getDetailTotalActualInPackingQuantity(); // 实际进仓件数
        float detailTotalPlanInPackingQuantity = CBManageDetailsEntity.getOrderExport().getDetailTotalPlanInPackingQuantity(); // 预计进仓件数
        if (detailTotalActualInPackingQuantity == 0) {
            tv_head_title.setText("货物状态：待收货");
        } else if (detailTotalActualInPackingQuantity == detailTotalPlanInPackingQuantity) {
            tv_head_title.setText("货物状态：已收货");
        } else if (detailTotalPlanInPackingQuantity > detailTotalActualInPackingQuantity) {
            tv_head_title.setText("货物状态：部分收货");
        }
    }

    // 经营单位 仓库
    private void initFragments() {
        if (mViewPager != null) {
            Bundle bundle = new Bundle();
            bundle.putSerializable(AppDelegate.MANAGE_DETAILS_ENTITY, CBManageDetailsEntity);
            TableLayoutAdapter mAdapter = new TableLayoutAdapter(getSupportFragmentManager());

            if (accountNature != AppDelegate.ACCOUNT_NATURE_DECLARE) { // 报关行：报关单管理详情和清单管理详情都不需要仓单信息
                CB_ManageDetailsFragment_WarehouseInfo_ fragmentWarehouseInfo = new CB_ManageDetailsFragment_WarehouseInfo_();
                fragmentWarehouseInfo.setArguments(bundle);
                mAdapter.addFragment(fragmentWarehouseInfo, "仓单信息");
            }

            CB_ManageDetailsFragment_DeclareInfo_ fragmentDeclareInfo = new CB_ManageDetailsFragment_DeclareInfo_();
            fragmentDeclareInfo.setArguments(bundle);
            mAdapter.addFragment(fragmentDeclareInfo, "报关信息");

            if (CBManageDetailsEntity.getOrderExport().getDetailList().size() != 0) {
                CB_ManageDetailsFragment_CommodityInfo_ fragmentCommodityInfo = new CB_ManageDetailsFragment_CommodityInfo_();
                fragmentCommodityInfo.setArguments(bundle);
                mAdapter.addFragment(fragmentCommodityInfo, "商品信息");
            }

            mViewPager.setAdapter(mAdapter);
            mTabLayout.setupWithViewPager(mViewPager);
        }

        showView(ll_tablayout_viewpager);
    }

    /**
     * 货代：拨打给司机
     */
    @Click(R.id.tv_deliveryCarDriverTelephone)
    void callCarDriver() {
        String deliveryCarDriverTelephone = tv_deliveryCarDriverTelephone.getText().toString().trim();
        if (!TextUtils.isEmpty(deliveryCarDriverTelephone)) {
            callNumber(deliveryCarDriverTelephone);
        }
    }

    /**
     * 查看地图
     */
    @Click(R.id.tv_check_map)
    void checkMap() {
        CB_BaiduMapActivity_.intent(this).start();
    }

    /**
     * 货代：查看大图
     */
    @Click(R.id.iv_location)
    void checkLargeImage() {
        CB_DisplayLargeImageActivity_.intent(this).start();
    }

    // 货代：拨打电话
    @Click(R.id.tv_phonenumber1)
    void callNumber1() {
        callNumber("0574-26896830");
    }

    @Click(R.id.tv_phonenumber2)
    void callNumber2() {
        callNumber("0574-26896930");
    }

    @Click(R.id.tv_phonenumber3)
    void callNumber3() {
        callNumber("0574-26896931");
    }

    @Click(R.id.tv_phonenumber4)
    void callNumber4() {
        callNumber("0574-26896932");
    }

    /**
     * 打开查看货物的网址
     */
    @Click(R.id.ll_check_goods)
    void checkGoods() {
        CB_WebActivity_.intent(this).extra(AppDelegate.URL, "http://www.nbskylark.com/csccmis/csccmis.asp").extra(AppDelegate.TOOLBAR_TITLE, "查货").start();
    }

    /**
     * 拨打电话：传入电话号码就好
     *
     * @param number
     */
    public void callNumber(final String number) {
        String[] strings = {"拨打", "新建联系人", "复制号码"};
        AlertDialog.Builder builder = new AlertDialog.Builder(CB_ManageDetailsActivity.this, R.style.MyAlertDialogStyle);
        builder.setTitle(number)
                .setItems(strings, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0: // 直接拨打电话
                                Intent intent_call = new Intent(Intent.ACTION_CALL);
                                intent_call.setData(Uri.parse("tel:" + number));
                                if (ActivityCompat.checkSelfPermission(CB_ManageDetailsActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                                    //    ActivityCompat#requestPermissions
                                    // here to request the missing permissions, and then overriding
                                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                    //                                          int[] grantResults)
                                    // to handle the case where the user grants the permission. See the documentation
                                    // for ActivityCompat#requestPermissions for more details.
                                    return;
                                }
                                startActivity(intent_call);
                                break;
                            case 1: // 新建联系人
                                Intent intent_create_new_contact = new Intent(Intent.ACTION_INSERT);
                                intent_create_new_contact.setType("vnd.android.cursor.dir/person");
                                intent_create_new_contact.setType("vnd.android.cursor.dir/contact");
                                intent_create_new_contact.setType("vnd.android.cursor.dir/raw_contact");
                                intent_create_new_contact.putExtra(ContactsContract.Intents.Insert.PHONE, number);
                                // intent1.putExtra(ContactsContract.Intents.Insert.NAME, name);
                                startActivity(intent_create_new_contact);
                                break;
                            case 2: // 复制号码
                                ClipboardManager clip = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                                clip.setText(number);
                                ToastUtil.showToast(CB_ManageDetailsActivity.this, "复制成功");
                                break;
                        }
                    }
                }).setNegativeButton("关闭", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).create().show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (accountNature == AppDelegate.ACCOUNT_NATURE_MANAGEMENT_UNIT || accountNature == AppDelegate.ACCOUNT_NATURE_FORWARDER)
            getMenuInflater().inflate(R.menu.menu_more, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.action_more:
                TitlePopup titlePopup = new TitlePopup(this, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
                if (accountNature == AppDelegate.ACCOUNT_NATURE_MANAGEMENT_UNIT) {
                    titlePopup.addAction(new ActionItem(this, "预录入单", R.mipmap.ic_paste));
                    titlePopup.addAction(new ActionItem(this, "单据导出", R.mipmap.ic_export));
                    titlePopup.show(mToolbar);
                    titlePopup.setItemOnClickListener(new TitlePopup.OnItemOnClickListener() {
                        @Override
                        public void onItemClick(ActionItem item, int position) {
                            switch (position) {
                                case 0:// 预录入单
                                    // 如果提单号为空，requestYLR()请求将会返回很多字段(所以不要传空)
                                    if (blNo == null || "null".equals(blNo) || TextUtils.isEmpty(blNo)) {
                                        ToastUtil.showToast(CB_ManageDetailsActivity.this, "没有预录单信息，请确认是否已报关");
                                    } else {
                                        showProgressDialog();
                                        requestYLR();
                                    }
                                    break;
                                case 1: // 单据导出（写死的一个请求地址，需带上参数仓单id）
                                    String url = "http://61.164.68.34:8081/HappyServer/hrServlet?fileName=hte_2015_07_23142304799&targetVolume=LH&authId=anonymous_LH&variants=ID=" + id;
                                    CB_WebActivity_.intent(CB_ManageDetailsActivity.this).extra(AppDelegate.URL, url).extra(AppDelegate.TOOLBAR_TITLE, "单据导出").start();
                                    break;
                            }
                        }
                    });
                } else if (accountNature == AppDelegate.ACCOUNT_NATURE_FORWARDER) {
                    titlePopup.addAction(new ActionItem(this, "清单查看", R.mipmap.ic_paste));
                    titlePopup.show(mToolbar);
                    titlePopup.setItemOnClickListener(new TitlePopup.OnItemOnClickListener() {
                        @Override
                        public void onItemClick(ActionItem item, int position) {
                            switch (position) {
                                case 0: // 清单查看
                                    if (CBManageDetailsEntity != null)
                                        CB_ManageDetailsActivity_HD_ListCheck_.intent(CB_ManageDetailsActivity.this)
                                                .extra(AppDelegate.MANAGE_DETAILS_ENTITY, CBManageDetailsEntity).start();
                                    break;
                            }
                        }
                    });
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 进仓管理-经营单位：请求查看预录入单
     */
    @Background
    void requestYLR() {
        final String url = MyAPI.getBaseUrl() + "/api/Orders/Storage/ResolvePreCustomsDeclaration?blNo=" + blNo;
        JsonArrayRequest ylrRequest = new JsonArrayRequest(Request.Method.POST, url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                String res = response.toString();
                LogUtils.d("\n预录入单-URL:" + url + "\n预录入单-RESPONSE:" + res);
                //  [ {"blNo": "NGBNYC028386","customsDeclarationNo": "310420160549945415","queryUrl": "http://lh.nbytg.com/WebApp/AgentManage/biz/YTGEntryPage.aspx?t1=310420160549945415&t2=lh.nbytg.com"}]
                if (res.equals("[]")) {
                    mProgressDialog.dismiss();
                    ToastUtil.showToast(CB_ManageDetailsActivity.this, "提单编号 [" + blNo + "] 查询的预录入单不存在");
                } else {
                    mProgressDialog.dismiss();
                    com.alibaba.fastjson.JSONObject jsonObject = JSON.parseObject(JSON.parseArray(res).get(0).toString());
                    String customsDeclarationNo = jsonObject.getString("customsDeclarationNo");
                    String queryUrl = jsonObject.getString("queryUrl");
                    CB_WebActivity_.intent(CB_ManageDetailsActivity.this).extra(AppDelegate.URL, queryUrl).extra(AppDelegate.TOOLBAR_TITLE, "海关编号-" + customsDeclarationNo).start();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                LogUtils.e("预录入单:" + error.toString());
                mProgressDialog.dismiss();
                ToastUtil.showToast(CB_ManageDetailsActivity.this, "查询请求超时，请稍后再试");
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("Content-Type", "application/x-www-form-urlencoded");
                return map;
            }
        };

        ylrRequest.setTag(this);
        mRequestQueue.add(ylrRequest);

    }

    // 经营单位
    private void showProgressDialog() {
        mProgressDialog = new ProgressDialog(this, R.style.MyProgressDialogStyle);
        mProgressDialog.setMessage("正在查询，请稍后...");
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.setCancelable(true);
        mProgressDialog.show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    @Override
    protected void onDestroy() {
        mRequestQueue.cancelAll(this);
        super.onDestroy();
    }

}