
package com.shamshadlive.parentapplicationv4.models.getupi;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetUpiResponse {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private UpiData upiData;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UpiData getData() {
        return upiData;
    }

    public void setData(UpiData upiData) {
        this.upiData = upiData;
    }

}
