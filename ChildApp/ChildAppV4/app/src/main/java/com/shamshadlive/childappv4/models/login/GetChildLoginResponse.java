
package com.shamshadlive.childappv4.models.login;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class GetChildLoginResponse {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private UserData userData;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UserData getData() {
        return userData;
    }

    public void setData(UserData userData) {
        this.userData = userData;
    }

}
