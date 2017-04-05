package com.qskj.tyt.entity;

import java.util.List;

public class T_TitleChangeEntity {

    /**
     * totalPage : 1
     * total : 4
     * pageSize : 10
     * pageIndex : 1
     * rows : [{"id":2811600,"code":"T256","name":"宁波联合集团进出口股份有限公司","nameEn":"NINGBO UNITED GROUP IE. & EXP. CO., LTD.","nameShort":null,"address":"宁波市和义路77号23楼","addressEn":"23/F, FORTUNE PLAZA ,77 HEYI ROAD, NINGBO, P.R. CHINA","phone":null,"fax":null,"email":null,"zipCode":null,"hsCode":"3302210017","offerTaxRegistryNumber":"330206732114177","bank":"建行宁波市分行营业部","bankAccount":"33101983679050541948","deleted":false,"disable":false,"signStampFileId":null,"signStampFilePath":null,"specialStampFileId":null,"specialStampFilePath":null,"creatorId":2794701,"creatorName":"王冰","createDate":"2015-04-22T10:28:10","modifierId":2794701,"modifierName":"王冰","modifyDate":"2015-07-10T15:00:32","ownerId":2674400,"ownerName":"宁波联合集团进出口有限公司","ssPolicyNo":null,"ssClientNo":null}]
     */

    private int totalPage;
    private int total;
    private int pageSize;
    private int pageIndex;
    /**
     * id : 2811600
     * code : T256
     * name : 宁波联合集团进出口股份有限公司
     * nameEn : NINGBO UNITED GROUP IE. & EXP. CO., LTD.
     * nameShort : null
     * address : 宁波市和义路77号23楼
     * addressEn : 23/F, FORTUNE PLAZA ,77 HEYI ROAD, NINGBO, P.R. CHINA
     * phone : null
     * fax : null
     * email : null
     * zipCode : null
     * hsCode : 3302210017
     * offerTaxRegistryNumber : 330206732114177
     * bank : 建行宁波市分行营业部
     * bankAccount : 33101983679050541948
     * deleted : false
     * disable : false
     * signStampFileId : null
     * signStampFilePath : null
     * specialStampFileId : null
     * specialStampFilePath : null
     * creatorId : 2794701
     * creatorName : 王冰
     * createDate : 2015-04-22T10:28:10
     * modifierId : 2794701
     * modifierName : 王冰
     * modifyDate : 2015-07-10T15:00:32
     * ownerId : 2674400
     * ownerName : 宁波联合集团进出口有限公司
     * ssPolicyNo : null
     * ssClientNo : null
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
        private boolean isChecked;
        private String code;
        private String name;
        private String nameEn;
        private Object nameShort;
        private String address;
        private String addressEn;
        private Object phone;
        private Object fax;
        private Object email;
        private Object zipCode;
        private String hsCode;
        private String offerTaxRegistryNumber;
        private String bank;
        private String bankAccount;
        private boolean deleted;
        private boolean disable;
        private Object signStampFileId;
        private Object signStampFilePath;
        private Object specialStampFileId;
        private Object specialStampFilePath;
        private int creatorId;
        private String creatorName;
        private String createDate;
        private int modifierId;
        private String modifierName;
        private String modifyDate;
        private int ownerId;
        private String ownerName;
        private Object ssPolicyNo;
        private Object ssClientNo;

        public boolean isChecked() {
            return isChecked;
        }

        public void setChecked(boolean checked) {
            isChecked = checked;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getNameEn() {
            return nameEn;
        }

        public void setNameEn(String nameEn) {
            this.nameEn = nameEn;
        }

        public Object getNameShort() {
            return nameShort;
        }

        public void setNameShort(Object nameShort) {
            this.nameShort = nameShort;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getAddressEn() {
            return addressEn;
        }

        public void setAddressEn(String addressEn) {
            this.addressEn = addressEn;
        }

        public Object getPhone() {
            return phone;
        }

        public void setPhone(Object phone) {
            this.phone = phone;
        }

        public Object getFax() {
            return fax;
        }

        public void setFax(Object fax) {
            this.fax = fax;
        }

        public Object getEmail() {
            return email;
        }

        public void setEmail(Object email) {
            this.email = email;
        }

        public Object getZipCode() {
            return zipCode;
        }

        public void setZipCode(Object zipCode) {
            this.zipCode = zipCode;
        }

        public String getHsCode() {
            return hsCode;
        }

        public void setHsCode(String hsCode) {
            this.hsCode = hsCode;
        }

        public String getOfferTaxRegistryNumber() {
            return offerTaxRegistryNumber;
        }

        public void setOfferTaxRegistryNumber(String offerTaxRegistryNumber) {
            this.offerTaxRegistryNumber = offerTaxRegistryNumber;
        }

        public String getBank() {
            return bank;
        }

        public void setBank(String bank) {
            this.bank = bank;
        }

        public String getBankAccount() {
            return bankAccount;
        }

        public void setBankAccount(String bankAccount) {
            this.bankAccount = bankAccount;
        }

        public boolean isDeleted() {
            return deleted;
        }

        public void setDeleted(boolean deleted) {
            this.deleted = deleted;
        }

        public boolean isDisable() {
            return disable;
        }

        public void setDisable(boolean disable) {
            this.disable = disable;
        }

        public Object getSignStampFileId() {
            return signStampFileId;
        }

        public void setSignStampFileId(Object signStampFileId) {
            this.signStampFileId = signStampFileId;
        }

        public Object getSignStampFilePath() {
            return signStampFilePath;
        }

        public void setSignStampFilePath(Object signStampFilePath) {
            this.signStampFilePath = signStampFilePath;
        }

        public Object getSpecialStampFileId() {
            return specialStampFileId;
        }

        public void setSpecialStampFileId(Object specialStampFileId) {
            this.specialStampFileId = specialStampFileId;
        }

        public Object getSpecialStampFilePath() {
            return specialStampFilePath;
        }

        public void setSpecialStampFilePath(Object specialStampFilePath) {
            this.specialStampFilePath = specialStampFilePath;
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

        public int getModifierId() {
            return modifierId;
        }

        public void setModifierId(int modifierId) {
            this.modifierId = modifierId;
        }

        public String getModifierName() {
            return modifierName;
        }

        public void setModifierName(String modifierName) {
            this.modifierName = modifierName;
        }

        public String getModifyDate() {
            return modifyDate;
        }

        public void setModifyDate(String modifyDate) {
            this.modifyDate = modifyDate;
        }

        public int getOwnerId() {
            return ownerId;
        }

        public void setOwnerId(int ownerId) {
            this.ownerId = ownerId;
        }

        public String getOwnerName() {
            return ownerName;
        }

        public void setOwnerName(String ownerName) {
            this.ownerName = ownerName;
        }

        public Object getSsPolicyNo() {
            return ssPolicyNo;
        }

        public void setSsPolicyNo(Object ssPolicyNo) {
            this.ssPolicyNo = ssPolicyNo;
        }

        public Object getSsClientNo() {
            return ssClientNo;
        }

        public void setSsClientNo(Object ssClientNo) {
            this.ssClientNo = ssClientNo;
        }
    }
}
