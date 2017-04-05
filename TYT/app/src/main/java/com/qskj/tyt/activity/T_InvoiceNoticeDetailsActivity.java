package com.qskj.tyt.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import com.qskj.tyt.R;
import com.qskj.tyt.adapter.TableLayoutAdapter;
import com.qskj.tyt.entity.T_InvoiceNoticeEntity;
import com.qskj.tyt.fragment.T_InvoiceNoticeDetails_InvoiceDetailsFragment_;
import com.qskj.tyt.fragment.T_InvoiceNoticeDetails_ProductsDetailsFragment_;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * 开票通知-详情页
 */
@EActivity(R.layout.tablayout_viewpager_toolbar_with_progressbar)
public class T_InvoiceNoticeDetailsActivity extends BaseActivity {

    @ViewById(R.id.tabLayout)
    TabLayout mTabLayout;

    @ViewById(R.id.viewpager)
    ViewPager mViewPager;

    @ViewById(R.id.tv_title)
    AppCompatTextView tv_title;

    @ViewById(R.id.toolbar)
    Toolbar mToolbar;

    private void initToolbar() {
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        tv_title.setText(R.string.title_activity_invoice_notice_details);
    }

    @Override
    public void onAfterViews() {
        initToolbar();
        initTabLayout();
    }

    private void initTabLayout() {
        T_InvoiceNoticeEntity.RowsEntity rowsEntity = (T_InvoiceNoticeEntity.RowsEntity) getIntent().getSerializableExtra("rowsEntity");

        if (mViewPager != null) {
            TableLayoutAdapter mAdapter = new TableLayoutAdapter(getSupportFragmentManager());

            T_InvoiceNoticeDetails_InvoiceDetailsFragment_ invoiceDetailsFragment_ = new T_InvoiceNoticeDetails_InvoiceDetailsFragment_();
            Bundle bundle = new Bundle();
            bundle.putInt("id", rowsEntity.getId());
            invoiceDetailsFragment_.setArguments(bundle);
            mAdapter.addFragment(invoiceDetailsFragment_, getStrings(R.string.notice_details));

            T_InvoiceNoticeDetails_ProductsDetailsFragment_ productsDetailsFragment_ = new T_InvoiceNoticeDetails_ProductsDetailsFragment_();
            Bundle bundle1 = new Bundle();
            bundle1.putInt("id", rowsEntity.getId());
            productsDetailsFragment_.setArguments(bundle1);
            mAdapter.addFragment(productsDetailsFragment_, getStrings(R.string.products_details));

            mViewPager.setAdapter(mAdapter);
            mTabLayout.setupWithViewPager(mViewPager);
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

}
