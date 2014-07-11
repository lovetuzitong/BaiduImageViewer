package me.nereo.baiduimageview.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2014-07-08.
 */
public class Owner implements Serializable{

    private String userName;
    private String userId;
    private String userSign;
    private String isSelf;
    private String portrait;
    private String isVip;
    private String isLanv;
    private String isJiaju;
    private String orgName;
    private String cert;
    private String budgetNum;
    private String lanvName;
    private String contactName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserSign() {
        return userSign;
    }

    public void setUserSign(String userSign) {
        this.userSign = userSign;
    }

    public String getIsSelf() {
        return isSelf;
    }

    public void setIsSelf(String isSelf) {
        this.isSelf = isSelf;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public String getIsVip() {
        return isVip;
    }

    public void setIsVip(String isVip) {
        this.isVip = isVip;
    }

    public String getIsLanv() {
        return isLanv;
    }

    public void setIsLanv(String isLanv) {
        this.isLanv = isLanv;
    }

    public String getIsJiaju() {
        return isJiaju;
    }

    public void setIsJiaju(String isJiaju) {
        this.isJiaju = isJiaju;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getCert() {
        return cert;
    }

    public void setCert(String cert) {
        this.cert = cert;
    }

    public String getBudgetNum() {
        return budgetNum;
    }

    public void setBudgetNum(String budgetNum) {
        this.budgetNum = budgetNum;
    }

    public String getLanvName() {
        return lanvName;
    }

    public void setLanvName(String lanvName) {
        this.lanvName = lanvName;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }
}
