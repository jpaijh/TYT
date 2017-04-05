package com.qskj.tyt.entity;

import java.util.List;

/**
 * 开票通知-详情 实体
 */
public class T_InvoiceNoticeDetailsEntity {

    /**
     * amount : 2009961.9
     * code : 15D22607
     * orderId : 2958826
     * fileId : 0
     * fileName : null
     * filePath : null
     * orderCode : 15D22607
     * receiptAmount : 278880.0
     * receiptAmountRmb : 0.0
     * receiptCurrency : USD
     * invoiceMode : 0
     * settleMode : 0
     * agencyFee : 0.0
     * rebateFee : 0.0
     * rebateAmount : 0.0
     * rebateRate : 0.0
     * id : 2961109
     * status : 3
     * costAmount : 22310.4
     * details : [{"id":2961214,"detailId":2960426,"amount":0,"exchangeCosts":6.16,"orderCommodityCode":"8712008900","orderCommodityName":"自行车","orderCommodityUnit":"辆","orderCommodityVatRate":0.17,"orderCommodityQuantity":3320,"orderCommodityPrice":605.41021,"orderCommodityAmount":2009961.9,"orderCommodityPriceWov":517.44462,"orderCommodityAmountWov":1717916.15,"orderCommodityRebateRate":0.17,"orderCommodityVatAmount":292045.75,"orderCommoditySaleAmount":278880,"customQuantity":3320,"noticeQuantity":0,"vatQuantiy":0,"settleRate":0,"specification":null,"isFirst":true}]
     * receiptInfos : [{"id":2967365,"currency":"USD","amount":28800,"receiptDate":"2015-09-14T00:00:00","orderId":2958826,"orderCode":"15D22607","invoiceCode":null,"invoiceDate":null,"invoiceAmount":0,"receiptAmount":0,"rmbAmount":178678.08,"rmbRate":6.2041,"usdAmount":28800,"usdRate":1,"netAmount":28800,"foreignBankFee":0,"orderReceiptCompleted":false,"foreignName":"BHD"},{"id":2967371,"currency":"USD","amount":7065,"receiptDate":"2015-09-14T00:00:00","orderId":2958826,"orderCode":"15D22607","invoiceCode":null,"invoiceDate":null,"invoiceAmount":0,"receiptAmount":0,"rmbAmount":43816.42,"rmbRate":6.2019,"usdAmount":7065,"usdRate":1,"netAmount":7065,"foreignBankFee":0,"orderReceiptCompleted":false,"foreignName":"BHD"},{"id":2967377,"currency":"USD","amount":157678.75,"receiptDate":"2015-09-14T00:00:00","orderId":2958826,"orderCode":"15D22607","invoiceCode":null,"invoiceDate":null,"invoiceAmount":0,"receiptAmount":0,"rmbAmount":977781.7,"rmbRate":6.2011,"usdAmount":157678.75,"usdRate":1,"netAmount":157678.75,"foreignBankFee":0,"orderReceiptCompleted":false,"foreignName":"BHD"},{"id":2967383,"currency":"USD","amount":8519610,"receiptDate":"2015-09-14T00:00:00","orderId":2958826,"orderCode":"15D22607","invoiceCode":null,"invoiceDate":null,"invoiceAmount":0,"receiptAmount":0,"rmbAmount":5.287951535E7,"rmbRate":6.2068,"usdAmount":8519610,"usdRate":1,"netAmount":8519610,"foreignBankFee":0,"orderReceiptCompleted":false,"foreignName":"BHD"},{"id":2967387,"currency":"USD","amount":-8519610,"receiptDate":"2015-09-14T00:00:00","orderId":2958826,"orderCode":"15D22607","invoiceCode":null,"invoiceDate":null,"invoiceAmount":0,"receiptAmount":0,"rmbAmount":-5.287951535E7,"rmbRate":6.2068,"usdAmount":-8519610,"usdRate":1,"netAmount":-8519610,"foreignBankFee":0,"orderReceiptCompleted":false,"foreignName":"BHD"},{"id":2967395,"currency":"USD","amount":85196.1,"receiptDate":"2015-09-14T00:00:00","orderId":2958826,"orderCode":"15D22607","invoiceCode":null,"invoiceDate":null,"invoiceAmount":0,"receiptAmount":0,"rmbAmount":528795.15,"rmbRate":6.2068,"usdAmount":85196.1,"usdRate":1,"netAmount":85196.1,"foreignBankFee":0,"orderReceiptCompleted":false,"foreignName":"BHD"},{"id":2968409,"currency":"USD","amount":-28800,"receiptDate":"2015-09-14T00:00:00","orderId":2958826,"orderCode":"15D22607","invoiceCode":null,"invoiceDate":null,"invoiceAmount":0,"receiptAmount":0,"rmbAmount":-178678.08,"rmbRate":6.2041,"usdAmount":-28800,"usdRate":1,"netAmount":-28800,"foreignBankFee":0,"orderReceiptCompleted":false,"foreignName":"BHD"}]
     * costInfos : [{"id":0,"orderCode":null,"ownerId":null,"smdServiceId":null,"smdServiceName":null,"accountId":null,"accountName":null,"accountCode":null,"costName":"代理费","costId":null,"currency":"RMB","amount":11155.2,"usdRate":0,"rmbRate":1,"usdAmount":0,"rmbAmount":11155.2,"status":0,"statusDes":null,"serviceVendorId":null,"serviceVendorName":"天津市利雅得工贸有限公司","paymentType":1,"invoiceNoticeFlag":false,"occurDate":"2015-09-14T14:41:26","filePath":null,"fileId":null,"fileName":null,"remark":null,"type":0,"desc":"扣除代理费:11,155.20","financeFlag":false,"settleFlag":false,"titleId":null,"titleName":null,"redFlag":false,"salesOrder":null,"settleNode":0,"isSystem":false,"fundAccountName":null},{"id":0,"orderCode":null,"ownerId":null,"smdServiceId":null,"smdServiceName":null,"accountId":null,"accountName":null,"accountCode":null,"costName":"退税融资手续费","costId":null,"currency":"RMB","amount":11155.2,"usdRate":0,"rmbRate":1,"usdAmount":0,"rmbAmount":11155.2,"status":0,"statusDes":null,"serviceVendorId":null,"serviceVendorName":"天津市利雅得工贸有限公司","paymentType":1,"invoiceNoticeFlag":false,"occurDate":"2015-09-14T14:41:26","filePath":null,"fileId":null,"fileName":null,"remark":null,"type":0,"desc":"扣除退税融资手续费:11,155.20","financeFlag":false,"settleFlag":false,"titleId":null,"titleName":null,"redFlag":false,"salesOrder":null,"settleNode":0,"isSystem":false,"fundAccountName":null}]
     * agencyPays : [{"id":0,"orderCode":null,"ownerId":null,"smdServiceId":null,"smdServiceName":null,"accountId":null,"accountName":null,"accountCode":null,"costName":"代理费","costId":null,"currency":"RMB","amount":11155.2,"usdRate":0,"rmbRate":1,"usdAmount":0,"rmbAmount":11155.2,"status":0,"statusDes":null,"serviceVendorId":null,"serviceVendorName":"天津市利雅得工贸有限公司","paymentType":1,"invoiceNoticeFlag":false,"occurDate":"2015-09-14T14:41:26","filePath":null,"fileId":null,"fileName":null,"remark":null,"type":0,"desc":"扣除代理费:11,155.20","financeFlag":false,"settleFlag":false,"titleId":null,"titleName":null,"redFlag":false,"salesOrder":null,"settleNode":0,"isSystem":false,"fundAccountName":null},{"id":0,"orderCode":null,"ownerId":null,"smdServiceId":null,"smdServiceName":null,"accountId":null,"accountName":null,"accountCode":null,"costName":"退税融资手续费","costId":null,"currency":"RMB","amount":11155.2,"usdRate":0,"rmbRate":1,"usdAmount":0,"rmbAmount":11155.2,"status":0,"statusDes":null,"serviceVendorId":null,"serviceVendorName":"天津市利雅得工贸有限公司","paymentType":1,"invoiceNoticeFlag":false,"occurDate":"2015-09-14T14:41:26","filePath":null,"fileId":null,"fileName":null,"remark":null,"type":0,"desc":"扣除退税融资手续费:11,155.20","financeFlag":false,"settleFlag":false,"titleId":null,"titleName":null,"redFlag":false,"salesOrder":null,"settleNode":0,"isSystem":false,"fundAccountName":null}]
     * accountName : 天津市利雅得工贸有限公司
     * accountId : 2962321
     * accountCode : 600
     * draweeId : 2959626
     * draweeName : 天津市利雅得工贸有限公司
     * orderAmount : 278880.0
     * orderCurrency : USD
     * settleFormula : null
     * adviceDate : 2015-09-14T14:32:41
     * predictRmbAmont : 0.0
     * predictAmont : 0.0
     * predictCost : 0.0
     * rmbRate : 0.0
     * titleId : 1569600
     * titleName : 天津自贸通外贸服务股份有限公司
     * titleNameCn : null
     * taxCode : zjwc001
     * address : 天津市和平区云南路3号白玫瑰大厦
     * phone : 4000622621
     * bank : null
     * bankAccount : null
     * bankAddress : null
     * settleType : 0
     * sourceType : 0
     * source : 0
     * remark : null
     * detailsFrom : null
     */

    private double amount; //开票总金额
    private String code;
    private int orderId;
    private int fileId;
    private Object fileName;
    private Object filePath;
    private String orderCode;
    private double receiptAmount; //收汇总金额
    private double receiptAmountRmb; //收汇总金额
    private String receiptCurrency; //收汇币别
    private int invoiceMode; //开票类型：0：一次开票；1：多次开票
    private String settleMode;//结算模式
    private double agencyFee;//代理费
    private double rebateFee;//退税融资手续费
    private double rebateAmount;//退税金额
    private double rebateRate;//退税手续lv
    private int id;
    private int status;  //状态值
    private double costAmount; //费用总金额
    private String accountName;
    private int accountId;
    private String accountCode; //客户编码
    private int draweeId; //开票单位ID
    private String draweeName; //开票单位名称
    private double orderAmount; //订单报关金额
    private String orderCurrency; //订单币别
    private Object settleFormula; //结算公式
    private String adviceDate; //通知时间
    private double predictRmbAmont;//预计收汇折RMB金额
    private double predictAmont;//预计收汇外币金额[币别为报关币别]
    private double predictCost;//预计费用
    private double rmbRate;
    private int titleId; //抬头ID
    private String titleName;//抬头Name
    private Object titleNameCn;//抬头Name
    private String taxCode; //税号
    private String address; //中瑞地址
    private String phone;//联系电话
    private Object bank; //开户行账号
    private Object bankAccount; //开户行账号
    private Object bankAddress;//开户行地址
    private int settleType;
    private int sourceType;
    private int source;
    private Object remark; //备注 add by YangRenZhi
    private Object detailsFrom; //1、从预录单中来
    private List<DetailsEntity> details;
    private List<ReceiptInfosEntity> receiptInfos;
    private List<CostInfosEntity> costInfos;
    private List<AgencyPaysEntity> agencyPays;

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public void setFileId(int fileId) {
        this.fileId = fileId;
    }

    public void setFileName(Object fileName) {
        this.fileName = fileName;
    }

    public void setFilePath(Object filePath) {
        this.filePath = filePath;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public void setReceiptAmount(double receiptAmount) {
        this.receiptAmount = receiptAmount;
    }

    public void setReceiptAmountRmb(double receiptAmountRmb) {
        this.receiptAmountRmb = receiptAmountRmb;
    }

    public void setReceiptCurrency(String receiptCurrency) {
        this.receiptCurrency = receiptCurrency;
    }

    public void setInvoiceMode(int invoiceMode) {
        this.invoiceMode = invoiceMode;
    }

    public void setSettleMode(String settleMode) {
        this.settleMode = settleMode;
    }

    public void setAgencyFee(double agencyFee) {
        this.agencyFee = agencyFee;
    }

    public void setRebateFee(double rebateFee) {
        this.rebateFee = rebateFee;
    }

    public void setRebateAmount(double rebateAmount) {
        this.rebateAmount = rebateAmount;
    }

    public void setRebateRate(double rebateRate) {
        this.rebateRate = rebateRate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setCostAmount(double costAmount) {
        this.costAmount = costAmount;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public void setAccountCode(String accountCode) {
        this.accountCode = accountCode;
    }

    public void setDraweeId(int draweeId) {
        this.draweeId = draweeId;
    }

    public void setDraweeName(String draweeName) {
        this.draweeName = draweeName;
    }

    public void setOrderAmount(double orderAmount) {
        this.orderAmount = orderAmount;
    }

    public void setOrderCurrency(String orderCurrency) {
        this.orderCurrency = orderCurrency;
    }

    public void setSettleFormula(Object settleFormula) {
        this.settleFormula = settleFormula;
    }

    public void setAdviceDate(String adviceDate) {
        this.adviceDate = adviceDate;
    }

    public void setPredictRmbAmont(double predictRmbAmont) {
        this.predictRmbAmont = predictRmbAmont;
    }

    public void setPredictAmont(double predictAmont) {
        this.predictAmont = predictAmont;
    }

    public void setPredictCost(double predictCost) {
        this.predictCost = predictCost;
    }

    public void setRmbRate(double rmbRate) {
        this.rmbRate = rmbRate;
    }

    public void setTitleId(int titleId) {
        this.titleId = titleId;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }

    public void setTitleNameCn(Object titleNameCn) {
        this.titleNameCn = titleNameCn;
    }

    public void setTaxCode(String taxCode) {
        this.taxCode = taxCode;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setBank(Object bank) {
        this.bank = bank;
    }

    public void setBankAccount(Object bankAccount) {
        this.bankAccount = bankAccount;
    }

    public void setBankAddress(Object bankAddress) {
        this.bankAddress = bankAddress;
    }

    public void setSettleType(int settleType) {
        this.settleType = settleType;
    }

    public void setSourceType(int sourceType) {
        this.sourceType = sourceType;
    }

    public void setSource(int source) {
        this.source = source;
    }

    public void setRemark(Object remark) {
        this.remark = remark;
    }

    public void setDetailsFrom(Object detailsFrom) {
        this.detailsFrom = detailsFrom;
    }

    public void setDetails(List<DetailsEntity> details) {
        this.details = details;
    }

    public void setReceiptInfos(List<ReceiptInfosEntity> receiptInfos) {
        this.receiptInfos = receiptInfos;
    }

    public void setCostInfos(List<CostInfosEntity> costInfos) {
        this.costInfos = costInfos;
    }

    public void setAgencyPays(List<AgencyPaysEntity> agencyPays) {
        this.agencyPays = agencyPays;
    }

    public double getAmount() {
        return amount;
    }

    public String getCode() {
        return code;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getFileId() {
        return fileId;
    }

    public Object getFileName() {
        return fileName;
    }

    public Object getFilePath() {
        return filePath;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public double getReceiptAmount() {
        return receiptAmount;
    }

    public double getReceiptAmountRmb() {
        return receiptAmountRmb;
    }

    public String getReceiptCurrency() {
        return receiptCurrency;
    }

    public int getInvoiceMode() {
        return invoiceMode;
    }

    public String getSettleMode() {
        return settleMode;
    }

    public double getAgencyFee() {
        return agencyFee;
    }

    public double getRebateFee() {
        return rebateFee;
    }

    public double getRebateAmount() {
        return rebateAmount;
    }

    public double getRebateRate() {
        return rebateRate;
    }

    public int getId() {
        return id;
    }

    public int getStatus() {
        return status;
    }

    public double getCostAmount() {
        return costAmount;
    }

    public String getAccountName() {
        return accountName;
    }

    public int getAccountId() {
        return accountId;
    }

    public String getAccountCode() {
        return accountCode;
    }

    public int getDraweeId() {
        return draweeId;
    }

    public String getDraweeName() {
        return draweeName;
    }

    public double getOrderAmount() {
        return orderAmount;
    }

    public String getOrderCurrency() {
        return orderCurrency;
    }

    public Object getSettleFormula() {
        return settleFormula;
    }

    public String getAdviceDate() {
        return adviceDate;
    }

    public double getPredictRmbAmont() {
        return predictRmbAmont;
    }

    public double getPredictAmont() {
        return predictAmont;
    }

    public double getPredictCost() {
        return predictCost;
    }

    public double getRmbRate() {
        return rmbRate;
    }

    public int getTitleId() {
        return titleId;
    }

    public String getTitleName() {
        return titleName;
    }

    public Object getTitleNameCn() {
        return titleNameCn;
    }

    public String getTaxCode() {
        return taxCode;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public Object getBank() {
        return bank;
    }

    public Object getBankAccount() {
        return bankAccount;
    }

    public Object getBankAddress() {
        return bankAddress;
    }

    public int getSettleType() {
        return settleType;
    }

    public int getSourceType() {
        return sourceType;
    }

    public int getSource() {
        return source;
    }

    public Object getRemark() {
        return remark;
    }

    public Object getDetailsFrom() {
        return detailsFrom;
    }

    public List<DetailsEntity> getDetails() {
        return details;
    }

    public List<ReceiptInfosEntity> getReceiptInfos() {
        return receiptInfos;
    }

    public List<CostInfosEntity> getCostInfos() {
        return costInfos;
    }

    public List<AgencyPaysEntity> getAgencyPays() {
        return agencyPays;
    }

    public static class DetailsEntity {
        /**
         * id : 2961214
         * detailId : 2960426
         * amount : 0.0
         * exchangeCosts : 6.16
         * orderCommodityCode : 8712008900
         * orderCommodityName : 自行车
         * orderCommodityUnit : 辆
         * orderCommodityVatRate : 0.17
         * orderCommodityQuantity : 3320.0
         * orderCommodityPrice : 605.41021
         * orderCommodityAmount : 2009961.9
         * orderCommodityPriceWov : 517.44462
         * orderCommodityAmountWov : 1717916.15
         * orderCommodityRebateRate : 0.17
         * orderCommodityVatAmount : 292045.75
         * orderCommoditySaleAmount : 278880.0
         * customQuantity : 3320.0
         * noticeQuantity : 0.0
         * vatQuantiy : 0.0
         * settleRate : 0.0
         * specification : null
         * isFirst : true
         */

        private int id;
        private int detailId;
        private double amount;
        private double exchangeCosts;
        private String orderCommodityCode;
        private String orderCommodityName; // 品名
        private String orderCommodityUnit; // 单位
        private Object specification; // 规格
        private double orderCommodityVatRate;
        private double orderCommodityQuantity; // 数量
        private double orderCommodityPrice;
        private double orderCommodityAmount;
        private double orderCommodityPriceWov; // 单价
        private double orderCommodityAmountWov; // 不含税金额
        private double orderCommodityRebateRate;// 税率
        private double orderCommodityVatAmount; // 税额
        private double orderCommoditySaleAmount;
        private double customQuantity;
        private double noticeQuantity;
        private double vatQuantiy;
        private double settleRate;
        private boolean isFirst;

        public void setId(int id) {
            this.id = id;
        }

        public void setDetailId(int detailId) {
            this.detailId = detailId;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }

        public void setExchangeCosts(double exchangeCosts) {
            this.exchangeCosts = exchangeCosts;
        }

        public void setOrderCommodityCode(String orderCommodityCode) {
            this.orderCommodityCode = orderCommodityCode;
        }

        public void setOrderCommodityName(String orderCommodityName) {
            this.orderCommodityName = orderCommodityName;
        }

        public void setOrderCommodityUnit(String orderCommodityUnit) {
            this.orderCommodityUnit = orderCommodityUnit;
        }

        public void setOrderCommodityVatRate(double orderCommodityVatRate) {
            this.orderCommodityVatRate = orderCommodityVatRate;
        }

        public void setOrderCommodityQuantity(double orderCommodityQuantity) {
            this.orderCommodityQuantity = orderCommodityQuantity;
        }

        public void setOrderCommodityPrice(double orderCommodityPrice) {
            this.orderCommodityPrice = orderCommodityPrice;
        }

        public void setOrderCommodityAmount(double orderCommodityAmount) {
            this.orderCommodityAmount = orderCommodityAmount;
        }

        public void setOrderCommodityPriceWov(double orderCommodityPriceWov) {
            this.orderCommodityPriceWov = orderCommodityPriceWov;
        }

        public void setOrderCommodityAmountWov(double orderCommodityAmountWov) {
            this.orderCommodityAmountWov = orderCommodityAmountWov;
        }

        public void setOrderCommodityRebateRate(double orderCommodityRebateRate) {
            this.orderCommodityRebateRate = orderCommodityRebateRate;
        }

        public void setOrderCommodityVatAmount(double orderCommodityVatAmount) {
            this.orderCommodityVatAmount = orderCommodityVatAmount;
        }

        public void setOrderCommoditySaleAmount(double orderCommoditySaleAmount) {
            this.orderCommoditySaleAmount = orderCommoditySaleAmount;
        }

        public void setCustomQuantity(double customQuantity) {
            this.customQuantity = customQuantity;
        }

        public void setNoticeQuantity(double noticeQuantity) {
            this.noticeQuantity = noticeQuantity;
        }

        public void setVatQuantiy(double vatQuantiy) {
            this.vatQuantiy = vatQuantiy;
        }

        public void setSettleRate(double settleRate) {
            this.settleRate = settleRate;
        }

        public void setSpecification(Object specification) {
            this.specification = specification;
        }

        public void setIsFirst(boolean isFirst) {
            this.isFirst = isFirst;
        }

        public int getId() {
            return id;
        }

        public int getDetailId() {
            return detailId;
        }

        public double getAmount() {
            return amount;
        }

        public double getExchangeCosts() {
            return exchangeCosts;
        }

        public String getOrderCommodityCode() {
            return orderCommodityCode;
        }

        public String getOrderCommodityName() {
            return orderCommodityName;
        }

        public String getOrderCommodityUnit() {
            return orderCommodityUnit;
        }

        public double getOrderCommodityVatRate() {
            return orderCommodityVatRate;
        }

        public double getOrderCommodityQuantity() {
            return orderCommodityQuantity;
        }

        public double getOrderCommodityPrice() {
            return orderCommodityPrice;
        }

        public double getOrderCommodityAmount() {
            return orderCommodityAmount;
        }

        public double getOrderCommodityPriceWov() {
            return orderCommodityPriceWov;
        }

        public double getOrderCommodityAmountWov() {
            return orderCommodityAmountWov;
        }

        public double getOrderCommodityRebateRate() {
            return orderCommodityRebateRate;
        }

        public double getOrderCommodityVatAmount() {
            return orderCommodityVatAmount;
        }

        public double getOrderCommoditySaleAmount() {
            return orderCommoditySaleAmount;
        }

        public double getCustomQuantity() {
            return customQuantity;
        }

        public double getNoticeQuantity() {
            return noticeQuantity;
        }

        public double getVatQuantiy() {
            return vatQuantiy;
        }

        public double getSettleRate() {
            return settleRate;
        }

        public Object getSpecification() {
            return specification;
        }

        public boolean getIsFirst() {
            return isFirst;
        }
    }

    public static class ReceiptInfosEntity {
        /**
         * id : 2967365
         * currency : USD
         * amount : 28800.0
         * receiptDate : 2015-09-14T00:00:00
         * orderId : 2958826
         * orderCode : 15D22607
         * invoiceCode : null
         * invoiceDate : null
         * invoiceAmount : 0.0
         * receiptAmount : 0.0
         * rmbAmount : 178678.08
         * rmbRate : 6.2041
         * usdAmount : 28800.0
         * usdRate : 1.0
         * netAmount : 28800.0
         * foreignBankFee : 0.0
         * orderReceiptCompleted : false
         * foreignName : BHD
         */

        private int id;
        private String currency;
        private double amount;
        private String receiptDate;
        private int orderId;
        private String orderCode;
        private Object invoiceCode;
        private Object invoiceDate;
        private double invoiceAmount;
        private double receiptAmount;
        private double rmbAmount;
        private double rmbRate;
        private double usdAmount;
        private double usdRate;
        private double netAmount;
        private double foreignBankFee;
        private boolean orderReceiptCompleted;
        private String foreignName;

        public void setId(int id) {
            this.id = id;
        }

        public void setCurrency(String currency) {
            this.currency = currency;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }

        public void setReceiptDate(String receiptDate) {
            this.receiptDate = receiptDate;
        }

        public void setOrderId(int orderId) {
            this.orderId = orderId;
        }

        public void setOrderCode(String orderCode) {
            this.orderCode = orderCode;
        }

        public void setInvoiceCode(Object invoiceCode) {
            this.invoiceCode = invoiceCode;
        }

        public void setInvoiceDate(Object invoiceDate) {
            this.invoiceDate = invoiceDate;
        }

        public void setInvoiceAmount(double invoiceAmount) {
            this.invoiceAmount = invoiceAmount;
        }

        public void setReceiptAmount(double receiptAmount) {
            this.receiptAmount = receiptAmount;
        }

        public void setRmbAmount(double rmbAmount) {
            this.rmbAmount = rmbAmount;
        }

        public void setRmbRate(double rmbRate) {
            this.rmbRate = rmbRate;
        }

        public void setUsdAmount(double usdAmount) {
            this.usdAmount = usdAmount;
        }

        public void setUsdRate(double usdRate) {
            this.usdRate = usdRate;
        }

        public void setNetAmount(double netAmount) {
            this.netAmount = netAmount;
        }

        public void setForeignBankFee(double foreignBankFee) {
            this.foreignBankFee = foreignBankFee;
        }

        public void setOrderReceiptCompleted(boolean orderReceiptCompleted) {
            this.orderReceiptCompleted = orderReceiptCompleted;
        }

        public void setForeignName(String foreignName) {
            this.foreignName = foreignName;
        }

        public int getId() {
            return id;
        }

        public String getCurrency() {
            return currency;
        }

        public double getAmount() {
            return amount;
        }

        public String getReceiptDate() {
            return receiptDate;
        }

        public int getOrderId() {
            return orderId;
        }

        public String getOrderCode() {
            return orderCode;
        }

        public Object getInvoiceCode() {
            return invoiceCode;
        }

        public Object getInvoiceDate() {
            return invoiceDate;
        }

        public double getInvoiceAmount() {
            return invoiceAmount;
        }

        public double getReceiptAmount() {
            return receiptAmount;
        }

        public double getRmbAmount() {
            return rmbAmount;
        }

        public double getRmbRate() {
            return rmbRate;
        }

        public double getUsdAmount() {
            return usdAmount;
        }

        public double getUsdRate() {
            return usdRate;
        }

        public double getNetAmount() {
            return netAmount;
        }

        public double getForeignBankFee() {
            return foreignBankFee;
        }

        public boolean getOrderReceiptCompleted() {
            return orderReceiptCompleted;
        }

        public String getForeignName() {
            return foreignName;
        }
    }

    public static class CostInfosEntity {
        /**
         * id : 0
         * orderCode : null
         * ownerId : null
         * smdServiceId : null
         * smdServiceName : null
         * accountId : null
         * accountName : null
         * accountCode : null
         * costName : 代理费
         * costId : null
         * currency : RMB
         * amount : 11155.2
         * usdRate : 0.0
         * rmbRate : 1.0
         * usdAmount : 0.0
         * rmbAmount : 11155.2
         * status : 0
         * statusDes : null
         * serviceVendorId : null
         * serviceVendorName : 天津市利雅得工贸有限公司
         * paymentType : 1
         * invoiceNoticeFlag : false
         * occurDate : 2015-09-14T14:41:26
         * filePath : null
         * fileId : null
         * fileName : null
         * remark : null
         * type : 0
         * desc : 扣除代理费:11,155.20
         * financeFlag : false
         * settleFlag : false
         * titleId : null
         * titleName : null
         * redFlag : false
         * salesOrder : null
         * settleNode : 0
         * isSystem : false
         * fundAccountName : null
         */

        private int id;
        private Object orderCode;
        private Object ownerId;
        private Object smdServiceId;
        private Object smdServiceName;
        private Object accountId;
        private Object accountName;
        private Object accountCode;
        private String costName;
        private Object costId;
        private String currency;
        private double amount;
        private double usdRate;
        private double rmbRate;
        private double usdAmount;
        private double rmbAmount;
        private int status;
        private Object statusDes;
        private Object serviceVendorId;
        private String serviceVendorName;
        private int paymentType;
        private boolean invoiceNoticeFlag;
        private String occurDate;
        private Object filePath;
        private Object fileId;
        private Object fileName;
        private Object remark;
        private int type;
        private String desc;
        private boolean financeFlag;
        private boolean settleFlag;
        private Object titleId;
        private Object titleName;
        private boolean redFlag;
        private Object salesOrder;
        private int settleNode;
        private boolean isSystem;
        private Object fundAccountName;

        public void setId(int id) {
            this.id = id;
        }

        public void setOrderCode(Object orderCode) {
            this.orderCode = orderCode;
        }

        public void setOwnerId(Object ownerId) {
            this.ownerId = ownerId;
        }

        public void setSmdServiceId(Object smdServiceId) {
            this.smdServiceId = smdServiceId;
        }

        public void setSmdServiceName(Object smdServiceName) {
            this.smdServiceName = smdServiceName;
        }

        public void setAccountId(Object accountId) {
            this.accountId = accountId;
        }

        public void setAccountName(Object accountName) {
            this.accountName = accountName;
        }

        public void setAccountCode(Object accountCode) {
            this.accountCode = accountCode;
        }

        public void setCostName(String costName) {
            this.costName = costName;
        }

        public void setCostId(Object costId) {
            this.costId = costId;
        }

        public void setCurrency(String currency) {
            this.currency = currency;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }

        public void setUsdRate(double usdRate) {
            this.usdRate = usdRate;
        }

        public void setRmbRate(double rmbRate) {
            this.rmbRate = rmbRate;
        }

        public void setUsdAmount(double usdAmount) {
            this.usdAmount = usdAmount;
        }

        public void setRmbAmount(double rmbAmount) {
            this.rmbAmount = rmbAmount;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public void setStatusDes(Object statusDes) {
            this.statusDes = statusDes;
        }

        public void setServiceVendorId(Object serviceVendorId) {
            this.serviceVendorId = serviceVendorId;
        }

        public void setServiceVendorName(String serviceVendorName) {
            this.serviceVendorName = serviceVendorName;
        }

        public void setPaymentType(int paymentType) {
            this.paymentType = paymentType;
        }

        public void setInvoiceNoticeFlag(boolean invoiceNoticeFlag) {
            this.invoiceNoticeFlag = invoiceNoticeFlag;
        }

        public void setOccurDate(String occurDate) {
            this.occurDate = occurDate;
        }

        public void setFilePath(Object filePath) {
            this.filePath = filePath;
        }

        public void setFileId(Object fileId) {
            this.fileId = fileId;
        }

        public void setFileName(Object fileName) {
            this.fileName = fileName;
        }

        public void setRemark(Object remark) {
            this.remark = remark;
        }

        public void setType(int type) {
            this.type = type;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public void setFinanceFlag(boolean financeFlag) {
            this.financeFlag = financeFlag;
        }

        public void setSettleFlag(boolean settleFlag) {
            this.settleFlag = settleFlag;
        }

        public void setTitleId(Object titleId) {
            this.titleId = titleId;
        }

        public void setTitleName(Object titleName) {
            this.titleName = titleName;
        }

        public void setRedFlag(boolean redFlag) {
            this.redFlag = redFlag;
        }

        public void setSalesOrder(Object salesOrder) {
            this.salesOrder = salesOrder;
        }

        public void setSettleNode(int settleNode) {
            this.settleNode = settleNode;
        }

        public void setIsSystem(boolean isSystem) {
            this.isSystem = isSystem;
        }

        public void setFundAccountName(Object fundAccountName) {
            this.fundAccountName = fundAccountName;
        }

        public int getId() {
            return id;
        }

        public Object getOrderCode() {
            return orderCode;
        }

        public Object getOwnerId() {
            return ownerId;
        }

        public Object getSmdServiceId() {
            return smdServiceId;
        }

        public Object getSmdServiceName() {
            return smdServiceName;
        }

        public Object getAccountId() {
            return accountId;
        }

        public Object getAccountName() {
            return accountName;
        }

        public Object getAccountCode() {
            return accountCode;
        }

        public String getCostName() {
            return costName;
        }

        public Object getCostId() {
            return costId;
        }

        public String getCurrency() {
            return currency;
        }

        public double getAmount() {
            return amount;
        }

        public double getUsdRate() {
            return usdRate;
        }

        public double getRmbRate() {
            return rmbRate;
        }

        public double getUsdAmount() {
            return usdAmount;
        }

        public double getRmbAmount() {
            return rmbAmount;
        }

        public int getStatus() {
            return status;
        }

        public Object getStatusDes() {
            return statusDes;
        }

        public Object getServiceVendorId() {
            return serviceVendorId;
        }

        public String getServiceVendorName() {
            return serviceVendorName;
        }

        public int getPaymentType() {
            return paymentType;
        }

        public boolean getInvoiceNoticeFlag() {
            return invoiceNoticeFlag;
        }

        public String getOccurDate() {
            return occurDate;
        }

        public Object getFilePath() {
            return filePath;
        }

        public Object getFileId() {
            return fileId;
        }

        public Object getFileName() {
            return fileName;
        }

        public Object getRemark() {
            return remark;
        }

        public int getType() {
            return type;
        }

        public String getDesc() {
            return desc;
        }

        public boolean getFinanceFlag() {
            return financeFlag;
        }

        public boolean getSettleFlag() {
            return settleFlag;
        }

        public Object getTitleId() {
            return titleId;
        }

        public Object getTitleName() {
            return titleName;
        }

        public boolean getRedFlag() {
            return redFlag;
        }

        public Object getSalesOrder() {
            return salesOrder;
        }

        public int getSettleNode() {
            return settleNode;
        }

        public boolean getIsSystem() {
            return isSystem;
        }

        public Object getFundAccountName() {
            return fundAccountName;
        }
    }

    public static class AgencyPaysEntity {
        /**
         * id : 0
         * orderCode : null
         * ownerId : null
         * smdServiceId : null
         * smdServiceName : null
         * accountId : null
         * accountName : null
         * accountCode : null
         * costName : 代理费
         * costId : null
         * currency : RMB
         * amount : 11155.2
         * usdRate : 0.0
         * rmbRate : 1.0
         * usdAmount : 0.0
         * rmbAmount : 11155.2
         * status : 0
         * statusDes : null
         * serviceVendorId : null
         * serviceVendorName : 天津市利雅得工贸有限公司
         * paymentType : 1
         * invoiceNoticeFlag : false
         * occurDate : 2015-09-14T14:41:26
         * filePath : null
         * fileId : null
         * fileName : null
         * remark : null
         * type : 0
         * desc : 扣除代理费:11,155.20
         * financeFlag : false
         * settleFlag : false
         * titleId : null
         * titleName : null
         * redFlag : false
         * salesOrder : null
         * settleNode : 0
         * isSystem : false
         * fundAccountName : null
         */

        private int id;
        private Object orderCode;
        private Object ownerId;
        private Object smdServiceId;
        private Object smdServiceName;
        private Object accountId;
        private Object accountName;
        private Object accountCode;
        private String costName;
        private Object costId;
        private String currency;
        private double amount;
        private double usdRate;
        private double rmbRate;
        private double usdAmount;
        private double rmbAmount;
        private int status;
        private Object statusDes;
        private Object serviceVendorId;
        private String serviceVendorName;
        private int paymentType;
        private boolean invoiceNoticeFlag;
        private String occurDate;
        private Object filePath;
        private Object fileId;
        private Object fileName;
        private Object remark;
        private int type;
        private String desc;
        private boolean financeFlag;
        private boolean settleFlag;
        private Object titleId;
        private Object titleName;
        private boolean redFlag;
        private Object salesOrder;
        private int settleNode;
        private boolean isSystem;
        private Object fundAccountName;

        public void setId(int id) {
            this.id = id;
        }

        public void setOrderCode(Object orderCode) {
            this.orderCode = orderCode;
        }

        public void setOwnerId(Object ownerId) {
            this.ownerId = ownerId;
        }

        public void setSmdServiceId(Object smdServiceId) {
            this.smdServiceId = smdServiceId;
        }

        public void setSmdServiceName(Object smdServiceName) {
            this.smdServiceName = smdServiceName;
        }

        public void setAccountId(Object accountId) {
            this.accountId = accountId;
        }

        public void setAccountName(Object accountName) {
            this.accountName = accountName;
        }

        public void setAccountCode(Object accountCode) {
            this.accountCode = accountCode;
        }

        public void setCostName(String costName) {
            this.costName = costName;
        }

        public void setCostId(Object costId) {
            this.costId = costId;
        }

        public void setCurrency(String currency) {
            this.currency = currency;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }

        public void setUsdRate(double usdRate) {
            this.usdRate = usdRate;
        }

        public void setRmbRate(double rmbRate) {
            this.rmbRate = rmbRate;
        }

        public void setUsdAmount(double usdAmount) {
            this.usdAmount = usdAmount;
        }

        public void setRmbAmount(double rmbAmount) {
            this.rmbAmount = rmbAmount;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public void setStatusDes(Object statusDes) {
            this.statusDes = statusDes;
        }

        public void setServiceVendorId(Object serviceVendorId) {
            this.serviceVendorId = serviceVendorId;
        }

        public void setServiceVendorName(String serviceVendorName) {
            this.serviceVendorName = serviceVendorName;
        }

        public void setPaymentType(int paymentType) {
            this.paymentType = paymentType;
        }

        public void setInvoiceNoticeFlag(boolean invoiceNoticeFlag) {
            this.invoiceNoticeFlag = invoiceNoticeFlag;
        }

        public void setOccurDate(String occurDate) {
            this.occurDate = occurDate;
        }

        public void setFilePath(Object filePath) {
            this.filePath = filePath;
        }

        public void setFileId(Object fileId) {
            this.fileId = fileId;
        }

        public void setFileName(Object fileName) {
            this.fileName = fileName;
        }

        public void setRemark(Object remark) {
            this.remark = remark;
        }

        public void setType(int type) {
            this.type = type;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public void setFinanceFlag(boolean financeFlag) {
            this.financeFlag = financeFlag;
        }

        public void setSettleFlag(boolean settleFlag) {
            this.settleFlag = settleFlag;
        }

        public void setTitleId(Object titleId) {
            this.titleId = titleId;
        }

        public void setTitleName(Object titleName) {
            this.titleName = titleName;
        }

        public void setRedFlag(boolean redFlag) {
            this.redFlag = redFlag;
        }

        public void setSalesOrder(Object salesOrder) {
            this.salesOrder = salesOrder;
        }

        public void setSettleNode(int settleNode) {
            this.settleNode = settleNode;
        }

        public void setIsSystem(boolean isSystem) {
            this.isSystem = isSystem;
        }

        public void setFundAccountName(Object fundAccountName) {
            this.fundAccountName = fundAccountName;
        }

        public int getId() {
            return id;
        }

        public Object getOrderCode() {
            return orderCode;
        }

        public Object getOwnerId() {
            return ownerId;
        }

        public Object getSmdServiceId() {
            return smdServiceId;
        }

        public Object getSmdServiceName() {
            return smdServiceName;
        }

        public Object getAccountId() {
            return accountId;
        }

        public Object getAccountName() {
            return accountName;
        }

        public Object getAccountCode() {
            return accountCode;
        }

        public String getCostName() {
            return costName;
        }

        public Object getCostId() {
            return costId;
        }

        public String getCurrency() {
            return currency;
        }

        public double getAmount() {
            return amount;
        }

        public double getUsdRate() {
            return usdRate;
        }

        public double getRmbRate() {
            return rmbRate;
        }

        public double getUsdAmount() {
            return usdAmount;
        }

        public double getRmbAmount() {
            return rmbAmount;
        }

        public int getStatus() {
            return status;
        }

        public Object getStatusDes() {
            return statusDes;
        }

        public Object getServiceVendorId() {
            return serviceVendorId;
        }

        public String getServiceVendorName() {
            return serviceVendorName;
        }

        public int getPaymentType() {
            return paymentType;
        }

        public boolean getInvoiceNoticeFlag() {
            return invoiceNoticeFlag;
        }

        public String getOccurDate() {
            return occurDate;
        }

        public Object getFilePath() {
            return filePath;
        }

        public Object getFileId() {
            return fileId;
        }

        public Object getFileName() {
            return fileName;
        }

        public Object getRemark() {
            return remark;
        }

        public int getType() {
            return type;
        }

        public String getDesc() {
            return desc;
        }

        public boolean getFinanceFlag() {
            return financeFlag;
        }

        public boolean getSettleFlag() {
            return settleFlag;
        }

        public Object getTitleId() {
            return titleId;
        }

        public Object getTitleName() {
            return titleName;
        }

        public boolean getRedFlag() {
            return redFlag;
        }

        public Object getSalesOrder() {
            return salesOrder;
        }

        public int getSettleNode() {
            return settleNode;
        }

        public boolean getIsSystem() {
            return isSystem;
        }

        public Object getFundAccountName() {
            return fundAccountName;
        }
    }
}
