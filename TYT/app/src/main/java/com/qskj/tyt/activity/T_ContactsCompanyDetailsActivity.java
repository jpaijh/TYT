package com.qskj.tyt.activity;

import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.lsjwzh.widget.materialloadingprogressbar.CircleProgressBar;
import com.qskj.tyt.AppDelegate;
import com.qskj.tyt.R;
import com.qskj.tyt.entity.T_ContactsCompanyEntity;
import com.qskj.tyt.utils.StringUtil;
import com.umeng.analytics.MobclickAgent;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * 往来单位-列表详情
 */
@EActivity(R.layout.t_activity_contacts_company_details)
public class T_ContactsCompanyDetailsActivity extends BaseActivity {

    private static final String TAG = "T_ContactsCompanyDetailsActivity";
    private T_ContactsCompanyEntity.RowsEntity rowsEntity;

    @ViewById(R.id.progressbar)
    CircleProgressBar mProgressbar;

    @ViewById(R.id.tv_title)
    AppCompatTextView tv_title;

    @ViewById(R.id.toolbar)
    Toolbar mToolbar;

    @ViewById(R.id.nestedScrollView)
    NestedScrollView mNestedScrollView;

    @ViewById(R.id.tv_nameCn)
    AppCompatTextView tv_nameCn; // 名称（公司名称）

    @ViewById(R.id.tv_status)
    AppCompatTextView tv_status; // 状态

    @ViewById(R.id.tv_contactPerson)
    AppCompatTextView tv_contactPerson; // 联系人

    @ViewById(R.id.tv_contactWay)
    AppCompatTextView tv_contactWay; // 联系方式

    @ViewById(R.id.tv_addressCn)
    AppCompatTextView tv_addressCn; // 地址

    @ViewById(R.id.tv_legalRepresentative)
    AppCompatTextView tv_legalRepresentative; // 法人

    @ViewById(R.id.tv_registeredAddress)
    AppCompatTextView tv_registeredAddress; // 注册地址

    @ViewById(R.id.tv_registeredCaption)
    AppCompatTextView tv_registeredCaption; // 注册资金

    @ViewById(R.id.tv_creditInformation)
    AppCompatTextView tv_creditInformation; // 资信调查信息

    @ViewById(R.id.tv_remark)
    AppCompatTextView tv_remark;  // 备注

    @Override
    public void onAfterViews() {
        initToolbar();
        initViews();
    }

    private void initViews() {
        showProgress();
        rowsEntity = (T_ContactsCompanyEntity.RowsEntity) getIntent().getSerializableExtra(AppDelegate.ROWS_ENTITY);

        tv_title.setText(rowsEntity.getNameCn());
        tv_nameCn.setText(rowsEntity.getNameCn());
        // <!-- 0：新制 1：待批，2：审批通过 -2：审批不通过-->
        switch (rowsEntity.getStatus()) {
            case 0: // 新制
                tv_status.setText(getStrings(R.string.brand_new));
                break;
            case 1: // 待批
                tv_status.setText(getStrings(R.string.wait_approve));
                break;
            case 2: // 审批同意
                tv_status.setText(getStrings(R.string.pass_approve));
                break;
            case -2: // 审批不同意
                tv_status.setText(getStrings(R.string.nopass_approve));
                break;
            default:
                tv_status.setText("");
                break;
        }

        tv_contactPerson.setText(rowsEntity.getContactPerson());
        tv_contactWay.setText(rowsEntity.getContactWay());
        tv_addressCn.setText(rowsEntity.getAddressCn());
        tv_legalRepresentative.setText(rowsEntity.getLegalRepresentative());
        tv_registeredAddress.setText(rowsEntity.getRegisteredAddress());
        tv_registeredCaption.setText(StringUtil.numberFormat(rowsEntity.getRegisteredCaption()));
        tv_creditInformation.setText(rowsEntity.getCreditInformation());
        tv_remark.setText(rowsEntity.getRemark());

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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_more, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.action_more:
                if (rowsEntity != null) {
                    if (rowsEntity.getType() == 0) { // 国内往来单位 - 资金往来
                        T_ContactsCompanyDetailsMoreInlandActivity_.intent(T_ContactsCompanyDetailsActivity.this).extra(AppDelegate.ROWS_ENTITY, rowsEntity).start();
                    } else if (rowsEntity.getType() == 1) { // 外商 - 收汇信息
                        T_ContactsCompanyDetailsMoreForeignActivity_.intent(T_ContactsCompanyDetailsActivity.this).extra(AppDelegate.ROWS_ENTITY, rowsEntity).start();
                    }
                }
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
