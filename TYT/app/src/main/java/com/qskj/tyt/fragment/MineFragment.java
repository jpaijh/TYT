package com.qskj.tyt.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.widget.RelativeLayout;

import com.apkfuns.logutils.LogUtils;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.qskj.tyt.AppDelegate;
import com.qskj.tyt.MyApplication_;
import com.qskj.tyt.R;
import com.qskj.tyt.activity.AboutActivity_;
import com.qskj.tyt.activity.ModifyPasswordActivity_;
import com.qskj.tyt.activity.T_TitleChangeActivity_;
import com.qskj.tyt.activity.UserInfoActivity_;
import com.qskj.tyt.utils.DataCleanUtil;
import com.qskj.tyt.utils.ToastUtil;
import com.qskj.tyt.view.AlwaysMarqueeTextView;
import com.umeng.analytics.MobclickAgent;
import com.umeng.update.UmengUpdateAgent;
import com.umeng.update.UmengUpdateListener;
import com.umeng.update.UpdateResponse;
import com.umeng.update.UpdateStatus;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.io.File;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 我的 界面
 */
@EFragment(R.layout.fragment_mine)
public class MineFragment extends BaseFragment {

    private static final String TAG = "MineFragment";

    private LocalBroadcastManager mBroadcastManager;
    private BroadcastReceiver mReceiver;
    private SharedPreferences userInfoSp;

    @ViewById(R.id.img_user_icon)
    CircleImageView mUserIcon;

    @ViewById(R.id.tv_accountName)
    AppCompatTextView tv_accountName;

    @ViewById(R.id.tv_pltAccountName)
    AlwaysMarqueeTextView tv_pltAccountName;

    @ViewById(R.id.tv_cache)
    AppCompatTextView tv_cache; // 内部缓存getCache()  data/data/xxx.xxx.xxx/cach 目录下的缓存

    @ViewById(R.id.rl_title_change)
    RelativeLayout rl_title_change;

    @Override
    public void onAfterViews() {
        userInfoSp = MyApplication_.getInstance().getUserInfoSp();
        initBroadcastReceiver();

        if (MyApplication_.getInstance().getPlatFormIntKey() == AppDelegate.PLATFORM_TRADING) {
            showView(rl_title_change);
        } else {
            hideView(rl_title_change);
        }

        tv_accountName.setText(userInfoSp.getString(AppDelegate.ACCOUNT_NAME, ""));
        tv_pltAccountName.setText(userInfoSp.getString(AppDelegate.PLT_ACCOUNT_NAME, ""));
        setUserIcon();

        try {
            String cacheSize = DataCleanUtil.getCacheSize(getContext().getCacheDir());
            tv_cache.setText(cacheSize);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 注册广播接收者:用来更新用户头像
     */
    private void initBroadcastReceiver() {
        mBroadcastManager = LocalBroadcastManager.getInstance(getActivity());
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(AppDelegate.ACTION_UPDATE_USER_ICON);
        mReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                setUserIcon();
            }
        };
        mBroadcastManager.registerReceiver(mReceiver, intentFilter);
    }

    /**
     * 设置/更新 用户头像
     */
    private void setUserIcon() {
        // 如果本地头像文件存在那么在刚进入界面的时候就获取并设置
        // ImageLoader会做图片的缓存处理，如果本地相册图片已经被删除了，但是ImageLoader缓存的图片还是存在的话，那么图片还是会自动显示的
        String path = userInfoSp.getString(AppDelegate.USER_ICON_PATH + userInfoSp.getString(AppDelegate.LOGIN_NAME, ""), "");
        if (!TextUtils.isEmpty(path) && new File(path).exists())
            ImageLoader.getInstance().displayImage("file://" + path, mUserIcon);
    }

    /**
     * 进入抬头切换界面
     */
    @Click(R.id.rl_title_change)
    void goTitleChange() {
        T_TitleChangeActivity_.intent(getActivity()).start();
    }

    /**
     * 缓存清理
     * 使用Volley会产生请求数据的缓存：这时 data/data/xxx.xxx.xxx/cach目录下会产生 volley和org.chrominum.android_webview 两个文件夹，里面是缓存数据
     * 需要清理
     */
    @Click(R.id.rl_clear_cache)
    void clearCache() {
        try {
            DataCleanUtil.cleanInternalCache(getContext());
            String cacheSize = DataCleanUtil.getCacheSize(getContext().getCacheDir());
            tv_cache.setText(cacheSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 进入个人信息界面
     */
    @Click(R.id.rl_user_icon)
    void goUserInfo() {
        UserInfoActivity_.intent(getActivity()).start();
    }


    /**
     * 进入修改密码界面(就是安全设置)
     */
    @Click(R.id.rl_safe_setting)
    void goModifyPassword() {
        ModifyPasswordActivity_.intent(this).start();
    }

    /**
     * 进入关于界面
     */
    @Click(R.id.rl_about)
    void goAbout() {
        AboutActivity_.intent(this).start();
    }

    /**
     * 检查更新
     */
    @Click(R.id.rl_checkUpdate)
    void checkUpdate() {
        UmengUpdateAgent.forceUpdate(getActivity());
        UmengUpdateAgent.setUpdateListener(new UmengUpdateListener() {
            @Override
            public void onUpdateReturned(int updateStatus, UpdateResponse updateInfo) {
                switch (updateStatus) {
                    case UpdateStatus.Yes: // has update
                        UmengUpdateAgent.showUpdateDialog(getActivity(), updateInfo);
                        break;
                    case UpdateStatus.No: // has no update
                        ToastUtil.showToast(getActivity(), "当前已是最新版本");
                        break;
                    case UpdateStatus.NoneWifi: // none wifi
                        // 注意：我已经在MainActivity中设置，更新时忽略只在WIFE下更新，所以这个状态暂时没用
                        ToastUtil.showToast(getActivity(), "没有wifi连接，只在wifi下更新");
                        break;
                    case UpdateStatus.Timeout: // time out
                        ToastUtil.showToast(getActivity(), "请求超时，请检查网络");
                        break;
                }
            }
        });

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
    public void onDestroyView() {
        mBroadcastManager.unregisterReceiver(mReceiver);
        super.onDestroyView();
    }

}
