package com.qskj.tyt.entity;

import java.util.List;

/**
 * 往来单位详情 更多操作 外商 收汇信息的 实体
 */
public class T_ContactsCompanyDetailsMoreEntity {

    /**
     * totalPage : 1
     * total : 2
     * pageSize : 10
     * pageIndex : 1
     * rows : [{"id":2816203,"bankSlipAccounts":null,"clerkId":2803800,"clerkName":"赵兰兰","accountId":2804200,"accountName":"宁波高新区恒拓户外休闲用品有限公司","leftAmount":0,"leftReceiptAmount":0,"leftForeignBankFee":0,"leftDomesticBankFee":0,"leftOtherFee":0,"financeFlag":false,"endFlag":true,"creatorId":2803800,"creatorName":"赵兰兰","createDate":"2015-04-29T13:20:21","modifiedId":0,"modifiedName":null,"modifiedDate":null,"status":1,"approveStatus":0,"approveRemark":null,"settleFlag":true,"filePath":null,"financeReceive":false,"customerCode":null,"titleName":"宁波联合集团进出口股份有限公司","redFlag":false,"drawee":null,"actualUsdAmount":0,"actualUsdRate":0,"actualRmbAmount":0,"actualRmbRate":0,"leftSettlementAmount":null,"settlementAmount":null,"settlementAmountUsd":null,"accountDes":null,"draweeCountry":null,"receiptBankShotName":null,"isCopy":null,"foreignId":null,"foreignName":null,"code":null,"receiptBank":null,"bankAccount":null,"receiptType":"110","receiptTypeCn":"T/T Before Shipment","receiptNature":"","receiptDate":"2015-04-29T00:00:00","receiptAmount":6288.36,"rmbAmount":38923.69,"rmbRate":6.1898,"usdAmount":6288.36,"usdRate":1,"foreignBankFee":0,"domesticBankFee":0,"netAmount":6288.36,"otherFee":0,"currency":"USD","type":0,"remark":null,"domesticBankAccount":null,"domesticBank":null,"nature":0,"fileId":0,"fileName":null}]
     */

    private int totalPage;
    private int total;
    private int pageSize;
    private int pageIndex;
    /**
     * id : 2816203
     * bankSlipAccounts : null
     * clerkId : 2803800
     * clerkName : 赵兰兰
     * accountId : 2804200
     * accountName : 宁波高新区恒拓户外休闲用品有限公司
     * leftAmount : 0
     * leftReceiptAmount : 0
     * leftForeignBankFee : 0
     * leftDomesticBankFee : 0
     * leftOtherFee : 0
     * financeFlag : false
     * endFlag : true
     * creatorId : 2803800
     * creatorName : 赵兰兰
     * createDate : 2015-04-29T13:20:21
     * modifiedId : 0
     * modifiedName : null
     * modifiedDate : null
     * status : 1
     * approveStatus : 0
     * approveRemark : null
     * settleFlag : true
     * filePath : null
     * financeReceive : false
     * customerCode : null
     * titleName : 宁波联合集团进出口股份有限公司
     * redFlag : false
     * drawee : null
     * actualUsdAmount : 0
     * actualUsdRate : 0
     * actualRmbAmount : 0
     * actualRmbRate : 0
     * leftSettlementAmount : null
     * settlementAmount : null
     * settlementAmountUsd : null
     * accountDes : null
     * draweeCountry : null
     * receiptBankShotName : null
     * isCopy : null
     * foreignId : null
     * foreignName : null
     * code : null
     * receiptBank : null
     * bankAccount : null
     * receiptType : 110
     * receiptTypeCn : T/T Before Shipment
     * receiptNature :
     * receiptDate : 2015-04-29T00:00:00
     * receiptAmount : 6288.36
     * rmbAmount : 38923.69
     * rmbRate : 6.1898
     * usdAmount : 6288.36
     * usdRate : 1
     * foreignBankFee : 0
     * domesticBankFee : 0
     * netAmount : 6288.36
     * otherFee : 0
     * currency : USD
     * type : 0
     * remark : null
     * domesticBankAccount : null
     * domesticBank : null
     * nature : 0
     * fileId : 0
     * fileName : null
     */

    private List<RowsEntity> rows;

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public List<RowsEntity> getRows() {
        return rows;
    }

    public void setRows(List<RowsEntity> rows) {
        this.rows = rows;
    }

    public static class RowsEntity {
        private int id;
        private Object bankSlipAccounts;
        private int clerkId;
        private String clerkName;
        private int accountId;
        private String accountName;
        private int leftAmount;
        private int leftReceiptAmount;
        private int leftForeignBankFee;
        private int leftDomesticBankFee;
        private int leftOtherFee;
        private boolean financeFlag;
        private boolean endFlag;
        private int creatorId;
        private String creatorName;
        private String createDate;
        private int modifiedId;
        private Object modifiedName;
        private Object modifiedDate;
        private int status;
        private int approveStatus;
        private Object approveRemark;
        private boolean settleFlag;
        private Object filePath;
        private boolean financeReceive;
        private Object customerCode;
        private String titleName;
        private boolean redFlag;
        private Object drawee;
        private int actualUsdAmount;
        private int actualUsdRate;
        private int actualRmbAmount;
        private int actualRmbRate;
        private Object leftSettlementAmount;
        private Object settlementAmount;
        private Object settlementAmountUsd;
        private Object accountDes;
        private Object draweeCountry;
        private Object receiptBankShotName;
        private Object isCopy;
        private Object foreignId;
        private Object foreignName;
        private Object code;
        private Object receiptBank;
        private Object bankAccount;
        private String receiptType;
        private String receiptTypeCn;
        private String receiptNature;
        private String receiptDate;
        private double receiptAmount;
        private double rmbAmount;
        private double rmbRate;
        private double usdAmount;
        private int usdRate;
        private int foreignBankFee;
        private int domesticBankFee;
        private double netAmount;
        private int otherFee;
        private String currency;
        private int type;
        private Object remark;
        private Object domesticBankAccount;
        private Object domesticBank;
        private int nature;
        private int fileId;
        private Object fileName;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public Object getBankSlipAccounts() {
            return bankSlipAccounts;
        }

        public void setBankSlipAccounts(Object bankSlipAccounts) {
            this.bankSlipAccounts = bankSlipAccounts;
        }

        public int getClerkId() {
            return clerkId;
        }

        public void setClerkId(int clerkId) {
            this.clerkId = clerkId;
        }

        public String getClerkName() {
            return clerkName;
        }

        public void setClerkName(String clerkName) {
            this.clerkName = clerkName;
        }

        public int getAccountId() {
            return accountId;
        }

        public void setAccountId(int accountId) {
            this.accountId = accountId;
        }

        public String getAccountName() {
            return accountName;
        }

        public void setAccountName(String accountName) {
            this.accountName = accountName;
        }

        public int getLeftAmount() {
            return leftAmount;
        }

        public void setLeftAmount(int leftAmount) {
            this.leftAmount = leftAmount;
        }

        public int getLeftReceiptAmount() {
            return leftReceiptAmount;
        }

        public void setLeftReceiptAmount(int leftReceiptAmount) {
            this.leftReceiptAmount = leftReceiptAmount;
        }

        public int getLeftForeignBankFee() {
            return leftForeignBankFee;
        }

        public void setLeftForeignBankFee(int leftForeignBankFee) {
            this.leftForeignBankFee = leftForeignBankFee;
        }

        public int getLeftDomesticBankFee() {
            return leftDomesticBankFee;
        }

        public void setLeftDomesticBankFee(int leftDomesticBankFee) {
            this.leftDomesticBankFee = leftDomesticBankFee;
        }

        public int getLeftOtherFee() {
            return leftOtherFee;
        }

        public void setLeftOtherFee(int leftOtherFee) {
            this.leftOtherFee = leftOtherFee;
        }

        public boolean isFinanceFlag() {
            return financeFlag;
        }

        public void setFinanceFlag(boolean financeFlag) {
            this.financeFlag = financeFlag;
        }

        public boolean isEndFlag() {
            return endFlag;
        }

        public void setEndFlag(boolean endFlag) {
            this.endFlag = endFlag;
        }

        public int getCreatorId() {
            return creatorId;
        }

        public void setCreatorId(int creatorId) {
            this.creatorId = creatorId;
        }

        public String getCreatorName() {
            return creatorName;
        }

        public void setCreatorName(String creatorName) {
            this.creatorName = creatorName;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public int getModifiedId() {
            return modifiedId;
        }

        public void setModifiedId(int modifiedId) {
            this.modifiedId = modifiedId;
        }

        public Object getModifiedName() {
            return modifiedName;
        }

        public void setModifiedName(Object modifiedName) {
            this.modifiedName = modifiedName;
        }

        public Object getModifiedDate() {
            return modifiedDate;
        }

        public void setModifiedDate(Object modifiedDate) {
            this.modifiedDate = modifiedDate;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getApproveStatus() {
            return approveStatus;
        }

        public void setApproveStatus(int approveStatus) {
            this.approveStatus = approveStatus;
        }

        public Object getApproveRemark() {
            return approveRemark;
        }

        public void setApproveRemark(Object approveRemark) {
            this.approveRemark = approveRemark;
        }

        public boolean isSettleFlag() {
            return settleFlag;
        }

        public void setSettleFlag(boolean settleFlag) {
            this.settleFlag = settleFlag;
        }

        public Object getFilePath() {
            return filePath;
        }

        public void setFilePath(Object filePath) {
            this.filePath = filePath;
        }

        public boolean isFinanceReceive() {
            return financeReceive;
        }

        public void setFinanceReceive(boolean financeReceive) {
            this.financeReceive = financeReceive;
        }

        public Object getCustomerCode() {
            return customerCode;
        }

        public void setCustomerCode(Object customerCode) {
            this.customerCode = customerCode;
        }

        public String getTitleName() {
            return titleName;
        }

        public void setTitleName(String titleName) {
            this.titleName = titleName;
        }

        public boolean isRedFlag() {
            return redFlag;
        }

        public void setRedFlag(boolean redFlag) {
            this.redFlag = redFlag;
        }

        public Object getDrawee() {
            return drawee;
        }

        public void setDrawee(Object drawee) {
            this.drawee = drawee;
        }

        public int getActualUsdAmount() {
            return actualUsdAmount;
        }

        public void setActualUsdAmount(int actualUsdAmount) {
            this.actualUsdAmount = actualUsdAmount;
        }

        public int getActualUsdRate() {
            return actualUsdRate;
        }

        public void setActualUsdRate(int actualUsdRate) {
            this.actualUsdRate = actualUsdRate;
        }

        public int getActualRmbAmount() {
            return actualRmbAmount;
        }

        public void setActualRmbAmount(int actualRmbAmount) {
            this.actualRmbAmount = actualRmbAmount;
        }

        public int getActualRmbRate() {
            return actualRmbRate;
        }

        public void setActualRmbRate(int actualRmbRate) {
            this.actualRmbRate = actualRmbRate;
        }

        public Object getLeftSettlementAmount() {
            return leftSettlementAmount;
        }

        public void setLeftSettlementAmount(Object leftSettlementAmount) {
            this.leftSettlementAmount = leftSettlementAmount;
        }

        public Object getSettlementAmount() {
            return settlementAmount;
        }

        public void setSettlementAmount(Object settlementAmount) {
            this.settlementAmount = settlementAmount;
        }

        public Object getSettlementAmountUsd() {
            return settlementAmountUsd;
        }

        public void setSettlementAmountUsd(Object settlementAmountUsd) {
            this.settlementAmountUsd = settlementAmountUsd;
        }

        public Object getAccountDes() {
            return accountDes;
        }

        public void setAccountDes(Object accountDes) {
            this.accountDes = accountDes;
        }

        public Object getDraweeCountry() {
            return draweeCountry;
        }

        public void setDraweeCountry(Object draweeCountry) {
            this.draweeCountry = draweeCountry;
        }

        public Object getReceiptBankShotName() {
            return receiptBankShotName;
        }

        public void setReceiptBankShotName(Object receiptBankShotName) {
            this.receiptBankShotName = receiptBankShotName;
        }

        public Object getIsCopy() {
            return isCopy;
        }

        public void setIsCopy(Object isCopy) {
            this.isCopy = isCopy;
        }

        public Object getForeignId() {
            return foreignId;
        }

        public void setForeignId(Object foreignId) {
            this.foreignId = foreignId;
        }

        public Object getForeignName() {
            return foreignName;
        }

        public void setForeignName(Object foreignName) {
            this.foreignName = foreignName;
        }

        public Object getCode() {
            return code;
        }

        public void setCode(Object code) {
            this.code = code;
        }

        public Object getReceiptBank() {
            return receiptBank;
        }

        public void setReceiptBank(Object receiptBank) {
            this.receiptBank = receiptBank;
        }

        public Object getBankAccount() {
            return bankAccount;
        }

        public void setBankAccount(Object bankAccount) {
            this.bankAccount = bankAccount;
        }

        public String getReceiptType() {
            return receiptType;
        }

        public void setReceiptType(String receiptType) {
            this.receiptType = receiptType;
        }

        public String getReceiptTypeCn() {
            return receiptTypeCn;
        }

        public void setReceiptTypeCn(String receiptTypeCn) {
            this.receiptTypeCn = receiptTypeCn;
        }

        public String getReceiptNature() {
            return receiptNature;
        }

        public void setReceiptNature(String receiptNature) {
            this.receiptNature = receiptNature;
        }

        public String getReceiptDate() {
            return receiptDate;
        }

        public void setReceiptDate(String receiptDate) {
            this.receiptDate = receiptDate;
        }

        public double getReceiptAmount() {
            return receiptAmount;
        }

        public void setReceiptAmount(double receiptAmount) {
            this.receiptAmount = receiptAmount;
        }

        public double getRmbAmount() {
            return rmbAmount;
        }

        public void setRmbAmount(double rmbAmount) {
            this.rmbAmount = rmbAmount;
        }

        public double getRmbRate() {
            return rmbRate;
        }

        public void setRmbRate(double rmbRate) {
            this.rmbRate = rmbRate;
        }

        public double getUsdAmount() {
            return usdAmount;
        }

        public void setUsdAmount(double usdAmount) {
            this.usdAmount = usdAmount;
        }

        public int getUsdRate() {
            return usdRate;
        }

        public void setUsdRate(int usdRate) {
            this.usdRate = usdRate;
        }

        public int getForeignBankFee() {
            return foreignBankFee;
        }

        public void setForeignBankFee(int foreignBankFee) {
            this.foreignBankFee = foreignBankFee;
        }

        public int getDomesticBankFee() {
            return domesticBankFee;
        }

        public void setDomesticBankFee(int domesticBankFee) {
            this.domesticBankFee = domesticBankFee;
        }

        public double getNetAmount() {
            return netAmount;
        }

        public void setNetAmount(double netAmount) {
            this.netAmount = netAmount;
        }

        public int getOtherFee() {
            return otherFee;
        }

        public void setOtherFee(int otherFee) {
            this.otherFee = otherFee;
        }

        public String getCurrency() {
            return currency;
        }

        public void setCurrency(String currency) {
            this.currency = currency;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public Object getRemark() {
            return remark;
        }

        public void setRemark(Object remark) {
            this.remark = remark;
        }

        public Object getDomesticBankAccount() {
            return domesticBankAccount;
        }

        public void setDomesticBankAccount(Object domesticBankAccount) {
            this.domesticBankAccount = domesticBankAccount;
        }

        public Object getDomesticBank() {
            return domesticBank;
        }

        public void setDomesticBank(Object domesticBank) {
            this.domesticBank = domesticBank;
        }

        public int getNature() {
            return nature;
        }

        public void setNature(int nature) {
            this.nature = nature;
        }

        public int getFileId() {
            return fileId;
        }

        public void setFileId(int fileId) {
            this.fileId = fileId;
        }

        public Object getFileName() {
            return fileName;
        }

        public void setFileName(Object fileName) {
            this.fileName = fileName;
        }
    }
}
