package com.qskj.tyt.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 通知收汇 实体类
 */
public class T_NoticeForeignExchangeEntity {

    /**
     * pageSize : 10
     * total : 2
     * totalPage : 1
     * rows : [{"accountId":2762801,"customerRemark":"445","receiptType":140,"foreignName":"3232","remark":null,"status":0,
     * "modifierName":null,"filePath":null,"saleOrder":"5454","sendTime":null,"creatorId":2763403,"modifyDate":null,"currency":"GBP",
     * "fileId":null,"id":3096000,"amount":32,"receiptBank":"54","receiptDate":"2015-09-22T00:00:00","accountName":"zm001",
     * "modifierId":null,"creatorName":"zm001","fileName":"","createDate":"2015-09-15T16:17:19"}]
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
         * customerRemark : 445
         * receiptType : 140
         * foreignName : 3232
         * remark : null
         * status : 0
         * modifierName : null
         * filePath : null
         * saleOrder : 5454
         * sendTime : null
         * creatorId : 2763403
         * modifyDate : null
         * currency : GBP
         * fileId : null
         * id : 3096000
         * amount : 32
         * receiptBank : 54
         * receiptDate : 2015-09-22T00:00:00
         * accountName : zm001
         * modifierId : null
         * creatorName : zm001
         * fileName :
         * createDate : 2015-09-15T16:17:19
         */

        private int accountId;
        private String customerRemark; // 自定义备注
        private int receiptType; // 收汇类型
        private String receiptBank;// 收汇银行
        private String receiptDate; // 收汇日期
        private String accountName; // 账户
        private String foreignName; // 外商
        private String currency; // 币别
        private double amount; // 金额
        private String remark;
        private int status; // 状态
        private Object modifierName;
        private String fileName; // 文件名
        private Object filePath; // 文件路径
        private String saleOrder;
        private Object sendTime;
        private int creatorId;
        private Object modifyDate;
        private Object fileId;
        private int id;
        private Object modifierId;
        private String creatorName;
        private String createDate;

        public void setAccountId(int accountId) {
            this.accountId = accountId;
        }

        public void setCustomerRemark(String customerRemark) {
            this.customerRemark = customerRemark;
        }

        public void setReceiptType(int receiptType) {
            this.receiptType = receiptType;
        }

        public void setForeignName(String foreignName) {
            this.foreignName = foreignName;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public void setModifierName(Object modifierName) {
            this.modifierName = modifierName;
        }

        public void setFilePath(Object filePath) {
            this.filePath = filePath;
        }

        public void setSaleOrder(String saleOrder) {
            this.saleOrder = saleOrder;
        }

        public void setSendTime(Object sendTime) {
            this.sendTime = sendTime;
        }

        public void setCreatorId(int creatorId) {
            this.creatorId = creatorId;
        }

        public void setModifyDate(Object modifyDate) {
            this.modifyDate = modifyDate;
        }

        public void setCurrency(String currency) {
            this.currency = currency;
        }

        public void setFileId(Object fileId) {
            this.fileId = fileId;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }

        public void setReceiptBank(String receiptBank) {
            this.receiptBank = receiptBank;
        }

        public void setReceiptDate(String receiptDate) {
            this.receiptDate = receiptDate;
        }

        public void setAccountName(String accountName) {
            this.accountName = accountName;
        }

        public void setModifierId(Object modifierId) {
            this.modifierId = modifierId;
        }

        public void setCreatorName(String creatorName) {
            this.creatorName = creatorName;
        }

        public void setFileName(String fileName) {
            this.fileName = fileName;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public int getAccountId() {
            return accountId;
        }

        public String getCustomerRemark() {
            return customerRemark;
        }

        public int getReceiptType() {
            return receiptType;
        }

        public String getForeignName() {
            return foreignName;
        }

        public String getRemark() {
            return remark;
        }

        public int getStatus() {
            return status;
        }

        public Object getModifierName() {
            return modifierName;
        }

        public Object getFilePath() {
            return filePath;
        }

        public String getSaleOrder() {
            return saleOrder;
        }

        public Object getSendTime() {
            return sendTime;
        }

        public int getCreatorId() {
            return creatorId;
        }

        public Object getModifyDate() {
            return modifyDate;
        }

        public String getCurrency() {
            return currency;
        }

        public Object getFileId() {
            return fileId;
        }

        public int getId() {
            return id;
        }

        public double getAmount() {
            return amount;
        }

        public String getReceiptBank() {
            return receiptBank;
        }

        public String getReceiptDate() {
            return receiptDate;
        }

        public String getAccountName() {
            return accountName;
        }

        public Object getModifierId() {
            return modifierId;
        }

        public String getCreatorName() {
            return creatorName;
        }

        public String getFileName() {
            return fileName;
        }

        public String getCreateDate() {
            return createDate;
        }
    }
}
