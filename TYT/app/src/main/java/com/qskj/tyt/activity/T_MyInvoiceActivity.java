package com.qskj.tyt.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.lsjwzh.widget.materialloadingprogressbar.CircleProgressBar;
import com.qskj.tyt.R;
import com.qskj.tyt.adapter.TableLayoutAdapter;
import com.qskj.tyt.fragment.T_MyInvoice_InvoiceInformationFragment_;
import com.qskj.tyt.fragment.T_MyInvoice_InvoiceNoticeFragment_;
import com.umeng.analytics.MobclickAgent;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * 我的票据
 * Created by 赵 鑫 on 2015/9/16.
 */
@EActivity(R.layout.tablayout_viewpager_toolbar_with_progressbar)
public class T_MyInvoiceActivity extends BaseActivity {

    private static final String TAG = "T_MyInvoiceActivity";

    @ViewById(R.id.tabLayout)
    TabLayout mTabLayout;

    @ViewById(R.id.viewpager)
    ViewPager mViewPager;

    @ViewById(R.id.tv_title)
    AppCompatTextView tv_title;

    @ViewById(R.id.toolbar)
    Toolbar mToolbar;

    @ViewById(R.id.progressbar)
    CircleProgressBar mProgressbar;

    @AfterViews
    public void onAfterViews() {
        initToolbar();
        showView(mProgressbar);

        if (mViewPager != null) {
            TableLayoutAdapter mAdapter = new TableLayoutAdapter(getSupportFragmentManager());
            mAdapter.addFragment(new T_MyInvoice_InvoiceNoticeFragment_(), getStrings(R.string.invoice_notice));
            mAdapter.addFragment(new T_MyInvoice_InvoiceInformationFragment_(), getStrings(R.string.invoice_information));
            mViewPager.setAdapter(mAdapter);
            mTabLayout.setupWithViewPager(mViewPager);
        }

        hideView(mProgressbar);
    }

    private void initToolbar() {
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        tv_title.setText(R.string.title_activity_my_invoice);
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
