package com.qskj.tyt.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.apkfuns.logutils.LogUtils;
import com.lsjwzh.widget.materialloadingprogressbar.CircleProgressBar;
import com.qskj.tyt.AppDelegate;
import com.qskj.tyt.MyAPI;
import com.qskj.tyt.MyApplication_;
import com.qskj.tyt.R;
import com.qskj.tyt.adapter.TableLayoutAdapter;
import com.qskj.tyt.fragment.T_OrderFormDetailsGeneralTradeFragment_;
import com.qskj.tyt.fragment.T_OrderFormDetailsGoodsGenerationTankFragment_;
import com.qskj.tyt.fragment.T_OrderFormDetalsCreditFinancingFragment_;
import com.qskj.tyt.fragment.T_OrderFormDetalsExportRebatesFragment_;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.HashMap;
import java.util.Map;

/**
 * 订单详情页
 */
@EActivity(R.layout.tablayout_viewpager_toolbar_with_progressbar)
public class T_OrderFormDetailsActivity extends BaseActivity {

    private RequestQueue mRequestQueue;
    private TableLayoutAdapter mAdapter;
    private SharedPreferences userInfoSp;
    private int orderId;

    @ViewById(R.id.tabLayout)
    TabLayout mTabLayout;

    @ViewById(R.id.viewpager)
    ViewPager mViewPager;

    @ViewById(R.id.progressbar)
    CircleProgressBar mProgressbar;

    @ViewById(R.id.toolbar)
    Toolbar mToolbar;

    @ViewById(R.id.tv_title)
    AppCompatTextView tv_title;

    @Override
    public void onAfterViews() {
        initToolbar();
        orderId = getIntent().getIntExtra(AppDelegate.ORDER_ID, 0);
        mRequestQueue = Volley.newRequestQueue(this);
        userInfoSp = MyApplication_.getInstance().getUserInfoSp();
        showView(mProgressbar);
        onBackgrounds();
    }

    private void initToolbar() {
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        tv_title.setText(R.string.title_activity_orderform_details);
    }

    @Override
    public void onBackgrounds() {
        // 获取订单详情
        final String url = MyAPI.getBaseUrl() + "/api/Orders/Order/GetById?id=" + orderId;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, new Response.Listener<org.json.JSONObject>() {
            @Override
            public void onResponse(org.json.JSONObject response) {
                LogUtils.d("\n订单详情-URL:" + url + "\n订单详情-RESPONSE:" + response.toString());

                JSONObject jsonObject = JSON.parseObject(response.toString());
                JSONArray orderItems = jsonObject.getJSONArray("orderItems");
                JSONObject orderExport = jsonObject.getJSONObject("orderExport");

                if (mViewPager != null) {
                    mAdapter = new TableLayoutAdapter(getSupportFragmentManager());
                    if (orderItems.getJSONObject(0).get("isUsed").toString().equals("true")) {
                        LogUtils.d("一般贸易出口通关：true");
                        T_OrderFormDetailsGeneralTradeFragment_ t_orderFormDetailsGeneralTradeFragment_ = new T_OrderFormDetailsGeneralTradeFragment_();
                        Bundle bundle = new Bundle();
//                                <!--通关-->
//                               <string name="orderExport_invoicingCompanyName">开票公司</string>
//                               <string name="consignor">发货人</string>
//                               <string name="consignee">收货人</string>
//                               <string name="orderExport_foreignName">外币付款人</string>
//                              <string name="notifier">通知人</string>
                        bundle.putString("orderExport_invoicingCompanyName", ""); // TODO: 2015/10/23 找不到字段
                        bundle.putString("consignor", orderExport.getString("consignor"));
                        bundle.putString("consignee", "");
                        bundle.putString("orderExport_foreignName", orderExport.getString("foreignName"));
                        bundle.putString("notifier", "");

//                                <!-- 报关基本信息-->
//                                <string name="orderExport_ckCustomsClearanceNo">报关单号</string>
//                                <string name="orderExport_salesOrder">外销发票号</string>
//                                <string name="orderExport_finalDestCountry">贸易国别</string>
//                                <string name="orderExport_priceTerm">价格条款</string>
//                                <string name="orderExport_currency">币别</string>
//                                <string name="orderExport_amount">报关金额</string>
//                                <string name="orderExport_payment1Desc">收汇方式1</string>
//                                <string name="orderExport_payment2Desc">收汇方式2</string>
//                                <string name="orderExport_modeOfTrade">贸易性质</string>
//                                <string name="orderExport_source">境内货源地</string>
//                                <string name="orderExport_completeDeliveryDate">完货日期</string>
//                                <string name="orderExport_invoiceAmount">开票总金额</string>
//                                <string name="order_remark">订单备注</string>
                        bundle.putString("orderExport_ckCustomsClearanceNo", "");  // TODO: 2015/10/23 找不到字段
                        bundle.putString("orderExport_salesOrder", orderExport.getString("salesOrder"));
                        // TODO 这里有Bug,先传个空字符串 这个 贸易国别 返回的是int 型，需要转成 对应的 字符串
//                         int finalDestCountry = orderItems.getJSONObject(0).getJSONObject("curNodeDisplay").getIntValue("finalDestCountry");
                        bundle.putString("orderExport_finalDestCountry", String.valueOf(""));

                        bundle.putString("orderExport_priceTerm", orderExport.getString("priceTerm"));
                        bundle.putString("orderExport_currency", orderExport.getString("currency"));
                        bundle.putString("orderExport_amount", orderExport.getString("amount"));
                        bundle.putString("orderExport_payment1Desc", orderExport.getString("payment1Desc"));
                        bundle.putString("orderExport_payment2Desc", "");
                        bundle.putString("orderExport_modeOfTrade", orderExport.getString("modeOfTrade"));
                        // TODO  这里有Bug,先传个空字符串 境内货源地 返回的是int 型，需要转成 对应的 字符串
//                        int source = orderItems.getJSONObject(0).getJSONObject("curNodeDisplay").getIntValue("source");
                        bundle.putString("orderExport_source", String.valueOf(""));

                        bundle.putString("orderExport_completeDeliveryDate", "");
                        bundle.putString("orderExport_invoiceAmount", orderExport.getString("invoiceAmount"));
                        bundle.putString("order_remark", "");

//                                <!-- 物流信息 -->
//                                <string name="orderExport_modeOfTransportation">运输方式</string>
//                                <string name="orderExport_exportPortEn">出口口岸</string>
//                                <string name="orderExport_transshippmentPortEn">中转口岸</string>
//                                <string name="orderExport_destinationPortEn">目的口岸</string>
//                                <string name="orderExport_shippmentDate">装运期限</string>
//                                <string name="orderExport_shippingSchedule">船期</string>
//                                <string name="orderExport_closingDate">截关日期</string>
//                                <string name="orderExport_grossWeight">总毛重</string>
//                                <string name="orderExport_netWeight">总净重</string>
//                                <string name="orderExport_vol">总体积</string>
//                                <string name="orderExport_frontMark">正唛</string>
//                                <string name="orderExport_sideMark">侧唛</string>
//                                <string name="orderExport_forwarder">货代名称</string>
//                                <string name="orderExport_forwardingContactor">货代联系人</string>
//                                <string name="orderExport_forwardingPhone">货代电话</string>
                        // TODO: 2015/10/23 运输方式，返回的是 0 可能要转成相应字符串，这个目前不知道
                        bundle.putString("orderExport_modeOfTransportation", orderExport.getString("modeOfTransportation"));
                        bundle.putString("orderExport_exportPortEn", ""); // TODO: 2015/10/23 找不到字段
                        bundle.putString("orderExport_transshippmentPortEn", "");
                        // TODO 这个 目的口岸 返回的是int 型，需要转成 对应的 字符串
//                        int destinationPortEn = orderItems.getJSONObject(0).getJSONObject("curNodeDisplay").getIntValue("destinationPortEn");
                        bundle.putString("orderExport_destinationPortEn", String.valueOf(""));
                        bundle.putString("orderExport_shippmentDate", "");
                        bundle.putString("orderExport_shippingSchedule", "");
                        bundle.putString("orderExport_closingDate", "");
                        bundle.putString("orderExport_grossWeight", orderExport.getString("grossWeight"));
                        bundle.putString("orderExport_netWeight", orderExport.getString("netWeight"));
                        bundle.putString("orderExport_vol", orderExport.getString("vol"));
                        bundle.putString("orderExport_frontMark", "");
                        bundle.putString("orderExport_sideMark", "");
                        bundle.putString("orderExport_forwarder", "");
                        bundle.putString("orderExport_forwardingContactor", "");
                        bundle.putString("orderExport_forwardingPhone", "");

                        // 货柜信息 forwardingContainer
                        JSONArray forwardingContainer = jsonObject.getJSONArray("forwardingContainer");
                        bundle.putString("forwardingContainer", forwardingContainer.toString());
                        // 商品信息:合并前
                        JSONArray detailList = orderExport.getJSONArray("detailList");
                        bundle.putString("detailList", detailList.toString());
                        // 商品信息:合并后
                        JSONArray combineDetailList = orderExport.getJSONArray("combineDetailList");
                        bundle.putString("combineDetailList", combineDetailList.toString());
                        // 进度明细
                        JSONArray nodes = orderItems.getJSONObject(0).getJSONArray("nodes");
                        bundle.putString("nodes", nodes.toString());
                        // 附件 attachments
                        JSONArray attachments = orderItems.getJSONObject(0).getJSONArray("attachments");
                        bundle.putString("attachments", attachments.toString());

                        t_orderFormDetailsGeneralTradeFragment_.setArguments(bundle);
                        mAdapter.addFragment(t_orderFormDetailsGeneralTradeFragment_, getStrings(R.string.order_details_general_trade));
                    }
                    if (orderItems.getJSONObject(1).get("isUsed").toString().equals("true")) {
                        LogUtils.d("出口退税服务：true");
                        Bundle bundle = new Bundle();
//                                <!-- 退税 -->
//                                <string name="orderExport_orderExport_tsIsPaymentCost">是否已支付相关费用(海运费、保险费等)</string>
//                                <string name="orderExport_actualCustomsClearanceDate">报关日期</string>
//                                <string name="orderExport_tsMustActualAmount">应退税金额</string>
//                                <string name="orderExport_tsFormalitiesCharges">手续费</string>
//                                <string name="orderExport_tsCustomRebateAmount">客户退税额</string>
//                                <string name="orderExport_tsRebateDeadline">最晚退税日</string>
//                                <string name="orderExport_tsPaymentAmount">已放款金额</string>
//                                <string name="orderExport_tsActualDate">实际退税日</string>
//                                <string name="orderExport_tsActualAmount">实际退税额</string>
                        String orderExport_tsIsPaymentCost = orderExport.getString("orderExport_tsIsPaymentCost");
                        bundle.putString("orderExport_tsIsPaymentCost", "true".equals(orderExport_tsIsPaymentCost) ? "是" : "否");
                        // TODO 返回的是int型 需要转，暂时就这样
                        String actualCustomsClearanceDate = orderItems.getJSONObject(1).getJSONObject("curNodeDisplay").getString("actualCustomsClearanceDate");
                        bundle.putString("orderExport_actualCustomsClearanceDate", actualCustomsClearanceDate);
                        bundle.putString("orderExport_tsMustActualAmount", orderExport.getString("tsMustActualAmount"));
                        bundle.putString("orderExport_tsFormalitiesCharges", orderExport.getString("tsFormalitiesCharges"));
                        bundle.putString("orderExport_tsCustomRebateAmount", orderExport.getString("tsCustomRebateAmount"));
                        bundle.putString("orderExport_tsRebateDeadline", ""); // TODO 字段找不到
                        // TODO 返回的是int型 需要转，暂时就这样
                        String tsPaymentAmount = orderItems.getJSONObject(1).getJSONObject("curNodeDisplay").getString("tsPaymentAmount");
                        bundle.putString("orderExport_tsPaymentAmount", tsPaymentAmount);
                        bundle.putString("orderExport_tsActualDate", "");// TODO 字段找不到
                        bundle.putString("orderExport_tsActualAmount", "");// TODO 字段找不到

                        // 进度明细
                        JSONArray nodes = orderItems.getJSONObject(1).getJSONArray("nodes");
                        bundle.putString("nodes", nodes.toString());
                        // 附件 attachments
                        JSONArray attachments = orderItems.getJSONObject(1).getJSONArray("attachments");
                        bundle.putString("attachments", attachments.toString());

                        T_OrderFormDetalsExportRebatesFragment_ t_orderFormDetalsExportRebatesFragment_ = new T_OrderFormDetalsExportRebatesFragment_();
                        t_orderFormDetalsExportRebatesFragment_.setArguments(bundle);
                        mAdapter.addFragment(t_orderFormDetalsExportRebatesFragment_, getStrings(R.string.order_details_export_rebates));
                    }
                    if (orderItems.getJSONObject(2).get("isUsed").toString().equals("true")) {
                        LogUtils.d("赊销融资：true");
                        Bundle bundle = new Bundle();
//                                 <!-- 基本信息 -->
//                                <string name="order_accountName">用户名称</string>
//                                <string name="order_accountCode">用户编码</string>
//                                <string name="orderExport_sxCustomCreditLimit">信用额度</string>
//                                <string name="orderExport_currency1">报关币别</string>
//                                <string name="orderExport_amount1">报关金额</string>
                        bundle.putString("order_accountName", jsonObject.getString("accountName"));
                        bundle.putString("order_accountCode", jsonObject.getString("accountCode"));
                        bundle.putString("orderExport_sxCustomCreditLimit", orderExport.getString("sxCustomCreditLimit"));
                        bundle.putString("orderExport_currency1", orderExport.getString("currency"));
                        bundle.putString("orderExport_amount1", orderExport.getString("amount"));

//                                <!--投保信息-->
//                                <string name="order_accountName1">外商</string>
//                                <string name="order_accountCode1">信报编码</string>
//                                <string name="orderExport_sxCustomCreditLimit1">中信保剩余额度</string>
//                                <string name="orderExport_sxPaymentType">付款方式</string>
//                                <string name="orderExport_sxSinosureCurrency">投保币别</string>
//                                <string name="orderExport_sxSinosureAmount">投保金额</string>
//                                <string name="orderExport_sxSinosureRate">信保汇率</string>
//                                <string name="orderExport_sxShipmentDate">出运日期</string>
//                                <string name="orderExport_sxFinacingDays">账期(天)</string>
//                                <string name="orderExport_sxIsCustomsConfirm">是否电话确认</string>
                        bundle.putString("order_accountName1", orderExport.getString("accountName"));
                        bundle.putString("order_accountCode1", orderExport.getString("accountCode"));
                        bundle.putString("orderExport_sxCustomCreditLimit1", orderExport.getString("sxCustomCreditLimit"));
                        bundle.putString("orderExport_sxPaymentType", orderExport.getString("sxPaymentType"));
                        bundle.putString("orderExport_sxSinosureCurrency", orderExport.getString("sxSinosureCurrency"));
                        bundle.putString("orderExport_sxSinosureAmount", orderExport.getString("sxSinosureAmount"));
                        bundle.putString("orderExport_sxSinosureRate", orderExport.getString("sxSinosureRate"));
                        // TODO 返回的是int型 需要转，暂时就这样
                        String sxShipmentDate = orderItems.getJSONObject(2).getJSONObject("curNodeDisplay").getString("sxShipmentDate");
                        bundle.putString("orderExport_sxShipmentDate", sxShipmentDate);
                        bundle.putString("orderExport_sxFinacingDays", orderExport.getString("sxFinacingDays"));
                        String sxIsCustomsConfirm = orderExport.getString("sxIsCustomsConfirm");
                        bundle.putString("orderExport_sxIsCustomsConfirm", "true".equals(sxIsCustomsConfirm) ? "是" : "否");

//                                <!--融资信息-->
//                                <string name="orderExport_sxFinancionCreditLimit">融资金额(包含利息和保费等)</string>
//                                <string name="financeLoanAmount">融资放款金额</string>
//                                <string name="orderExport_sxReservationRate">拟定汇率</string>
//                                <string name="orderExport_sxFinancingProportion">融资比例</string>
//                                <string name="orderExport_sxOperatorConfirmFlag">是否和业务员确认</string>
//                                <string name="orderExport_sxReservationInterest">预订利息</string>
//                                <string name="orderExport_sxSinosureCostRate">保费率</string>
//                                <string name="orderExport_sxSinosureTtCost">保费电汇费</string>
//                                <string name="orderExport_sxSinosureCost">保费(包括电汇费)</string>
//                                <string name="orderExport_sxPaymentDate">还款日</string>
//                                <string name="orderExport_sxEndDate">逾期宽限日</string>

                        bundle.putString("orderExport_sxFinancionCreditLimit", orderExport.getString("sxFinancionCreditLimit"));
                        bundle.putString("financeLoanAmount", ""); //TODO 找不到字段
                        bundle.putString("orderExport_sxReservationRate", "");//TODO 找不到字段
                        bundle.putString("orderExport_sxFinancingProportion", "");//TODO 找不到字段
                        String sxOperatorConfirmFlag = orderExport.getString("sxOperatorConfirmFlag");
                        bundle.putString("orderExport_sxOperatorConfirmFlag", "true".equals(sxOperatorConfirmFlag) ? "是" : "否");

                        bundle.putString("orderExport_sxReservationInterest", orderExport.getString("sxReservationInterest"));
                        bundle.putString("orderExport_sxSinosureCostRate", orderExport.getString("sxSinosureCostRate"));
                        bundle.putString("orderExport_sxSinosureTtCost", orderExport.getString("sxSinosureTtCost"));
                        bundle.putString("orderExport_sxSinosureCost", orderExport.getString("sxSinosureCost"));
                        bundle.putString("orderExport_sxPaymentDate", "");//TODO 找不到字段
                        bundle.putString("orderExport_sxEndDate", "");//TODO 找不到字段

//                                <!--还款信息-->
//                                <string name="orderExport_sxActualFkDate">放款日期</string>
//                                <string name="orderExport_sxShipmentDate1">出运日期</string>
//                                <string name="orderExport_sxFinancionCreditLimit1">融资金额</string>
//                                <string name="orderExport_sxActualInterest">最终实际利息</string>
//                                <string name="orderExport_sxActualPaymentAmount">实际还款额</string>
//                                <string name="orderExport_sxSinosureTtCost1">已付利息</string>
//                                <string name="orderExport_sxUnPaymentAmount">未还款额</string>
//                                <string name="orderExport_sxDifferenceAmount">利息差额</string>
                        bundle.putString("orderExport_sxActualFkDate", ""); // TODO 找不到字段
                        // TODO 返回的是int型 需要转，暂时就这样
                        String sxShipmentDate1 = orderItems.getJSONObject(2).getJSONObject("curNodeDisplay").getString("sxShipmentDate");
                        bundle.putString("orderExport_sxShipmentDate1", sxShipmentDate1);

                        bundle.putString("orderExport_sxFinancionCreditLimit1", orderExport.getString("sxFinancionCreditLimit"));
                        bundle.putString("orderExport_sxActualInterest", "");// TODO 找不到字段
                        bundle.putString("orderExport_sxActualPaymentAmount", orderExport.getString("sxActualPaymentAmount"));
                        bundle.putString("orderExport_sxSinosureTtCost1", orderExport.getString("sxSinosureTtCost"));
                        bundle.putString("orderExport_sxUnPaymentAmount", orderExport.getString("sxUnPaymentAmount"));
                        bundle.putString("orderExport_sxDifferenceAmount", "");// TODO 找不到字段

                        // 进度明细
                        JSONArray nodes = orderItems.getJSONObject(2).getJSONArray("nodes");
                        bundle.putString("nodes", nodes.toString());
                        // 附件 attachments
                        JSONArray attachments = orderItems.getJSONObject(2).getJSONArray("attachments");
                        bundle.putString("attachments", attachments.toString());

                        T_OrderFormDetalsCreditFinancingFragment_ t_orderFormDetalsCreditFinancingFragment_ = new T_OrderFormDetalsCreditFinancingFragment_();
                        t_orderFormDetalsCreditFinancingFragment_.setArguments(bundle);
                        mAdapter.addFragment(t_orderFormDetalsCreditFinancingFragment_, getStrings(R.string.order_details_credit_financing));
                    }
                    if (orderItems.getJSONObject(3).get("isUsed").toString().equals("true")) {
                        LogUtils.d("货代订舱：true");
                        Bundle bundle = new Bundle();

                        //        <!-- 相关方信息 -->
                        //        <string name="hd_consignor">发货人</string>
                        //        <string name="hd_consignee">收货人</string>
                        //        <string name="hd_notifier">通知人</string>
                        bundle.putString("hd_consignor", orderExport.getString("consignor"));
                        bundle.putString("hd_consignee", "");// TODO 找不到字段
                        bundle.putString("hd_notifier", "");// TODO 找不到字段

                        //        <!-- 物流信息 -->
                        //        <string name="orderExport_exportPortEn1">起运港</string>
                        //        <string name="orderExport_transshippmentPortEn1">中转港</string>
                        //        <string name="orderExport_destinationPortEn1">目的港</string>
                        //        <string name="orderExport_etd">开航日期</string>
                        //        <string name="orderExport_modeOfTrade1">贸易性质</string>
                        //        <string name="orderExport_freightTerms">运费条款</string>
                        //        <string name="orderExport_isTransport">可否转运</string>
                        //        <string name="orderExport_shippingOrderType">货柜类型</string>
                        //        <string name="orderExport_payment1Desc1">收汇方式</string>
                        //        <string name="orderExport_carrierNameEn">船司</string>
                        //        <string name="orderExport_blNo">提单号</string>
                        //        <string name="orderExport_vesselName">船名</string>
                        //        <string name="orderExport_voyageNo">船次</string>
                        //        <string name="orderExport_forwarder1">货代名称</string>
                        //        <string name="orderExport_forwardingContactor1">联系人</string>
                        //        <string name="orderExport_forwardingPhone1">联系电话</string>
                        //        <string name="orderExport_forwardingAddress">货代地址</string>
                        //        <string name="orderExport_forwardingEmail">货代邮箱</string>
                        //        <string name="orderExport_forwardingFax">货代传真</string>
                        //        <string name="orderExport_frontMark1">标记唛头</string>
                        //        <string name="orderExport_forwardingRemark">备注</string>
                        bundle.putString("orderExport_exportPortEn1", ""); // TODO 找不到字段
                        bundle.putString("orderExport_transshippmentPortEn1", "");// TODO 找不到字段
                        bundle.putString("orderExport_destinationPortEn1", "");// TODO 找不到字段
                        bundle.putString("orderExport_etd", "");// TODO 找不到字段
                        bundle.putString("orderExport_modeOfTrade1", orderExport.getString("modeOfTrade"));
                        bundle.putString("orderExport_freightTerms", orderExport.getString("freightTerms"));
                        bundle.putString("orderExport_isTransport", "");// TODO 找不到字段
                        bundle.putString("orderExport_shippingOrderType", orderExport.getString("shippingOrderType"));
                        bundle.putString("orderExport_payment1Desc1", orderExport.getString("payment1Desc"));
                        bundle.putString("orderExport_carrierNameEn", "");// TODO 找不到字段
                        bundle.putString("orderExport_blNo", "");// TODO 找不到字段
                        bundle.putString("orderExport_vesselName", "");// TODO 找不到字段
                        bundle.putString("orderExport_voyageNo", "");// TODO 找不到字段
                        bundle.putString("orderExport_forwarder1", "");// TODO 找不到字段
                        bundle.putString("orderExport_forwardingContactor1", "");// TODO 找不到字段
                        bundle.putString("orderExport_forwardingPhone1", "");// TODO 找不到字段
                        bundle.putString("orderExport_forwardingAddress", "");// TODO 找不到字段
                        bundle.putString("orderExport_forwardingEmail", "");// TODO 找不到字段
                        bundle.putString("orderExport_forwardingFax", "");// TODO 找不到字段
                        bundle.putString("orderExport_frontMark1", "");// TODO 找不到字段
                        bundle.putString("orderExport_forwardingRemark", "");// TODO 找不到字段

                        // 货代订舱：商品信息
                        JSONArray shippingDetailList = orderExport.getJSONArray("shippingDetailList");
                        bundle.putString("shippingDetailList", shippingDetailList.toString());
                        // 货代订舱：货柜信息 forwardingContainer
                        JSONArray forwardingContainer = jsonObject.getJSONArray("forwardingContainer");
                        bundle.putString("forwardingContainer", forwardingContainer.toString());
                        // 进度明细
                        JSONArray nodes = orderItems.getJSONObject(3).getJSONArray("nodes");
                        bundle.putString("nodes", nodes.toString());
                        // 附件 attachments
                        JSONArray attachments = orderItems.getJSONObject(3).getJSONArray("attachments");
                        bundle.putString("attachments", attachments.toString());

                        T_OrderFormDetailsGoodsGenerationTankFragment_ t_orderFormDetailsGoodsGenerationTankFragment_ = new T_OrderFormDetailsGoodsGenerationTankFragment_();
                        t_orderFormDetailsGoodsGenerationTankFragment_.setArguments(bundle);
                        mAdapter.addFragment(t_orderFormDetailsGoodsGenerationTankFragment_, getStrings(R.string.order_details_goods_generation_tank));
                    }

                    mViewPager.setAdapter(mAdapter);
                    mTabLayout.setupWithViewPager(mViewPager);
                }

                hideView(mProgressbar);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                LogUtils.e("请求错误:" + error.toString());
                hideView(mProgressbar);
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put(AppDelegate.QS_LOGIN, userInfoSp.getString(AppDelegate.LOGIN_NAME, ""));
                return map;
            }
        };

        jsonObjectRequest.setTag(this);
        mRequestQueue.add(jsonObjectRequest);
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
    protected void onDestroy() {
        mRequestQueue.cancelAll(this);
        super.onDestroy();
    }

}