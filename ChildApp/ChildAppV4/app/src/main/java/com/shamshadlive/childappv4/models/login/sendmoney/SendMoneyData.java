
package com.shamshadlive.childappv4.models.login.sendmoney;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

;


public class SendMoneyData {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("balance")
    @Expose
    private String balance;
    @SerializedName("log_id")
    @Expose
    private Integer logId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public Integer getLogId() {
        return logId;
    }

    public void setLogId(Integer logId) {
        this.logId = logId;
    }

}
