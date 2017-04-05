package com.qskj.tyt.activity;

import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;


import com.qskj.tyt.R;
import com.qskj.tyt.entity.T_AccountFundDetailsEntity;
import com.qskj.tyt.utils.StringUtil;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * 账户资金条目详情页
 */
@EActivity(R.layout.t_activity_account_fund_details)
public class T_AccountFundDetailsActivity extends BaseActivity {

    @ViewById(R.id.tv_earning_or_expenditure)
    AppCompatTextView tv_earning_or_expenditure; // 显示 收入/支出

    @ViewById(R.id.tv_amountRmb)
    AppCompatTextView tv_amountRmb; // 收入/支出 金额

    @ViewById(R.id.tv_createDate)
    AppCompatTextView tv_createDate; // 日期

    @ViewById(R.id.tv_fund_name)
    AppCompatTextView tv_fund_name; // 项目

    @ViewById(R.id.tv_export_invoice_number)
    AppCompatTextView tv_export_invoice_number; // 外销发票号

    @ViewById(R.id.tv_original_currency)
    AppCompatTextView tv_original_currency; //  原币金额

    @ViewById(R.id.tv_exchange_rate)
    AppCompatTextView tv_exchange_rate; // 汇率

    @ViewById(R.id.tv_related_company_name)
    AppCompatTextView tv_related_company_name; // 往来方

    @ViewById(R.id.toolbar)
    Toolbar mToolbar;

    @ViewById(R.id.tv_title)
    AppCompatTextView tv_title;

    private void initToolbar() {
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        tv_title.setText(R.string.title_activity_account_fund_details);
    }

    @Override
    public void onAfterViews() {
        initToolbar();

        T_AccountFundDetailsEntity.RowsEntity rowsEntity =
                (T_AccountFundDetailsEntity.RowsEntity) getIntent().getSerializableExtra("rowsEntity");

        if (rowsEntity != null) {
            double amountRmb = rowsEntity.getAmountRmb();

            if (amountRmb <= 0) {
                tv_earning_or_expenditure.setText(getStrings(R.string.expenditure));
                tv_amountRmb.setText(StringUtil.numberFormat(amountRmb));
                tv_amountRmb.setTextColor(getColors(R.color.red_500));
            } else {
                tv_earning_or_expenditure.setText(getStrings(R.string.earning));
                tv_amountRmb.setText("+" + StringUtil.numberFormat(amountRmb));
                tv_amountRmb.setTextColor(getColors(R.color.green_300));
            }

            tv_createDate.setText(StringUtil.dateRemoveT(rowsEntity.getCreateDate()));
            tv_fund_name.setText(rowsEntity.getFundName());

//            public enum FundStatus
//            {
//                未开票=0,
//                已发开票通知=1,
//                //已收到增票=2,
//                已结算=3
//            }

            int status = rowsEntity.getStatus();
            if (status == 0) {
                tv_export_invoice_number.setText(String.valueOf(rowsEntity.getOrderCode()) + "（未开票）");
            } else if (status == 1) {
                tv_export_invoice_number.setText(String.valueOf(rowsEntity.getOrderCode()) + "（已发开票通知）");
            } else if (status == 3) {
                tv_export_invoice_number.setText(String.valueOf(rowsEntity.getOrderCode()) + "（已结算）");
            }
            // 原币金额 （原币 + 金额）
            tv_original_currency.setText(rowsEntity.getOriginalCurrency() + " " + StringUtil.numberFormat(rowsEntity.getOriginalAmount()));
            tv_exchange_rate.setText(StringUtil.numberFormat(rowsEntity.getOriginalRmbRate()));
            tv_related_company_name.setText(rowsEntity.getRelatedCompanyName());
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
