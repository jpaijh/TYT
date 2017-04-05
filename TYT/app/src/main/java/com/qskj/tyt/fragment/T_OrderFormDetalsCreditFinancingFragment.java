package com.qskj.tyt.fragment;

import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lsjwzh.widget.materialloadingprogressbar.CircleProgressBar;
import com.qskj.tyt.R;
import com.qskj.tyt.adapter.T_OrderFormDetailsAttachmentsAdapter;
import com.qskj.tyt.entity.T_OrderFormDetailsAttachmentsEntity;
import com.qskj.tyt.view.TimeLine.TimeLineAdapter;
import com.qskj.tyt.view.TimeLine.TimeLineEntity;


import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

/**
 * 订单详情页 - 赊销融资
 */
@EFragment(R.layout.t_fragment_order_form_credit_financing_details)
public class T_OrderFormDetalsCreditFinancingFragment extends BaseFragment {

    @ViewById(R.id.nestedScrollView)
    NestedScrollView mNestedScrollView;

    @ViewById(R.id.progressbar)
    CircleProgressBar mProgressBar;

    @ViewById(R.id.tv_order_accountName)
    AppCompatTextView order_accountName;

    @ViewById(R.id.tv_order_accountCode)
    AppCompatTextView order_accountCode;

    @ViewById(R.id.tv_orderExport_sxCustomCreditLimit)
    AppCompatTextView orderExport_sxCustomCreditLimit;

    @ViewById(R.id.tv_orderExport_currency1)
    AppCompatTextView orderExport_currency1;

    @ViewById(R.id.tv_orderExport_amount1)
    AppCompatTextView orderExport_amount1;

    @ViewById(R.id.tv_order_accountName1)
    AppCompatTextView order_accountName1;

    @ViewById(R.id.tv_order_accountCode1)
    AppCompatTextView order_accountCode1;

    @ViewById(R.id.tv_orderExport_sxCustomCreditLimit1)
    AppCompatTextView orderExport_sxCustomCreditLimit1;

    @ViewById(R.id.tv_orderExport_sxPaymentType)
    AppCompatTextView orderExport_sxPaymentType;

    @ViewById(R.id.tv_orderExport_sxSinosureCurrency)
    AppCompatTextView orderExport_sxSinosureCurrency;

    @ViewById(R.id.tv_orderExport_sxSinosureAmount)
    AppCompatTextView orderExport_sxSinosureAmount;

    @ViewById(R.id.tv_orderExport_sxSinosureRate)
    AppCompatTextView orderExport_sxSinosureRate;

    @ViewById(R.id.tv_orderExport_sxShipmentDate)
    AppCompatTextView orderExport_sxShipmentDate;

    @ViewById(R.id.tv_orderExport_sxFinacingDays)
    AppCompatTextView orderExport_sxFinacingDays;

    @ViewById(R.id.tv_orderExport_sxIsCustomsConfirm)
    AppCompatTextView orderExport_sxIsCustomsConfirm;

    @ViewById(R.id.tv_orderExport_sxFinancionCreditLimit)
    AppCompatTextView orderExport_sxFinancionCreditLimit;

    @ViewById(R.id.tv_financeLoanAmount)
    AppCompatTextView financeLoanAmount;

    @ViewById(R.id.tv_orderExport_sxReservationRate)
    AppCompatTextView orderExport_sxReservationRate;

    @ViewById(R.id.tv_orderExport_sxFinancingProportion)
    AppCompatTextView orderExport_sxFinancingProportion;

    @ViewById(R.id.tv_orderExport_sxOperatorConfirmFlag)
    AppCompatTextView orderExport_sxOperatorConfirmFlag;

    @ViewById(R.id.tv_orderExport_sxReservationInterest)
    AppCompatTextView orderExport_sxReservationInterest;

    @ViewById(R.id.tv_orderExport_sxSinosureCostRate)
    AppCompatTextView orderExport_sxSinosureCostRate;

    @ViewById(R.id.tv_orderExport_sxSinosureTtCost)
    AppCompatTextView orderExport_sxSinosureTtCost;

    @ViewById(R.id.tv_orderExport_sxSinosureCost)
    AppCompatTextView orderExport_sxSinosureCost;

    @ViewById(R.id.tv_orderExport_sxPaymentDate)
    AppCompatTextView orderExport_sxPaymentDate;

    @ViewById(R.id.tv_orderExport_sxEndDate)
    AppCompatTextView orderExport_sxEndDate;

    @ViewById(R.id.tv_orderExport_sxActualFkDate)
    AppCompatTextView orderExport_sxActualFkDate;

    @ViewById(R.id.tv_orderExport_sxShipmentDate1)
    AppCompatTextView orderExport_sxShipmentDate1;

    @ViewById(R.id.tv_orderExport_sxFinancionCreditLimit1)
    AppCompatTextView orderExport_sxFinancionCreditLimit1;

    @ViewById(R.id.tv_orderExport_sxActualInterest)
    AppCompatTextView orderExport_sxActualInterest;

    @ViewById(R.id.tv_orderExport_sxActualPaymentAmount)
    AppCompatTextView orderExport_sxActualPaymentAmount;

    @ViewById(R.id.tv_orderExport_sxSinosureTtCost1)
    AppCompatTextView orderExport_sxSinosureTtCost1;

    @ViewById(R.id.tv_orderExport_sxUnPaymentAmount)
    AppCompatTextView orderExport_sxUnPaymentAmount;

    @ViewById(R.id.tv_orderExport_sxDifferenceAmount)
    AppCompatTextView orderExport_sxDifferenceAmount;

//    <!-- TODO 还款信息还有这4个字段不知道怎么获取 -->
//    <!--<string name="orderExport_sxEndDate">还款日期</string>-->
//    <!--<string name="orderExport_sxEndDate">金额</string>-->
//    <!--<string name="orderExport_sxEndDate">实际账期(天)</string>-->
//    <!--<string name="orderExport_sxEndDate">备注</string>-->

    //    <!--收款方信息-->
//    <!-- TODO 不知道怎么获取 -->
//    <!--货款使用人 	收款银行 	收款账号 	融资金额-->

    @ViewById(R.id.time_line_recycler)
    RecyclerView time_line_recycler; // 时间线RecyclerView

    @ViewById(R.id.attachments_recycler)
    RecyclerView attachments_recycler; // 附件 RecyclerView

    @ViewById(R.id.tv_no_attachments)
    AppCompatTextView tv_no_attachments; // 暂无附件

    @Override
    public void onAfterViews() {
        mProgressBar.setVisibility(View.VISIBLE);
        mNestedScrollView.setVisibility(View.GONE);

        Bundle arguments = getArguments();

        // 进度信息
        List<TimeLineEntity> timeLineEntityList = new ArrayList<>();
        JSONArray nodes = JSON.parseArray(arguments.getString("nodes"));
        for (int i = 0; i < nodes.size(); i++) {
            String name = nodes.getJSONObject(i).getString("name");
            int nodeStatus = nodes.getJSONObject(i).getIntValue("nodeStatus");
            timeLineEntityList.add(new TimeLineEntity(name, nodeStatus, getActivity()));
        }
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        TimeLineAdapter adapter = new TimeLineAdapter(timeLineEntityList);
        time_line_recycler.setLayoutManager(layoutManager);
        time_line_recycler.setAdapter(adapter);

        order_accountName.setText(arguments.getString("order_accountName"));
        order_accountCode.setText(arguments.getString("order_accountCode"));
        orderExport_sxCustomCreditLimit.setText(arguments.getString("orderExport_sxCustomCreditLimit"));
        orderExport_currency1.setText(arguments.getString("orderExport_currency1"));
        orderExport_amount1.setText(arguments.getString("orderExport_amount1"));

        order_accountName1.setText(arguments.getString("order_accountName1"));
        order_accountCode1.setText(arguments.getString("order_accountCode1"));
        orderExport_sxCustomCreditLimit1.setText(arguments.getString("orderExport_sxCustomCreditLimit1"));
        orderExport_sxPaymentType.setText(arguments.getString("orderExport_sxPaymentType"));
        orderExport_sxSinosureCurrency.setText(arguments.getString("orderExport_sxSinosureCurrency"));
        orderExport_sxSinosureAmount.setText(arguments.getString("orderExport_sxSinosureAmount"));
        orderExport_sxSinosureRate.setText(arguments.getString("orderExport_sxSinosureRate"));
        orderExport_sxShipmentDate.setText(arguments.getString("orderExport_sxShipmentDate"));
        orderExport_sxFinacingDays.setText(arguments.getString("orderExport_sxFinacingDays"));
        orderExport_sxIsCustomsConfirm.setText(arguments.getString("orderExport_sxIsCustomsConfirm"));

//        <!--收款方信息-->
//        <!-- TODO 不知道怎么获取 -->
//        <!--货款使用人 	收款银行 	收款账号 	融资金额-->
//        <!--<string name="orderExport_sxIsCustomsConfirm">货款使用人</string>-->
//        <!--<string name="orderExport_sxIsCustomsConfirm">收款银行</string>-->
//        <!--<string name="orderExport_sxIsCustomsConfirm">收款账号</string>-->
//        <!--<string name="orderExport_sxIsCustomsConfirm">融资金额</string>-->

        orderExport_sxFinancionCreditLimit.setText(arguments.getString("orderExport_sxFinancionCreditLimit"));
        financeLoanAmount.setText(arguments.getString("financeLoanAmount"));
        orderExport_sxReservationRate.setText(arguments.getString("orderExport_sxReservationRate"));
        orderExport_sxFinancingProportion.setText(arguments.getString("orderExport_sxFinancingProportion"));
        orderExport_sxOperatorConfirmFlag.setText(arguments.getString("orderExport_sxOperatorConfirmFlag"));
        orderExport_sxReservationInterest.setText(arguments.getString("orderExport_sxReservationInterest"));
        orderExport_sxSinosureCostRate.setText(arguments.getString("orderExport_sxSinosureCostRate"));
        orderExport_sxSinosureTtCost.setText(arguments.getString("orderExport_sxSinosureTtCost"));
        orderExport_sxSinosureCost.setText(arguments.getString("orderExport_sxSinosureCost"));
        orderExport_sxPaymentDate.setText(arguments.getString("orderExport_sxPaymentDate"));
        orderExport_sxEndDate.setText(arguments.getString("orderExport_sxEndDate"));

        orderExport_sxActualFkDate.setText(arguments.getString("orderExport_sxActualFkDate"));
        orderExport_sxShipmentDate1.setText(arguments.getString("orderExport_sxShipmentDate1"));
        orderExport_sxFinancionCreditLimit1.setText(arguments.getString("orderExport_sxFinancionCreditLimit1"));
        orderExport_sxActualInterest.setText(arguments.getString("orderExport_sxActualInterest"));
        orderExport_sxActualPaymentAmount.setText(arguments.getString("orderExport_sxActualPaymentAmount"));
        orderExport_sxSinosureTtCost1.setText(arguments.getString("orderExport_sxSinosureTtCost1"));
        orderExport_sxUnPaymentAmount.setText(arguments.getString("orderExport_sxUnPaymentAmount"));
        orderExport_sxDifferenceAmount.setText(arguments.getString("orderExport_sxDifferenceAmount"));

//        <!-- TODO 还款信息还有这4个字段不知道怎么获取 -->
//        <!--<string name="orderExport_sxEndDate">还款日期</string>-->
//        <!--<string name="orderExport_sxEndDate">金额</string>-->
//        <!--<string name="orderExport_sxEndDate">实际账期(天)</string>-->
//        <!--<string name="orderExport_sxEndDate">备注</string>-->

        // 附件
        JSONArray attachments = JSON.parseArray(arguments.getString("attachments"));
        final ArrayList<T_OrderFormDetailsAttachmentsEntity> T_OrderFormDetailsAttachmentsEntityList = new ArrayList<>();
        if (attachments.size() == 0) {
            attachments_recycler.setVisibility(View.GONE);
            tv_no_attachments.setVisibility(View.VISIBLE);
        } else {
            for (int i = 0; i < attachments.size(); i++) {
                JSONObject jsonObject = attachments.getJSONObject(i);

                String fileName = jsonObject.getString("fileName");
                if (fileName != null && !TextUtils.isEmpty(fileName)) {
                    T_OrderFormDetailsAttachmentsEntity T_OrderFormDetailsAttachmentsEntity = new T_OrderFormDetailsAttachmentsEntity();
                    T_OrderFormDetailsAttachmentsEntity.setFileName(fileName);
                    T_OrderFormDetailsAttachmentsEntity.setAttachmentMetaDataId(jsonObject.getString("attachmentMetaDataId"));
                    T_OrderFormDetailsAttachmentsEntity.setAttachmentName(jsonObject.getString("attachmentName"));
                    T_OrderFormDetailsAttachmentsEntity.setCreateDate(jsonObject.getString("createDate"));
                    T_OrderFormDetailsAttachmentsEntity.setCreator(jsonObject.getString("creator"));
                    T_OrderFormDetailsAttachmentsEntity.setFileId(jsonObject.getString("fileId"));
                    T_OrderFormDetailsAttachmentsEntity.setFilePath(jsonObject.getString("filePath"));
                    T_OrderFormDetailsAttachmentsEntity.setId(jsonObject.getString("id"));
                    T_OrderFormDetailsAttachmentsEntity.setIsUpload(jsonObject.getString("isUpload"));
                    T_OrderFormDetailsAttachmentsEntity.setNeeded(jsonObject.getString("needed"));
                    T_OrderFormDetailsAttachmentsEntity.setOrderId(jsonObject.getString("orderId"));
                    T_OrderFormDetailsAttachmentsEntityList.add(T_OrderFormDetailsAttachmentsEntity);
                }
            }

            if (T_OrderFormDetailsAttachmentsEntityList.size() == 0) {
                attachments_recycler.setVisibility(View.GONE);
                tv_no_attachments.setVisibility(View.VISIBLE);
            } else {
                attachments_recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
                T_OrderFormDetailsAttachmentsAdapter orderFormDetailsAttachmentsAdapter =
                        new T_OrderFormDetailsAttachmentsAdapter(T_OrderFormDetailsAttachmentsEntityList);
                attachments_recycler.setAdapter(orderFormDetailsAttachmentsAdapter);
            }

        }

        mProgressBar.setVisibility(View.GONE);
        mNestedScrollView.setVisibility(View.VISIBLE);
    }

}
