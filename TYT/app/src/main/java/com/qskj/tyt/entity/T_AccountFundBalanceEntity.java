package com.qskj.tyt.entity;

/**
 * 账户资金余额 实体类
 */
public class T_AccountFundBalanceEntity {

    /**
     * amount : 6602.59
     * fundAccountName : 1
     * currency : RMB
     */

    private double amount;
    private int fundAccountName;
    private String currency;

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setFundAccountName(int fundAccountName) {
        this.fundAccountName = fundAccountName;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public double getAmount() {
        return amount;
    }

    public int getFundAccountName() {
        return fundAccountName;
    }

    public String getCurrency() {
        return currency;
    }
}
