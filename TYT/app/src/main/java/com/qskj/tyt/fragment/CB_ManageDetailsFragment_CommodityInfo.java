package com.qskj.tyt.fragment;

import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qskj.tyt.AppDelegate;
import com.qskj.tyt.R;
import com.qskj.tyt.entity.CB_ManageDetailsEntity;
import com.qskj.tyt.utils.StringUtil;
import com.umeng.analytics.MobclickAgent;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.List;

/**
 * 经营单位-仓单详情-商品信息
 * Created by 赵 鑫 on 2015/12/4.
 */
@EFragment(R.layout.recyclerview)
public class CB_ManageDetailsFragment_CommodityInfo extends BaseFragment {

    private static final String TAG = "CB_ManageDetailsFragment_CommodityInfo";

    @ViewById(R.id.recyclerview)
    RecyclerView mRecyclerView;

    @Override
    public void onAfterViews() {
        Bundle arguments = getArguments();
        CB_ManageDetailsEntity entity = (CB_ManageDetailsEntity) arguments.getSerializable(AppDelegate.MANAGE_DETAILS_ENTITY);
        CB_ManageDetailsEntity.OrderExportEntity orderExport = entity.getOrderExport();
        List<CB_ManageDetailsEntity.DetailListEntity> detailList = orderExport.getDetailList();

        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setHasFixedSize(true);

        if (detailList != null) {
            MyAdapter mAdapter = new MyAdapter(detailList);
            mRecyclerView.setAdapter(mAdapter);
        }
    }

    private class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

        private List<CB_ManageDetailsEntity.DetailListEntity> list;

        public MyAdapter(List<CB_ManageDetailsEntity.DetailListEntity> list) {
            this.list = list;
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cb_fragment_manage_details_commodity_info, viewGroup, false);
            return new ViewHolder(view);
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            public AppCompatTextView tv_count; // 商品计数标示
            public AppCompatTextView tv_detailList_customsRecordNo; // 商品备案号
            public AppCompatTextView tv_detailList_hsCode1; // 商品HS码
            public AppCompatTextView tv_detailList_nameCn1; // 中文品名
            public AppCompatTextView tv_detailList_nameEn1; // 英文品名
            public AppCompatTextView tv_detailList_categoryCode; // 物品编码
            public AppCompatTextView tv_detailList_categoryName; // 物品类别
            public AppCompatTextView tv_detailList_customsSummary; // 申报要素
            public AppCompatTextView tv_detailList_supervisionCondition; // 监管条件
            public AppCompatTextView tv_detailList_tariffRate; // 出口关税
            public AppCompatTextView tv_detailList_brand; // 品牌
            public AppCompatTextView tv_detailList_material; // 材质
            public AppCompatTextView tv_detailList_placeOfOrigin; // 产地
            public AppCompatTextView tv_detailList_specification; // 规格型号
            public AppCompatTextView tv_detailList_jldwQuantity; // 第一法定数量
            public AppCompatTextView tv_detailList_jldwName; // 第一计量单位
            public AppCompatTextView tv_detailList_jldwSecondQuantity; // 第二法定数量
            public AppCompatTextView tv_detailList_JldwSecondName; // 第二计量单位
            public AppCompatTextView tv_detailList_packingAmount1; // 包装数量
            public AppCompatTextView tv_detailList_packingUnitCn; // 包装单位
            public AppCompatTextView tv_detailList_quantity1; // 工厂开票数量
            public AppCompatTextView tv_detailList_unitCn1; // 工厂开票单位
            public AppCompatTextView tv_detailList_grossWeight; // 毛重/KG
            public AppCompatTextView tv_detailList_vol1; // 体积/CB.M
            public AppCompatTextView tv_detailList_currency; // 币别
            public AppCompatTextView tv_detailList_saleUnitPrice1; // 单价
            public AppCompatTextView tv_detailList_saleAmount1; // 总价

            public ViewHolder(View itemView) {
                super(itemView);
                tv_count = (AppCompatTextView) itemView.findViewById(R.id.tv_count);
                tv_detailList_customsRecordNo = (AppCompatTextView) itemView.findViewById(R.id.tv_detailList_customsRecordNo);
                tv_detailList_hsCode1 = (AppCompatTextView) itemView.findViewById(R.id.tv_detailList_hsCode1);
                tv_detailList_nameCn1 = (AppCompatTextView) itemView.findViewById(R.id.tv_detailList_nameCn1);
                tv_detailList_nameEn1 = (AppCompatTextView) itemView.findViewById(R.id.tv_detailList_nameEn1);
                tv_detailList_categoryCode = (AppCompatTextView) itemView.findViewById(R.id.tv_detailList_categoryCode);
                tv_detailList_categoryName = (AppCompatTextView) itemView.findViewById(R.id.tv_detailList_categoryName);
                tv_detailList_customsSummary = (AppCompatTextView) itemView.findViewById(R.id.tv_detailList_customsSummary);
                tv_detailList_supervisionCondition = (AppCompatTextView) itemView.findViewById(R.id.tv_detailList_supervisionCondition);
                tv_detailList_tariffRate = (AppCompatTextView) itemView.findViewById(R.id.tv_detailList_tariffRate);
                tv_detailList_brand = (AppCompatTextView) itemView.findViewById(R.id.tv_detailList_brand);
                tv_detailList_material = (AppCompatTextView) itemView.findViewById(R.id.tv_detailList_material);
                tv_detailList_placeOfOrigin = (AppCompatTextView) itemView.findViewById(R.id.tv_detailList_placeOfOrigin);
                tv_detailList_specification = (AppCompatTextView) itemView.findViewById(R.id.tv_detailList_specification);
                tv_detailList_jldwQuantity = (AppCompatTextView) itemView.findViewById(R.id.tv_detailList_jldwQuantity);
                tv_detailList_jldwName = (AppCompatTextView) itemView.findViewById(R.id.tv_detailList_jldwName);
                tv_detailList_jldwSecondQuantity = (AppCompatTextView) itemView.findViewById(R.id.tv_detailList_jldwSecondQuantity);
                tv_detailList_JldwSecondName = (AppCompatTextView) itemView.findViewById(R.id.tv_detailList_JldwSecondName);
                tv_detailList_packingAmount1 = (AppCompatTextView) itemView.findViewById(R.id.tv_detailList_packingAmount1);
                tv_detailList_packingUnitCn = (AppCompatTextView) itemView.findViewById(R.id.tv_detailList_packingUnitCn);
                tv_detailList_quantity1 = (AppCompatTextView) itemView.findViewById(R.id.tv_detailList_quantity1);
                tv_detailList_unitCn1 = (AppCompatTextView) itemView.findViewById(R.id.tv_detailList_unitCn1);
                tv_detailList_grossWeight = (AppCompatTextView) itemView.findViewById(R.id.tv_detailList_grossWeight);
                tv_detailList_vol1 = (AppCompatTextView) itemView.findViewById(R.id.tv_detailList_vol1);
                tv_detailList_currency = (AppCompatTextView) itemView.findViewById(R.id.tv_detailList_currency);
                tv_detailList_saleUnitPrice1 = (AppCompatTextView) itemView.findViewById(R.id.tv_detailList_saleUnitPrice1);
                tv_detailList_saleAmount1 = (AppCompatTextView) itemView.findViewById(R.id.tv_detailList_saleAmount1);

            }

        }

        @Override
        public void onBindViewHolder(ViewHolder viewHolder, int position) {
            CB_ManageDetailsEntity.DetailListEntity entity = list.get(position);
            viewHolder.tv_count.setText("【" + (position + 1) + "】");
            viewHolder.tv_detailList_customsRecordNo.setText(entity.getCustomsRecordNo());
            viewHolder.tv_detailList_hsCode1.setText(entity.getHsCode());
            viewHolder.tv_detailList_nameCn1.setText(entity.getNameCn());
            viewHolder.tv_detailList_nameEn1.setText(entity.getNameEn());
            viewHolder.tv_detailList_categoryCode.setText(entity.getCategoryCode());
            viewHolder.tv_detailList_categoryName.setText(entity.getCategoryName());
            viewHolder.tv_detailList_customsSummary.setText(entity.getCustomsSummary());
            viewHolder.tv_detailList_supervisionCondition.setText(entity.getSupervisionCondition());
            viewHolder.tv_detailList_tariffRate.setText(StringUtil.numberFormat(entity.getTariffRate()));
            viewHolder.tv_detailList_brand.setText(entity.getBrand());
            viewHolder.tv_detailList_material.setText(entity.getMaterial());
            viewHolder.tv_detailList_placeOfOrigin.setText(entity.getPlaceOfOrigin());
            viewHolder.tv_detailList_specification.setText(entity.getSpecification());
            viewHolder.tv_detailList_jldwQuantity.setText(StringUtil.numberFormat(entity.getJldwQuantity()));
            viewHolder.tv_detailList_jldwName.setText(entity.getJldwNameEn());
            viewHolder.tv_detailList_jldwSecondQuantity.setText(StringUtil.numberFormat(entity.getJldwSecondQuantity()));
            viewHolder.tv_detailList_JldwSecondName.setText(entity.getJldwSecondNameEn());
            viewHolder.tv_detailList_packingAmount1.setText(StringUtil.numberFormat(entity.getPackingAmount()));
            viewHolder.tv_detailList_packingUnitCn.setText(entity.getPackingUnitCn());
            viewHolder.tv_detailList_quantity1.setText(StringUtil.numberFormat(entity.getQuantity()));
            viewHolder.tv_detailList_unitCn1.setText(entity.getUnitCn());
            viewHolder.tv_detailList_grossWeight.setText(StringUtil.numberFormat(entity.getGrossWeight()));
            viewHolder.tv_detailList_vol1.setText(StringUtil.numberFormat(entity.getVol()));
            viewHolder.tv_detailList_currency.setText(entity.getCurrency());
            viewHolder.tv_detailList_saleUnitPrice1.setText(StringUtil.numberFormat(entity.getSaleUnitPrice()));
            viewHolder.tv_detailList_saleAmount1.setText(StringUtil.numberFormat(entity.getSaleAmount()));

        }
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
