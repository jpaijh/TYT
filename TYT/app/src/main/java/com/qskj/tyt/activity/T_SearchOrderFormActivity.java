package com.qskj.tyt.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatDialogFragment;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.ListPopupWindow;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.appeaser.sublimepickerlibrary.datepicker.SelectedDate;
import com.appeaser.sublimepickerlibrary.helpers.SublimeOptions;
import com.appeaser.sublimepickerlibrary.recurrencepicker.SublimeRecurrencePicker;

import com.qskj.tyt.AppDelegate;
import com.qskj.tyt.MyApplication_;
import com.qskj.tyt.R;
import com.qskj.tyt.adapter.ListpopAdapter;
import com.qskj.tyt.fragment.SublimePickerFragment;
import com.qskj.tyt.utils.DateUtil;
import com.qskj.tyt.utils.DeviceUtil;
import com.qskj.tyt.view.AppCompatAutoCompleteClearTextView;
import com.umeng.analytics.MobclickAgent;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.Set;

/**
 * 订单搜素界面
 */
@EActivity(R.layout.t_activity_order_form_search)
public class T_SearchOrderFormActivity extends BaseActivity {

    private static final String TAG = "T_SearchOrderFormActivity";

    private ListPopupWindow mListPop;
    private String[] listOrderStatus = new String[]{"全部", "草稿", "受理中", "进行中", "已完成", "已拒绝", "已取消"};
    private String[] listOrderAmount = new String[]{"自定义", "3000-5000万", "5000万-1亿", "1亿-2亿", "2亿以上"};
    private int clickTag;

    @ViewById(R.id.acct_order_status)
    AppCompatEditText acct_order_status; // 订单状态

    @ViewById(R.id.acct_sale_invoice)
    AppCompatAutoCompleteClearTextView acct_sale_invoice; // 外销发票号

    @ViewById(R.id.acct_start_date)
    AppCompatAutoCompleteClearTextView acct_start_date; // 起止时间

    @ViewById(R.id.acct_end_date)
    AppCompatAutoCompleteClearTextView acct_end_date; // 结束时间

    @ViewById(R.id.acct_order_amount)
    AppCompatEditText acct_order_amount; // 订单金额

    @ViewById(R.id.acct_min_amount)
    AppCompatAutoCompleteClearTextView acct_min_amount; // 最小金额

    @ViewById(R.id.acct_max_amount)
    AppCompatAutoCompleteClearTextView acct_max_amount; // 最大金额

    @ViewById(R.id.tv_title)
    AppCompatTextView tv_title;

    @ViewById(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    public void onAfterViews() {
        initToolbar();
        initView();
    }

    public void initToolbar() {
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        tv_title.setText(R.string.title_activity_orderform_search);
    }

    private void initView() {
        mListPop = new ListPopupWindow(this);
        acct_order_status.setText("全部");
        acct_order_status.setInputType(InputType.TYPE_NULL);

        acct_order_amount.setText("自定义");
        acct_order_amount.setInputType(InputType.TYPE_NULL);

        acct_start_date.setInputType(InputType.TYPE_NULL);
        acct_end_date.setInputType(InputType.TYPE_NULL);

        // 设置外销发票号搜索的历史记录数据
        SharedPreferences searchHistorySp = MyApplication_.getInstance().getSearchHistorySp();
        Set<String> saleInvoiceHistory = searchHistorySp.getStringSet(AppDelegate.HISTORY_SALE_INVOICE_SEARCH, null);
        if (saleInvoiceHistory != null) {
            ArrayList<String> arrayList = new ArrayList<>(saleInvoiceHistory);
            acct_sale_invoice.setAdapter(new ListpopAdapter(this, R.layout.cb_item_listpop_with_delete, R.id.tv_text, arrayList,
                    searchHistorySp.edit(), AppDelegate.HISTORY_SALE_INVOICE_SEARCH));
        }

        acct_sale_invoice.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DeviceUtil.hideSoft(getApplicationContext(), acct_sale_invoice);
            }
        });

        acct_order_status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListPop.setAdapter(new ArrayAdapter<>(T_SearchOrderFormActivity.this, R.layout.cb_item_listpop_search, listOrderStatus));
                mListPop.setAnchorView(acct_order_status);
                mListPop.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        acct_order_status.setText(listOrderStatus[position]);
                        mListPop.dismiss();
                    }
                });
                mListPop.show();
            }
        });

        acct_order_amount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListPop.setAdapter(new ArrayAdapter<>(T_SearchOrderFormActivity.this, R.layout.cb_item_listpop_search, listOrderAmount));
                mListPop.setAnchorView(acct_order_amount);
                mListPop.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        acct_order_amount.setText(listOrderAmount[position]);

                        switch (position) {
                            case 0: // "自定义"
                                acct_min_amount.setText("");
                                acct_max_amount.setText("");
                                acct_min_amount.setEnabled(true);
                                acct_max_amount.setEnabled(true);
                                break;
                            case 1: // 3000-5000万
                                acct_min_amount.setText("30,000,000.00");
                                acct_max_amount.setText("50,000,000.00");
                                acct_min_amount.setEnabled(false);
                                acct_max_amount.setEnabled(false);
                                break;
                            case 2: //"5000万-1亿"
                                acct_min_amount.setText("50,000,000.00");
                                acct_max_amount.setText("100,000,000.00");
                                acct_min_amount.setEnabled(false);
                                acct_max_amount.setEnabled(false);
                                break;
                            case 3: // "1亿-2亿"
                                acct_min_amount.setText("100,000,000.00");
                                acct_max_amount.setText("200,000,000.00");
                                acct_min_amount.setEnabled(false);
                                acct_max_amount.setEnabled(false);
                                break;
                            case 4: // "2亿以上"
                                acct_min_amount.setText("200,000,000.00");
                                acct_max_amount.setText("");
                                acct_min_amount.setEnabled(false);
                                acct_max_amount.setEnabled(false);
                                break;
                        }

                        mListPop.dismiss();
                    }
                });
                mListPop.show();
            }
        });

        acct_start_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickTag = AppDelegate.CLICK_START_DATE;
                createSublimePicker();
            }
        });

        acct_end_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickTag = AppDelegate.CLICK_END_DATE;
                createSublimePicker();
            }
        });

    }

    private void createSublimePicker() {
        SublimePickerFragment pickerFrag = new SublimePickerFragment();
        pickerFrag.setCallback(mFragmentCallback);

        SublimeOptions options = new SublimeOptions();
        options.setDisplayOptions(SublimeOptions.ACTIVATE_DATE_PICKER); // 设置显示什么选择器（日期，时间）
        options.setPickerToShow(SublimeOptions.Picker.DATE_PICKER); // 设置显示什么选择器（日期，时间）
        options.setCanPickDateRange(true); // 设置长按拖动进行日期范围选择

        Bundle bundle = new Bundle();
        bundle.putParcelable("SUBLIME_OPTIONS", options);
        pickerFrag.setArguments(bundle);

        pickerFrag.setStyle(AppCompatDialogFragment.STYLE_NO_TITLE, 0);
        pickerFrag.show(getSupportFragmentManager(), "SUBLIME_PICKER");

    }

    SublimePickerFragment.Callback mFragmentCallback = new SublimePickerFragment.Callback() {
        // 取消
        @Override
        public void onCancelled() {
        }

        // 确定
        @Override
        public void onDateTimeRecurrenceSet(SelectedDate selectedDate, int hourOfDay, int minute,
                                            SublimeRecurrencePicker.RecurrenceOption recurrenceOption, String recurrenceRule) {

            if (selectedDate != null) {
                String startDate = DateUtil.dateToStr(selectedDate.getStartDate().getTime());
                if (clickTag == AppDelegate.CLICK_START_DATE && selectedDate.getType() == SelectedDate.Type.SINGLE) {
                    acct_start_date.setText(startDate);
                } else if (clickTag == AppDelegate.CLICK_END_DATE && selectedDate.getType() == SelectedDate.Type.SINGLE) {
                    acct_end_date.setText(startDate);
                } else if (selectedDate.getType() == SelectedDate.Type.RANGE) {
                    acct_start_date.setText(startDate);
                    acct_end_date.setText(DateUtil.dateToStr(selectedDate.getEndDate().getTime()));
                }
            }

        }
    };

    // 搜索
    @Click(R.id.btn_search)
    void search() {
        // 订单状态
        String status = acct_order_status.getText().toString().trim();
        switch (status) {
            case "全部":
                status = "";
                break;
            case "草稿":
                status = "0";
                break;
            case "受理中":
                status = "1";
                break;
            case "进行中":
                status = "2";
                break;
            case "已完成":
                status = "4";
                break;
            case "已拒绝":
                status = "-2";
                break;
            case "已取消":
                status = "-1";
                break;
        }
        // 外销发票号
        String saleOrder = acct_sale_invoice.getText().toString().trim();
        // 开始时间
        String submitStartDate = acct_start_date.getText().toString().trim();
        // 结束时间
        String submitEndDate = acct_end_date.getText().toString().trim();
        // 订单金额
        String amountIndex = acct_order_amount.getText().toString().trim();
        String minAmount = "";
        String maxAmount = "";
        switch (amountIndex) {
            case "自定义":
                minAmount = acct_min_amount.getText().toString().trim();
                maxAmount = acct_max_amount.getText().toString().trim();
                break;
            case "3000-5000万":
                minAmount = "30000000";
                maxAmount = "50000000";
                break;
            case "5000万-1亿":
                minAmount = "50000000";
                maxAmount = "100000000";
                break;
            case "1亿-2亿":
                minAmount = "100000000";
                maxAmount = "200000000";
                break;
            case "2亿以上":
                minAmount = "200000000";
                maxAmount = "";
                break;
        }

        Intent intent = new Intent(AppDelegate.ACTION_T_ORDER_FORM_SEARCH);

        // 发送广播OrderFormFragment，将搜索条件传递过去，然后请求数据
        if ("".equals(status)) { // 如果状态是全部那么就不要 status 参数
            String ACTION_ORDER_FORM_SEARCH_DATE = "&saleOrder=" + saleOrder
                    + "&submitStartDate=" + submitStartDate + "&submitEndDate=" + submitEndDate
                    + "&minAmount=" + minAmount + "&maxAmount=" + maxAmount;
            intent.putExtra(AppDelegate.ACTION_T_ORDER_FORM_SEARCH, ACTION_ORDER_FORM_SEARCH_DATE);
        } else {
            String ACTION_ORDER_FORM_SEARCH_DATE = "&saleOrder=" + saleOrder + "&status=" + status
                    + "&submitStartDate=" + submitStartDate + "&submitEndDate=" + submitEndDate
                    + "&minAmount=" + minAmount + "&maxAmount=" + maxAmount;
            intent.putExtra(AppDelegate.ACTION_T_ORDER_FORM_SEARCH, ACTION_ORDER_FORM_SEARCH_DATE);
        }

        intent.putExtra(AppDelegate.KEYWORD, saleOrder);

        LocalBroadcastManager.getInstance(T_SearchOrderFormActivity.this).sendBroadcast(intent);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_reset, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.action_reset:
                resetData();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 重置所有数据
     */
    private void resetData() {
        acct_order_status.setText("全部");
        acct_order_amount.setText("自定义");
        acct_sale_invoice.setText("");
        acct_start_date.setText("");
        acct_end_date.setText("");
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
