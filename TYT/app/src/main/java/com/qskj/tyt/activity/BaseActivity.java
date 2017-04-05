package com.qskj.tyt.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.qskj.tyt.AppDelegate;
import com.qskj.tyt.R;
import com.qskj.tyt.utils.DateUtil;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EActivity;

/**
 * Activity 基类
 */
@EActivity
public class BaseActivity extends AppCompatActivity {

    /**
     * @AfterViews 布局初始化完成
     */
    @AfterViews
    public void onAfterViews() {
    }

    /**
     * @Background 网络请求数据
     */
    @Background
    public void onBackgrounds() {
    }

    /**
     * 获取颜色资源
     */
    public int getColors(int colorRes) {
        return getResources().getColor(colorRes);
    }

    /**
     * 获取文字资源
     *
     * @param stringRes
     * @return
     */
    public String getStrings(int stringRes) {
        return getResources().getString(stringRes);
    }

    /**
     * 保存当前时间
     *
     * @param str 键名
     */
    public void saveCurrentTime(String str) {
        getSharedPreferences(AppDelegate.SP_REFRESH_DATE, Context.MODE_PRIVATE).edit().
                putString(str, DateUtil.longDateToStrMDHMS(System.currentTimeMillis())).commit();
    }

    /**
     * 创建 正在刷新... - 上次刷新时间 Snackbar（用 SharedPreferences 保存上次刷新时间）
     *
     * @param view ViewGroup 子类
     * @param str  记录上次刷新时间的标记：统一用TAG
     */
    public void createRefreshSnackbar(ViewGroup view, String str) {
        String currentDate = DateUtil.longDateToStrMDHMS(System.currentTimeMillis());
        SharedPreferences spRefreshDate = getSharedPreferences(AppDelegate.SP_REFRESH_DATE, Context.MODE_PRIVATE);
        String lastRefreshDate = spRefreshDate.getString(str, currentDate);
        Snackbar snackbar = Snackbar.make(view, "正在刷新...", Snackbar.LENGTH_SHORT);
        snackbar.setAction("上次刷新时间：" + lastRefreshDate, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 这个点击事件必须加上不然 snackbar_action 就无法显示
                // snackbar.dismiss();
            }
        });
        TextView snackbar_action = (TextView) snackbar.getView().findViewById(R.id.snackbar_action);
        snackbar_action.setTextSize(11f);
        snackbar.setActionTextColor(getColors(R.color.white));
        Snackbar.SnackbarLayout snackBarLayout = (Snackbar.SnackbarLayout) snackbar.getView();
        snackBarLayout.setBackgroundColor(getColors(R.color.color_theme));
        snackBarLayout.setAlpha(0.9f);
        snackbar.show();
        spRefreshDate.edit().putString(str, currentDate).commit();
    }

    /**
     * 创建 正在加载... Snackbar
     *
     * @param view ViewGroup 子类
     */
    public void createLoadingSnackbar(ViewGroup view) {
        Snackbar snackbar = Snackbar.make(view, "正在加载...", Snackbar.LENGTH_INDEFINITE);
        Snackbar.SnackbarLayout snackBarLayout = (Snackbar.SnackbarLayout) snackbar.getView();
        snackBarLayout.setBackgroundColor(getColors(R.color.color_theme));
        snackBarLayout.setAlpha(0.9f);
        snackbar.show();
    }

    /**
     * 创建 加载完成... Snackbar
     *
     * @param view ViewGroup 子类
     */
    public void createLoadingCompleteSnackbar(ViewGroup view) {
        Snackbar snackbar = Snackbar.make(view, "加载完成...", Snackbar.LENGTH_SHORT);
        Snackbar.SnackbarLayout snackBarLayout = (Snackbar.SnackbarLayout) snackbar.getView();
        snackBarLayout.setBackgroundColor(getColors(R.color.decoView_line1_start_color));
        snackBarLayout.setAlpha(0.9f);
        snackbar.show();
    }

    /**
     * 创建 已经没有数据了...  Snackbar
     *
     * @param view ViewGroup 子类
     */
    public void createNoDateSnackbar(ViewGroup view) {
        Snackbar snackbar = Snackbar.make(view, "已经没有数据了...", Snackbar.LENGTH_SHORT);
        Snackbar.SnackbarLayout snackBarLayout = (Snackbar.SnackbarLayout) snackbar.getView();
        snackBarLayout.setBackgroundColor(getColors(R.color.red));
        snackBarLayout.setAlpha(0.8f);
        snackbar.show();
    }

    /**
     * 创建 刷新成功...  Snackbar
     *
     * @param view ViewGroup 子类
     */
    public void createRefreshCompleteSnackbar(ViewGroup view) {
        Snackbar snackbar = Snackbar.make(view, "刷新成功...", Snackbar.LENGTH_SHORT);
        Snackbar.SnackbarLayout snackBarLayout = (Snackbar.SnackbarLayout) snackbar.getView();
        snackBarLayout.setBackgroundColor(getColors(R.color.decoView_line1_start_color));
        snackBarLayout.setAlpha(0.9f);
        snackbar.show();
    }

    /**
     * 显示 View
     *
     * @param view
     */
    public void showView(View view) {
        view.setVisibility(View.VISIBLE);
    }

    /**
     * 隐藏 View
     *
     * @param view
     */
    public void hideView(View view) {
        view.setVisibility(View.GONE);
    }

}
