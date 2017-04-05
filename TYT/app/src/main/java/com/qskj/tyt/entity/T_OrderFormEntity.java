package com.qskj.tyt.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 我的订单 实体类
 */
public class T_OrderFormEntity {

    /**
     * pageSize : 10
     * total : 66
     * totalPage : 7
     * rows : [{"completedInvoice":false,"salesName":"冯佳慧","remark":null,"reason":null,"handoverStatus":null,
     * "modifyDate":"2015-09-08T10:42:48","amount":1000,"accountCode":"zm001","shippingSchedule":null,"customerViewed":true,
     * "logisiticsClerkId":null,"accountUserName":"zm001","usdRate":null,"completedPayment":false,"salesOrder":"zm001CNC15014",
     * "accountEmail":"65223521@qq.com","orderType":1,"status":2,"titleName":"QS INTERNATIONAL TRADING GROUP CO.,LTD",
     * "matchType":"未匹配","code":"201508211107130001","accountContactor":"周敏","settleRemark":null,"isMarketPruchaseModel":false,
     * "accountUserId":2763403,"customerPurchaseOrder":null,"clerkId":2748700,"systemViewed":true,"settleFlag":false,"titleId":1569600,
     * "accountId":2762801,"logisiticsClerkName":null,"submitTime":"2015-08-21T11:07:13","accountContactPhone":"","usdAmount":null,
     * "currency":"USD","statusFormatCn":"进行中","id":3093103,"goodsInspection":null,"commodityDescCn":null,"closingDate":null,
     * "tsIsInvoiceMatch":false,"createDate":"2015-08-21T11:07:13","isPltReceipt":true,"accountContactQq":"","servicesName":null,
     * "foreignName":null,"clerkName":"卢慧","creatorId":2744501,"orderItemName":"出口代理,退税融资","accountContactSkype":null,
     * "salesId":2751700,"accountName":"周敏","creatorName":"超级管理员","acceptTime":"2015-08-21T11:07:14",
     * "accountContactMobilePhone":"13656881787","financeFlag":false,"rebateStatus":null,"completeTime":null,"completedExchange":false}]
     */

    private int pageSize;
    private int total;
    private int totalPage;
    private List<RowsEntity> rows;

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public void setRows(List<RowsEntity> rows) {
        this.rows = rows;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getTotal() {
        return total;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public List<RowsEntity> getRows() {
        return rows;
    }

    public static class RowsEntity implements Serializable {
        /**
         * completedInvoice : false
         * salesName : 冯佳慧
         * remark : null
         * reason : null
         * handoverStatus : null
         * modifyDate : 2015-09-08T10:42:48
         * amount : 1000
         * accountCode : zm001
         * shippingSchedule : null
         * customerViewed : true
         * logisiticsClerkId : null
         * accountUserName : zm001
         * usdRate : null
         * completedPayment : false
         * salesOrder : zm001CNC15014
         * accountEmail : 65223521@qq.com
         * orderType : 1
         * status : 2
         * titleName : QS INTERNATIONAL TRADING GROUP CO.,LTD
         * matchType : 未匹配
         * code : 201508211107130001
         * accountContactor : 周敏
         * settleRemark : null
         * isMarketPruchaseModel : false
         * accountUserId : 2763403
         * customerPurchaseOrder : null
         * clerkId : 2748700
         * systemViewed : true
         * settleFlag : false
         * titleId : 1569600
         * accountId : 2762801
         * logisiticsClerkName : null
         * submitTime : 2015-08-21T11:07:13
         * accountContactPhone :
         * usdAmount : null
         * currency : USD
         * statusFormatCn : 进行中
         * id : 3093103
         * goodsInspection : null
         * commodityDescCn : null
         * closingDate : null
         * tsIsInvoiceMatch : false
         * createDate : 2015-08-21T11:07:13
         * isPltReceipt : true
         * accountContactQq :
         * servicesName : null
         * foreignName : null
         * clerkName : 卢慧
         * creatorId : 2744501
         * orderItemName : 出口代理,退税融资
         * accountContactSkype : null
         * salesId : 2751700
         * accountName : 周敏
         * creatorName : 超级管理员
         * acceptTime : 2015-08-21T11:07:14
         * accountContactMobilePhone : 13656881787
         * financeFlag : false
         * rebateStatus : null
         * completeTime : null
         * completedExchange : false
         */

        private int clerkId; // 客服ID
        private int salesId; // 业务员ID
        private String salesName; // 业务员名
        private String salesOrder;
        private String accountCode;
        private int accountId; //客户ID
        private String accountName; // 客户名称
        private String accountEmail; //  客户邮箱
        private int accountUserId; //客户关联用户ID
        private String accountUserName; //客户关联用户名称
        private String accountContactor; //客户联系人
        private String accountContactMobilePhone;//客户联系电话
        private String accountContactQq; // 客户联系QQ
        private Object accountContactSkype; // 客户网络电话
        private String accountContactPhone; // 客户联系电话
        private Object remark; // 备注
        private Object reason; // 退回原因
        private Object handoverStatus;
        private String modifyDate;
        private double amount;
        private Object shippingSchedule;
        private boolean customerViewed; // 客户是否查看
        private boolean systemViewed; // 后台是否查看
        private Object logisiticsClerkId;
        private Object usdRate;
        private int orderType; // 服务类型
        private int status; //状态值;0:草稿; 1:受理中; 2:进行中; 3:已完成; -1取消
        private String titleName; // 抬头名称
        private String matchType;
        private String code; // 服务订单号
        private boolean isMarketPruchaseModel; // 是否是市场采购模式订单
        private String customerPurchaseOrder;
        private String clerkName; // 客服名
        private Object settleRemark; // 结算备注
        private boolean settleFlag; // 结算标志
        private int titleId; // 抬头
        private Object logisiticsClerkName;
        private Object usdAmount;
        private String currency;
        private String statusFormatCn; // 状态中文
        private int id;
        private Object goodsInspection;
        private Object commodityDescCn;
        private Object closingDate;
        private boolean tsIsInvoiceMatch;
        private Object servicesName;
        private String foreignName;
        private int creatorId;
        private String orderItemName;
        private String creatorName;
        private String createDate; //创建时间
        private String submitTime; //提交时间
        private String acceptTime; // 受理时间
        private Object completeTime; //完成时间
        private boolean financeFlag; // 财务是否入账
        private Object rebateStatus; // 退税状态
        private boolean isPltReceipt; // 是否平台收汇
        private boolean completedInvoice; // 是否开票完
        private boolean completedExchange; // 是否收汇完
        private boolean completedPayment; // 是否付款完

        public void setCompletedInvoice(boolean completedInvoice) {
            this.completedInvoice = completedInvoice;
        }

        public void setSalesName(String salesName) {
            this.salesName = salesName;
        }

        public void setRemark(Object remark) {
            this.remark = remark;
        }

        public void setReason(Object reason) {
            this.reason = reason;
        }

        public void setHandoverStatus(Object handoverStatus) {
            this.handoverStatus = handoverStatus;
        }

        public void setModifyDate(String modifyDate) {
            this.modifyDate = modifyDate;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }

        public void setAccountCode(String accountCode) {
            this.accountCode = accountCode;
        }

        public void setShippingSchedule(Object shippingSchedule) {
            this.shippingSchedule = shippingSchedule;
        }

        public void setCustomerViewed(boolean customerViewed) {
            this.customerViewed = customerViewed;
        }

        public void setLogisiticsClerkId(Object logisiticsClerkId) {
            this.logisiticsClerkId = logisiticsClerkId;
        }

        public void setAccountUserName(String accountUserName) {
            this.accountUserName = accountUserName;
        }

        public void setUsdRate(Object usdRate) {
            this.usdRate = usdRate;
        }

        public void setCompletedPayment(boolean completedPayment) {
            this.completedPayment = completedPayment;
        }

        public void setSalesOrder(String salesOrder) {
            this.salesOrder = salesOrder;
        }

        public void setAccountEmail(String accountEmail) {
            this.accountEmail = accountEmail;
        }

        public void setOrderType(int orderType) {
            this.orderType = orderType;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public void setTitleName(String titleName) {
            this.titleName = titleName;
        }

        public void setMatchType(String matchType) {
            this.matchType = matchType;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public void setAccountContactor(String accountContactor) {
            this.accountContactor = accountContactor;
        }

        public void setSettleRemark(Object settleRemark) {
            this.settleRemark = settleRemark;
        }

        public void setIsMarketPruchaseModel(boolean isMarketPruchaseModel) {
            this.isMarketPruchaseModel = isMarketPruchaseModel;
        }

        public void setAccountUserId(int accountUserId) {
            this.accountUserId = accountUserId;
        }

        public void setCustomerPurchaseOrder(String customerPurchaseOrder) {
            this.customerPurchaseOrder = customerPurchaseOrder;
        }

        public void setClerkId(int clerkId) {
            this.clerkId = clerkId;
        }

        public void setSystemViewed(boolean systemViewed) {
            this.systemViewed = systemViewed;
        }

        public void setSettleFlag(boolean settleFlag) {
            this.settleFlag = settleFlag;
        }

        public void setTitleId(int titleId) {
            this.titleId = titleId;
        }

        public void setAccountId(int accountId) {
            this.accountId = accountId;
        }

        public void setLogisiticsClerkName(Object logisiticsClerkName) {
            this.logisiticsClerkName = logisiticsClerkName;
        }

        public void setSubmitTime(String submitTime) {
            this.submitTime = submitTime;
        }

        public void setAccountContactPhone(String accountContactPhone) {
            this.accountContactPhone = accountContactPhone;
        }

        public void setUsdAmount(Object usdAmount) {
            this.usdAmount = usdAmount;
        }

        public void setCurrency(String currency) {
            this.currency = currency;
        }

        public void setStatusFormatCn(String statusFormatCn) {
            this.statusFormatCn = statusFormatCn;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setGoodsInspection(Object goodsInspection) {
            this.goodsInspection = goodsInspection;
        }

        public void setCommodityDescCn(Object commodityDescCn) {
            this.commodityDescCn = commodityDescCn;
        }

        public void setClosingDate(Object closingDate) {
            this.closingDate = closingDate;
        }

        public void setTsIsInvoiceMatch(boolean tsIsInvoiceMatch) {
            this.tsIsInvoiceMatch = tsIsInvoiceMatch;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public void setIsPltReceipt(boolean isPltReceipt) {
            this.isPltReceipt = isPltReceipt;
        }

        public void setAccountContactQq(String accountContactQq) {
            this.accountContactQq = accountContactQq;
        }

        public void setServicesName(Object servicesName) {
            this.servicesName = servicesName;
        }

        public void setForeignName(String foreignName) {
            this.foreignName = foreignName;
        }

        public void setClerkName(String clerkName) {
            this.clerkName = clerkName;
        }

        public void setCreatorId(int creatorId) {
            this.creatorId = creatorId;
        }

        public void setOrderItemName(String orderItemName) {
            this.orderItemName = orderItemName;
        }

        public void setAccountContactSkype(Object accountContactSkype) {
            this.accountContactSkype = accountContactSkype;
        }

        public void setSalesId(int salesId) {
            this.salesId = salesId;
        }

        public void setAccountName(String accountName) {
            this.accountName = accountName;
        }

        public void setCreatorName(String creatorName) {
            this.creatorName = creatorName;
        }

        public void setAcceptTime(String acceptTime) {
            this.acceptTime = acceptTime;
        }

        public void setAccountContactMobilePhone(String accountContactMobilePhone) {
            this.accountContactMobilePhone = accountContactMobilePhone;
        }

        public void setFinanceFlag(boolean financeFlag) {
            this.financeFlag = financeFlag;
        }

        public void setRebateStatus(Object rebateStatus) {
            this.rebateStatus = rebateStatus;
        }

        public void setCompleteTime(Object completeTime) {
            this.completeTime = completeTime;
        }

        public void setCompletedExchange(boolean completedExchange) {
            this.completedExchange = completedExchange;
        }

        public boolean getCompletedInvoice() {
            return completedInvoice;
        }

        public String getSalesName() {
            return salesName;
        }

        public Object getRemark() {
            return remark;
        }

        public Object getReason() {
            return reason;
        }

        public Object getHandoverStatus() {
            return handoverStatus;
        }

        public String getModifyDate() {
            return modifyDate;
        }

        public double getAmount() {
            return amount;
        }

        public String getAccountCode() {
            return accountCode;
        }

        public Object getShippingSchedule() {
            return shippingSchedule;
        }

        public boolean getCustomerViewed() {
            return customerViewed;
        }

        public Object getLogisiticsClerkId() {
            return logisiticsClerkId;
        }

        public String getAccountUserName() {
            return accountUserName;
        }

        public Object getUsdRate() {
            return usdRate;
        }

        public boolean getCompletedPayment() {
            return completedPayment;
        }

        public String getSalesOrder() {
            return salesOrder;
        }

        public String getAccountEmail() {
            return accountEmail;
        }

        public int getOrderType() {
            return orderType;
        }

        public int getStatus() {
            return status;
        }

        public String getTitleName() {
            return titleName;
        }

        public String getMatchType() {
            return matchType;
        }

        public String getCode() {
            return code;
        }

        public String getAccountContactor() {
            return accountContactor;
        }

        public Object getSettleRemark() {
            return settleRemark;
        }

        public boolean getIsMarketPruchaseModel() {
            return isMarketPruchaseModel;
        }

        public int getAccountUserId() {
            return accountUserId;
        }

        public String getCustomerPurchaseOrder() {
            return customerPurchaseOrder;
        }

        public int getClerkId() {
            return clerkId;
        }

        public boolean getSystemViewed() {
            return systemViewed;
        }

        public boolean getSettleFlag() {
            return settleFlag;
        }

        public int getTitleId() {
            return titleId;
        }

        public int getAccountId() {
            return accountId;
        }

        public Object getLogisiticsClerkName() {
            return logisiticsClerkName;
        }

        public String getSubmitTime() {
            return submitTime;
        }

        public String getAccountContactPhone() {
            return accountContactPhone;
        }

        public Object getUsdAmount() {
            return usdAmount;
        }

        public String getCurrency() {
            return currency;
        }

        public String getStatusFormatCn() {
            return statusFormatCn;
        }

        public int getId() {
            return id;
        }

        public Object getGoodsInspection() {
            return goodsInspection;
        }

        public Object getCommodityDescCn() {
            return commodityDescCn;
        }

        public Object getClosingDate() {
            return closingDate;
        }

        public boolean getTsIsInvoiceMatch() {
            return tsIsInvoiceMatch;
        }

        public String getCreateDate() {
            return createDate;
        }

        public boolean getIsPltReceipt() {
            return isPltReceipt;
        }

        public String getAccountContactQq() {
            return accountContactQq;
        }

        public Object getServicesName() {
            return servicesName;
        }

        public String getForeignName() {
            return foreignName;
        }

        public String getClerkName() {
            return clerkName;
        }

        public int getCreatorId() {
            return creatorId;
        }

        public String getOrderItemName() {
            return orderItemName;
        }

        public Object getAccountContactSkype() {
            return accountContactSkype;
        }

        public int getSalesId() {
            return salesId;
        }

        public String getAccountName() {
            return accountName;
        }

        public String getCreatorName() {
            return creatorName;
        }

        public String getAcceptTime() {
            return acceptTime;
        }

        public String getAccountContactMobilePhone() {
            return accountContactMobilePhone;
        }

        public boolean getFinanceFlag() {
            return financeFlag;
        }

        public Object getRebateStatus() {
            return rebateStatus;
        }

        public Object getCompleteTime() {
            return completeTime;
        }

        public boolean getCompletedExchange() {
            return completedExchange;
        }
    }
}
