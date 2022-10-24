
package com.shamshadlive.childappv4.models.login.sendmoney;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class SendMoneyResponse {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private SendMoneyData sendMoneyData;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public SendMoneyData getData() {
        return sendMoneyData;
    }

    public void setData(SendMoneyData sendMoneyData) {
        this.sendMoneyData = sendMoneyData;
    }

}
