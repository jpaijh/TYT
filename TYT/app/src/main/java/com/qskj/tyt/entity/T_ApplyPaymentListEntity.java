package com.qskj.tyt.entity;

/**
 * 提款申请列表 实体类
 */
public class T_ApplyPaymentListEntity {

    /**
     * code : 201512231812
     * erpCode : null
     * companyId : 2875301
     * companyName : 湖北仟姿蒂妮皮草服饰有限公司
     * applyPaymentAmount : 99630.64
     * remark : null
     * bank : 中国银行
     * bankAccount : 123321
     * fileName : null
     * customFileName : null
     * fileId : null
     * status : 1
     * statusName : 审批中
     * zjzhye : 0
     * zfpje : 0
     * zzpje : 0
     * zfyfpje : 0
     * applyId : 2911907
     * ztkje : 0
     * lssdye : 0
     * sxrzye : 0
     * applyingAmount : 0
     * actualPaymentAmount : 99630.64
     * actualPaymentDate : null
     * createType : 0
     * max : 79228162514264337593543950335
     * currency : RMB
     * rmbRate : 1
     * paymentType : 0
     * costId : 1196403
     * costName : 货款
     * remittanceFee : 0
     * titleId : 2757900
     * titleName : 湖北省凯盟国际贸易有限公司
     * source : 0
     * accountName : 湖北仟姿蒂妮皮草服饰有限公司
     * accountId : 2875001
     * accountCode : L002
     * paymentBankAccount : null
     * paymentBank : null
     * id : 0
     * payCompleted : true
     * payMethod : 183
     * payMethodName : 电汇
     * approvedProcess : 0
     * saleOrders : null
     * paymentSlipOrder : null
     * autoApproval : false
     * fundSource : 0
     * customerApplyCode : null
     * applyDate : 2015-07-23T00:00:00
     * costType : 0
     * creatorId : 2761505
     * creatorName : 刘玲
     * relatedCompanyId : null
     * orderId : null
     * isThreeBusiness : false
     * isAccruedExpenses : null
     */

    private String code;
    private String erpCode;
    private String companyId;
    private String companyName;
    private double applyPaymentAmount;
    private String remark;
    private String bank;
    private String bankAccount;
    private String fileName;
    private String customFileName;
    private String fileId;
    private int status;
    private String statusName;
    private int zjzhye;
    private int zfpje;
    private int zzpje;
    private int zfyfpje;
    private int applyId;
    private int ztkje;
    private int lssdye;
    private int sxrzye;
    private int applyingAmount;
    private double actualPaymentAmount;
    private String actualPaymentDate;
    private int createType;
    private String max;
    private String currency;
    private String rmbRate;
    private int paymentType;
    private int costId;
    private String costName;
    private String remittanceFee;
    private int titleId;
    private String titleName;
    private String source;
    private String accountName;
    private int accountId;
    private String accountCode;
    private String paymentBankAccount;
    private String paymentBank;
    private int id;
    private boolean payCompleted;
    private String payMethod;
    private String payMethodName;
    private String approvedProcess;
    private String saleOrders;
    private String paymentSlipOrder;
    private boolean autoApproval;
    private int fundSource;
    private String customerApplyCode;
    private String applyDate;
    private int costType;
    private int creatorId;
    private String creatorName;
    private String relatedCompanyId;
    private String orderId;
    private boolean isThreeBusiness;
    private String isAccruedExpenses;

    public void setCode(String code) {
        this.code = code;
    }

    public void setErpCode(String erpCode) {
        this.erpCode = erpCode;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setApplyPaymentAmount(double applyPaymentAmount) {
        this.applyPaymentAmount = applyPaymentAmount;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setCustomFileName(String customFileName) {
        this.customFileName = customFileName;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public void setZjzhye(int zjzhye) {
        this.zjzhye = zjzhye;
    }

    public void setZfpje(int zfpje) {
        this.zfpje = zfpje;
    }

    public void setZzpje(int zzpje) {
        this.zzpje = zzpje;
    }

    public void setZfyfpje(int zfyfpje) {
        this.zfyfpje = zfyfpje;
    }

    public void setApplyId(int applyId) {
        this.applyId = applyId;
    }

    public void setZtkje(int ztkje) {
        this.ztkje = ztkje;
    }

    public void setLssdye(int lssdye) {
        this.lssdye = lssdye;
    }

    public void setSxrzye(int sxrzye) {
        this.sxrzye = sxrzye;
    }

    public void setApplyingAmount(int applyingAmount) {
        this.applyingAmount = applyingAmount;
    }

    public void setActualPaymentAmount(double actualPaymentAmount) {
        this.actualPaymentAmount = actualPaymentAmount;
    }

    public void setActualPaymentDate(String actualPaymentDate) {
        this.actualPaymentDate = actualPaymentDate;
    }

    public void setCreateType(int createType) {
        this.createType = createType;
    }

    public void setMax(String max) {
        this.max = max;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setRmbRate(String rmbRate) {
        this.rmbRate = rmbRate;
    }

    public void setPaymentType(int paymentType) {
        this.paymentType = paymentType;
    }

    public void setCostId(int costId) {
        this.costId = costId;
    }

    public void setCostName(String costName) {
        this.costName = costName;
    }

    public void setRemittanceFee(String remittanceFee) {
        this.remittanceFee = remittanceFee;
    }

    public void setTitleId(int titleId) {
        this.titleId = titleId;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }

    public void setSource(String source) {
        this.source = source;
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

    public void setPaymentBankAccount(String paymentBankAccount) {
        this.paymentBankAccount = paymentBankAccount;
    }

    public void setPaymentBank(String paymentBank) {
        this.paymentBank = paymentBank;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPayCompleted(boolean payCompleted) {
        this.payCompleted = payCompleted;
    }

    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod;
    }

    public void setPayMethodName(String payMethodName) {
        this.payMethodName = payMethodName;
    }

    public void setApprovedProcess(String approvedProcess) {
        this.approvedProcess = approvedProcess;
    }

    public void setSaleOrders(String saleOrders) {
        this.saleOrders = saleOrders;
    }

    public void setPaymentSlipOrder(String paymentSlipOrder) {
        this.paymentSlipOrder = paymentSlipOrder;
    }

    public void setAutoApproval(boolean autoApproval) {
        this.autoApproval = autoApproval;
    }

    public void setFundSource(int fundSource) {
        this.fundSource = fundSource;
    }

    public void setCustomerApplyCode(String customerApplyCode) {
        this.customerApplyCode = customerApplyCode;
    }

    public void setApplyDate(String applyDate) {
        this.applyDate = applyDate;
    }

    public void setCostType(int costType) {
        this.costType = costType;
    }

    public void setCreatorId(int creatorId) {
        this.creatorId = creatorId;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public void setRelatedCompanyId(String relatedCompanyId) {
        this.relatedCompanyId = relatedCompanyId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public void setIsThreeBusiness(boolean isThreeBusiness) {
        this.isThreeBusiness = isThreeBusiness;
    }

    public void setIsAccruedExpenses(String isAccruedExpenses) {
        this.isAccruedExpenses = isAccruedExpenses;
    }

    public String getCode() {
        return code;
    }

    public String getErpCode() {
        return erpCode;
    }

    public String getCompanyId() {
        return companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public double getApplyPaymentAmount() {
        return applyPaymentAmount;
    }

    public String getRemark() {
        return remark;
    }

    public String getBank() {
        return bank;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public String getFileName() {
        return fileName;
    }

    public String getCustomFileName() {
        return customFileName;
    }

    public String getFileId() {
        return fileId;
    }

    public int getStatus() {
        return status;
    }

    public String getStatusName() {
        return statusName;
    }

    public int getZjzhye() {
        return zjzhye;
    }

    public int getZfpje() {
        return zfpje;
    }

    public int getZzpje() {
        return zzpje;
    }

    public int getZfyfpje() {
        return zfyfpje;
    }

    public int getApplyId() {
        return applyId;
    }

    public int getZtkje() {
        return ztkje;
    }

    public int getLssdye() {
        return lssdye;
    }

    public int getSxrzye() {
        return sxrzye;
    }

    public int getApplyingAmount() {
        return applyingAmount;
    }

    public double getActualPaymentAmount() {
        return actualPaymentAmount;
    }

    public String getActualPaymentDate() {
        return actualPaymentDate;
    }

    public int getCreateType() {
        return createType;
    }

    public String getMax() {
        return max;
    }

    public String getCurrency() {
        return currency;
    }

    public String getRmbRate() {
        return rmbRate;
    }

    public int getPaymentType() {
        return paymentType;
    }

    public int getCostId() {
        return costId;
    }

    public String getCostName() {
        return costName;
    }

    public String getRemittanceFee() {
        return remittanceFee;
    }

    public int getTitleId() {
        return titleId;
    }

    public String getTitleName() {
        return titleName;
    }

    public String getSource() {
        return source;
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

    public String getPaymentBankAccount() {
        return paymentBankAccount;
    }

    public String getPaymentBank() {
        return paymentBank;
    }

    public int getId() {
        return id;
    }

    public boolean isPayCompleted() {
        return payCompleted;
    }

    public String getPayMethod() {
        return payMethod;
    }

    public String getPayMethodName() {
        return payMethodName;
    }

    public String getApprovedProcess() {
        return approvedProcess;
    }

    public String getSaleOrders() {
        return saleOrders;
    }

    public String getPaymentSlipOrder() {
        return paymentSlipOrder;
    }

    public boolean isAutoApproval() {
        return autoApproval;
    }

    public int getFundSource() {
        return fundSource;
    }

    public String getCustomerApplyCode() {
        return customerApplyCode;
    }

    public String getApplyDate() {
        return applyDate;
    }

    public int getCostType() {
        return costType;
    }

    public int getCreatorId() {
        return creatorId;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public String getRelatedCompanyId() {
        return relatedCompanyId;
    }

    public String getOrderId() {
        return orderId;
    }

    public boolean isIsThreeBusiness() {
        return isThreeBusiness;
    }

    public String getIsAccruedExpenses() {
        return isAccruedExpenses;
    }
}
