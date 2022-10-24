
package com.shamshadlive.parentapplicationv4.models.getchild;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class GetChildDataResponse {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private List<ChildKeyData> data = null;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ChildKeyData> getData() {
        return data;
    }

    public void setData(List<ChildKeyData> data) {
        this.data = data;
    }

}
