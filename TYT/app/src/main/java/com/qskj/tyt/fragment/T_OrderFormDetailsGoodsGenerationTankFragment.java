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
 * 订单详情页 - 货代订舱
 */
@EFragment(R.layout.t_fragment_order_form_goods_generation_tank_details)
public class T_OrderFormDetailsGoodsGenerationTankFragment extends BaseFragment {

    @ViewById(R.id.nestedScrollView)
    NestedScrollView mNestedScrollView;

    @ViewById(R.id.progressbar)
    CircleProgressBar mProgressBar;

    @ViewById(R.id.ll_goods_information)
    LinearLayout ll_goods_information;

    @ViewById(R.id.tv_no_goods_information)
    AppCompatTextView tv_no_goods_information;

    @ViewById(R.id.btn_check_goods_information)
    AppCompatButton btn_check_goods_information;

    @ViewById(R.id.tv_hd_consignor)
    AppCompatTextView hd_consignor;

    @ViewById(R.id.tv_hd_consignee)
    AppCompatTextView hd_consignee;

    @ViewById(R.id.tv_hd_notifier)
    AppCompatTextView hd_notifier;

    @ViewById(R.id.tv_orderExport_exportPortEn1)
    AppCompatTextView orderExport_exportPortEn1;

    @ViewById(R.id.tv_orderExport_transshippmentPortEn1)
    AppCompatTextView orderExport_transshippmentPortEn1;

    @ViewById(R.id.tv_orderExport_destinationPortEn1)
    AppCompatTextView orderExport_destinationPortEn1;

    @ViewById(R.id.tv_orderExport_etd)
    AppCompatTextView orderExport_etd;

    @ViewById(R.id.tv_orderExport_modeOfTrade1)
    AppCompatTextView orderExport_modeOfTrade1;

    @ViewById(R.id.tv_orderExport_freightTerms)
    AppCompatTextView orderExport_freightTerms;

    @ViewById(R.id.tv_orderExport_isTransport)
    AppCompatTextView orderExport_isTransport;

    @ViewById(R.id.tv_orderExport_shippingOrderType)
    AppCompatTextView orderExport_shippingOrderType;

    @ViewById(R.id.tv_orderExport_payment1Desc1)
    AppCompatTextView orderExport_payment1Desc1;

    @ViewById(R.id.tv_orderExport_carrierNameEn)
    AppCompatTextView orderExport_carrierNameEn;

    @ViewById(R.id.tv_orderExport_blNo)
    AppCompatTextView orderExport_blNo;

    @ViewById(R.id.tv_orderExport_vesselName)
    AppCompatTextView orderExport_vesselName;

    @ViewById(R.id.tv_orderExport_voyageNo)
    AppCompatTextView orderExport_voyageNo;

    @ViewById(R.id.tv_orderExport_forwarder1)
    AppCompatTextView orderExport_forwarder1;

    @ViewById(R.id.tv_orderExport_forwardingContactor1)
    AppCompatTextView orderExport_forwardingContactor1;

    @ViewById(R.id.tv_orderExport_forwardingPhone1)
    AppCompatTextView orderExport_forwardingPhone1;

    @ViewById(R.id.tv_orderExport_forwardingAddress)
    AppCompatTextView orderExport_forwardingAddress;

    @ViewById(R.id.tv_orderExport_forwardingEmail)
    AppCompatTextView orderExport_forwardingEmail;

    @ViewById(R.id.tv_orderExport_forwardingFax)
    AppCompatTextView orderExport_forwardingFax;

    @ViewById(R.id.tv_orderExport_frontMark1)
    AppCompatTextView orderExport_frontMark1;

    @ViewById(R.id.tv_orderExport_forwardingRemark)
    AppCompatTextView orderExport_forwardingRemark;

    @ViewById(R.id.tv_shippingDetailList_nameCn)
    AppCompatTextView shippingDetailList_nameCn;

    @ViewById(R.id.tv_shippingDetailList_quantity)
    AppCompatTextView shippingDetailList_quantity;

    @ViewById(R.id.tv_shippingDetailList_packingUnitEn)
    AppCompatTextView shippingDetailList_packingUnitEn;

    @ViewById(R.id.tv_shippingDetailList_grossWeight)
    AppCompatTextView shippingDetailList_grossWeight;

    @ViewById(R.id.tv_shippingDetailList_vol)
    AppCompatTextView shippingDetailList_vol;

    @ViewById(R.id.tv_shippingDetailList_netWeight)
    AppCompatTextView shippingDetailList_netWeight;

    @ViewById(R.id.ll_forwardingContainer)
    LinearLayout ll_forwardingContainery; // 货柜信息

    @ViewById(R.id.tv_no_forwardingContainer_information)
    AppCompatTextView tv_no_forwardingContainer_information; // 暂无货柜信息

    @ViewById(R.id.btn_check_forwardingContainer_information)
    AppCompatButton btn_check_forwardingContainer_information; // 查看全部货柜信息

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

        hd_consignor.setText(arguments.getString("hd_consignor"));
        hd_consignee.setText(arguments.getString("hd_consignee"));
        hd_notifier.setText(arguments.getString("hd_notifier"));

        orderExport_exportPortEn1.setText(arguments.getString("orderExport_exportPortEn1"));
        orderExport_transshippmentPortEn1.setText(arguments.getString("orderExport_transshippmentPortEn1"));
        orderExport_destinationPortEn1.setText(arguments.getString("orderExport_destinationPortEn1"));
        orderExport_etd.setText(arguments.getString("orderExport_etd"));
        orderExport_modeOfTrade1.setText(arguments.getString("orderExport_modeOfTrade1"));
        orderExport_freightTerms.setText(arguments.getString("orderExport_freightTerms"));
        orderExport_isTransport.setText(arguments.getString("orderExport_isTransport"));
        orderExport_shippingOrderType.setText(arguments.getString("orderExport_shippingOrderType"));
        orderExport_payment1Desc1.setText(arguments.getString("orderExport_payment1Desc1"));
        orderExport_carrierNameEn.setText(arguments.getString("orderExport_carrierNameEn"));
        orderExport_blNo.setText(arguments.getString("orderExport_blNo"));
        orderExport_vesselName.setText(arguments.getString("orderExport_vesselName"));
        orderExport_voyageNo.setText(arguments.getString("orderExport_voyageNo"));
        orderExport_forwarder1.setText(arguments.getString("orderExport_forwarder1"));
        orderExport_forwardingContactor1.setText(arguments.getString("orderExport_forwardingContactor1"));
        orderExport_forwardingPhone1.setText(arguments.getString("orderExport_forwardingPhone1"));
        orderExport_forwardingEmail.setText(arguments.getString("orderExport_forwardingEmail"));
        orderExport_forwardingFax.setText(arguments.getString("orderExport_forwardingFax"));
        orderExport_frontMark1.setText(arguments.getString("orderExport_frontMark1"));
        orderExport_forwardingRemark.setText(arguments.getString("orderExport_forwardingRemark"));

        // 商品信息
        JSONArray shippingDetailList = JSON.parseArray(arguments.getString("shippingDetailList"));
        final ArrayList<ShippingDetailListEntity> shippingDetailListEntities = new ArrayList<>();

        if (shippingDetailList.size() == 0) {
            ll_goods_information.setVisibility(View.GONE);
            btn_check_goods_information.setVisibility(View.GONE);
            tv_no_goods_information.setVisibility(View.VISIBLE);
        }

        for (int i = 0; i < shippingDetailList.size(); i++) {
            JSONObject jsonObject = shippingDetailList.getJSONObject(i);
            if (i == 0) {
                shippingDetailList_nameCn.setText(jsonObject.getString("nameCn"));
                shippingDetailList_quantity.setText(jsonObject.getString("quantity"));
                shippingDetailList_packingUnitEn.setText(""); // TODO 包装单位 找不到字段
                shippingDetailList_grossWeight.setText(jsonObject.getString("grossWeight"));
                shippingDetailList_vol.setText(jsonObject.getString("vol"));
                shippingDetailList_netWeight.setText(jsonObject.getString("netWeight"));
            }

            ShippingDetailListEntity shippingDetailListEntity = new ShippingDetailListEntity();
            shippingDetailListEntity.setGrossWeight(jsonObject.getString("grossWeight"));
            shippingDetailListEntity.setId(jsonObject.getString("id"));
            shippingDetailListEntity.setInspectionFlag(jsonObject.getString("inspectionFlag"));
            shippingDetailListEntity.setNameCn(jsonObject.getString("nameCn"));
            shippingDetailListEntity.setNetWeight(jsonObject.getString("netWeight"));
            shippingDetailListEntity.setOwnerId(jsonObject.getString("ownerId"));
            shippingDetailListEntity.setQuantity(jsonObject.getString("quantity"));
            shippingDetailListEntity.setStort(jsonObject.getString("stort"));
            shippingDetailListEntity.setType(jsonObject.getString("type"));
            shippingDetailListEntity.setUnitVol(jsonObject.getString("unitVol"));
            shippingDetailListEntity.setVol(jsonObject.getString("vol"));
            shippingDetailListEntities.add(shippingDetailListEntity);
        }

        btn_check_goods_information.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 弹出 全部商品信息 对话框
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                RecyclerView mRecyclerView = (RecyclerView) LayoutInflater.from(getActivity()).inflate(R.layout.recyclerview, null);
                mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                MyShippingDetailListAdapter mAdapter = new MyShippingDetailListAdapter(shippingDetailListEntities);
                mRecyclerView.setAdapter(mAdapter);

                builder.setView(mRecyclerView);
                builder.setTitle("全部商品信息")
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

        // 货柜信息
        JSONArray forwardingContainer = JSON.parseArray(arguments.getString("forwardingContainer"));
        final ArrayList<ForwardingContainerEntity> forwardingContainerEntityList = new ArrayList<>();

        if (forwardingContainer.size() == 0) {
            ll_forwardingContainery.setVisibility(View.GONE);
            btn_check_forwardingContainer_information.setVisibility(View.GONE);
            tv_no_forwardingContainer_information.setVisibility(View.VISIBLE);
        }

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
                // 弹出 全部商品信息 对话框
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
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

        // 附件
        JSONArray attachments = JSON.parseArray(arguments.getString("attachments"));
        ArrayList<T_OrderFormDetailsAttachmentsEntity> T_OrderFormDetailsAttachmentsEntityList = new ArrayList<>();
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

    /**
     * 货代订舱：商品信息 实体类
     */
    public class ShippingDetailListEntity {

        private String grossWeight;
        private String id;
        private String inspectionFlag;
        private String nameCn;
        private String netWeight;
        private String ownerId;
        private String quantity;
        private String stort;
        private String type;
        private String unitVol;
        private String vol;

        public String getGrossWeight() {
            return grossWeight;
        }

        public void setGrossWeight(String grossWeight) {
            this.grossWeight = grossWeight;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getInspectionFlag() {
            return inspectionFlag;
        }

        public void setInspectionFlag(String inspectionFlag) {
            this.inspectionFlag = inspectionFlag;
        }

        public String getNameCn() {
            return nameCn;
        }

        public void setNameCn(String nameCn) {
            this.nameCn = nameCn;
        }

        public String getNetWeight() {
            return netWeight;
        }

        public void setNetWeight(String netWeight) {
            this.netWeight = netWeight;
        }

        public String getOwnerId() {
            return ownerId;
        }

        public void setOwnerId(String ownerId) {
            this.ownerId = ownerId;
        }

        public String getQuantity() {
            return quantity;
        }

        public void setQuantity(String quantity) {
            this.quantity = quantity;
        }

        public String getStort() {
            return stort;
        }

        public void setStort(String stort) {
            this.stort = stort;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUnitVol() {
            return unitVol;
        }

        public void setUnitVol(String unitVol) {
            this.unitVol = unitVol;
        }

        public String getVol() {
            return vol;
        }

        public void setVol(String vol) {
            this.vol = vol;
        }
    }

    private class MyShippingDetailListAdapter extends RecyclerView.Adapter<MyShippingDetailListAdapter.ViewHolder> {

        private List<ShippingDetailListEntity> list;

        public MyShippingDetailListAdapter(List<ShippingDetailListEntity> list) {
            this.list = list;
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.t_item_orderform_details_goods_generation_tank_shippingdetaillist, viewGroup, false);
            return new ViewHolder(view);
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            public AppCompatTextView tv_count; // 商品计数
            public AppCompatTextView tv_shippingDetailList_nameCn; // 中文品名
            public AppCompatTextView tv_shippingDetailList_quantity; // 包装件数
            public AppCompatTextView tv_shippingDetailList_packingUnitEn; // 包装单位
            public AppCompatTextView tv_shippingDetailList_grossWeight; // 毛重(KG)
            public AppCompatTextView tv_shippingDetailList_vol;  // 体积(CBM)
            public AppCompatTextView tv_shippingDetailList_netWeight; // 净重(KG)

            public ViewHolder(final View itemView) {
                super(itemView);
                tv_count = (AppCompatTextView) itemView.findViewById(R.id.tv_count);
                tv_shippingDetailList_nameCn = (AppCompatTextView) itemView.findViewById(R.id.tv_shippingDetailList_nameCn);
                tv_shippingDetailList_quantity = (AppCompatTextView) itemView.findViewById(R.id.tv_shippingDetailList_quantity);
                tv_shippingDetailList_packingUnitEn = (AppCompatTextView) itemView.findViewById(R.id.tv_shippingDetailList_packingUnitEn);
                tv_shippingDetailList_grossWeight = (AppCompatTextView) itemView.findViewById(R.id.tv_shippingDetailList_grossWeight);
                tv_shippingDetailList_vol = (AppCompatTextView) itemView.findViewById(R.id.tv_shippingDetailList_vol);
                tv_shippingDetailList_netWeight = (AppCompatTextView) itemView.findViewById(R.id.tv_shippingDetailList_netWeight);

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
            ShippingDetailListEntity entity = list.get(position);

            viewHolder.tv_count.setText("商品（" + (position + 1) + ")");
            viewHolder.tv_shippingDetailList_nameCn.setText(entity.getNameCn());
            viewHolder.tv_shippingDetailList_grossWeight.setText(entity.getGrossWeight());
            viewHolder.tv_shippingDetailList_netWeight.setText(entity.getNetWeight());
            viewHolder.tv_shippingDetailList_quantity.setText(entity.getQuantity());
            viewHolder.tv_shippingDetailList_vol.setText(entity.getVol());
            viewHolder.tv_shippingDetailList_packingUnitEn.setText(""); // TODO 找不到字段

        }

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
}


