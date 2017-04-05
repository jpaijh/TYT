package com.qskj.tyt;

/**
 * 代理类
 */
public final class AppDelegate {

    public static final String QS_LOGIN = "QS-LOGIN";
    /**
     * SharedPreferences-PlatForm_Info 存储平台信息的sp
     */
    public static final String SP_PLATFORM_INFO = "PlatForm_Info";
    /**
     * SharedPreferences-platformInfo-platform 存储登陆的平台
     * 1：跨境平台
     * 2：外贸平台
     */
    public static final String PLATFORM = "platform";
    /**
     * 跨境平台
     */
    public static final int PLATFORM_CROSS_BORDER = 1;
    /**
     * 外贸平台
     */
    public static final int PLATFORM_TRADING = 2;

    /**
     * SharedPreferences-Search_History 存储历史搜索记录的sp(管理列表搜索记录，商品列表关键字搜索记录)
     */
    public static final String SP_SEARCH_HISTORY = "Search_History";
    /**
     * SharedPreferences-Search_History-History_Commodity_Key_Search 商品搜索界面-商品的关键字搜索历史记录
     */
    public static final String HISTORY_COMMODITY_KEY_SEARCH = "History_Commodity_Key_Search";
    /**
     * SharedPreferences-Search_History-History_Sale_Invoice_Search 外贸平台，订单搜索界面-外销发票号的关键字搜索历史记录
     */
    public static final String HISTORY_SALE_INVOICE_SEARCH = "History_Sale_Invoice_Search";
    /**
     * SharedPreferences-Search_History-History_Sale_Invoice_Search 外贸平台，账户搜索界面-外销发票号的关键字搜索历史记录
     */
    public static final String HISTORY_SALE_INVOICE_ACCOUNT_SEARCH = "History_Sale_Invoice_Account_Search";

    /**
     * SharedPreferences-Refresh_Date 保存所有列表下拉刷新时间的sp
     */
    public static final String SP_REFRESH_DATE = "Refresh_Date";
    /**
     * SharedPreferences-User_Info 存储用户信息的sp
     */
    public static final String SP_USER_INFO = "User_Info";
    /**
     * SharedPreferences-User_Info-isLogin 存储是否已经登录
     */
    public static final String IS_LOGIN = "isLogin";
    /**
     * SharedPreferences-User_Info-titleId 存储titleId
     */
    public static final String TITLE_ID = "titleId";
    /**
     * SharedPreferences-User_Info-user_names 存储登录用户名（做登录历史记录）
     */
    public static final String USER_NAMES = "user_names";
    /**
     * SharedPreferences-User_Info-password 存储登录密码
     */
    public static final String PASSWORD = "password";
    /**
     * SharedPreferences-User_Info-loginName 存储loginName(QS-LOGIN,很多请求需要带这个参数)
     */
    public static final String LOGIN_NAME = "loginName";
    /**
     * SharedPreferences-User_Info-accountName 存储用户名
     */
    public static final String ACCOUNT_NAME = "accountName";
    /**
     * SharedPreferences-User_Info-lgName 存储登录名
     */
    public static final String LG_NAME = "lgName";
    /**
     * SharedPreferences-User_Info-email 存储邮箱
     */
    public static final String EMAIL = "email";
    /**
     * SharedPreferences-User_Info-mobile 存储手机号码
     */
    public static final String MOBILE = "mobile";
    /**
     * SharedPreferences-User_Info-accountId 存储pltAccountId（就是accountId）
     */
    public static final String ACCOUNT_ID = "accountId";
    /**
     * SharedPreferences-User_Info-pltAccountName 存储账户名（平台）
     */
    public static final String PLT_ACCOUNT_NAME = "pltAccountName";
    /**
     * SharedPreferences-User_Info-accountNature 存储用户类型
     */
    public static final String ACCOUNT_NATURE = "accountNature";
    /**
     * 角色：经营单位
     */
    public static final int ACCOUNT_NATURE_MANAGEMENT_UNIT = 0;
    /**
     * 角色：仓库
     */
    public static final int ACCOUNT_NATURE_WAREHOUSE = 1;
    /**
     * 角色：货代
     */
    public static final int ACCOUNT_NATURE_FORWARDER = 2;
    /**
     * 角色：报关行
     */
    public static final int ACCOUNT_NATURE_DECLARE = 3;

    public static final String MANAGE_UI_NAME = "ManageUiName";

    /**
     * SharedPreferences-User_Info-UserIconPath 存储用户头像本地地址
     */
    public static final String USER_ICON_PATH = "UserIconPath";
    /**
     * 请求API
     */
    public static final String REQUEST_API = "Api";

    /**
     * 管理-仓单 ID
     */
    public static final String ID = "id";
    /**
     * 管理详情-isCustomsFlag 是否已经提交报关
     */
    public static final String IS_CUSTOMS_FLAG = "isCustomsFlag";
    /**
     * 管理详情实体 ManageDetailsEntity
     */
    public static final String MANAGE_DETAILS_ENTITY = "ManageDetailsEntity";
    /**
     * 商品管理 实体
     */
    public static final String COMMODITY_MANAGE_ROWS_ENTITY = "CommodityManageRowsEntity";
    /**
     * 列表条目实体
     */
    public static final String ROWS_ENTITY = "RowsEntity";
    /**
     * 商品搜索关键字,订单搜索外销发票号关键字
     */
    public static final String KEYWORD = "keyword";
    /**
     * toolbar标题
     */
    public static final String TOOLBAR_TITLE = "ToolbarTitle";
    public static final String URL = "url";

    /**
     * 天易通-商品搜素 的广播动作
     */
    public static final String ACTION_CB_SEARCH_COMMODITY = "android.intent.action.ACTION_CB_SEARCH_COMMODITY";
    /**
     * 更新用户头像 的广播动作
     */
    public static final String ACTION_UPDATE_USER_ICON = "android.intent.action.ACTION_UPDATE_USER_ICON";
    /**
     * 外贸（代理）平台：更新首页资金 的广播动作
     */
    public static final String ACTION_UPDATE_AMOUNT = "android.intent.action.ACTION_UPDATE_AMOUNT";
    /**
     * 订单搜索 的广播动作
     */
    public static final String ACTION_T_ORDER_FORM_SEARCH = "android.intent.action.ACTION_T_ORDER_FORM_SEARCH";
    /**
     * 订单id
     */
    public static final String ORDER_ID = "order_id";

    /**
     * 资金搜索
     */
    public static final String ACTION_T_ACCOUNT_SEARCH = "ACTION_T_ACCOUNT_SEARCH";
    /**
     * 资金搜索 普通账户 的广播动作
     */
    public static final String ACTION_T_GENERAL_ACCOUNT_SEARCH = "android.intent.action.ACTION_T_GENERAL_ACCOUNT_SEARCH";
    /**
     * 资金搜索 融资账户 的广播动作
     */
    public static final String ACTION_T_FINANCING_ACCOUNT_SEARCH = "android.intent.action.ACTION_T_FINANCING_ACCOUNT_SEARCH";
    /**
     * 提款操作后申请提款界面刷新列表 的广播动作
     */
    public static final String ACTION_T_REFRESH_APPLY_PAYMENT_LIST = "android.intent.action.ACTION_T_REFRESH_APPLY_PAYMENT_LIST";
    /**
     * 创建收汇通知操作后通知收汇界面刷新列表 的广播动作
     */
    public static final String ACTION_T_REFRESH_NOTICE_FOREIGN_EXCHANGE_LIST = "android.intent.action.ACTION_T_REFRESH_NOTICE_FOREIGN_EXCHANGE_LIST";
    /**
     * 用来标记点击的是 起止日期还是结束日期
     */
    public static final int CLICK_START_DATE = 0;
    public static final int CLICK_END_DATE = 1;

    private static AppDelegate mInstance = new AppDelegate();

    private AppDelegate() {
    }

    public static AppDelegate getInstance() {
        return mInstance;
    }

    private MyApplication mApp;

    public void init(MyApplication context) {
        this.mApp = context;
    }

}
