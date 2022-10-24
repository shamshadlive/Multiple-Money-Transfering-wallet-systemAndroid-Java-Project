
package com.shamshadlive.childappv4.models.login.savetransaction;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class GetTransSaveResponse {

    @SerializedName("message")
    @Expose
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
