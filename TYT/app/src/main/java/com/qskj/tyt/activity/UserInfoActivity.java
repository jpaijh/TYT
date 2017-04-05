package com.qskj.tyt.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.apkfuns.logutils.LogUtils;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.qskj.tyt.AppDelegate;
import com.qskj.tyt.MyApplication_;
import com.qskj.tyt.R;
import com.umeng.analytics.MobclickAgent;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.io.File;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 个人信息
 */
@EActivity(R.layout.activity_user_info)
public class UserInfoActivity extends BaseActivity {

    private static final String TAG = "UserInfoActivity";

    private static final int REQUEST_PICK = 0;//请求照片的请求码
    private ImageLoader imageLoader = ImageLoader.getInstance();
    private SharedPreferences userInfoSp;

    @ViewById(R.id.rl_user_icon)
    RelativeLayout rl_user_icon;

    @ViewById(R.id.ll_accountNature)
    LinearLayout ll_accountNature; // 跨境显示角色，外贸（代理）不显示

    @ViewById(R.id.img_user_icon)
    CircleImageView img_user_icon;

    @ViewById(R.id.tv_login_name)
    AppCompatTextView tv_login_name;

    @ViewById(R.id.tv_username)
    AppCompatTextView tv_username;

    @ViewById(R.id.tv_mobile)
    AppCompatTextView tv_mobile;

    @ViewById(R.id.tv_email)
    AppCompatTextView tv_email;

    @ViewById(R.id.tv_pltAccountName)
    AppCompatTextView tv_pltAccountName;

    @ViewById(R.id.tv_accountNature)
    AppCompatTextView tv_accountNature;

    @ViewById(R.id.toolbar)
    Toolbar mToolbar;

    @ViewById(R.id.tv_title)
    AppCompatTextView tv_title;

    private void initToolbar() {
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        tv_title.setText(R.string.title_activity_user_info);
    }

    @Override
    public void onAfterViews() {
        initToolbar();
        initUserInfo();
    }

    private void initUserInfo() {
        userInfoSp = MyApplication_.getInstance().getUserInfoSp();
        tv_username.setText(userInfoSp.getString(AppDelegate.ACCOUNT_NAME, ""));
        tv_email.setText(userInfoSp.getString(AppDelegate.EMAIL, ""));
        tv_mobile.setText(userInfoSp.getString(AppDelegate.MOBILE, ""));
        tv_login_name.setText(userInfoSp.getString(AppDelegate.LG_NAME, ""));
        tv_pltAccountName.setText(userInfoSp.getString(AppDelegate.PLT_ACCOUNT_NAME, ""));

        if (MyApplication_.getInstance().getPlatFormIntKey() == AppDelegate.PLATFORM_CROSS_BORDER) {
            showView(ll_accountNature);
            switch (userInfoSp.getInt(AppDelegate.ACCOUNT_NATURE, -1)) {
                case AppDelegate.ACCOUNT_NATURE_MANAGEMENT_UNIT:
                    tv_accountNature.setText("经营单位");
                    break;
                case AppDelegate.ACCOUNT_NATURE_WAREHOUSE:
                    tv_accountNature.setText("仓库");
                    break;
                case AppDelegate.ACCOUNT_NATURE_FORWARDER:
                    tv_accountNature.setText("货代");
                    break;
                case AppDelegate.ACCOUNT_NATURE_DECLARE:
                    tv_accountNature.setText("报关行");
                    break;
                default:
                    tv_accountNature.setText("");
                    break;
            }
        } else {
            hideView(ll_accountNature);
        }

        // 如果本地头像文件存在那么在刚进入界面的时候就获取并设置
        // ImageLoader会做图片的缓存处理，如果本地相册图片已经被删除了，但是ImageLoader缓存的图片还是存在的话，那么图片还是会自动显示的
        // 若清除缓存的时候将ImageLoader缓存图片文件夹清除，那么图片将不会再显示，目前清除缓存时没有做清除ImageLoader缓存图片文件夹的处理
        String path = userInfoSp.getString(AppDelegate.USER_ICON_PATH + userInfoSp.getString(AppDelegate.LOGIN_NAME, ""), "");
        if (!TextUtils.isEmpty(path) && new File(path).exists())
            imageLoader.displayImage("file://" + path, img_user_icon);

    }

    /**
     * 退出登录
     */
    @Click(R.id.btn_logout)
    void logout() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.MyAlertDialogStyle);
        builder.setMessage("确定退出“" + MyApplication_.getInstance().getPlatFormString() + "”?")
                .setPositiveButton("退出", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 退出需要重新登录，所以要将isLogin设置为false,避免会根据已存用户信息自动登录
                        userInfoSp.edit().putBoolean(AppDelegate.IS_LOGIN, false).commit();
                        // 跳转登录界面,重新登录
                        LoadingActivity_.intent(UserInfoActivity.this).flags(Intent.FLAG_ACTIVITY_NEW_TASK).start();
                        // 友盟账号登出时需调用此接口，调用之后不再发送账号相关内容
                        MobclickAgent.onProfileSignOff();
                        // 退出登录必须要把之前的界面全部Finish掉，不然按返回键还是会回到之前界面
                        // 目前退出登录需要关掉的界面：自身 和 MainActivty
                        finish();
                        // 暂时不清除，这样有头像图片体验好一点
                        //  ImageLoader.getInstance().clearDiscCache(); // 清除自定义的ImageLoader缓存的图片
                        MyApplication_.getInstance().exit();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).create().show();
    }

    /**
     * 拍摄/选择照片
     */
    @Click(R.id.rl_user_icon)
    void selectUserIcon() {
        SelectPictureActivity_.intent(this).startForResult(REQUEST_PICK);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null) {
            return;
        }
        if (requestCode == REQUEST_PICK) {
            // 刚新换的头像文件本地地址
            String path = data.getStringExtra(SelectPictureActivity.INTENT_SELECTED_PICTURE);
            // 先加载本地(速度快)，然后设置到控件上
            imageLoader.displayImage("file://" + path, img_user_icon);
            // 将本地头像图片地址保存
            userInfoSp.edit().putString(AppDelegate.USER_ICON_PATH + userInfoSp.getString(AppDelegate.LOGIN_NAME, ""), path).commit();
            // 发送广播：让MineFragment更新头像
            LocalBroadcastManager.getInstance(this).sendBroadcast(new Intent(AppDelegate.ACTION_UPDATE_USER_ICON));

            LogUtils.d("头像文件地址：" + path);
        }
        super.onActivityResult(requestCode, resultCode, data);
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

}
