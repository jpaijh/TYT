package com.qskj.tyt.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 往来单位 - 我的国内开票单位，我的外商 实体类
 */
public class T_ContactsCompanyEntity {

    /**
     * pageSize : 10
     * total : 183
     * totalPage : 19
     * rows : [{"subApproverName":"卢花","contactWay":"123456789","approverId":2748700,"remark":"系统自动审批",
     * "foreignSinosurePeriodDate":0,"type":0,"handingCharge":null,"currency":null,"foreignSinosureNo":null,"modifiedId":0,
     * "id":3086221,"approveDate":"2015-08-19T15:16:34","subApproveDate":"2015-08-19T15:16:32","registeredAddress":null,
     * "registeredCaption":null,"createDate":"2015-08-19T15:16:31","approverName":"卢花","legalRepresentative":null,
     * "isSignAgreement":true,"payment":null,"status":2,"foreignSinosureCreditBalance":0,"nameEn":"","creatorId":2748700,
     * "countryCode":null,"creditInformation":null,"nameCn":"余天","modifiedDate":null,"country":"中国","creatorName":"卢花",
     * "addressCn":null,"email":null,"subApproverId":2748700,"addressEn":null,"uuid":"余天","refundRates":null,"modifiedName":null,
     * "contactPerson":"余天"}]
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
         * subApproverName : 卢花
         * contactWay : 123456789
         * approverId : 2748700
         * remark : 系统自动审批
         * foreignSinosurePeriodDate : 0
         * type : 0
         * handingCharge : null
         * currency : null
         * foreignSinosureNo : null
         * modifiedId : 0
         * id : 3086221
         * approveDate : 2015-08-19T15:16:34
         * subApproveDate : 2015-08-19T15:16:32
         * registeredAddress : null
         * registeredCaption : null
         * createDate : 2015-08-19T15:16:31
         * approverName : 卢花
         * legalRepresentative : null
         * isSignAgreement : true
         * payment : null
         * status : 2
         * foreignSinosureCreditBalance : 0
         * nameEn :
         * creatorId : 2748700
         * countryCode : null
         * creditInformation : null
         * nameCn : 余天
         * modifiedDate : null
         * country : 中国
         * creatorName : 卢花
         * addressCn : null
         * email : null
         * subApproverId : 2748700
         * addressEn : null
         * uuid : 余天
         * refundRates : null
         * modifiedName : null
         * contactPerson : 余天
         */

        private String subApproverName; // 提交审批人
        private String subApproveDate; // 提交审批时间
        private int subApproverId; // 提交审批人编码
        private String approverName;// 审批人
        private int approverId; // 审批人编码
        private String approveDate; // 审批时间
        private String remark; //备注
        private int type; //类型（0:境内企业; 1:个人; 2:境外企业;）
        private Object handingCharge; //手续费
        private Object currency; // 币别
        private int id; // 编码
        private String registeredAddress; //注册地址
        private double registeredCaption; //注册资金
        private String legalRepresentative; // 法人
        private boolean isSignAgreement; // 是否已与工厂签订服务协议
        private Object payment; //支付方式
        private int status; //状态 0：新制 1：待批，2：审批通过 -2：审批不通过
        private String nameEn; // 英文名
        private int creatorId; //创建人编码
        private String creatorName; //创建人姓名
        private String createDate; //创建时间
        private String creditInformation; // 资信调查信息
        private String nameCn; // 中文名
        private String country; // 所在国家/地区
        private Object countryCode; // 所在国家/地区 编码
        private String addressCn; //公司地址（中文）
        private Object email;// 邮箱
        private Object addressEn; //公司地址（英文）
        private Object refundRates; //退税率
        private String contactPerson; // 联系人
        private String contactWay; //联系方式
        private int foreignSinosurePeriodDate; // 外商中信保账期
        private int foreignSinosureCreditBalance; // 外商中信保剩余额度
        private Object foreignSinosureNo; // 外商信保编号
        private String uuid;
        private int modifiedId;
        private Object modifiedDate;
        private Object modifiedName;

        public void setSubApproverName(String subApproverName) {
            this.subApproverName = subApproverName;
        }

        public void setContactWay(String contactWay) {
            this.contactWay = contactWay;
        }

        public void setApproverId(int approverId) {
            this.approverId = approverId;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public void setForeignSinosurePeriodDate(int foreignSinosurePeriodDate) {
            this.foreignSinosurePeriodDate = foreignSinosurePeriodDate;
        }

        public void setType(int type) {
            this.type = type;
        }

        public void setHandingCharge(Object handingCharge) {
            this.handingCharge = handingCharge;
        }

        public void setCurrency(Object currency) {
            this.currency = currency;
        }

        public void setForeignSinosureNo(Object foreignSinosureNo) {
            this.foreignSinosureNo = foreignSinosureNo;
        }

        public void setModifiedId(int modifiedId) {
            this.modifiedId = modifiedId;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setApproveDate(String approveDate) {
            this.approveDate = approveDate;
        }

        public void setSubApproveDate(String subApproveDate) {
            this.subApproveDate = subApproveDate;
        }

        public void setRegisteredAddress(String registeredAddress) {
            this.registeredAddress = registeredAddress;
        }

        public void setRegisteredCaption(double registeredCaption) {
            this.registeredCaption = registeredCaption;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public void setApproverName(String approverName) {
            this.approverName = approverName;
        }

        public void setLegalRepresentative(String legalRepresentative) {
            this.legalRepresentative = legalRepresentative;
        }

        public void setIsSignAgreement(boolean isSignAgreement) {
            this.isSignAgreement = isSignAgreement;
        }

        public void setPayment(Object payment) {
            this.payment = payment;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public void setForeignSinosureCreditBalance(int foreignSinosureCreditBalance) {
            this.foreignSinosureCreditBalance = foreignSinosureCreditBalance;
        }

        public void setNameEn(String nameEn) {
            this.nameEn = nameEn;
        }

        public void setCreatorId(int creatorId) {
            this.creatorId = creatorId;
        }

        public void setCountryCode(Object countryCode) {
            this.countryCode = countryCode;
        }

        public void setCreditInformation(String creditInformation) {
            this.creditInformation = creditInformation;
        }

        public void setNameCn(String nameCn) {
            this.nameCn = nameCn;
        }

        public void setModifiedDate(Object modifiedDate) {
            this.modifiedDate = modifiedDate;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public void setCreatorName(String creatorName) {
            this.creatorName = creatorName;
        }

        public void setAddressCn(String addressCn) {
            this.addressCn = addressCn;
        }

        public void setEmail(Object email) {
            this.email = email;
        }

        public void setSubApproverId(int subApproverId) {
            this.subApproverId = subApproverId;
        }

        public void setAddressEn(Object addressEn) {
            this.addressEn = addressEn;
        }

        public void setUuid(String uuid) {
            this.uuid = uuid;
        }

        public void setRefundRates(Object refundRates) {
            this.refundRates = refundRates;
        }

        public void setModifiedName(Object modifiedName) {
            this.modifiedName = modifiedName;
        }

        public void setContactPerson(String contactPerson) {
            this.contactPerson = contactPerson;
        }

        public String getSubApproverName() {
            return subApproverName;
        }

        public String getContactWay() {
            return contactWay;
        }

        public int getApproverId() {
            return approverId;
        }

        public String getRemark() {
            return remark;
        }

        public int getForeignSinosurePeriodDate() {
            return foreignSinosurePeriodDate;
        }

        public int getType() {
            return type;
        }

        public Object getHandingCharge() {
            return handingCharge;
        }

        public Object getCurrency() {
            return currency;
        }

        public Object getForeignSinosureNo() {
            return foreignSinosureNo;
        }

        public int getModifiedId() {
            return modifiedId;
        }

        public int getId() {
            return id;
        }

        public String getApproveDate() {
            return approveDate;
        }

        public String getSubApproveDate() {
            return subApproveDate;
        }

        public String getRegisteredAddress() {
            return registeredAddress;
        }

        public double getRegisteredCaption() {
            return registeredCaption;
        }

        public String getCreateDate() {
            return createDate;
        }

        public String getApproverName() {
            return approverName;
        }

        public String getLegalRepresentative() {
            return legalRepresentative;
        }

        public boolean getIsSignAgreement() {
            return isSignAgreement;
        }

        public Object getPayment() {
            return payment;
        }

        public int getStatus() {
            return status;
        }

        public int getForeignSinosureCreditBalance() {
            return foreignSinosureCreditBalance;
        }

        public String getNameEn() {
            return nameEn;
        }

        public int getCreatorId() {
            return creatorId;
        }

        public Object getCountryCode() {
            return countryCode;
        }

        public String getCreditInformation() {
            return creditInformation;
        }

        public String getNameCn() {
            return nameCn;
        }

        public Object getModifiedDate() {
            return modifiedDate;
        }

        public String getCountry() {
            return country;
        }

        public String getCreatorName() {
            return creatorName;
        }

        public String getAddressCn() {
            return addressCn;
        }

        public Object getEmail() {
            return email;
        }

        public int getSubApproverId() {
            return subApproverId;
        }

        public Object getAddressEn() {
            return addressEn;
        }

        public String getUuid() {
            return uuid;
        }

        public Object getRefundRates() {
            return refundRates;
        }

        public Object getModifiedName() {
            return modifiedName;
        }

        public String getContactPerson() {
            return contactPerson;
        }
    }
}
