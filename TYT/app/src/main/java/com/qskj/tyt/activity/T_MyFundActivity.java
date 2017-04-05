package com.qskj.tyt.activity;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.hookedonplay.decoviewlib.DecoView;
import com.hookedonplay.decoviewlib.charts.EdgeDetail;
import com.hookedonplay.decoviewlib.charts.SeriesItem;
import com.hookedonplay.decoviewlib.charts.SeriesLabel;
import com.hookedonplay.decoviewlib.events.DecoEvent;
import com.qskj.tyt.R;
import com.qskj.tyt.adapter.TableLayoutAdapter;
import com.qskj.tyt.fragment.T_MyFund_AccountFinancingFragment_;
import com.qskj.tyt.fragment.T_MyFund_AccountGeneralFragment_;
import com.qskj.tyt.utils.StringUtil;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * 我的资金
 */
@EActivity(R.layout.t_activity_my_fund)
public class T_MyFundActivity extends BaseActivity {

    private float lineWidth = 36f; // 圆环线宽,单位：px
    private int Duration = 1000; // 圆环转动持续时间，单位:ms
    private double dou_total;

    @ViewById(R.id.deco_view)
    DecoView mDecoView; // 动态圆环

    @ViewById(R.id.tv_total_balance)
    TextView tv_TotalBalance; // 总资金余额

    @ViewById(R.id.tv_general_account)
    TextView mGeneralAccount; // 普通账户

    @ViewById(R.id.tv_financing_account)
    TextView mFinancingAccount; // 融资账户

    @ViewById(R.id.tabLayout)
    TabLayout mTabLayout;

    @ViewById(R.id.viewpager)
    ViewPager mViewPager;

    @ViewById(R.id.toolbar)
    Toolbar mToolbar;

    @ViewById(R.id.tv_title)
    AppCompatTextView tv_title;

    @Override
    public void onAfterViews() {
        double[] accountFundBalances = getIntent().getDoubleArrayExtra("AccountFundBalance");
        dou_total = accountFundBalances[2];
        initDecoView((float) accountFundBalances[0], (float) accountFundBalances[1], (float) dou_total);

        // 必须将ToolBar设置给ActionBar，因为该界面主题是Application的主题是没有ActionBar的,否则getSupportActionBar()会报空指针
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        tv_title.setText(R.string.title_activity_my_fund);

        // 初始化ViewPager，两个Fragment和Tablayout
        if (mViewPager != null) {
            TableLayoutAdapter mAdapter = new TableLayoutAdapter(getSupportFragmentManager());
            mAdapter.addFragment(new T_MyFund_AccountGeneralFragment_(), getStrings(R.string.general_account));
            mAdapter.addFragment(new T_MyFund_AccountFinancingFragment_(), getStrings(R.string.financing_account));
            mViewPager.setAdapter(mAdapter);
            mTabLayout.setupWithViewPager(mViewPager);
        }

    }

    /**
     * 初始化圆环，圆点
     *
     * @param totalBalance     总余额
     * @param generalAccount   普通账户
     * @param financingAccount 融资账户
     */
    private void initDecoView(final float generalAccount, final float financingAccount, final float totalBalance) {
        float minValue = 0;     // 数据最小值（必须minValue<maxValue）
        float initialValue = 0; // 数据显示的初始化值（必须minValue<initialValue<maxValue）

        tv_TotalBalance.setText(StringUtil.numberFormat(dou_total));

        //总余额为0，画一个特别的圆环比较好，并给文字提示，这个全部做完还是要测一下总余额为0这个情况
        if (totalBalance == 0) {
            mDecoView.addSeries(new SeriesItem.Builder(getColors(R.color.black_alpha_64))
                    .setRange(minValue, 100, 100).setLineWidth(lineWidth).build());
            return;
        }

        // 第一段：普通账户
        // SeriesItem. Builder(int color,int colorSecondary)构造是圆环的一半一半会有两种颜色
        final SeriesItem seriesGeneralAccount = new SeriesItem.Builder(getColors(R.color.color_progress_2))
                .setRange(minValue, totalBalance, initialValue)
                .setInitialVisibility(false) // 是否显示设置的数据的初始化的值，默认：true 显示
                .setLineWidth(lineWidth)
                // 设置内外圈边缘细节：设定边缘内圈或者外圈的颜色和占圈宽度的比例
                .addEdgeDetail(new EdgeDetail(EdgeDetail.EdgeType.EDGE_OUTER, Color.parseColor("#22000000"), 0.4f))
                .setShowPointWhenEmpty(false) // 当圆环从开始位置旋转时，在起始位置显示一个点，默认：true 显示
                .setCapRounded(false) // 设置圆环线是圆角，默认:true 圆角
                .setSpinClockwise(false) // false:逆时针,默认true:顺时针
//              .setChartStyle(SeriesItem.ChartStyle.STYLE_DONUT) // STYLE_PIE：饼状图 默认：STYLE_DONUT 不设置风格
//              .setInset(new PointF(100f, 200f)) // 设置圆环内凹，从而可改变圆环的形状，下面设置画出来就是一个椭圆
                .setSeriesLabel(new SeriesLabel.Builder("Percent %.0f%%").build()) // 显示标签
//                .setInterpolator(new OvershootInterpolator()) // 进度回退的效果
                .build();

        // 第二段：融资账户
        final SeriesItem seriesFinancingAccount = new SeriesItem.Builder(getColors(R.color.color_progress_1))
                .setRange(minValue, totalBalance, initialValue)
                .setLineWidth(lineWidth).setShowPointWhenEmpty(false).setSpinClockwise(true) // 顺时针
                .setCapRounded(false).addEdgeDetail(new EdgeDetail(EdgeDetail.EdgeType.EDGE_OUTER, Color.parseColor("#22000000"), 0.4f))
                .build();

        int seriesIndexGeneralAccount = mDecoView.addSeries(seriesGeneralAccount);
        int seriesIndexFinancingAccount = mDecoView.addSeries(seriesFinancingAccount);

        // 设置颜色绘图时可以达到颜色渐变效果，最终由SeriesItem.Builder(int color)变为setCoror(int color)里的值
        mDecoView.addEvent(new DecoEvent.Builder(generalAccount).setIndex(seriesIndexGeneralAccount).setColor(getColors(R.color.color_progress_2)).setDuration(Duration).build());
        mDecoView.addEvent(new DecoEvent.Builder(financingAccount).setIndex(seriesIndexFinancingAccount).setColor(getColors(R.color.color_progress_1)).setDuration(Duration).build());

        // 设置动态的数据变化
        seriesGeneralAccount.addArcSeriesItemListener(new SeriesItem.SeriesItemListener() {
            @Override
            public void onSeriesItemAnimationProgress(float percentComplete, float currentPosition) {
                double percentFilled = currentPosition / seriesGeneralAccount.getMaxValue();
                // 格式化打印 只显示两位小数
                mGeneralAccount.setText(StringUtil.numberFormat(percentFilled * dou_total));
            }

            @Override
            public void onSeriesItemDisplayProgress(float percentComplete) {

            }
        });

        seriesFinancingAccount.addArcSeriesItemListener(new SeriesItem.SeriesItemListener() {
            @Override
            public void onSeriesItemAnimationProgress(float percentComplete, float currentPosition) {
                double percentFilled = currentPosition / seriesFinancingAccount.getMaxValue();
                mFinancingAccount.setText(StringUtil.numberFormat(percentFilled * dou_total));
            }

            @Override
            public void onSeriesItemDisplayProgress(float percentComplete) {

            }
        });

        // 圆环显示百分比方法
//        final String format = "%.0f%%";
//        seriesGeneralAccount.addArcSeriesItemListener(new SeriesItem.SeriesItemListener() {
//            @Override
//            public void onSeriesItemAnimationProgress(float percentComplete, float currentPosition) {
//                if (format.contains("%%")) {
//                    float percentFilled = ((currentPosition - seriesGeneralAccount.getMinValue()) / (seriesGeneralAccount.getMaxValue() - seriesGeneralAccount.getMinValue()));
//                    tv_TotalBalance.setText(String.format(format, percentFilled * 100f));
//                } else {
//                    tv_TotalBalance.setText(String.format(format, currentPosition));
//                }
//            }
//
//            @Override
//            public void onSeriesItemDisplayProgress(float percentComplete) {
//
//            }
//        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.action_search: // 进入搜索界面
                int selectedTabPosition = mTabLayout.getSelectedTabPosition();
                if (selectedTabPosition == 0) { // 普通账户
                    T_AccountSearchActivity_.intent(this).extra("ACCOUNT_TYPE", 0).start();
                } else if (selectedTabPosition == 1) { // 融资账户
                    T_AccountSearchActivity_.intent(this).extra("ACCOUNT_TYPE", 1).start();
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
