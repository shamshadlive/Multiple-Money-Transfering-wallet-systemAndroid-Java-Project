
package com.shamshadlive.parentapplicationv4.models.getusername;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class GetUsernameResponse {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private UsernameData usernameData;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UsernameData getData() {
        return usernameData;
    }

    public void setData(UsernameData usernameData) {
        this.usernameData = usernameData;
    }

}
