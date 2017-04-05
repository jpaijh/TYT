package com.qskj.tyt.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 普通账户，资金账户 实体类
 */
public class T_AccountFundDetailsEntity {

    /**
     * total : 32
     * totalPage : 4
     * pageIndex : 1
     * pageSize : 10
     * rows : [{"id":3090154,"accountId":2762801,"fundAccountName":1,"desc":"USD 500.00",
     * "currency":null,"amount":400,"relatedCompanyId":null,"isOrderExp":false,
     * "creatorId":0,"creatorName":null,"createDate":1439912320000,"status":0,
     * "orderId":null,"orderCode":null,"itemType":"收汇-分摊",
     * "itemTypeId":1,"type":null,"relatedCompanyName":"HAIKE INTERNATIONAL CO.,LTD",
     * "originalCurrency":"USD","originalAmount":0,"originalAmount1":500,"originalAmount2":100,
     * "originalRmbRate":6.1901,"sourceType":null,"sourceCode":"","serviceItemType":null,"balance":2476.04,
     * "occurDate":1419868800000,"fundName":"收汇-分摊","amountRmb":2476.04,"amount1":3095.05,"amount2":619.01,
     * "orderSettleFlag":false,"subStatus":0,"costType":null}]
     */

    private int total;       // 该用户数据总条目数
    private int totalPage; // 总页数
    private int pageIndex; // 当前页数
    private int pageSize; // 每页条目数
    private List<RowsEntity> rows;

    public void setTotal(int total) {
        this.total = total;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setRows(List<RowsEntity> rows) {
        this.rows = rows;
    }

    public int getTotal() {
        return total;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public List<RowsEntity> getRows() {
        return rows;
    }

    public static class RowsEntity implements Serializable {
        /**
         * id : 3090154
         * accountId : 2762801
         * fundAccountName : 1
         * desc : USD 500.00
         * currency : null
         * amount : 400.0
         * relatedCompanyId : null
         * isOrderExp : false
         * creatorId : 0
         * creatorName : null
         * createDate : 1439912320000
         * status : 0
         * orderId : null
         * orderCode : null
         * itemType : 收汇-分摊
         * itemTypeId : 1
         * type : null
         * relatedCompanyName : HAIKE INTERNATIONAL CO.,LTD
         * originalCurrency : USD
         * originalAmount : 0.0
         * originalAmount1 : 500.0
         * originalAmount2 : 100.0
         * originalRmbRate : 6.1901
         * sourceType : null
         * sourceCode :
         * serviceItemType : null
         * balance : 2476.04
         * occurDate : 1419868800000
         * fundName : 收汇-分摊
         * amountRmb : 2476.04
         * amount1 : 3095.05
         * amount2 : 619.01
         * orderSettleFlag : false
         * subStatus : 0
         * costType : null
         */

        private int id;
        private int accountId;
        private int fundAccountName;
        private String desc;
        private Object currency;
        private double amount;
        private Object relatedCompanyId;
        private boolean isOrderExp;
        private int creatorId;
        private Object creatorName;
        private String createDate;
        private int status;
        private Object orderId;
        private Object orderCode;
        private String itemType;
        private int itemTypeId;
        private Object type;
        private String relatedCompanyName;
        private String originalCurrency;
        private double originalAmount;
        private double originalAmount1;
        private double originalAmount2;
        private double originalRmbRate;
        private Object sourceType;
        private String sourceCode;
        private Object serviceItemType;
        private double balance;
        private long occurDate;
        private String fundName;
        private double amountRmb;
        private double amount1;
        private double amount2;
        private boolean orderSettleFlag;
        private int subStatus;
        private Object costType;

        public void setId(int id) {
            this.id = id;
        }

        public void setAccountId(int accountId) {
            this.accountId = accountId;
        }

        public void setFundAccountName(int fundAccountName) {
            this.fundAccountName = fundAccountName;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public void setCurrency(Object currency) {
            this.currency = currency;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }

        public void setRelatedCompanyId(Object relatedCompanyId) {
            this.relatedCompanyId = relatedCompanyId;
        }

        public void setIsOrderExp(boolean isOrderExp) {
            this.isOrderExp = isOrderExp;
        }

        public void setCreatorId(int creatorId) {
            this.creatorId = creatorId;
        }

        public void setCreatorName(Object creatorName) {
            this.creatorName = creatorName;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public void setOrderId(Object orderId) {
            this.orderId = orderId;
        }

        public void setOrderCode(Object orderCode) {
            this.orderCode = orderCode;
        }

        public void setItemType(String itemType) {
            this.itemType = itemType;
        }

        public void setItemTypeId(int itemTypeId) {
            this.itemTypeId = itemTypeId;
        }

        public void setType(Object type) {
            this.type = type;
        }

        public void setRelatedCompanyName(String relatedCompanyName) {
            this.relatedCompanyName = relatedCompanyName;
        }

        public void setOriginalCurrency(String originalCurrency) {
            this.originalCurrency = originalCurrency;
        }

        public void setOriginalAmount(double originalAmount) {
            this.originalAmount = originalAmount;
        }

        public void setOriginalAmount1(double originalAmount1) {
            this.originalAmount1 = originalAmount1;
        }

        public void setOriginalAmount2(double originalAmount2) {
            this.originalAmount2 = originalAmount2;
        }

        public void setOriginalRmbRate(double originalRmbRate) {
            this.originalRmbRate = originalRmbRate;
        }

        public void setSourceType(Object sourceType) {
            this.sourceType = sourceType;
        }

        public void setSourceCode(String sourceCode) {
            this.sourceCode = sourceCode;
        }

        public void setServiceItemType(Object serviceItemType) {
            this.serviceItemType = serviceItemType;
        }

        public void setBalance(double balance) {
            this.balance = balance;
        }

        public void setOccurDate(long occurDate) {
            this.occurDate = occurDate;
        }

        public void setFundName(String fundName) {
            this.fundName = fundName;
        }

        public void setAmountRmb(double amountRmb) {
            this.amountRmb = amountRmb;
        }

        public void setAmount1(double amount1) {
            this.amount1 = amount1;
        }

        public void setAmount2(double amount2) {
            this.amount2 = amount2;
        }

        public void setOrderSettleFlag(boolean orderSettleFlag) {
            this.orderSettleFlag = orderSettleFlag;
        }

        public void setSubStatus(int subStatus) {
            this.subStatus = subStatus;
        }

        public void setCostType(Object costType) {
            this.costType = costType;
        }

        public int getId() {
            return id;
        }

        public int getAccountId() {
            return accountId;
        }

        public int getFundAccountName() {
            return fundAccountName;
        }

        public String getDesc() {
            return desc;
        }

        public Object getCurrency() {
            return currency;
        }

        public double getAmount() {
            return amount;
        }

        public Object getRelatedCompanyId() {
            return relatedCompanyId;
        }

        public boolean getIsOrderExp() {
            return isOrderExp;
        }

        public int getCreatorId() {
            return creatorId;
        }

        public Object getCreatorName() {
            return creatorName;
        }

        public String getCreateDate() {
            return createDate;
        }

        public int getStatus() {
            return status;
        }

        public Object getOrderId() {
            return orderId;
        }

        public Object getOrderCode() {
            return orderCode;
        }

        public String getItemType() {
            return itemType;
        }

        public int getItemTypeId() {
            return itemTypeId;
        }

        public Object getType() {
            return type;
        }

        public String getRelatedCompanyName() {
            return relatedCompanyName;
        }

        public String getOriginalCurrency() {
            return originalCurrency;
        }

        public double getOriginalAmount() {
            return originalAmount;
        }

        public double getOriginalAmount1() {
            return originalAmount1;
        }

        public double getOriginalAmount2() {
            return originalAmount2;
        }

        public double getOriginalRmbRate() {
            return originalRmbRate;
        }

        public Object getSourceType() {
            return sourceType;
        }

        public String getSourceCode() {
            return sourceCode;
        }

        public Object getServiceItemType() {
            return serviceItemType;
        }

        public double getBalance() {
            return balance;
        }

        public long getOccurDate() {
            return occurDate;
        }

        public String getFundName() {
            return fundName;
        }

        public double getAmountRmb() {
            return amountRmb;
        }

        public double getAmount1() {
            return amount1;
        }

        public double getAmount2() {
            return amount2;
        }

        public boolean getOrderSettleFlag() {
            return orderSettleFlag;
        }

        public int getSubStatus() {
            return subStatus;
        }

        public Object getCostType() {
            return costType;
        }
    }
}
