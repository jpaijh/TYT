package com.qskj.tyt.entity;

public class T_ApplyPaymentWaterSplitEntity {

    /**
     * titleId : 0
     * titleName : null
     * settleFlag : true
     * orderCode : WHXMZ002
     * id : 2909800
     * orderId : null
     * amount : 99630.64
     * actualAmount : 99630.64
     * slipCompleted : false
     * slipedAmount : 320552.41
     * zpAmount : 322051.28
     * currency : USD
     * relatedCompanyId : null
     */

    private int titleId;
    private String titleName;
    private boolean settleFlag;
    private String orderCode;
    private int id;
    private String orderId;
    private double amount;
    private double actualAmount;
    private String slipCompleted;
    private double slipedAmount;
    private double zpAmount;
    private String currency;
    private String relatedCompanyId;

    public int getTitleId() {
        return titleId;
    }

    public void setTitleId(int titleId) {
        this.titleId = titleId;
    }

    public String getTitleName() {
        return titleName;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }

    public boolean isSettleFlag() {
        return settleFlag;
    }

    public void setSettleFlag(boolean settleFlag) {
        this.settleFlag = settleFlag;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getActualAmount() {
        return actualAmount;
    }

    public void setActualAmount(double actualAmount) {
        this.actualAmount = actualAmount;
    }

    public String getSlipCompleted() {
        return slipCompleted;
    }

    public void setSlipCompleted(String slipCompleted) {
        this.slipCompleted = slipCompleted;
    }

    public double getSlipedAmount() {
        return slipedAmount;
    }

    public void setSlipedAmount(double slipedAmount) {
        this.slipedAmount = slipedAmount;
    }

    public double getZpAmount() {
        return zpAmount;
    }

    public void setZpAmount(double zpAmount) {
        this.zpAmount = zpAmount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getRelatedCompanyId() {
        return relatedCompanyId;
    }

    public void setRelatedCompanyId(String relatedCompanyId) {
        this.relatedCompanyId = relatedCompanyId;
    }
}
