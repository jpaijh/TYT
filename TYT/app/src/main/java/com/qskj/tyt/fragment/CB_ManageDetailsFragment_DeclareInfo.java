package com.qskj.tyt.fragment;

import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;

import com.qskj.tyt.AppDelegate;
import com.qskj.tyt.R;
import com.qskj.tyt.entity.CB_ManageDetailsEntity;
import com.qskj.tyt.utils.StringUtil;
import com.umeng.analytics.MobclickAgent;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;


/**
 * 经营单位-仓单详情-报关信息
 * Created by 赵 鑫 on 2015/12/3.
 */
@EFragment(R.layout.cb_fragment_manage_details_declare_info)
public class CB_ManageDetailsFragment_DeclareInfo extends BaseFragment {

    private static final String TAG = "CB_ManageDetailsFragment_DeclareInfo";

    @ViewById(R.id.tv_orderDelivery_businessEnterpriseName1)
    AppCompatTextView tv_orderDelivery_businessEnterpriseName1;

    @ViewById(R.id.tv_orderDelivery_businessEnterpriseCode1)
    AppCompatTextView tv_orderDelivery_businessEnterpriseCode1;

    @ViewById(R.id.tv_customsName)
    AppCompatTextView tv_customsName;

    @ViewById(R.id.tv_customsName1)
    AppCompatTextView tv_customsName1;

    @ViewById(R.id.tv_blNo)
    AppCompatTextView tv_blNo;

    @ViewById(R.id.tv_orderType)
    AppCompatTextView tv_orderType;

    @ViewById(R.id.tv_vesselName)
    AppCompatTextView tv_vesselName;

    @ViewById(R.id.tv_voyageNo)
    AppCompatTextView tv_voyageNo;

    @ViewById(R.id.tv_actualCustomsClearanceDate)
    AppCompatTextView tv_actualCustomsClearanceDate;

    @ViewById(R.id.tv_orderDelivery_declareDate)
    AppCompatTextView tv_orderDelivery_declareDate;

    @ViewById(R.id.tv_salesOrder)
    AppCompatTextView tv_salesOrder;

    @ViewById(R.id.tv_modeOfTransportationString)
    AppCompatTextView tv_modeOfTransportationString;

    @ViewById(R.id.tv_orderDelivery_customsRecordNo)
    AppCompatTextView tv_orderDelivery_customsRecordNo;

    @ViewById(R.id.tv_licenseNo)
    AppCompatTextView tv_licenseNo;

    @ViewById(R.id.tv_orderDelivery_deliveryEnterpriseCode)
    AppCompatTextView tv_orderDelivery_deliveryEnterpriseCode;

    @ViewById(R.id.tv_orderDelivery_deliveryEnterpriseName)
    AppCompatTextView tv_orderDelivery_deliveryEnterpriseName;

    @ViewById(R.id.tv_orderDelivery_declareEnterpriseCode)
    AppCompatTextView tv_orderDelivery_declareEnterpriseCode;

    @ViewById(R.id.tv_orderDelivery_declareEnterpriseName)
    AppCompatTextView tv_orderDelivery_declareEnterpriseName;

    @ViewById(R.id.tv_priceTerm)
    AppCompatTextView tv_priceTerm;

    @ViewById(R.id.tv_payment1)
    AppCompatTextView tv_payment1;

    @ViewById(R.id.tv_ckCustomsClearanceNo)
    AppCompatTextView tv_ckCustomsClearanceNo;

    @ViewById(R.id.tv_orderDelivery_domesticSaleRate)
    AppCompatTextView tv_orderDelivery_domesticSaleRate;

    @ViewById(R.id.tv_orderDelivery_exemptionWayId)
    AppCompatTextView tv_orderDelivery_exemptionWayId;

    @ViewById(R.id.tv_finalDestCountryCn)
    AppCompatTextView tv_finalDestCountryCn;

    @ViewById(R.id.tv_destinationPortCn1)
    AppCompatTextView tv_destinationPortCn1;

    @ViewById(R.id.tv_source)
    AppCompatTextView tv_source;

    @ViewById(R.id.tv_shippingOrderType)
    AppCompatTextView tv_shippingOrderType;

    @ViewById(R.id.tv_packingQuantity1)
    AppCompatTextView tv_packingQuantity1;

    @ViewById(R.id.tv_vol1)
    AppCompatTextView tv_vol1;

    @ViewById(R.id.tv_grossWeight1)
    AppCompatTextView tv_grossWeight1;

    @ViewById(R.id.tv_netWeight)
    AppCompatTextView tv_netWeight;

    @ViewById(R.id.tv_amount)
    AppCompatTextView tv_amount;

    @ViewById(R.id.tv_currency)
    AppCompatTextView tv_currency;

    @ViewById(R.id.tv_frontMark1)
    AppCompatTextView tv_frontMark1;

    @ViewById(R.id.tv_remark1)
    AppCompatTextView tv_remark1;

    @Override
    public void onAfterViews() {
        Bundle arguments = getArguments();
        CB_ManageDetailsEntity entity = (CB_ManageDetailsEntity) arguments.getSerializable(AppDelegate.MANAGE_DETAILS_ENTITY);
        CB_ManageDetailsEntity.OrderDeliveryEntity orderDelivery = entity.getOrderDelivery();
        CB_ManageDetailsEntity.OrderExportEntity orderExport = entity.getOrderExport();

        tv_orderDelivery_businessEnterpriseName1.setText(orderDelivery.getBusinessEnterpriseName());
        tv_orderDelivery_businessEnterpriseCode1.setText(orderDelivery.getBusinessEnterpriseCode());
        tv_customsName.setText(orderExport.getCustomsName());
        tv_customsName1.setText(orderExport.getCustomsName());
        tv_blNo.setText(orderExport.getBlNo());
        tv_orderType.setText(entity.getOrderType() == 1 ? "出口" : "进口");
        tv_vesselName.setText(orderExport.getVesselName());
        tv_voyageNo.setText(orderExport.getVoyageNo());
        tv_actualCustomsClearanceDate.setText(StringUtil.YMDDtoYMD(orderExport.getActualCustomsClearanceDate()));
        tv_orderDelivery_declareDate.setText(StringUtil.YMDDtoYMD(orderDelivery.getDeclareDate()));
        tv_salesOrder.setText(orderExport.getSalesOrder());

        // 运输方式 1：BY SEA 2：BY AIR 3：BY LAND 4:BY SEA AND LAND
        int modeOfTransportation = orderExport.getModeOfTransportation();
        if (modeOfTransportation == 1) {
            tv_modeOfTransportationString.setText("BY SEA");
        } else if (modeOfTransportation == 2) {
            tv_modeOfTransportationString.setText("BY AIR");
        } else if (modeOfTransportation == 3) {
            tv_modeOfTransportationString.setText("BY LAND");
        } else if (modeOfTransportation == 4) {
            tv_modeOfTransportationString.setText("BY SEA AND LAND");
        }

        tv_orderDelivery_customsRecordNo.setText(orderDelivery.getCustomsRecordNo());
        tv_licenseNo.setText(orderExport.getLicenseNo());
        tv_orderDelivery_deliveryEnterpriseCode.setText(orderDelivery.getDeliveryEnterpriseCode());
        tv_orderDelivery_deliveryEnterpriseName.setText(orderDelivery.getDeliveryEnterpriseName());
        tv_orderDelivery_declareEnterpriseCode.setText(orderDelivery.getDeclareEnterpriseCode());
        tv_orderDelivery_declareEnterpriseName.setText(orderDelivery.getDeclareEnterpriseName());
        tv_priceTerm.setText(orderExport.getPriceTerm());
        switch (orderExport.getPayment1()) {
            case "1":
                tv_payment1.setText("信汇");
                break;
            case "2":
                tv_payment1.setText("电汇");
                break;
            case "3":
                tv_payment1.setText("票汇");
                break;
            case "4":
                tv_payment1.setText("付款交单");
                break;
            case "5":
                tv_payment1.setText("承兑交单");
                break;
            case "6":
                tv_payment1.setText("信用证");
                break;
            case "7":
                tv_payment1.setText("先出后结");
                break;
            case "8":
                tv_payment1.setText("先结后出");
                break;
            case "9":
                tv_payment1.setText("其他");
                break;
        }

        tv_ckCustomsClearanceNo.setText(orderExport.getCkCustomsClearanceNo());
        tv_orderDelivery_domesticSaleRate.setText(StringUtil.numberFormat(orderDelivery.getDomesticSaleRate()));
        setExemptionWay(orderDelivery.getExemptionWayId());
        tv_finalDestCountryCn.setText(orderExport.getFinalDestCountryCn());
        tv_destinationPortCn1.setText(orderExport.getDestinationPortCn());
        tv_source.setText(orderExport.getSource());
        String shippingOrderType = orderExport.getShippingOrderType();
        if ("LCL".equals(shippingOrderType)) {
            tv_shippingOrderType.setText("拼箱");
        } else if ("FCL".equals(shippingOrderType)) {
            tv_shippingOrderType.setText("整箱");
        }

        tv_packingQuantity1.setText(StringUtil.numberFormat(orderExport.getPackingQuantity()) + " " + orderExport.getPackingUnitCn());
        tv_vol1.setText(StringUtil.numberFormat(orderExport.getVol()) + " " + orderExport.getVolUnitEn());
        tv_grossWeight1.setText(StringUtil.numberFormat(orderExport.getGrossWeight()) + " " + orderExport.getWeightUnitEn());
        tv_netWeight.setText(StringUtil.numberFormat(orderExport.getNetWeight()) + " " + orderExport.getWeightUnitEn());
        tv_currency.setText(orderExport.getCurrency());
        tv_amount.setText(StringUtil.numberFormat(orderExport.getAmount()));
        tv_frontMark1.setText(orderExport.getFrontMark());
        tv_remark1.setText(orderExport.getRemark());
    }

    /**
     * 设置 征免性质
     *
     * @param exemptionWayId
     */
    private void setExemptionWay(int exemptionWayId) {
        switch (exemptionWayId) {
            case 2811610:
                tv_orderDelivery_exemptionWayId.setText("101 一般征税");
                break;
            case 2811611:
                tv_orderDelivery_exemptionWayId.setText("118 整车征税");
                break;
            case 2811612:
                tv_orderDelivery_exemptionWayId.setText("119 零部件征税");
                break;
            case 2811613:
                tv_orderDelivery_exemptionWayId.setText("201 无偿援助");
                break;
            case 2811614:
                tv_orderDelivery_exemptionWayId.setText("299 其他法定");
                break;
            case 2811615:
                tv_orderDelivery_exemptionWayId.setText("301 特定区域");
                break;
            case 2811616:
                tv_orderDelivery_exemptionWayId.setText("307 保税区");
                break;
            case 2811617:
                tv_orderDelivery_exemptionWayId.setText("399 其他地区");
                break;
            case 2811618:
                tv_orderDelivery_exemptionWayId.setText("401 科教用品");
                break;
            case 2811619:
                tv_orderDelivery_exemptionWayId.setText("403 技术改造");
                break;
            case 2811620:
                tv_orderDelivery_exemptionWayId.setText("406 重大项目");
                break;
            case 2811621:
                tv_orderDelivery_exemptionWayId.setText("408 重大技术");
                break;
            case 2811622:
                tv_orderDelivery_exemptionWayId.setText("412 基础设施");
                break;
            case 2811623:
                tv_orderDelivery_exemptionWayId.setText("413 残疾人");
                break;
            case 2811624:
                tv_orderDelivery_exemptionWayId.setText("417 远洋渔业");
                break;
            case 2811625:
                tv_orderDelivery_exemptionWayId.setText("418 国产化");
                break;
            case 2811626:
                tv_orderDelivery_exemptionWayId.setText("419 整车特征");
                break;
            case 2811627:
                tv_orderDelivery_exemptionWayId.setText("420 远洋船舶");
                break;
            case 2811628:
                tv_orderDelivery_exemptionWayId.setText("421 内销设备");
                break;
            case 2811629:
                tv_orderDelivery_exemptionWayId.setText("422 集成电路");
                break;
            case 2811630:
                tv_orderDelivery_exemptionWayId.setText("423 新型显示器件");
                break;
            case 2811631:
                tv_orderDelivery_exemptionWayId.setText("499 ITA产品");
                break;
            case 2811632:
                tv_orderDelivery_exemptionWayId.setText("501 加工设备");
                break;
            case 2811633:
                tv_orderDelivery_exemptionWayId.setText("502 来料加工");
                break;
            case 2811634:
                tv_orderDelivery_exemptionWayId.setText("503 进料加工");
                break;
            case 2811635:
                tv_orderDelivery_exemptionWayId.setText("506 边境小额");
                break;
            case 2811636:
                tv_orderDelivery_exemptionWayId.setText("510 港澳OPA");
                break;
            case 2811637:
                tv_orderDelivery_exemptionWayId.setText("601 中外合资");
                break;
            case 2811638:
                tv_orderDelivery_exemptionWayId.setText("602 中外合作");
                break;
            case 2811639:
                tv_orderDelivery_exemptionWayId.setText("603 外资企业");
                break;
            case 2811640:
                tv_orderDelivery_exemptionWayId.setText("605 勘探开发煤层气");
                break;
            case 2811641:
                tv_orderDelivery_exemptionWayId.setText("606 海洋石油");
                break;
            case 2811642:
                tv_orderDelivery_exemptionWayId.setText("608 陆上石油");
                break;
            case 2811643:
                tv_orderDelivery_exemptionWayId.setText("609 贷款项目");
                break;
            case 2811644:
                tv_orderDelivery_exemptionWayId.setText("611 贷款中标");
                break;
            case 2811645:
                tv_orderDelivery_exemptionWayId.setText("789 鼓励项目");
                break;
            case 2811646:
                tv_orderDelivery_exemptionWayId.setText("799 自有资金");
                break;
            case 2811647:
                tv_orderDelivery_exemptionWayId.setText("801 救灾捐赠");
                break;
            case 2811648:
                tv_orderDelivery_exemptionWayId.setText("802 扶贫慈善");
                break;
            case 2811649:
                tv_orderDelivery_exemptionWayId.setText("999 例外减免");
                break;
            case 2811650:
                tv_orderDelivery_exemptionWayId.setText("000 照章征税");
                break;
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
