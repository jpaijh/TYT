package com.qskj.tyt.fragment;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatTextView;
import android.text.ClipboardManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;

import com.qskj.tyt.AppDelegate;
import com.qskj.tyt.R;
import com.qskj.tyt.activity.CB_BaiduMapActivity_;
import com.qskj.tyt.activity.CB_WebActivity_;
import com.qskj.tyt.entity.CB_ManageDetailsEntity;
import com.qskj.tyt.utils.ToastUtil;
import com.umeng.analytics.MobclickAgent;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;


/**
 * 经营单位-仓单详情-仓单信息
 * Created by 赵 鑫 on 2015/12/3.
 */
@EFragment(R.layout.cb_fragment_manage_details_warehousing_info)
public class CB_ManageDetailsFragment_WarehouseInfo extends BaseFragment {

    private static final String TAG = "CB_ManageDetailsFragment_WarehouseInfo";

    private String businessEnterpriseContactTelephone;

    @ViewById(R.id.tv_warehouseRemark)
    AppCompatTextView tv_warehouseRemark;

    @ViewById(R.id.tv_warehouseAddress)
    AppCompatTextView tv_warehouseAddress;

    @ViewById(R.id.tv_orderDelivery_businessEnterpriseCode)
    AppCompatTextView tv_orderDelivery_businessEnterpriseCode;

    @ViewById(R.id.tv_orderDelivery_businessEnterpriseName)
    AppCompatTextView tv_orderDelivery_businessEnterpriseName;

    @ViewById(R.id.tv_orderDelivery_businessEnterpriseContactTelephone)
    AppCompatTextView tv_orderDelivery_businessEnterpriseContactTelephone;

    @ViewById(R.id.tv_orderDelivery_businessEnterpriseAddress)
    AppCompatTextView tv_orderDelivery_businessEnterpriseAddress;

    @ViewById(R.id.tv_check_map)
    AppCompatTextView tv_check_map;

    @ViewById(R.id.ll_check_map)
    LinearLayout ll_check_map;

    @Override
    public void onAfterViews() {
        Bundle arguments = getArguments();
        CB_ManageDetailsEntity entity = (CB_ManageDetailsEntity) arguments.getSerializable(AppDelegate.MANAGE_DETAILS_ENTITY);
        CB_ManageDetailsEntity.OrderDeliveryEntity orderDelivery = entity.getOrderDelivery();

        tv_warehouseRemark.setText(orderDelivery.getWarehouseRemark());

        String warehouseAddress = orderDelivery.getWarehouseAddress();
        if (TextUtils.isEmpty(warehouseAddress)) {
            ll_check_map.setVisibility(View.GONE);
        } else {
            ll_check_map.setVisibility(View.VISIBLE);
        }
        tv_warehouseAddress.setText(warehouseAddress);

        tv_orderDelivery_businessEnterpriseCode.setText(orderDelivery.getBusinessEnterpriseCode());
        tv_orderDelivery_businessEnterpriseName.setText(orderDelivery.getBusinessEnterpriseName());
        businessEnterpriseContactTelephone = orderDelivery.getBusinessEnterpriseContactTelephone();
        tv_orderDelivery_businessEnterpriseContactTelephone.setText(businessEnterpriseContactTelephone);
        tv_orderDelivery_businessEnterpriseAddress.setText(orderDelivery.getBusinessEnterpriseAddress());
    }

    /**
     * 操作电话号码:委托人
     */
    @Click(R.id.tv_orderDelivery_businessEnterpriseContactTelephone)
    void createWeituoren() {
        if (!TextUtils.isEmpty(businessEnterpriseContactTelephone)) {
            callNumber(businessEnterpriseContactTelephone);
        }
    }

    @Click(R.id.tv_phonenumber1)
    void callNumber1() {
        callNumber("0574-26896830");
    }

    @Click(R.id.tv_phonenumber2)
    void callNumber2() {
        callNumber("0574-26896930");
    }

    @Click(R.id.tv_phonenumber3)
    void callNumber3() {
        callNumber("0574-26896931");
    }

    @Click(R.id.tv_phonenumber4)
    void callNumber4() {
        callNumber("0574-26896932");
    }

    /**
     * 拨打电话：传入电话号码就好
     *
     * @param number
     */
    public void callNumber(final String number) {
        String[] strings = {"拨打", "新建联系人", "复制号码"};
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.MyAlertDialogStyle);
        builder.setTitle(number)
                .setItems(strings, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0: // 直接拨打电话
                                Intent intent_call = new Intent(Intent.ACTION_CALL);
                                intent_call.setData(Uri.parse("tel:" + number));
                                if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                                    //    ActivityCompat#requestPermissions
                                    // here to request the missing permissions, and then overriding
                                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                    //                                          int[] grantResults)
                                    // to handle the case where the user grants the permission. See the documentation
                                    // for ActivityCompat#requestPermissions for more details.
                                    return;
                                }
                                startActivity(intent_call);
                                break;
                            case 1: // 新建联系人
                                Intent intent_create_new_contact = new Intent(Intent.ACTION_INSERT);
                                intent_create_new_contact.setType("vnd.android.cursor.dir/person");
                                intent_create_new_contact.setType("vnd.android.cursor.dir/contact");
                                intent_create_new_contact.setType("vnd.android.cursor.dir/raw_contact");
                                intent_create_new_contact.putExtra(ContactsContract.Intents.Insert.PHONE, number);
                                // intent1.putExtra(ContactsContract.Intents.Insert.NAME, name);
                                startActivity(intent_create_new_contact);
                                break;
                            case 2: // 复制号码
                                ClipboardManager clip = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
                                clip.setText(number);
                                ToastUtil.showToast(getActivity(), "复制成功");
                                break;
                        }
                    }
                }).setNegativeButton("关闭", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).create().show();

    }

    /**
     * 查看地图
     */
    @Click(R.id.tv_check_map)
    void checkMap() {
        CB_BaiduMapActivity_.intent(this).start();
    }

    /**
     * 打开查看货物的网址
     */
    @Click(R.id.ll_check_goods)
    void checkGoods() {
        CB_WebActivity_.intent(this).extra(AppDelegate.URL, "http://www.nbskylark.com/csccmis/csccmis.asp").extra(AppDelegate.TOOLBAR_TITLE, "查货").start();
    }

    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart(TAG);
    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd(TAG);
    }

}
