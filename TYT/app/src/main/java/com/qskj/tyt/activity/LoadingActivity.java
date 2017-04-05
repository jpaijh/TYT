package com.qskj.tyt.activity;

import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.LinearLayout;

import com.qskj.tyt.AppDelegate;
import com.qskj.tyt.MyApplication_;
import com.qskj.tyt.R;
import com.umeng.analytics.MobclickAgent;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import co.mobiwise.materialintro.MaterialIntroConfiguration;
import co.mobiwise.materialintro.animation.MaterialIntroListener;
import co.mobiwise.materialintro.shape.Focus;
import co.mobiwise.materialintro.shape.FocusGravity;
import co.mobiwise.materialintro.view.MaterialIntroView;

/**
 * Loading 界面，平台选择界面
 */
@EActivity(R.layout.activity_loading)
public class LoadingActivity extends BaseActivity {

    private static final String TAG = "LoadingActivity";
    private boolean isCanClick;

    @ViewById(R.id.ll_btns)
    LinearLayout ll_buttons;

    @ViewById(R.id.btn_crossBorder)
    AppCompatButton btn_crossBorder;

    @ViewById(R.id.btn_trading)
    AppCompatButton btn_trading;

    @Override
    public void onAfterViews() {
        MyApplication_.getInstance().addActivity(this);
        onBackgrounds();
    }

    @Override
    public void onBackgrounds() {
        try {
            if (getSharedPreferences(AppDelegate.SP_USER_INFO, MODE_PRIVATE).getBoolean(AppDelegate.IS_LOGIN, false)) {
                Thread.sleep(2000);
                MainActivity_.intent(this).start();
                finish();
            } else {
                initButtons();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @UiThread
    public void initButtons() {
        ll_buttons.setVisibility(View.VISIBLE);

        btn_crossBorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyApplication_.getInstance().getPlatFormEditor().putInt(AppDelegate.PLATFORM, AppDelegate.PLATFORM_CROSS_BORDER).commit();
                LoginActivity_.intent(LoadingActivity.this).start();
            }
        });

        btn_trading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyApplication_.getInstance().getPlatFormEditor().putInt(AppDelegate.PLATFORM, AppDelegate.PLATFORM_TRADING).commit();
                LoginActivity_.intent(LoadingActivity.this).start();
            }
        });

        initMaterialIntroView();
    }

    private void initMaterialIntroView() {
        final MaterialIntroConfiguration config = new MaterialIntroConfiguration();
        config.setMaskColor(getColors(R.color.black_alpha_144));// 背景颜色
        config.setFocusGravity(FocusGravity.CENTER);// 需显示在控件上的位置
        config.setFocusType(Focus.NORMAL);// 显示的圈的大小（MINIMUM小，NORMAL正常，ALL大（包裹控件））
        config.setPadding(10); // 设置圈的Padding
        config.setDotViewEnabled(true); // 是否显示中间带动画的放大缩小的白点
        config.setFadeAnimationEnabled(true);
        // config.setDelayMillis(1000); // 延迟显示时间
        // config.setColorTextViewInfo(getColors(R.color.color_theme)); // 设置提示字体颜色

        // 给两个按钮添加透明指示View
        new MaterialIntroView.Builder(this).setConfiguration(config)
                .setTarget(btn_crossBorder).setUsageId("btn_crossBorder")
                .setInfoText("点击这里，登录\"跨境服务平台\"")
                .setInfoTextSize(16).enableIcon(true)   // 是否显示 ？ 图标
                .performClick(false) // 点击提示消失，true:能点击到包裹控件，false:不能点击到包裹控件
                .setListener(new MaterialIntroListener() {
                    @Override
                    public void onUserClicked(String s) {
                        if (!isCanClick) {
                            btn_crossBorder.setEnabled(false);
                            new MaterialIntroView.Builder(LoadingActivity.this).setConfiguration(config)
                                    .setTarget(LoadingActivity.this.btn_trading).setUsageId("btn_trading")
                                    .setInfoText("点击这里，登录\"外贸服务平台\"")
                                    .setInfoTextSize(16).enableIcon(true).performClick(false)
                                    .setListener(new MaterialIntroListener() {
                                        @Override
                                        public void onUserClicked(String s) {
                                            btn_crossBorder.setEnabled(true);
                                        }
                                    })
                                    .show();
                            isCanClick = true;
                        }
                    }
                })
                .show();

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

    /**
     * Loading界面-返回键就是退出应用
     */
    @Override
    public void onBackPressed() {
        MobclickAgent.onKillProcess(this);
        System.exit(0);
    }

}
