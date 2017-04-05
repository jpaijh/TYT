package com.qskj.tyt.activity;

import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.lsjwzh.widget.materialloadingprogressbar.CircleProgressBar;
import com.qskj.tyt.AppDelegate;
import com.qskj.tyt.R;
import com.qskj.tyt.entity.CB_CommodityManageEntity;
import com.qskj.tyt.utils.StringUtil;
import com.umeng.analytics.MobclickAgent;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * 商品管理-商品详情
 */
@EActivity(R.layout.cb_activity_commodity_details)
public class CB_CommodityDetailsActivity extends BaseActivity {

    private static final String TAG = "CB_CommodityDetailsActivity";

    @ViewById(R.id.progressbar)
    CircleProgressBar mProgressbar;

    @ViewById(R.id.tv_title)
    AppCompatTextView tv_title;

    @ViewById(R.id.toolbar)
    Toolbar mToolbar;

    @ViewById(R.id.nestedScrollView)
    NestedScrollView mNestedScrollView;

    @ViewById(R.id.tv_commodityNameCn)
    AppCompatTextView tv_commodityNameCn;

    @ViewById(R.id.tv_commodityNameEn)
    AppCompatTextView tv_commodityNameEn;

    @ViewById(R.id.tv_hsCode)
    AppCompatTextView tv_hsCode;

    @ViewById(R.id.tv_commodityManagementGrid_statusView)
    AppCompatTextView tv_commodityManagementGrid_statusView;

    @ViewById(R.id.tv_commodityAttribute_specifications)
    AppCompatTextView tv_commodityAttribute_specifications;

    @ViewById(R.id.tv_vatRate)
    AppCompatTextView tv_vatRate;

    @ViewById(R.id.tv_retRate)
    AppCompatTextView tv_retRate;

    @ViewById(R.id.tv_commodityAttribute_categoryName)
    AppCompatTextView tv_commodityAttribute_categoryName;

    @ViewById(R.id.tv_commodityAttribute_categoryCode)
    AppCompatTextView tv_commodityAttribute_categoryCode;

    @ViewById(R.id.tv_commodityAttribute_supervisionCondition)
    AppCompatTextView tv_commodityAttribute_supervisionCondition;

    @ViewById(R.id.tv_customsRecordNo)
    AppCompatTextView tv_customsRecordNo;

    @ViewById(R.id.tv_commodityPrice_price)
    AppCompatTextView tv_commodityPrice_price;

    @ViewById(R.id.tv_unitDetail_cn)
    AppCompatTextView tv_unitDetail_cn;

    @ViewById(R.id.tv_commodityAttribute_materialQuality)
    AppCompatTextView tv_commodityAttribute_materialQuality;

    @ViewById(R.id.tv_commodityAttribute_commodityBrand)
    AppCompatTextView tv_commodityAttribute_commodityBrand;

    @ViewById(R.id.tv_commodityAttribute_placeOfOrigin)
    AppCompatTextView tv_commodityAttribute_placeOfOrigin;

    @ViewById(R.id.tv_commodityAttribute_description)
    AppCompatTextView tv_commodityAttribute_description;

    @ViewById(R.id.tv_commodityAttribute_isPublishToElecplt)
    AppCompatTextView tv_commodityAttribute_isPublishToElecplt;

    @Override
    public void onAfterViews() {
        initToolbar();
        initViews();
    }

    private void initViews() {
        showProgress();

        CB_CommodityManageEntity.RowsEntity rowsEntity = (CB_CommodityManageEntity.RowsEntity) getIntent().getSerializableExtra(AppDelegate.COMMODITY_MANAGE_ROWS_ENTITY);
        CB_CommodityManageEntity.RowsEntity.CommodityPriceEntity commodityPriceEntity = rowsEntity.getCommodityPrice();
        CB_CommodityManageEntity.RowsEntity.CommodityAttributeEntity commodityAttributeEntity = rowsEntity.getCommodityAttribute();
        CB_CommodityManageEntity.RowsEntity.UnitDetailEntity unitDetailEntity = rowsEntity.getUnitDetail();

        tv_title.setText(rowsEntity.getCommodityNameCn());
        tv_commodityNameCn.setText(rowsEntity.getCommodityNameCn());
        tv_commodityNameEn.setText(rowsEntity.getCommodityNameEn());
        tv_hsCode.setText(rowsEntity.getHsCode());
        tv_commodityManagementGrid_statusView.setText(rowsEntity.getStatusView());
        tv_commodityAttribute_specifications.setText(commodityAttributeEntity.getSpecifications());
        tv_vatRate.setText(StringUtil.numberFormat(rowsEntity.getVatRate()));
        tv_retRate.setText(StringUtil.numberFormat(rowsEntity.getRetRate()));
        tv_commodityAttribute_categoryName.setText(commodityAttributeEntity.getCategoryName());
        tv_commodityAttribute_categoryCode.setText(commodityAttributeEntity.getCategoryCode());
        tv_customsRecordNo.setText(rowsEntity.getCustomsRecordNo());
        if (commodityPriceEntity.getPrice() == 0) {
            tv_commodityPrice_price.setText("");
        } else {
            tv_commodityPrice_price.setText(StringUtil.numberFormat(commodityPriceEntity.getPrice()) + " "
                    + commodityPriceEntity.getPriceCurrency() != null ? commodityPriceEntity.getPriceCurrency() : "");
        }
        tv_commodityAttribute_supervisionCondition.setText(commodityAttributeEntity.getSupervisionCondition());
        tv_unitDetail_cn.setText(unitDetailEntity.getCn());
        tv_commodityAttribute_materialQuality.setText(commodityAttributeEntity.getMaterialQuality());
        tv_commodityAttribute_commodityBrand.setText(commodityAttributeEntity.getCommodityBrand());
        tv_commodityAttribute_placeOfOrigin.setText(commodityAttributeEntity.getPlaceOfOrigin());
        tv_commodityAttribute_description.setText(commodityAttributeEntity.getDescription());
        tv_commodityAttribute_isPublishToElecplt.setText(commodityAttributeEntity.isIsPublishToElecplt() == true ? "是" : "否");

        hideProgress();
    }

    public void initToolbar() {
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
    }

    private void hideProgress() {
        hideView(mProgressbar);
        showView(mNestedScrollView);
    }

    private void showProgress() {
        showView(mProgressbar);
        hideView(mNestedScrollView);
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
