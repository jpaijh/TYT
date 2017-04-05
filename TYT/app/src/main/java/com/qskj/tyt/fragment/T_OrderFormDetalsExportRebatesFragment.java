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
 * 订单详情页 - 出口退税服务
 */
@EFragment(R.layout.t_fragment_order_form_export_rebates_details)
public class T_OrderFormDetalsExportRebatesFragment extends BaseFragment {

    @ViewById(R.id.nestedScrollView)
    NestedScrollView mNestedScrollView;

    @ViewById(R.id.progressbar)
    CircleProgressBar mProgressBar;

    @ViewById(R.id.tv_orderExport_tsIsPaymentCost)
    AppCompatTextView orderExport_tsIsPaymentCost;

    @ViewById(R.id.tv_orderExport_actualCustomsClearanceDate)
    AppCompatTextView orderExport_actualCustomsClearanceDate;

    @ViewById(R.id.tv_orderExport_tsMustActualAmount)
    AppCompatTextView orderExport_tsMustActualAmount;

    @ViewById(R.id.tv_orderExport_tsFormalitiesCharges)
    AppCompatTextView orderExport_tsFormalitiesCharges;

    @ViewById(R.id.tv_orderExport_tsCustomRebateAmount)
    AppCompatTextView orderExport_tsCustomRebateAmount;

    @ViewById(R.id.tv_orderExport_tsRebateDeadline)
    AppCompatTextView orderExport_tsRebateDeadline;

    @ViewById(R.id.tv_orderExport_tsPaymentAmount)
    AppCompatTextView orderExport_tsPaymentAmount;

    @ViewById(R.id.tv_orderExport_tsActualDate)
    AppCompatTextView orderExport_tsActualDate;

    @ViewById(R.id.tv_orderExport_tsActualAmount)
    AppCompatTextView orderExport_tsActualAmount;

    @ViewById(R.id.time_line_recycler)
    RecyclerView time_line_recycler;

    @ViewById(R.id.attachments_recycler)
    RecyclerView attachments_recycler;

    @ViewById(R.id.tv_no_attachments)
    AppCompatTextView tv_no_attachments;

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

        orderExport_tsIsPaymentCost.setText(arguments.getString("orderExport_tsIsPaymentCost"));
        orderExport_actualCustomsClearanceDate.setText(arguments.getString("orderExport_actualCustomsClearanceDate"));
        orderExport_tsMustActualAmount.setText(arguments.getString("orderExport_tsMustActualAmount"));
        orderExport_tsFormalitiesCharges.setText(arguments.getString("orderExport_tsFormalitiesCharges"));
        orderExport_tsCustomRebateAmount.setText(arguments.getString("orderExport_tsCustomRebateAmount"));
        orderExport_tsRebateDeadline.setText(arguments.getString("orderExport_tsRebateDeadline"));
        orderExport_tsPaymentAmount.setText(arguments.getString("orderExport_tsPaymentAmount"));
        orderExport_tsActualDate.setText(arguments.getString("orderExport_tsActualDate"));
        orderExport_tsActualAmount.setText(arguments.getString("orderExport_tsActualAmount"));

        // 附件
        JSONArray attachments = JSON.parseArray(arguments.getString("attachments"));
        final ArrayList<T_OrderFormDetailsAttachmentsEntity> orderFormDetailsAttachmentsEntityList = new ArrayList<>();
        if (attachments.size() == 0) {
            attachments_recycler.setVisibility(View.GONE);
            tv_no_attachments.setVisibility(View.VISIBLE);
        } else {
            for (int i = 0; i < attachments.size(); i++) {
                JSONObject jsonObject = attachments.getJSONObject(i);

                String fileName = jsonObject.getString("fileName");
                if (fileName != null && !TextUtils.isEmpty(fileName)) {
                    T_OrderFormDetailsAttachmentsEntity orderFormDetailsAttachmentsEntity = new T_OrderFormDetailsAttachmentsEntity();
                    orderFormDetailsAttachmentsEntity.setFileName(fileName);
                    orderFormDetailsAttachmentsEntity.setAttachmentMetaDataId(jsonObject.getString("attachmentMetaDataId"));
                    orderFormDetailsAttachmentsEntity.setAttachmentName(jsonObject.getString("attachmentName"));
                    orderFormDetailsAttachmentsEntity.setCreateDate(jsonObject.getString("createDate"));
                    orderFormDetailsAttachmentsEntity.setCreator(jsonObject.getString("creator"));
                    orderFormDetailsAttachmentsEntity.setFileId(jsonObject.getString("fileId"));
                    orderFormDetailsAttachmentsEntity.setFilePath(jsonObject.getString("filePath"));
                    orderFormDetailsAttachmentsEntity.setId(jsonObject.getString("id"));
                    orderFormDetailsAttachmentsEntity.setIsUpload(jsonObject.getString("isUpload"));
                    orderFormDetailsAttachmentsEntity.setNeeded(jsonObject.getString("needed"));
                    orderFormDetailsAttachmentsEntity.setOrderId(jsonObject.getString("orderId"));
                    orderFormDetailsAttachmentsEntityList.add(orderFormDetailsAttachmentsEntity);
                }
            }

            if (orderFormDetailsAttachmentsEntityList.size() == 0) {
                attachments_recycler.setVisibility(View.GONE);
                tv_no_attachments.setVisibility(View.VISIBLE);
            } else {
                attachments_recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
                T_OrderFormDetailsAttachmentsAdapter orderFormDetailsAttachmentsAdapter =
                        new T_OrderFormDetailsAttachmentsAdapter(orderFormDetailsAttachmentsEntityList);
                attachments_recycler.setAdapter(orderFormDetailsAttachmentsAdapter);
            }

        }

        mProgressBar.setVisibility(View.GONE);
        mNestedScrollView.setVisibility(View.VISIBLE);
    }

}
