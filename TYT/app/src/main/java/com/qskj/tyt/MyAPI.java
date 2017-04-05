package com.qskj.tyt;

/**
 * 天易通-API
 */
public class MyAPI {

    /**
     * 跨境平台：正式库
     */
    public static final String CROSS_BORDER_BASE_URL_RELEASE = "http://61.164.68.34:10103";
    /**
     * 跨境平台：测试库
     */
    public static final String CROSS_BORDER_BASE_URL_DEBUG = "http://61.164.68.34:10104";
    /**
     * 外贸平台：正式库
     */
//    public static final String TRADING_BASE_URL_RELEASE = "http://117.78.5.212:10104";
    public static final String TRADING_BASE_URL_RELEASE = "http://61.164.68.34:19201";
    /**
     * 外贸平台：测试库
     */
//    public static final String TRADING_BASE_URL_DEBUG = "http://117.78.5.212:10104";
    public static final String TRADING_BASE_URL_DEBUG = "http://61.164.68.34:19201";

    public static String getBaseUrl() {
        int platForm = MyApplication_.getInstance().getPlatFormIntKey();

        if (MyApplication_.IS_RELEASE_VERSION && platForm == AppDelegate.PLATFORM_CROSS_BORDER) {
            return CROSS_BORDER_BASE_URL_RELEASE;
        } else if (!MyApplication_.IS_RELEASE_VERSION && platForm == AppDelegate.PLATFORM_CROSS_BORDER) {
            return CROSS_BORDER_BASE_URL_DEBUG;
        } else if (MyApplication_.IS_RELEASE_VERSION && platForm == AppDelegate.PLATFORM_TRADING) {
            return TRADING_BASE_URL_RELEASE;
        } else {
            return TRADING_BASE_URL_DEBUG;
        }
    }

}

// 跨境平台测试账号：
// 货代2 :npsel 123456
// 经营单位0: NUGIE 123456
// 报关行3: NUGBG 123456 NUGBG nblhbg(正式库密码)
// 仓库1: txwm 123456

// 天易通外贸平台客户测试账号："http://61.164.68.34:19201"
// 9A74: 密码 123456
// 9B98: 密码 123456
// 9B42: 密码 123456
// 9E63: 密码 123456
// 9K54: 密码 123456

// Accept:application/json,text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8
// QS-LOGIN:3010201
// Content-Type:application/json


