package com.qskj.tyt;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;

import com.apkfuns.logutils.LogLevel;
import com.apkfuns.logutils.LogUtils;
import com.baidu.mapapi.SDKInitializer;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.umeng.analytics.MobclickAgent;

import org.androidannotations.annotations.EApplication;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

/**
 * MyApplication
 */
@EApplication
public class MyApplication extends Application {

    /**
     * 是否是 Release 正式版本,发布正式版本的时候要改成true
     * 作用：正式测试库的Api开关，LogUtils的开关，友盟统计SDK集成测试开关
     */
    public static boolean IS_RELEASE_VERSION = true;

    private List<Activity> activityList = new LinkedList<>();
    private static MyApplication instance;

    /**
     * 获取平台的int值
     *
     * @return 1:跨境平台，2：外贸平台
     */
    public int getPlatFormIntKey() {
        return getSharedPreferences(AppDelegate.SP_PLATFORM_INFO, MODE_PRIVATE).getInt(AppDelegate.PLATFORM, 0);
    }

    /**
     * 获取平台int值对应的中文名称
     *
     * @return 1:跨境平台，2：外贸平台
     */
    public String getPlatFormString() {
        if (getPlatFormIntKey() == AppDelegate.PLATFORM_CROSS_BORDER) {
            return getResources().getString(R.string.platform_cross_border);
        } else {
            return getResources().getString(R.string.platform_trading);
        }
    }

    /**
     * 获取平台信息SharedPreferences.Editor
     */
    public SharedPreferences.Editor getPlatFormEditor() {
        return getSharedPreferences(AppDelegate.SP_PLATFORM_INFO, MODE_PRIVATE).edit();
    }

    /**
     * 获取用户信息SharedPreferences
     */
    public SharedPreferences getUserInfoSp() {
        return getSharedPreferences(AppDelegate.SP_USER_INFO, Context.MODE_PRIVATE);
    }

    /**
     * 获取用户信息SharedPreferences.Editor
     */
    public SharedPreferences.Editor getUserInfoEditor() {
        return getUserInfoSp().edit();
    }

    /**
     * 获取搜索记录SharedPreferences
     */
    public SharedPreferences getSearchHistorySp() {
        return getSharedPreferences(AppDelegate.SP_SEARCH_HISTORY, Context.MODE_PRIVATE);
    }

    /**
     * 获取搜索记录SharedPreferences.Editor
     */
    public SharedPreferences.Editor getSearchHistoryEdit() {
        return getSearchHistorySp().edit();
    }

    public MyApplication() {
    }

    // 单例模式中获取唯一的MyApplication实例
    public static MyApplication getInstance() {
        if (null == instance) {
            instance = new MyApplication();
        }
        return instance;
    }

    // 添加Activity到容器中
    public void addActivity(Activity activity) {
        activityList.add(activity);
    }

    /**
     * 遍历所有Activity并finish，但是不关闭应用
     * （这个方法主要是用于 修改密码和退出登录后 跳转登陆界面，需关闭掉被addActivity（Activity activity）方法添加进activityList的Activity）
     * 不然按返回键会回到之前界面
     */
    public void exit() {
        for (Activity activity : activityList) {
            activity.finish();
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        // 禁止默认的页面统计方式，这样将不会再自动统计Activity(默认页面统计只能统计Activity，不能统计Activity下面包含的Fragment)
        MobclickAgent.openActivityDurationTrack(false);

        LogUtils.getLogConfig().configAllowLog(!IS_RELEASE_VERSION)  // Log日志开关
                .configTagPrefix(getPackageName())  //设置Log日志Tag前缀:用包名
                .configShowBorders(true)
                .configLevel(LogLevel.TYPE_VERBOSE);

        if (IS_RELEASE_VERSION) {
            MobclickAgent.setDebugMode(false); // 友盟统计SDK，集成测试开关
        } else {
            MobclickAgent.setDebugMode(true);
        }

        initImageLoader(this); // 初始化ImageLoader
        SDKInitializer.initialize(this); // 初始化百度地图-注意：在SDK各功能组件使用之前都需要调用,因此我们建议该方法放在Application的初始化方法中
    }

    /**
     * 初始化ImageLoader
     */
    public static void initImageLoader(Context context) {
        // 哪个地方想要清除自定义路径下的缓存，就在那里调用这个方法
        //  ImageLoader.getInstance().clearDiscCache(); // 清除自定义的ImageLoader缓存的图片

        // 自定义的缓存路径ImageLoaderCache：sdcard/picture/ImageLoaderCache ，属于外部缓存
        File cacheDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "ImageLoaderCache");
        if (!cacheDir.exists()) {
            cacheDir.mkdir();
        }

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
                .memoryCacheExtraOptions(480, 800) // maxwidth, max height，即保存的每个缓存文件的最大长宽
                .threadPoolSize(3).threadPriority(Thread.NORM_PRIORITY - 2)//线程池内加载的数量
                .denyCacheImageMultipleSizesInMemory()
                //.memoryCache(new UsingFreqLimitedMemoryCache(2 * 1024 * 1024)) //你可以通过自己的内存缓存实现,那么getCache()就能获取到缓存图片，在data/data/xxx.xxx.xxx/cache 文件夹下
                //.memoryCacheSize(2 * 1024 * 1024)  // 内存缓存大小
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .diskCacheFileCount(100) //缓存图片的个数
                .discCache(new UnlimitedDiscCache(cacheDir))// 自定义缓存路径 ，这个注释的话，就没有自定的缓存了，不过还可以设置内部缓存
                .defaultDisplayImageOptions(DisplayImageOptions.createSimple())
                .imageDownloader(new BaseImageDownloader(context, 5 * 1000, 30 * 1000)) // connectTimeout (5 s), readTimeout (30 s)超时时间
                .writeDebugLogs() // Remove for releaseapp
                .build();//开始构建
        ImageLoader.getInstance().init(config);

    }

}