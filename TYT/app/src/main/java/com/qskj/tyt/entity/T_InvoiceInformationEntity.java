package com.qskj.tyt.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 发票信息 实体类
 * /api/Funds/VatRegister/FindVatInvoicePreList
 */
public class T_InvoiceInformationEntity {

    /**
     * pageSize : 10
     * total : 16
     * totalPage : 2
     * rows : [{"invoiceDate":"2015-03-06T00:00:00","accountId":null,"orderCode":"201504280936150002",
     * "remark":null,"isMatched":true,"refId":null,"financeReceive":false,"type":0,"amount":151400,
     * "id":2869800,"details":null,"customerCode":"zm001","invoiceFlag":false,"rebateAmount":0,
     * "refCode":null,"createDate":"2015-04-29T10:19:01","orderId":2857902,"draweerName":null,
     * "sourceType":0,"status":2,"saleOrder":"A772CNC15003","creatorId":2748702,"code":"330014214004045490",
     * "confirmDate":null,"isInit":null,"accountName":null,"creatorName":"王敏","draweerId":null,
     * "financeFlag":false,"deadline":null,"verifyStatus":0},{"invoiceDate":"2015-04-03T00:00:00",
     * "accountId":null,"orderCode":"201504210907170014","remark":null,"isMatched":true,"refId":null,
     * "financeReceive":false,"type":0,"amount":32632.6,"id":2879104,"details":null,"customerCode":"zm001",
     * "invoiceFlag":false,"rebateAmount":0,"refCode":null,"createDate":"2015-05-04T14:04:38","orderId":2834702,
     * "draweerName":null,"sourceType":0,"status":2,"saleOrder":"A586CNC1502","creatorId":2748702,"code":"330214214006286645",
     * "confirmDate":null,"isInit":null,"accountName":null,"creatorName":"王敏","draweerId":null,"financeFlag":false,
     * "deadline":null,"verifyStatus":0}]
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

    public static class RowsEntity implements Serializable{
        /**
         * invoiceDate : 2015-03-06T00:00:00
         * accountId : null
         * orderCode : 201504280936150002
         * remark : null
         * isMatched : true
         * refId : null
         * financeReceive : false
         * type : 0
         * amount : 151400
         * id : 2869800
         * details : null
         * customerCode : zm001
         * invoiceFlag : false
         * rebateAmount : 0
         * refCode : null
         * createDate : 2015-04-29T10:19:01
         * orderId : 2857902
         * draweerName : null
         * sourceType : 0
         * status : 2
         * saleOrder : A772CNC15003
         * creatorId : 2748702
         * code : 330014214004045490
         * confirmDate : null
         * isInit : null
         * accountName : null
         * creatorName : 王敏
         * draweerId : null
         * financeFlag : false
         * deadline : null
         * verifyStatus : 0
         */

        private String invoiceDate;
        private Object accountId;
        private String orderCode;
        private Object remark;
        private boolean isMatched;
        private Object refId;
        private boolean financeReceive;
        private int type;
        private int amount;
        private int id;
        private Object details;
        private String customerCode;
        private boolean invoiceFlag;
        private int rebateAmount;
        private Object refCode;
        private String createDate;
        private int orderId;
        private Object draweerName;
        private int sourceType;
        private int status;
        private String saleOrder;
        private int creatorId;
        private String code;
        private Object confirmDate;
        private Object isInit;
        private Object accountName;
        private String creatorName;
        private Object draweerId;
        private boolean financeFlag;
        private Object deadline;
        private int verifyStatus;

        public void setInvoiceDate(String invoiceDate) {
            this.invoiceDate = invoiceDate;
        }

        public void setAccountId(Object accountId) {
            this.accountId = accountId;
        }

        public void setOrderCode(String orderCode) {
            this.orderCode = orderCode;
        }

        public void setRemark(Object remark) {
            this.remark = remark;
        }

        public void setIsMatched(boolean isMatched) {
            this.isMatched = isMatched;
        }

        public void setRefId(Object refId) {
            this.refId = refId;
        }

        public void setFinanceReceive(boolean financeReceive) {
            this.financeReceive = financeReceive;
        }

        public void setType(int type) {
            this.type = type;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setDetails(Object details) {
            this.details = details;
        }

        public void setCustomerCode(String customerCode) {
            this.customerCode = customerCode;
        }

        public void setInvoiceFlag(boolean invoiceFlag) {
            this.invoiceFlag = invoiceFlag;
        }

        public void setRebateAmount(int rebateAmount) {
            this.rebateAmount = rebateAmount;
        }

        public void setRefCode(Object refCode) {
            this.refCode = refCode;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public void setOrderId(int orderId) {
            this.orderId = orderId;
        }

        public void setDraweerName(Object draweerName) {
            this.draweerName = draweerName;
        }

        public void setSourceType(int sourceType) {
            this.sourceType = sourceType;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public void setSaleOrder(String saleOrder) {
            this.saleOrder = saleOrder;
        }

        public void setCreatorId(int creatorId) {
            this.creatorId = creatorId;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public void setConfirmDate(Object confirmDate) {
            this.confirmDate = confirmDate;
        }

        public void setIsInit(Object isInit) {
            this.isInit = isInit;
        }

        public void setAccountName(Object accountName) {
            this.accountName = accountName;
        }

        public void setCreatorName(String creatorName) {
            this.creatorName = creatorName;
        }

        public void setDraweerId(Object draweerId) {
            this.draweerId = draweerId;
        }

        public void setFinanceFlag(boolean financeFlag) {
            this.financeFlag = financeFlag;
        }

        public void setDeadline(Object deadline) {
            this.deadline = deadline;
        }

        public void setVerifyStatus(int verifyStatus) {
            this.verifyStatus = verifyStatus;
        }

        public String getInvoiceDate() {
            return invoiceDate;
        }

        public Object getAccountId() {
            return accountId;
        }

        public String getOrderCode() {
            return orderCode;
        }

        public Object getRemark() {
            return remark;
        }

        public boolean getIsMatched() {
            return isMatched;
        }

        public Object getRefId() {
            return refId;
        }

        public boolean getFinanceReceive() {
            return financeReceive;
        }

        public int getType() {
            return type;
        }

        public int getAmount() {
            return amount;
        }

        public int getId() {
            return id;
        }

        public Object getDetails() {
            return details;
        }

        public String getCustomerCode() {
            return customerCode;
        }

        public boolean getInvoiceFlag() {
            return invoiceFlag;
        }

        public int getRebateAmount() {
            return rebateAmount;
        }

        public Object getRefCode() {
            return refCode;
        }

        public String getCreateDate() {
            return createDate;
        }

        public int getOrderId() {
            return orderId;
        }

        public Object getDraweerName() {
            return draweerName;
        }

        public int getSourceType() {
            return sourceType;
        }

        public int getStatus() {
            return status;
        }

        public String getSaleOrder() {
            return saleOrder;
        }

        public int getCreatorId() {
            return creatorId;
        }

        public String getCode() {
            return code;
        }

        public Object getConfirmDate() {
            return confirmDate;
        }

        public Object getIsInit() {
            return isInit;
        }

        public Object getAccountName() {
            return accountName;
        }

        public String getCreatorName() {
            return creatorName;
        }

        public Object getDraweerId() {
            return draweerId;
        }

        public boolean getFinanceFlag() {
            return financeFlag;
        }

        public Object getDeadline() {
            return deadline;
        }

        public int getVerifyStatus() {
            return verifyStatus;
        }
    }
}
