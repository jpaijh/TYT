package com.qskj.tyt.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 开票通知 实体类
 */
public class T_InvoiceNoticeEntity {

    /**
     * pageSize : 10
     * total : 9
     * totalPage : 1
     * rows : [{"accountId":2762801,"settleMode":0,"remark":null,"orderCode":"zm001CNC15007",
     * "settleModeDesc":null,"draweeId":0,"adviceDate":"2015-07-03T09:41:10","currency":null,
     * "modifiedId":0,"amount":356934.29,"id":3064200,"accountCode":"zm001","entrustLetter":null,
     * "rmbRate":0,"createDate":"2015-07-03T09:40:44","draweeName":"慈溪市远景娱乐用品有限公司",
     * "invoiceMode":0,"orderId":3054402,"frontStatus":"生效","status":3,"statusDes":"生效",
     * "creatorId":2748703,"receiptAmount":48630.6,"code":"zm001CNC15007","modifiedDate":null,
     * "declarationNumber":"310120100519979605","accountName":"周敏","creatorName":"谢晓燕","source":0,
     * "receiptAmountRmb":0,"modifiedName":null,"agencyFee":0}]
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
         * accountId : 2762801
         * settleMode : 0
         * remark : null
         * orderCode : zm001CNC15007
         * settleModeDesc : null
         * draweeId : 0
         * adviceDate : 2015-07-03T09:41:10
         * currency : null
         * modifiedId : 0
         * amount : 356934.29
         * id : 3064200
         * accountCode : zm001
         * entrustLetter : null
         * rmbRate : 0
         * createDate : 2015-07-03T09:40:44
         * draweeName : 慈溪市远景娱乐用品有限公司
         * invoiceMode : 0
         * orderId : 3054402
         * frontStatus : 生效
         * status : 3
         * statusDes : 生效
         * creatorId : 2748703
         * receiptAmount : 48630.6
         * code : zm001CNC15007
         * modifiedDate : null
         * declarationNumber : 310120100519979605
         * accountName : 周敏
         * creatorName : 谢晓燕
         * source : 0
         * receiptAmountRmb : 0
         * modifiedName : null
         * agencyFee : 0
         */

        private int accountId; // 账户ID
        private String accountName; // 帐户名称
        private int settleMode; // 结算模式 0:每单多少钱;1:1美金报关金额多少钱
        private Object remark;
        private String orderCode; // 订单编码
        private Object settleModeDesc; // 结算模式说明
        private int draweeId; // 开票单位ID
        private String adviceDate;
        private Object currency; // 币别
        private int modifiedId;
        private double amount; // 总金额
        private int id; // 未知
        private String accountCode; // 客户编码
        private Object entrustLetter;
        private int rmbRate; // RMB汇率
        private String createDate;
        private String draweeName; // 收款单位
        private int invoiceMode; // 开票类型：0：一次开票；1：多次开票
        private int orderId; // 订单ID
        private String frontStatus; // 前台状态值
//        public enum InvoiceStatus
//        {
//            新制 = 0,
//            提交=1,
//            受理=2,
//            生效 = 3,
//            退回=4,
//        }
        private int status; // 状态

        private String statusDes;
        private int creatorId;
        private double receiptAmount; // 收汇金额
        private String code; // 开票通知单号
        private Object modifiedDate;
        private String declarationNumber;
        private String creatorName;
        private int source; // 来源；0：后台，1：前台
        private int receiptAmountRmb;
        private Object modifiedName;
        private int agencyFee; // 代理费

        public void setAccountId(int accountId) {
            this.accountId = accountId;
        }

        public void setSettleMode(int settleMode) {
            this.settleMode = settleMode;
        }

        public void setRemark(Object remark) {
            this.remark = remark;
        }

        public void setOrderCode(String orderCode) {
            this.orderCode = orderCode;
        }

        public void setSettleModeDesc(Object settleModeDesc) {
            this.settleModeDesc = settleModeDesc;
        }

        public void setDraweeId(int draweeId) {
            this.draweeId = draweeId;
        }

        public void setAdviceDate(String adviceDate) {
            this.adviceDate = adviceDate;
        }

        public void setCurrency(Object currency) {
            this.currency = currency;
        }

        public void setModifiedId(int modifiedId) {
            this.modifiedId = modifiedId;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setAccountCode(String accountCode) {
            this.accountCode = accountCode;
        }

        public void setEntrustLetter(Object entrustLetter) {
            this.entrustLetter = entrustLetter;
        }

        public void setRmbRate(int rmbRate) {
            this.rmbRate = rmbRate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public void setDraweeName(String draweeName) {
            this.draweeName = draweeName;
        }

        public void setInvoiceMode(int invoiceMode) {
            this.invoiceMode = invoiceMode;
        }

        public void setOrderId(int orderId) {
            this.orderId = orderId;
        }

        public void setFrontStatus(String frontStatus) {
            this.frontStatus = frontStatus;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public void setStatusDes(String statusDes) {
            this.statusDes = statusDes;
        }

        public void setCreatorId(int creatorId) {
            this.creatorId = creatorId;
        }

        public void setReceiptAmount(double receiptAmount) {
            this.receiptAmount = receiptAmount;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public void setModifiedDate(Object modifiedDate) {
            this.modifiedDate = modifiedDate;
        }

        public void setDeclarationNumber(String declarationNumber) {
            this.declarationNumber = declarationNumber;
        }

        public void setAccountName(String accountName) {
            this.accountName = accountName;
        }

        public void setCreatorName(String creatorName) {
            this.creatorName = creatorName;
        }

        public void setSource(int source) {
            this.source = source;
        }

        public void setReceiptAmountRmb(int receiptAmountRmb) {
            this.receiptAmountRmb = receiptAmountRmb;
        }

        public void setModifiedName(Object modifiedName) {
            this.modifiedName = modifiedName;
        }

        public void setAgencyFee(int agencyFee) {
            this.agencyFee = agencyFee;
        }

        public int getAccountId() {
            return accountId;
        }

        public int getSettleMode() {
            return settleMode;
        }

        public Object getRemark() {
            return remark;
        }

        public String getOrderCode() {
            return orderCode;
        }

        public Object getSettleModeDesc() {
            return settleModeDesc;
        }

        public int getDraweeId() {
            return draweeId;
        }

        public String getAdviceDate() {
            return adviceDate;
        }

        public Object getCurrency() {
            return currency;
        }

        public int getModifiedId() {
            return modifiedId;
        }

        public double getAmount() {
            return amount;
        }

        public int getId() {
            return id;
        }

        public String getAccountCode() {
            return accountCode;
        }

        public Object getEntrustLetter() {
            return entrustLetter;
        }

        public int getRmbRate() {
            return rmbRate;
        }

        public String getCreateDate() {
            return createDate;
        }

        public String getDraweeName() {
            return draweeName;
        }

        public int getInvoiceMode() {
            return invoiceMode;
        }

        public int getOrderId() {
            return orderId;
        }

        public String getFrontStatus() {
            return frontStatus;
        }

        public int getStatus() {
            return status;
        }

        public String getStatusDes() {
            return statusDes;
        }

        public int getCreatorId() {
            return creatorId;
        }

        public double getReceiptAmount() {
            return receiptAmount;
        }

        public String getCode() {
            return code;
        }

        public Object getModifiedDate() {
            return modifiedDate;
        }

        public String getDeclarationNumber() {
            return declarationNumber;
        }

        public String getAccountName() {
            return accountName;
        }

        public String getCreatorName() {
            return creatorName;
        }

        public int getSource() {
            return source;
        }

        public int getReceiptAmountRmb() {
            return receiptAmountRmb;
        }

        public Object getModifiedName() {
            return modifiedName;
        }

        public int getAgencyFee() {
            return agencyFee;
        }
    }
}
