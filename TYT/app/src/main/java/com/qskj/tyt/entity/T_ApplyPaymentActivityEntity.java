package com.qskj.tyt.entity;

import java.util.List;

/**
 * 申请提款 实体类
 */
public class T_ApplyPaymentActivityEntity {

    /**
     * pageSize : 10
     * total : 12
     * totalPage : 2
     * rows : [{"signPortrait":null,"subApproverName":"超级管理员","approverId":2744501,"salesName":null,"remark":"。最新审批说明：okla",
     * "statusName":"审批同意","modifyDate":"2015-07-03T13:31:17","type":0,"draweeId":3047700,"paymentBankAccount":null,
     * "costType":0,"paymentDesc":null,"draweeBankAccount":"39509001040006433","customerCode":"zm001",
     * "subApproveDate":"2015-07-03T13:29:47","rmbRate":1,"saleOrders":"zm001CNC15007","remittanceFee":0,
     * "draweeName":"慈溪市远景娱乐用品有限公司","payMethod":"183","ztkje":0,"payBank":null,"finaceFlag":false,"paymentTypeCn":"货款",
     * "paymentBank":null,"status":2,"titleName":"杭州全顺集团有限公司","code":"CNC201502038705","fundSource":0,"approvedProcess":"0",
     * "applyDate":"2015-07-03T00:00:00","modifierId":2744501,"subApproverId":2744501,"lssdye":0,"titleId":1569600,"settleFlag":true,
     * "actualPaymentDate":null,"zjzhye":0,"accountId":2762801,"modifierName":"超级管理员","draweeCode":null,"leftAmount":0,
     * "spjc":"审批通过。","currency":"RMB","id":3065300,"transferCw":true,"actualPaymentAmount":356934.29,
     * "draweeBankName":"中国农业银行慈溪市范市支行","approveDate":"2015-07-03T13:31:17","createDate":"2015-07-03T13:29:37",
     * "approverName":"超级管理员","costId":1196403,"rmbAmount":356934.29,"payment":null,"sxrzye":0,"paymentType":0,
     * "titleNameShort":null,"creatorId":2744501,"slipAmount":0,"applyPaymentAmount":356934.29,"accountName":"周敏",
     * "creatorName":"超级管理员","source":0,"payMethodName":"电汇","erpCode":null,"orderCodes":null,"costName":"货款"}]
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

    public static class RowsEntity {
        /**
         * signPortrait : null
         * subApproverName : 超级管理员
         * approverId : 2744501
         * salesName : null
         * remark : 。最新审批说明：okla
         * statusName : 审批同意
         * modifyDate : 2015-07-03T13:31:17
         * type : 0
         * draweeId : 3047700
         * paymentBankAccount : null
         * costType : 0
         * paymentDesc : null
         * draweeBankAccount : 39509001040006433
         * customerCode : zm001
         * subApproveDate : 2015-07-03T13:29:47
         * rmbRate : 1
         * saleOrders : zm001CNC15007
         * remittanceFee : 0
         * draweeName : 慈溪市远景娱乐用品有限公司
         * payMethod : 183
         * ztkje : 0
         * payBank : null
         * finaceFlag : false
         * paymentTypeCn : 货款
         * paymentBank : null
         * status : 2
         * titleName : 杭州全顺集团有限公司
         * code : CNC201502038705
         * fundSource : 0
         * approvedProcess : 0
         * applyDate : 2015-07-03T00:00:00
         * modifierId : 2744501
         * subApproverId : 2744501
         * lssdye : 0
         * titleId : 1569600
         * settleFlag : true
         * actualPaymentDate : null
         * zjzhye : 0
         * accountId : 2762801
         * modifierName : 超级管理员
         * draweeCode : null
         * leftAmount : 0
         * spjc : 审批通过。
         * currency : RMB
         * id : 3065300
         * transferCw : true
         * actualPaymentAmount : 356934.29
         * draweeBankName : 中国农业银行慈溪市范市支行
         * approveDate : 2015-07-03T13:31:17
         * createDate : 2015-07-03T13:29:37
         * approverName : 超级管理员
         * costId : 1196403
         * rmbAmount : 356934.29
         * payment : null
         * sxrzye : 0
         * paymentType : 0
         * titleNameShort : null
         * creatorId : 2744501
         * slipAmount : 0
         * applyPaymentAmount : 356934.29
         * accountName : 周敏
         * creatorName : 超级管理员
         * source : 0
         * payMethodName : 电汇
         * erpCode : null
         * orderCodes : null
         * costName : 货款
         */

        private int draweeId; // 收款单位ID
        private String draweeName; // 收款单位名称
        private String draweeBankName; // 收款单位开户银行
        private String draweeBankAccount; // 收款单位银行帐号
        private String id; // 预付款编号
        private String code; //预付款编码
        private int status; // 审批状态 新制 = 0,提交 = 1,已认证 = 2,取消 = -2
        private int approverId;// 审批人编码
        private String approvedProcess;  /// 审批进程
        private String approveDate;// 审批时间
        private String approverName; // 审批人
        private int subApproverId; // 提交审批人编码
        private String subApproverName; // 提交审批人
        private String subApproveDate; // 提交审批时间
        private Object signPortrait;
        private Object salesName; // 业务员
        private String saleOrders;// 明细的外销发票号，用,隔开
        private Object orderCodes; //明细的外销发票号，用,隔开,不带发票的金额
        private String remark;
        private String statusName; // 状态名
        private int modifierId; // 修改人编码
        private String modifierName; //修改人
        private String modifyDate; //修改时间
        private int type;
        private Object paymentBankAccount;
        private int costType;
        private Object paymentDesc; // 付款方式说明
        private String customerCode; // 客户编码
        private int rmbRate;
        private int remittanceFee;
        private Object payBank; // 支付银行
        private String payMethod; // 支付方式
        private String payMethodName;// 付款方式Name
        private int ztkje; //总提款金额
        private boolean finaceFlag; // 财务入账标志
        private boolean settleFlag; // 结算标志
        private Object paymentBank;
        private String titleName;
        private int fundSource; // 资金来源
        private String applyDate; // 声请时间
        private int lssdye; //临时水单余额
        private int titleId; // 抬头标志
        private Object payment; // 付款方式
        private int paymentType; // 付款类型（0：货款；1：预付款；2:费用；3：融资款；4：往来款）
        private String paymentTypeCn; // 付款类型（0：货款；1：预付款；2:费用；3：融资款；4：往来款）
        private Object actualPaymentDate; // 实际付款时间
        private double actualPaymentAmount; // 实际付款金额
        private int zjzhye;
        private int accountId; // 账户ID
        private Object draweeCode;
        private int leftAmount; // 剩余未拆分金额
        private String spjc; // 审批进程
        private String currency; ///币别
        private boolean transferCw; // 是否转财务 默认不转
        private String createDate;// 创建时间
        private double rmbAmount; //人民币金额
        private int sxrzye; //赊销融资余额
        private Object titleNameShort;
        private int creatorId; // 创建人编码
        private String creatorName;// 创建人名称
        private int slipAmount; // 拆分金额
        private double applyPaymentAmount; // 申请付款金额
        private String accountName;
        private int source; // 来源（1：客户自助申请，0：后台代客申请）
        private Object erpCode; //ERP付款申请单号
        private int costId;// 费用项ID
        private String costName; // 费用名称

        public void setSignPortrait(Object signPortrait) {
            this.signPortrait = signPortrait;
        }

        public void setSubApproverName(String subApproverName) {
            this.subApproverName = subApproverName;
        }

        public void setApproverId(int approverId) {
            this.approverId = approverId;
        }

        public void setSalesName(Object salesName) {
            this.salesName = salesName;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public void setStatusName(String statusName) {
            this.statusName = statusName;
        }

        public void setModifyDate(String modifyDate) {
            this.modifyDate = modifyDate;
        }

        public void setType(int type) {
            this.type = type;
        }

        public void setDraweeId(int draweeId) {
            this.draweeId = draweeId;
        }

        public void setPaymentBankAccount(Object paymentBankAccount) {
            this.paymentBankAccount = paymentBankAccount;
        }

        public void setCostType(int costType) {
            this.costType = costType;
        }

        public void setPaymentDesc(Object paymentDesc) {
            this.paymentDesc = paymentDesc;
        }

        public void setDraweeBankAccount(String draweeBankAccount) {
            this.draweeBankAccount = draweeBankAccount;
        }

        public void setCustomerCode(String customerCode) {
            this.customerCode = customerCode;
        }

        public void setSubApproveDate(String subApproveDate) {
            this.subApproveDate = subApproveDate;
        }

        public void setRmbRate(int rmbRate) {
            this.rmbRate = rmbRate;
        }

        public void setSaleOrders(String saleOrders) {
            this.saleOrders = saleOrders;
        }

        public void setRemittanceFee(int remittanceFee) {
            this.remittanceFee = remittanceFee;
        }

        public void setDraweeName(String draweeName) {
            this.draweeName = draweeName;
        }

        public void setPayMethod(String payMethod) {
            this.payMethod = payMethod;
        }

        public void setZtkje(int ztkje) {
            this.ztkje = ztkje;
        }

        public void setPayBank(Object payBank) {
            this.payBank = payBank;
        }

        public void setFinaceFlag(boolean finaceFlag) {
            this.finaceFlag = finaceFlag;
        }

        public void setPaymentTypeCn(String paymentTypeCn) {
            this.paymentTypeCn = paymentTypeCn;
        }

        public void setPaymentBank(Object paymentBank) {
            this.paymentBank = paymentBank;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public void setTitleName(String titleName) {
            this.titleName = titleName;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public void setFundSource(int fundSource) {
            this.fundSource = fundSource;
        }

        public void setApprovedProcess(String approvedProcess) {
            this.approvedProcess = approvedProcess;
        }

        public void setApplyDate(String applyDate) {
            this.applyDate = applyDate;
        }

        public void setModifierId(int modifierId) {
            this.modifierId = modifierId;
        }

        public void setSubApproverId(int subApproverId) {
            this.subApproverId = subApproverId;
        }

        public void setLssdye(int lssdye) {
            this.lssdye = lssdye;
        }

        public void setTitleId(int titleId) {
            this.titleId = titleId;
        }

        public void setSettleFlag(boolean settleFlag) {
            this.settleFlag = settleFlag;
        }

        public void setActualPaymentDate(Object actualPaymentDate) {
            this.actualPaymentDate = actualPaymentDate;
        }

        public void setZjzhye(int zjzhye) {
            this.zjzhye = zjzhye;
        }

        public void setAccountId(int accountId) {
            this.accountId = accountId;
        }

        public void setModifierName(String modifierName) {
            this.modifierName = modifierName;
        }

        public void setDraweeCode(Object draweeCode) {
            this.draweeCode = draweeCode;
        }

        public void setLeftAmount(int leftAmount) {
            this.leftAmount = leftAmount;
        }

        public void setSpjc(String spjc) {
            this.spjc = spjc;
        }

        public void setCurrency(String currency) {
            this.currency = currency;
        }

        public void setId(String id) {
            this.id = id;
        }

        public void setTransferCw(boolean transferCw) {
            this.transferCw = transferCw;
        }

        public void setActualPaymentAmount(double actualPaymentAmount) {
            this.actualPaymentAmount = actualPaymentAmount;
        }

        public void setDraweeBankName(String draweeBankName) {
            this.draweeBankName = draweeBankName;
        }

        public void setApproveDate(String approveDate) {
            this.approveDate = approveDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public void setApproverName(String approverName) {
            this.approverName = approverName;
        }

        public void setCostId(int costId) {
            this.costId = costId;
        }

        public void setRmbAmount(double rmbAmount) {
            this.rmbAmount = rmbAmount;
        }

        public void setPayment(Object payment) {
            this.payment = payment;
        }

        public void setSxrzye(int sxrzye) {
            this.sxrzye = sxrzye;
        }

        public void setPaymentType(int paymentType) {
            this.paymentType = paymentType;
        }

        public void setTitleNameShort(Object titleNameShort) {
            this.titleNameShort = titleNameShort;
        }

        public void setCreatorId(int creatorId) {
            this.creatorId = creatorId;
        }

        public void setSlipAmount(int slipAmount) {
            this.slipAmount = slipAmount;
        }

        public void setApplyPaymentAmount(double applyPaymentAmount) {
            this.applyPaymentAmount = applyPaymentAmount;
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

        public void setPayMethodName(String payMethodName) {
            this.payMethodName = payMethodName;
        }

        public void setErpCode(Object erpCode) {
            this.erpCode = erpCode;
        }

        public void setOrderCodes(Object orderCodes) {
            this.orderCodes = orderCodes;
        }

        public void setCostName(String costName) {
            this.costName = costName;
        }

        public Object getSignPortrait() {
            return signPortrait;
        }

        public String getSubApproverName() {
            return subApproverName;
        }

        public int getApproverId() {
            return approverId;
        }

        public Object getSalesName() {
            return salesName;
        }

        public String getRemark() {
            return remark;
        }

        public String getStatusName() {
            return statusName;
        }

        public String getModifyDate() {
            return modifyDate;
        }

        public int getType() {
            return type;
        }

        public int getDraweeId() {
            return draweeId;
        }

        public Object getPaymentBankAccount() {
            return paymentBankAccount;
        }

        public int getCostType() {
            return costType;
        }

        public Object getPaymentDesc() {
            return paymentDesc;
        }

        public String getDraweeBankAccount() {
            return draweeBankAccount;
        }

        public String getCustomerCode() {
            return customerCode;
        }

        public String getSubApproveDate() {
            return subApproveDate;
        }

        public int getRmbRate() {
            return rmbRate;
        }

        public String getSaleOrders() {
            return saleOrders;
        }

        public int getRemittanceFee() {
            return remittanceFee;
        }

        public String getDraweeName() {
            return draweeName;
        }

        public String getPayMethod() {
            return payMethod;
        }

        public int getZtkje() {
            return ztkje;
        }

        public Object getPayBank() {
            return payBank;
        }

        public boolean getFinaceFlag() {
            return finaceFlag;
        }

        public String getPaymentTypeCn() {
            return paymentTypeCn;
        }

        public Object getPaymentBank() {
            return paymentBank;
        }

        public int getStatus() {
            return status;
        }

        public String getTitleName() {
            return titleName;
        }

        public String getCode() {
            return code;
        }

        public int getFundSource() {
            return fundSource;
        }

        public String getApprovedProcess() {
            return approvedProcess;
        }

        public String getApplyDate() {
            return applyDate;
        }

        public int getModifierId() {
            return modifierId;
        }

        public int getSubApproverId() {
            return subApproverId;
        }

        public int getLssdye() {
            return lssdye;
        }

        public int getTitleId() {
            return titleId;
        }

        public boolean getSettleFlag() {
            return settleFlag;
        }

        public Object getActualPaymentDate() {
            return actualPaymentDate;
        }

        public int getZjzhye() {
            return zjzhye;
        }

        public int getAccountId() {
            return accountId;
        }

        public String getModifierName() {
            return modifierName;
        }

        public Object getDraweeCode() {
            return draweeCode;
        }

        public int getLeftAmount() {
            return leftAmount;
        }

        public String getSpjc() {
            return spjc;
        }

        public String getCurrency() {
            return currency;
        }

        public String getId() {
            return id;
        }

        public boolean getTransferCw() {
            return transferCw;
        }

        public double getActualPaymentAmount() {
            return actualPaymentAmount;
        }

        public String getDraweeBankName() {
            return draweeBankName;
        }

        public String getApproveDate() {
            return approveDate;
        }

        public String getCreateDate() {
            return createDate;
        }

        public String getApproverName() {
            return approverName;
        }

        public int getCostId() {
            return costId;
        }

        public double getRmbAmount() {
            return rmbAmount;
        }

        public Object getPayment() {
            return payment;
        }

        public int getSxrzye() {
            return sxrzye;
        }

        public int getPaymentType() {
            return paymentType;
        }

        public Object getTitleNameShort() {
            return titleNameShort;
        }

        public int getCreatorId() {
            return creatorId;
        }

        public int getSlipAmount() {
            return slipAmount;
        }

        public double getApplyPaymentAmount() {
            return applyPaymentAmount;
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

        public String getPayMethodName() {
            return payMethodName;
        }

        public Object getErpCode() {
            return erpCode;
        }

        public Object getOrderCodes() {
            return orderCodes;
        }

        public String getCostName() {
            return costName;
        }
    }
}
