package com.qskj.tyt.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.lsjwzh.widget.materialloadingprogressbar.CircleProgressBar;
import com.qskj.tyt.AppDelegate;
import com.qskj.tyt.R;
import com.qskj.tyt.adapter.TableLayoutAdapter;
import com.qskj.tyt.entity.CB_ManageDetailsEntity;
import com.qskj.tyt.fragment.CB_ManageDetailsFragment_CommodityInfo_;
import com.qskj.tyt.fragment.CB_ManageDetailsFragment_DeclareInfo_;
import com.qskj.tyt.utils.StringUtil;
import com.umeng.analytics.MobclickAgent;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * 仓单详情-货代-清单查看 界面
 */
@EActivity(R.layout.cb_activity_manage_details)
public class CB_ManageDetailsActivity_HD_ListCheck extends BaseActivity {

    private CB_ManageDetailsEntity CBManageDetailsEntity;

    @ViewById(R.id.tabLayout)
    TabLayout mTabLayout;

    @ViewById(R.id.progressbar)
    CircleProgressBar mProgressbar;

    @ViewById(R.id.viewpager)
    ViewPager mViewPager;

    @ViewById(R.id.tv_title)
    AppCompatTextView tv_title;

    @ViewById(R.id.toolbar)
    Toolbar mToolbar;

    @ViewById(R.id.ll_tablayout_viewpager)
    LinearLayout ll_jydw;

    @ViewById(R.id.frameLayout_head)
    FrameLayout mFrameLayoutHead; // 头部布局

    @ViewById(R.id.tv_head_big_title)
    AppCompatTextView tv_head_big_title;  // 出口申报清单

    @ViewById(R.id.tv_head_title)
    AppCompatTextView tv_head_title; // 头部布局：是否生成报关单

    @ViewById(R.id.tv_head_subtitle)
    AppCompatTextView tv_head_subtitle; // 头部布局：创建时间

    @ViewById(R.id.tv_head_subtitle2)
    AppCompatTextView tv_head_subtitle2; // 头部布局：清单状态

    public void initToolbar() {
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        tv_title.setText(CBManageDetailsEntity.getOrderDelivery().getDeliveryInvoiceCode());
    }

    @Override
    public void onAfterViews() {
        CBManageDetailsEntity = (CB_ManageDetailsEntity) getIntent().getSerializableExtra(AppDelegate.MANAGE_DETAILS_ENTITY);
        showView(mProgressbar);
        initToolbar();
        initHeadView();

        if (mViewPager != null) {
            Bundle bundle = new Bundle();
            bundle.putSerializable(AppDelegate.MANAGE_DETAILS_ENTITY, CBManageDetailsEntity);
            TableLayoutAdapter mAdapter = new TableLayoutAdapter(getSupportFragmentManager());

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

        hideView(mProgressbar);
        showView(mFrameLayoutHead);
        showView(ll_jydw);
    }

    private void initHeadView() {
        tv_head_big_title.setText("出口申报清单");
        tv_head_subtitle.setText("创建日期：" + StringUtil.dateRemoveT(CBManageDetailsEntity.getCreateDate()));

        boolean generatedDeclarationFlag = CBManageDetailsEntity.getOrderDelivery().isGeneratedDeclarationFlag();
        if (generatedDeclarationFlag) {
            tv_head_title.setText("已生成报关单");
        } else {
            tv_head_title.setText("未生成报关单");
        }

        boolean forwarderConfirmed = CBManageDetailsEntity.getOrderDelivery().isForwarderConfirmed();
        tv_head_subtitle2.setVisibility(View.VISIBLE);
        if (forwarderConfirmed) {
            tv_head_subtitle2.setText("清单状态：已确认");
        } else {
            tv_head_subtitle2.setText("清单状态：未确认");
        }
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
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

}