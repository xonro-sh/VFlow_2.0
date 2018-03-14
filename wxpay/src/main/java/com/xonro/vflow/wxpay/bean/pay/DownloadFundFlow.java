package com.xonro.vflow.wxpay.bean.pay;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 * 下载资金流水
 * @author louie
 * @date created in 2018-3-14 11:18
 */
public class DownloadFundFlow implements Serializable{
    @JSONField(name = "bill_date")
    private String billDate;

    @JSONField(name = "account_type")
    private String accountType;

    @JSONField(name = "tar_type")
    private String tarType;

    public String getBillDate() {
        return billDate;
    }

    public void setBillDate(String billDate) {
        this.billDate = billDate;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getTarType() {
        return tarType;
    }

    public void setTarType(String tarType) {
        this.tarType = tarType;
    }
}
