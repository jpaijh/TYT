package com.qskj.tyt.entity;

/**
 * 订单详情-附件 实体类
 * Created by 赵 鑫 on 2015/10/22.
 */
public class T_OrderFormDetailsAttachmentsEntity {
//    "attachmentMetaDataId": 1009,
//            "attachmentName": "报关单",
//            "createDate": "2015-10-15T10:50:12",
//            "creator": "凯盟-系统管理员",
//            "fileId": 2975800,
//            "fileName": "2015-09-14-f65f78cf-3340-451b-8bf3-83be423a6c5c (3).docx",
//            "filePath": "2015/10/15/e59ff70e-4c07-4f7a-bb40-a3d0b4832eed.docx",
//            "id": 2975900,
//            "isUpload": true,
//            "needed": false,
//            "orderId": 2938005

    private String attachmentMetaDataId;
    private String attachmentName;
    private String createDate;
    private String creator;
    private String fileId;
    private String fileName;
    private String filePath;
    private String id;
    private String isUpload;
    private String needed;
    private String orderId;

    public T_OrderFormDetailsAttachmentsEntity() {
    }

    public String getAttachmentMetaDataId() {
        return attachmentMetaDataId;
    }

    public void setAttachmentMetaDataId(String attachmentMetaDataId) {
        this.attachmentMetaDataId = attachmentMetaDataId;
    }

    public String getAttachmentName() {
        return attachmentName;
    }

    public void setAttachmentName(String attachmentName) {
        this.attachmentName = attachmentName;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIsUpload() {
        return isUpload;
    }

    public void setIsUpload(String isUpload) {
        this.isUpload = isUpload;
    }

    public String getNeeded() {
        return needed;
    }

    public void setNeeded(String needed) {
        this.needed = needed;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
