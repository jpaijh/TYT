package com.qskj.tyt.entity;

import java.util.List;

/**
 * 仓单列表 实体类
 * Created by 赵 鑫 on 2015/11/26.
 */
public class CB_ManageListEntity {

    /**
     * totalPage : 3
     * total : 28
     * pageSize : 10
     * pageIndex : 1
     * rows : [{"storageStatus":1,"id":3139401,"code":"201511241003330002","accountId":3009701,"accountCode":null,"accountName":"宁波港东南物流有限公司","accountUserId":3010201,"accountUserName":"npsel","accountUserDeptId":null,"accountUserDeptName":null,"deliveryInvoiceCode":"NUGIE323wwe","deliveryDate":"2015-11-24T15:18:59","latestTimeOfDelivery":"2015-11-23T08:00:00","businessEnterpriseId":0,"businessEnterpriseName":null,"businessEnterpriseCode":null,"businessEnterpriseCustomsCode":null,"containerNo":"","ordOrderExportId":3139701,"blNo":null,"declareEnterpriseId":3009702,"declareEnterpriseCode":"3302480040","declareEnterpriseName":"宁波联合报关有限公司","salesOrder":null,"itemNoteStatus":2983301,"itemNoteStatusDesc":"等待经营单位录入进仓单明细和进仓单附带的报关信息","declareDate":null,"declareNo":null,"approveDate":null,"isGeneratedLink":true,"linkAddress":null,"packingQuantity":3,"leftQuantity":0,"outQuantity":0,"generatedDeclarationFlag":false,"generatedPreDeclarationFlag":false,"customsAreaEntrySended":false,"customsAreaEntryStatus":0,"customsAreaEntryStatusFormat":"未申报","forwarderConfirmed":false},{"storageStatus":6,"id":3139400,"code":"201511241003330001","accountId":3009701,"accountCode":null,"accountName":"宁波港东南物流有限公司","accountUserId":3010201,"accountUserName":"npsel","accountUserDeptId":null,"accountUserDeptName":null,"deliveryInvoiceCode":"KJ25001","deliveryDate":"2015-11-24T10:03:35","latestTimeOfDelivery":"2015-11-23T08:00:00","businessEnterpriseId":3009700,"businessEnterpriseName":"宁波联合集团进出口股份有限公司","businessEnterpriseCode":null,"businessEnterpriseCustomsCode":"3302210017","containerNo":"","ordOrderExportId":3139700,"blNo":"444432","declareEnterpriseId":3009702,"declareEnterpriseCode":"3302480040","declareEnterpriseName":"宁波联合报关有限公司","salesOrder":"343","itemNoteStatus":3011201,"itemNoteStatusDesc":"等待报关行报关操作","declareDate":"2015-11-24T08:00:00","declareNo":null,"approveDate":null,"isGeneratedLink":true,"linkAddress":null,"packingQuantity":11,"leftQuantity":0,"outQuantity":0,"generatedDeclarationFlag":true,"generatedPreDeclarationFlag":true,"customsAreaEntrySended":true,"customsAreaEntryStatus":12,"customsAreaEntryStatusFormat":"放行","forwarderConfirmed":true},{"storageStatus":6,"id":3135200,"code":"201511191249570001","accountId":3009701,"accountCode":null,"accountName":"宁波港东南物流有限公司","accountUserId":3010201,"accountUserName":"npsel","accountUserDeptId":null,"accountUserDeptName":null,"deliveryInvoiceCode":"NPSEL19001","deliveryDate":"2015-11-19T12:49:58","latestTimeOfDelivery":"2015-11-24T08:00:00","businessEnterpriseId":3009700,"businessEnterpriseName":"宁波联合集团进出口股份有限公司","businessEnterpriseCode":null,"businessEnterpriseCustomsCode":"3302210017","containerNo":"","ordOrderExportId":3135600,"blNo":"TD19001","declareEnterpriseId":3009702,"declareEnterpriseCode":"3302480040","declareEnterpriseName":"宁波联合报关有限公司","salesOrder":"WX19001","itemNoteStatus":3011201,"itemNoteStatusDesc":"等待报关行报关操作","declareDate":null,"declareNo":null,"approveDate":null,"isGeneratedLink":true,"linkAddress":null,"packingQuantity":1600,"leftQuantity":0,"outQuantity":0,"generatedDeclarationFlag":true,"generatedPreDeclarationFlag":true,"customsAreaEntrySended":true,"customsAreaEntryStatus":12,"customsAreaEntryStatusFormat":"放行","forwarderConfirmed":true},{"storageStatus":6,"id":3132603,"code":"201511161527190004","accountId":3009701,"accountCode":null,"accountName":"宁波港东南物流有限公司","accountUserId":3010201,"accountUserName":"npsel","accountUserDeptId":null,"accountUserDeptName":null,"deliveryInvoiceCode":"AB000001","deliveryDate":"2015-11-16T16:37:36","latestTimeOfDelivery":"2015-11-25T08:00:00","businessEnterpriseId":3009700,"businessEnterpriseName":"宁波联合集团进出口股份有限公司","businessEnterpriseCode":null,"businessEnterpriseCustomsCode":"3302210017","containerNo":"","ordOrderExportId":3133003,"blNo":"CNNBO446113","declareEnterpriseId":3009702,"declareEnterpriseCode":"3302480040","declareEnterpriseName":"宁波联合报关有限公司","salesOrder":"15NUGQ7656","itemNoteStatus":3011201,"itemNoteStatusDesc":"等待报关行报关操作","declareDate":null,"declareNo":null,"approveDate":null,"isGeneratedLink":true,"linkAddress":null,"packingQuantity":1428,"leftQuantity":0,"outQuantity":0,"generatedDeclarationFlag":true,"generatedPreDeclarationFlag":true,"customsAreaEntrySended":true,"customsAreaEntryStatus":12,"customsAreaEntryStatusFormat":"放行","forwarderConfirmed":true},{"storageStatus":4,"id":3132602,"code":"201511161527190003","accountId":3009701,"accountCode":null,"accountName":"宁波港东南物流有限公司","accountUserId":3010201,"accountUserName":"npsel","accountUserDeptId":null,"accountUserDeptName":null,"deliveryInvoiceCode":"ss1234","deliveryDate":"2015-11-16T16:00:45","latestTimeOfDelivery":"2015-11-24T08:00:00","businessEnterpriseId":3009700,"businessEnterpriseName":"宁波联合集团进出口股份有限公司","businessEnterpriseCode":null,"businessEnterpriseCustomsCode":"3302210017","containerNo":"","ordOrderExportId":3133002,"blNo":null,"declareEnterpriseId":3009702,"declareEnterpriseCode":"3302480040","declareEnterpriseName":"宁波联合报关有限公司","salesOrder":"SS1234","itemNoteStatus":2983305,"itemNoteStatusDesc":"等待仓库收货","declareDate":null,"declareNo":null,"approveDate":null,"isGeneratedLink":true,"linkAddress":null,"packingQuantity":50,"leftQuantity":0,"outQuantity":0,"generatedDeclarationFlag":false,"generatedPreDeclarationFlag":false,"customsAreaEntrySended":false,"customsAreaEntryStatus":0,"customsAreaEntryStatusFormat":"未申报","forwarderConfirmed":false},{"storageStatus":6,"id":3132601,"code":"201511161527190002","accountId":3009701,"accountCode":null,"accountName":"宁波港东南物流有限公司","accountUserId":3010201,"accountUserName":"npsel","accountUserDeptId":null,"accountUserDeptName":null,"deliveryInvoiceCode":"FD1234","deliveryDate":"2015-11-16T15:42:26","latestTimeOfDelivery":"2015-11-23T08:00:00","businessEnterpriseId":3009700,"businessEnterpriseName":"宁波联合集团进出口股份有限公司","businessEnterpriseCode":null,"businessEnterpriseCustomsCode":"3302210017","containerNo":"","ordOrderExportId":3133001,"blNo":null,"declareEnterpriseId":3009702,"declareEnterpriseCode":"3302480040","declareEnterpriseName":"宁波联合报关有限公司","salesOrder":"vdsb","itemNoteStatus":3011201,"itemNoteStatusDesc":"等待报关行报关操作","declareDate":null,"declareNo":null,"approveDate":null,"isGeneratedLink":true,"linkAddress":null,"packingQuantity":50,"leftQuantity":0,"outQuantity":0,"generatedDeclarationFlag":false,"generatedPreDeclarationFlag":true,"customsAreaEntrySended":true,"customsAreaEntryStatus":0,"customsAreaEntryStatusFormat":"未申报","forwarderConfirmed":true},{"storageStatus":6,"id":3132600,"code":"201511161527190001","accountId":3009701,"accountCode":null,"accountName":"宁波港东南物流有限公司","accountUserId":3010201,"accountUserName":"npsel","accountUserDeptId":null,"accountUserDeptName":null,"deliveryInvoiceCode":"SD1223","deliveryDate":"2015-11-16T15:27:20","latestTimeOfDelivery":"2015-11-23T08:00:00","businessEnterpriseId":3009700,"businessEnterpriseName":"宁波联合集团进出口股份有限公司","businessEnterpriseCode":null,"businessEnterpriseCustomsCode":"3302210017","containerNo":"","ordOrderExportId":3133000,"blNo":"TD234234","declareEnterpriseId":3009702,"declareEnterpriseCode":"3302480040","declareEnterpriseName":"宁波联合报关有限公司","salesOrder":"d22333","itemNoteStatus":3011201,"itemNoteStatusDesc":"等待报关行报关操作","declareDate":null,"declareNo":null,"approveDate":null,"isGeneratedLink":true,"linkAddress":null,"packingQuantity":100,"leftQuantity":0,"outQuantity":0,"generatedDeclarationFlag":true,"generatedPreDeclarationFlag":true,"customsAreaEntrySended":true,"customsAreaEntryStatus":12,"customsAreaEntryStatusFormat":"放行","forwarderConfirmed":true},{"storageStatus":3,"id":3131000,"code":"201511121720520001","accountId":3009701,"accountCode":null,"accountName":"宁波港东南物流有限公司","accountUserId":3010201,"accountUserName":"npsel","accountUserDeptId":null,"accountUserDeptName":null,"deliveryInvoiceCode":"JCD120011","deliveryDate":"2015-11-12T17:20:53","latestTimeOfDelivery":"2015-11-23T08:00:00","businessEnterpriseId":3009700,"businessEnterpriseName":"宁波联合集团进出口股份有限公司","businessEnterpriseCode":null,"businessEnterpriseCustomsCode":"3302210017","containerNo":"","ordOrderExportId":3131400,"blNo":null,"declareEnterpriseId":3009702,"declareEnterpriseCode":"3302480040","declareEnterpriseName":"宁波联合报关有限公司","salesOrder":"wx012001","itemNoteStatus":2983304,"itemNoteStatusDesc":"进仓单商品明细提交备案","declareDate":null,"declareNo":null,"approveDate":null,"isGeneratedLink":true,"linkAddress":null,"packingQuantity":500,"leftQuantity":0,"outQuantity":0,"generatedDeclarationFlag":false,"generatedPreDeclarationFlag":false,"customsAreaEntrySended":false,"customsAreaEntryStatus":0,"customsAreaEntryStatusFormat":"未申报","forwarderConfirmed":false},{"storageStatus":2,"id":3129500,"code":"201511111323200001","accountId":3009701,"accountCode":null,"accountName":"宁波港东南物流有限公司","accountUserId":3010201,"accountUserName":"npsel","accountUserDeptId":null,"accountUserDeptName":null,"deliveryInvoiceCode":"KJCS511034","deliveryDate":"2015-11-11T13:23:21","latestTimeOfDelivery":"2015-11-13T08:00:00","businessEnterpriseId":3009700,"businessEnterpriseName":"宁波联合集团进出口股份有限公司","businessEnterpriseCode":null,"businessEnterpriseCustomsCode":"3302210017","containerNo":"","ordOrderExportId":3129900,"blNo":null,"declareEnterpriseId":3009702,"declareEnterpriseCode":"3302480040","declareEnterpriseName":"宁波联合报关有限公司","salesOrder":"45645645645646","itemNoteStatus":2983303,"itemNoteStatusDesc":"等待经营单位录入进仓单明细和进仓单附带的报关信息的确认","declareDate":null,"declareNo":null,"approveDate":null,"isGeneratedLink":true,"linkAddress":null,"packingQuantity":500,"leftQuantity":0,"outQuantity":0,"generatedDeclarationFlag":false,"generatedPreDeclarationFlag":false,"customsAreaEntrySended":false,"customsAreaEntryStatus":0,"customsAreaEntryStatusFormat":"未申报","forwarderConfirmed":false},{"storageStatus":5,"id":3128300,"code":"201511101621020001","accountId":3009701,"accountCode":null,"accountName":"宁波港东南物流有限公司","accountUserId":3010201,"accountUserName":"npsel","accountUserDeptId":null,"accountUserDeptName":null,"deliveryInvoiceCode":"KJCS511028","deliveryDate":"2015-11-10T16:21:04","latestTimeOfDelivery":"2015-11-11T08:00:00","businessEnterpriseId":3009700,"businessEnterpriseName":"宁波联合集团进出口股份有限公司","businessEnterpriseCode":null,"businessEnterpriseCustomsCode":"3302210017","containerNo":"","ordOrderExportId":3128700,"blNo":null,"declareEnterpriseId":3009702,"declareEnterpriseCode":"3302480040","declareEnterpriseName":"宁波联合报关有限公司","salesOrder":"NUG511028","itemNoteStatus":2983306,"itemNoteStatusDesc":"等待报关行发送申报指令","declareDate":null,"declareNo":null,"approveDate":null,"isGeneratedLink":true,"linkAddress":null,"packingQuantity":870,"leftQuantity":0,"outQuantity":0,"generatedDeclarationFlag":false,"generatedPreDeclarationFlag":true,"customsAreaEntrySended":false,"customsAreaEntryStatus":0,"customsAreaEntryStatusFormat":"未申报","forwarderConfirmed":true}]
     */

    private int totalPage;
    private int total;
    private int pageSize;
    private int pageIndex;
    /**
     * storageStatus : 1
     * id : 3139401
     * code : 201511241003330002
     * accountId : 3009701
     * accountCode : null
     * accountName : 宁波港东南物流有限公司
     * accountUserId : 3010201
     * accountUserName : npsel
     * accountUserDeptId : null
     * accountUserDeptName : null
     * deliveryInvoiceCode : NUGIE323wwe
     * deliveryDate : 2015-11-24T15:18:59
     * latestTimeOfDelivery : 2015-11-23T08:00:00
     * businessEnterpriseId : 0
     * businessEnterpriseName : null
     * businessEnterpriseCode : null
     * businessEnterpriseCustomsCode : null
     * containerNo : 
     * ordOrderExportId : 3139701
     * blNo : null
     * declareEnterpriseId : 3009702
     * declareEnterpriseCode : 3302480040
     * declareEnterpriseName : 宁波联合报关有限公司
     * salesOrder : null
     * itemNoteStatus : 2983301
     * itemNoteStatusDesc : 等待经营单位录入进仓单明细和进仓单附带的报关信息
     * declareDate : null
     * declareNo : null
     * approveDate : null
     * isGeneratedLink : true
     * linkAddress : null
     * packingQuantity : 3
     * leftQuantity : 0
     * outQuantity : 0
     * generatedDeclarationFlag : false
     * generatedPreDeclarationFlag : false
     * customsAreaEntrySended : false
     * customsAreaEntryStatus : 0
     * customsAreaEntryStatusFormat : 未申报
     * forwarderConfirmed : false
     */

    private List<RowsEntity> rows;

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public void setRows(List<RowsEntity> rows) {
        this.rows = rows;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public int getTotal() {
        return total;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public List<RowsEntity> getRows() {
        return rows;
    }

    public static class RowsEntity {
        private int storageStatus;
        private int id;
        private String code;
        private int accountId;
        private String accountCode;
        private String accountName;
        private int accountUserId;
        private String accountUserName;
        private String accountUserDeptId;
        private String accountUserDeptName;
        private String deliveryInvoiceCode;
        private String deliveryDate;
        private String latestTimeOfDelivery;
        private int businessEnterpriseId;
        private String businessEnterpriseName;
        private String businessEnterpriseCode;
        private String businessEnterpriseCustomsCode;
        private String containerNo;
        private int ordOrderExportId;
        private String blNo;
        private int declareEnterpriseId;
        private String declareEnterpriseCode;
        private String declareEnterpriseName;
        private String salesOrder;
        private int itemNoteStatus;
        private String itemNoteStatusDesc;
        private String declareDate;
        private String declareNo;
        private String approveDate;
        private boolean isGeneratedLink;
        private String linkAddress;
        private int packingQuantity;
        private int leftQuantity;
        private int outQuantity;
        private boolean generatedDeclarationFlag;
        private boolean generatedPreDeclarationFlag;
        private boolean customsAreaEntrySended;
        private int customsAreaEntryStatus;
        private String customsAreaEntryStatusFormat;
        private boolean forwarderConfirmed;

        public int getStorageStatus() {
            return storageStatus;
        }

        public void setStorageStatus(int storageStatus) {
            this.storageStatus = storageStatus;
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

        public int getAccountId() {
            return accountId;
        }

        public void setAccountId(int accountId) {
            this.accountId = accountId;
        }

        public String getAccountCode() {
            return accountCode;
        }

        public void setAccountCode(String accountCode) {
            this.accountCode = accountCode;
        }

        public String getAccountName() {
            return accountName;
        }

        public void setAccountName(String accountName) {
            this.accountName = accountName;
        }

        public int getAccountUserId() {
            return accountUserId;
        }

        public void setAccountUserId(int accountUserId) {
            this.accountUserId = accountUserId;
        }

        public String getAccountUserName() {
            return accountUserName;
        }

        public void setAccountUserName(String accountUserName) {
            this.accountUserName = accountUserName;
        }

        public String getAccountUserDeptId() {
            return accountUserDeptId;
        }

        public void setAccountUserDeptId(String accountUserDeptId) {
            this.accountUserDeptId = accountUserDeptId;
        }

        public String getAccountUserDeptName() {
            return accountUserDeptName;
        }

        public void setAccountUserDeptName(String accountUserDeptName) {
            this.accountUserDeptName = accountUserDeptName;
        }

        public String getDeliveryInvoiceCode() {
            return deliveryInvoiceCode;
        }

        public void setDeliveryInvoiceCode(String deliveryInvoiceCode) {
            this.deliveryInvoiceCode = deliveryInvoiceCode;
        }

        public String getDeliveryDate() {
            return deliveryDate;
        }

        public void setDeliveryDate(String deliveryDate) {
            this.deliveryDate = deliveryDate;
        }

        public String getLatestTimeOfDelivery() {
            return latestTimeOfDelivery;
        }

        public void setLatestTimeOfDelivery(String latestTimeOfDelivery) {
            this.latestTimeOfDelivery = latestTimeOfDelivery;
        }

        public int getBusinessEnterpriseId() {
            return businessEnterpriseId;
        }

        public void setBusinessEnterpriseId(int businessEnterpriseId) {
            this.businessEnterpriseId = businessEnterpriseId;
        }

        public String getBusinessEnterpriseName() {
            return businessEnterpriseName;
        }

        public void setBusinessEnterpriseName(String businessEnterpriseName) {
            this.businessEnterpriseName = businessEnterpriseName;
        }

        public String getBusinessEnterpriseCode() {
            return businessEnterpriseCode;
        }

        public void setBusinessEnterpriseCode(String businessEnterpriseCode) {
            this.businessEnterpriseCode = businessEnterpriseCode;
        }

        public String getBusinessEnterpriseCustomsCode() {
            return businessEnterpriseCustomsCode;
        }

        public void setBusinessEnterpriseCustomsCode(String businessEnterpriseCustomsCode) {
            this.businessEnterpriseCustomsCode = businessEnterpriseCustomsCode;
        }

        public String getContainerNo() {
            return containerNo;
        }

        public void setContainerNo(String containerNo) {
            this.containerNo = containerNo;
        }

        public int getOrdOrderExportId() {
            return ordOrderExportId;
        }

        public void setOrdOrderExportId(int ordOrderExportId) {
            this.ordOrderExportId = ordOrderExportId;
        }

        public String getBlNo() {
            return blNo;
        }

        public void setBlNo(String blNo) {
            this.blNo = blNo;
        }

        public int getDeclareEnterpriseId() {
            return declareEnterpriseId;
        }

        public void setDeclareEnterpriseId(int declareEnterpriseId) {
            this.declareEnterpriseId = declareEnterpriseId;
        }

        public String getDeclareEnterpriseCode() {
            return declareEnterpriseCode;
        }

        public void setDeclareEnterpriseCode(String declareEnterpriseCode) {
            this.declareEnterpriseCode = declareEnterpriseCode;
        }

        public String getDeclareEnterpriseName() {
            return declareEnterpriseName;
        }

        public void setDeclareEnterpriseName(String declareEnterpriseName) {
            this.declareEnterpriseName = declareEnterpriseName;
        }

        public String getSalesOrder() {
            return salesOrder;
        }

        public void setSalesOrder(String salesOrder) {
            this.salesOrder = salesOrder;
        }

        public int getItemNoteStatus() {
            return itemNoteStatus;
        }

        public void setItemNoteStatus(int itemNoteStatus) {
            this.itemNoteStatus = itemNoteStatus;
        }

        public String getItemNoteStatusDesc() {
            return itemNoteStatusDesc;
        }

        public void setItemNoteStatusDesc(String itemNoteStatusDesc) {
            this.itemNoteStatusDesc = itemNoteStatusDesc;
        }

        public String getDeclareDate() {
            return declareDate;
        }

        public void setDeclareDate(String declareDate) {
            this.declareDate = declareDate;
        }

        public String getDeclareNo() {
            return declareNo;
        }

        public void setDeclareNo(String declareNo) {
            this.declareNo = declareNo;
        }

        public String getApproveDate() {
            return approveDate;
        }

        public void setApproveDate(String approveDate) {
            this.approveDate = approveDate;
        }

        public boolean isGeneratedLink() {
            return isGeneratedLink;
        }

        public void setIsGeneratedLink(boolean isGeneratedLink) {
            this.isGeneratedLink = isGeneratedLink;
        }

        public String getLinkAddress() {
            return linkAddress;
        }

        public void setLinkAddress(String linkAddress) {
            this.linkAddress = linkAddress;
        }

        public int getPackingQuantity() {
            return packingQuantity;
        }

        public void setPackingQuantity(int packingQuantity) {
            this.packingQuantity = packingQuantity;
        }

        public int getLeftQuantity() {
            return leftQuantity;
        }

        public void setLeftQuantity(int leftQuantity) {
            this.leftQuantity = leftQuantity;
        }

        public int getOutQuantity() {
            return outQuantity;
        }

        public void setOutQuantity(int outQuantity) {
            this.outQuantity = outQuantity;
        }

        public boolean isGeneratedDeclarationFlag() {
            return generatedDeclarationFlag;
        }

        public void setGeneratedDeclarationFlag(boolean generatedDeclarationFlag) {
            this.generatedDeclarationFlag = generatedDeclarationFlag;
        }

        public boolean isGeneratedPreDeclarationFlag() {
            return generatedPreDeclarationFlag;
        }

        public void setGeneratedPreDeclarationFlag(boolean generatedPreDeclarationFlag) {
            this.generatedPreDeclarationFlag = generatedPreDeclarationFlag;
        }

        public boolean isCustomsAreaEntrySended() {
            return customsAreaEntrySended;
        }

        public void setCustomsAreaEntrySended(boolean customsAreaEntrySended) {
            this.customsAreaEntrySended = customsAreaEntrySended;
        }

        public int getCustomsAreaEntryStatus() {
            return customsAreaEntryStatus;
        }

        public void setCustomsAreaEntryStatus(int customsAreaEntryStatus) {
            this.customsAreaEntryStatus = customsAreaEntryStatus;
        }

        public String getCustomsAreaEntryStatusFormat() {
            return customsAreaEntryStatusFormat;
        }

        public void setCustomsAreaEntryStatusFormat(String customsAreaEntryStatusFormat) {
            this.customsAreaEntryStatusFormat = customsAreaEntryStatusFormat;
        }

        public boolean isForwarderConfirmed() {
            return forwarderConfirmed;
        }

        public void setForwarderConfirmed(boolean forwarderConfirmed) {
            this.forwarderConfirmed = forwarderConfirmed;
        }
    }
}
