package com.qskj.tyt.entity;

import java.util.List;

/**
 * 消息中心 实体类
 * Created by 赵 鑫 on 2015/9/17.
 */
public class MessageCenterEntity {

    /**
     * pageSize : 10
     * total : 449
     * totalPage : 45
     * rows : [{"typeName":"服务订单操作","sendDate":"2015-08-21T11:07:14",
     * "content":"您的订单【201508211107130001】包含服务项【出口代理,退税融资】已被受理，备注【】，如有疑问请联系: 超级管理员 - ",
     * "id":3092905,"importantFlag":null,"readed":true,"title":"订单【201508211107130001】包含服务项【出口代理,退税融资】已被受理",
     * "creatorName":"超级管理员","receiverId":2763403,"creatorId":2744501,"messageType":1,"readDate":"2015-09-17T10:51:42","type":0}]
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
         * typeName : 服务订单操作
         * sendDate : 2015-08-21T11:07:14
         * content : 您的订单【201508211107130001】包含服务项【出口代理,退税融资】已被受理，备注【】，如有疑问请联系: 超级管理员 -
         * id : 3092905
         * importantFlag : null
         * readed : true
         * title : 订单【201508211107130001】包含服务项【出口代理,退税融资】已被受理
         * creatorName : 超级管理员
         * receiverId : 2763403
         * creatorId : 2744501
         * messageType : 1
         * readDate : 2015-09-17T10:51:42
         * type : 0
         */

        private int type;
        private String typeName; // 消息类别 字符串型
        private String sendDate; // 发送时间
        private String content; // 内容
        private int id;// id
        private Object importantFlag; // 是否是重要标志
        private boolean readed; // 是否阅读
        private String title; // 标题
        private int creatorId; // 创建人id
        private String creatorName; // 创建人
        private int receiverId; // 接收方
        private int messageType; // 消息类型
        private String readDate; // 读取时间

        public void setTypeName(String typeName) {
            this.typeName = typeName;
        }

        public void setSendDate(String sendDate) {
            this.sendDate = sendDate;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setImportantFlag(Object importantFlag) {
            this.importantFlag = importantFlag;
        }

        public void setReaded(boolean readed) {
            this.readed = readed;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setCreatorName(String creatorName) {
            this.creatorName = creatorName;
        }

        public void setReceiverId(int receiverId) {
            this.receiverId = receiverId;
        }

        public void setCreatorId(int creatorId) {
            this.creatorId = creatorId;
        }

        public void setMessageType(int messageType) {
            this.messageType = messageType;
        }

        public void setReadDate(String readDate) {
            this.readDate = readDate;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getTypeName() {
            return typeName;
        }

        public String getSendDate() {
            return sendDate;
        }

        public String getContent() {
            return content;
        }

        public int getId() {
            return id;
        }

        public Object getImportantFlag() {
            return importantFlag;
        }

        public boolean getReaded() {
            return readed;
        }

        public String getTitle() {
            return title;
        }

        public String getCreatorName() {
            return creatorName;
        }

        public int getReceiverId() {
            return receiverId;
        }

        public int getCreatorId() {
            return creatorId;
        }

        public int getMessageType() {
            return messageType;
        }

        public String getReadDate() {
            return readDate;
        }

        public int getType() {
            return type;
        }
    }
}
