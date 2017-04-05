package com.qskj.tyt.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.qskj.tyt.R;
import com.qskj.tyt.adapter.TableLayoutAdapter;
import com.qskj.tyt.fragment.T_WarningCenter_InvoiceWarning_;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * 预警中心
 */
@EActivity(R.layout.tablayout_viewpager_toolbar_with_progressbar)
public class T_WarningCenterActivity extends BaseActivity {

    @ViewById(R.id.tabLayout)
    TabLayout mTabLayout;

    @ViewById(R.id.viewpager)
    ViewPager mViewPager;

    @ViewById(R.id.tv_title)
    AppCompatTextView tv_title;

    @ViewById(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    public void onAfterViews() {
        initToolbar();
        mViewPager.setOffscreenPageLimit(4);

        if (mViewPager != null) {
            TableLayoutAdapter mAdapter = new TableLayoutAdapter(getSupportFragmentManager());
            mAdapter.addFragment(new T_WarningCenter_InvoiceWarning_(), getStrings(R.string.invoice_warning));
            mAdapter.addFragment(new T_WarningCenter_InvoiceWarning_(), getStrings(R.string.invoice_approve_warning));
            mAdapter.addFragment(new T_WarningCenter_InvoiceWarning_(), getStrings(R.string.credit_card_validity_warning));
            mAdapter.addFragment(new T_WarningCenter_InvoiceWarning_(), getStrings(R.string.credit_card_at_latest_shipping_warning));
            mAdapter.addFragment(new T_WarningCenter_InvoiceWarning_(), getStrings(R.string.credit_card_surrender_document_warning));

//            mAdapter.addFragment(new WarningCenter_InvoiceApproveWarning_(), getStrings(R.string.invoice_approve_warning));
//            mAdapter.addFragment(new WarningCenter_CreditCardValidityWarning_(), getStrings(R.string.credit_card_validity_warning));
//            mAdapter.addFragment(new WarningCenter_CreditCardAtLatestShippingWarning_(), getStrings(R.string.credit_card_at_latest_shipping_warning));
//            mAdapter.addFragment(new WarningCenter_CreditCardSurrenderDocumentWarning_(), getStrings(R.string.credit_card_surrender_document_warning));
            mViewPager.setAdapter(mAdapter);
            mTabLayout.setupWithViewPager(mViewPager);
        }
    }

    public void initToolbar() {
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        tv_title.setText(R.string.title_activity_warning_center);
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
