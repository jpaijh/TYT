package com.qskj.tyt.fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

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
 * 订单详情页 - 一般贸易出口通关
 */
@EFragment(R.layout.t_fragment_order_form_general_trade_details)
public class T_OrderFormDetailsGeneralTradeFragment extends BaseFragment {

    @ViewById(R.id.nestedScrollView)
    NestedScrollView mNestedScrollView;

    @ViewById(R.id.progressbar)
    CircleProgressBar mProgressBar; // MaterialDesign风格

    @ViewById(R.id.ll_forwardingContainer)
    LinearLayout ll_forwardingContainery;

    @ViewById(R.id.tv_no_forwardingContainer_information)
    AppCompatTextView tv_no_forwardingContainer_information; // 暂无货柜信息

    @ViewById(R.id.btn_check_forwardingContainer_information)
    AppCompatButton btn_check_forwardingContainer_information; // 查看全部货柜信息

    @ViewById(R.id.tv_orderExport_invoicingCompanyName)
    AppCompatTextView orderExport_invoicingCompanyName;

    @ViewById(R.id.tv_consignor)
    AppCompatTextView consignor;

    @ViewById(R.id.tv_consignee)
    AppCompatTextView consignee;

    @ViewById(R.id.tv_orderExport_foreignName)
    AppCompatTextView orderExport_foreignName;

    @ViewById(R.id.tv_notifier)
    AppCompatTextView notifier;

    @ViewById(R.id.tv_orderExport_ckCustomsClearanceNo)
    AppCompatTextView orderExport_ckCustomsClearanceNo;

    @ViewById(R.id.tv_orderExport_salesOrder)
    AppCompatTextView orderExport_salesOrder;

    @ViewById(R.id.tv_orderExport_finalDestCountry)
    AppCompatTextView orderExport_finalDestCountry;

    @ViewById(R.id.tv_orderExport_priceTerm)
    AppCompatTextView orderExport_priceTerm;

    @ViewById(R.id.tv_orderExport_currency)
    AppCompatTextView orderExport_currency;

    @ViewById(R.id.tv_orderExport_amount)
    AppCompatTextView orderExport_amount;

    @ViewById(R.id.tv_orderExport_payment1Desc)
    AppCompatTextView orderExport_payment1Desc;

    @ViewById(R.id.tv_orderExport_payment2Desc)
    AppCompatTextView orderExport_payment2Desc;

    @ViewById(R.id.tv_orderExport_modeOfTrade)
    AppCompatTextView orderExport_modeOfTrade;

    @ViewById(R.id.tv_orderExport_source)
    AppCompatTextView orderExport_source;

    @ViewById(R.id.tv_orderExport_completeDeliveryDate)
    AppCompatTextView orderExport_completeDeliveryDate;

    @ViewById(R.id.tv_orderExport_invoiceAmount)
    AppCompatTextView orderExport_invoiceAmount;

    @ViewById(R.id.tv_order_remark)
    AppCompatTextView order_remark;

    @ViewById(R.id.tv_orderExport_modeOfTransportation)
    AppCompatTextView orderExport_modeOfTransportation;

    @ViewById(R.id.tv_orderExport_exportPortEn)
    AppCompatTextView orderExport_exportPortEn;

    @ViewById(R.id.tv_orderExport_destinationPortEn)
    AppCompatTextView orderExport_destinationPortEn;

    @ViewById(R.id.tv_orderExport_transshippmentPortEn)
    AppCompatTextView orderExport_transshippmentPortEn;

    @ViewById(R.id.tv_orderExport_shippmentDate)
    AppCompatTextView orderExport_shippmentDate;

    @ViewById(R.id.tv_orderExport_shippingSchedule)
    AppCompatTextView orderExport_shippingSchedule;

    @ViewById(R.id.tv_orderExport_closingDate)
    AppCompatTextView orderExport_closingDate;

    @ViewById(R.id.tv_orderExport_grossWeight)
    AppCompatTextView orderExport_grossWeight;

    @ViewById(R.id.tv_orderExport_netWeight)
    AppCompatTextView orderExport_netWeight;

    @ViewById(R.id.tv_orderExport_vol)
    AppCompatTextView orderExport_vol;

    @ViewById(R.id.tv_orderExport_frontMark)
    AppCompatTextView orderExport_frontMark;

    @ViewById(R.id.tv_orderExport_sideMark)
    AppCompatTextView orderExport_sideMark;

    @ViewById(R.id.tv_orderExport_forwarder)
    AppCompatTextView orderExport_forwarder;

    @ViewById(R.id.tv_orderExport_forwardingContactor)
    AppCompatTextView orderExport_forwardingContactor;

    @ViewById(R.id.tv_orderExport_forwardingPhone)
    AppCompatTextView orderExport_forwardingPhone;

    // 货柜信息
    @ViewById(R.id.tv_container_typeName)
    AppCompatTextView container_typeName;

    @ViewById(R.id.tv_container_count)
    AppCompatTextView container_count;

    @ViewById(R.id.tv_container_remark)
    AppCompatTextView container_remark;

    @ViewById(R.id.tv_container_sealNo)
    AppCompatTextView container_sealNo;

    @ViewById(R.id.tv_container_carNo)
    AppCompatTextView container_carNo;

    @ViewById(R.id.tv_container_whiteCardNo)
    AppCompatTextView container_whiteCardNo;

    @ViewById(R.id.tv_container_driverName)
    AppCompatTextView container_driverName;

    @ViewById(R.id.tv_container_driverPhone)
    AppCompatTextView container_driverPhone;

    @ViewById(R.id.ll_detailList)
    LinearLayout ll_goods_information;

    @ViewById(R.id.tv_no_goods_information)
    AppCompatTextView tv_no_goods_information;

    @ViewById(R.id.tv_check_all_goods_information)
    AppCompatTextView tv_check_all_goods_information;

    @ViewById(R.id.ll_btns)
    LinearLayout ll_btns; // 两个按钮，合并前 合并后

    @ViewById(R.id.btn_pre_commbine)
    AppCompatButton btn_pre_commbine; // 合并前

    @ViewById(R.id.btn_back_commbine)
    AppCompatButton btn_back_commbine; // 合并后

    @ViewById(R.id.tv_detailList_hsCode)
    AppCompatTextView detailList_hsCode;

    @ViewById(R.id.tv_detailList_nameCn)
    AppCompatTextView detailList_nameCn;

    @ViewById(R.id.tv_detailList_nameEn)
    AppCompatTextView detailList_nameEn;

    @ViewById(R.id.tv_detailList_quantity)
    AppCompatTextView detailList_quantity;

    @ViewById(R.id.tv_detailList_unitCn)
    AppCompatTextView detailList_unitCn;

    @ViewById(R.id.tv_detailList_saleUnitPrice)
    AppCompatTextView detailList_saleUnitPrice;

    @ViewById(R.id.tv_detailList_saleAmount)
    AppCompatTextView detailList_saleAmount;

    @ViewById(R.id.tv_detailList_packingAmount)
    AppCompatTextView detailList_packingAmount;

    @ViewById(R.id.tv_detailList_packingUnitInfo)
    AppCompatTextView detailList_packingUnitInfo;

    @ViewById(R.id.tv_detailList_unitNWeight)
    AppCompatTextView detailList_unitNWeight;

    @ViewById(R.id.tv_detailList_unitGWeight)
    AppCompatTextView detailList_unitGWeight;

    @ViewById(R.id.tv_detailList_vol)
    AppCompatTextView detailList_vol;

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

        orderExport_invoicingCompanyName.setText(arguments.getString("orderExport_invoicingCompanyName"));
        consignor.setText(arguments.getString("consignor"));
        consignee.setText(arguments.getString("consignee"));
        orderExport_foreignName.setText(arguments.getString("orderExport_foreignName"));
        notifier.setText(arguments.getString("notifier"));

        orderExport_ckCustomsClearanceNo.setText(arguments.getString("orderExport_ckCustomsClearanceNo"));
        orderExport_salesOrder.setText(arguments.getString("orderExport_salesOrder"));
        orderExport_finalDestCountry.setText(arguments.getString("orderExport_finalDestCountry"));
        orderExport_priceTerm.setText(arguments.getString("orderExport_priceTerm"));
        orderExport_currency.setText(arguments.getString("orderExport_currency"));
        orderExport_amount.setText(arguments.getString("orderExport_amount"));
        orderExport_payment1Desc.setText(arguments.getString("orderExport_payment1Desc"));
        orderExport_payment2Desc.setText(arguments.getString("orderExport_payment2Desc"));
        orderExport_modeOfTrade.setText(arguments.getString("orderExport_modeOfTrade"));
        orderExport_source.setText(arguments.getString("orderExport_source"));
        orderExport_completeDeliveryDate.setText(arguments.getString("orderExport_completeDeliveryDate"));
        orderExport_invoiceAmount.setText(arguments.getString("orderExport_invoiceAmount"));
        order_remark.setText(arguments.getString("order_remark"));

        orderExport_modeOfTransportation.setText(arguments.getString("orderExport_modeOfTransportation"));
        orderExport_exportPortEn.setText(arguments.getString("orderExport_exportPortEn"));
        orderExport_transshippmentPortEn.setText(arguments.getString("orderExport_transshippmentPortEn"));
        orderExport_destinationPortEn.setText(arguments.getString("orderExport_destinationPortEn"));
        orderExport_shippmentDate.setText(arguments.getString("orderExport_shippmentDate"));
        orderExport_shippingSchedule.setText(arguments.getString("orderExport_shippingSchedule"));
        orderExport_closingDate.setText(arguments.getString("orderExport_closingDate"));
        orderExport_grossWeight.setText(arguments.getString("orderExport_grossWeight"));
        orderExport_netWeight.setText(arguments.getString("orderExport_netWeight"));
        orderExport_vol.setText(arguments.getString("orderExport_vol"));
        orderExport_frontMark.setText(arguments.getString("orderExport_frontMark"));
        orderExport_sideMark.setText(arguments.getString("orderExport_sideMark"));
        orderExport_forwarder.setText(arguments.getString("orderExport_forwarder"));
        orderExport_forwardingContactor.setText(arguments.getString("orderExport_forwardingContactor"));
        orderExport_forwardingPhone.setText(arguments.getString("orderExport_forwardingPhone"));

        // 货柜信息
        JSONArray forwardingContainer = JSON.parseArray(arguments.getString("forwardingContainer"));
        final ArrayList<ForwardingContainerEntity> forwardingContainerEntityList = new ArrayList<>();

        if (forwardingContainer.size() == 0) {
            ll_forwardingContainery.setVisibility(View.GONE);
            btn_check_forwardingContainer_information.setVisibility(View.GONE);
            tv_no_forwardingContainer_information.setVisibility(View.VISIBLE);
        } else {
            for (int i = 0; i < forwardingContainer.size(); i++) {
                JSONObject jsonObject = forwardingContainer.getJSONObject(i);
                if (i == 0) {
                    container_typeName.setText(jsonObject.getString("typeName"));
                    container_count.setText(jsonObject.getString("count"));
                    container_remark.setText(jsonObject.getString("remark"));
                    container_sealNo.setText(jsonObject.getString("sealNo"));
                    container_carNo.setText(jsonObject.getString("carNo"));
                    container_whiteCardNo.setText(jsonObject.getString("whiteCardNo"));
                    container_driverName.setText(jsonObject.getString("driverName"));
                    container_driverPhone.setText(jsonObject.getString("driverPhone"));
                }

                ForwardingContainerEntity forwardingContainerEntity = new ForwardingContainerEntity();
                forwardingContainerEntity.setTypeName(jsonObject.getString("typeName"));
                forwardingContainerEntity.setCount(jsonObject.getString("count"));
                forwardingContainerEntity.setRemark(jsonObject.getString("remark"));
                forwardingContainerEntity.setSealNo(jsonObject.getString("sealNo"));
                forwardingContainerEntity.setCarNo(jsonObject.getString("carNo"));
                forwardingContainerEntity.setWhiteCardNo(jsonObject.getString("whiteCardNo"));
                forwardingContainerEntity.setDriverName(jsonObject.getString("driverName"));
                forwardingContainerEntity.setDriverPhone(jsonObject.getString("driverPhone"));
                forwardingContainerEntityList.add(forwardingContainerEntity);
            }

            btn_check_forwardingContainer_information.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 弹出 全部货柜信息 对话框
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.MyAlertDialogStyle);
                    RecyclerView mRecyclerView = (RecyclerView) LayoutInflater.from(getActivity()).inflate(R.layout.recyclerview, null);
                    mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                    MyAdapter mAdapter = new MyAdapter(forwardingContainerEntityList);
                    mRecyclerView.setAdapter(mAdapter);

                    builder.setView(mRecyclerView);
                    builder.setTitle("全部货柜信息")
                            .setNegativeButton("关闭", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .create()
                            .show();
                }
            });

        }

        // 合并前商品信息
        JSONArray detailList = JSON.parseArray(arguments.getString("detailList"));
        final ArrayList<DetailListEntity> detailListEntityList = new ArrayList<>();

        if (detailList.size() == 0) {
            ll_goods_information.setVisibility(View.GONE);
            tv_check_all_goods_information.setVisibility(View.GONE);
            ll_btns.setVisibility(View.GONE);
            tv_no_goods_information.setVisibility(View.VISIBLE);
        } else {
            for (int i = 0; i < detailList.size(); i++) {
                JSONObject jsonObject = detailList.getJSONObject(i);
                if (i == 0) {
                    detailList_hsCode.setText(jsonObject.getString("hsCode"));
                    detailList_nameCn.setText(jsonObject.getString("nameCn"));
                    detailList_nameEn.setText(jsonObject.getString("nameEn"));
                    detailList_quantity.setText(jsonObject.getString("quantity"));
                    detailList_unitCn.setText(jsonObject.getString("unitCn"));
                    detailList_saleUnitPrice.setText(jsonObject.getString("saleUnitPrice"));
                    detailList_saleAmount.setText(jsonObject.getString("saleAmount"));
                    detailList_packingAmount.setText(jsonObject.getString("packingAmount"));
                    detailList_packingUnitInfo.setText(jsonObject.getString("packingUnitInfo"));
                    detailList_unitNWeight.setText(jsonObject.getString("unitNWeight"));
                    detailList_unitGWeight.setText(jsonObject.getString("unitGWeight"));
                    detailList_vol.setText(jsonObject.getString("vol"));
                }

                DetailListEntity detailListEntity = new DetailListEntity();
                detailListEntity.setHsCode(jsonObject.getString("hsCode"));
                detailListEntity.setNameCn(jsonObject.getString("nameCn"));
                detailListEntity.setNameEn(jsonObject.getString("nameEn"));
                detailListEntity.setQuantity(jsonObject.getString("quantity"));
                detailListEntity.setUnitCn(jsonObject.getString("unitCn"));
                detailListEntity.setSaleUnitPrice(jsonObject.getString("saleUnitPrice"));
                detailListEntity.setSaleAmount(jsonObject.getString("saleAmount"));
                detailListEntity.setPackingAmount(jsonObject.getString("packingAmount"));
                detailListEntity.setPackingUnitInfo(jsonObject.getString("packingUnitInfo"));
                detailListEntity.setUnitNWeight(jsonObject.getString("unitNWeight"));
                detailListEntity.setUnitGWeight(jsonObject.getString("unitGWeight"));
                detailListEntity.setVol(jsonObject.getString("vol"));
                detailListEntityList.add(detailListEntity);
            }

            btn_pre_commbine.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 弹出 合并前 商品信息 对话框
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.MyAlertDialogStyle);
                    RecyclerView mRecyclerView = (RecyclerView) LayoutInflater.from(getActivity()).inflate(R.layout.recyclerview, null);
                    mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                    MyDetailListAdapter mAdapter = new MyDetailListAdapter(detailListEntityList);
                    mRecyclerView.setAdapter(mAdapter);

                    builder.setView(mRecyclerView);
                    builder.setTitle("合并前-商品信息")
                            .setNegativeButton("关闭", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .create()
                            .show();
                }
            });

            // 合并后-商品信息
            JSONArray combineDetail = JSON.parseArray(arguments.getString("combineDetailList"));
            final ArrayList<DetailListEntity> combineDetailList = new ArrayList<>();

            if (combineDetail.size() == 0) {
                btn_back_commbine.setVisibility(View.INVISIBLE);
            } else {
                for (int i = 0; i < combineDetail.size(); i++) {
                    JSONObject jsonObject = combineDetail.getJSONObject(i);
                    DetailListEntity detailListEntity = new DetailListEntity();
                    detailListEntity.setHsCode(jsonObject.getString("hsCode"));
                    detailListEntity.setNameCn(jsonObject.getString("nameCn"));
                    detailListEntity.setNameEn(jsonObject.getString("nameEn"));
                    detailListEntity.setQuantity(jsonObject.getString("quantity"));
                    detailListEntity.setUnitCn(jsonObject.getString("unitCn"));
                    detailListEntity.setSaleUnitPrice(jsonObject.getString("saleUnitPrice"));
                    detailListEntity.setSaleAmount(jsonObject.getString("saleAmount"));
                    detailListEntity.setPackingAmount(jsonObject.getString("packingAmount"));
                    detailListEntity.setPackingUnitInfo(jsonObject.getString("packingUnitInfo"));
                    detailListEntity.setUnitNWeight(jsonObject.getString("unitNWeight"));
                    detailListEntity.setUnitGWeight(jsonObject.getString("unitGWeight"));
                    detailListEntity.setVol(jsonObject.getString("vol"));
                    combineDetailList.add(detailListEntity);
                }

                btn_back_commbine.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // 弹出 合并后 商品信息 对话框
                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.MyAlertDialogStyle);
                        RecyclerView mRecyclerView = (RecyclerView) LayoutInflater.from(getActivity()).inflate(R.layout.recyclerview, null);
                        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                        MyDetailListAdapter mAdapter = new MyDetailListAdapter(combineDetailList);
                        mRecyclerView.setAdapter(mAdapter);

                        builder.setView(mRecyclerView);
                        builder.setTitle("合并后-商品信息")
                                .setNegativeButton("关闭", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                })
                                .create()
                                .show();
                    }
                });
            }

        }

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
                    T_OrderFormDetailsAttachmentsEntity entity = new T_OrderFormDetailsAttachmentsEntity();
                    entity.setFileName(fileName);
                    entity.setAttachmentMetaDataId(jsonObject.getString("attachmentMetaDataId"));
                    entity.setAttachmentName(jsonObject.getString("attachmentName"));
                    entity.setCreateDate(jsonObject.getString("createDate"));
                    entity.setCreator(jsonObject.getString("creator"));
                    entity.setFileId(jsonObject.getString("fileId"));
                    entity.setFilePath(jsonObject.getString("filePath"));
                    entity.setId(jsonObject.getString("id"));
                    entity.setIsUpload(jsonObject.getString("isUpload"));
                    entity.setNeeded(jsonObject.getString("needed"));
                    entity.setOrderId(jsonObject.getString("orderId"));
                    T_OrderFormDetailsAttachmentsEntityList.add(entity);
                }
            }

            if (T_OrderFormDetailsAttachmentsEntityList.size() == 0) {
                attachments_recycler.setVisibility(View.GONE);
                tv_no_attachments.setVisibility(View.VISIBLE);
            } else {
                attachments_recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
                T_OrderFormDetailsAttachmentsAdapter mAttachmentsAdapter =
                        new T_OrderFormDetailsAttachmentsAdapter(T_OrderFormDetailsAttachmentsEntityList);
                attachments_recycler.setAdapter(mAttachmentsAdapter);
            }

        }

        mProgressBar.setVisibility(View.GONE);
        mNestedScrollView.setVisibility(View.VISIBLE);
    }

    /**
     * 一般贸易出口通关：货柜信息 实体类
     */
    public class ForwardingContainerEntity {

        private String typeName;
        private String count;
        private String remark;
        private String sealNo;
        private String carNo;
        private String whiteCardNo;
        private String driverName;
        private String driverPhone;

        public String getCarNo() {
            return carNo;
        }

        public void setCarNo(String carNo) {
            this.carNo = carNo;
        }

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
        }

        public String getDriverName() {
            return driverName;
        }

        public void setDriverName(String driverName) {
            this.driverName = driverName;
        }

        public String getDriverPhone() {
            return driverPhone;
        }

        public void setDriverPhone(String driverPhone) {
            this.driverPhone = driverPhone;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getSealNo() {
            return sealNo;
        }

        public void setSealNo(String sealNo) {
            this.sealNo = sealNo;
        }

        public String getTypeName() {
            return typeName;
        }

        public void setTypeName(String typeName) {
            this.typeName = typeName;
        }

        public String getWhiteCardNo() {
            return whiteCardNo;
        }

        public void setWhiteCardNo(String whiteCardNo) {
            this.whiteCardNo = whiteCardNo;
        }
    }

    private class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

        private List<ForwardingContainerEntity> list;

        public MyAdapter(List<ForwardingContainerEntity> list) {
            this.list = list;
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.t_item_orderform_details_general_trade_forwardingcontainer, viewGroup, false);
            return new ViewHolder(view);
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            public AppCompatTextView tv_count; // 货柜计数
            public AppCompatTextView tv_container_typeName; // 柜型
            public AppCompatTextView tv_container_count; // 数量
            public AppCompatTextView tv_container_remark; // 说明
            public AppCompatTextView tv_container_sealNo; // 柜号
            public AppCompatTextView tv_container_carNo; // 车号
            public AppCompatTextView tv_container_whiteCardNo;  // 白卡号
            public AppCompatTextView tv_container_driverName; // 司机
            public AppCompatTextView tv_container_driverPhone; // 联系方式

            public ViewHolder(final View itemView) {
                super(itemView);
                tv_count = (AppCompatTextView) itemView.findViewById(R.id.tv_count);
                tv_container_typeName = (AppCompatTextView) itemView.findViewById(R.id.tv_container_typeName);
                tv_container_count = (AppCompatTextView) itemView.findViewById(R.id.tv_container_count);
                tv_container_remark = (AppCompatTextView) itemView.findViewById(R.id.tv_container_remark);
                tv_container_sealNo = (AppCompatTextView) itemView.findViewById(R.id.tv_container_sealNo);
                tv_container_carNo = (AppCompatTextView) itemView.findViewById(R.id.tv_container_carNo);
                tv_container_whiteCardNo = (AppCompatTextView) itemView.findViewById(R.id.tv_container_whiteCardNo);
                tv_container_driverName = (AppCompatTextView) itemView.findViewById(R.id.tv_container_driverName);
                tv_container_driverPhone = (AppCompatTextView) itemView.findViewById(R.id.tv_container_driverPhone);

                // 整个条目点击事件
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
            }
        }

        @Override
        public void onBindViewHolder(ViewHolder viewHolder, int position) {
            ForwardingContainerEntity entity = list.get(position);

            viewHolder.tv_count.setText("货柜（" + (position + 1) + ")");
            viewHolder.tv_container_typeName.setText(entity.getTypeName());
            viewHolder.tv_container_count.setText(entity.getCount());
            viewHolder.tv_container_remark.setText(entity.getRemark());
            viewHolder.tv_container_sealNo.setText(entity.getSealNo());
            viewHolder.tv_container_carNo.setText(entity.getCarNo());
            viewHolder.tv_container_whiteCardNo.setText(entity.getWhiteCardNo());
            viewHolder.tv_container_driverName.setText(entity.getDriverName());
            viewHolder.tv_container_driverPhone.setText(entity.getDriverPhone());

        }
    }

    /**
     * 一般贸易出口通关：商品信息（合并前 DetailListEntity） 实体类
     */
    public class DetailListEntity {

        private String hsCode; // 海关编码
        private String nameCn; // 中文品名
        private String nameEn; // 英文品名
        private String quantity; // 数量
        private String unitCn;  // 单位
        private String saleUnitPrice; // 单价
        private String saleAmount; // 总价
        private String packingAmount; // 包装件数
        private String packingUnitInfo; // 包装单位
        private String unitNWeight; // 净重(KG)
        private String unitGWeight; // 毛重(KG)
        private String vol; //体积(CBM)

        public String getHsCode() {
            return hsCode;
        }

        public void setHsCode(String hsCode) {
            this.hsCode = hsCode;
        }

        public String getNameCn() {
            return nameCn;
        }

        public void setNameCn(String nameCn) {
            this.nameCn = nameCn;
        }

        public String getNameEn() {
            return nameEn;
        }

        public void setNameEn(String nameEn) {
            this.nameEn = nameEn;
        }

        public String getPackingAmount() {
            return packingAmount;
        }

        public void setPackingAmount(String packingAmount) {
            this.packingAmount = packingAmount;
        }

        public String getPackingUnitInfo() {
            return packingUnitInfo;
        }

        public void setPackingUnitInfo(String packingUnitInfo) {
            this.packingUnitInfo = packingUnitInfo;
        }

        public String getQuantity() {
            return quantity;
        }

        public void setQuantity(String quantity) {
            this.quantity = quantity;
        }

        public String getSaleAmount() {
            return saleAmount;
        }

        public void setSaleAmount(String saleAmount) {
            this.saleAmount = saleAmount;
        }

        public String getSaleUnitPrice() {
            return saleUnitPrice;
        }

        public void setSaleUnitPrice(String saleUnitPrice) {
            this.saleUnitPrice = saleUnitPrice;
        }

        public String getUnitCn() {
            return unitCn;
        }

        public void setUnitCn(String unitCn) {
            this.unitCn = unitCn;
        }

        public String getUnitGWeight() {
            return unitGWeight;
        }

        public void setUnitGWeight(String unitGWeight) {
            this.unitGWeight = unitGWeight;
        }

        public String getUnitNWeight() {
            return unitNWeight;
        }

        public void setUnitNWeight(String unitNWeight) {
            this.unitNWeight = unitNWeight;
        }

        public String getVol() {
            return vol;
        }

        public void setVol(String vol) {
            this.vol = vol;
        }
    }

    private class MyDetailListAdapter extends RecyclerView.Adapter<MyDetailListAdapter.ViewHolder> {

        private List<DetailListEntity> list;

        public MyDetailListAdapter(List<DetailListEntity> list) {
            this.list = list;
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.t_item_orderform_details_general_trade_detaillist, viewGroup, false);
            return new ViewHolder(view);
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            public AppCompatTextView tv_count;
            public AppCompatTextView tv_detailList_hsCode;
            public AppCompatTextView tv_detailList_nameCn;
            public AppCompatTextView tv_detailList_nameEn;
            public AppCompatTextView tv_detailList_quantity;
            public AppCompatTextView tv_detailList_unitCn;
            public AppCompatTextView tv_detailList_saleUnitPrice;
            public AppCompatTextView tv_detailList_saleAmount;
            public AppCompatTextView tv_detailList_packingAmount;
            public AppCompatTextView tv_detailList_packingUnitInfo;
            public AppCompatTextView tv_detailList_unitNWeight;
            public AppCompatTextView tv_detailList_unitGWeight;
            public AppCompatTextView tv_detailList_vol;

            public ViewHolder(final View itemView) {
                super(itemView);
                tv_count = (AppCompatTextView) itemView.findViewById(R.id.tv_count);
                tv_detailList_hsCode = (AppCompatTextView) itemView.findViewById(R.id.tv_detailList_hsCode);
                tv_detailList_nameCn = (AppCompatTextView) itemView.findViewById(R.id.tv_detailList_nameCn);
                tv_detailList_nameEn = (AppCompatTextView) itemView.findViewById(R.id.tv_detailList_nameEn);
                tv_detailList_quantity = (AppCompatTextView) itemView.findViewById(R.id.tv_detailList_quantity);
                tv_detailList_unitCn = (AppCompatTextView) itemView.findViewById(R.id.tv_detailList_unitCn);
                tv_detailList_saleUnitPrice = (AppCompatTextView) itemView.findViewById(R.id.tv_detailList_saleUnitPrice);
                tv_detailList_saleAmount = (AppCompatTextView) itemView.findViewById(R.id.tv_detailList_saleAmount);
                tv_detailList_packingAmount = (AppCompatTextView) itemView.findViewById(R.id.tv_detailList_packingAmount);
                tv_detailList_packingUnitInfo = (AppCompatTextView) itemView.findViewById(R.id.tv_detailList_packingUnitInfo);
                tv_detailList_unitNWeight = (AppCompatTextView) itemView.findViewById(R.id.tv_detailList_unitNWeight);
                tv_detailList_unitGWeight = (AppCompatTextView) itemView.findViewById(R.id.tv_detailList_unitGWeight);
                tv_detailList_vol = (AppCompatTextView) itemView.findViewById(R.id.tv_detailList_vol);

                // 整个条目点击事件
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
            }
        }

        @Override
        public void onBindViewHolder(ViewHolder viewHolder, int position) {
            DetailListEntity entity = list.get(position);

            viewHolder.tv_count.setText("商品（" + (position + 1) + ")");
            viewHolder.tv_detailList_hsCode.setText(entity.getHsCode());
            viewHolder.tv_detailList_nameCn.setText(entity.getNameCn());
            viewHolder.tv_detailList_nameEn.setText(entity.getNameEn());
            viewHolder.tv_detailList_quantity.setText(entity.getQuantity());
            viewHolder.tv_detailList_unitCn.setText(entity.getUnitCn());
            viewHolder.tv_detailList_saleUnitPrice.setText(entity.getSaleUnitPrice());
            viewHolder.tv_detailList_saleAmount.setText(entity.getSaleAmount());
            viewHolder.tv_detailList_packingAmount.setText(entity.getPackingAmount());
            viewHolder.tv_detailList_packingUnitInfo.setText(entity.getPackingUnitInfo());
            viewHolder.tv_detailList_unitNWeight.setText(entity.getUnitNWeight());
            viewHolder.tv_detailList_unitGWeight.setText(entity.getUnitGWeight());
            viewHolder.tv_detailList_vol.setText(entity.getVol());

        }
    }

}
