package com.qskj.tyt.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Vibrator;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.AppCompatTextView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.ToxicBakery.viewpager.transforms.*;

import android.support.v4.view.ViewPager.PageTransformer;
import android.widget.LinearLayout;

import com.alibaba.fastjson.JSON;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.apkfuns.logutils.LogUtils;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.lsjwzh.widget.materialloadingprogressbar.CircleProgressBar;
import com.qskj.tyt.AppDelegate;
import com.qskj.tyt.MyAPI;
import com.qskj.tyt.MyApplication_;
import com.qskj.tyt.R;
import com.qskj.tyt.activity.CB_CommodityManageActivity_;
import com.qskj.tyt.activity.CB_ManageActivity_;
import com.qskj.tyt.activity.MessageCenterActivity_;
import com.qskj.tyt.activity.T_ApplyPaymentActivity_;
import com.qskj.tyt.activity.T_ContactsCompanyActivity_;
import com.qskj.tyt.activity.T_CustomerServiceActivity_;
import com.qskj.tyt.activity.T_MyFundActivity_;
import com.qskj.tyt.activity.T_MyInvoiceActivity_;
import com.qskj.tyt.activity.T_NoticeForeignExchangeActivity_;
import com.qskj.tyt.activity.T_WarningCenterActivity_;
import com.qskj.tyt.adapter.DynamicGridViewAdapter;
import com.qskj.tyt.entity.T_AccountFundBalanceEntity;
import com.qskj.tyt.utils.StringUtil;
import com.qskj.tyt.view.dynamicgridview.DynamicGridView;
import com.qskj.tyt.view.pulllayout.PullToZoomLayout;
import com.umeng.analytics.MobclickAgent;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;
import org.json.JSONArray;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 天易通-首页(跨境平台，外贸平台)
 */
@EFragment(R.layout.fragment_home)
public class HomeFragment extends BaseFragment {

    private static final String TAG = "HomeFragment";

    private List<DynamicGridViewAdapter.ItemHomeGridView> itemLists;
    private DynamicGridViewAdapter mAdapter;
    private SharedPreferences userInfoSp;

    @ViewById(R.id.gv_dynamic_grid)
    DynamicGridView mGridView;

    // 跨境
    private static final ArrayList<TransformerItem> TRANSFORM_CLASSES;
    /**
     * 当前翻页特效
     * 0:平滑，没有任何效果, 1-3:翻转样式，4-7：层叠样式，8-16 并行样式
     */
    private int CURRENT_TRANSFORMER = 1;

    static {
        TRANSFORM_CLASSES = new ArrayList<>();
        TRANSFORM_CLASSES.add(new TransformerItem(DefaultTransformer.class)); // 0.平滑，没有任何效果
        TRANSFORM_CLASSES.add(new TransformerItem(CubeOutTransformer.class)); // 1.翻转式：立体3维柱面横向翻转
        TRANSFORM_CLASSES.add(new TransformerItem(FlipHorizontalTransformer.class)); // 2.翻转式：以图片纵中心线为轴，横向360°旋转
        TRANSFORM_CLASSES.add(new TransformerItem(FlipVerticalTransformer.class));  // 3.翻转式：以图片横中心线为轴，纵向360°旋转

        TRANSFORM_CLASSES.add(new TransformerItem(StackTransformer.class)); // 4: 层叠式：上面图片盖住下面图片，滑动时显示上面图片滑出去，下面图片直接显示且显示不带任何效果
        TRANSFORM_CLASSES.add(new TransformerItem(DepthPageTransformer.class)); // 5:层叠式：上面图片盖住下面图片，滑动时显示上面图片滑出去，下面图片由小到大，由模糊变清晰显示
        TRANSFORM_CLASSES.add(new TransformerItem(ZoomInTransformer.class)); // 6: 层叠式：图片不滑动，上面图片由大缩小隐藏，里面图片由小放大显示
        TRANSFORM_CLASSES.add(new TransformerItem(ZoomOutTranformer.class)); // 7:层叠式：图片不滑动，上面图片放大透明消失，里面图片由大变小，由透明变清晰显示

        TRANSFORM_CLASSES.add(new TransformerItem(ScaleInOutTransformer.class)); // 8: 并行式,滑动时左边图片慢慢缩小，右边图片慢慢变大，两张图片连在一起的
        TRANSFORM_CLASSES.add(new TransformerItem(ForegroundToBackgroundTransformer.class)); // 9：并行式,滑动时左边图片瞬间变小，右边图片正常出现，两张图片不是连在一起的
        TRANSFORM_CLASSES.add(new TransformerItem(RotateDownTransformer.class)); // 10: 并行式,左边图片慢慢向下倾斜一定角度滑出，右边图边由倾斜一定角度出现并慢慢变正，两张图片连在一起
        TRANSFORM_CLASSES.add(new TransformerItem(RotateUpTransformer.class)); // 11:并行式,左边图片慢慢向上倾斜一定角度滑出，右边图边由倾斜一定角度出现并慢慢变正，两张图片连在一起
        TRANSFORM_CLASSES.add(new TransformerItem(AccordionTransformer.class)); // 12:并行式，左边图片被压缩变小，右边图片由压缩状态慢慢变大变正常，两张图片连在一起
        TRANSFORM_CLASSES.add(new TransformerItem(BackgroundToForegroundTransformer.class)); // 13:并行式，左边图片正常滑出，稍快，两张图片间距变大，右边图片出现，刚开始是小的，滑到中间开始慢慢变大至正常
        TRANSFORM_CLASSES.add(new TransformerItem(CubeInTransformer.class)); // 14:并行式，滑动时左边图片从左边部分开始放大，不是整体放大，右边图片出现右边部分是放大的，左边部分正常，然后右边慢慢变正常，两张图片不是连在一起的
        TRANSFORM_CLASSES.add(new TransformerItem(TabletTransformer.class)); // 15: 并行式,滑动时，左边图片右侧部分向里面凹右边图片左侧部分由凹的向外慢慢展平，两张图是连在一起的
        TRANSFORM_CLASSES.add(new TransformerItem(ZoomOutSlideTransformer.class)); // 16:并行式,滑动时，左边图片缩小一点点，并透明处理和右边图片并行滑动，当右边图片滑到左边图片位置时放大显示并清晰，两张图片有一点间距
    }

    @ViewById(R.id.convenientBanner)
    ConvenientBanner mConvenientBanner;

    // 外贸（代理）
    private RequestQueue mRequestQueue;
    private double GeneralAccount = 0; // 普通账户资金余额
    private double FinancingAccount = 0; // 融资账户资金余额
    private double TotalFundBalance = 0; // 总资金余额
    private LocalBroadcastManager mBroadcastManager;
    private BroadcastReceiver mReceiver;

    @ViewById(R.id.frameLayout)
    FrameLayout mFrameLayout;

    @ViewById(R.id.tv_total_fund_balance)
    AppCompatTextView mTotalFundBalance; // 资金余额：普通账户 + 融资账户

    @ViewById(R.id.ll)
    LinearLayout mLinearLayout;

    @ViewById(R.id.pull_layout)
    PullToZoomLayout mPullLayout;

    @ViewById(R.id.progressbar)
    CircleProgressBar mProgressBar;

    @Override
    public void onAfterViews() {
        mRequestQueue = Volley.newRequestQueue(getActivity());
        userInfoSp = MyApplication_.getInstance().getUserInfoSp();
        int platFormIntKey = MyApplication_.getInstance().getPlatFormIntKey();
        if (platFormIntKey == AppDelegate.PLATFORM_CROSS_BORDER) {
            initConvenientBanner();
        } else if (platFormIntKey == AppDelegate.PLATFORM_TRADING) {
            showView(mFrameLayout);
            showProgressbar();
            initBroadcastReceiver();
            initPullLayout();
            onBackgrounds();
        }
        initGridView(platFormIntKey);
    }

    /**
     * 外贸（代理）平台：注册广播接收者: 用来切换抬头设置之后 更新首页资金
     */
    private void initBroadcastReceiver() {
        mBroadcastManager = LocalBroadcastManager.getInstance(getActivity());
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(AppDelegate.ACTION_UPDATE_AMOUNT);
        mReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                onBackgrounds();
            }
        };
        mBroadcastManager.registerReceiver(mReceiver, intentFilter);
    }

    // 外贸
    private void showProgressbar() {
        showView(mProgressBar);
        hideView(mLinearLayout);
    }

    private void hideProgressbar() {
        hideView(mProgressBar);
        showView(mLinearLayout);
    }

    /**
     * 外贸：初始化PullLayout
     */
    private void initPullLayout() {
        // 获取头部布局文件，可动态设置头部图片
        // ((ImageView) (mPullLayout.findViewById(R.id.img_head))).setImageResource(R.mipmap.ic_home_head_bg_cat);

        // 监听下拉状态,刷新总余额(下拉时会调用move方法)
        mPullLayout.setOnTouchListener(new View.OnTouchListener() {
            float startY;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:// 触摸
                        startY = event.getY();
                        break;
                    case MotionEvent.ACTION_MOVE:// 滑动，刷新资金余额
                        if ((event.getY() - startY) > 400 && mProgressBar.getVisibility() != View.VISIBLE) {
                            showProgressbar();
                        }
                        break;
                    case MotionEvent.ACTION_UP:// 抬起
                        if ((event.getY() - startY) > 400) {
                            onBackgrounds();
                        }
                        break;
                }
                return false; // 让父View去消耗事件，子View不消耗事件
            }
        });
    }

    /**
     * 进入 我的资金
     */
    @Click(R.id.tv_total_fund_balance)
    void goMyFundActivity() {
        double[] doubles = {GeneralAccount, FinancingAccount, TotalFundBalance};
        T_MyFundActivity_.intent(getActivity()).extra("AccountFundBalance", doubles).start();
    }

    /**
     * 进入 申请提款
     */
    @Click(R.id.tv_apply_payment)
    void goApplyPaymentActivity() {
        T_ApplyPaymentActivity_.intent(getActivity()).start();
    }

    /**
     * 进入 通知收汇
     */
    @Click(R.id.tv_notice_foreign_exchange)
    void goNoticeForeignExchangeActivity() {
        T_NoticeForeignExchangeActivity_.intent(getActivity()).start();
    }

    @Override
    public void onBackgrounds() {
        // 请求获取首页资金 titleId=2811600
        final String url = MyAPI.getBaseUrl() + "/api/Funds/FundAccount/GetAccountNameFundBalance?titleId=" + userInfoSp.getInt(AppDelegate.TITLE_ID, -1);
        JsonArrayRequest getAccountNameFundBalance = new JsonArrayRequest(Request.Method.GET, url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                LogUtils.d("我的资金URL-" + url + "\n我的资金-RESPONSE:" + response.toString());
                // [{ "currency": "RMB","amount": 5823771.94,"fundAccountName": 1}]
                if (response.toString().equals("[]")) {
                    mTotalFundBalance.setText("0.00");
                } else {
                    List<T_AccountFundBalanceEntity> entity = JSON.parseArray(response.toString(), T_AccountFundBalanceEntity.class);

                    if (entity.size() != 0) {
                        GeneralAccount = entity.get(0).getAmount();
                    }

                    if (entity.size() > 2) {
                        FinancingAccount = entity.get(2).getAmount();
                    }

                    TotalFundBalance = GeneralAccount + FinancingAccount;
                    mTotalFundBalance.setText(StringUtil.numberFormat(TotalFundBalance));
                }

                hideProgressbar();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                LogUtils.e("请求错误:" + error.toString());
                mTotalFundBalance.setText("0.00");
                hideProgressbar();
            }
        }
        ) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put(AppDelegate.QS_LOGIN, userInfoSp.getString(AppDelegate.LOGIN_NAME, ""));
                return map;
            }
        };

        getAccountNameFundBalance.setTag(this);
        mRequestQueue.add(getAccountNameFundBalance);

    }

    @Override
    public void onDestroyView() {
        if (mBroadcastManager != null) // 这是因为只有外贸平台才会初始化mBroadcastManager
            mBroadcastManager.unregisterReceiver(mReceiver);
        mRequestQueue.cancelAll(this);
        super.onDestroyView();
    }

    // 跨境：初始化banner
    public void initConvenientBanner() {
        showView(mConvenientBanner);

        //  mConvenientBanner.setManualPageable(false); // 设置不能手动影响,其实就是不能手动滑动，不能按住暂停滚动

        ArrayList<Integer> localImages = new ArrayList<>();
        for (int position = 0; position < 4; position++)
            localImages.add(getResId("ic_banner_" + position, R.mipmap.class));

        mConvenientBanner.setPages(new CBViewHolderCreator<LocalImageHolderView>() {
            @Override
            public LocalImageHolderView createHolder() {
                return new LocalImageHolderView();
            }
        }, localImages)
                // 设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可以不设
                .setPageIndicator(new int[]{R.mipmap.ic_page_indicator, R.mipmap.ic_page_indicator_focused});
        // .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT) // 设置指示器的位置，可左可右，目前居中
        //  .setOnPageChangeListener(this)// 监听翻页事件
        // .setOnItemClickListener(this) // 监听图片的点击事件

        try {
            // 设置翻页动画
            PageTransformer pageTransformer = TRANSFORM_CLASSES.get(CURRENT_TRANSFORMER).clazz.newInstance();
            mConvenientBanner.getViewPager().setPageTransformer(true, pageTransformer);
            // 这个3D特效需要调整滑动速度
            if (pageTransformer.toString().equals("StackTransformer")) {
                mConvenientBanner.setScrollDuration(1200);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private static final class TransformerItem {

        final String title;
        final Class<? extends PageTransformer> clazz;

        public TransformerItem(Class<? extends PageTransformer> clazz) {
            this.clazz = clazz;
            title = clazz.getSimpleName();
        }

        @Override
        public String toString() {
            return title;
        }

    }

    /**
     * 通过文件名获取资源id 例子：getResId("icon", R.drawable.class);
     *
     * @param variableName
     * @param c
     * @return
     */
    private static int getResId(String variableName, Class<?> c) {
        try {
            Field idField = c.getDeclaredField(variableName);
            return idField.getInt(idField);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    private class LocalImageHolderView implements Holder<Integer> {
        private ImageView imageView;

        @Override
        public View createView(Context context) {
            imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            return imageView;
        }

        @Override
        public void UpdateUI(Context context, int position, Integer data) {
            imageView.setImageResource(data);
        }

    }

    @Override
    public void onResume() {
        mConvenientBanner.startTurning(4000);  // 设置自动翻页 4S每次
        MobclickAgent.onPageStart(TAG);
        super.onResume();
    }

    @Override
    public void onPause() {
        mConvenientBanner.stopTurning(); // 关闭自动翻页
        MobclickAgent.onPageEnd(TAG);
        super.onPause();
    }

    private void initGridView(int platFormIntKey) {
        itemLists = new ArrayList<>();
        // 消息中心
        DynamicGridViewAdapter.ItemHomeGridView message_center = new DynamicGridViewAdapter.ItemHomeGridView(R.string.title_activity_message_center, R.mipmap.ic_messagemanage_tyt);

        if (platFormIntKey == AppDelegate.PLATFORM_CROSS_BORDER) {
            // 仓单管理
            DynamicGridViewAdapter.ItemHomeGridView warehousingManage = new DynamicGridViewAdapter.ItemHomeGridView(R.string.title_activity_warehousingmanage, R.mipmap.ic_warehousingmanage_tyt);
            // 商品管理
            DynamicGridViewAdapter.ItemHomeGridView commodityManage = new DynamicGridViewAdapter.ItemHomeGridView(R.string.title_activity_commodutymanage, R.mipmap.ic_commodutymanage_tyt);
            // 清单管理
            DynamicGridViewAdapter.ItemHomeGridView listManage = new DynamicGridViewAdapter.ItemHomeGridView(R.string.title_activity_listmanage, R.mipmap.ic_listmanage_tyt);
            // 报关单管理
            DynamicGridViewAdapter.ItemHomeGridView declareManage = new DynamicGridViewAdapter.ItemHomeGridView(R.string.title_activity_declaremanage, R.mipmap.ic_declaremanage_tyt);
            // 入库管理
            DynamicGridViewAdapter.ItemHomeGridView stockManage = new DynamicGridViewAdapter.ItemHomeGridView(R.string.title_activity_stockmanage, R.mipmap.ic_stockmanage_tyt);

            int accountNature = userInfoSp.getInt(AppDelegate.ACCOUNT_NATURE, -1);
            switch (accountNature) {
                case 0: // 经营单位
                    itemLists.add(warehousingManage);
                    itemLists.add(commodityManage);
                    break;
                case 1: // 仓库
                    itemLists.add(stockManage);
                    break;
                case 2: // 货代
                    itemLists.add(warehousingManage);
                    break;
                case 3: // 报关行
                    itemLists.add(listManage);
                    itemLists.add(declareManage);
                    break;
            }

        } else if (platFormIntKey == AppDelegate.PLATFORM_TRADING) {
            // 我的票据
            DynamicGridViewAdapter.ItemHomeGridView mine_bill = new DynamicGridViewAdapter.ItemHomeGridView(R.string.title_activity_my_invoice, R.mipmap.ic_home_gv_mine_bill);
            // 往来单位
            DynamicGridViewAdapter.ItemHomeGridView contacts_company = new DynamicGridViewAdapter.ItemHomeGridView(R.string.title_activity_contacts_company, R.mipmap.ic_home_gv_contacts_organize);
            // 预警中心
            DynamicGridViewAdapter.ItemHomeGridView warning_center = new DynamicGridViewAdapter.ItemHomeGridView(R.string.title_activity_warning_center, R.mipmap.ic_home_gv_warning_center);
            // 专属客服
            DynamicGridViewAdapter.ItemHomeGridView customer_service = new DynamicGridViewAdapter.ItemHomeGridView(R.string.title_activity_customer_service, R.mipmap.ic_home_gv_customer_service);

            itemLists.add(mine_bill);
            itemLists.add(contacts_company);
            itemLists.add(warning_center);
            itemLists.add(customer_service);
        }

        itemLists.add(message_center); // 消息中心
        mAdapter = new DynamicGridViewAdapter(getActivity(), itemLists, getResources().getInteger(R.integer.gridView_columns));

        mGridView.setAdapter(mAdapter);
        // 设置是否显示网格线
        mGridView.setShowLines(true, true);
        // 拖拽之后的处理：停止编辑模式,保存用户操作之后状态
        mGridView.setOnDropListener(new DynamicGridView.OnDropListener() {
                                        @Override
                                        public void onActionDrop() {
                                            mGridView.stopEditMode();
                                        }
                                    }

        );

        mGridView.setOnDragListener(new DynamicGridView.OnDragListener() {
                                        @Override
                                        public void onDragStarted(int position) {
                                            LogUtils.d("drag gridview item:" + position);
                                        }

                                        @Override
                                        public void onDragPositionsChanged(int oldPosition, int newPosition) {
                                            LogUtils.d(String.format("drag gridview item %d to %d", oldPosition, newPosition));
                                        }
                                    }

        );

        mGridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                                                 @Override
                                                 public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                                                     // 添加震动
                                                     Vibrator mVibrator = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
                                                     mVibrator.vibrate(50);
                                                     // 长点击开启拖拽模式
                                                     mGridView.startEditMode(position);
                                                     return true;
                                                 }
                                             }

        );

        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                             @Override
                                             public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                                 DynamicGridViewAdapter.ItemHomeGridView item = (DynamicGridViewAdapter.ItemHomeGridView) parent.getAdapter().getItem(position);
                                                 switch (item.getTextRes()) {
                                                     // 跨境
                                                     case R.string.title_activity_warehousingmanage: // 进入仓单管理
                                                         CB_ManageActivity_.intent(getActivity()).extra(AppDelegate.TOOLBAR_TITLE, getStrings(R.string.title_activity_warehousingmanage)).start();
                                                         break;
                                                     case R.string.title_activity_stockmanage: // 进入入库管理
                                                         CB_ManageActivity_.intent(getActivity()).extra(AppDelegate.TOOLBAR_TITLE, getStrings(R.string.title_activity_stockmanage)).start();
                                                         break;
                                                     case R.string.title_activity_listmanage: // 进入清单管理
                                                         CB_ManageActivity_.intent(getActivity()).extra(AppDelegate.TOOLBAR_TITLE, getStrings(R.string.title_activity_listmanage)).start();
                                                         break;
                                                     case R.string.title_activity_declaremanage: // 进入报关单管理
                                                         CB_ManageActivity_.intent(getActivity()).extra(AppDelegate.TOOLBAR_TITLE, getStrings(R.string.title_activity_declaremanage)).start();
                                                         break;
                                                     case R.string.title_activity_commodutymanage: // 进入商品管理
                                                         CB_CommodityManageActivity_.intent(getActivity()).start();
                                                         break;
                                                     case R.string.title_activity_message_center:// 进入消息中心
                                                         MessageCenterActivity_.intent(getActivity()).start();
                                                         break;
                                                     // 外贸
                                                     case R.string.title_activity_my_invoice:  // 我的票据
                                                         T_MyInvoiceActivity_.intent(getActivity()).start();
                                                         break;
                                                     case R.string.title_activity_contacts_company:  // 往来单位
                                                         T_ContactsCompanyActivity_.intent(getActivity()).start();
                                                         break;
                                                     case R.string.title_activity_warning_center: // 预警中心
                                                         T_WarningCenterActivity_.intent(getActivity()).start();
                                                         break;
                                                     case R.string.title_activity_customer_service: //专属客服
                                                         T_CustomerServiceActivity_.intent(getActivity()).start();
                                                         break;
                                                 }
                                             }
                                         }

        );
    }

}
