package com.qskj.tyt.activity;

import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.qskj.tyt.AppDelegate;
import com.qskj.tyt.R;
import com.qskj.tyt.entity.T_NoticeForeignExchangeEntity;
import com.qskj.tyt.utils.StringUtil;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * 通知收汇详情
 */
@EActivity(R.layout.t_notice_foreign_exchange_details_list)
public class T_NoticeForeignExchangeDetailsActivity extends BaseActivity {

    @ViewById(R.id.toolbar)
    Toolbar mToolbar;

    @ViewById(R.id.tv_title)
    AppCompatTextView tv_title;

    @ViewById(R.id.tv_amount_currency)
    AppCompatTextView tv_amount_currency;

    @ViewById(R.id.tv_amount)
    AppCompatTextView tv_amount;

    @ViewById(R.id.tv_foreignName)
    AppCompatTextView tv_foreignName;

    @ViewById(R.id.tv_receiptBank)
    AppCompatTextView tv_receiptBank;

    @ViewById(R.id.tv_saleOrder)
    AppCompatTextView tv_saleOrder;

    @ViewById(R.id.tv_createDate)
    AppCompatTextView tv_createDate;

    @ViewById(R.id.tv_receiptDate)
    AppCompatTextView tv_receiptDate;

    @ViewById(R.id.tv_status)
    AppCompatTextView tv_status;

    @ViewById(R.id.tv_remark)
    AppCompatTextView tv_remark;

    @Override
    public void onAfterViews() {
        initToolbar();
        initView();
    }

    private void initToolbar() {
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        tv_title.setText("收汇详情");
    }

    private void initView() {
        T_NoticeForeignExchangeEntity.RowsEntity entity = (T_NoticeForeignExchangeEntity.RowsEntity) getIntent().getSerializableExtra(AppDelegate.ROWS_ENTITY);
        tv_amount_currency.setText("金额(" + entity.getCurrency() + ")");
        tv_amount.setText("+" + StringUtil.numberFormat(entity.getAmount()));
        tv_amount.setTextColor(getColors(R.color.green_300));
        tv_foreignName.setText(entity.getForeignName());
        tv_saleOrder.setText(entity.getSaleOrder());
        tv_receiptBank.setText(entity.getReceiptBank());
        tv_createDate.setText(StringUtil.dateRemoveT(entity.getCreateDate()));
        tv_receiptDate.setText(StringUtil.dateRemoveT(entity.getReceiptDate()));
        tv_remark.setText(entity.getRemark());

        int status = entity.getStatus();
        switch (status) {
            case 0: // 新制
                tv_status.setText(getStrings(R.string.brand_new));
                tv_status.setTextColor(getColors(R.color.red_500));
                break;
            case 1: // 审批汇总
                tv_status.setText(getStrings(R.string.approve_collect));
                tv_status.setTextColor(getColors(R.color.red_500));
                break;
            case -1: // 未申请审批
                tv_status.setText(getStrings(R.string.no_applly_approve));
                tv_status.setTextColor(getColors(R.color.red_500));
                break;
            case 2: // 审批同意
                tv_status.setText(getStrings(R.string.pass_approve));
                tv_status.setTextColor(getColors(R.color.green_300));
                break;
            case -2: // 审批不同意
                tv_status.setText(getStrings(R.string.nopass_approve));
                tv_status.setTextColor(getColors(R.color.red));
                break;
            case 3: // 已支付
                tv_status.setText(getStrings(R.string.prepaid));
                tv_status.setTextColor(getColors(R.color.green_300));
                break;
            case 4: // 已入账
                tv_status.setText(getStrings(R.string.postinged));
                tv_status.setTextColor(getColors(R.color.green_300));
                break;
            case -10: // 待受理
                tv_status.setText(getStrings(R.string.wait_accepted));
                tv_status.setTextColor(getColors(R.color.color_theme));
                break;
            case -11: // 已退回
                tv_status.setText(getStrings(R.string.returned));
                tv_status.setTextColor(getColors(R.color.red_500));
                break;
            case -12: // 结算撤回
                tv_status.setText(getStrings(R.string.settle_revoke));
                tv_status.setTextColor(getColors(R.color.red_500));
                break;
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
