package com.qskj.tyt.entity;

import java.io.Serializable;

/**
 * Created by 赵 鑫 on 2015/11/2.
 */
public class UserLoginEntity implements Serializable {


    /**
     * accountName : qianzi
     * email : 32@rf.com
     * latestLoginTime : 2015-11-02T16:49:23
     * departmentId : null
     * pltAccountId : 2875001
     * displayName : null
     * departmentName : null
     * telephone : null
     * pltAccountName : 湖北仟姿蒂妮皮草服饰有限公司
     * activateStatus : false
     * mobile : 13926996331
     * loginName : 2875601
     */

    private String accountName;
    private String email;
    private String latestLoginTime;
    private String departmentId;
    private String pltAccountId;
    private String displayName;
    private String departmentName;
    private String telephone;
    private String pltAccountName;
    private boolean activateStatus;
    private String mobile;
    private String loginName;

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public boolean isActivateStatus() {
        return activateStatus;
    }

    public void setActivateStatus(boolean activateStatus) {
        this.activateStatus = activateStatus;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getPltAccountId() {
        return pltAccountId;
    }

    public void setPltAccountId(String pltAccountId) {
        this.pltAccountId = pltAccountId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLatestLoginTime() {
        return latestLoginTime;
    }

    public void setLatestLoginTime(String latestLoginTime) {
        this.latestLoginTime = latestLoginTime;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPltAccountName() {
        return pltAccountName;
    }

    public void setPltAccountName(String pltAccountName) {
        this.pltAccountName = pltAccountName;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Override
    public String toString() {
        return "UserLoginEntity{" +
                "accountName='" + accountName + '\'' +
                ", email='" + email + '\'' +
                ", latestLoginTime='" + latestLoginTime + '\'' +
                ", departmentId=" + departmentId +
                ", pltAccountId=" + pltAccountId +
                ", displayName='" + displayName + '\'' +
                ", departmentName='" + departmentName + '\'' +
                ", telephone='" + telephone + '\'' +
                ", pltAccountName='" + pltAccountName + '\'' +
                ", activateStatus=" + activateStatus +
                ", mobile='" + mobile + '\'' +
                ", loginName='" + loginName + '\'' +
                '}';
    }
}
